package com.yule.company.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.company.dao.IProductDao;
import com.yule.company.service.IProductService;
import com.yule.constant.DeleteConst;
import com.yule.constant.StatusConst;
import com.yule.exception.YuleException;
import com.yule.param.InsertProductParam;
import com.yule.pojo.Product;
import com.yule.util.IDUtil;
import com.yule.vo.Page;
import com.yule.vo.ProductVO;

@Service("productServiceImpl")
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao productDao;

	public boolean insertProduct(Product product) throws YuleException {
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(product.getId())){
				product.setId(IDUtil.getID());
			}
			product.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			product.setStatus(StatusConst.STATUS_TRUE);
			this.productDao.insertProduct(product);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteProductById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.productDao.deleteProductById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;

	}

	public String batchInsertAndUpdateProduct(InsertProductParam productQuery) throws YuleException {
		JSONArray array = new JSONArray();
	     try {
			List<String> lists = productQuery.getId();
			if(null != lists && lists.size()>0){
				List<Product> insertProducts = new ArrayList<Product>();
				List<Product> updateProducts = new ArrayList<Product>();
				String companyId = productQuery.getCompany_id();
				List<String> productCategoryIds = productQuery.getProduct_category_id();
				List<Integer> personNums = productQuery.getPerson_num();
				List<BigDecimal> minExpense = productQuery.getMin_expense();
				int i =0;
				Product product = null;
				JSONObject jsonObject = null;
				for (String id:lists) {
					product = new Product();
					product.setPerson_num(personNums.get(i));
					product.setMin_expense(minExpense.get(i));
					if(StringUtils.isEmpty(id)){
						product.setProduct_category_id(productCategoryIds.get(i));
						product.setCompany_id(companyId);
						id = IDUtil.getID();
						product.setId(id);
						jsonObject = new JSONObject();
						jsonObject.put("order", i);
						jsonObject.put("id", id);
						array.add(jsonObject);
						product.setIs_delete(DeleteConst.IS_DELETE_TRUE);
						product.setStatus(StatusConst.STATUS_TRUE);
						insertProducts.add(product);
					}else{
						product.setId(id);
						updateProducts.add(product);
					}
					i++;
				}
				productCategoryIds.clear();
				personNums.clear();
				minExpense.clear();
				productCategoryIds = null;
				personNums=null;
				minExpense=null;
				if(insertProducts.size()>0){
				    this.productDao.batchInsertProduct(insertProducts);	
				    insertProducts.clear();
				}
				insertProducts=null;
				if(updateProducts.size()>0){
				    this.productDao.batchUpdateProduct(updateProducts);	
				    updateProducts.clear();
				}
				updateProducts=null;
			}
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return array.toString();
	}

	public List<ProductVO> findProductVOList(String companyId) throws YuleException {
		return this.productDao.findProductVOList(companyId);
	}
	
	public boolean updateProduct(Product product) throws YuleException {
		boolean flag = false;
		try {
			this.productDao.updateProduct(product);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public Page<ProductVO> findOtherCompanyProductPage(String company_name,String id, int pageSize, int pageNo)throws YuleException {
		Page<ProductVO> page =new Page<ProductVO>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("company_name",company_name);
		params.put("id",id);
		params.put("pageStart",( pageNo-1)* pageSize);
		params.put("pageEnd",pageSize);
		page.setDatas(this.productDao.findOtherCompanyProductList(params));
		page.setPageSize(pageSize);
		page.setRowCount(this.productDao.findOtherCompanyProductCount(params));
		page.setPageNo(pageNo);
		return page;
	}

	public List<ProductVO> findSimpleProductVOList(String companyId)throws YuleException {
		return this.productDao.findSimpleProductVOList(companyId);
	}
	
}
