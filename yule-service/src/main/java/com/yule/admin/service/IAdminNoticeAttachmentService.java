package com.yule.admin.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AdminNoticeAttachment;

public interface IAdminNoticeAttachmentService {

	public List<AdminNoticeAttachment> findAdminNoticeAttachmentList(String notice_id)throws YuleException;
	
	public boolean insertAdminNoticeAttachment(AdminNoticeAttachment adminNoticeAttachment)throws YuleException;
	
	public boolean updateAdminNoticeAttachment(AdminNoticeAttachment adminNoticeAttachment)throws YuleException;
	
	public boolean deleteAdminNoticeAttachmentById(String attachment_id)throws YuleException;

}
