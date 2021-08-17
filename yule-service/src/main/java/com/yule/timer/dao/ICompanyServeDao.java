package com.yule.timer.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.timer.vo.CompanyServeLuceneVO;

public interface ICompanyServeDao {
	
	public List<CompanyServeLuceneVO> findCompanyServeLuceneVOList(Map<String,Object> params) throws YuleException;
	
}
