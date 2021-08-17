package com.yule.quartz;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

import com.yule.constant.CustomBeanConst;
import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyTask;
import com.yule.mongo.timer.service.ICompanyGalleryMongo;
import com.yule.mongo.timer.service.ICompanyTaskMongo;
import com.yule.timer.service.ICompanyService;
import com.yule.timer.vo.CompanyTaskVO;
import com.yule.util.CustomBeanFactory;
import com.yule.util.TimerJobUtil;

//MethodInvokingJob异步
public class CompanyTaskQuartz extends StatefulMethodInvokingJob{
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		ICompanyService companyServiceImpl = (ICompanyService)CustomBeanFactory.getContext(CustomBeanConst.TIMER_SERVICE_PATHS).getBean("companyServiceImpl");
		ICompanyTaskMongo companyTaskMongoImpl = (ICompanyTaskMongo)CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("companyTaskMongoImpl");
		ICompanyGalleryMongo companyGalleryMongoImpl = (ICompanyGalleryMongo)CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("companyGalleryMongoImpl");
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		try{
			List<CompanyTaskVO> companyTaskVOs = companyServiceImpl.findCompanyTaskVOList();
			if(null!=companyTaskVOs&&companyTaskVOs.size()>0){
				List<CompanyTask> companyTasks = new ArrayList<CompanyTask>();
				CompanyTask companyTask = null;
				String id = null;
				for(CompanyTaskVO companyTaskVO:companyTaskVOs){
					id = companyTaskVO.getId();
					companyTaskMongoImpl.deleteCompanyTask(id);
					
					if(companyTaskVO.getProduct_count()<=0){
						companyTask = new CompanyTask();
						companyTask.setName("企业产品");
						companyTask.setUrl("/product/findProduct.do");
						companyTask.setCompany_id(id);
						companyTasks.add(companyTask);
					}
					//企业图册
					if(companyGalleryMongoImpl.findCompanyGalleryCountByCompanyId(id)<=0){
						companyTask = new CompanyTask();
						companyTask.setName("企业图册");
						companyTask.setUrl("/companyGallery/findCompanyGallery.do");
						companyTask.setCompany_id(id);
						companyTasks.add(companyTask);
					}
					if(companyTaskVO.getCompany_favorable_count()<=0){
						companyTask = new CompanyTask();
						companyTask.setName("企业优惠");
						companyTask.setUrl("/companyFavorable/findCompanyFavorable.do");
						companyTask.setCompany_id(id);
						companyTasks.add(companyTask);
					}
					if(companyTaskVO.getCompany_phone_count()<=0){
						companyTask = new CompanyTask();
						companyTask.setName("企业联系电话");
						companyTask.setUrl("/companyPhone/findCompanyPhone.do");
						companyTask.setCompany_id(id);
						companyTasks.add(companyTask);
					}
					//企业服务设施
					if(companyTaskVO.getCompany_serve_count()<=0){
						companyTask = new CompanyTask();
						companyTask.setName("企业服务设施");
						companyTask.setUrl("/companyServe/findCompanyServe.do");
						companyTask.setCompany_id(id);
						companyTasks.add(companyTask);
					}
					//企业服务设施
					if(companyTaskVO.getCompany_expense_count()<=0){
						companyTask = new CompanyTask();
						companyTask.setName("企业消费管理");
						companyTask.setUrl("/companyExpense/findCompanyExpense.do");
						companyTask.setCompany_id(id);
						companyTasks.add(companyTask);
					}
				}
				if(companyTasks.size()>0){
					companyTaskMongoImpl.batchInsertCompanyTask(companyTasks);
					companyTasks.clear();
					companyTasks = null;
				}
			}
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
	
}
