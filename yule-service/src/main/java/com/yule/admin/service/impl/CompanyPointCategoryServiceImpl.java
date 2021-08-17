package com.yule.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.ICompanyPointCategoryDao;
import com.yule.admin.param.InsertCompanyPointCategoryParam;
import com.yule.admin.service.ICompanyPointCategoryService;
import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPointCategory;
import com.yule.util.IDUtil;
import com.yule.vo.CompanyPointCategoryVO;

@Service("companyPointCategoryServiceImpl")
public class CompanyPointCategoryServiceImpl implements ICompanyPointCategoryService {

	@Autowired
	private ICompanyPointCategoryDao companyPointCategoryDao;

	public boolean insertCompanyPointCategory(CompanyPointCategory companyPointCategory) throws YuleException {
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(companyPointCategory.getId())){
				companyPointCategory.setId(IDUtil.getID());
				companyPointCategory.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			}
			this.companyPointCategoryDao.insertCompanyPointCategory(companyPointCategory);
			flag=true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateCompanyPointCategory(CompanyPointCategory companyPointCategory) throws YuleException {
		boolean flag = false;
		try {
			this.companyPointCategoryDao.updateCompanyPointCategory(companyPointCategory);
			flag=true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteCompanyPointCategoryById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.companyPointCategoryDao.deleteCompanyPointCategoryById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean deleteCompanyPointCategoryAll() throws YuleException {
		boolean flag = false;
		try {
			this.companyPointCategoryDao.deleteCompanyPointCategoryAll();
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public List<CompanyPointCategory> findCompanyPointCategoryList() throws YuleException {
		return this.companyPointCategoryDao.findCompanyPointCategoryList();
	}
	
	public List<CompanyPointCategoryVO> findCompanyPointCategoryVOList() throws YuleException {
		return this.companyPointCategoryDao.findCompanyPointCategoryVOList();
	}

	public boolean batchInsertCompanyPointCategory(List<CompanyPointCategory> companyPointCategories)throws YuleException {
		boolean flag=false;
		try {
			companyPointCategoryDao.batchInsertCompanyPointCategory(companyPointCategories);
			flag=true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean batchInsertAndUpdateCompanyPointCategory(InsertCompanyPointCategoryParam insertCompanyPointCategoryParam)throws YuleException {
		boolean flag=false;
		try {
			List<String> lists=insertCompanyPointCategoryParam.getId();
			if (null!=lists&&lists.size()>0) {
				List<CompanyPointCategory> insertCompanyPointCategoryes=new ArrayList<CompanyPointCategory>();
				List<CompanyPointCategory> updateCompanyPointCategoryes=new ArrayList<CompanyPointCategory>();
				List<String> names = insertCompanyPointCategoryParam.getName();
				List<Integer> points = insertCompanyPointCategoryParam.getPoint();
				int i = 0;
				CompanyPointCategory companyPointCategory = null;
				for (String id : lists) {
					companyPointCategory = new CompanyPointCategory();
					companyPointCategory.setName(names.get(i));
					companyPointCategory.setPoint(points.get(i));
					if (StringUtils.isEmpty(id)) {
						companyPointCategory.setId(IDUtil.getID());
						companyPointCategory.setIs_delete(DeleteConst.IS_DELETE_TRUE);
						insertCompanyPointCategoryes.add(companyPointCategory);
					} else {
						companyPointCategory.setId(id);
						updateCompanyPointCategoryes.add(companyPointCategory);
					}
					i++;
				}
				lists.clear();
				names.clear();
				points.clear();
				lists=null;
				names = null;
				points = null;
				if (insertCompanyPointCategoryes.size()>0) {
					this.companyPointCategoryDao.batchInsertCompanyPointCategory(insertCompanyPointCategoryes);
					insertCompanyPointCategoryes.clear();
				}
				insertCompanyPointCategoryes=null;
				if (updateCompanyPointCategoryes.size()>0) {
					this.companyPointCategoryDao.batchUpdateCompanyPointCategory(updateCompanyPointCategoryes);
					updateCompanyPointCategoryes.clear();
				}
				updateCompanyPointCategoryes=null;
				flag=true;
			}
			insertCompanyPointCategoryParam = null;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

}
