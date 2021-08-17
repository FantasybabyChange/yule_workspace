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

import com.yule.admin.param.InsertCompanyServeParam;
import com.yule.admin.param.UpdateCompanyServeCheckParam;
import com.yule.admin.service.ICompanyServeService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.CheckConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.CompanyServe;
import com.yule.util.AdminLogUtil;
import com.yule.vo.CompanyServeVO;

@Controller
@Scope("prototype")
@RequestMapping("/companyServe")
public class CompanyServeAction extends BaseAction{

	@Autowired
	private ICompanyServeService companyServeServiceImpl;
	
	/**
	 * 查询企业服务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCompanyServe",method = RequestMethod.GET)
	public String findCompanyServe() throws Exception {
		try {
			List<CompanyServe> lists = companyServeServiceImpl.findCompanyServeList();
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
			rowHtml.append("<input class=\"text-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入名称!\" datatype=\"\" errormsg=\"\" repeatmsg=\"名称重复!\" value=\""+PlaceholderConst.NAME+"\"/></td>");
			rowHtml.append("<td><input class=\"text-input\" type=\"text\"  name=\"order\"  nullmsg=\"请输入排序号!\" datatype=\"n\" errormsg=\"\"  value=\""+PlaceholderConst.ORDER+"\"/> </td>");
			
			StringBuffer htmls = new StringBuffer("");
			
			if(null!=lists&&lists.size()>0){
				StringBuffer id = new StringBuffer("");
				for(CompanyServe companyServe:lists){
					id.append(companyServe.getId());
					htmls.append("<tr>");
					htmls.append(rowHtml.toString()
							.replace(PlaceholderConst.ID, id)
							.replace(PlaceholderConst.NAME, companyServe.getName())
							.replace(PlaceholderConst.ORDER, String.valueOf(companyServe.getOrder())));
					htmls.append("<td>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)+"</td>");
					htmls.append("</tr>");
					id.setLength(0);
				}
				id = null;
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
			AdminLogUtil.insertLog("查看更新企业服务", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/company/serve/index";
	}
	
	@RequestMapping(value = "/batchUpdateCompanyServe",method = RequestMethod.POST)
	public String batchUpdateCompanyServe(InsertCompanyServeParam insertCompanyServeParam) throws Exception {
		try {
			this.companyServeServiceImpl.batchInsertAndUpdateCompanyServe(insertCompanyServeParam);
			AdminLogUtil.insertLog("批量更新企业服务", getAdminUser(), LogEnum.BATCH);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/companyServe/findCompanyServe.do";
	}
	
	
	/**
	 * 删除企业服务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteCompanyServe",method = RequestMethod.POST)
	public String deleteCompanyServe(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyServeServiceImpl.deleteCompanyServeById(id);
			obj.put("status", flag);
			AdminLogUtil.insertLog("删除企业服务设施", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 更新企业服务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompanyServe",method = RequestMethod.POST)
	public String updateCompanyServe(@ModelAttribute("companyServe")CompanyServe companyServe) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyServeServiceImpl.updateCompanyServe(companyServe);
			obj.put("status", flag);
			AdminLogUtil.insertLog("更新企业服务设施", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 更新企业服务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertCompanyServe",method = RequestMethod.POST)
	public String insertCompanyServe(@ModelAttribute("companyServe")CompanyServe companyServe) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyServeServiceImpl.insertCompanyServe(companyServe);
			obj.put("id", companyServe.getId());
			obj.put("status", flag);
			AdminLogUtil.insertLog("新增企业服务", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 查询企业选中服务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCompanyServeCheck",method = RequestMethod.GET)
	public String findCompanyCheckServe(@RequestParam(value="id",required=false)String id) throws Exception {
		try {
			List<CompanyServeVO> companyServeVOs = companyServeServiceImpl.findCompanyServeCheckList(id);
			StringBuffer htmls = new StringBuffer();
			if(null != companyServeVOs && companyServeVOs.size()>0){
				for (CompanyServeVO companyServeVO : companyServeVOs) {
					if(CheckConst.IS_CHECK_TRUE == companyServeVO.getIs_check()){
						htmls.append("<input type=\"checkbox\" name=\"company_serve_id\"  checked=\"checked\"  value=\""+ companyServeVO.getId()+ "\" />"+ companyServeVO.getName()+ " ");
					}else{
						htmls.append("<input type=\"checkbox\" name=\"company_serve_id\"  value=\""+ companyServeVO.getId()+ "\" />"+ companyServeVO.getName()+ "");
					}
				}
				companyServeVOs.clear();
				companyServeVOs=null;
			}
			request.setAttribute("companyId", id);
			request.setAttribute("html", htmls);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/company/serve/serveConfig";
	}
	
	/**
	 * 更新企业选中服务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompanyServeCheck",method = RequestMethod.POST)
	public String updateCompanyCheckServe(UpdateCompanyServeCheckParam updateCompanyServeCheckParam) throws Exception {
		String companyId=updateCompanyServeCheckParam.getCompany_id();
		try {
			companyServeServiceImpl.updateCompanyServeCheck(updateCompanyServeCheckParam);
			AdminLogUtil.insertLog("配置企业服务设施", getAdminUser(), LogEnum.CONFIG);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/companyServe/findCompanyServeCheck.do?id="+companyId;
	}
	
}
