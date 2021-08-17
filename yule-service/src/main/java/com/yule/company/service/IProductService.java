package com.yule.company.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.param.InsertProductParam;
import com.yule.pojo.Product;
import com.yule.vo.Page;
import com.yule.vo.ProductVO;
public interface IProductService {

	public boolean insertProduct(Product product) throws YuleException;

	public boolean deleteProductById(String id) throws YuleException;

	public String batchInsertAndUpdateProduct(InsertProductParam insertProductParam) throws YuleException;
	
	public List<ProductVO> findProductVOList(String companyId) throws YuleException;
	
	public boolean updateProduct(Product product) throws YuleException ;
	
	public Page<ProductVO> findOtherCompanyProductPage(String company_name,String id,int pageSize,int pageNo)throws YuleException;
	
	public List<ProductVO> findSimpleProductVOList(String companyId) throws YuleException;
}
