package com.yule.action.company;


import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.LogConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.company.query.CompanyLogQuery;
import com.yule.mongo.company.service.ICompanyLogMongo;
import com.yule.mongo.pojo.CompanyLog;
import com.yule.util.CompanyLogUtil;
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
	@RequestMapping(value = "/findCompanyLog")
	public String findCompanyLog(CompanyLogQuery companyLogQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception{
		if(null==pageNo){
			return "company/log/index";
		}
		companyLogQuery.setCompany_id(getCompanyUser().getCompany_id());
		JSONObject obj = new JSONObject();
		try {
			Page<CompanyLog> page = companyLogMongoImpl.findCompanyLogPage(companyLogQuery,PageConst.PAGE_SIZE_TEN, pageNo);
			StringBuffer tfoot = new StringBuffer();
			StringBuffer tbody = new StringBuffer();
			tfoot.append("<tr><td colspan=\"7\">");
			tfoot.append("<div class=\"bulk-actions align-left\"></div>");
			tfoot.append(PaginationUtil.getPaginationHtml(page));
			tfoot.append("<div class=\"clear\"></div>");
			tfoot.append("</td></tr>");
			if(page.getRowCount()>0){
				for(CompanyLog companyLog : page.getDatas()){
					tbody.append("<tr>");
					tbody.append("<td><a href=\"javascript:;\">"+companyLog.getName()+"</a></td>");
					tbody.append("<td>"+companyLog.getCompany_name()+"</td>");
					tbody.append("<td>"+LogConst.TYPE[companyLog.getStatus()]+"</td>");
					tbody.append("<td>"+DateUtil.DateToString(companyLog.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+"</td>");
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
			obj.clear();
			obj = null;
			CompanyLogUtil.insertLog("查询企业操作日志", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return null;
	}
}
