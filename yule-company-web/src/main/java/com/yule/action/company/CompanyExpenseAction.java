package com.yule.action.company;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.company.query.CompanyExpenseQuery;
import com.yule.company.service.ICompanyExpenseCategoryService;
import com.yule.company.service.ICompanyExpenseService;
import com.yule.constant.ErrorConst;
import com.yule.constant.PageConst;
import com.yule.constant.PlaceholderConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyExpense;
import com.yule.pojo.CompanyExpenseCategory;
import com.yule.util.CompanyLogUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/companyExpense")
public class CompanyExpenseAction extends BaseAction{

	@Autowired
	private ICompanyExpenseCategoryService companyExpenseCategoryServiceImpl;

	@Autowired
	private ICompanyExpenseService companyExpenseServiceImpl;
	
	@RequestMapping(value="/findCompanyExpense")
	public String findCompanyExpense(CompanyExpenseQuery companyExpenseQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo)throws Exception{
		companyExpenseQuery.setCompany_id(getCompanyUser().getCompany_id());
		StringBuffer privilegeHtml = new StringBuffer("");
		StringBuffer tfoot = new StringBuffer();
		StringBuffer tbody = new StringBuffer();
		StringBuffer select =new StringBuffer("");
		privilegeHtml.append("<button type=\"button\" class=\"btn btn-xs btn-success\" href=\"javascript:;\" data-url=\""+"/companyExpense/updateCompanyExpense.do"+"\" data-update=\"\" >"+"更新"+"</button>&nbsp;");
		privilegeHtml.append("<button type=\"button\" class=\"btn btn-xs btn-success\" href=\"javascript:;\" onclick=\"deleteExpense(this)\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+"/companyExpense/deleteCompanyExpense.do"+"\"  >"+"删除"+"</button>&nbsp;");
		StringBuffer insertPrivilegeHtml = new StringBuffer("");
		insertPrivilegeHtml.append("<button  type=\"button\" class=\"btn btn-xs btn-success\" href=\"javascript:;\" data-url=\""+"/companyExpense/insertCompanyExpense.do"+"\" data-insert=\"\" >"+"新增"+"</button>&nbsp;");
		
		JSONObject obj =new JSONObject();
		if (null==pageNo||pageNo<1) {
			List<CompanyExpenseCategory> companyExpenseCategories = this.companyExpenseCategoryServiceImpl.findCompanyExpenseList(null);	
			select = new StringBuffer();
			select.append("<select id=\"expense_category_id\" name=\"expense_category_id\">");
			if (null!=companyExpenseCategories&&companyExpenseCategories.size()>1) {
				for (CompanyExpenseCategory companyExpenseCategory : companyExpenseCategories) {
					select.append("<option value=\""+companyExpenseCategory.getId()+"\" >"+companyExpenseCategory.getName()+"</option>");
				}
				companyExpenseCategories.clear();
				companyExpenseCategories = null;
			}
			select.append("</select>");
			request.setAttribute("privilegeHtml", privilegeHtml.toString());
			request.setAttribute("insertPrivilegeHtml", insertPrivilegeHtml);
			request.setAttribute("select", select);
			return "/company/expense/index";
		}
		try {
		Page<CompanyExpense> page = this.companyExpenseServiceImpl.findCompanyExpensePage(companyExpenseQuery,  PageConst.PAGE_SIZE_TEN, pageNo);
		tfoot.append("<tr><td colspan=\"3\">");
		tfoot.append("<div class=\"bulk-actions align-left\"></div>");
		tfoot.append(PaginationUtil.getPaginationHtml(page));
		tfoot.append("<div class=\"clear\"></div>");
		tfoot.append("</td></tr>");
		if (page.getRowCount()>0){
			StringBuffer rowHtml = new StringBuffer("");
			rowHtml.append("<td>");
			rowHtml.append("<input name=\"id\" type=\"hidden\" value=\""+PlaceholderConst.ID+"\" />");
			rowHtml.append("<input id=\"expense_category_id\" name=\"expense_category_id\" type=\"hidden\" value=\""+PlaceholderConst.EXPENSE_CATEGORY_ID+"\" />");
			rowHtml.append("<div name=\"\">"+PlaceholderConst.NAME+"</div>");
			rowHtml.append("</td>");
			rowHtml.append("<td><input class=\"text-input\" type=\"text\"  name=\"price\" value=\"" + PlaceholderConst.PRICE+ "\"  nullmsg=\"请输入价格!\" datatype=\"n\" errormsg=\"请输入数字!\" /></td>");
			StringBuffer id = new StringBuffer("");
			for (CompanyExpense companyExpense :page.getDatas()) {
	        	id.append(companyExpense.getId());
	        	tbody.append("<tr>");
				if(!StringUtils.isEmpty(companyExpense.getPrice())){
					tbody.append(rowHtml.toString().replace(PlaceholderConst.NAME, companyExpense.getName()).replace(PlaceholderConst.PRICE, String.valueOf(companyExpense.getPrice()))
							.replace(PlaceholderConst.ID, id).replace(PlaceholderConst.EXPENSE_CATEGORY_ID, companyExpense.getExpense_category_id()));
					tbody.append("<td><div>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)+"</div></td>");
				}else{
					tbody.append(rowHtml.toString().replace(PlaceholderConst.NAME, companyExpense.getName()).replace(PlaceholderConst.PRICE, "").replace(PlaceholderConst.ID, "").replace(PlaceholderConst.EXPENSE_CATEGORY_ID,companyExpense.getExpense_category_id()));
					tbody.append("<td><div>"+insertPrivilegeHtml.toString()+"</div></td>");
				}
				tbody.append("</tr>");
				id.setLength(0);
			}
		}else{
			tbody.append("<tr>");
			tbody.append("<td class=\"td-center-style\" colspan=\"3\">暂无数据</td>");
			tbody.append("</tr>");
		}
		obj.put("tbody",tbody.toString());
		obj.put("tfoot", tfoot.toString());
		privilegeHtml = null;
		insertPrivilegeHtml=null;
		companyExpenseQuery=null;
		CompanyLogUtil.insertLog("查看企业消费", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查看企业消费[findCompanyExpense]错误",e);
			throw e;
		}finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
			
		}
		return null;
		
	}
	/**
	 * 新增企业消费
	 */
	@RequestMapping(value = "/insertCompanyExpense",method = RequestMethod.POST)
	public String insertCompanyExpense(@ModelAttribute("companyExpense")CompanyExpense companyExpense) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			companyExpense.setCompany_id(getCompanyUser().getCompany_id());
			boolean flag = this.companyExpenseServiceImpl.insertCompanyExpense(companyExpense);
			obj.put("id", companyExpense.getId());
			obj.put("status", flag);
			CompanyLogUtil.insertLog("新增企业消费", getCompanyUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("企业消费分类[insertCompanyExpense]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
		}
		return null;
	}
	
	/**
	 * 更新企业消费
	 */
	@RequestMapping(value = "/updateCompanyExpense",method = RequestMethod.POST)
	public String updateCompanyExpense(@ModelAttribute("companyExpense")CompanyExpense companyExpense) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			companyExpense.setCompany_id(getCompanyUser().getCompany_id());
			boolean flag = this.companyExpenseServiceImpl.updateCompanyExpense(companyExpense);
			obj.put("status", flag);
			CompanyLogUtil.insertLog("更新企业消费", getCompanyUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新企业消费[updateCompanyExpense]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
		}
		return null;
	}
	
	/**
	 * 删除企业消费
	 */
	@RequestMapping(value = "/deleteCompanyExpense",method = RequestMethod.POST)
	public String deleteCompanyExpense(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = this.companyExpenseServiceImpl.deleteCompanyExpense(id);
			obj.put("status", flag);
			CompanyLogUtil.insertLog("更新企业消费", getCompanyUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除企业消费[/deleteCompanyExpense]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
		}
		return null;
	}
}
