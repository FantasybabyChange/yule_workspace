package com.yule.company.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.company.dao.IAdminNoticeDao;
import com.yule.company.query.AdminNoticeQuery;
import com.yule.company.service.IAdminNoticeService;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminNotice;
import com.yule.vo.Page;
@Service("adminNoticeServiceImpl")
public class AdminNoticeServiceImpl implements IAdminNoticeService {

	@Autowired
	private IAdminNoticeDao adminNoticeDao;
	
	public AdminNotice findAdminNoticeById(String id) throws YuleException {
		return this.adminNoticeDao.findAdminNoticeById(id);
	}

	public List<AdminNotice> findAdminNoticeList() throws YuleException {
		return this.adminNoticeDao.findAdminNoticeList();
	}


	public Page<AdminNotice> findAdminNoticePage(AdminNoticeQuery adminNoticeQuery ,int pageSize, int pageNo) throws YuleException {
		Page<AdminNotice> page = new Page<AdminNotice>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (null!=adminNoticeQuery) {
				params.put("title", adminNoticeQuery.getTitle());
				params.put("start_time", adminNoticeQuery.getStart_time());
				params.put("end_time", adminNoticeQuery.getEnd_time());
			}
			params.put("pageStart", (pageNo - 1) * pageSize);
			params.put("pageEnd", pageSize);
			page.setDatas(this.adminNoticeDao.findAdminNoticeList(params));
			page.setRowCount(this.adminNoticeDao.findAdminNoticeCount(params));
			page.setPageSize(pageSize);
			page.setPageNo(pageNo);
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return page;
	}

	public AdminNotice findAdminNoticeVOById(String id) throws YuleException {
		return this.adminNoticeDao.findAdminNoticeVOById(id);
	}

	public List<AdminNotice> findAdminNoticeFirstPage(int pageEnd)throws YuleException {
		try {
			return this.adminNoticeDao.findAdminNoticeByPageEnd(pageEnd);
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
	}
}
