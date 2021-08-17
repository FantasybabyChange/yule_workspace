package com.yule.admin.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AreaProvince;


public interface IAreaProvinceDao {

	public int insertAreaProvince(AreaProvince areaProvince) throws YuleException;
	
	public int updateAreaProvince(AreaProvince areaProvince) throws YuleException;
	
	public int deleteAreaProvinceById(String id) throws YuleException;

	public List<AreaProvince> findAreaProvinceList() throws YuleException;
	
	public int findAreaProvinceCount() throws YuleException;

	public int deleteAreaProvinceAll() throws YuleException;
	
	public int batchInsertAreaProvince(List<AreaProvince> areaProvinces) throws YuleException;
	
}
