package com.yule.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.detail.service.ICompanyExpenseService;
import com.yule.detail.vo.CompanyExpenseVO;
import com.yule.exception.YuleException;
@Controller
@Scope("prototype")
@RequestMapping("/companyExpense")
public class CompanyExpenseAction extends BaseAction{
	
	@Autowired
	private ICompanyExpenseService  companyExpenseServiceImpl;
	
	@RequestMapping(value="/findcompanyExpense",method=RequestMethod.POST)
	public String findcompanyExpense(@RequestParam(value="company_id",required=false)String company_id)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			List<CompanyExpenseVO> list = this.companyExpenseServiceImpl.findCompanyExpenseVoList(company_id);
			StringBuffer companyExpenseHtml = new StringBuffer();
			if (list != null && list.size() > 0) {
		    	for (CompanyExpenseVO first : list) {
		    		if (null == first.getParent_id()) {
		    			companyExpenseHtml.append("<div class=\"expense-content\">");
		    			companyExpenseHtml.append("<div class=\"expense-title\">"+first.getName()+"</div>");
		    			companyExpenseHtml.append("<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"expense-table\">");
		    			companyExpenseHtml.append("<thead><tr><th class=\"expense-table-name\">名称</th><th>单价</th></tr></thead><tbody>");
						for (CompanyExpenseVO second : list) {
							if (second.getParent_id() != null && second.getParent_id().equals(first.getId())) {
								companyExpenseHtml.append("<tr>");
								companyExpenseHtml.append("<td>"+second.getName()+"</td>");
								companyExpenseHtml.append("<td>"+second.getPrice()+"元</td>");
								companyExpenseHtml.append("</tr>");
							}
						}
						companyExpenseHtml.append("</tbody></table></div>");
					}
				}
			}else{
				companyExpenseHtml.append("<div class=\"description clearfix\" >");
				companyExpenseHtml.append("暂无消费价格");
				companyExpenseHtml.append("</div>");
			}
			object.put("companyExpenseHtml", companyExpenseHtml.toString());
			companyExpenseHtml.setLength(0);
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询消费价格【findcompanyExpense】出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
}
