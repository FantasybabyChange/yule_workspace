package com.yule.pc.dao;

import java.util.List;
import java.util.Map;

import com.yule.pc.vo.CompanyHotVO;


public interface ICompanyDao {
	
	public List<CompanyHotVO> findCompanyHotVOList(Map<String,Object> params);
	
}
