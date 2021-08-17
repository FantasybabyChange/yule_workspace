package com.yule.action.company;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.LogConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.query.CompanyLogQuery;
import com.yule.mongo.admin.service.ICompanyLogMongo;
import com.yule.mongo.pojo.CompanyLog;
import com.yule.util.AdminLogUtil;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/companyLog")
public class CompanyLogAction extends BaseAction {
	
	@Autowired
	private ICompanyLogMongo companyLogMongoImpl;
	/**
	 * 日志查询
	 */
	@RequestMapping(value = "/findCompanyLog",method = RequestMethod.GET)
	public String findCompanyLog(CompanyLogQuery companyLogQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception{
		if(null==pageNo||pageNo<1){
			pageNo = 1;
		}
		try {
			StringBuffer queryCheckboxHtml = new StringBuffer();
			List<Integer> status = companyLogQuery.getStatus();
			int i = 0;
			for (String type : LogConst.TYPE) {
				if(null!=status&&status.contains(i)){
					queryCheckboxHtml.append("<input type=\"checkbox\" name=\"status\" value="+i+" checked >"+type+"&nbsp;");
				}else{
					queryCheckboxHtml.append("<input type=\"checkbox\" name=\"status\" value="+i+">"+type+"&nbsp;");
				}
				i++;
			}
			StringBuffer htmls = new StringBuffer();
			htmls.append("<tfoot><tr><td colspan=\"7\">");
			htmls.append("<div class=\"bulPage<T>tions align-left\"></div>");
			Page<CompanyLog> companyLogs = this.companyLogMongoImpl.findCompanyLogPage(companyLogQuery, PageConst.PAGE_SIZE_TEN, pageNo);
			htmls.append(PaginationUtil.getPaginationHtml(companyLogs));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody id=\"list\">");
			if(companyLogs.getRowCount()>0){
				List<CompanyLog> datas = companyLogs.getDatas();
				for (CompanyLog companyLog : datas) {
					htmls.append("<tr>");
					htmls.append("<td>"+companyLog.getName()+"</td>");
					htmls.append("<td>"+LogConst.TYPE[companyLog.getStatus()]+"</td>");
					htmls.append("<td>"+companyLog.getCompany_name()+"</td>");
					htmls.append("<td>"+DateUtil.DateToString(companyLog.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+" </td>");
					htmls.append("</tr>");
				}
				datas.clear();
				datas = null;
				companyLogs = null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"5\">暂无数据</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			request.setAttribute("queryCheckboxHtml", queryCheckboxHtml);
			request.setAttribute("companyLogQuery", companyLogQuery);
			request.setAttribute("htmls", htmls);
			AdminLogUtil.insertLog("查询日志", getAdminUser(),LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查询日志[findCompanyLog]发生异常",e);
			throw e;
		}
		return "company/log/index";
	}
}
