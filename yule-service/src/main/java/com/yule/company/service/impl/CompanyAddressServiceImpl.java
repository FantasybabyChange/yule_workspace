package com.yule.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.company.dao.ICompanyAddressDao;
import com.yule.company.service.ICompanyAddressService;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyAddress;
import com.yule.vo.CompanyAddressVO;

@Service("companyAddressServiceImpl")
public class CompanyAddressServiceImpl implements ICompanyAddressService {

	@Autowired
	private ICompanyAddressDao companyAddressDao;

	public boolean updateCompanyAddress(CompanyAddress companyAddress)
			throws YuleException {
		boolean flag = false;
		try {
			this.companyAddressDao.updateCompanyAddress(companyAddress);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public CompanyAddressVO findCompanyAddressVOById(String id) throws YuleException {
		return this.companyAddressDao.findCompanyAddressVOById(id);
	}

	public boolean updateCompanyAddress(CompanyAddress companyAddress,String companyId)throws YuleException {
		boolean flag = false;
		try {
			if (StringUtils.isEmpty(companyAddress.getArea_county_id())) {
				companyAddress.setArea_county_id(null);
			}	
			if (StringUtils.isEmpty(companyAddress.getArea_business_id())) {
				companyAddress.setArea_business_id(null);
			}
			if (StringUtils.isEmpty(companyAddress.getAddress_detail())) {
				companyAddress.setAddress_detail(null);
			}
			this.companyAddressDao.updateCompanyAddress(companyAddress);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public int findCompanyAddressAuthById(String id) throws YuleException {
		return this.companyAddressDao.findCompanyAddressAuthById(id);
	}

}
