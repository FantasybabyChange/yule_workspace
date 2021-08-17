package com.yule.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.admin.dao.ICompanyExpensePriceDao;
import com.yule.admin.service.ICompanyExpensePriceService;
import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyExpensePrice;
import com.yule.util.IDUtil;
import com.yule.vo.Page;

@Service("companyExpensePriceServiceImpl")
public class CompanyExpensePriceServiceImpl implements ICompanyExpensePriceService{

	@Autowired
	private ICompanyExpensePriceDao companyExpensePriceDao;
	
	public boolean updateCompanyExpensePrice(CompanyExpensePrice companyExpensePrice) throws YuleException {
		boolean flag = false;
		try {
			this.companyExpensePriceDao.updateCompanyExpensePrice(companyExpensePrice);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean insertCompanyExpensePrice(CompanyExpensePrice companyExpensePrice) throws YuleException {
		boolean flag = false ;
		try {
			companyExpensePrice.setId(IDUtil.getID());
			companyExpensePrice.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			this.companyExpensePriceDao.insertCompanyExpensePrice(companyExpensePrice);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteCompanyExpensePriceById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.companyExpensePriceDao.deleteCompanyExpensePriceById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public Page<CompanyExpensePrice> findCompanyExpensePricePage(String company_id,int pageSize,int pageNo) throws YuleException {
		Page<CompanyExpensePrice> page=new Page<CompanyExpensePrice>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageStart", (pageNo - 1) * pageSize);
		params.put("pageEnd", pageSize);
		params.put("company_id", company_id);
		page.setDatas(this.companyExpensePriceDao.findCompanyExpensePriceListByCompanyId(params));
		page.setPageSize(pageSize);
		page.setRowCount(this.companyExpensePriceDao.findCompanyExpensePriceCountByCompanyId(company_id));
		page.setPageNo(pageNo);
		return page;
	}
}
