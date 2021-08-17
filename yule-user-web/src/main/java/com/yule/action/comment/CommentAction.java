package com.yule.action.comment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.CommentConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.OrdersConst;
import com.yule.constant.ScoreConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.mongo.pojo.CompanyCommentPoint;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.pojo.UserScore;
import com.yule.mongo.user.service.ICompanyCommentMongo;
import com.yule.mongo.user.service.ICompanyCommentPointMongo;
import com.yule.mongo.user.service.IOrdersMongo;
import com.yule.mongo.user.service.IUserScoreMongo;
import com.yule.pojo.CompanyCommentCategory;
import com.yule.pojo.CompanyPointCategory;
import com.yule.user.param.InsertCompanyCommentParam;
import com.yule.user.service.ICompanyCommentCategoryService;
import com.yule.user.service.ICompanyPointCategoryService;
import com.yule.util.CompanyPointCategoryUtil;
import com.yule.util.DateUtil;
import com.yule.vo.UserLoginVO;

@Controller
@Scope("prototype")
@RequestMapping("/comment")
public class CommentAction extends BaseAction{
	
	@Autowired
	private IOrdersMongo ordersMongoImpl;
	@Autowired
	private ICompanyCommentCategoryService companyCommentCategoryService;
	@Autowired
	private ICompanyPointCategoryService companyPointCategoryService;
	@Autowired
	private IUserScoreMongo userScoreMongoImpl;
	@Autowired
	private ICompanyCommentMongo companyCommentMongoImpl;
	@Autowired
	private ICompanyCommentPointMongo companyCommentPointMongoImpl;
	
	@RequestMapping(value = "/findComment",method = RequestMethod.GET)
	public String findOrders(@RequestParam(value="order_num",required=false)String order_num) throws Exception {
		if (!StringUtils.isEmpty(order_num)) {
			Orders order = ordersMongoImpl.findOrdersByOrderNum(order_num);
			if(order != null && order.getStatus() == OrdersConst.ORDERS_STATUS_THREE){
				String image_path = FileUploadConst.COMPANY_IMAGE_PATH+order.getCompany_id()+"/"+FileUploadConst.PX_200_150+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE;
				request.setAttribute("image_path", image_path);
				request.setAttribute("create_time", DateUtil.DateToString(order.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
				request.setAttribute("arrive_time", DateUtil.DateToString(order.getArrive_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
				request.setAttribute("orders", order);
				return "user-comment";		
			}
		}
		return ActionReturnConst.REDIRECT+"/orders.do";
	}
	

	@RequestMapping(value = "/findCommentCategorys",method=RequestMethod.POST)
	public String findCommentCategorys() throws Exception {
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			List<CompanyCommentCategory> companyCommentCategorys = this.companyCommentCategoryService.findCompanyCommentCategoryList();
			List<CompanyPointCategory> companyPointCategorys = this.companyPointCategoryService.findCompanyPointCategoryList();
			StringBuffer htmls = new StringBuffer("");
			if(companyCommentCategorys != null && companyCommentCategorys.size() > 0){
				for(CompanyCommentCategory companyCommentCategory:companyCommentCategorys){
					 htmls.append("<div class=\"raty-item\" categoryName=\""+companyCommentCategory.getName()+"\"data-type=\"categoryDiv\" data-name=\"commentCategory_id\" data-value="+companyCommentCategory.getId()+">"+companyCommentCategory.getName()+"");
					 htmls.append("<span class=\"raty-star\"></span>");
					 htmls.append("<span class=\"hint\"></span>");
					 htmls.append("</div>");					
				}
				if (companyPointCategorys != null && companyPointCategorys.size() > 0) {
					JSONArray ja = new JSONArray();
					int i = 0;
					for (CompanyPointCategory companyPointCategory : companyPointCategorys) {
						if(companyPointCategory.getPoint() > 0){
							ja.add(i,companyPointCategory.getPoint()+"/"+companyPointCategory.getName());	
						}
					}
					object.put("poinJSON", ja.toString());
				}
			}else{
				htmls.append("<div class=\"raty-item\" >暂无评分");
				 htmls.append("<span class=\"raty-star\"></span>");
				 htmls.append("<span class=\"hint\"></span>");
				 htmls.append("</div>");
			}
			object.put("commentCategoryHTML", htmls.toString());
			object.put("status", ErrorConst.STATUS_ERROR);		
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
	
	@RequestMapping(value = "/insertCommentPoints",method=RequestMethod.POST)
	public String insertCommentPoints(@ModelAttribute(value="insertCompanyCommentParam")InsertCompanyCommentParam insertCompanyCommentParam) throws Exception {
		JSONObject josnObject = new JSONObject();
		josnObject.put("status", ErrorConst.STATUS_ERROR);
		try {
			UserLoginVO userLoginVO = getUserLoginVO();
			String order_num = insertCompanyCommentParam.getOrder_num();
			Orders order = this.ordersMongoImpl.findOrdersByOrderNum(order_num);
			if (userLoginVO != null && insertCompanyCommentParam != null&&order != null &&order.getIs_comment() != CommentConst.IS_COMMENT_TRUE) {
				String user_id =  userLoginVO.getId();
				String user_name = userLoginVO.getName();
				CompanyComment companyComment = new CompanyComment();
				companyComment.setAdvice(insertCompanyCommentParam.getAdvice());
				companyComment.setComment(insertCompanyCommentParam.getComment());
				companyComment.setCompany_id(insertCompanyCommentParam.getCompany_id());
				companyComment.setOrder_num(order_num);
				companyComment.setType(insertCompanyCommentParam.getCommentType());
				companyComment.setUser_id(user_id);
				companyComment.setUser_name(user_name);
				Date date = DateUtil.getCurrentDate();
				companyComment.setCreate_time(date);
				List<String> commentCategory_id = insertCompanyCommentParam.getCommentCategory_id();
				List<String> commentCategory_name = insertCompanyCommentParam.getCommentCategory_name();
				List<Integer> companyPointCategory_point = insertCompanyCommentParam.getCompanyPointCategory_point();
				List<CompanyCommentPoint> companyCommentPoints = new ArrayList<CompanyCommentPoint>();
				if (commentCategory_id != null && commentCategory_id.size() > 0) {
					float count =  0;	
					for (int i = 0; i < commentCategory_id.size(); i++) {
						CompanyCommentPoint companyCommentPoint = new CompanyCommentPoint();
						companyCommentPoint.setCompany_comment_category_id(commentCategory_id.get(i));
						companyCommentPoint.setCompany_comment_category_name(commentCategory_name.get(i));
						companyCommentPoint.setCompany_id(insertCompanyCommentParam.getCompany_id());
						Integer point = companyPointCategory_point.get(i);
						companyCommentPoint.setPoint(point);
						count += point;
						companyCommentPoint.setUser_id(user_id);
						companyCommentPoint.setUser_name(user_name);
						companyCommentPoint.setOrder_num(order_num);
						companyCommentPoint.setCreate_time(date);
						companyCommentPoints.add(companyCommentPoint);
					}
					DecimalFormat format = new DecimalFormat("0.0");
					float avg = count/companyCommentPoints.size();
					String avgPoint = format.format(avg);
					companyComment.setTitle(CompanyPointCategoryUtil.getCompanyPointName(avg));
					companyComment.setPoint(Float.parseFloat(avgPoint));
					this.companyCommentMongoImpl.insertCompanyComment(companyComment);
					this.companyCommentPointMongoImpl.insertCompanyPoint(companyCommentPoints);
					companyCommentPoints.clear();
					companyCommentPoints = null;
					UserScore userScore = new UserScore();
					userScore.setScore(ScoreConst.SCORE_COMMENT);
					userScore.setStatus(ScoreConst.STATUS_IN);
					userScore.setType(ScoreConst.TYPE_FIVE);
					userScore.setUser_id(user_id);
					this.userScoreMongoImpl.insertUserScore(userScore);
					Orders orders = new Orders();
					orders.setOrder_num(order_num);
					orders.setIs_comment(CommentConst.IS_COMMENT_TRUE);
					this.ordersMongoImpl.updateOrdersByOrderNum(orders);
					josnObject.put("status", ErrorConst.STATUS_SUCCESS);
				}
			}
		} catch (Exception e) {
			new YuleException("插入评论出现异常", e);
			throw e;
		}finally{
			outputResult(josnObject.toString());
		}
		return null;
	}
	
	
}