package com.yule.company.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.param.InsertCompanyPhoneParam;
import com.yule.company.dao.ICompanyPhoneDao;
import com.yule.company.service.ICompanyPhoneService;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPhone;
import com.yule.util.IDUtil;


@Service("companyPhoneServiceImpl")
public class CompanyPhoneServiceImpl implements ICompanyPhoneService {

	@Autowired
	private ICompanyPhoneDao companyPhoneDao;

	public boolean insertCompanyPhone(CompanyPhone companyPhone) throws YuleException {
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(companyPhone.getId())){
				companyPhone.setId(IDUtil.getID());
			}
			this.companyPhoneDao.insertCompanyPhone(companyPhone);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateCompanyPhone(CompanyPhone companyPhone) throws YuleException {
		boolean flag = false;
		try {
			this.companyPhoneDao.updateCompanyPhone(companyPhone);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteCompanyPhoneById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.companyPhoneDao.deleteCompanyPhoneById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public List<CompanyPhone> findCompanyPhoneListByCompanyId(String companyId) throws YuleException {
		return this.companyPhoneDao.findCompanyPhoneList(companyId);
	}

	public boolean batchUpdateCompanyPhone(InsertCompanyPhoneParam insertCompanyPhoneParam) throws YuleException {
		boolean flag =false;
		try {
			List<String> lists = insertCompanyPhoneParam.getId();
			if(null != lists && lists.size()>0){
				List<CompanyPhone> updateCompanyPhones = new ArrayList<CompanyPhone>();
				List<String> names = insertCompanyPhoneParam.getName();
				List<String> phones = insertCompanyPhoneParam.getPhone();
				int i=0;
				CompanyPhone companyPhone = null;
				for (String id : lists) {
					companyPhone = new CompanyPhone();
					companyPhone.setName(names.get(i));
					companyPhone.setPhone(phones.get(i));
					companyPhone.setId(id);
					updateCompanyPhones.add(companyPhone);
					i++;
				}
				lists.clear();
				names.clear();
				phones.clear();
				lists = null;
				names = null;
				phones = null;
				this.companyPhoneDao.batchUpdateCompanyPhone(updateCompanyPhones);
				updateCompanyPhones.clear();
				updateCompanyPhones=null;
				flag = true;
			}
			insertCompanyPhoneParam=null;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
}
