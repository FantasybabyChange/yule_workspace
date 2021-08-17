package com.yule.action.orders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.company.service.ICompanyService;
import com.yule.company.service.IProductService;
import com.yule.company.service.IUserService;
import com.yule.company.vo.UserVO;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.CommentConst;
import com.yule.constant.JSONConst;
import com.yule.constant.OrdersConst;
import com.yule.constant.PageConst;
import com.yule.constant.ScoreConst;
import com.yule.constant.StatusConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.company.service.ICompanyCommentMongo;
import com.yule.mongo.company.service.ICompanyCommentPointMongo;
import com.yule.mongo.company.service.IOrdersMongo;
import com.yule.mongo.company.service.IUserExpenseMongo;
import com.yule.mongo.company.service.IUserScoreMongo;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.mongo.pojo.CompanyCommentPoint;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.pojo.UserExpense;
import com.yule.mongo.pojo.UserScore;
import com.yule.mongo.query.OrdersQuery;
import com.yule.pojo.User;
import com.yule.pojo.UserLevel;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CompanyCommentUtil;
import com.yule.util.CompanyLogUtil;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@RequestMapping("/orders")
public class OrdersAction extends BaseAction{

	@Autowired  
	private IOrdersMongo ordersMongoImpl;
	
	@Autowired
	private IProductService productServiceImpl;
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	@Autowired
	private ICompanyCommentMongo companyCommentMongoImpl;
	@Autowired
	private ICompanyCommentPointMongo companyCommentPointMongo;
	
	@Autowired
	private IUserScoreMongo userScoreMongoImpl;
	
	@Autowired
	private IUserExpenseMongo userExpenseMongoImpl;
	
	@Autowired
	private IUserService userServiceImpl;
	/**
	 * 查询订单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findOrders")
	public String findOrders(OrdersQuery ordersQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception {
		if(null==pageNo){
			return "orders/index";
		}
		ordersQuery.setCompany_id(getCompanyUser().getCompany_id());
		JSONObject obj = new JSONObject();
		try {
			Page<Orders> page = ordersMongoImpl.findOrdersPage(ordersQuery,PageConst.PAGE_SIZE_TEN, pageNo);
			StringBuffer tfoot = new StringBuffer();
			StringBuffer tbody = new StringBuffer();
			tfoot.append("<tr><td colspan=\"9\">");
			tfoot.append("<div class=\"bulk-actions align-left\"></div>");
			tfoot.append(PaginationUtil.getPaginationHtml(page));
			tfoot.append("<div class=\"clear\"></div>");
			tfoot.append("</td></tr>");
			if(page.getRowCount()>0){
				for(Orders orders : page.getDatas()){
					tbody.append("<tr>");
					tbody.append("<td><a href=\"#order-wizard\" modal-order=\"\"  data-toggle=\"modal\"  modal-dialog=\""+orders.getOrder_num()+"\">"+orders.getOrder_num()+"</a></td>");
					tbody.append("<td>"+orders.getProduct_name()+"</td>");
					tbody.append("<td>"+orders.getCustomer_name()+"</td>");
					if (!StringUtils.isEmpty(orders.getUser_score())) {
						tbody.append("<td>"+orders.getUser_score()+"</td>");
					} else {
						tbody.append("<td>未使用积分</td>");
					}
					tbody.append("<td>"+orders.getCustomer_phone()+"</td>");
					tbody.append("<td expnese-change=\"\">"+new BigDecimal(orders.getExpense_money()).toString()+"</td>");
					tbody.append("<td><span class=\"label label-sm label-success\" status-change=\"\">"+OrdersConst.ORDERS[orders.getStatus()]+"</span></td>");
					tbody.append("<td>"+DateUtil.DateToString(orders.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+"</td>");
					tbody.append("<td>");
					tbody.append("<div class=\"hidden-sm hidden-xs btn-group\">");
					if(orders.getIs_comment() != null && orders.getIs_comment() == CommentConst.IS_COMMENT_TRUE){
						tbody.append("<a href=\"#comment-wizard\" modal-comment=\"\" data-toggle=\"modal\"  modal-dialog=\""+orders.getOrder_num()+"\"class=\"btn btn-xs btn-success\">查看评论</a>");	
					}else{
						tbody.append("&nbsp");
					}
					tbody.append("</div>");
					tbody.append("</td>");
					tbody.append("</tr>");					
				}
			}else{
				tbody.append("<tr>");
				tbody.append("<td colspan=\"9\">没有数据</td>");
				tbody.append("</tr>");	
			}
			obj.put("tfoot", tfoot.toString());
			obj.put("tbody", tbody.toString());
			outputResult(obj.toString());
			tfoot.setLength(0);
			tbody.setLength(0);
			obj.clear();
			obj = null;
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return null;
	}
	/**
	 * 更新订单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateOrders",method = RequestMethod.POST)
	public String updateOrders(@ModelAttribute("orders")Orders orders) throws Exception {
		try {
			ordersMongoImpl.updateOrders(orders);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/findOrders.do";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateOrdersStatus",method = RequestMethod.POST)
	public String updateOrdersStatus(@ModelAttribute("orders")Orders orders) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Orders order = this.ordersMongoImpl.findOrdersByNum(orders.getOrder_num());
			if(order.getStatus()==OrdersConst.ORDERS_STATUS_FIVE){
				jsonObject.put("status", order.getStatus());
				return null;
			}
			if (orders.getStatus() == OrdersConst.ORDERS_STATUS_TWO) {
				orders.setArrive_time(DateUtil.getCurrentDate());	
			}
			orders.setUpdate_time(DateUtil.getCurrentDate());
			ordersMongoImpl.updateOrdersByNum(orders);
			jsonObject.put("status", orders.getStatus());
		    order = this.ordersMongoImpl.findOrdersByNum(orders.getOrder_num());
			if(order.getStatus()==OrdersConst.ORDERS_STATUS_THREE&&!StringUtils.isEmpty(order.getExpense_money())){
				//消费
				UserExpense userExpense =new UserExpense();
				userExpense.setCompany_id(order.getCompany_id());
				userExpense.setCompany_name(order.getCompany_name());
				userExpense.setMoney(order.getExpense_money());
				userExpense.setOrder_num(order.getOrder_num());
				userExpense.setUser_id(order.getUser_id());
				userExpense.setUser_name(order.getCustomer_name());
				userExpense.setCreate_time(DateUtil.getCurrentDate());
				this.userExpenseMongoImpl.insertUserExpense(userExpense);
				//获得该用户消费
				Double user_expense_sum = this.userExpenseMongoImpl.findUserExpenseByuserId(order.getUser_id());
				UserVO userVO = this.userServiceImpl.findUserVOById(order.getUser_id());
				User user =new User();
				user.setId(order.getUser_id());
				
				UserScore userScore = new UserScore();
				int user_score =(int) ((100+userVO.getScore_ratio())*order.getExpense_money()/100);
				userScore.setScore(user_score);
				userScore.setStatus(ScoreConst.STATUS_IN);
				userScore.setType(ScoreConst.TYPE_THREE);
				userScore.setUser_id(order.getUser_id());
				userScore.setCreate_time(DateUtil.getCurrentDate());
				this.userScoreMongoImpl.insertUserScore(userScore);
				
				//遍历用户等级，根据消费升级
				StringBuffer user_evel_id =new StringBuffer();
				List<UserLevel> userLevels = new ArrayList<UserLevel>();
				if(JedisUtil.getInstance().exists(RedisConst.USER_LEVEL)){
					userLevels.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.USER_LEVEL)),new UserLevel(),JSONConst.JSON_CONFIG));
				}
				if (userLevels.size()>0) {
					for (UserLevel userLevel : userLevels) {
						if(user_expense_sum>=userLevel.getExpense()){
							user_evel_id.setLength(0);
							user_evel_id.append(userLevel.getId());
						}
					}
				}
				if (!StringUtils.isEmpty(user_evel_id)&&!userVO.getUser_level_id().equals(user_evel_id.toString())) {
					user.setUser_level_id(user_evel_id.toString());
					this.userServiceImpl.updateUserLevel(user);
					user_evel_id.setLength(0);
					user_evel_id = null;
				}
			}
			
			if (order.getStatus()>OrdersConst.ORDERS_STATUS_THREE&&!StringUtils.isEmpty(order.getUser_score())) {
				UserScore userScore = new UserScore();
				userScore.setScore(order.getUser_score());
				userScore.setStatus(ScoreConst.STATUS_IN);
				userScore.setType(ScoreConst.TYPE_FOUR);
				userScore.setUser_id(order.getUser_id());
				userScore.setCreate_time(DateUtil.getCurrentDate());
				this.userScoreMongoImpl.insertUserScore(userScore);
			}
			if (orders.getStatus() == OrdersConst.ORDERS_STATUS_ONE) {
				String okey = RedisConst.COMPANY_ORDERS_NUM + getCompanyUser().getCompany_id();
				if (JedisUtil.getInstance().exists(okey)) {
					int count = Integer.parseInt(JedisUtil.getInstance().get(okey));
					if((count - 1) < 0){
						count = 0;
					}else{
						count = count - 1;
					}
					JedisUtil.getInstance().set(okey, String.valueOf(count));
				}
			}
			
			CompanyLogUtil.insertLog("更改订单状态", getCompanyUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("ajax更改订单状态出现异常",e);
			throw e;
		}finally{
			outputResult(jsonObject.toString());
			jsonObject.clear();
			jsonObject=null;
		}
		return null;
	}
	
	@RequestMapping(value = "/findOrdersDetailsData",method = RequestMethod.POST)
	public String findOrdersDetailsData(@RequestParam(value="order_num",required=false)String order_num)throws Exception{
		JSONObject json = new JSONObject();
		try {
			StringBuffer detailsHTML = new StringBuffer();
			Orders orders = ordersMongoImpl.findOrdersByNum(order_num);
			if (orders != null) {
				detailsHTML.append("<div class=\"alert alert-warning text\">");
				detailsHTML.append("订单编号:"+orders.getOrder_num()+"<br>");
				detailsHTML.append("产品名称:"+orders.getProduct_name()+"<br>");
				if (orders.getEstimate_arrive_time() != null) {
					detailsHTML.append("最晚到店时间:"+DateUtil.DateToString(orders.getEstimate_arrive_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+"<br>");	
				}
				if (orders.getArrive_time() != null) {
					detailsHTML.append("到店时间:"+DateUtil.DateToString(orders.getArrive_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+"<br>");	
				}
				 if(orders.getStatus() == OrdersConst.ORDERS_STATUS_THREE){
					detailsHTML.append("消费金额:"+orders.getExpense_money()+"<br>");	
				}
				detailsHTML.append("客户名称:"+orders.getCustomer_name()+"<br>");
				detailsHTML.append("客户电话:"+orders.getCustomer_phone()+"<br>");	
				detailsHTML.append("特殊要求:"+orders.getDesc()+"<br>");
				if (orders.getCreate_time() != null) {
					detailsHTML.append("创建时间:"+DateUtil.DateToString(orders.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+"</div>");					
				}
				if (orders.getStatus() == OrdersConst.ORDERS_STATUS_TWO) {
					detailsHTML.append("消费金额:<input type=\"text\" name=\"expense_money\"/>");	
				}
				json.put("orderStatus",orders.getStatus());
			}
			json.put("detailsHTML", detailsHTML.toString());
			detailsHTML.setLength(0);
			outputResult(json.toString());
			json.clear();
			json=null;
		} catch (Exception e) {		
				throw e;
		}
		return null;
	}
	
	@RequestMapping(value = "/findOrdersComment",method = RequestMethod.POST)
	public String findOrdersComment(@RequestParam(value="order_num",required=false)String order_num)throws Exception{
		JSONObject obj = new JSONObject();
		try {
			StringBuffer tbody = new StringBuffer();
			CompanyComment companyComment = companyCommentMongoImpl.findCompanyCommentByOrderNum(order_num);
			List<CompanyCommentPoint> companyCommentPoint = this.companyCommentPointMongo.findCompanyCommentPoint(order_num);
			if(companyComment != null){
				CompanyCommentUtil.getCommentHTML(tbody, companyComment,companyCommentPoint);	
				obj.put("tbody", tbody.toString());
				obj.put("status", StatusConst.STATUS_TRUE);
			}else{
				obj.put("status", StatusConst.STATUS_FALSE);
			}
			tbody.setLength(0);
			outputResult(obj.toString());
			obj.clear();
			obj = null;
		} catch (Exception e) {
			new YuleException("查询订单评论出现异常",e);
			throw e;
		}
		return null;
	}
	
}
