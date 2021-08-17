package com.yule.company.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.AdminNotice;
/**
 * 系统通知DAO
 */
public interface IAdminNoticeDao {

	public AdminNotice findAdminNoticeById(String id) throws YuleException;

	public List<AdminNotice> findAdminNoticeByPageEnd(int pageEnd) throws YuleException;
	
	public List<AdminNotice> findAdminNoticeList() throws YuleException;

	public int findAdminNoticeCount(Map<String, Object> params) throws YuleException;
	
	public List<AdminNotice> findAdminNoticeList(Map<String, Object> params) throws YuleException;
	
	public AdminNotice findAdminNoticeVOById(String id) throws YuleException;
	
	
	
}
