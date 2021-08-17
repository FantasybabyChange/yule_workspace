package com.yule.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.company.service.IAdminNoticeService;
import com.yule.company.service.ICompanyService;
import com.yule.company.service.ICompanyUserService;
import com.yule.company.vo.CompanyUserVO;
import com.yule.constant.CompanyCookieConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.MessageConst;
import com.yule.constant.OrdersConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.company.service.IAdminMessageMongo;
import com.yule.mongo.company.service.ICompanyCommentPointMongo;
import com.yule.mongo.company.service.ICompanyTaskMongo;
import com.yule.mongo.company.service.IOrdersMongo;
import com.yule.mongo.company.vo.CompanyCommentPointVO;
import com.yule.mongo.pojo.AdminMessage;
import com.yule.mongo.pojo.CompanyTask;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.query.AdminMessageQuery;
import com.yule.mongo.query.CompanyCommentQuery;
import com.yule.pojo.AdminNotice;
import com.yule.util.CompanyCommentUtil;
import com.yule.util.CompanyLogUtil;
import com.yule.util.CompanyUtil;
import com.yule.util.CookieUtil;
import com.yule.util.DateUtil;
import com.yule.util.PrivilegeUtil;
import com.yule.util.YuLeEncryptUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
public class IndexAction extends BaseAction {
	
	@Autowired 
	private IAdminMessageMongo adminMessageMongoImpl;
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	@Autowired
	private ICompanyUserService companyUserServiceImpl;
	
	@Autowired
	private IAdminNoticeService adminNoticeServiceImpl;
	
	@Autowired
	private ICompanyCommentPointMongo companyCommentPointMongo;
	
	@Autowired
	private IOrdersMongo ordersMongoImpl; 
	
	@Autowired
	private ICompanyTaskMongo companyTaskMongoImpl; 
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() throws Exception{
		try{
			List<AdminNotice> adminNotices = adminNoticeServiceImpl.findAdminNoticeFirstPage(PageConst.PAGE_SIZE_FIVE);
			StringBuffer adminNoticeHtmls = new StringBuffer();
			if (adminNotices != null) {
				for (AdminNotice adminNotice : adminNotices) {
				adminNoticeHtmls.append("<li class=\"+item-blue clearfix\">");
				adminNoticeHtmls.append("<label class=\"inline\">");
				adminNoticeHtmls.append("<span class=\"lbl\"> "+adminNotice.getTitle()+"</span>");
				adminNoticeHtmls.append("</label>  <span style='float:right;'>"+DateUtil.DateToString(adminNotice.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+"</span></li>");
			}
				if (adminNotices.size() == PageConst.PAGE_SIZE_TEN) {
					adminNoticeHtmls.append("<li class=\"+item-blue clearfix\">");
					adminNoticeHtmls.append("<label class=\"inline\">");
					adminNoticeHtmls.append("<span class=\"lbl\"> </span>");
					adminNoticeHtmls.append("</label>  <span style='float:right;' class=\"class='label label-sm label-primary arrowed arrowed-right'>\"><a href=\"/adminNotice/findAdminNotice.do\">更多。。</a></span></li>");
				}
			request.setAttribute("adminNoticeHtmls", adminNoticeHtmls);		
			adminNotices.clear();
			adminNotices = null;
			}else{
				adminNoticeHtmls.append("<li class=\"+item-blue clearfix\">");
				adminNoticeHtmls.append("<label class=\"inline\">");
				adminNoticeHtmls.append("<span class=\"lbl\">暂无系统通知</span>");
				adminNoticeHtmls.append("</label></li>");
			}
			CompanyCommentQuery companyCommentQuery = new CompanyCommentQuery();
			companyCommentQuery.setCompany_id(getCompanyUser().getCompany_id());
			companyCommentQuery.setUser_name("");
			Page<CompanyCommentPointVO> page = companyCommentPointMongo.findCompanyCommentPointList(companyCommentQuery, PageConst.PAGE_NO_DEFAULT,PageConst.PAGE_SIZE_TEN);
			StringBuffer companyCommentHtmls = new StringBuffer();
			if (page.getRowCount() > 0) {
				List<CompanyCommentPointVO> datas = page.getDatas();
				for (CompanyCommentPointVO companyCommentPointVO : datas) {
						companyCommentHtmls.append("<div class=\"itemdiv dialogdiv\">");
						companyCommentHtmls.append("<div class=\"user\">");
						companyCommentHtmls.append("<img alt=\""+companyCommentPointVO.getUser_name()+"\" src=\""+FileUploadConst.USER_IMAGE_PATH+companyCommentPointVO.getUser_id()+"/"+FileUploadConst.PX_50_50+FileUploadConst.USER_FACE+FileUploadConst.IMAGE_TYPE+"\" />");
						companyCommentHtmls.append("</div>");
						companyCommentHtmls.append("<div class=\"body\">");
						companyCommentHtmls.append("<div class=\"time\">");
						companyCommentHtmls.append("<i class=\"ace-icon fa fa-clock-o\"></i>");
						StringBuffer dateBuffer = new StringBuffer(DateUtil.DateToString(companyCommentPointVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
						companyCommentHtmls.append("<span class=\"green\"><abbr class=\"timeago\" title=\""+dateBuffer+"\">"+dateBuffer+"</abbr></span>");
						dateBuffer.setLength(0);
						companyCommentHtmls.append("</div>");
						companyCommentHtmls.append("<div class=\"name\">");
						companyCommentHtmls.append("<a href=\"#\">"+companyCommentPointVO.getUser_name()+"</a>&nbsp;&nbsp;<span>订单号:</span>(<strong>"+companyCommentPointVO.getOrder_num()+"</strong>)&nbsp;&nbsp;<a href=\"#comment-wizard\" data-toggle=\"modal\"  modal-dialog=\""+companyCommentPointVO.getOrder_num()+"\">查看评论内容</a>");
						companyCommentHtmls.append("</div>");
						companyCommentHtmls.append("<div>");
						List<String> lists = companyCommentPointVO.getName();
						int i=0;
						List<Double> point = companyCommentPointVO.getPoint();
						double pointCount = 0;
						if(lists != null && lists.size() > 0&&point != null && point.size() > 0){
							for(String s :lists){
								if(i < point.size()){
								pointCount+=point.get(i);
								companyCommentHtmls.append("<span class=\"badge badge-success\">");
								companyCommentHtmls.append(s+"(<strong>"+point.get(i)+"</strong>)");
								companyCommentHtmls.append("</span>");	
								}
								i++;
							}
							companyCommentHtmls.append("<span class=\"label label-danger arrowed\">综合评分:"+CompanyCommentUtil.formatDouble(pointCount/point.size(),1)+"</span>");
							lists.clear();
							lists=null;
							point.clear();
							point=null;
						}
						companyCommentHtmls.append("</div>");
						companyCommentHtmls.append("<div class=\"tools\">");
						companyCommentHtmls.append("<a href=\"#\" class=\"btn btn-minier btn-info\">");
						companyCommentHtmls.append("<i class=\"icon-only ace-icon fa fa-share\"></i>");
						companyCommentHtmls.append("</a>");
						companyCommentHtmls.append("</div>");
						companyCommentHtmls.append("</div>");
						companyCommentHtmls.append("</div>");
				}
				datas.clear();
				datas = null;
			}
			request.setAttribute("companyCommentHtmls", companyCommentHtmls);
			CompanyLogUtil.insertLog("查询主页面数据", getCompanyUser(), LogEnum.QUERY);
		}catch(Exception e){
			new YuleException(e);
			throw e;
		}
		return "index";
	}
	
	@RequestMapping(value = "/findNotReadInfo", method = RequestMethod.POST)
	public String findNotReadInfo()throws Exception{
		try {
			CompanyUserVO companyUser = getCompanyUser();
			if (companyUser != null) {
				JSONObject jsonObject = CompanyUtil.getCompanyNotInfoNum(companyUser.getCompany_id());
				outputResult(jsonObject.toString());
			}
		} catch (Exception e) {
			new YuleException("查询订单findNotReadInfo出现异常", e);
			throw e;
		}
		return null;
	}
	
	@RequestMapping(value = "/findMessageNotRead", method = RequestMethod.POST)
	public String findMessageNotRead()throws Exception{
		JSONObject json = new JSONObject();
		try {
			StringBuffer messageNotReadHtmls = new StringBuffer();
			AdminMessageQuery adminMessageQuery = new AdminMessageQuery();
			adminMessageQuery.setReceive_id(getCompanyUser().getCompany_id());
			adminMessageQuery.setIs_read(MessageConst.IS_READ_FALSE);
			Page<AdminMessage> page = this.adminMessageMongoImpl.findAdminMessagePageByType(adminMessageQuery, PageConst.PAGE_SIZE_FIVE, PageConst.PAGE_NO_DEFAULT);
			if (page.getRowCount() > 0) {
				List<AdminMessage> datas = page.getDatas();
				for (AdminMessage adminMessage : datas) {
					messageNotReadHtmls.append("<li><a href=\"/adminMessage/findAdminMessage.do\">");
					messageNotReadHtmls.append("<div class=\"clearfix\">");
					messageNotReadHtmls.append("	<span class=\"pull-left\">");
					messageNotReadHtmls.append(adminMessage.getTitle());
					messageNotReadHtmls.append("</span>");
					messageNotReadHtmls.append("<span class=\"pull-right badge badge-info\">"+DateUtil.DateToString(adminMessage.getCreate_time(), DateStyle.YYYY_MM_DD)+"</span>");
					messageNotReadHtmls.append("</div>");
					messageNotReadHtmls.append("</a></li>");
				}
				messageNotReadHtmls.append("<li class=\"dropdown-footer\">");	
				messageNotReadHtmls.append("<a href=\"/adminMessage/findAdminMessage.do\">");
				messageNotReadHtmls.append("马上去查看");	
				messageNotReadHtmls.append("<i class=\"ace-icon fa fa-arrow-right\"></i>");
				messageNotReadHtmls.append("</a>");
				messageNotReadHtmls.append("</li>");
			}else{
				messageNotReadHtmls.append("<li><a href=\"#\">");
				messageNotReadHtmls.append("<div class=\"clearfix\">");
				messageNotReadHtmls.append("	<span class=\"pull-left\">");
				messageNotReadHtmls.append("暂无数据</span>");
				messageNotReadHtmls.append("</div>");
				messageNotReadHtmls.append("</a></li>");
			}
			json.put("mesageHtmls", messageNotReadHtmls.toString());
			messageNotReadHtmls.setLength(0);
			outputResult(json.toString());
			json.clear();
			json=null;
			CompanyLogUtil.insertLog("查询未读消息", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查询未读消息出现异常【findMessageNotRead】", e);
			throw e;
		}
		return null;
		
	}
	
	@RequestMapping(value = "/findOrderNotRead.do", method = RequestMethod.POST)
	public String findOrderNotRead()throws Exception{
		JSONObject json = new JSONObject();
		try {
			StringBuffer orderNotReadHtmls = new StringBuffer();
			Page<Orders> page = this.ordersMongoImpl.findOrdersPageByCompanyId(getCompanyUser().getCompany_id(), OrdersConst.ORDERS_STATUS_ZERO, PageConst.PAGE_SIZE_FIVE, PageConst.PAGE_NO_DEFAULT);
			if (page.getRowCount() > 0) {
				List<Orders> datas = page.getDatas();
				for (Orders orders : datas) {
					orderNotReadHtmls.append("<li><a href=\"#modal-wizard\" data-toggle=\"modal\"  modal-dialog=\""+orders.getOrder_num()+"\">");
					orderNotReadHtmls.append("<div class=\"clearfix\">");
					orderNotReadHtmls.append("	<span class=\"pull-left\" >");
					orderNotReadHtmls.append(orders.getProduct_name());
					orderNotReadHtmls.append("</span>");
					if (orders.getCreate_time() != null) {
					StringBuffer dateBuffer = new StringBuffer(DateUtil.DateToString(orders.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
					orderNotReadHtmls.append("<span class=\"pull-right badge badge-info\"><i class=\"ace-icon fa fa-clock-o\"></i><span class=\"white\"><abbr class=\"timeago\" title=\""+dateBuffer+"\">"+dateBuffer+"</abbr></span></span>");
					dateBuffer.setLength(0);
					}
					orderNotReadHtmls.append("</div>");
					orderNotReadHtmls.append("</a></li>");
				}
				orderNotReadHtmls.append("<li class=\"dropdown-footer\">");	
				orderNotReadHtmls.append("<a href=\"/orders/findOrders.do\">");
				orderNotReadHtmls.append("马上去确定");	
				orderNotReadHtmls.append("<i class=\"ace-icon fa fa-arrow-right\"></i>");
				orderNotReadHtmls.append("</a>");
				orderNotReadHtmls.append("</li>");
			}else{
				orderNotReadHtmls.append("<li><a href=\"#\">");
				orderNotReadHtmls.append("<div class=\"clearfix\">");
				orderNotReadHtmls.append("	<span class=\"pull-left\">");
				orderNotReadHtmls.append("暂无订单</span>");
				orderNotReadHtmls.append("</div>");
				orderNotReadHtmls.append("</a></li>");
			}
			json.put("orderHtmls", orderNotReadHtmls.toString());
			orderNotReadHtmls.setLength(0);
			outputResult(json.toString());
			json.clear();
			json=null;
			CompanyLogUtil.insertLog("查询未确认订单", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查询未读消息出现异常【findOrderNotRead】", e);
			throw e;
		}
		return null;
	}
	
	@RequestMapping(value = "/findTaskNotRead.do", method = RequestMethod.POST)
	public String findTaskNotRead()throws Exception{
		JSONObject json = new JSONObject();
		try {
			StringBuffer companyTaskNotReadHtmls = new StringBuffer();
			Page<CompanyTask> page = companyTaskMongoImpl.findCompanyTaskPage(getCompanyUser().getCompany_id(), PageConst.PAGE_SIZE_FIVE, PageConst.PAGE_NO_DEFAULT);
			int count = 0;
			if (page.getRowCount() > 0) {
				List<CompanyTask> datas = page.getDatas();
				String value = YuLeEncryptUtil.decode(CookieUtil.getCookieValue(request.getCookies(), CompanyCookieConst.COMPANYUSER_COOKIE_NAME));
				for (CompanyTask companyTask : datas) {
					if(PrivilegeUtil.getCompanyPrivilegeUrl(value, companyTask.getUrl())) {
					count++;
					companyTaskNotReadHtmls.append("<li><a href=\""+companyTask.getUrl()+"\">");
					companyTaskNotReadHtmls.append("<div class=\"clearfix\">");
					companyTaskNotReadHtmls.append("<span class=\"pull-left\">");
					companyTaskNotReadHtmls.append(companyTask.getName());
					companyTaskNotReadHtmls.append("</span>");
					companyTaskNotReadHtmls.append("<span class=\"pull-right\">任务完成后前台展示</span>");
					companyTaskNotReadHtmls.append("</div>");
					companyTaskNotReadHtmls.append("</a></li>");
					}
				}
				companyTaskNotReadHtmls.append("<li class=\"dropdown-footer\">");	
				companyTaskNotReadHtmls.append("<a href=\"/companyTask/findCompanyTask.do\">");
			//	companyTaskNotReadHtmls.append("查看所有任务");
				companyTaskNotReadHtmls.append("<i class=\"ace-icon fa fa-arrow-right\"></i>");
				companyTaskNotReadHtmls.append("</a>");
				companyTaskNotReadHtmls.append("</li>");
			}else{
				companyTaskNotReadHtmls.append("<li><a href=\"javascript:;\">");
				companyTaskNotReadHtmls.append("<div class=\"clearfix\">");
				companyTaskNotReadHtmls.append("<span class=\"pull-left\">");
				companyTaskNotReadHtmls.append("已完成所有任务</span>");
				companyTaskNotReadHtmls.append("</div>");
				companyTaskNotReadHtmls.append("</a></li>");
			}
			json.put("taskCount", count);
			json.put("companyTaskHtmls", companyTaskNotReadHtmls.toString());
			companyTaskNotReadHtmls.setLength(0);
			outputResult(json.toString());
			json.clear();
			json=null;
			CompanyLogUtil.insertLog("查询未完成的任务", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查询未读消息出现异常【findOrderNotRead】", e);
			throw e;
		}
		return null;
	}
}
