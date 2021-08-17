package com.yule.action.admin;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.service.IAdminNoticeAttachmentService;
import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminNoticeAttachment;
import com.yule.util.AdminLogUtil;

@Controller
@Scope("prototype")
@RequestMapping("/adminNoticeAttachment")
public class AdminNoticeAttachmentAction extends BaseAction{

	@Autowired
	private IAdminNoticeAttachmentService adminNoticeAttachmentServiceImpl;
	
	@RequestMapping(value = "/insertAdminNoticeAttachment", method = RequestMethod.POST)
	public String insertAdminNoticeAttachment(@ModelAttribute("adminNoticeAttachment")AdminNoticeAttachment adminNoticeAttachment ) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			boolean flag = this.adminNoticeAttachmentServiceImpl.insertAdminNoticeAttachment(adminNoticeAttachment);
			obj.put("id", adminNoticeAttachment.getId());
			obj.put("status", flag);
			AdminLogUtil.insertLog("新增通知附件", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("新增通知附件[insertAdminNoticeAttachment]发生错误!",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}

	
	@RequestMapping(value = "/deleteAdminNoticeAttachment",method = RequestMethod.POST)
	public String deleteCompanyUser(@RequestParam(value = "id", required = false)String id) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			boolean flag = this.adminNoticeAttachmentServiceImpl.deleteAdminNoticeAttachmentById(id);
			obj.put("status", flag);
			AdminLogUtil.insertLog("删除通知附件", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除通知附件[deleteAdminNoticeAttachment]发生异常",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	
}
