package com.yule.mongo.timer.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.TimerJob;
import com.yule.vo.Page;

public interface ITimerJobMongo {
	
	public Page<TimerJob> findTimerJobPage(int pageSize,int pageNo) throws YuleException;
	
	public TimerJob findTimerJobById(String id) throws YuleException;
	
	public boolean insertTimerJob(TimerJob timerJob) throws YuleException;
	
	public boolean updateTimerJob(TimerJob timerJob) throws YuleException;
	
	public boolean deleteTimerJob(String id) throws YuleException;
	
	public List<TimerJob> findTimerJobList() throws YuleException;
	
}
