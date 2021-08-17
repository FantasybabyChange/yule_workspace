package com.yule.action.admin;


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
import com.yule.mongo.admin.query.AdminLogQuery;
import com.yule.mongo.admin.service.IAdminLogMongo;
import com.yule.mongo.pojo.AdminLog;
import com.yule.util.AdminLogUtil;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/adminLog")
public class AdminLogAction extends BaseAction {
	
	@Autowired
	private IAdminLogMongo adminLogMongoImpl;
	/**
	 * 日志查询
	 */
	@RequestMapping(value = "/findAdminLog",method = RequestMethod.GET)
	public String findAdminLog(AdminLogQuery adminLogQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception{
		if(null==pageNo||pageNo<1){
			pageNo = 1;
		}
		try {
			StringBuffer queryCheckboxHtml = new StringBuffer();
			List<Integer> status = adminLogQuery.getStatus();
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
			Page<AdminLog> page = this.adminLogMongoImpl.findAdminLogPage(adminLogQuery, PageConst.PAGE_SIZE_TEN, pageNo);
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody id=\"list\">");
			if(page.getRowCount()>0){
				List<AdminLog> datas = page.getDatas();
				for (AdminLog adminLog : datas) {
					htmls.append("<tr>");
					htmls.append("<td>"+adminLog.getName()+"</td>");
					htmls.append("<td>"+LogConst.TYPE[adminLog.getStatus()]+"</td>");
					htmls.append("<td>"+adminLog.getAdmin_user_name()+"</td>");
					htmls.append("<td>"+DateUtil.DateToString(adminLog.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+" </td>");
					htmls.append("</tr>");
				}
				datas.clear();
				datas = null;
				page = null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"5\">暂无数据</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			request.setAttribute("queryCheckboxHtml", queryCheckboxHtml);
			request.setAttribute("htmls", htmls);
			request.setAttribute("adminLogQuery", adminLogQuery);
			AdminLogUtil.insertLog("查询日志", getAdminUser(),LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查询日志[findAdminLog]发生异常",e);
			throw e;
		}
		return "admin/log/index";
	}
}
