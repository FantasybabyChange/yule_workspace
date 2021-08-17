package com.yule.detail.service;

import java.util.List;

import com.yule.detail.query.CompanyDistanceQuery;
import com.yule.detail.vo.CompanyCountVO;
import com.yule.detail.vo.CompanyDistanceVO;
import com.yule.detail.vo.CompanyVO;
import com.yule.exception.YuleException;
import com.yule.pojo.Company;

public interface ICompanyService {
	public CompanyVO findCompanyVOById(String id)throws YuleException;
	
	public List<Company>findOtherCompanyByCompanyId(String id,String city_id,int pageSize,int pageNo)throws YuleException;
	
	public CompanyCountVO findCompanyCountVO(String province_id,String city_id,String country_id)throws YuleException;
	
	public List<CompanyDistanceVO> findCompanyDistanceVO(CompanyDistanceQuery companyDistanceQuery,int pagesize,int pageNo) throws YuleException;
}
