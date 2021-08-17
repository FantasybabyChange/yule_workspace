package com.yule.mongo.timer.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyTask;



public interface ICompanyTaskMongo {
	
	public boolean deleteCompanyTask(String companyId) throws YuleException;
	
	public boolean batchInsertCompanyTask(List<CompanyTask> companyTasks) throws YuleException;
	
}
