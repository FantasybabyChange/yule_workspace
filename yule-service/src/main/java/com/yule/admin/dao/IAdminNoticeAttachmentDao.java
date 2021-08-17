package com.yule.admin.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AdminNoticeAttachment;

public interface IAdminNoticeAttachmentDao {

	public List<AdminNoticeAttachment> findAdminNoticeAttachmentList(String notice_id)throws YuleException;
	
	public int insertAdminNoticeAttachment(AdminNoticeAttachment adminNoticeAttachment)throws YuleException;
	
	public int updateAdminNoticeAttachment(AdminNoticeAttachment adminNoticeAttachment)throws YuleException;
	
	public int deleteAdminNoticeAttachmentById(String attachment_id) throws YuleException;
	
	public int batchInsertAdminNoticeAttachment(List<AdminNoticeAttachment> batchInsertAdminNoticeAttachment) throws YuleException;
}
