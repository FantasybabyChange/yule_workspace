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
import com.yule.mongo.pojo.SalesmanBalance;
import com.yule.mongo.timer.service.IOrdersMongo;
import com.yule.mongo.timer.service.ISalesmanBalanceMongo;
import com.yule.mongo.timer.vo.CompanyOrdersBalanceVO;
import com.yule.timer.service.ICompanyService;
import com.yule.timer.service.ISalesmanService;
import com.yule.timer.vo.CompanyVO;
import com.yule.timer.vo.SalesmanVO;
import com.yule.util.CalendarUtil;
import com.yule.util.CustomBeanFactory;
import com.yule.util.DateUtil;
import com.yule.util.TimerJobUtil;

public class SalesmanQuartz extends StatefulMethodInvokingJob{

	@Override
	protected void executeInternal(JobExecutionContext context)throws JobExecutionException {
		ISalesmanService salesmanServiceImpl = (ISalesmanService)CustomBeanFactory.getContext(CustomBeanConst.TIMER_SERVICE_PATHS).getBean("salesmanServiceImpl");
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		try {
			List<SalesmanBalance> salesmanBalances = new ArrayList<SalesmanBalance>();
			List<SalesmanVO> salesmanVOs = salesmanServiceImpl.findSalesmanVOList();
			if(null!=salesmanVOs&&salesmanVOs.size()>0){
				ICompanyService companyServiceImpl = (ICompanyService)CustomBeanFactory.getContext(CustomBeanConst.TIMER_SERVICE_PATHS).getBean("companyServiceImpl");
				IOrdersMongo ordersMongoImpl = (IOrdersMongo) CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("ordersMongoImpl");
				ISalesmanBalanceMongo salesmanBalanceMongoImpl = (ISalesmanBalanceMongo) CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("salesmanBalanceMongoImpl");
				List<CompanyVO> companyVOs = null;
				CompanyOrdersBalanceVO companyOrdersBalanceVOs = null;
				SalesmanBalance salesmanBalance = null;
				int orderCount = 0;
				int expenseCount = 0;
				String month = CalendarUtil.lastMonth();
				DecimalFormat df =new DecimalFormat("#.00");
				for(SalesmanVO salesmanVO : salesmanVOs){
					salesmanBalance = new SalesmanBalance();
					companyVOs = companyServiceImpl.findCompanyVOListBySalesmanId(salesmanVO.getId());
					if(null!=companyVOs&&companyVOs.size()>0){
						for(CompanyVO companyVO : companyVOs){
							companyOrdersBalanceVOs = ordersMongoImpl.findLastMonthOrdersByCompanyId(companyVO.getId(), month);
							if(null!=companyOrdersBalanceVOs){
								orderCount += companyOrdersBalanceVOs.getOrders_count();
								expenseCount += companyOrdersBalanceVOs.getOrders_expense_sum();
							}
						}
						companyVOs.clear();
					}
					salesmanBalance.setOrders_expense_sum(expenseCount);
					salesmanBalance.setOrders_count(orderCount);
					if(salesmanBalance.getOrders_count()>0){
						salesmanBalance.setPay_status(BalanceConst.NOT_PAY);
					}else{
						salesmanBalance.setPay_status(BalanceConst.HAS_PAY);
						salesmanBalance.setPay_time(DateUtil.getCurrentDate());
					}
					salesmanBalance.setPay_money(Double.valueOf(df.format(salesmanVO.getCommision()*expenseCount)));
					salesmanBalance.setSalesman_id(salesmanVO.getId());
					salesmanBalance.setSalesman_name(salesmanVO.getName());
					salesmanBalance.setSalesman_commision(salesmanVO.getCommision());
					salesmanBalance.setCreate_time(new Date());
					salesmanBalances.add(salesmanBalance);
					expenseCount = 0;
					orderCount = 0;
				}
				salesmanVOs.clear();
				salesmanBalanceMongoImpl.batchInsertSalesmanBalance(salesmanBalances);
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
