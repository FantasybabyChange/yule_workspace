package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyGrade;
import com.yule.vo.CompanyGradeVO;


public interface ICompanyGradeDao {
	
	public int insertCompanyGrade(CompanyGrade companyGrade) throws YuleException;

	public int updateCompanyGrade(CompanyGrade companyGrade) throws YuleException;

	public int deleteCompanyGradeById(String id) throws YuleException;
	
	public int deleteCompanyGradeAll() throws YuleException;

	public List<CompanyGrade> findCompanyGradeList() throws YuleException;
	
	public List<CompanyGradeVO> findCompanyGradeVOList() throws YuleException;

	public int findCompanyGradeCount() throws YuleException;
	
	public List<CompanyGrade> findCompanyGradeList(Map<String,Object> params) throws YuleException;
	
	public int batchInsertCompanyGrade(List<CompanyGrade> companyGrade) throws YuleException;
	
	public int batchUpdateCompanyGrade(List<CompanyGrade> companyGrade) throws YuleException;
	
}
