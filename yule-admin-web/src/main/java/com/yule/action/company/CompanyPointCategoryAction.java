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

import com.yule.admin.param.InsertCompanyPointCategoryParam;
import com.yule.admin.service.ICompanyPointCategoryService;
import com.yule.cached.CompanyInit;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.CompanyPointCategory;
import com.yule.util.AdminLogUtil;

@Controller
@Scope("prototype")
@RequestMapping("/companyPointCategory")
public class CompanyPointCategoryAction extends BaseAction{
	
	@Autowired
	private ICompanyPointCategoryService companyPointCategoryServiceImpl;
	
	/**
	 * 查询企业评分分类
	 */
	@RequestMapping(value = "/findCompanyPointCategory",method = RequestMethod.GET)
	public String findCompanyPointCategoryList() throws Exception {
		try {
			List<CompanyPointCategory> lists = companyPointCategoryServiceImpl.findCompanyPointCategoryList();
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
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-delete=\"\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>");
				adminPrivilege = null;
			}
			StringBuffer rowHtml = new StringBuffer("");
			rowHtml.append("<td><input id=\"id\" type=\"hidden\" name=\"id\" value=\""+PlaceholderConst.ID+"\"  />");
			rowHtml.append("<input id=\"name\" class=\"text-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入名称!\" datatype=\"\" errormsg=\"\" repeatmsg=\"名称重复!\" value=\""+PlaceholderConst.NAME+"\"/></td>");
			rowHtml.append("<td><input id=\"point\" class=\"text-input\" type=\"text\"  name=\"point\"  nullmsg=\"请输入排序号!\" datatype=\"n\" errormsg=\"\"  value=\""+PlaceholderConst.POINT+"\"/></td>");
			
			StringBuffer htmls = new StringBuffer("");
			
			if(null!=lists&&lists.size()>0){
				StringBuffer id = new StringBuffer();
				for(CompanyPointCategory companyPointCategory:lists){
					id.append(companyPointCategory.getId());
					htmls.append("<tr>");
					htmls.append(rowHtml.toString()
							.replace(PlaceholderConst.ID, id)
							.replace(PlaceholderConst.NAME, companyPointCategory.getName())
							.replace(PlaceholderConst.POINT, String.valueOf(companyPointCategory.getPoint())));
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
			request.setAttribute("privilegeHtml", privilegeHtml);
			request.setAttribute("operatorHtml", operatorHtml);
			request.setAttribute("htmls", htmls);
			AdminLogUtil.insertLog("查询企业评分分类", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查询企业评分分类[findCompanyPointCategory]错误",e);
			throw e;
		} 
		return "/company/pointCategory/index";
	}
	
	/**
	 * 删除企业评分分类
	 */
	@RequestMapping(value = "/deleteCompanyPointCategory",method = RequestMethod.POST)
	public String deleteCompanyPointCategory(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag =companyPointCategoryServiceImpl.deleteCompanyPointCategoryById(id);
			if (flag) {
				CompanyInit.initCompanyPointCategory();
			}
			obj.put("status", flag);
			AdminLogUtil.insertLog("删除企业评分分类", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除企业评分分类[deleteCompanyPointCategory]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 更新企业评分分类
	 */
	@RequestMapping(value = "/updateCompanyPointCategory",method = RequestMethod.POST)
	public String updateCompanyPointCategory(@ModelAttribute("companyPointCategory")CompanyPointCategory companyPointCategory) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyPointCategoryServiceImpl.updateCompanyPointCategory(companyPointCategory);
			if (flag) {
				CompanyInit.initCompanyPointCategory();
			}
			obj.put("status", flag);
			AdminLogUtil.insertLog("更新企业评分分类", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新企业评分分类[updateCompanyPointCategory]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	
	/**
	 * 新增企业评分分类
	 */
	@RequestMapping(value = "/insertCompanyPointCategory",method = RequestMethod.POST)
	public String insertCompanyPointCategory(@ModelAttribute("companyPointCategory")CompanyPointCategory companyPointCategory) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyPointCategoryServiceImpl.insertCompanyPointCategory(companyPointCategory);
			if (flag) {
				CompanyInit.initCompanyPointCategory();
			}
			obj.put("id", companyPointCategory.getId());
			obj.put("status", flag);
			AdminLogUtil.insertLog("新增企业评分分类", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("新增企业评分[insertCompanyPointCategory]分类",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}

	@RequestMapping(value = "/batchUpdateCompanyPointCategory",method = RequestMethod.POST)
	public String batchUpdateCompanyPointCategory(InsertCompanyPointCategoryParam insertCompanyPointCategoryParam) throws Exception {
		try {																		  				
			boolean flag=companyPointCategoryServiceImpl.batchInsertAndUpdateCompanyPointCategory(insertCompanyPointCategoryParam);
			if (flag) {
				CompanyInit.initCompanyPointCategory();
			}
			AdminLogUtil.insertLog("批量新增并更新企业评分分类", getAdminUser(), LogEnum.BATCH);
		} catch (Exception e) {
			new YuleException("批量新增并更新企业评分分类[/batchUpdateCompanyPointCategory]出错",e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/companyPointCategory/findCompanyPointCategory.do";
	}
}
