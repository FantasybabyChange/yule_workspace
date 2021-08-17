package com.yule.test;

import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.beans.factory.annotation.Autowired;

import com.yule.constant.CustomBeanConst;
import com.yule.constant.TimerJobConst;
import com.yule.exception.YuleException;
import com.yule.mongo.pojo.TimerJob;
import com.yule.mongo.timer.service.ITimerJobMongo;
import com.yule.util.CustomBeanFactory;

public class DataInit extends TestCase{
	
	@Autowired
	private ITimerJobMongo timerJobMongoImpl;

	public DataInit(String name) {
		super(name);
		timerJobMongoImpl = (ITimerJobMongo) CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("timerJobMongoImpl");
	}

	public static Test suite() {
		TestSuite test = new TestSuite("AdminNoticeServiceTest接口测试");
		test.addTest(new DataInit("insertTimerJob"));
		return test;
	}
	
	public void insertTimerJob() {
		try {
			TimerJob timerJob = new TimerJob();
			timerJob.setName("企业结算");
			timerJob.setGroup("结算");
			timerJob.setStatus(TimerJobConst.STATUS_TRUE);
			timerJob.setAsync(TimerJobConst.ASYNC_ZERO);
			timerJob.setClassName("com.yule.quartz.balance.CompanyQuartz");
			timerJob.setCronExpression("59 59 23 L * ?");
			timerJob.setDetails("企业结算");
			timerJob.setCreate_time(new Date());
			timerJobMongoImpl.insertTimerJob(timerJob);
			timerJob = new TimerJob();
			timerJob.setName("业务员结算");
			timerJob.setGroup("结算");
			timerJob.setStatus(TimerJobConst.STATUS_TRUE);
			timerJob.setAsync(TimerJobConst.ASYNC_ZERO);
			timerJob.setClassName("com.yule.quartz.balance.SalesmanQuartz");
			timerJob.setCronExpression("59 59 23 ? * SUN");
			timerJob.setDetails("业务员结算");
			timerJob.setCreate_time(new Date());
			timerJobMongoImpl.insertTimerJob(timerJob);
			timerJob = new TimerJob();
			timerJob.setName("区域缓存");
			timerJob.setGroup("缓存Redis");
			timerJob.setStatus(TimerJobConst.STATUS_TRUE);
			timerJob.setAsync(TimerJobConst.ASYNC_ZERO);
			timerJob.setClassName("com.yule.quartz.cached.AreaQuartz");
			timerJob.setCronExpression("0 0 0/1 * * ?");
			timerJob.setDetails("区域缓存");
			timerJob.setCreate_time(new Date());
			timerJobMongoImpl.insertTimerJob(timerJob);
			timerJob = new TimerJob();
			timerJob.setName("企业数据缓存");
			timerJob.setGroup("缓存Redis");
			timerJob.setStatus(TimerJobConst.STATUS_TRUE);
			timerJob.setAsync(TimerJobConst.ASYNC_ZERO);
			timerJob.setClassName("com.yule.quartz.cached.CompanyQuartz");
			timerJob.setCronExpression("0 0 0/1 * * ?");
			timerJob.setDetails("企业数据缓存");
			timerJob.setCreate_time(new Date());
			timerJobMongoImpl.insertTimerJob(timerJob);
			timerJob = new TimerJob();
			timerJob.setName("用户数据缓存");
			timerJob.setGroup("缓存Redis");
			timerJob.setStatus(TimerJobConst.STATUS_TRUE);
			timerJob.setAsync(TimerJobConst.ASYNC_ZERO);
			timerJob.setClassName("com.yule.quartz.cached.UserQuartz");
			timerJob.setCronExpression("0 0 0/1 * * ?");
			timerJob.setDetails("用户数据缓存");
			timerJob.setCreate_time(new Date());
			timerJobMongoImpl.insertTimerJob(timerJob);
			timerJob = new TimerJob();
			timerJob.setName("企业列表索引");
			timerJob.setGroup("索引Lucene");
			timerJob.setStatus(TimerJobConst.STATUS_TRUE);
			timerJob.setAsync(TimerJobConst.ASYNC_ZERO);
			timerJob.setClassName("com.yule.quartz.lucene.CompanyQuartz");
			timerJob.setCronExpression("0 0 0/1 * * ?");
			timerJob.setDetails("企业列表索引");
			timerJob.setCreate_time(new Date());
			timerJobMongoImpl.insertTimerJob(timerJob);
			timerJob = new TimerJob();
			timerJob.setName("搜索条件索引");
			timerJob.setGroup("索引Lucene");
			timerJob.setStatus(TimerJobConst.STATUS_TRUE);
			timerJob.setAsync(TimerJobConst.ASYNC_ZERO);
			timerJob.setClassName("com.yule.quartz.lucene.CompanyCriteriaQuartz");
			timerJob.setCronExpression("0 0 0/1 * * ?");
			timerJob.setDetails("搜索条件索引");
			timerJob.setCreate_time(new Date());
			timerJobMongoImpl.insertTimerJob(timerJob);
			timerJob = new TimerJob();
			timerJob.setName("PC首页HTML");
			timerJob.setGroup("静态页面");
			timerJob.setStatus(TimerJobConst.STATUS_TRUE);
			timerJob.setAsync(TimerJobConst.ASYNC_ZERO);
			timerJob.setClassName("com.yule.quartz.html.PcIndexQuartz");
			timerJob.setCronExpression("0 0/45 * * * ?");
			timerJob.setDetails("搜索条件索引");
			timerJob.setCreate_time(new Date());
			timerJobMongoImpl.insertTimerJob(timerJob);
		} catch (YuleException e) {
			e.printStackTrace();
		}
		
	}
}
