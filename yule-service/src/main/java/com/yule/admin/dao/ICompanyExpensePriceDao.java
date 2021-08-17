package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyExpensePrice;

public interface ICompanyExpensePriceDao {
	
	public int insertCompanyExpensePrice(CompanyExpensePrice companyExpensePrice)throws YuleException;
	
	public int updateCompanyExpensePrice(CompanyExpensePrice companyExpensePrice)throws YuleException;

	public int deleteCompanyExpensePriceById(String id)throws YuleException;
	
/*	//级联
	public List<CompanyExpensePriceVO> findCompanyExpensePriceVOListByCompanyId(Map<String, Object> params);
	
	public int findCompanyExpensePriceVOCountByCompanyId(String company_id);
*/
	//不需要级联
	public List<CompanyExpensePrice> findCompanyExpensePriceListByCompanyId(Map<String, Object> params);
		
	public int findCompanyExpensePriceCountByCompanyId(String company_id);
	
	
	public int batchUpdateCompanyExpensePrice(List<CompanyExpensePrice> companyExpensePrices);
	
	public int batchInsertCompanyExpensePrice(List<CompanyExpensePrice> companyExpensePrices);
}
