package com.yule.timer.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.timer.vo.SalesmanVO;

public interface ISalesmanService {
	
	public List<SalesmanVO> findSalesmanVOList() throws YuleException;
	
}
