package com.yule.action;

import java.util.List;

import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.PageConst;
import com.yule.constant.TimerJobConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.pojo.TimerJob;
import com.yule.mongo.timer.service.ITimerJobMongo;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.util.QuartzUtil;
import com.yule.util.StringUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
public class TimerJobAction extends BaseAction{
	
	@Autowired
	private ITimerJobMongo timerJobMongoImpl;

	/**
	 * 查询定时任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findTimerJob",method = RequestMethod.GET)
	public String findTimerJobList(@RequestParam(value="pageNo",required=false)Integer pageNo) throws Exception {
		if(pageNo==null||pageNo<=0){
			pageNo = 1;
		}
		try {
			Page<TimerJob> page = timerJobMongoImpl.findTimerJobPage(PageConst.PAGE_SIZE_TEN,pageNo);
			StringBuffer htmls = new StringBuffer("");
			htmls.append("<tfoot>");
			htmls.append("<tr>");
			htmls.append("<td colspan=\"11\">");
			htmls.append("<div class=\"bulk-actions align-left\">");
//			htmls.append("<a class=\"button\" href=\"#\">批量删除</a>");
			htmls.append("</div>");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td>");
			htmls.append("</tr>");
			htmls.append("</tfoot>");					
			List<TimerJob> lists = page.getDatas();
			if(null!=lists&&lists.size()>0){
				for(TimerJob timerJob:lists){
					htmls.append("<tbody>");
					htmls.append("<tr>");
					htmls.append("<td>"+timerJob.getName()+"</td>");
					htmls.append("<td>"+timerJob.getGroup()+"</td>");
					htmls.append("<td>"+timerJob.getClassName()+"</td>");
					htmls.append("<td>"+timerJob.getCronExpression()+"</td>");
					htmls.append("<td>"+TimerJobConst.ASYNCS[timerJob.getAsync()]+"</td>");
					htmls.append("<td>"+TimerJobConst.STATUS[timerJob.getStatus()]+"</td>");
					htmls.append("<td>"+timerJob.getExecute_num()+"</td>");
					htmls.append("<td>"+DateUtil.DateToString(timerJob.getExecute_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+"</td>");
					htmls.append("<td>"+StringUtil.cut(timerJob.getError_message(), 10)+"</td>");
					htmls.append("<td>");
					htmls.append("<a class=\"button\" href=\"/findTimerJobById.do?id="+timerJob.getId()+"\" >更新</a>&nbsp;");
					htmls.append("<a class=\"button\" href=\"/deleteTimerJob.do?id="+timerJob.getId()+"\" >删除</a>");
					htmls.append("</td>");
					htmls.append("</tr>");
					htmls.append("</tbody>");
				}
				page.getDatas().clear();
			}else{
				htmls.append("<tbody>");
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"10\">暂无任务</td>");
				htmls.append("</td>");
				htmls.append("</tr>");
				htmls.append("</tbody>");
			}
			request.setAttribute("timerJobHtmls", htmls);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		}finally{
			System.gc();
		}
		return "index";
	}
	
	/**
	 * 添加定时任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertTimerJob",method = RequestMethod.POST)
	public String insertTimerJob(TimerJob timerJob) throws Exception {
		try {
			String superName = Class.forName(timerJob.getClassName()).getSuperclass().getSimpleName();
			if(TimerJobConst.METHOD_INVOKING_JOB.equals(superName)){
				timerJob.setAsync(TimerJobConst.ASYNC_ONE);
			}else if(TimerJobConst.SATEFUL_METHOD_INVOKING_JOB.equals(superName)){
				timerJob.setAsync(TimerJobConst.ASYNC_ZERO);
			}
			timerJobMongoImpl.insertTimerJob(timerJob);
			JobDataMap paramsMap = new JobDataMap();
			paramsMap.put("id",timerJob.getId().toString());
			QuartzUtil.enableCronSchedule(timerJob, paramsMap);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		}finally{
			System.gc();
		}
		return ActionReturnConst.REDIRECT+"/findTimerJob.do";
	}
	
	/**
	 * 删除定时任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteTimerJob",method = RequestMethod.GET)
	public String deleteTimerJob(@RequestParam(value="id",required=false)String id) throws Exception {
		try {
			TimerJob timerJob = timerJobMongoImpl.findTimerJobById(id);
			boolean flag = QuartzUtil.disableSchedule(timerJob.getId().toString(),timerJob.getGroup());
			if(flag){
				timerJobMongoImpl.deleteTimerJob(id);
			}
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/findTimerJob.do";
	}
	
	/**
	 * 根据ID查询任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findTimerJobById",method = RequestMethod.GET)
	public String findTimerJobById(@RequestParam(value="id",required=false)String id) throws Exception {
		try {
			TimerJob timerJob = timerJobMongoImpl.findTimerJobById(id);
			request.setAttribute("timerJob",timerJob);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			System.gc();
		}
		return "update";
	}
	
	/**
	 * 更新定时任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateTimerJob",method = RequestMethod.POST)
	public String updateTimerJob(TimerJob timerJob) throws Exception {
		try {
			String id = timerJob.getId().toString();
			TimerJob job = timerJobMongoImpl.findTimerJobById(id);
//			String superName = Class.forName(timerJob.getClassName()).getSuperclass().getSimpleName();
//			if(TimerJobConst.METHOD_INVOKING_JOB.equals(superName)){
//				timerJob.setAsync(TimerJobConst.IS_ASYNC_ONE);
//			}else if(TimerJobConst.SATEFUL_METHOD_INVOKING_JOB.equals(superName)){
//				timerJob.setAsync(TimerJobConst.IS_ASYNC_ZERO);
//			}
			if(TimerJobConst.STATUS_FALSE==timerJob.getStatus()){
				QuartzUtil.disableSchedule(id, job.getGroup());
			}else{
				job.setCronExpression(timerJob.getCronExpression());
				JobDataMap jobDateMap = new JobDataMap();
				jobDateMap.put("id", id);
				QuartzUtil.enableCronSchedule(job, jobDateMap);
			}
			timerJobMongoImpl.updateTimerJob(timerJob);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/findTimerJob.do";
	}
	
}
