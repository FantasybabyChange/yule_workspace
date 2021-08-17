package com.yule.action.company;


import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.service.ICompanyExpenseCategoryService;
import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.constant.PageConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.CompanyExpenseCategory;
import com.yule.util.AdminLogUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/companyExpenseCategory")
public class CompanyExpenseCategoryAction extends BaseAction{
	
	@Autowired
	private ICompanyExpenseCategoryService companyExpenseCategoryServiceImpl;
	
	@RequestMapping(value = "/findCompanyExpenseCategory",method = RequestMethod.GET)
	public String findCompanyExpenseCategory(@RequestParam(value="parentId",required = false) String parentId, @RequestParam(value = "pageNo", required = false) Integer pageNo)throws Exception{
		try {
			if(null==pageNo||pageNo<1){
				pageNo = 1;
			}
			if(StringUtils.isEmpty(parentId)){
				parentId = null;
			}
			Page<CompanyExpenseCategory> page = companyExpenseCategoryServiceImpl.findCompanyExpenseCategoryByParentId(parentId,PageConst.PAGE_SIZE_TEN, pageNo);
			AdminPrivilege adminPrivilege = null;
			StringBuffer operatorHtml = new StringBuffer("");
			StringBuffer gobackHtml = new StringBuffer("");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				operatorHtml.append("<a class=\"button\" href=\"javascript:;\" data-add-row=\"\" />新增一行</a>&nbsp;");
			}
			StringBuffer privilegeHtml = new StringBuffer("");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE);
				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-update=\"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			StringBuffer rowHtml = new StringBuffer("");
			if (null==parentId) {
				privilegeHtml.append("<a  class=\"button\"  href=\""+request.getServletPath()+"?parentId="+PlaceholderConst.ID+"\" >"+"编辑子类"+"</a>&nbsp;");
			}else {
				rowHtml.append("<input type=\"hidden\"name=\"parent_id\" value=\""+parentId+"\"/>&nbsp;&nbsp;");
				gobackHtml.append("<a class=\"button\" style=\"float: right;margin: 10px 10px 0 0\" href=\"/companyExpenseCategory/findCompanyExpenseCategory.do\">返回上级分类</a>");
				request.setAttribute("parentId", parentId);
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-delete=\"\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>");
				adminPrivilege = null;
			}
			rowHtml.append("<td><input type=\"hidden\" name=\"id\" value=\""+PlaceholderConst.ID+"\"  />");
			rowHtml.append("<input class=\"text-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入名称!\" datatype=\"\" errormsg=\"\" repeatmsg=\"名称重复!\" value=\""+PlaceholderConst.NAME+"\"/></td>");
			StringBuffer htmls = new StringBuffer("");
			htmls.append("<tfoot><tr><td colspan=\"2\">");
			htmls.append("<div class=\"bulk-actions align-left\"></div>");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody id=\"list\">");
			if(page.getRowCount()>0){
				StringBuffer id = new StringBuffer("");
				for(CompanyExpenseCategory companyExpenseCategory :page.getDatas()){
					id.append(companyExpenseCategory.getId());
					htmls.append("<tr>");
					
					htmls.append(rowHtml.toString()
							.replace(PlaceholderConst.ID, id)
							.replace(PlaceholderConst.NAME, companyExpenseCategory.getName()));
					htmls.append("<td>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)+"</td>");
					htmls.append("</tr>");
					id.setLength(0);
				}
				id = null;
				page.cleanDatas();
				page = null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"2\">暂无数据(点击新增一行添加数据)</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			rowHtml.append("<td>");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT);
				rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-insert=\"\" data-url=\""+adminPrivilege.getUrl()+"\">"+adminPrivilege.getName()+"</a>&nbsp;");
				rowHtml.append("<a data-del-row=\"\" href=\"javascript:;\" class=\"button\">删除</a>&nbsp;");
				adminPrivilege = null;
			}
			rowHtml.append("</td>");
			request.setAttribute("gobackHtml", gobackHtml);
			request.setAttribute("rowHtml", rowHtml.toString().replace(PlaceholderConst.ID, "").replace(PlaceholderConst.NAME, ""));
			request.setAttribute("privilegeHtml", privilegeHtml);
			request.setAttribute("operatorHtml", operatorHtml);
			request.setAttribute("htmls", htmls);
			AdminLogUtil.insertLog("查看企业消费分类", getAdminUser(), LogEnum.QUERY);
			gobackHtml = null;
			rowHtml = null;
			privilegeHtml= null;
			operatorHtml = null;
			htmls = null;
		} catch (Exception e) {
			new YuleException("查看企业消费分类[findCompanyExpenseCategory]",e);
			throw e;
		} 
		return "/company/expenseCategory/index";
	}
	
	
	/**
	 * 新增企业消费分类
	 */
	@RequestMapping(value = "/insertCompanyExpenseCategory",method = RequestMethod.POST)
	public String insertCompanyExpenseCategory(@ModelAttribute("companyExpenseCategory")CompanyExpenseCategory companyExpenseCategory) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = this.companyExpenseCategoryServiceImpl.insertCompanyExpenseCategory(companyExpenseCategory);
			obj.put("id", companyExpenseCategory.getId());
			obj.put("status", flag);
			AdminLogUtil.insertLog("企业消费分类", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("企业消费分类[insertCompanyExpenseCategory]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
		}
		return null;
	}
	
	/**
	 * 更新企业消费分类
	 */
	@RequestMapping(value = "/updateCompanyExpenseCategory",method = RequestMethod.POST)
	public String updateCompanyExpenseCategory(@ModelAttribute("companyExpenseCategory")CompanyExpenseCategory companyExpenseCategory) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = this.companyExpenseCategoryServiceImpl.updateCompanyExpenseCategory(companyExpenseCategory);
			obj.put("status", flag);
			AdminLogUtil.insertLog("更新企业消费分类", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新企业消费分类[updateCompanyExpenseCategory]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
		}
		return null;
	}
}
