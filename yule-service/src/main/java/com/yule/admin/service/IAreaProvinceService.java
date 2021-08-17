package com.yule.admin.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AreaProvince;

public interface IAreaProvinceService {

	public boolean insertAreaProvince(AreaProvince areaProvince) throws YuleException;

	public boolean updateAreaProvince(AreaProvince areaProvince) throws YuleException;

	public boolean deleteAreaProvinceById(String id) throws YuleException;

	public List<AreaProvince> findAreaProvinceList()throws YuleException;

	public int findAreaProvinceCount()throws YuleException;
	
	public boolean deleteAreaProvinceAll()throws YuleException;
	
	public boolean batchInsertAreaProvince(List<AreaProvince> areaProvinces)throws YuleException;

}
