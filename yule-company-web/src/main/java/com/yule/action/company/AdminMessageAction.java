package com.yule.action.company;
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

import com.yule.common.BaseAction;
import com.yule.company.service.IAdminRoleService;
import com.yule.company.service.IAdminUserService;
import com.yule.constant.CompanyRedisConst;
import com.yule.constant.DeleteConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.MessageConst;
import com.yule.constant.PageConst;
import com.yule.constant.StatusConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.company.service.IAdminMessageMongo;
import com.yule.mongo.pojo.AdminMessage;
import com.yule.mongo.query.AdminMessageQuery;
import com.yule.pojo.AdminRole;
import com.yule.pojo.AdminUser;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CompanyLogUtil;
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
	private IAdminRoleService adminRoleServiceImpl;
	@Autowired
	private IAdminUserService adminUserServiceImpl;
	
	/**
	 * 消息分页数据
	 */
	@RequestMapping(value = "/findAdminMessage")
	public String findAdminMessagePage(AdminMessageQuery adminMessageMongoQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception{
		if(null==pageNo){
			return "company/message/index";
		}
		if(null==adminMessageMongoQuery){
			adminMessageMongoQuery = new AdminMessageQuery();
		}
		adminMessageMongoQuery.setReceive_id(getCompanyUser().getCompany_id());
		JSONObject obj = new JSONObject();
		try {
			Page<AdminMessage> page = adminMessageMongoImpl.findAdminMessagePage(adminMessageMongoQuery,PageConst.PAGE_SIZE_TEN, pageNo);
			StringBuffer tfoot = new StringBuffer();
			StringBuffer tbody = new StringBuffer();
			tfoot.append("<tr><td colspan=\"7\">");
			tfoot.append("<div class=\"bulk-actions align-left\"></div>");
			tfoot.append(PaginationUtil.getPaginationHtml(page));
			tfoot.append("<div class=\"clear\"></div>");
			tfoot.append("</td></tr>");
			if(page.getRowCount()>0){
				for(AdminMessage adminMessage : page.getDatas()){
					tbody.append("<tr>");
					tbody.append("<td><a href=\"#message-wizard\" modal-message=\""+adminMessage.getIs_read()+"\" data-toggle=\"modal\"  read-toggle=\"modal\" modal-dialog=\""+adminMessage.getId()+"\" >" + adminMessage.getTitle() + "</a></td>");
					tbody.append("<td>" + MessageConst.IS_READ[adminMessage.getIs_read()] + "</td>");
					tbody.append("<td>" + DateUtil.DateToString(adminMessage.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
					tbody.append("<div class=\"hidden-sm hidden-xs btn-group\">");
					tbody.append("</tr>");
					tbody.append("</div>");
					tbody.append("</td>");
					tbody.append("</tr>");					
				}
			}else{
				tbody.append("<tr>");
				tbody.append("<td colspan=\"7\">没有数据</td>");
				tbody.append("</tr>");	
			}
			obj.put("tfoot", tfoot.toString());
			obj.put("tbody", tbody.toString());
			outputResult(obj.toString());
			tfoot.setLength(0);
			tbody.setLength(0);
			obj.clear();
			obj = null;
			CompanyLogUtil.insertLog("查询企业信息", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return null;
	}
	@RequestMapping(value = "/findAdminMessageById",method=RequestMethod.POST)
	public String findAdminMessage(@RequestParam(value = "id", required = false) String id) throws Exception{
		JSONObject obj = new JSONObject();
		try {
			AdminMessage adminMessage = this.adminMessageMongoImpl.findAdminMessageById(id);
			if(adminMessage !=null){
				obj.put("status", StatusConst.STATUS_TRUE);
				obj.put("title", adminMessage.getTitle());
				obj.put("content", adminMessage.getContent());
				obj.put("createTime", DateUtil.DateToString(adminMessage.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
			}else{
				obj.put("status", StatusConst.STATUS_FALSE);
			}
			outputResult(obj.toString());
			obj.clear();
			obj = null;
			CompanyLogUtil.insertLog("通过ID查询消息", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("通过ID查询消息出现异常【findAdminMessage】",e);
			throw e;
		}
		return null;
	}
	@RequestMapping(value = "/updateAdminMessage",method=RequestMethod.POST)
	public String updateAdminMessage(@ModelAttribute("adminMessage")AdminMessage adminMessage) throws Exception{
		JSONObject obj = new JSONObject();
		try {
		boolean flag = this.adminMessageMongoImpl.updateAdminMessage(adminMessage);
				obj.put("status", flag);
				outputResult(obj.toString());
				String mkey = CompanyRedisConst.COMPANY_MESSAGE_NUM + getCompanyUser().getCompany_id();
				if (JedisUtil.getInstance().exists(mkey)) {
					JedisUtil.getInstance().set(mkey, String.valueOf(Integer.parseInt(JedisUtil.getInstance().get(mkey))-1));
				}
			obj.clear();
			obj = null;
			CompanyLogUtil.insertLog("通过ID查询消息", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("通过ID查询消息出现异常【findAdminMessage】",e);
			throw e;
		}
		return null;
	}
	
	@RequestMapping(value = "/findAdminRole",method=RequestMethod.POST)
	public String findAdminRole() throws Exception {
		try {
		List<AdminRole> list = this.adminRoleServiceImpl.findAdminRoleList();
		JSONArray array = new JSONArray();
		JSONObject object = null;
		for (AdminRole adminRole : list) {
			object = new JSONObject();
			if (!StringUtils.isEmpty(adminRole.getName())) {
				object.put("label",adminRole.getName());	
			}
			object.put("value", adminRole.getId());
			array.add(object);
			object.clear();
		}
		object.put("adminRoles", array);
		outputResult(object.toString());
		list.clear();
		list= null;
		array.clear();
		array = null;
		object.clear();
		object =null;
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
	
	/**
	 * 添加消息
	 */
	@RequestMapping(value="/insertAdminMessage",method = RequestMethod.POST)
	public String insertAdminMessage(@ModelAttribute("adminMessage") AdminMessage adminMessage,@RequestParam("roleIds") List<String> roleIds)throws Exception{
		JSONObject json = new JSONObject();
		boolean flag = false;
		try {
			if (roleIds != null && roleIds.size() > 0) {
				List<AdminUser> adminUsers = this.adminUserServiceImpl.findAdminUserListByRoleIds(roleIds);
				List<AdminMessage> adminMessages = new ArrayList<AdminMessage>();
				for (AdminUser adminUser : adminUsers) {
					AdminMessage receive= new AdminMessage();
		    		receive.setIs_delete(DeleteConst.IS_DELETE_TRUE);
		    		receive.setIs_read(MessageConst.IS_READ_FALSE);
		    		receive.setSend_id(getCompanyUser().getCompany_id());
		    		receive.setReceive_id(adminUser.getId());
		    		receive.setContent(adminMessage.getContent());
		    		receive.setTitle(adminMessage.getTitle());
		    		receive.setCreate_time(DateUtil.getCurrentDate());
			    	adminMessages.add(receive);
				}
				adminUsers.clear();
				adminUsers = null;
				flag = this.adminMessageMongoImpl.batchInsertAdminMessage(adminMessages);
					json.put("status", flag);
			}else{
				json.put("status", flag);
			}
			outputResult(json.toString());
		} catch (Exception e) {
			new YuleException("添加消息【insertAdminMessage】发生错误:", e);
			throw e;
		}
		return null;
	}
	
	/**
	 * 删除消息
	 */
	@RequestMapping(value="/deleteAdminMessage",method = RequestMethod.POST)
	public String deleteAdminMessage(@ModelAttribute("adminMessage")AdminMessage adminMessage,@RequestParam(value="id",required=false)String id)throws Exception{
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("status", ErrorConst.STATUS_ERROR);
		try {
			adminMessage.setId(new ObjectId(id));
			boolean flag = adminMessageMongoImpl.updateAdminMessage(adminMessage);
			int status = adminMessage.getIs_delete();
			jsonObject.put("status", flag);
			jsonObject.put("value",DeleteConst.BUTTON_DELETE_VALUE[status]);
			jsonObject.put("is_delete_text",DeleteConst.IS_DELETE[status]);
			jsonObject.put("text",DeleteConst.BUTTON_DELETE[status]);
		} catch (Exception e) {
			new YuleException("删除消息【deleteAdminMessage】发生错误:", e);
			throw e;
		} finally{
			outputResult(jsonObject.toString());
		}
		return null;
	}
	
}
