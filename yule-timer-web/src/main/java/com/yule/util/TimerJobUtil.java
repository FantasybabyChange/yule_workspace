package com.yule.util;

import java.util.List;

import org.quartz.JobDataMap;

import com.yule.constant.CustomBeanConst;
import com.yule.constant.StatusConst;
import com.yule.exception.YuleException;
import com.yule.mongo.pojo.TimerJob;
import com.yule.mongo.timer.service.ITimerJobMongo;

public class TimerJobUtil {
	
	public static void init() throws YuleException {
		ITimerJobMongo timerJobMongoImpl  = (ITimerJobMongo)CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("timerJobMongoImpl");
		try{
			List<TimerJob> timerJobs = timerJobMongoImpl.findTimerJobList();
			if(null!=timerJobs&&timerJobs.size()>0){
				JobDataMap jobDataMap = new JobDataMap();
				for(TimerJob timerJob : timerJobs){
					jobDataMap.put("id", timerJob.getId().toString());
					QuartzUtil.enableCronSchedule(timerJob, jobDataMap);
				}
				timerJobs.clear();
				timerJobs = null;
			}
		}catch(Exception e){
			throw new YuleException(e);
		}
	}
	
	public static void updateTimerJob(String id) throws YuleException{
		ITimerJobMongo timerJobMongoImpl  = (ITimerJobMongo)CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("timerJobMongoImpl");
		try {
			TimerJob timerJob = timerJobMongoImpl.findTimerJobById(id);
			timerJob.setExecute_num(timerJob.getExecute_num()+1);
			timerJob.setExecute_time(DateUtil.getCurrentDate());
			timerJobMongoImpl.updateTimerJob(timerJob);
		} catch (Exception e) {
			throw new YuleException(e);
		}
	}
	
	public static void updateExceptionTimerJob(String id,String group,String message) throws YuleException{
		ITimerJobMongo timerJobMongoImpl  = (ITimerJobMongo)CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("timerJobMongoImpl");
		try {
			TimerJob timerJob = null;
			try {
				timerJob = timerJobMongoImpl.findTimerJobById(id);
//				EmailUtil.sendEmail("任务:"+timerJob.getName()+"发生了错误!", e.getMessage());
			} catch (Exception e) {
//				new YuleException("发送邮件发生错误!",e);
				throw new YuleException("发送邮件发生错误!",e);
			}finally{
				timerJob.setError_message(message);
				timerJob.setStatus(StatusConst.STATUS_FALSE);
				try {
					timerJobMongoImpl.updateTimerJob(timerJob);
				} catch (Exception e) {
//					new YuleException("更新定时任务发生错误!",e);
					throw new YuleException(e);
				}
			}
		} catch (Exception e) {
			throw new YuleException(e);
		} finally{
			QuartzUtil.disableSchedule(id, group);
		}
	} 
	
}