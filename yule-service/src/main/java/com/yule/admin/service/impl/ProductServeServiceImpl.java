package com.yule.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.IProductServeDao;
import com.yule.admin.param.InsertProductServeParam;
import com.yule.admin.service.IProductServeService;
import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.pojo.ProductServe;
import com.yule.util.IDUtil;

@Service("productServeServiceImpl")
public class ProductServeServiceImpl implements IProductServeService{

	@Autowired
	private IProductServeDao productServeDao;
	
	public boolean insertProductServe(ProductServe productServe)throws YuleException {
		boolean flag = false;
		try {
			productServe.setId(IDUtil.getID());
			productServe.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			this.productServeDao.insertProductServe(productServe);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateProductServe(ProductServe productServe)throws YuleException {
		boolean flag = false;
		try {
			this.productServeDao.updateProductServe(productServe);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteProductServeById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.productServeDao.deleteProductServeById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public List<ProductServe> findProductServeList() throws YuleException {
		return this.productServeDao.findProductServeList();
	}

	public boolean batchUpdateAndInsertProductServe(InsertProductServeParam productServeParam) throws YuleException {
		boolean flag = false;
		try {
			List<ProductServe> insertProductServes = new ArrayList<ProductServe>();
			List<ProductServe> updateProductServes = new ArrayList<ProductServe>();
			List<String> ids = productServeParam.getId();
			if (null!=ids&&ids.size()>0) {
				List<String> names = productServeParam.getName();
				List<Integer> orders = productServeParam.getOrder();
				ProductServe productServe = null;
				for (int i = 0; i < ids.size(); i++) {
					productServe = new ProductServe();
					productServe.setOrder(orders.get(i));
					productServe.setName(names.get(i));
					if (StringUtils.isEmpty(ids.get(i))) {
						productServe.setId(IDUtil.getID());
						productServe.setIs_delete(DeleteConst.IS_DELETE_TRUE);
						insertProductServes.add(productServe);
					}else {
						productServe.setId(ids.get(i));
						updateProductServes.add(productServe);
					}
				}	
				ids.clear();
				ids=null;
				names.clear();
				names = null;
				orders.clear();
				orders = null;
				if (insertProductServes.size()>0) {
					this.productServeDao.batchInsertProductServe(insertProductServes);
					insertProductServes.clear();
				}
				insertProductServes = null;
				if (updateProductServes.size()>0) {
					this.productServeDao.batchUpdateProductServe(updateProductServes);
					updateProductServes.clear();
				}
				updateProductServes = null;
				flag = true ;
			}
			productServeParam = null;
			
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
}
