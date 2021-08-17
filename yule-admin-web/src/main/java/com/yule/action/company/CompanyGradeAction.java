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

import com.yule.admin.param.InsertCompanyGradeParam;
import com.yule.admin.service.ICompanyGradeService;
import com.yule.cached.CompanyInit;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.CompanyGrade;
import com.yule.util.AdminLogUtil;

@Controller
@Scope("prototype")
@RequestMapping("/companyGrade")
public class CompanyGradeAction extends BaseAction{

	@Autowired
	private ICompanyGradeService companyGradeServiceImpl;
	
	/**
	 * 查询企业分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCompanyGrade",method = RequestMethod.GET)
	public String findCompanyGradeList() throws Exception {
		try {
			List<CompanyGrade> lists = companyGradeServiceImpl.findCompanyGradeList();
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
			rowHtml.append("<input class=\"text-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入名称!\" datatype=\"\" errormsg=\"\" errormsg=\"\" repeatmsg=\"名称重复!\"  value=\""+PlaceholderConst.NAME+"\"/></td>");
			rowHtml.append("<td><input class=\"text-input\" type=\"text\"  name=\"order\"  nullmsg=\"请输入排序号!\" datatype=\"n\" errormsg=\"\"  value=\""+PlaceholderConst.ORDER+"\"/> </td>");
			
			StringBuffer htmls = new StringBuffer("");
			if(null!=lists&&lists.size()>0) {
				StringBuffer id = new StringBuffer("");
				for(CompanyGrade companyGrade:lists) {
					id.append(companyGrade.getId());
					htmls.append("<tr>");
					htmls.append(rowHtml.toString()
							.replace(PlaceholderConst.ID, id)
							.replace(PlaceholderConst.NAME, companyGrade.getName())
							.replace(PlaceholderConst.ORDER, String.valueOf(companyGrade.getOrder())));
					htmls.append("<td>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)+"</td>");
					htmls.append("</tr>");
					id.setLength(0);
				}
				id = null;
				lists.clear();
				lists=null;
			} else {
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
			AdminLogUtil.insertLog("查看企业档次", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/company/grade/index";
	}
	
	@RequestMapping(value = "/batchUpdateCompanyGrade",method = RequestMethod.POST)
	public String batchUpdateCompanyGrade(InsertCompanyGradeParam insertCompanyGradeParam) throws Exception {
		try {
			boolean flag = this.companyGradeServiceImpl.batchInsertAndUpdateCompanyGrade(insertCompanyGradeParam);
			if(flag){
				CompanyInit.initCompanyGrade();
			}
			AdminLogUtil.insertLog("批量更新企业档次", getAdminUser(), LogEnum.BATCH);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/companyGrade/findCompanyGrade.do";
	}
	
	/**
	 * 添加企业分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertCompanyGrade",method = RequestMethod.POST)
	public String insertCompanyGrade(@ModelAttribute("companyGrade")CompanyGrade companyGrade) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyGradeServiceImpl.insertCompanyGrade(companyGrade);
			if(flag){
				CompanyInit.initCompanyGrade();
			}
			obj.put("id", companyGrade.getId());
			obj.put("status", flag);
			AdminLogUtil.insertLog("新增企业档次", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 删除企业分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteCompanyGrade",method = RequestMethod.POST)
	public String deleteCompanyGrade(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyGradeServiceImpl.deleteCompanyGradeById(id);
			if(flag){
				CompanyInit.initCompanyGrade();
			}
			obj.put("status", flag);
			AdminLogUtil.insertLog("删除企业档次", getAdminUser(), LogEnum.DELETE);
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
	@RequestMapping(value = "/updateCompanyGrade",method = RequestMethod.POST)
	public String updateCompanyGrade(@ModelAttribute("companyGrade")CompanyGrade companyGrade) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyGradeServiceImpl.updateCompanyGrade(companyGrade);
			if(flag){
				CompanyInit.initCompanyGrade();
			}
			obj.put("status", flag);
			AdminLogUtil.insertLog("更新企业档次", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
