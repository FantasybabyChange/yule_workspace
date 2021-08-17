package com.yule.admin.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.ICompanyCategoryDao;
import com.yule.admin.param.InsertCompanyCategoryParam;
import com.yule.admin.service.ICompanyCategoryService;
import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyCategory;
import com.yule.util.IDUtil;
import com.yule.vo.CompanyCategoryCountVO;
import com.yule.vo.CompanyCategoryVO;
@Service("companyCategoryServiceImpl")
public class CompanyCategoryServiceImpl implements ICompanyCategoryService {

	@Autowired
	private ICompanyCategoryDao companyCategoryDao;

	public boolean insertCompanyCategory(CompanyCategory companyCategory) throws YuleException {
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(companyCategory.getId())){
				companyCategory.setId(IDUtil.getID());
			}
			companyCategory.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			this.companyCategoryDao.insertCompanyCategory(companyCategory);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateCompanyCategory(CompanyCategory companyCategory) throws YuleException {
		boolean flag = false;
		try {
			this.companyCategoryDao.updateCompanyCategory(companyCategory);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteCompanyCategoryById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.companyCategoryDao.deleteCompanyCategoryById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean deleteCompanyCategoryAll() throws YuleException {
		boolean flag = false;
		try {
			this.companyCategoryDao.deleteCompanyCategoryAll();
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public List<CompanyCategory> findCompanyCategoryList() throws YuleException {
		return this.companyCategoryDao.findCompanyCategoryList();
	}

	public List<CompanyCategoryVO> findCompanyCategoryVOList() throws YuleException {
		return this.companyCategoryDao.findCompanyCategoryVOList();
	}

	public boolean batchInsertCompanyCategory(List<CompanyCategory> companyCategorys) throws YuleException {
		boolean flag = false;
		try {
			this.companyCategoryDao.batchInsertCompanyCategory(companyCategorys);	
			flag=true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean batchInsertAndUpdateCompanyCategory(InsertCompanyCategoryParam insertCompanyCategoryParam) throws YuleException {
		boolean flag = false;
		try {
			List<String> lists = insertCompanyCategoryParam.getId();
			if(null != lists && lists.size() >0){
				List<CompanyCategory> insertCompanyCategorys = new ArrayList<CompanyCategory>();
				List<CompanyCategory> updateCompanyCategorys = new ArrayList<CompanyCategory>();
				List<String> names = insertCompanyCategoryParam.getName();
				List<Integer> orders = insertCompanyCategoryParam.getOrder();
				int i =0;
				CompanyCategory companyCategory = null;
				for (String id:lists) {
					companyCategory = new CompanyCategory();
					companyCategory.setName(names.get(i));
					companyCategory.setOrder(orders.get(i));
					if(StringUtils.isEmpty(id)){
						companyCategory.setId(IDUtil.getID());
						companyCategory.setIs_delete(DeleteConst.IS_DELETE_TRUE);
						insertCompanyCategorys.add(companyCategory);
					}else{
						companyCategory.setId(id);
						updateCompanyCategorys.add(companyCategory);
					}
					i++;
				}
				lists.clear();
				names.clear();
				orders.clear();
				lists = null;
				names = null;
				orders = null;
				if(insertCompanyCategorys.size()>0){
					   this.companyCategoryDao.batchInsertCompanyCategory(insertCompanyCategorys);	
					   insertCompanyCategorys.clear();
				}
				insertCompanyCategorys=null;
				if(updateCompanyCategorys.size()>0){
					   this.companyCategoryDao.batchUpdateCompanyCategory(updateCompanyCategorys);	
					   updateCompanyCategorys.clear();
				}
				updateCompanyCategorys=null;
				flag=true;
			}
			insertCompanyCategoryParam = null;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public List<CompanyCategoryCountVO> findCompanyCategoryCountVOListByAreaProvinceId(
			String areaProvinceId) throws YuleException {
		return companyCategoryDao.findCompanyCategoryCountVOListByAreaProvinceId(areaProvinceId);
	}

	public List<CompanyCategoryCountVO> findCompanyCategoryCountVOListByAreaCityId(String areaCityId) throws YuleException {
		return companyCategoryDao.findCompanyCategoryCountVOListByAreaCityId(areaCityId);
	}

	public List<CompanyCategoryCountVO> findCompanyCategoryCountVOListByAreaCountyId(String areaCountId) throws YuleException {
		return companyCategoryDao.findCompanyCategoryCountVOListByAreaCountyId(areaCountId);
	}

	public List<CompanyCategoryCountVO> findCompanyCategoryCountVOListByAreaBusinessId(String areaBusinessId) throws YuleException {
		return companyCategoryDao.findCompanyCategoryCountVOListByAreaBusinessId(areaBusinessId);
	}

}
