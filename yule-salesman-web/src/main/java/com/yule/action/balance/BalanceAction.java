package com.yule.action.balance;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.PageConst;
import com.yule.constant.SalesmanBalanceConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.DecimalEnum;
import com.yule.mongo.salesman.query.SalesmanBalanceQuery;
import com.yule.mongo.salesman.service.ISalesmanBalanceMongo;
import com.yule.mongo.salesman.vo.SalesmanBalanceVO;
import com.yule.pojo.Company;
import com.yule.salesman.service.ICompanyService;
import com.yule.salesman.vo.SalesmanVO;
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
	private ISalesmanBalanceMongo salesmanBalanceMongoImpl;
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	@RequestMapping("/findLastMonthBalance")
	public String findLastMonthBalance() throws Exception{
		SalesmanVO salesmanVO=getSalesman();
		SalesmanBalanceQuery salesmanBalanceQuery = new SalesmanBalanceQuery();
		salesmanBalanceQuery.setStart_time(CalendarUtil.fisrtDay());
		salesmanBalanceQuery.setEnd_time(DateUtil.DateToString(DateUtil.getCurrentDate(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		
		List<Company> companies = this.companyServiceImpl.findCompanyList(getCookieValue());
		List<String> company_ids = new ArrayList<String>();
		if (null!=companies&&companies.size()>0) {
			for (Company company:companies) {
				company_ids.add(company.getId());
			}
			salesmanBalanceQuery.setCompany_ids(company_ids);
		}
		if (company_ids.size()>0) {
			SalesmanBalanceVO salesmanBalance = salesmanBalanceMongoImpl.findNowMonthSalesmanBalanceVOByQuery(salesmanBalanceQuery);
			if (null!=salesmanBalance&&Integer.valueOf(salesmanBalance.getOrders_count())>0) {
				request.setAttribute("salesmanBalanceHtml", "您在本月截止当前时间("+salesmanBalanceQuery.getStart_time()+"--"+salesmanBalanceQuery.getEnd_time()+")合作的娱乐场所的订单数为"+DecimalUtil.parseMoney(salesmanBalance.getOrders_count(),DecimalEnum.THOUSAND)+"笔,娱乐场所 收入为"+DecimalUtil.parseMoney(salesmanBalance.getOrders_expense_sum(),DecimalEnum.THOUSAND)+"元,您当前提成为"+salesmanVO.getCommision()+"%,故您的收益为"+DecimalUtil.parseMoney(String.valueOf(Double.valueOf(salesmanBalance.getOrders_expense_sum())*salesmanVO.getCommision()/100),DecimalEnum.THOUSAND)+"元");
			}else{
				request.setAttribute("salesmanBalanceHtml", "您在本月截止当前时间("+salesmanBalanceQuery.getStart_time()+"--"+salesmanBalanceQuery.getEnd_time()+")合作的娱乐场所没有订单");
			}
		}else{
			request.setAttribute("salesmanBalanceHtml", "您暂时没有合作的娱乐场所");
		}

	    return "/balance/index";
	}
	
	@RequestMapping("/findSalesmanBalance")
	public String findSalesmanBalance(SalesmanBalanceQuery salesmanBalanceQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception{
		if (null==pageNo||pageNo<1) {
			pageNo=1;
		}
		salesmanBalanceQuery.setSalesman_id(getCookieValue());
		JSONObject obj = new JSONObject();
		try {
			Page<SalesmanBalanceVO> page = salesmanBalanceMongoImpl.findSalesmanBalancePageByQuery(salesmanBalanceQuery,PageConst.PAGE_SIZE_TEN, pageNo);
			StringBuffer tfoot = new StringBuffer();
			StringBuffer tbody = new StringBuffer();
			tfoot.append("<tr><td colspan=\"7\">");
			tfoot.append("<div class=\"bulk-actions align-left\"></div>");
			tfoot.append(PaginationUtil.getPaginationHtml(page));
			tfoot.append("<div class=\"clear\"></div>");
			tfoot.append("</td></tr>");
			if(page.getRowCount()>0){
				for(SalesmanBalanceVO salesmanBalance : page.getDatas()){
					tbody.append("<tr>");
					tbody.append("<td>"+DateUtil.DateToString(salesmanBalance.getCreate_time(), DateStyle.YYYY_MM_CN)+"</td>");
					tbody.append("<td>"+DecimalUtil.parseMoney(salesmanBalance.getOrders_count(),DecimalEnum.THOUSAND)+"</td>");
					tbody.append("<td>"+DecimalUtil.parseMoney(salesmanBalance.getOrders_expense_sum(),DecimalEnum.THOUSAND)+"</td>");
					tbody.append("<td>"+salesmanBalance.getSalesman_commision()*100+"</td>");
					tbody.append("<td>"+DecimalUtil.parseMoney(salesmanBalance.getPay_money(),DecimalEnum.THOUSAND)+"</td>");
					tbody.append("<td>"+SalesmanBalanceConst.PAY_type[salesmanBalance.getPay_status()]+"</td>");
					if(null!=salesmanBalance.getPay_time()){
						tbody.append("<td>"+DateUtil.DateToString(salesmanBalance.getPay_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN)+"</td>");
					}else{
						tbody.append("<td></td>");
					}
					tbody.append("</tr>");					
				}
			}else{
				tbody.append("<tr>");
				tbody.append("<td colspan=\"7\">没有数据</td>");
				tbody.append("</tr>");	
			}
			obj.put("tfoot", tfoot.toString());
			obj.put("tbody", tbody.toString());
			outputResult(obj.toString());
			tfoot.setLength(0);
			tbody.setLength(0);
		} catch (Exception e) {
			throw e;
		}finally{
			outputResult(obj.toString());
			obj.clear();
			obj = null;
		}
	    return null;
	}
	
}
