package com.yule.quartz.balance;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

import com.yule.constant.BalanceConst;
import com.yule.constant.CustomBeanConst;
import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyBalance;
import com.yule.mongo.timer.service.ICompanyBalanceMongo;
import com.yule.mongo.timer.service.IOrdersMongo;
import com.yule.mongo.timer.vo.CompanyOrdersBalanceVO;
import com.yule.timer.service.ICompanyService;
import com.yule.timer.vo.CompanyVO;
import com.yule.util.CalendarUtil;
import com.yule.util.CustomBeanFactory;
import com.yule.util.DateUtil;
import com.yule.util.TimerJobUtil;

public class CompanyQuartz extends StatefulMethodInvokingJob{

	@Override
	protected void executeInternal(JobExecutionContext context)throws JobExecutionException {
		ICompanyService companyServiceImpl = (ICompanyService)CustomBeanFactory.getContext(CustomBeanConst.TIMER_SERVICE_PATHS).getBean("companyServiceImpl");
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		try {
			List<CompanyBalance> companyBalances = new ArrayList<CompanyBalance>();
			List<CompanyVO> companyVOs = companyServiceImpl.findCompanyVOList();
			DecimalFormat df =new DecimalFormat("#.00");
			if (null!=companyVOs&&companyVOs.size()>0) {
				IOrdersMongo ordersMongoImpl = (IOrdersMongo) CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("ordersMongoImpl");
				ICompanyBalanceMongo companyBalanceMongoImpl = (ICompanyBalanceMongo) CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("companyBalanceMongoImpl");
				String startTime = CalendarUtil.getLastWeekMonday();
				String endTime = CalendarUtil.getLastWeekSunday();
				CompanyOrdersBalanceVO companyOrdersBalanceVO = null;
				CompanyBalance companyBalance = null;
				for(CompanyVO companyVO:companyVOs){
					companyBalance  = new CompanyBalance();
					companyOrdersBalanceVO = ordersMongoImpl.findOrdersByCompanyId(companyVO.getId(), startTime, endTime);
					companyBalance.setCompany_id(companyVO.getId());
					companyBalance.setCompany_name(companyVO.getCompany_name());
					companyBalance.setCommision(companyVO.getCommision());
					companyBalance.setPay_status(BalanceConst.HAS_PAY);
					companyBalance.setPay_time(DateUtil.getCurrentDate());
					if(null!=companyOrdersBalanceVO){
						companyBalance.setPay_time(null);
						companyBalance.setPay_status(BalanceConst.NOT_PAY);
						companyBalance.setOrders_count(companyOrdersBalanceVO.getOrders_count());
						companyBalance.setOrders_expense_sum(companyOrdersBalanceVO.getOrders_expense_sum());
						companyBalance.setOrders_score_sum(companyOrdersBalanceVO.getOrders_score_sum());
						companyBalance.setPay_money(Double.valueOf(df.format(companyVO.getCommision()*(companyOrdersBalanceVO.getOrders_expense_sum()-companyOrdersBalanceVO.getOrders_score_sum()/100))));
					}
					companyBalance.setCreate_time(new Date());
					companyBalances.add(companyBalance);
				}
				companyVOs.clear();
				companyVOs=null;
				companyBalanceMongoImpl.batchInsertCompanyBalance(companyBalances);
				companyBalances.clear();
				companyBalances = null;
			}
			TimerJobUtil.updateTimerJob(jobDataMap.getString("id"));
		} catch (Exception e) {
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
