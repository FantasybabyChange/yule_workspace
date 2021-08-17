package com.yule.action.salesman;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.query.SalesmanQuery;
import com.yule.admin.service.ISalesmanLoginService;
import com.yule.admin.vo.SalesmanVO;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.AdminErrorConst;
import com.yule.constant.DeleteConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PageConst;
import com.yule.constant.PrivilegeConst;
import com.yule.constant.StatusConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.SalesmanLogin;
import com.yule.util.AdminLogUtil;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;
@Controller
@Scope("prototype")
@RequestMapping("/salesmanLogin")
public class SalesmanLoginAction extends BaseAction {
	
	@Autowired
	private ISalesmanLoginService salesmanLoginServiceImpl;
	
	/**
	 * 查询销售人员
	 * @return
	 * @throws Exception
	 */
   @RequestMapping(value = "/findSalesmanLogin", method = RequestMethod.GET)
	public String findSalesmanLogin(@ModelAttribute("salesmanQuery")SalesmanQuery salesmanQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception {
		if (pageNo == null || pageNo <= 0) {
			pageNo = 1;
		}
		try {
			Page<SalesmanVO> page = salesmanLoginServiceImpl.findSalesmanVOPage(salesmanQuery,PageConst.PAGE_SIZE_TEN, pageNo);
			StringBuffer htmls = new StringBuffer("");
			htmls.append("<tfoot>");
			htmls.append("<tr>");
			htmls.append("<td colspan=\"8\">");
			htmls.append("<div class=\"bulk-actions align-left\">");
			htmls.append("</div>");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td>");
			htmls.append("</tr>");
			htmls.append("</tfoot>");
			htmls.append("<tbody>");
			List<SalesmanVO> lists = page.getDatas();
			if (null != lists && lists.size() > 0) {
				StringBuffer id = new StringBuffer();
				AdminPrivilege adminPrivilege = null;
				for (SalesmanVO salesmanVO : lists) {
					id.append(salesmanVO.getId());
					htmls.append("<tr>");
					htmls.append("<td><input type=\"checkbox\" /></td>");
					htmls.append("<td>" + id + "</td>");
					htmls.append("<td>" + salesmanVO.getAccount() + "</td>");
					htmls.append("<td>" + salesmanVO.getName() + "</td>");
					htmls.append("<td>" + DateUtil.DateToString(salesmanVO.getLogin_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
					if (StringUtils.isEmpty(salesmanVO.getIs_delete())) {
						htmls.append("<td class=\"is_delete\">" + DeleteConst.IS_DELETE[DeleteConst.IS_DELETE_FALSE] + "</td>");	
					}else{
						htmls.append("<td class=\"is_delete\">" + DeleteConst.IS_DELETE[salesmanVO.getIs_delete()] + "</td>");
					}
					htmls.append("<td>" + DateUtil.DateToString(salesmanVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
					htmls.append("<td>");
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE);
						htmls.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id=" + id + "\">"+adminPrivilege.getName()+"</a>&nbsp;");
						adminPrivilege = null;
					}
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DETAILS)){	
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DETAILS);
						htmls.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id=" + id + "\">"+adminPrivilege.getName()+"</a>&nbsp;");
						adminPrivilege = null;
					}
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE_STATUS)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE_STATUS);
						if (salesmanVO.getStatus() == null) {
							htmls.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl()+"\" data-id=\""+id+"\" data-name=\"status\" data-status=\""+StatusConst.BUTTON_STATUS_VALUE[StatusConst.STATUS_FALSE]+"\" />"+StatusConst.BUTTON_STATUS[StatusConst.STATUS_FALSE]+"</a>&nbsp;");	
						}else{
							htmls.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl()+"\" data-id=\""+id+"\" data-name=\"status\" data-status=\""+StatusConst.BUTTON_STATUS_VALUE[salesmanVO.getStatus()]+"\" />"+StatusConst.BUTTON_STATUS[salesmanVO.getStatus()]+"</a>&nbsp;");
						}
					}
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
						if (salesmanVO.getIs_delete() == null) {
							htmls.append("<a class=\"button\" data-name=\"is_delete\" href=\"javascript:;\"  name=\"is_delete\" data-url=\""+adminPrivilege.getUrl()+"\" data-id=\""+salesmanVO.getId()+"\" data-status=\""+DeleteConst.BUTTON_DELETE_VALUE[DeleteConst.IS_DELETE_FALSE]+"\" />"+DeleteConst.BUTTON_DELETE[DeleteConst.IS_DELETE_FALSE]+"</a>&nbsp;");
						}else{
							htmls.append("<a class=\"button\" data-name=\"is_delete\" href=\"javascript:;\"  name=\"is_delete\" data-url=\""+adminPrivilege.getUrl()+"\" data-id=\""+salesmanVO.getId()+"\" data-status=\""+DeleteConst.BUTTON_DELETE_VALUE[salesmanVO.getIs_delete()]+"\" />"+DeleteConst.BUTTON_DELETE[salesmanVO.getIs_delete()]+"</a>&nbsp;");
						}
						adminPrivilege = null;
					}
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_SALESMAN_BALANCE_CONFIG)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_SALESMAN_BALANCE_CONFIG);
						htmls.append("<a  class=\"button\" href=\"" + adminPrivilege.getUrl() + "?salesman_id=" +id + "\"  >" + adminPrivilege.getName() + "</a>&nbsp;&nbsp; ");
						adminPrivilege = null;
					}
					htmls.append("</td>");
					htmls.append("</tr>");
					id.setLength(0);
				}
				lists.clear();
				lists = null;
			} else {
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"8\">暂无数据</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			request.setAttribute("salesmanQuery", salesmanQuery);
			request.setAttribute("htmls", htmls);
			AdminLogUtil.insertLog("查看业务人员", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查看企业[findCompanyManagerVO]错误", e);
			throw e;
		}
		return "/salesman/index";
	}
	/**
	 * 新增业务员
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertSalesmanLogin",method = RequestMethod.POST)
	public String insertSalesmanLogin(@ModelAttribute("salesmanLogin")SalesmanLogin salesmanLogin,@RequestParam(value="commision",required=false)Integer commision) throws Exception {
		try {
			this.salesmanLoginServiceImpl.insertSalesmanLogin(salesmanLogin,commision);
			AdminLogUtil.insertLog("新增业务员", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("新增业务员【insertSalesmanLogin】出现异常",e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/salesmanLogin/findSalesmanLogin.do";
	}
	@RequestMapping(value = "/findSalesmanLoginPassword",method = RequestMethod.GET)
	public String findSalesmanLoginPassword(@RequestParam(value="id",required=false) String id) throws Exception {
		try {
			request.setAttribute("id", id);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/salesman/update";
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSalesmanLoginPassword",method = RequestMethod.POST)
	public String updateSalesmanLoginPassword(@ModelAttribute("salesmanLogin")SalesmanLogin salesmanLogin) throws Exception {
		try {
			this.salesmanLoginServiceImpl.updateSalesmanLoginPassword(salesmanLogin);
			AdminLogUtil.insertLog("修改业务员MOMA", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("修改业务员密码【updateSalesmanLoginPassword】出现异常",e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/salesmanLogin/findSalesmanLogin.do";
	}
	
	/**
	 * 更新业务员状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSalesmanLoginStatus",method = RequestMethod.POST)
	public String updateSalesmanLoginStatus(@ModelAttribute("salesmanLogin")SalesmanLogin salesmanLogin) throws Exception {
		JSONObject obj=new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try{
			boolean flag = this.salesmanLoginServiceImpl.updateSalesmanLoginStatus(salesmanLogin);
			int status = salesmanLogin.getStatus();
			obj.put("status", flag);
			obj.put("value",StatusConst.BUTTON_STATUS_VALUE[status]);
			obj.put("text",StatusConst.BUTTON_STATUS[status]);
		} catch(Exception e){
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 删除业务员
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteSalesmanLogin",method = RequestMethod.POST)
	public String deleteSalesmanLogin(@ModelAttribute("salesmanLogin") SalesmanLogin salesmanLogin) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = this.salesmanLoginServiceImpl.deleteSalesmanLogin(salesmanLogin);
			int status = salesmanLogin.getIs_delete();
			obj.put("status", flag);
			obj.put("value",DeleteConst.BUTTON_DELETE_VALUE[status]);
			obj.put("is_delete_text",DeleteConst.IS_DELETE[status]);
			obj.put("text",DeleteConst.BUTTON_DELETE[status]);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	@RequestMapping(value = "/verifySalesmanLogin", method = RequestMethod.POST)
	public String verifySalesmanLogin(@RequestParam(value="account",required=false)String account) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			boolean flag = salesmanLoginServiceImpl.findSalesmanLoginByAccount(account);
			if(!flag){
				obj.put("message",AdminErrorConst.ADMIN_USER_2);
				obj.put("status",ErrorConst.STATUS_ERROR);
			}else{
				obj.put("status",ErrorConst.STATUS_SUCCESS);
			}
			obj.put("name","account");
		} catch (Exception e) {
			new YuleException("验证销售人员verifySalesmanLogin】:发生错误!", e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
		
	}
	
	@RequestMapping(value = "/findSalesmanAjax",method = RequestMethod.POST)
	public String findSalesmanAjax()throws Exception{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", ErrorConst.STATUS_ERROR);
		try {
			List<SalesmanLogin> salesman = this.salesmanLoginServiceImpl.findSalesmanLoginList();
			if (salesman != null && salesman.size() > 0) {
				JSONArray jsonArray = new JSONArray();
				for(int i =0;i<salesman.size();i++){
					JSONObject obj = new JSONObject();
					obj.put("id",salesman.get(i).getId());
					obj.put("name",salesman.get(i).getAccount());
					jsonArray.add(obj);
				}
				jsonObject.put("salesmans", jsonArray.toString());
				jsonObject.put("status", ErrorConst.STATUS_SUCCESS);		
			}
		} catch (Exception e) {
			new YuleException("查询所有销售人员",e);
			throw e;
		}finally{
			outputResult(jsonObject.toString());
		}
		return null;
	}
	
}
