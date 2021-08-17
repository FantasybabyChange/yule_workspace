package com.yule.timer.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.timer.vo.CompanyServeLuceneVO;

/**
 * 企业服务设施
 */
public interface ICompanyServeService {

	public List<CompanyServeLuceneVO> findCompanyServeLuceneVOList(String companyId) throws YuleException;

}
