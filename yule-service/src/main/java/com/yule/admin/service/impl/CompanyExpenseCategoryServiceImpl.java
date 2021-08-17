package com.yule.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.admin.dao.ICompanyExpenseCategoryDao;
import com.yule.admin.service.ICompanyExpenseCategoryService;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyExpenseCategory;
import com.yule.util.IDUtil;
import com.yule.vo.Page;

@Service("companyExpenseCategoryServiceImpl")
public class CompanyExpenseCategoryServiceImpl implements ICompanyExpenseCategoryService{

	@Autowired
	private ICompanyExpenseCategoryDao companyExpenseCategoryDao;

	public boolean insertCompanyExpenseCategory(CompanyExpenseCategory companyExpenseCategory)throws YuleException {
		boolean flag = false;
		try {
			companyExpenseCategory.setId(IDUtil.getID());
			companyExpenseCategoryDao.insertCompanyExpenseCategory(companyExpenseCategory);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateCompanyExpenseCategory(CompanyExpenseCategory companyExpenseCategory)throws YuleException {
		boolean flag = false;
		try {
			companyExpenseCategoryDao.updateCompanyExpenseCategory(companyExpenseCategory);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public Page<CompanyExpenseCategory> findCompanyExpenseCategoryByParentId(String id,int pageSize, int pageNo)throws YuleException{
		Page<CompanyExpenseCategory> page = new Page<CompanyExpenseCategory>();
		page.setPageSize(pageSize);
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("pageStart", (pageNo - 1) * pageSize);
		params.put("pageEnd", pageSize);
		if (null==id) {
			page.setDatas(this.companyExpenseCategoryDao.findCompanyExpenseCategory(params));
			page.setRowCount(this.companyExpenseCategoryDao.findCompanyExpenseCategoryCount());
		}else{
			params.put("id", id);
			page.setDatas(this.companyExpenseCategoryDao.findCompanyExpenseCategoryByParentId(params));
			page.setRowCount(this.companyExpenseCategoryDao.findCompanyExpenseCategoryCountByParentId(id));
	
		}
		page.setPageNo(pageNo);
		return page;
	}

	public List<CompanyExpenseCategory> findCompanyExpenseList() throws YuleException {
		return this.companyExpenseCategoryDao.findCompanyExpenseCategory(null);
	}

	public boolean deleteALL() throws YuleException {
		boolean flag = false;
		try {
			this.companyExpenseCategoryDao.deleteALL();
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean batchInsertCompanyExpenseCategory(List<CompanyExpenseCategory> companyExpenseCategories)throws YuleException {
		boolean flag = false;
		try {
			this.companyExpenseCategoryDao.batchInsertCompanyExpenseCategory(companyExpenseCategories);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
}
