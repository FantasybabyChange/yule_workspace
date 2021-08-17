package com.yule.admin.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.yule.admin.dao.ICompanyFavorableDao;
import com.yule.admin.service.ICompanyFavorableService;
import com.yule.exception.YuleException;
import com.yule.param.InsertCompanyFavorableParam;
import com.yule.pojo.CompanyFavorable;
import com.yule.util.IDUtil;

@Service("companyFavorableServiceImpl")
public class CompanyFavorableServiceImpl implements ICompanyFavorableService {
	
	@Autowired
	private ICompanyFavorableDao companyFavorableDao;
	
	public boolean batchInsertAndUpdateCompanyFavorable(InsertCompanyFavorableParam insertCompanyFavorableParam) throws YuleException {
		boolean flag =false;
		try {
			List<String> lists = insertCompanyFavorableParam.getId();
			if(null != lists && lists.size()>0){
				List<CompanyFavorable> insertCompanyFavorables = new ArrayList<CompanyFavorable>();
				List<CompanyFavorable> updateCompanyFavorables = new ArrayList<CompanyFavorable>();
				String companyId = insertCompanyFavorableParam.getCompany_id();
				List<String> contents = insertCompanyFavorableParam.getContent();
				List<String> names = insertCompanyFavorableParam.getName();
				List<BigDecimal> prices = insertCompanyFavorableParam.getPrice();
				int i =0;
				CompanyFavorable companyFavorable = null;
				for (String id:lists) {
					companyFavorable = new CompanyFavorable();
					companyFavorable.setContent(contents.get(i));
					companyFavorable.setName(names.get(i));
					companyFavorable.setPrice(prices.get(i));
					if(StringUtils.isEmpty(id)){
						companyFavorable.setId(IDUtil.getID());
						companyFavorable.setCompany_id(companyId);
						insertCompanyFavorables.add(companyFavorable);
					}else{
						companyFavorable.setId(id);
						updateCompanyFavorables.add(companyFavorable);
					}
					i++;
				}
				lists.clear();
				contents.clear();
				names.clear();
				prices.clear();
				lists = null;
				contents = null;
				names = null;
				prices= null;
				if(insertCompanyFavorables.size() >0){
					this.companyFavorableDao.batchInsertCompanyFavorable(insertCompanyFavorables);
					insertCompanyFavorables.clear();
				}
				insertCompanyFavorables=null;
				if(updateCompanyFavorables.size() >0){
					this.companyFavorableDao.batchUpdateCompanyFavorable(updateCompanyFavorables);
					updateCompanyFavorables.clear();
				}
				updateCompanyFavorables=null;
				flag = true;
			}
			insertCompanyFavorableParam=null;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public List<CompanyFavorable> findCompanyFavorableByCompanyId(
			String companyId) throws YuleException {
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("company_id", companyId);
		return this.companyFavorableDao.findCompanyFavorableList(params);
	}

	public boolean deleteCompanyFavorableById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.companyFavorableDao.deleteCompanyFavorableById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateCompanyFavorable(CompanyFavorable companyFavorable)
			throws YuleException {
		boolean flag = false;
		try {
			this.companyFavorableDao.updateCompanyFavorable(companyFavorable);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean insertCompanyFavorable(CompanyFavorable companyFavorable)
			throws YuleException {
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(companyFavorable.getId())){
				companyFavorable.setId(IDUtil.getID());
			}
			this.companyFavorableDao.insertCompanyFavorable(companyFavorable);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

}
