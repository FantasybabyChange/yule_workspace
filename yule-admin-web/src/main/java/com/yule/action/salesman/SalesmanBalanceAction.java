package com.yule.action.salesman;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.BalanceConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PageConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.DecimalEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.query.SalesmanBalanceQuery;
import com.yule.mongo.admin.service.ISalesmanBalanceMongo;
import com.yule.mongo.admin.vo.SalesmanBalanceVO;
import com.yule.mongo.pojo.SalesmanBalance;
import com.yule.pojo.AdminPrivilege;
import com.yule.util.DateUtil;
import com.yule.util.DecimalUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@RequestMapping("/salesmanBalance")
public class SalesmanBalanceAction extends BaseAction{


	@Autowired
	private ISalesmanBalanceMongo salesmanBalanceMongoImpl;

	/**
	 * 查询企业结算
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findSalesmanBalances",method = RequestMethod.GET)
	public String findSalesmanBalances(SalesmanBalanceQuery salesmanBalanceQuery,@RequestParam(value="pageNo",required=false)Integer pageNo) throws Exception {
		if(pageNo==null||pageNo<=0){
			pageNo = 1;
		}
		try {
			
			Page<SalesmanBalanceVO> page = salesmanBalanceMongoImpl.findSalesmanBalancePageByQuery(salesmanBalanceQuery, PageConst.PAGE_SIZE_TEN, pageNo);
			StringBuffer htmls = new StringBuffer("");
			htmls.append("<tfoot>");
			htmls.append("<tr>");
			htmls.append("<td colspan=\"8\">");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td>");
			htmls.append("</tr>");
			htmls.append("</tfoot>");		
			htmls.append("<tbody>");
			List<SalesmanBalanceVO> lists = page.getDatas();
			if(null!=lists&&lists.size()>0){
					AdminPrivilege adminPrivilege = null;
				for(SalesmanBalanceVO salesmanBalanceVO:lists){
					htmls.append("<tr>");
					htmls.append("<td>"+DateUtil.DateToString(salesmanBalanceVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN)+"</td>");
					htmls.append("<td>"+salesmanBalanceVO.getSalesman_name()+"</td>");
					htmls.append("<td>"+DecimalUtil.parseMoney(salesmanBalanceVO.getOrders_count(),DecimalEnum.THOUSAND)+"</td>");
					htmls.append("<td>"+DecimalUtil.parseMoney(salesmanBalanceVO.getOrders_expense_sum(),DecimalEnum.THOUSAND)+"</td>");
					htmls.append("<td>"+DecimalUtil.parseMoney(salesmanBalanceVO.getPay_money(),DecimalEnum.THOUSAND)+"</td>");
					if(null!=salesmanBalanceVO.getPay_time()){
						htmls.append("<td class=\"pay_time\">"+DateUtil.DateToString(salesmanBalanceVO.getPay_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN)+"</td>");
					}else{
						htmls.append("<td class=\"pay_time\"></td>");
					}
					if(salesmanBalanceVO.getPay_status()==BalanceConst.HAS_PAY){
						htmls.append("<td>"+BalanceConst.PAY_TYPE[salesmanBalanceVO.getPay_status()]+"</td>");
					}else{
						/*htmls.append("<td><a class=\"button\" data-url=\"/salesmanBalance/updateSalesmanBalance.do\" pay=\"\" data-id=\""+salesmanBalanceVO.getId()+ "\">支付</a>&nbsp;</td>");*/
						if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_SALESMAN_PAY_MONEY)){	
							adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_SALESMAN_PAY_MONEY);
							htmls.append("<td><a class=\"button\" data-url=\""+adminPrivilege.getUrl()+"\" pay=\"\" data-id=\""+salesmanBalanceVO.getId()+ "\">"+adminPrivilege.getName()+"</a>&nbsp;</td>");
							adminPrivilege = null;
						}
					}
					htmls.append("</tr>");
				}
				page.getDatas().clear();
			}else{
				htmls.append("<tr>");
				htmls.append("<td class=\"td-center-style\" colspan=\"11\">暂无数据</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			request.setAttribute("salesmanBalanceQuery", salesmanBalanceQuery);
			request.setAttribute("salesmanBalanceHtmls", htmls);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/salesman/balance/index";
	}

	@RequestMapping(value = "/updateSalesmanBalance",method = RequestMethod.POST)
	public String updateSalesmanBalance(@ModelAttribute("salesmanBalance")SalesmanBalance salesmanBalance) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			salesmanBalance.setPay_status(BalanceConst.HAS_PAY);
			salesmanBalance.setPay_time(DateUtil.getCurrentDate());
			boolean flag = this.salesmanBalanceMongoImpl.updateSalesmanBalance(salesmanBalance);
			obj.put("status", flag);
			obj.put("pay_time",DateUtil.DateToString(salesmanBalance.getPay_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN));
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
}
