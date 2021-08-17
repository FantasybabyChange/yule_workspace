package com.yule.timer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.timer.dao.ISalesmanDao;
import com.yule.timer.service.ISalesmanService;
import com.yule.timer.vo.SalesmanVO;

@Service("salesmanServiceImpl")
public class SalesmanServiceImpl implements ISalesmanService {

	@Autowired
	private ISalesmanDao salesmanDao;

	public List<SalesmanVO> findSalesmanVOList() throws YuleException {
		return this.salesmanDao.findSalesmanVOList();
	}


}
