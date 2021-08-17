package com.yule.company.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.param.InsertProductExpenseParam;
import com.yule.pojo.ProductExpense;

public interface IProductExpenseService {

	public boolean insertProductExpense(ProductExpense productExpense) throws YuleException;

	public boolean updateProductExpense(ProductExpense productExpense) throws YuleException;
	
	public boolean updateProductExpense(InsertProductExpenseParam insertProductExpenseParam) throws YuleException;

	public boolean deleteProductExpenseById(String id) throws YuleException;

	public ProductExpense findProductExpenseById(String id)throws YuleException;

	public List<ProductExpense> findProductExpenseList()throws YuleException;

	public int findProductExpenseCount()throws YuleException;
	
	public List<ProductExpense> findProductExpenseListByProductId(String id)throws YuleException;
	
	
	public boolean batchInsertProductExpense(List<ProductExpense> productExpense) throws YuleException;
	

}
