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

import com.yule.admin.query.CompanyExpenseQuery;
import com.yule.admin.service.ICompanyExpenseCategoryService;
import com.yule.admin.service.ICompanyExpenseService;
import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.constant.PageConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.CompanyExpense;
import com.yule.pojo.CompanyExpenseCategory;
import com.yule.util.AdminLogUtil;
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
	
	@RequestMapping(value="/findCompanyExpense",method=RequestMethod.GET)
	public String findCompanyExpense(@RequestParam(value="companyId",required=false)String companyId,@RequestParam(value="expense_category_id",required=false)String expense_category_id,@RequestParam(value = "pageNo", required = false) Integer pageNo)throws Exception{
		StringBuffer select =new StringBuffer("");
		StringBuffer htmls = new StringBuffer("");
		CompanyExpenseQuery companyExpenseQuery =new CompanyExpenseQuery();
		companyExpenseQuery.setCompany_id(companyId);
		companyExpenseQuery.setExpense_category_id(expense_category_id);
		companyId=null;
		expense_category_id=null;
		if (pageNo==null||pageNo<1) {
			pageNo = 1;
		}
		try {
			List<CompanyExpenseCategory> companyExpenseCategories = this.companyExpenseCategoryServiceImpl.findCompanyExpenseList();	
				select = new StringBuffer();
				select.append("<select id=\"companyExpenseCategory\" name=\"expense_category_id\">");
				if (null!=companyExpenseCategories&&companyExpenseCategories.size()>1) {
					for (CompanyExpenseCategory companyExpenseCategory : companyExpenseCategories) {
						if(companyExpenseCategory.getId().equals(companyExpenseQuery.getExpense_category_id())){
							select.append("<option selected=\"selected\" value=\""+companyExpenseCategory.getId()+"\" >"+companyExpenseCategory.getName()+"</option>");
						}else{
							select.append("<option value=\""+companyExpenseCategory.getId()+"\" >"+companyExpenseCategory.getName()+"</option>");
						}
					}
					if (null==companyExpenseQuery.getExpense_category_id()) {
						companyExpenseQuery.setExpense_category_id(companyExpenseCategories.get(0).getId());
					}
					companyExpenseCategories.clear();
					companyExpenseCategories = null;
				}
				select.append("</select>");
			
			Page<CompanyExpense> page = this.companyExpenseServiceImpl.findCompanyExpensePage(companyExpenseQuery,  PageConst.PAGE_SIZE_TEN, pageNo);
			htmls.append("<tfoot><tr><td colspan=\"3\">");
			htmls.append("<div class=\"bulk-actions align-left\"></div>");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody>");
			AdminPrivilege adminPrivilege = null;
			StringBuffer privilegeHtml = new StringBuffer("");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){	
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE);
				privilegeHtml.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl()+"\" data-update=\"\" >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
				privilegeHtml.append("<a class=\"button\" href=\"javascript:;\" onclick=\"deleteExpense(this)\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+adminPrivilege.getUrl()+"\"  >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			StringBuffer insertPrivilegeHtml = new StringBuffer("");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT);
				insertPrivilegeHtml.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl()+"\" data-insert=\"\" data-param={\"company_id\":\""+companyExpenseQuery.getCompany_id()+"\"} >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
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
					htmls.append("<tr>");
					if(!StringUtils.isEmpty(companyExpense.getPrice())){
						htmls.append(rowHtml.toString().replace(PlaceholderConst.NAME, companyExpense.getName()).replace(PlaceholderConst.PRICE, String.valueOf(companyExpense.getPrice()))
								.replace(PlaceholderConst.ID, id).replace(PlaceholderConst.EXPENSE_CATEGORY_ID, companyExpense.getExpense_category_id()));
						htmls.append("<td>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id));
					}else{
						htmls.append(rowHtml.toString().replace(PlaceholderConst.NAME, companyExpense.getName()).replace(PlaceholderConst.PRICE, "").replace(PlaceholderConst.ID, "").replace(PlaceholderConst.EXPENSE_CATEGORY_ID,companyExpense.getExpense_category_id()));
						htmls.append("<td>"+insertPrivilegeHtml.toString()+"</td>");
					}
					htmls.append("</tr>");
					id.setLength(0);
				}
			}else{
				htmls.append("<tr>");
				htmls.append("<td class=\"td-center-style\" colspan=\"8\">暂无数据</td>");
				htmls.append("</tr>");
			}
			request.setAttribute("privilegeHtml", privilegeHtml.toString());
			request.setAttribute("insertPrivilegeHtml", insertPrivilegeHtml);
			htmls.append("</tbody>");
			request.setAttribute("select", select);
			request.setAttribute("companyExpenseQuery", companyExpenseQuery);
			request.setAttribute("htmls", htmls);
			privilegeHtml = null;
			insertPrivilegeHtml=null;
			select=null;
			companyExpenseQuery=null;
			htmls=null;
			AdminLogUtil.insertLog("查看企业消费", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查看企业消费[findCompanyExpense]错误",e);
			throw e;
		}
		return "/company/expense/index";
	}
	/**
	 * 新增企业消费
	 */
	@RequestMapping(value = "/insertCompanyExpense",method = RequestMethod.POST)
	public String insertCompanyExpense(@ModelAttribute("companyExpense")CompanyExpense companyExpense) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = this.companyExpenseServiceImpl.insertCompanyExpense(companyExpense);
			obj.put("id", companyExpense.getId());
			obj.put("status", flag);
			AdminLogUtil.insertLog("新增企业消费", getAdminUser(), LogEnum.INSERT);
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
			boolean flag = this.companyExpenseServiceImpl.updateCompanyExpense(companyExpense);
			obj.put("status", flag);
			AdminLogUtil.insertLog("更新企业消费", getAdminUser(), LogEnum.UPDATE);
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
			AdminLogUtil.insertLog("删除企业消费", getAdminUser(), LogEnum.DELETE);
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
