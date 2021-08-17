package com.yule.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.IProductCategoryDao;
import com.yule.admin.param.InsertProductCategoryParam;
import com.yule.admin.service.IProductCategoryService;
import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.pojo.ProductCategory;
import com.yule.util.IDUtil;

@Service("productCategoryServiceImpl")
public class ProductCategoryServiceImpl implements IProductCategoryService {

	@Autowired
	private IProductCategoryDao productCategoryDao;

	public List<ProductCategory> findProductCategoryList() throws YuleException{
		return this.productCategoryDao.findProductCategoryList();
	}

	public boolean insertProductCategory(ProductCategory productCategory) throws YuleException {
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(productCategory.getId())){
				productCategory.setId(IDUtil.getID());
			}
			productCategory.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			this.productCategoryDao.insertProductCategory(productCategory);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean updateProductCategory(ProductCategory productCategory) throws YuleException {
		boolean flag = false;
		try {
			this.productCategoryDao.updateProductCategory(productCategory);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean deleteProductCategoryById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.productCategoryDao.deleteProductCategoryById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean deleteProductCategoryAll() throws YuleException {
		boolean flag = false;
		try {
			this.productCategoryDao.deleteProductCategoryAll();
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public List<ProductCategory> findProductCategoryList(String companyCategoryId) throws YuleException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("company_category_id", companyCategoryId);
		return this.productCategoryDao.findProductCategoryList(params);
	}
	
	public boolean batchInsertAndUpdateProductCategory(InsertProductCategoryParam insertProductCategoryParam) throws YuleException {
		boolean flag =false;
		try {
			List<String> lists = insertProductCategoryParam.getId();
			if(null != lists && lists.size()>0){
				List<ProductCategory> insertProductCategorys = new ArrayList<ProductCategory>();
				List<ProductCategory> updateProductCategorys = new ArrayList<ProductCategory>();
				String companyCategoryId = insertProductCategoryParam.getCompany_category_id();
				List<String> names = insertProductCategoryParam.getName();
				List<Integer> orders = insertProductCategoryParam.getOrder();
				int i =0;
				ProductCategory productCategory = null;
				for (String id : lists) {
					productCategory = new ProductCategory();
					productCategory.setCompany_category_id(companyCategoryId);
					productCategory.setName(names.get(i));
					productCategory.setOrder(orders.get(i));
					if(StringUtils.isEmpty(id)){
						productCategory.setId(IDUtil.getID());
						productCategory.setIs_delete(DeleteConst.IS_DELETE_TRUE);
						insertProductCategorys.add(productCategory);
					}else{
						productCategory.setId(id);
						updateProductCategorys.add(productCategory);
					}
					i++;
				}
				lists.clear();
				names.clear();
				orders.clear();
				lists = null;
				names = null;
				orders = null;
				if(insertProductCategorys.size()>0){
					   this.productCategoryDao.batchInsertProductCategory(insertProductCategorys);	
					   insertProductCategorys.clear();
				}
				insertProductCategorys=null;
				if(updateProductCategorys.size()>0){
					   this.productCategoryDao.batchUpdateProductCategory(updateProductCategorys);	
					   updateProductCategorys.clear();
				}
				updateProductCategorys=null;
				flag = true;
			}
			insertProductCategoryParam=null;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean batchInsertProductCategory(List<ProductCategory> productCategorys) throws YuleException {
		boolean flag =false;
		try {
			this.productCategoryDao.batchInsertProductCategory(productCategorys);	
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
}
