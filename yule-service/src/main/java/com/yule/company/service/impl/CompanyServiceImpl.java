package com.yule.company.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.yule.company.dao.ICompanyDao;
import com.yule.company.service.ICompanyService;
import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.util.PinyinUtil;
import com.yule.vo.CompanyVO;

@Service("companyServiceImpl")
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyDao companyDao;
	
	public boolean updateCompany(Company company) throws YuleException {
		boolean flag = false;
		try {
			if (null!=company.getName()&&StringUtils.isEmpty(company.getName())) {
				company.setPinyin(PinyinUtil.getPinYin(company.getName()));
			}
			this.companyDao.updateCompany(company);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public CompanyVO findCompanyVOById(String id) throws YuleException {
		return this.companyDao.findCompanyVOById(id);
	}

	public List<Company> findCompanySame(String id) throws YuleException {
		return this.companyDao.findCompanySame(id);
	}

	public int findCompanyCountByMailOrPhone(Company company)throws YuleException {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("mail",company.getMail());
		params.put("phone",company.getPhone());
		return this.companyDao.findCompanyCount(params);
	}

	public int findCompanyAuthById(String id) throws YuleException {
		return this.companyDao.findCompanyAuthById(id);
	}

}
