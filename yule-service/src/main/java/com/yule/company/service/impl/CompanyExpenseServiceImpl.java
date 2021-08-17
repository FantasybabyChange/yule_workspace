package com.yule.company.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.company.dao.ICompanyExpenseDao;
import com.yule.company.query.CompanyExpenseQuery;
import com.yule.company.service.ICompanyExpenseService;
import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyExpense;
import com.yule.util.IDUtil;
import com.yule.vo.Page;

@Service("companyExpenseServiceImpl")
public class CompanyExpenseServiceImpl implements ICompanyExpenseService{

	@Autowired
	private ICompanyExpenseDao companyExpenseDao;
	
	public boolean updateCompanyExpense(CompanyExpense companyExpense)throws YuleException {
		boolean flag =false;
		try {
			this.companyExpenseDao.updateCompanyExpense(companyExpense);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean insertCompanyExpense(CompanyExpense companyExpense)throws YuleException {
		boolean flag = false;
		try {
			companyExpense.setId(IDUtil.getID());
			companyExpense.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			this.companyExpenseDao.insertCompanyExpense(companyExpense);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteCompanyExpense(String id)throws YuleException {
		boolean flag = false;
		try {
			this.companyExpenseDao.deleteCompanyExpense(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}


	public List<CompanyExpense> findCompanyExpenseList() throws YuleException {
		return this.companyExpenseDao.findCompanyExpenseList(null);
	}

	public Page<CompanyExpense> findCompanyExpensePage(CompanyExpenseQuery companyExpenseQuery, int pageSize, int pageNo)throws YuleException {
		Map<String, Object> params =new HashMap<String, Object>();
		Page<CompanyExpense> page = new Page<CompanyExpense>();
		params.put("pageStart", (pageNo - 1) * pageSize);
		params.put("pageEnd", pageSize);
		params.put("company_id", companyExpenseQuery.getCompany_id());
		params.put("parent_id", companyExpenseQuery.getExpense_category_id());
		page.setDatas(this.companyExpenseDao.findCompanyExpenseList(params));
		page.setPageSize(pageSize);
		page.setRowCount(this.companyExpenseDao.findCompanyExpenseCount(params));
		page.setPageNo(pageNo);
		return page;
	}

}
