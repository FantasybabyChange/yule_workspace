package com.yule.action.company;


import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.PageConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.company.service.ICompanyTaskMongo;
import com.yule.mongo.pojo.CompanyTask;
import com.yule.util.CompanyLogUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("companyTask")
public class CompanyTaskAction extends BaseAction{

	@Autowired
	private ICompanyTaskMongo companyTaskMongoImpl;
	
	@RequestMapping(value = "/findCompanyTask")
	public String findCompanyTask(@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception{
		StringBuffer htmls = new StringBuffer();
		if(null==pageNo){
			return "company/task/index";
		}
		JSONObject obj = new JSONObject();
		String companyId =getCompanyUser().getCompany_id();
		try {
			Page<CompanyTask> page = this.companyTaskMongoImpl.findCompanyTaskPage(companyId, PageConst.PAGE_SIZE_TEN, pageNo);
			StringBuffer tfoot = new StringBuffer();
			StringBuffer tbody = new StringBuffer();
			tfoot.append("<tr><td colspan=\"7\">");
			tfoot.append("<div class=\"bulk-actions align-left\"></div>");
			tfoot.append(PaginationUtil.getPaginationHtml(page));
			tfoot.append("<div class=\"clear\"></div>");
			tfoot.append("</td></tr>");
			if(page.getRowCount()>0){
				for(CompanyTask companyTask : page.getDatas()){
					tbody.append("<tr>");
					tbody.append("<td><a href=\""+companyTask.getUrl()+"\">"+companyTask.getName()+"</a></td>");
					tbody.append("</tr>");					
				}
			}else{
				tbody.append("<tr>");
				tbody.append("<td colspan=\"!\">没有数据</td>");
				tbody.append("</tr>");	
			}
			obj.put("tfoot", tfoot.toString());
			obj.put("tbody", tbody.toString());
			outputResult(obj.toString());
			tfoot.setLength(0);
			tbody.setLength(0);
			obj.clear();
			obj = null;
			CompanyLogUtil.insertLog("查询企业任务", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			request.setAttribute("htmls", htmls);
		}
		return null;
	}
}
