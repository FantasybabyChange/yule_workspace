package com.yule.cached;

import java.util.List;

import net.sf.json.JSONArray;

import com.yule.admin.service.ICompanyCategoryService;
import com.yule.admin.service.ICompanyCommentCategoryService;
import com.yule.admin.service.ICompanyGradeService;
import com.yule.admin.service.ICompanyPointCategoryService;
import com.yule.constant.CustomBeanConst;
import com.yule.constant.TimeConst;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CustomBeanFactory;
import com.yule.vo.CompanyCategoryVO;
import com.yule.vo.CompanyCommentCategoryVO;
import com.yule.vo.CompanyGradeVO;
import com.yule.vo.CompanyPointCategoryVO;

public class CompanyInit {
	
	public static void init() throws Exception{
		initCompanyGrade();
		initCompanyPointCategory();
		initCompanyCategory();
		initCompanyCommentCategory();
	}
	
	public static void initCompanyGrade() throws Exception{
		ICompanyGradeService companyGradeServiceImpl = (ICompanyGradeService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyGradeServiceImpl");
		List<CompanyGradeVO> companyGradeVOs = companyGradeServiceImpl.findCompanyGradeVOList();
		if(null!=companyGradeVOs&&companyGradeVOs.size()>0){
			JedisUtil.getInstance().del(RedisConst.COMPANY_GRADE);
			JedisUtil.getInstance().set(RedisConst.COMPANY_GRADE, JSONArray.fromObject(companyGradeVOs).toString(),TimeConst.TWO_DAY);
			companyGradeVOs.clear();
			companyGradeVOs = null;
		}
		companyGradeServiceImpl = null;
	}
	
	public static void initCompanyPointCategory() throws Exception{
		ICompanyPointCategoryService companyPointCategoryServiceImpl = (ICompanyPointCategoryService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyPointCategoryServiceImpl");
		List<CompanyPointCategoryVO> companyPointCategorys = companyPointCategoryServiceImpl.findCompanyPointCategoryVOList();
		if(null!=companyPointCategorys&&companyPointCategorys.size()>0){
			JedisUtil.getInstance().del(RedisConst.COMPANY_POINT_CATEGORY);
			JedisUtil.getInstance().set(RedisConst.COMPANY_POINT_CATEGORY, JSONArray.fromObject(companyPointCategorys).toString(),TimeConst.TWO_DAY);
			companyPointCategorys.clear();
			companyPointCategorys = null;
		}
		companyPointCategoryServiceImpl = null;
	}
	
	public static void initCompanyCommentCategory() throws Exception{
		ICompanyCommentCategoryService companyCommentCategoryServiceImpl = (ICompanyCommentCategoryService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyCommentCategoryServiceImpl");
		List<CompanyCommentCategoryVO> companyCommentCategorys = companyCommentCategoryServiceImpl.findCompanyCommentCategoryVOList();
		if(null!=companyCommentCategorys&&companyCommentCategorys.size()>0){
			JedisUtil.getInstance().del(RedisConst.COMPANY_COMMENT_CATEGORY);
			JedisUtil.getInstance().set(RedisConst.COMPANY_COMMENT_CATEGORY, JSONArray.fromObject(companyCommentCategorys).toString(),TimeConst.TWO_DAY);
			companyCommentCategorys.clear();
			companyCommentCategorys = null;
		}
		companyCommentCategoryServiceImpl = null;
	}
	
	public static void initCompanyCategory() throws Exception{
		ICompanyCategoryService companyCategoryServiceImpl = (ICompanyCategoryService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyCategoryServiceImpl");
		List<CompanyCategoryVO> companyCategoryVOs = companyCategoryServiceImpl.findCompanyCategoryVOList();
		if(null!=companyCategoryVOs&&companyCategoryVOs.size()>0){
			JedisUtil.getInstance().del(RedisConst.COMPANY_CATEGORY);
			JedisUtil.getInstance().set(RedisConst.COMPANY_CATEGORY, JSONArray.fromObject(companyCategoryVOs).toString(),TimeConst.TWO_DAY);
			companyCategoryVOs.clear();
			companyCategoryVOs = null;
		}
		companyCategoryServiceImpl = null;
	}
}