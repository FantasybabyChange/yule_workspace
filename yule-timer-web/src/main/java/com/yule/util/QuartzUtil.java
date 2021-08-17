package com.yule.util;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdScheduler;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.yule.constant.TimerJobConst;
import com.yule.exception.YuleException;
import com.yule.mongo.pojo.TimerJob;

/**
 * 
 */
public class QuartzUtil {
	
	private static Scheduler scheduler;

	static {
		scheduler = (StdScheduler) new ClassPathXmlApplicationContext(TimerJobConst.QUARTZ_PATH).getBean("schedulerFactory");
	}

	/**
	 * 启动一个自定义的job
	 * @param timerJob 自定义的job
	 * @param paramsMap 传递给job执行的数据
	 * @return 成功则返回true，否则返回false
	 */
	public static boolean enableCronSchedule(TimerJob timerJob,
			JobDataMap paramsMap) {
		if (timerJob == null) {
			return false;
		}
		try {
			String id = timerJob.getId().toString();
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(id+TimerJobConst.TRIGGER,timerJob.getGroup());
			if (null == trigger) {
				JobDetail jobDetail = null;
				if (TimerJobConst.ASYNC_ZERO==timerJob.getAsync()) {
					jobDetail = new JobDetail(id,
							timerJob.getGroup(),
							Class.forName(timerJob.getClassName()));
				} else {
					jobDetail = new JobDetail(id,
							timerJob.getGroup(),
							Class.forName(timerJob.getClassName()));
				}
				jobDetail.setJobDataMap(paramsMap);
				trigger = new CronTrigger(id+TimerJobConst.TRIGGER,
						timerJob.getGroup(),
						timerJob.getCronExpression());
				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				trigger.setCronExpression(timerJob.getCronExpression());
				scheduler.rescheduleJob(trigger.getName(), trigger.getGroup(),
						trigger);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 禁用一个job
	 * @param id 需要被禁用的job的id
	 * @param group 需要被警用的组ID
	 * @return 成功则返回true，否则返回false
	 */
	public static boolean disableSchedule(String id, String group) {
		if (StringUtils.isEmpty(id) || StringUtils.isEmpty(group)) {
			return false;
		}
		try {
			Trigger trigger = getJobTrigger(id, group);
			System.out.println(trigger);
			if (null != trigger) {
				boolean flag = scheduler.deleteJob(id, group);
				System.out.println(flag);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 得到job的详细信息
	 * @param id job的id
	 * @param group job的组ID
	 * @return job的详细信息,如果job不存在则返回null
	 */
	public static JobDetail getJobDetail(String id ,String group) throws SchedulerException{
		if (StringUtils.isEmpty(id) || StringUtils.isEmpty(group)) {
			return null;
		}
		try {
			return scheduler.getJobDetail(id, group);
		} catch (SchedulerException e) {
			new YuleException("得到job的详细信息发生错误!",e);
			throw e;
		}
	}

	/**
	 * 得到job对应的Trigger
	 * @param id job的id
	 * @param group job的组ID
	 * @return job的Trigger,如果Trigger不存在则返回null
	 */
	public static Trigger getJobTrigger(String id, String group) throws SchedulerException{
		if (StringUtils.isEmpty(id) || StringUtils.isEmpty(group)) {
			return null;
		}
		try {
			return scheduler.getTrigger(id + TimerJobConst.TRIGGER, group);
		} catch (SchedulerException e) {
			new YuleException("得到job对应的Trigger!",e);
			throw e;
		}
	}

}
