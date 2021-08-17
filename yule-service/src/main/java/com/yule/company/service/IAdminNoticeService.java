package com.yule.company.service;

import java.util.List;

import com.yule.company.query.AdminNoticeQuery;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminNotice;
import com.yule.vo.Page;
/**
 * 系统通知
 */
public interface IAdminNoticeService {
	public AdminNotice findAdminNoticeById(String id) throws YuleException;

	public List<AdminNotice> findAdminNoticeList() throws YuleException;

	public Page<AdminNotice> findAdminNoticePage(AdminNoticeQuery adminNoticeQuery,int pageSize, int pageNo) throws YuleException ;
	
	public AdminNotice findAdminNoticeVOById(String id) throws YuleException;
	//查询第一页的数据
	public List<AdminNotice> findAdminNoticeFirstPage(int pageEnd) throws YuleException;
}
