package com.yule.action.company;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.param.InsertCompanyCategoryParam;
import com.yule.admin.service.ICompanyCategoryService;
import com.yule.cached.CompanyInit;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.CompanyCategory;

@Controller
@Scope("prototype")
@RequestMapping("/companyCategory")
public class CompanyCategoryAction extends BaseAction{

	@Autowired
	private ICompanyCategoryService companyCategoryServiceImpl;
	
	/**
	 * 查询企业分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCompanyCategory",method = RequestMethod.GET)
	public String findCompanyCategoryList() throws Exception {
		try {
			List<CompanyCategory> lists = companyCategoryServiceImpl.findCompanyCategoryList();
			AdminPrivilege adminPrivilege = null;
			StringBuffer operatorHtml = new StringBuffer("");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				operatorHtml.append("<a class=\"button\" href=\"javascript:;\" data-add-row=\"\" />新增一行</a>&nbsp;");
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_BATCH_UPDATE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_BATCH_UPDATE);
				operatorHtml.append("<input class=\"button\" type=\"submit\" value=\""+adminPrivilege.getName()+"\">");
				adminPrivilege = null;
			}
			StringBuffer privilegeHtml = new StringBuffer("");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE);
				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-update=\"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
//			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
//				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
//				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-delete=\"\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>");
//				adminPrivilege = null;
//			}
			StringBuffer rowHtml = new StringBuffer("");
			rowHtml.append("<td><input type=\"hidden\" name=\"id\" value=\""+PlaceholderConst.ID+"\"  />");
			rowHtml.append("<input class=\"text-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入名称!\" datatype=\"\" errormsg=\"\" repeatmsg=\"名称重复!\" value=\""+PlaceholderConst.NAME+"\"/></td>");
			rowHtml.append("<td><input class=\"text-input\" type=\"text\"  name=\"order\"  nullmsg=\"请输入排序号!\" datatype=\"n\" errormsg=\"\"  value=\""+PlaceholderConst.ORDER+"\"/></td>");
			
			StringBuffer htmls = new StringBuffer("");
			
			if(null!=lists&&lists.size()>0){
				StringBuffer id = new StringBuffer();
				for(CompanyCategory companyCategory:lists){
					id.append(companyCategory.getId());
					htmls.append("<tr>");
					htmls.append(rowHtml.toString()
							.replace(PlaceholderConst.ID, id)
							.replace(PlaceholderConst.NAME, companyCategory.getName())
							.replace(PlaceholderConst.ORDER, String.valueOf(companyCategory.getOrder())));
					htmls.append("<td>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)+"</td>");
					htmls.append("</tr>");
					id.setLength(0);
				}
				lists.clear();
				lists=null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"5\">暂无数据(点击新增一行添加数据)</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			rowHtml.append("<td>");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT);
				rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-insert=\"\" data-url=\""+adminPrivilege.getUrl()+"\">"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-del-row=\"\" >删除</a>");
			rowHtml.append("</td>");
			request.setAttribute("rowHtml", rowHtml.toString().replace(PlaceholderConst.ID, "").replace(PlaceholderConst.NAME, ""));
			request.setAttribute("htmls", htmls);
			request.setAttribute("privilegeHtml", privilegeHtml);
			request.setAttribute("operatorHtml", operatorHtml);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		}
		return "/company/category/index";
	}
	
	@RequestMapping(value = "/batchUpdateCompanyCategory",method = RequestMethod.POST)
	public String batchUpdateCompanyCategory(InsertCompanyCategoryParam insertCompanyCategoryParam) throws Exception {
		try {
			boolean flag = companyCategoryServiceImpl.batchInsertAndUpdateCompanyCategory(insertCompanyCategoryParam);
			if(flag){
				CompanyInit.initCompanyCategory();
			}
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/companyCategory/findCompanyCategory.do";
	}
	
	
	/**
	 * 删除企业分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteCompanyCategory",method = RequestMethod.POST)
	public String deleteCompanyCategory(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyCategoryServiceImpl.deleteCompanyCategoryById(id);
			if(flag){
				CompanyInit.initCompanyCategory();
			}
			obj.put("status", flag);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 更新企业分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompanyCategory",method = RequestMethod.POST)
	public String updateCompanyCategory(@ModelAttribute("companyCategory")CompanyCategory companyCategory) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyCategoryServiceImpl.updateCompanyCategory(companyCategory);
			if(flag){
				CompanyInit.initCompanyCategory();
			}
			obj.put("status", flag);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 更新企业分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertCompanyCategory",method = RequestMethod.POST)
	public String insertCompanyCategory(@ModelAttribute("companyCategory")CompanyCategory companyCategory) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyCategoryServiceImpl.insertCompanyCategory(companyCategory);
			if(flag){
				CompanyInit.initCompanyCategory();
			}
			obj.put("id", companyCategory.getId());
			obj.put("status", flag);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
