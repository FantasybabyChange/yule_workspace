package com.yule.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.admin.dao.IAdminNoticeAttachmentDao;
import com.yule.admin.service.IAdminNoticeAttachmentService;
import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminNoticeAttachment;
import com.yule.util.IDUtil;

@Service("adminNoticeAttachmentServiceImpl")
public class AdminNoticeAttachmentServiceImpl implements IAdminNoticeAttachmentService{

	@Autowired
	private IAdminNoticeAttachmentDao adminNoticeAttachmentDao;
	
	
	public List<AdminNoticeAttachment> findAdminNoticeAttachmentList(String notice_id) throws YuleException {
		
		return this.adminNoticeAttachmentDao.findAdminNoticeAttachmentList(notice_id);
	}

	public boolean insertAdminNoticeAttachment(AdminNoticeAttachment adminNoticeAttachment) throws YuleException {
		boolean flag = false;
		try {
			adminNoticeAttachment.setId(IDUtil.getID());
			adminNoticeAttachment.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			this.adminNoticeAttachmentDao.insertAdminNoticeAttachment(adminNoticeAttachment);
			flag = true ;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}

	public boolean updateAdminNoticeAttachment(AdminNoticeAttachment adminNoticeAttachment) throws YuleException {
		boolean flag = false;
		try {
			this.adminNoticeAttachmentDao.updateAdminNoticeAttachment(adminNoticeAttachment);
			flag = true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}

	public boolean deleteAdminNoticeAttachmentById(String attachment_id) throws YuleException {
		boolean flag = false;
		try {
			this.adminNoticeAttachmentDao.deleteAdminNoticeAttachmentById(attachment_id);
			flag = true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}


	
}
