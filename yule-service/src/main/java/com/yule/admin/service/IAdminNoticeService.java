package com.yule.admin.service;

import java.util.List;

import com.yule.admin.param.InsertAdminNoticeParam;
import com.yule.admin.query.AdminNoticeQuery;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminNotice;
import com.yule.vo.Page;
/**
 * 系统通知
 */
public interface IAdminNoticeService {
	public boolean insertAdminNotice(AdminNotice adminNotice) throws YuleException;

	public boolean updateAdminNotice(AdminNotice adminNotice) throws YuleException;
	
	public boolean batchUpdateAdminNoticeById(List<String> id) throws YuleException;

	public boolean deleteAdminNoticeById(String id) throws YuleException;

	public List<AdminNotice> findAdminNoticeList() throws YuleException;

	public Page<AdminNotice> findAdminNoticePage(AdminNoticeQuery adminNoticeQuery,int pageSize, int pageNo) throws YuleException ;

	public boolean insertAdminNotice(InsertAdminNoticeParam adminNoticeAttachmentParam)throws YuleException ;
	
	public AdminNotice findAdminNoticeVOById(String id) throws YuleException;
}
