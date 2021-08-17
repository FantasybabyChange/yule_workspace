package com.yule.action.balance;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.company.vo.CompanyUserVO;
import com.yule.constant.CompanyBalanceConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.DecimalEnum;
import com.yule.mongo.company.query.BalanceQuery;
import com.yule.mongo.company.service.ICompanyBalanceMongo;
import com.yule.mongo.company.vo.CompanyBalanceVO;
import com.yule.mongo.timer.vo.CompanyOrdersBalanceVO;
import com.yule.util.CalendarUtil;
import com.yule.util.DateUtil;
import com.yule.util.DecimalUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/balance")
public class BalanceAction extends BaseAction{

	
	@Autowired
	private ICompanyBalanceMongo companyBalanceMongoImpl;
	
	@RequestMapping("/findLastMonthBalance")
	public String findLastMonthBalance() throws Exception{
		CompanyUserVO companyUserVO = getCompanyUser();
		String companyId = companyUserVO.getCompany_id();
		int yule_commision = companyUserVO.getCommision();
		companyUserVO  = null;
		String start_time =CalendarUtil.getNowWeekMonday();
		String end_time = DateUtil.DateToString(DateUtil.getCurrentDate(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN);
		CompanyOrdersBalanceVO companyOrdersBalanceVO=companyBalanceMongoImpl.findNowWeekCompanyBalanceByQuery(companyId,start_time,end_time);
		if (null!=companyOrdersBalanceVO&&!StringUtils.isEmpty(companyOrdersBalanceVO.getOrders_count())&&Integer.valueOf(companyOrdersBalanceVO.getOrders_count())>0) {
			request.setAttribute("balance", "贵公司在本周截止当前时间("+start_time+"--"+end_time+")通过预乐网完成订单总数为"+DecimalUtil.parseMoney(String.valueOf(companyOrdersBalanceVO.getOrders_count()),DecimalEnum.THOUSAND)+
					"笔,总收入为"+DecimalUtil.parseMoney(String.valueOf(companyOrdersBalanceVO.getOrders_expense_sum()),DecimalEnum.THOUSAND)+"元，用户使用积分为"+DecimalUtil.parseMoney(String.valueOf(companyOrdersBalanceVO.getOrders_score_sum()),DecimalEnum.THOUSAND)+"分,根据合同规定预乐网提成为"+yule_commision+"%,故贵公司须支付"+DecimalUtil.parseMoney(String.valueOf((companyOrdersBalanceVO.getOrders_expense_sum()-companyOrdersBalanceVO.getOrders_score_sum()/100)*yule_commision/100),DecimalEnum.THOUSAND)+"元。");
		}else{
			request.setAttribute("balance", "贵公司在本周截止当前时间("+start_time+"--"+end_time+")通过预乐网完成订单总数为0笔");
		}
		return "/balance/index";
	}
	
	@RequestMapping("/findHistoryBalance")
	public String findHistoryBalance(BalanceQuery balanceQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo)throws Exception{
		CompanyUserVO companyUserVO = getCompanyUser();
		balanceQuery.setCompany_id(companyUserVO.getCompany_id());
		JSONObject obj = new JSONObject();
		StringBuffer tfoot = new StringBuffer();
		StringBuffer tbody = new StringBuffer();
		try {
			Page<CompanyBalanceVO> page = this.companyBalanceMongoImpl.findHistryCompanyBalancePage(balanceQuery, PageConst.PAGE_SIZE_TEN, pageNo);
			tfoot.append("<tr><td colspan=\"8\">");
			tfoot.append("<div class=\"bulk-actions align-left\"></div>");
			tfoot.append(PaginationUtil.getPaginationHtml(page));
			tfoot.append("<div class=\"clear\"></div>");
			tfoot.append("</td></tr>");
			if(page.getRowCount()>0){
				for(CompanyBalanceVO companyBalance : page.getDatas()){
					tbody.append("<tr>");
					tbody.append("<td>"+DateUtil.DateToString(companyBalance.getCreate_time(), DateStyle.YYYY_MM_DD_CN)+"</td>");
					tbody.append("<td>"+DecimalUtil.parseMoney(companyBalance.getOrders_count(),DecimalEnum.THOUSAND)+"</td>");
					tbody.append("<td>"+DecimalUtil.parseMoney(companyBalance.getOrders_expense_sum(),DecimalEnum.THOUSAND)+"</td>");
					tbody.append("<td>"+DecimalUtil.parseMoney(companyBalance.getOrders_score_sum(),DecimalEnum.THOUSAND)+"</td>");
					tbody.append("<td>"+companyBalance.getCommision()*100+"</td>");
					tbody.append("<td>" +DecimalUtil.parseMoney(companyBalance.getPay_money(),DecimalEnum.THOUSAND)+"</td>");
					tbody.append("<td>" +CompanyBalanceConst.PAY_type[companyBalance.getPay_status()]+"</td>");
					if (null!=companyBalance.getPay_time()) {
						tbody.append("<td>" +DateUtil.DateToString(companyBalance.getPay_time(), DateStyle.MM_DD_HH_MM_SS_CN)+"</td>");
					}else{
						tbody.append("<td></td>");
					}
					tbody.append("</tr>");					
				}
				page.getDatas().clear();
				page = null;
				companyUserVO = null;
			}else{
				tbody.append("<tr>");
				tbody.append("<td colspan=\"8\">没有数据</td>");
				tbody.append("</tr>");	
			}
			obj.put("tfoot", tfoot.toString());
			obj.put("tbody", tbody.toString());
			} 
		catch (Exception e) {
			throw e;
		}finally{
			outputResult(obj.toString());
			obj.clear();
			obj = null;
			tfoot.setLength(0);
			tbody.setLength(0);
		
		}
		return null;
	}
}
