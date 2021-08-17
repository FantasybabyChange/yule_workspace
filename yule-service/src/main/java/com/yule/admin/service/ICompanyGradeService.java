package com.yule.admin.service;

import java.util.List;

import com.yule.admin.param.InsertCompanyGradeParam;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyGrade;
import com.yule.vo.CompanyGradeVO;

public interface ICompanyGradeService {

	public boolean insertCompanyGrade(CompanyGrade companyGrade) throws YuleException;

	public boolean updateCompanyGrade(CompanyGrade companyGrade) throws YuleException;

	public boolean deleteCompanyGradeById(String id) throws YuleException;
	
	public boolean deleteCompanyGradeAll() throws YuleException;

	public List<CompanyGradeVO> findCompanyGradeVOList() throws YuleException;
	
	public List<CompanyGrade> findCompanyGradeList() throws YuleException;
	
	public boolean batchInsertAndUpdateCompanyGrade(InsertCompanyGradeParam insertCompanyGradeParam) throws YuleException;
	
	public boolean batchInsertCompanyGrade(List<CompanyGrade> companyGrades) throws YuleException;


}
