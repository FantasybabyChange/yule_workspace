package com.yule.action.company;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.service.ICompanyExpensePriceService;
import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.constant.PageConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.CompanyExpensePrice;
import com.yule.util.AdminLogUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/companyExpensePrice")
public class CompanyExpensePriceAction extends BaseAction{

	@Autowired
	private ICompanyExpensePriceService companyExpensePriceServiceImpl;
	
	@RequestMapping(value = "/findCompanyExpensePrice",method = RequestMethod.GET)
	public String CompanyExpensePrice(@RequestParam(value = "companyId", required = false)String companyId,@RequestParam(value = "pageNo", required = false) Integer pageNo)throws Exception{
		if (pageNo==null||pageNo<1) {
			pageNo=1;
		}
		try {
			StringBuffer htmls = new StringBuffer("");
			StringBuffer privilegeHtml = new StringBuffer("");
			Page<CompanyExpensePrice> page = this.companyExpensePriceServiceImpl.findCompanyExpensePricePage(companyId, PageConst.PAGE_SIZE_TEN, pageNo);
			htmls.append("<tfoot><tr><td colspan=\"8\">");
			htmls.append("<div class=\"bulk-actions align-left\"></div>");
			AdminPrivilege adminPrivilege = null;
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				htmls.append("<a class=\"button\" href=\"javascript:;\" data-add-row=\"\" />新增一行</a>&nbsp;");
				adminPrivilege = null;
			}
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody id=\"list\">");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE);
				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-update=\"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-delete=\"\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>");
				adminPrivilege = null;
			}
			if(page.getRowCount()>0){
				StringBuffer id = new StringBuffer();
				for (CompanyExpensePrice companyExpensePrice:page.getDatas()) {
					id.append(companyExpensePrice.getId());
					htmls.append("<tr>");
					htmls.append("<td><input id=\"id\" type=\"hidden\" name=\"id\" value=\""+id+"\" />");
					htmls.append("<input id=\"name\" class=\"text-input small-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入金额!\" datatype=\"\" errormsg=\"\"  value=\"" + companyExpensePrice.getName() + "\"/> </td>");
					htmls.append("<td><input id=\"price\" class=\"text-input small-input\" type=\"text\"  name=\"price\"  nullmsg=\"请输入金额!\" datatype=\"\" errormsg=\"\"  value=\"" + companyExpensePrice.getPrice() + "\"/> </td>");
					htmls.append("<td>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)+"</td>");
					htmls.append("<td>");
					id.setLength(0);
				}
				
			}else {
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"5\">暂无数据(点击新增一行添加数据)</td>");
				htmls.append("</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			StringBuffer rowHtml = new StringBuffer("");
			rowHtml.append("<td><input id=\"id\" type=\"hidden\" name=\"id\" value=\"\"  />");
			rowHtml.append("<input id=\"company_id\" type=\"hidden\" name=\"company_id\" value=\""+companyId+"\"/>");
			rowHtml.append("<input id=\"name\" class=\"text-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入名称!\" datatype=\"\" errormsg=\"\"  value=\"\"/></td>");
			rowHtml.append("<td><input id=\"price\" class=\"text-input small-input\" type=\"text\"  name=\"price\"  nullmsg=\"请输入价格!\" datatype=\"\" errormsg=\"\"  value=\"\"/></td>");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT);
				rowHtml.append("<td><a class=\"button\" href=\"javascript:;\" data-insert=\"\" data-url=\""+adminPrivilege.getUrl()+"\">"+adminPrivilege.getName()+"</a>");
				rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-del-row=\"\" \">删除</a></td>");
				adminPrivilege = null;
			}
			request.setAttribute("rowHtml", rowHtml);
			request.setAttribute("privilegeHtml", privilegeHtml);
			request.setAttribute("htmls", htmls);
			request.setAttribute("companyId", companyId);
			AdminLogUtil.insertLog("查看企业消费", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查看企业消费[/findCompanyExpensePrice]错误",e);
			throw e;
		}
		return "/company/expensePrice/index";
	}
	
	/**
	 * 新增企业消费
	 */
	@RequestMapping(value = "/insertCompanyExpensePrice",method = RequestMethod.POST)
	public String insertCompanyExpensePrice(@ModelAttribute("companyExpensePrice")CompanyExpensePrice companyExpensePrice) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = this.companyExpensePriceServiceImpl.insertCompanyExpensePrice(companyExpensePrice);
			obj.put("id", companyExpensePrice.getId());
			obj.put("status", flag);
			AdminLogUtil.insertLog("新增企业消费", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("新增企业消费[insertCompanyExpensePrice]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 删除企业消费
	 */
	@RequestMapping(value = "/deleteCompanyExpensePrice",method = RequestMethod.POST)
	public String deleteCompanyExpensePrice(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = this.companyExpensePriceServiceImpl.deleteCompanyExpensePriceById(id);
			obj.put("status", flag);
			AdminLogUtil.insertLog("删除企业消费", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除企业消费[deleteCompanyExpensePrice]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}	
	
	
	/**
	 * 更新企业消费
	 */
	@RequestMapping(value = "/updateCompanyExpensePrice",method = RequestMethod.POST)
	public String updateCompanyExpensePrice(@ModelAttribute("companyExpensePrice")CompanyExpensePrice companyExpensePrice) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyExpensePriceServiceImpl.updateCompanyExpensePrice(companyExpensePrice);
			obj.put("status", flag);
			AdminLogUtil.insertLog("更新企业消费", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新企业消费updateCompanyExpensePrice[]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}

}
