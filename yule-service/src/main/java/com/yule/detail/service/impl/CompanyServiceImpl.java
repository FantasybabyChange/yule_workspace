package com.yule.detail.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.detail.dao.ICompanyDao;
import com.yule.detail.query.CompanyDistanceQuery;
import com.yule.detail.service.ICompanyService;
import com.yule.detail.vo.CompanyCountVO;
import com.yule.detail.vo.CompanyDistanceVO;
import com.yule.detail.vo.CompanyVO;
import com.yule.exception.YuleException;
import com.yule.pojo.Company;
@Service("companyServiceImpl")
public class CompanyServiceImpl implements ICompanyService {
	@Autowired
	private ICompanyDao companyDao;
	
	public CompanyVO findCompanyVOById(String id) throws YuleException {
		return  this.companyDao.findCompanyVOById(id);
	}

	public List<Company> findOtherCompanyByCompanyId(String id,String city_id, int pageSize,int pageNo) throws YuleException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageStart", (pageNo - 1) * pageSize);
		map.put("pageEnd", pageSize);
		map.put("id", id);
		map.put("city_id", city_id);
		return this.companyDao.findOtherCompanyById(map);
		
	}

	public CompanyCountVO findCompanyCountVO(String province_id,
			String city_id, String country_id) throws YuleException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("province_id", province_id);
		map.put("city_id", city_id);
		map.put("country_id", country_id);
		return this.companyDao.findCompanyCountVO(map);
	}

	public List<CompanyDistanceVO> findCompanyDistanceVO(CompanyDistanceQuery companyDistanceQuery, int pageSize, int pageNo)	throws YuleException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("company_id", companyDistanceQuery.getId());
		map.put("city_id", companyDistanceQuery.getCity_id());
		map.put("lng", companyDistanceQuery.getLng());
		map.put("lat", companyDistanceQuery.getLat());
		map.put("pageStart", (pageNo - 1) * pageSize);
		map.put("pageEnd", pageSize);
		return this.companyDao.findCompanyDistanceVO(map);
	}

}
