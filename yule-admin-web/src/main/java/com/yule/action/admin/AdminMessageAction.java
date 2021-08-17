package com.yule.action.admin;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.service.IAdminRoleService;
import com.yule.admin.service.IAdminUserService;
import com.yule.admin.service.ICompanyService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.CodeConst;
import com.yule.constant.DeleteConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.MessageConst;
import com.yule.constant.PageConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.service.IAdminMessageMongo;
import com.yule.mongo.pojo.AdminMessage;
import com.yule.mongo.query.AdminMessageQuery;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.Company;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/adminMessage")
public class AdminMessageAction extends BaseAction {
	
	@Autowired
	private IAdminMessageMongo adminMessageMongoImpl;
	
	@Autowired
	private IAdminUserService adminUserServiceImpl;
	
	@Autowired
	private IAdminRoleService adminRoleServiceImpl;
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	/**
	 * 消息分页数据
	 */
	@RequestMapping(value = "/findAdminMessage",method = RequestMethod.GET)
	public String findAdminMessagePage(AdminMessageQuery adminMessageMongoQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception{
		if(null==pageNo||pageNo<1){
			pageNo=1;
		}
		try {
			if (adminMessageMongoQuery == null) {
				adminMessageMongoQuery = new AdminMessageQuery();
			}
			if (StringUtils.isEmpty(adminMessageMongoQuery.getAdmin_user_id())) {
				adminMessageMongoQuery.setReceive_id(getAdminUserId());	
			}
		    Page<AdminMessage> page=adminMessageMongoImpl.findAdminMessagePage(adminMessageMongoQuery,PageConst.PAGE_SIZE_TEN, pageNo);
		    StringBuffer htmls = new StringBuffer();
		    htmls.append("<tfoot><tr><td colspan=\"5\">");
			htmls.append("<div class=\"bulk-actions align-left\"></div>");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody>");
		    if(page.getRowCount()>0){
		    	AdminPrivilege adminPrivilege = null;
				for (AdminMessage adminMessage : page.getDatas()) {
					htmls.append("<tr>");
					htmls.append("<td><input name=\"id\" type=\"checkbox\" value=\""+adminMessage.getId()+"\" /></td>");
					htmls.append("<td><a href=\"/adminMessage/findAdminMessageById.do?id="+ adminMessage.getId() + "\" >" + adminMessage.getTitle() + "</a></td>");
					htmls.append("<td>" + MessageConst.IS_READ[adminMessage.getIs_read()] + "</td>");
					htmls.append("<td>" + DateUtil.DateToString(adminMessage.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
					htmls.append("<td class=\"is_delete\">" + DeleteConst.IS_DELETE[adminMessage.getIs_delete()] + "</td>");
					htmls.append("<td>");
                    if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){	
                    	adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
                    	htmls.append("&nbsp;&nbsp;<a class=\"button\" data-name=\"is_delete\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl()+"\" data-id=\"" + adminMessage.getId() + "\" data-status=\""+DeleteConst.BUTTON_DELETE_VALUE[adminMessage.getIs_delete()]+"\" >"+DeleteConst.BUTTON_DELETE[adminMessage.getIs_delete()]+"</a>");
					}
					htmls.append("</td></tr>");
				}
				page.getDatas().clear();
				page=null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td class=\"td-center-style\" colspan=\"8\">暂无数据</td>");
				htmls.append("</tr>");
			}
		    htmls.append("</tbody>");			
			List<Company> companys = companyServiceImpl.findCompanyList();
			if(null!=companys&&companys.size()>0){
				JSONArray array = new JSONArray();
				JSONObject object = null;
				for (Company company : companys) {
					object = new JSONObject();
					if (!StringUtils.isEmpty(company.getName())) {
						object.put("label",URLEncoder.encode(company.getName(), CodeConst.UTF_8));	
					}
					object.put("pinyin", company.getPinyin());
					object.put("value", company.getId());
					array.add(object);
					object.clear();
				}
				companys.clear();
				companys= null;
				request.setAttribute("usersJson", array.toString());
				array.clear();
				array = null;
			}
			request.setAttribute("adminMessageMongoQuery",adminMessageMongoQuery);
		    request.setAttribute("htmls", htmls);
		} catch (Exception e) {
			new YuleException("消息分页数据【findAdminMessageManagerPage】发生错误:",e);
			throw e;
		}
		return "admin/message/index";
	}
	
	/**
	 * 获取所有未读信息
	 */
	@RequestMapping(value="/findAdminMessageNotRead",method = RequestMethod.GET)
	public String findAdminMessageNotRead(@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception{
		if(null == pageNo){
			pageNo= 1;
		}
		try {
			AdminMessageQuery adminMessageMongoQuery = new AdminMessageQuery();
			adminMessageMongoQuery.setAdmin_user_id(getAdminUserId());
			adminMessageMongoQuery.setIs_read(MessageConst.IS_READ_FALSE);
			Page<AdminMessage> page=adminMessageMongoImpl.findAdminMessagePage(adminMessageMongoQuery, PageConst.PAGE_SIZE_TEN, pageNo);
			List<AdminMessage> adminMessages = page.getDatas();
			StringBuffer htmls = new StringBuffer();
			htmls.append("<tfoot><tr><td colspan=\"3\">");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody>");
			if(null != adminMessages && adminMessages.size()>0){
				StringBuffer id = new StringBuffer("");
				AdminPrivilege adminPrivilege = null;
				for (AdminMessage adminMessage : adminMessages) {
					id.append(adminMessage.getId());
					htmls.append("<tr>");
					htmls.append("<td><a href=\"/adminMessage/findAdminMessageById.do?id="+ id + "\" >" + adminMessage.getTitle() + "</a></td>");
					htmls.append("<td>" + DateUtil.DateToString(adminMessage.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
					htmls.append("<td>");
                    if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){		
                    	adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
                	    htmls.append("</a><a href=\""+adminPrivilege.getUrl()+"?id=" + id+ "\" >"+adminPrivilege.getName()+"</a>");
					}
					htmls.append("</td></tr>");
				}
				
				adminMessages.clear();
				adminMessages=null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td colspan=\"10\" >暂无数据</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			request.setAttribute("htmls", htmls);
		} catch (Exception e) {
			new YuleException("获取所有未读信息【findAdminMessageNotRead】发生错误",e);
			throw e;
		}
		return "admin/message/notRead";
	}

	/**
	 * 添加消息
	 */
	@RequestMapping(value="/insertAdminMessage",method = RequestMethod.POST)
	public String insertAdminMessage(@ModelAttribute("adminMessage") AdminMessage adminMessage,@RequestParam("adminUserId") List<String> adminUserId)throws Exception{
		try {
			List<AdminMessage> adminMessages = new ArrayList<AdminMessage>();
			if(null!=adminUserId && adminUserId.size()>0){
			    for (String string : adminUserId) {
			    	if(string.length()>0){
			    		AdminMessage receive= new AdminMessage();
			    		receive.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			    		receive.setIs_read(MessageConst.IS_READ_FALSE);
			    		receive.setSend_id(getAdminUserId());
			    		receive.setReceive_id(string);
			    		receive.setContent(adminMessage.getContent());
			    		receive.setTitle(adminMessage.getTitle());
			    		receive.setCreate_time(DateUtil.getCurrentDate());
				    	adminMessages.add(receive);
			    	}
				}
			    adminUserId.clear();
			    adminUserId = null;
			}
			if(null != adminMessages && adminMessages.size()>0){
				adminMessageMongoImpl.batchInsertAdminMessage(adminMessages);
				for (AdminMessage admin : adminMessages) {
					String mkey = RedisConst.COMPANY_MESSAGE_NUM+admin.getReceive_id();
					if(JedisUtil.getInstance().exists(mkey)){
						JedisUtil.getInstance().set(mkey, String.valueOf(Integer.parseInt(JedisUtil.getInstance().get(mkey))+1));
					}
				}
				adminMessages.clear();
				adminMessages=null;
			}
		} catch (Exception e) {
			new YuleException("添加消息【insertAdminMessage】发生错误:",e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/adminMessage/findAdminMessage.do";
	}
	
	/**
	 * 删除消息
	 */
	@RequestMapping(value="/deleteAdminMessage",method = RequestMethod.POST)
	public String deleteAdminMessage(@ModelAttribute("adminMessage")AdminMessage adminMessage,@RequestParam(value="id",required=false)String id)throws Exception{
		JSONObject obj=new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			adminMessage.setId(new ObjectId(id));
			boolean flag = adminMessageMongoImpl.updateAdminMessage(adminMessage);
			int status = adminMessage.getIs_delete();
			obj.put("status", flag);
			obj.put("value",DeleteConst.BUTTON_DELETE_VALUE[status]);
			obj.put("is_delete_text",DeleteConst.IS_DELETE[status]);
			obj.put("text",DeleteConst.BUTTON_DELETE[status]);
		} catch (Exception e) {
			new YuleException("删除消息【deleteAdminMessage】发生错误:",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	@RequestMapping(value="/findAdminMessageById",method = RequestMethod.GET)
	public String findAdminMessageById(@RequestParam(value="id",required=false)String id)throws Exception{
		try {
			AdminMessage adminMessage = this.adminMessageMongoImpl.findAdminMessageById(id);
			request.setAttribute("create_time", DateUtil.DateToString(adminMessage.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN));
			request.setAttribute("adminMessage", adminMessage);
		} catch (Exception e) {
			new YuleException("查询消息【findAdminMessageById】出错",e);
			throw e;
		}
		return "admin/message/messageDetail";
	}
	
	@RequestMapping(value="/updateAdminMessage",method = RequestMethod.POST)
	public String updateAdminMessageStatus(@ModelAttribute("adminMessage")AdminMessage adminMessage)throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			boolean flag = this.adminMessageMongoImpl.updateAdminMessage(adminMessage);
			obj.put("status", flag);
		} catch (Exception e) {
			new YuleException("修改adminMessage【updateAdminMessage】状态出现异常",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
}
