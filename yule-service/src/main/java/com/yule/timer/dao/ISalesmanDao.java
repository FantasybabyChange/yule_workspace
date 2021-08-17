package com.yule.timer.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.timer.vo.SalesmanVO;

public interface ISalesmanDao {
	
	public List<SalesmanVO> findSalesmanVOList() throws YuleException;
	
}
