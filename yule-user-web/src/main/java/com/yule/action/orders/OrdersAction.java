package com.yule.action.orders;

import java.math.BigDecimal;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.CommentConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.OrdersConst;
import com.yule.constant.PageConst;
import com.yule.constant.ScoreConst;
import com.yule.constant.TimeConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.pojo.UserScore;
import com.yule.mongo.user.query.OrdersQuery;
import com.yule.mongo.user.service.IOrdersMongo;
import com.yule.mongo.user.service.IUserScoreMongo;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.user.service.ICompanyService;
import com.yule.user.vo.CompanyVO;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
public class OrdersAction extends BaseAction{
	
	@Autowired
	private IOrdersMongo ordersMongoImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private IUserScoreMongo userScoreMongoImpl;

	@RequestMapping(value = "/orders")
	public String findOrders(OrdersQuery orderQuery,@RequestParam(value="pageNo",required=false)Integer pageNo) throws Exception {
		if(null==pageNo){
			pageNo = 1;
		}
		orderQuery.setUserId(getCookieValue());
		Page<Orders> page = ordersMongoImpl.findOrdersPage(orderQuery, PageConst.PAGE_SIZE_TEN, pageNo);
		StringBuffer htmls = new StringBuffer("");
		if(page.getRowCount()>0){
			List<Orders> orderss = page.getDatas();
			for(Orders orders:orderss){
				htmls.append("<tr>");
				htmls.append("<td> <a href=\"javascript:;\" data-href=\"/findOrdersDetails.do?order_num=\" data-id=\""+orders.getOrder_num()+"\">"+orders.getOrder_num()+"</a></td>");
				htmls.append("<td><a href=\"#\">"+orders.getCompany_name()+"</a></td>");
				htmls.append("<td>"+orders.getProduct_name()+"</td>");
				htmls.append("<td>"+DateUtil.DateToString(orders.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+"</td>");
				htmls.append("<td>"+OrdersConst.ORDERS[orders.getStatus()]+"</td>");
				if(CommentConst.IS_COMMENT_FALSE==orders.getIs_comment()&&OrdersConst.ORDERS_STATUS_THREE == orders.getStatus()){
					htmls.append("<td><a href=\"javascript:;\" data-href=\"/comment/findComment.do?order_num=\" data-id=\""+orders.getOrder_num()+"\">评论</a></td>");
				}else if(CommentConst.IS_COMMENT_TRUE==orders.getIs_comment()){
					htmls.append("<td><a href=\"javascript:;\">查看评论</a></td>");
				}else{
					htmls.append("<td>&nbsp;</td>");
				}
				htmls.append("</tr>");
			}
			orderss.clear();
		}else{
			htmls.append("<tr>");
			htmls.append("<td colspan=\"6\">没有找到预订记录,预订后可查看</td>");
			htmls.append("</tr>");
		}
		request.setAttribute("pageHtmls", PaginationUtil.getPaginationHtml(page));
		request.setAttribute("htmls", htmls.toString());
		request.setAttribute("orderQuery", orderQuery);
		return "orders";
	}
	@RequestMapping(value = "/findOrdersDetails",method=RequestMethod.GET)
	public String findOrders(@RequestParam(value="order_num",required=false)String order_num) throws Exception {
		if (!StringUtils.isEmpty(order_num)) {
			Orders orders = ordersMongoImpl.findOrdersByOrderNum(order_num);
			if(null != orders){
				StringBuffer buttonHtml = new StringBuffer();
				buttonHtml.append("<a class=\"b-button w165 text-center\" target=\"_blank\"href=\"http://detail.yuleing.com/company/findCompanyDetails.do?id="+orders.getCompany_id()+"\"> 再次预订</a>");
			if(orders.getStatus() < OrdersConst.ORDERS_STATUS_TWO){
				buttonHtml.append("<a class=\"b-button w165 text-center\" data-cancle=\""+orders.getOrder_num()+"\"href=\"javascript:;\"> 取消订单</a>");
			}else if(orders.getStatus() == OrdersConst.ORDERS_STATUS_THREE && orders.getIs_comment() != CommentConst.IS_COMMENT_TRUE){
				buttonHtml.append("<a class=\"b-button w165 text-center\" href=\"/comment/findComment.do?order_num="+order_num+"\"> 我要点评</a>");
			}
			if(orders.getIs_comment() == CommentConst.IS_COMMENT_TRUE){
				request.setAttribute("isComment", "已评论");
			}else{
				request.setAttribute("isComment", "未评论");
			}
			if (orders.getUser_score() != null && orders.getUser_score() > 0) {
				request.setAttribute("user_score", orders.getUser_score());
			}else{
				request.setAttribute("user_score", "未使用积分");
			}
			if(orders.getEstimate_arrive_time() != null){
				request.setAttribute("emarrive_time", DateUtil.DateToString(orders.getEstimate_arrive_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN));	
			}
			if(orders.getArrive_time() != null) {
				request.setAttribute("arrive_time", DateUtil.DateToString(orders.getArrive_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN));
			}else{
				request.setAttribute("arrive_time", "未到店");
			}
			CompanyVO company = companyServiceImpl.findCompanyVOById(orders.getCompany_id());
			request.setAttribute("comapny", company);
			request.setAttribute("order_status", OrdersConst.ORDERS[orders.getStatus()]);
			request.setAttribute("create_time", DateUtil.DateToString(orders.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN));
			request.setAttribute("buttomHtml", buttonHtml);
			request.setAttribute("expense_money", new BigDecimal(orders.getExpense_money()).toString());
			request.setAttribute("orders", orders);
				return "user-order-detail";
			}
		}
		return ActionReturnConst.REDIRECT+"/orders.do";
	}
	@RequestMapping(value = "/ordersCancle",method=RequestMethod.POST)
	public String ordersCancle(@RequestParam(value="order_num",required=false)String order_num) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", ErrorConst.STATUS_ERROR);
		try {
			Orders orders = this.ordersMongoImpl.findOrdersByOrderNum(order_num);
			String okey = RedisConst.COMPANY_ORDERS_NUM + orders.getCompany_id();
			if (orders!= null && orders.getStatus() < OrdersConst.ORDERS_STATUS_TWO) {
				if (JedisUtil.getInstance().exists(okey)&&orders.getStatus() == OrdersConst.ORDERS_STATUS_ZERO) {
					int count = Integer.parseInt(JedisUtil.getInstance().get(okey));
					if((count - 1) < 0){
						count = 0;
					}else{
						count = count - 1;
					}
					JedisUtil.getInstance().set(okey, String.valueOf(count));
				}
				orders.setStatus(OrdersConst.ORDERS_STATUS_FIVE);
				orders.setUpdate_time(DateUtil.getCurrentDate());
				boolean flag = this.ordersMongoImpl.updateOrdersByOrderNum(orders);
				if (flag && orders.getUser_score() != null) {
					UserScore userScore = new UserScore();
					userScore.setScore(orders.getUser_score());
					userScore.setStatus(ScoreConst.STATUS_IN);
					userScore.setType(ScoreConst.TYPE_FOUR);
					userScore.setUser_id(orders.getUser_id());
					userScore.setCreate_time(DateUtil.getCurrentDate());
					userScoreMongoImpl.insertUserScore(userScore);
				}
				jsonObject.put("status", ErrorConst.STATUS_SUCCESS);
				String userOrderKey = RedisConst.USER_ORDERS+orders.getUser_id();
				if(!JedisUtil.getInstance().exists(userOrderKey)){
					JedisUtil.getInstance().set(userOrderKey, "1",TimeConst.FIVE_MINUTE);
				}else{
					String orderNum = JedisUtil.getInstance().get(userOrderKey);
					int orderNumInt = Integer.parseInt(orderNum);
					JedisUtil.getInstance().set(userOrderKey, String.valueOf(orderNumInt+1),TimeConst.FIVE_MINUTE);
				}
			}
		} catch (Exception e) {
			new YuleException(e);
			e.printStackTrace();
		}finally{
			outputResult(jsonObject.toString());
		}
		return null;
	}
}