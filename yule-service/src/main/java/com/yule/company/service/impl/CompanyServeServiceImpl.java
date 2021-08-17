package com.yule.company.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.company.dao.ICompanyServeDao;
import com.yule.company.dao.ICompanyServeRelevantDao;
import com.yule.company.service.ICompanyServeService;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyServeRelevant;
import com.yule.util.IDUtil;
import com.yule.vo.CompanyServeVO;

@Service("companyServeServiceImpl")
public class CompanyServeServiceImpl implements ICompanyServeService {

	@Autowired
	private ICompanyServeDao companyServeDao;
	
	@Autowired
	private ICompanyServeRelevantDao companyServeRelevantDao;

	public List<CompanyServeVO> findCompanyAndServeList(String companyId) throws YuleException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("company_id", companyId);
		return this.companyServeDao.findCompanyAndServeList(params);
	}
	
	public boolean updateCompanyCheckServe(String companyId,List<String> companyServeIds) throws YuleException {
		boolean flag = false;
		try{
			companyServeRelevantDao.deleteCompanyServeRelevantByCompanyId(companyId);
			if(null!=companyServeIds&&companyServeIds.size()>0){
				List<CompanyServeRelevant> lists = new ArrayList<CompanyServeRelevant>();
				CompanyServeRelevant companyServeRelevant = null;
				for(String id :companyServeIds){
					companyServeRelevant = new CompanyServeRelevant();
					companyServeRelevant.setCompany_id(companyId);
					companyServeRelevant.setId(IDUtil.getID());
					companyServeRelevant.setCompany_serve_id(id);
					lists.add(companyServeRelevant);
				}
				companyServeRelevantDao.batchInsertCompanyServeRelevant(lists);
				flag = true;
			}
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
}
