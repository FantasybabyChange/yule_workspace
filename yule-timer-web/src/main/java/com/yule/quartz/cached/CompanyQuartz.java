package com.yule.quartz.cached;

import java.util.List;

import net.sf.json.JSONArray;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

import com.yule.admin.service.ICompanyCategoryService;
import com.yule.admin.service.ICompanyCommentCategoryService;
import com.yule.admin.service.ICompanyGradeService;
import com.yule.admin.service.ICompanyPointCategoryService;
import com.yule.constant.CustomBeanConst;
import com.yule.constant.TimeConst;
import com.yule.exception.YuleException;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CustomBeanFactory;
import com.yule.util.TimerJobUtil;
import com.yule.vo.CompanyCategoryVO;
import com.yule.vo.CompanyCommentCategoryVO;
import com.yule.vo.CompanyGradeVO;
import com.yule.vo.CompanyPointCategoryVO;

public class CompanyQuartz extends StatefulMethodInvokingJob{
	
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		try {
			initCompanyGrade();
			initCompanyPointCategory();
			initCompanyCategory();
			initCompanyCommentCategory();
			TimerJobUtil.updateTimerJob(jobDataMap.getString("id"));
		} catch(Exception e){
			try {
				TimerJobUtil.updateExceptionTimerJob(jobDataMap.getString("id"),context.getTrigger().getGroup(),e.getMessage());
			} catch (Exception e1) {
				new YuleException(e1);
			}
			new YuleException(e);
		} finally{
			System.gc();
		}
	}
	
	private void initCompanyGrade() throws Exception{
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
	
	private void initCompanyPointCategory() throws Exception{
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
	
	private void initCompanyCommentCategory() throws Exception{
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
	
	private void initCompanyCategory() throws Exception{
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
