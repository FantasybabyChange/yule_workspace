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

import com.yule.admin.service.ICompanyFavorableService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.param.InsertCompanyFavorableParam;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.CompanyFavorable;
import com.yule.util.AdminLogUtil;

@Controller
@Scope("prototype")
@RequestMapping("/companyFavorable")
public class CompanyFavorableAction extends BaseAction{
	
	@Autowired
	private ICompanyFavorableService companyFavorableServiceImpl;
	
	@RequestMapping(value="findCompanyFavorable",method=RequestMethod.GET)
	public String findCompanyFavorable(@RequestParam(value="id",required=false) String companyId) throws Exception{
		try {
			 List<CompanyFavorable> lists = this.companyFavorableServiceImpl.findCompanyFavorableByCompanyId(companyId);
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
		 	 	 privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-update=\"\" data-url=\""+adminPrivilege.getUrl()+"?company_id="+companyId+"\" >"+adminPrivilege.getName()+"</a>&nbsp;");
		 	 	 adminPrivilege = null;
			 }
			 if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
			 	 adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
			 	 privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-delete=\"\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>");
			 	 adminPrivilege = null;
			 }
			 StringBuffer rowHtml = new StringBuffer("");
			 rowHtml.append("<td><input type=\"hidden\" name=\"id\" value=\""+PlaceholderConst.ID+"\"  />");
			 rowHtml.append("<input class=\"text-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入优惠名称!\" datatype=\"\" errormsg=\"\"  value=\""+PlaceholderConst.NAME+"\"/></td>");
			 rowHtml.append("<td><input class=\"text-input\" type=\"text\"  name=\"price\"  nullmsg=\"请输入价格!\" datatype=\"n\" errormsg=\"\"  value=\""+PlaceholderConst.PRICE+"\"/> </td>");
			 rowHtml.append("<td><input class=\"text-input\" type=\"text\"  name=\"content\"  nullmsg=\"请输入优惠内容!\" datatype=\"\" errormsg=\"\"  value=\""+PlaceholderConst.CONTENT+"\"/> </td>");
			 StringBuffer htmls = new StringBuffer("");
			 if(null != lists && lists.size()>0){
				 StringBuffer id = new StringBuffer("");
				 for (CompanyFavorable companyFavorable : lists) {
					 id.append(companyFavorable.getId());
					 htmls.append("<tr>");
					 htmls.append(rowHtml.toString()
								.replace(PlaceholderConst.ID, id)
								.replace(PlaceholderConst.NAME, companyFavorable.getName())
								.replace(PlaceholderConst.PRICE, String.valueOf(companyFavorable.getPrice()))
								.replace(PlaceholderConst.CONTENT, companyFavorable.getContent()));
					 htmls.append("<td>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)+"</td>");
					 htmls.append("</tr>");
					 id.setLength(0);
				 }
				 lists.clear();
				 lists = null;
			 }else{
				 htmls.append("<tr>");
				 htmls.append("<td style=\"text-align: center;\" colspan=\"5\">暂无数据(点击新增一行添加数据)</td>");
				 htmls.append("</tr>");
			 }
			 rowHtml.append("<td>");
			 if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT);
				rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-insert=\"\" data-url=\""+adminPrivilege.getUrl()+"?company_id="+companyId+"\">"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			 }
			 rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-del-row=\"\" >删除</a>");
			 rowHtml.append("</td>");
			 request.setAttribute("companyId", companyId);
			 request.setAttribute("htmls",htmls);
			 request.setAttribute("rowHtml", rowHtml.toString().replace(PlaceholderConst.ID, "")
					 .replace(PlaceholderConst.NAME, "")
					 .replace(PlaceholderConst.CONTENT, "")
					 .replace(PlaceholderConst.PRICE, ""));
			 request.setAttribute("privilegeHtml", privilegeHtml);
			 request.setAttribute("operatorHtml", operatorHtml);
			 AdminLogUtil.insertLog("查询企业优惠", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		}
		return "/company/favorable/index";
	}
	
	@RequestMapping(value = "/batchUpdateCompanyFavorable",method = RequestMethod.POST)
	public String batchUpdateCompanyFavorable(InsertCompanyFavorableParam insertCompanyFavorableParam) throws Exception {
		try {
			this.companyFavorableServiceImpl.batchInsertAndUpdateCompanyFavorable(insertCompanyFavorableParam);
			AdminLogUtil.insertLog("批量新增企业优惠", getAdminUser(), LogEnum.BATCH);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/companyFavorable/findCompanyFavorable.do?id="+insertCompanyFavorableParam.getCompany_id();
	}
	
	@RequestMapping(value="updateCompanyFavorable",method=RequestMethod.POST)
	public String updateCompanyFavorable(@ModelAttribute("companyFavorable")CompanyFavorable companyFavorable) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyFavorableServiceImpl.updateCompanyFavorable(companyFavorable);
			obj.put("status", flag);
			AdminLogUtil.insertLog("更新企业优惠", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	@RequestMapping(value="insertCompanyFavorable",method=RequestMethod.POST)
	public String insertCompanyFavorable(@ModelAttribute("companyFavorable")CompanyFavorable companyFavorable) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyFavorableServiceImpl.insertCompanyFavorable(companyFavorable);
			obj.put("id", companyFavorable.getId());
			obj.put("status", flag);
			AdminLogUtil.insertLog("新增企业优惠", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	@RequestMapping(value="deleteCompanyFavorable",method=RequestMethod.POST)
	public String deleteCompanyFavorable(@RequestParam(value="id",required=false) String id) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyFavorableServiceImpl.deleteCompanyFavorableById(id);
			if(flag){
//				CompanyFlowUtil.deleteCompanyFlow(.getCompany_id(), CompanyFlowEnum.COMPANY_FAVORABLE);
			}
			obj.put("status", flag);
			AdminLogUtil.insertLog("删除企业优惠", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
