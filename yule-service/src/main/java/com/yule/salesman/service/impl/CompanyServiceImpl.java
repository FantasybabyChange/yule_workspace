package com.yule.salesman.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.salesman.dao.ICompanyDao;
import com.yule.salesman.query.CompanyManagerQuery;
import com.yule.salesman.service.ICompanyService;
import com.yule.vo.CompanyManagerVO;
import com.yule.vo.Page;

@Service("companyServiceImpl")
public class CompanyServiceImpl implements ICompanyService{

	@Autowired
	private ICompanyDao companyDao;
	
	//查询自己与合作的企业
	public List<Company> findCompanyList(String id) throws YuleException {
		return this.companyDao.findCompanyList(id);
	}

	public Page<CompanyManagerVO> findCompanyManagerVOPage(CompanyManagerQuery companyManagerQuery ,int pageSize, int pageNo)
			throws YuleException {
		Page<CompanyManagerVO> page = new Page<CompanyManagerVO>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageStart", (pageNo - 1) * pageSize);
		params.put("pageEnd", pageSize);
		if (null != companyManagerQuery) {
			params.put("account", companyManagerQuery.getAccount());
			params.put("name", companyManagerQuery.getName());
			params.put("salesman_id", companyManagerQuery.getSalesman_id());
			params.put("start_time", companyManagerQuery.getStart_time());
			params.put("end_time", companyManagerQuery.getEnd_time());
			params.put("status", companyManagerQuery.getStatus());
			params.put("is_delete", companyManagerQuery.getIs_delete());
		}
		page.setDatas(this.companyDao.findCompanyManagerVOList(params));
		page.setRowCount(this.companyDao.findCompanyManagerVOCount(params));
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		return page;
	}
}
