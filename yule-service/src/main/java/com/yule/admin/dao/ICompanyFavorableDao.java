package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyFavorable;
public interface ICompanyFavorableDao {
	/**
	 * 批量添加企业优惠信息
	 */
	public int batchInsertCompanyFavorable(List<CompanyFavorable> companyFavorable) throws YuleException;
	/**
	 * 批量更新企业优惠信息
	 */
	public int batchUpdateCompanyFavorable(List<CompanyFavorable> companyFavorable) throws YuleException;
	/**
	 * 根据企业id删除企业优惠信息
	 */
	public int deleteCompanyFavorableById(String id) throws YuleException;
	/**
	 * 新增企业优惠信息
	 */
	public int insertCompanyFavorable(CompanyFavorable companyFavorable) throws YuleException;
	/**
	 * 更新企业优惠信息
	 */
	public int updateCompanyFavorable(CompanyFavorable companyFavorable) throws YuleException;
	/**
	 * 查询
	 */
	public List<CompanyFavorable> findCompanyFavorableList(Map<String,Object> params) throws YuleException;


}
