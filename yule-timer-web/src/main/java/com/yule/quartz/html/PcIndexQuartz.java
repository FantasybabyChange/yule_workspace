package com.yule.quartz.html;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

import com.yule.constant.TimeConst;
import com.yule.exception.YuleException;
import com.yule.util.HttpRequestUtil;
import com.yule.util.TimerJobUtil;

public class PcIndexQuartz extends StatefulMethodInvokingJob{
	
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		try {
			HttpRequestUtil.doGeVoid("http://www.yuleing.com/html/createIndexHtml.do?t=timer",TimeConst.FIFTEEN_MINUTE,TimeConst.FIFTEEN_MINUTE);
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
