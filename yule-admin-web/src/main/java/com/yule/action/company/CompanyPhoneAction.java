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

import com.yule.admin.param.InsertCompanyPhoneParam;
import com.yule.admin.service.ICompanyPhoneService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.CompanyPhone;
import com.yule.util.AdminLogUtil;


@Controller
@Scope("prototype")
@RequestMapping("/companyPhone")
public class CompanyPhoneAction extends BaseAction{

	@Autowired
	private ICompanyPhoneService companyPhoneServiceImpl;
	
	/**
	 * 查询企业联系方式
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCompanyPhone",method = RequestMethod.GET)
	public String findCompanyPhoneList(@RequestParam(value="id",required=false)String companyId) throws Exception {
		try {
			List<CompanyPhone> lists = companyPhoneServiceImpl.findCompanyPhoneListByCompanyId(companyId);
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
			rowHtml.append("<td><input type=\"hidden\" name=\"id\" value=\""+PlaceholderConst.ID+"\"  />");
			rowHtml.append("<input class=\"text-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入名称!\" datatype=\"\" errormsg=\"\" repeatmsg=\"名称重复!\"  value=\""+PlaceholderConst.NAME+"\"/></td>");
			rowHtml.append("<td><input class=\"text-input\" type=\"text\"  name=\"phone\"  nullmsg=\"请输入电话!\" datatype=\"phone\" errormsg=\"\"  value=\""+PlaceholderConst.PHONE+"\"/> </td>");
			
			StringBuffer htmls = new StringBuffer("");
			if(null!=lists&&lists.size()>0){
				StringBuffer id = new StringBuffer();
				for(CompanyPhone companyPhone:lists) {
					id.append(companyPhone.getId());
					htmls.append("<tr>");
					htmls.append(rowHtml.toString()
							.replace(PlaceholderConst.ID, id)
							.replace(PlaceholderConst.NAME, companyPhone.getName())
							.replace(PlaceholderConst.PHONE, String.valueOf(companyPhone.getPhone())));
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
			htmls.append("</form>");
			htmls.append("</tbody>");
			rowHtml.append("<td>");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT);
				rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-insert=\"\" data-url=\""+adminPrivilege.getUrl()+"\">"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-del-row=\"\" >删除</a>");
			rowHtml.append("</td>");
			request.setAttribute("rowHtml", rowHtml.toString().replace(PlaceholderConst.ID, "").replace(PlaceholderConst.NAME, "").replace(PlaceholderConst.PHONE, ""));
			request.setAttribute("htmls", htmls);
			request.setAttribute("privilegeHtml", privilegeHtml);
			request.setAttribute("operatorHtml", operatorHtml);
			request.setAttribute("companyId", companyId);
			AdminLogUtil.insertLog("查看企业电话", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/company/phone/index";
	}
	
	@RequestMapping(value = "/batchUpdateCompanyPhone",method = RequestMethod.POST)
	public String batchUpdateCompanyPhone(InsertCompanyPhoneParam insertCompanyPhoneParam) throws Exception {
		try {
			this.companyPhoneServiceImpl.batchInsertAndUpdateCompanyPhone(insertCompanyPhoneParam);
			AdminLogUtil.insertLog("批量更新企业电话", getAdminUser(), LogEnum.BATCH);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/companyPhone/findCompanyPhone.do?id="+insertCompanyPhoneParam.getCompany_id();
	}
	
	/**
	 * 添加企业联系方式
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertCompanyPhone",method = RequestMethod.POST)
	public String insertCompanyPhone(@ModelAttribute("companyPhone")CompanyPhone companyPhone) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyPhoneServiceImpl.insertCompanyPhone(companyPhone);
			obj.put("id", companyPhone.getId());
			obj.put("status", flag);
			AdminLogUtil.insertLog("新增企业电话", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	
	/**
	 * 删除企业联系方式
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteCompanyPhone",method = RequestMethod.POST)
	public String deleteCompanyPhone(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyPhoneServiceImpl.deleteCompanyPhoneById(id);
			obj.put("status", flag);
			AdminLogUtil.insertLog("删除企业电话", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 更新企业联系方式
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompanyPhone",method = RequestMethod.POST)
	public String updateCompanyPhone(@ModelAttribute("companyPhone")CompanyPhone companyPhone) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyPhoneServiceImpl.updateCompanyPhone(companyPhone);
			obj.put("status", flag);
			AdminLogUtil.insertLog("更新企业电话", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
