package com.yule.action.company;

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
import com.yule.mongo.admin.query.CompanyBalanceQuery;
import com.yule.mongo.admin.service.ICompanyBalanceMongo;
import com.yule.mongo.admin.vo.CompanyBalanceVO;
import com.yule.mongo.pojo.CompanyBalance;
import com.yule.pojo.AdminPrivilege;
import com.yule.util.DateUtil;
import com.yule.util.DecimalUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@RequestMapping("/companyBalance")
public class CompanyBalanceAction extends BaseAction{


	@Autowired
	private ICompanyBalanceMongo companyBalanceMongoImpl;

	/**
	 * 查询企业结算
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCompanyBalances",method = RequestMethod.GET)
	public String findCompanyBalances(CompanyBalanceQuery companyBalanceQuery,@RequestParam(value="pageNo",required=false)Integer pageNo) throws Exception {
		if(pageNo==null||pageNo<=0){
			pageNo = 1;
		}
		try {
			
			Page<CompanyBalanceVO> page = companyBalanceMongoImpl.findHistryCompanyBalancePage(companyBalanceQuery, PageConst.PAGE_SIZE_TEN, pageNo);
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
			List<CompanyBalanceVO> lists = page.getDatas();
			if(null!=lists&&lists.size()>0){
				AdminPrivilege adminPrivilege = null;
				for(CompanyBalanceVO companyBalanceVO:lists){
					htmls.append("<tr>");
					htmls.append("<td>"+DateUtil.DateToString(companyBalanceVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN)+"</td>");
/*					htmls.append("<td>"+companyBalanceVO.getId()+"</td>");
					htmls.append("<td>"+companyBalanceVO.getCompany_id()+"</td>");*/
					htmls.append("<td>"+companyBalanceVO.getCompany_name()+"</td>");
					htmls.append("<td>"+DecimalUtil.parseMoney(companyBalanceVO.getOrders_count(),DecimalEnum.THOUSAND)+"</td>");
					htmls.append("<td>"+DecimalUtil.parseMoney(companyBalanceVO.getOrders_expense_sum(),DecimalEnum.THOUSAND)+"</td>");
					htmls.append("<td>"+DecimalUtil.parseMoney(companyBalanceVO.getOrders_score_sum(),DecimalEnum.THOUSAND)+"</td>");
					htmls.append("<td>"+DecimalUtil.parseMoney(companyBalanceVO.getPay_money(),DecimalEnum.THOUSAND)+"</td>");
					/*htmls.append("<td>"+CompanyBalanceConst.PAY_TYPE[companyBalanceVO.getPay_status()]+"</td>");*/
					if(null!=companyBalanceVO.getPay_time()){
						htmls.append("<td class=\"pay_time\">"+DateUtil.DateToString(companyBalanceVO.getPay_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN)+"</td>");
					}else{
						htmls.append("<td class=\"pay_time\"></td>");
					}
					if(companyBalanceVO.getPay_status()==BalanceConst.HAS_PAY){
						htmls.append("<td>"+BalanceConst.PAY_TYPE[companyBalanceVO.getPay_status()]+"</td>");
					}else{
						if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_COMPANY_PAY_MONEY)){	
							adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_COMPANY_PAY_MONEY);
							htmls.append("<td><a class=\"button\" data-url=\""+adminPrivilege.getUrl()+"\" pay=\"\" data-id=\""+companyBalanceVO.getId()+ "\">"+adminPrivilege.getName()+"</a>&nbsp;</td>");
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
			request.setAttribute("companyBalanceQuery", companyBalanceQuery);
			request.setAttribute("companyBalanceHtmls", htmls);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/company/balance/index";
	}

	@RequestMapping(value = "/updateCompanyBalance",method = RequestMethod.POST)
	public String updateCompanyBalance(@ModelAttribute("companyBalance")CompanyBalance companyBalance) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			companyBalance.setPay_status(BalanceConst.HAS_PAY);
			companyBalance.setPay_time(DateUtil.getCurrentDate());
			boolean flag = this.companyBalanceMongoImpl.updateCompanyBalance(companyBalance);
			obj.put("status", flag);
			obj.put("pay_time",DateUtil.DateToString(companyBalance.getPay_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN));
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
}
