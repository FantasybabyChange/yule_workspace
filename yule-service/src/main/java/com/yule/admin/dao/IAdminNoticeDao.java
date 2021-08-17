package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.AdminNotice;
/**
 * 系统通知DAO
 */
public interface IAdminNoticeDao {
	
	public int insertAdminNotice(AdminNotice adminNotice) throws YuleException;

	public int updateAdminNotice(AdminNotice adminNotice) throws YuleException;

	public int deleteAdminNoticeById(String id) throws YuleException;

	public List<AdminNotice> findAdminNoticeList() throws YuleException;

	public int findAdminNoticeCount(Map<String, Object> params) throws YuleException;
	
	public List<AdminNotice> findAdminNoticeList(Map<String, Object> params) throws YuleException;
	
	public int batchUpdateAdminNoticeById(Map<String, Object> params) throws YuleException;
	
	public AdminNotice findAdminNoticeVOById(String id) throws YuleException;
}
