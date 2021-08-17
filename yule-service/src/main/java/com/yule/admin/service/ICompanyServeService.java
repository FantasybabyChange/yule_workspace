package com.yule.admin.service;

import java.util.List;

import com.yule.admin.param.InsertCompanyServeParam;
import com.yule.admin.param.UpdateCompanyServeCheckParam;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyServe;
import com.yule.vo.CompanyServeVO;

/**
 * 企业服务设施
 */
public interface ICompanyServeService {
	
	public boolean insertCompanyServe(CompanyServe companyServe) throws YuleException;

	public boolean updateCompanyServe(CompanyServe companyServe) throws YuleException;

	public boolean deleteCompanyServeById(String id) throws YuleException;
	
	public boolean deleteCompanyServeAll() throws YuleException;
	
	public List<CompanyServe> findCompanyServeIsCheckList(String companyId) throws YuleException;

	public List<CompanyServe> findCompanyServeList() throws YuleException;
	
	public List<CompanyServeVO> findCompanyServeCheckList(String companyId) throws YuleException;
	
    public boolean batchInsertAndUpdateCompanyServe(InsertCompanyServeParam insertCompanyServeParam)throws YuleException;
    
    public boolean batchInsertCompanyServe(List<CompanyServe> companyServes) throws YuleException;
    
    public boolean updateCompanyServeCheck(UpdateCompanyServeCheckParam updateCompanyServeCheckParam)throws YuleException;
    
}