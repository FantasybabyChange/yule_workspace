//package com.yule.quartz;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.util.StringUtils;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.constant.StatusConst;
//import com.yule.constant.TimerJobConst;
//import com.yule.mongo.pojo.Timer;
//import com.yule.mongo.timer.service.ITimerJobMongo;
//import com.yule.runnable.TimerRunnable;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.DateUtil;
//
//public class TimerJobQuartz {
//	
//	private static ITimerJobMongo timerMongoImpl = (ITimerJobMongo)CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("timerMongoImpl");
//	
//	public void execute() {
//		StringBuffer id = new StringBuffer();
//		try {
//			List<Timer> lists = timerMongoImpl.findTimerList();
//			if(null!=lists&&lists.size()>0){
//				Date executeTime = null;
//				long currentTime= 0;
//				long currentExecuteTime = 0;
//				long currentCycleTime = 0;
//				for(Timer timerJob : lists){
//					id.append(timerJob.getId().toString());
//					currentTime = System.currentTimeMillis();
//					executeTime = timerJob.getExecute_time();
//					if(null!=executeTime){
//						currentTime = currentTime - executeTime.getTime();
//						currentExecuteTime = getExecuteTime(timerJob.getCycle_num(),timerJob.getCycle_date());
//						if(currentTime > -1 && currentTime > currentExecuteTime){
//							requestTimerJob(timerJob);
//						}
//					}else{
////						currentCycleTime =  getCycleTime(timerJob.getCycle_time(),timerJob.getCycle_date());
//						currentCycleTime = timerJob.getCycle_time().getTime();
//						if(currentTime > currentCycleTime){
//							requestTimerJob(timerJob);
//						}
//					}
//					id.setLength(0);
//				}
//			}
//			lists.clear();
//		} catch (Exception e) {
//			new YuleException("定时器发生错误!",e);
//			if(!StringUtils.isEmpty(id)){
//				Timer timerJob = null;
//				try {
//					timerJob = timerMongoImpl.findTimerById(id.toString());
////					EmailUtil.sendEmail("任务:"+timerJob.getName()+"发生了错误!", e.getMessage());
//				} catch (Exception e1) {
//					new YuleException("发送邮件发生错误!",e);
//				}finally{
////					if(StatusConst.STATUS_TRUE==timerJob.getStatus()){
//					timerJob.setError_message(e.getMessage());
//					timerJob.setStatus(StatusConst.STATUS_FALSE);
//					try {
//						timerMongoImpl.updateTimer(timerJob);
//					} catch (Exception e1) {
//						new YuleException("更新定时任务发生错误!",e);
//					}
////					}
//				}
//			}
//		} 
//	}
//	
//	private static void requestTimerJob(Timer timer) throws Exception{
//		Timer timerJob = new Timer();
//		timerJob.setId(timer.getId());
//		timerJob.setExecute_num(timer.getExecute_num()+1);
//		timerJob.setExecute_time(DateUtil.getCurrentDate());
//		timerMongoImpl.updateTimer(timerJob);
//		Thread t = new Thread(new TimerRunnable(timer.getUrl()));
//		t.start();
//	}
//	
////	/**
////	 * 获取循环时间
////	 * @param num
////	 * @param type
////	 * @return
////	 */
////	private long getCycleTime(int num,int date){
////		long time = 0;
////		Calendar c = Calendar.getInstance();
////		switch (date) {
////			case TimerConst.CYCLE_DATE_YEAR:
////				c.set(Calendar.YEAR, num);
////				time = c.getTimeInMillis();
////				break;
////			case TimerConst.CYCLE_DATE_MONTH:
////				c.set(Calendar.MONTH,  num - 1);  
////		        time = c.getTimeInMillis();
////				break;
////			case TimerConst.CYCLE_DATE_DAY:
////				c.add(Calendar.DATE,num);  
////				time = c.getTimeInMillis();
////				break;
////			case TimerConst.CYCLE_DATE_HOUR:
////				c.set(Calendar.HOUR_OF_DAY, num);
////		        time = c.getTimeInMillis();
////				break;
////			case TimerConst.CYCLE_DATE_MINUTE:
////				c.set(Calendar.MINUTE, num);
////				time = c.getTimeInMillis();
////				break;
////			case TimerConst.CYCLE_DATE_SECOND:
////				c.set(Calendar.SECOND, num);
////				time = c.getTimeInMillis();
////				break;
////		}
////		return time;
////	}
//	
//	/**
//	 * 获取循环时间
//	 * @param num
//	 * @param type
//	 * @return
//	 */
//	private long getExecuteTime(int num,int date){
//		long time = 0;
//		switch (date) {
//			case TimerJobConst.CYCLE_DATE_YEAR:
//				time = (12 * 30 * 24 * 60 * 60 * 1000) * num;
//				break;
//			case TimerJobConst.CYCLE_DATE_MONTH:
//				time = (30 * 24 * 60 * 60 * 1000) * num;
//				break;
//			case TimerJobConst.CYCLE_DATE_DAY:
//				time = (24 * 60 * 60 * 1000) * num;
//				break;
//			case TimerJobConst.CYCLE_DATE_HOUR:
//				time = (60 * 60 * 1000) * num;
//				break;
//			case TimerJobConst.CYCLE_DATE_MINUTE:
//				time = (60 * 1000) * num;
//				break;
//			case TimerJobConst.CYCLE_DATE_SECOND:
//				time = 1000 * num;
//				break;
//		}
//		return time;
//	}
//	
//}
