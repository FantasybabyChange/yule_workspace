package com.yule.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.ICompanyCommentCategoryDao;
import com.yule.admin.param.InsertCompanyCommentCategoryParam;
import com.yule.admin.service.ICompanyCommentCategoryService;
import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyCommentCategory;
import com.yule.util.IDUtil;
import com.yule.vo.CompanyCommentCategoryVO;

@Service("companyCommentCategoryServiceImpl")
public class CompanyCommentCategoryServiceImpl implements ICompanyCommentCategoryService{

	@Autowired
	private ICompanyCommentCategoryDao companyCommentCategoryDao;
	
	public List<CompanyCommentCategory> findCompanyCommentCategoryList()throws YuleException {
		return this.companyCommentCategoryDao.findCompanyCommentCategoryList();
	}

	public boolean insertCompanyCommentCategory(CompanyCommentCategory companyCommentCategory) throws YuleException {
		boolean flag = false;
		try {
			companyCommentCategory.setId(IDUtil.getID());
			companyCommentCategory.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			this.companyCommentCategoryDao.insertCompanyCommentCategory(companyCommentCategory);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateCompanyCommentCategory(CompanyCommentCategory companyCommentCategory) throws YuleException {
		boolean flag =false;
		try {
			this.companyCommentCategoryDao.updateCompanyCommentCategory(companyCommentCategory);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteCompanyCommentCategoryById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.companyCommentCategoryDao.deleteCompanyCommentCategoryById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean batchInsertAndUpdateCompanyCommentCategory(InsertCompanyCommentCategoryParam companyCommentCategoryParam)throws YuleException {
		boolean flag=false;
		try {
			List<String> lists=companyCommentCategoryParam.getId();
			if (null!=lists&&lists.size()>0) {
				List<CompanyCommentCategory> insertCompanyCommentCategoryes=new ArrayList<CompanyCommentCategory>();
				List<CompanyCommentCategory> updateCompanyCommentCategoryes=new ArrayList<CompanyCommentCategory>();
				List<String> names = companyCommentCategoryParam.getName();
				List<Integer> orders = companyCommentCategoryParam.getOrder();
				int i = 0;
				CompanyCommentCategory companyCommentCategory = null;
				for (String id : lists) {
					companyCommentCategory = new CompanyCommentCategory();
					companyCommentCategory.setName(names.get(i));
					companyCommentCategory.setOrder(orders.get(i));
					if (StringUtils.isEmpty(id)) {
						companyCommentCategory.setId(IDUtil.getID());
						companyCommentCategory.setIs_delete(DeleteConst.IS_DELETE_TRUE);
						insertCompanyCommentCategoryes.add(companyCommentCategory);
					} else {
						companyCommentCategory.setId(id);
						updateCompanyCommentCategoryes.add(companyCommentCategory);
					}
					i++;
				}
				lists.clear();
				names.clear();
				orders.clear();
				lists=null;
				names = null;
				orders = null;
				if (insertCompanyCommentCategoryes.size()>0) {
					this.companyCommentCategoryDao.batchInsertCompanyCommentCategory(insertCompanyCommentCategoryes);
					insertCompanyCommentCategoryes.clear();
				}
				insertCompanyCommentCategoryes=null;
				if (updateCompanyCommentCategoryes.size()>0) {
					this.companyCommentCategoryDao.batchUpdateCompanyCommentCategory(updateCompanyCommentCategoryes);
					updateCompanyCommentCategoryes.clear();
				}
				updateCompanyCommentCategoryes=null;
				flag=true;
			}
			companyCommentCategoryParam = null;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public List<CompanyCommentCategoryVO> findCompanyCommentCategoryVOList()throws YuleException {
		return this.companyCommentCategoryDao.findCompanyCommentCategoryVOList();
	}

	public boolean deleteCompanyPointCategoryAll() throws YuleException {
		boolean flag = false;
		try {
			this.companyCommentCategoryDao.deleteCompanyPointCategoryAll();
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean batchInsertCompanyCommentCategory(List<CompanyCommentCategory> companyCommentCategories)throws YuleException {
		boolean flag = false;
		try {
			this.companyCommentCategoryDao.batchInsertCompanyCommentCategory(companyCommentCategories);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

}
