package com.yule.company.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.company.dao.IProductExpenseDao;
import com.yule.company.service.IProductExpenseService;
import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.param.InsertProductExpenseParam;
import com.yule.pojo.ProductExpense;
import com.yule.util.IDUtil;

@Service("productExpenseServiceImpl")
public class ProductExpenseServiceImpl implements IProductExpenseService {

	@Autowired
	private IProductExpenseDao productExpenseDao;

	public List<ProductExpense> findProductExpenseList() throws YuleException{
		return this.productExpenseDao.findProductExpenseList();
	}

	public boolean insertProductExpense(ProductExpense productExpense)
			throws YuleException {
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(productExpense.getId())){
				productExpense.setId(IDUtil.getID());
			}
			this.productExpenseDao.insertProductExpense(productExpense);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateProductExpense(ProductExpense productExpense)
			throws YuleException {
		boolean flag = false;
		try {
			this.productExpenseDao.updateProductExpense(productExpense);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteProductExpenseById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.productExpenseDao.deleteProductExpenseById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public ProductExpense findProductExpenseById(String id)throws YuleException {
		return this.productExpenseDao.findProductExpenseById(id);
	}

	public int findProductExpenseCount() throws YuleException{
		return this.productExpenseDao.findProductExpenseCount();
	}

	public List<ProductExpense> findProductExpenseListByProductId(String id)throws YuleException {
		return this.productExpenseDao.findProductExpenseListByProductId(id);
	}

	public boolean updateProductExpense(InsertProductExpenseParam insertProductExpenseParam)throws YuleException {	
		boolean flag = false;
		List<ProductExpense> insert = new ArrayList<ProductExpense>();
	    this.productExpenseDao.deleteProductExpenseByProductId(insertProductExpenseParam.getProductId());
	    List<ProductExpense> productExpenses = insertProductExpenseParam.getProductExpense();
	    if(null!=productExpenses&&productExpenses.size()>0){
			for (ProductExpense productExpense : productExpenses) {
				if(!StringUtils.isEmpty(productExpense.getName())){
					productExpense.setIs_delete(DeleteConst.IS_DELETE_TRUE);
					productExpense.setProduct_id(insertProductExpenseParam.getProductId());
					productExpense.setId(IDUtil.getID());
					insert.add(productExpense);
				}
				productExpenses.clear();
				productExpenses=null;
			}
	    }
		if(null != insert && insert.size()>0){
			this.productExpenseDao.batchInsertProductExpense(insert);
			insert.clear();
			insert=null;
		}
		flag =true;
		return flag;
	}

	public boolean batchInsertProductExpense(List<ProductExpense> productExpense)
			throws YuleException {
		boolean flag = false;
		try {
			this.productExpenseDao.batchInsertProductExpense(productExpense);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

}
