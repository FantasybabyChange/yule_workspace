package com.yule.admin.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.ProductExpense;


public interface IProductExpenseDao {

	public int insertProductExpense(ProductExpense productExpense) throws YuleException;
	
	public int updateProductExpense(ProductExpense productExpense) throws YuleException;
	
	public int deleteProductExpenseById(String id) throws YuleException;

	public ProductExpense findProductExpenseById(String id) throws YuleException;

	public List<ProductExpense> findProductExpenseList() throws YuleException;
	
	public int findProductExpenseCount() throws YuleException;
	
	public List<ProductExpense> findProductExpenseListByProductId(String id) throws YuleException;
	
	public int batchInsertProductExpense(List<ProductExpense> productExpense) throws YuleException;
	
	public int deleteProductExpenseByProductId(String id) throws YuleException;

}
