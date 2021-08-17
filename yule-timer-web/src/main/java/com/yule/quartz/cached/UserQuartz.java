package com.yule.quartz.cached;

import java.util.List;

import net.sf.json.JSONArray;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

import com.yule.admin.service.IUserLevelService;
import com.yule.constant.CustomBeanConst;
import com.yule.constant.TimeConst;
import com.yule.exception.YuleException;
import com.yule.pojo.UserLevel;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CustomBeanFactory;
import com.yule.util.TimerJobUtil;

public class UserQuartz extends StatefulMethodInvokingJob{
	
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		try {
			initUserLevel();
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
	
	private void initUserLevel() throws Exception{
		IUserLevelService userLevelServiceImpl = (IUserLevelService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("userLevelServiceImpl");
		List<UserLevel> userLevels = userLevelServiceImpl.findUserLevelList();
		if(null!=userLevels&&userLevels.size()>0){
			JedisUtil.getInstance().del(RedisConst.USER_LEVEL);
			JedisUtil.getInstance().set(RedisConst.USER_LEVEL, JSONArray.fromObject(userLevels).toString(),TimeConst.TWO_DAY);
			userLevels.clear();
			userLevels = null;
		}
		userLevelServiceImpl = null;
	}
	
}
