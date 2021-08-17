package com.yule.admin.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.IProductDao;
import com.yule.admin.service.IProductService;
import com.yule.constant.DeleteConst;
import com.yule.constant.StatusConst;
import com.yule.exception.YuleException;
import com.yule.param.InsertProductParam;
import com.yule.pojo.Product;
import com.yule.util.IDUtil;
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

	public boolean updateProduct(Product product) throws YuleException {
		boolean flag = false;
		try {
//			if(!StringUtils.isEmpty(product.getIs_show())){
//				Map<String, Object> params = new HashMap<String, Object>();
//				params.put("is_show", StatusConst.IS_SHOW_FALSE);
//				List<String> ids= new ArrayList<String>();
//				List<Product> products = this.productDao.findProductList(params);
//				if(null != products && products.size()>0){
//					for (Product productById : products) {
//						ids.add(productById.getId());
//					}
//					products.clear();
//					products=null;
//				}
//				if(null != ids && ids.size()>0){
//					params.put("ids", ids);
//					this.productDao.batchUpdateProductById(params);
//					ids.clear();
//					ids=null;
//				}
//			}
			this.productDao.updateProduct(product);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public Product findProductById(String id) throws YuleException{
		return this.productDao.findProductById(id);
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

	public boolean batchInsertAndUpdateProduct(InsertProductParam insertProductParam) throws YuleException {
		boolean flag = false;
	     try {
			List<String> lists = insertProductParam.getId();
			if(null != lists && lists.size()>0){
				List<Product> insertProducts = new ArrayList<Product>();
				List<Product> updateProducts = new ArrayList<Product>();
				String companyId = insertProductParam.getCompany_id();
				List<String> productCategoryIds = insertProductParam.getProduct_category_id();
				List<Integer> personNums = insertProductParam.getPerson_num();
				List<BigDecimal> minExpense = insertProductParam.getMin_expense();
				int i =0;
				Product product = null;
				for (String id:lists) {
					product = new Product();
					product.setPerson_num(personNums.get(i));
					product.setMin_expense(minExpense.get(i));
					if(StringUtils.isEmpty(id)){
						product.setProduct_category_id(productCategoryIds.get(i));
						product.setCompany_id(companyId);
						product.setId(IDUtil.getID());
						product.setIs_delete(DeleteConst.IS_DELETE_TRUE);
						product.setStatus(StatusConst.STATUS_TRUE);
						insertProducts.add(product);
					}else{
						product.setId(id);
						updateProducts.add(product);
					}
					i++;
				}
				lists.clear();
				productCategoryIds.clear();
				personNums.clear();
				minExpense.clear();
				lists = null;
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
				flag = true;
			}
			insertProductParam=null;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public List<ProductVO> findProductVOList(String companyId) throws YuleException {
		return this.productDao.findProductVOList(companyId);
	}
	
}
