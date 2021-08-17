package com.yule.action.company;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.param.InsertCompanyManagerParam;
import com.yule.admin.query.CompanyManagerQuery;
import com.yule.admin.service.ICompanyService;
import com.yule.admin.service.ICompanyUserService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.AdminErrorConst;
import com.yule.constant.CompanyErrorConst;
import com.yule.constant.CooperatorConst;
import com.yule.constant.DeleteConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.JSONConst;
import com.yule.constant.PageConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.constant.StatusConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.Company;
import com.yule.pojo.CompanyUser;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.AdminLogUtil;
import com.yule.util.CompanyUtil;
import com.yule.util.DateUtil;
import com.yule.util.IDUtil;
import com.yule.util.PaginationUtil;
import com.yule.util.PinyinUtil;
import com.yule.vo.CompanyCategoryVO;
import com.yule.vo.CompanyGradeVO;
import com.yule.vo.CompanyManagerVO;
import com.yule.vo.CompanyVO;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/company")
public class CompanyAction extends BaseAction{
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	@Autowired
	private ICompanyUserService companyUserService;
	
    @RequestMapping(value = "/findCompanyById",method = RequestMethod.GET)
    public String findCompanyById(@RequestParam(value="id",required=false) String id) throws Exception{
    	CompanyVO companyVO = companyServiceImpl.findCompanyVOById(id);
    	companyVO.setBusiness_license_image(FileUploadConst.COMPANY_IMAGE_PATH+companyVO.getId()+"/"+FileUploadConst.PX_150_150+FileUploadConst.BUSINESS_LICENSE+FileUploadConst.IMAGE_TYPE);
    	companyVO.setLegal_person_image(FileUploadConst.COMPANY_IMAGE_PATH+companyVO.getId()+"/"+FileUploadConst.PX_150_150+FileUploadConst.LEGAL_PERSON+FileUploadConst.IMAGE_TYPE);
    	companyVO.setImage_path(FileUploadConst.COMPANY_IMAGE_PATH+companyVO.getId()+"/"+FileUploadConst.PX_150_150+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE);
    	request.setAttribute("company", companyVO);
    	request.setAttribute("gradeHtml", CompanyUtil.getCompanyGradeSelect());
    	request.setAttribute("categoryHtml", CompanyUtil.getCompanyCategorySelect());
    	return "/company/companyInfo";
    }
   
    /**
     * 更新基本信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateCompanyInfo",method = RequestMethod.POST)
    public String updateCompanyInfo(@ModelAttribute("company")Company company) throws Exception {
    	try {
    		if (company.getCooperatory_type() == CooperatorConst.COOPERATOR_TYPE_COMPANY) {
    			company.setSalesman_id(null);
    		}
    		companyServiceImpl.updateCompanyInfo(company);
    	} catch (Exception e) {
    		new YuleException(e);
    		throw e;
    	} 
    	return ActionReturnConst.REDIRECT+"/company/findCompanyById.do?id="+company.getId();
    }
	/**
	 * 更新企业类别
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompanyCategory",method = RequestMethod.POST)
	public String updateCompanyCategory(@ModelAttribute("company")Company company) throws Exception {
		try {
			companyServiceImpl.updateCompanyCategory(company);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/company/findCompanyById.do?id="+company.getId();
	}
	/**
	 * 更新企业
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompany",method = RequestMethod.POST)
	public String updateCompany(@ModelAttribute("company")Company company) throws Exception {
		try {
			companyServiceImpl.updateCompany(company);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/company/findCompanyById.do?id="+company.getId();
	}
	
	/**
	 * 查询企业
	 * @return
	 * @throws Exception
	 */
   @SuppressWarnings("unchecked")
@RequestMapping(value = "/findCompany", method = RequestMethod.GET)
	public String findCompany(CompanyManagerQuery companyManagerQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception {
		if (pageNo == null || pageNo <= 0) {
			pageNo = 1;
		}
		try {
			StringBuffer search_html = new StringBuffer("");
			search_html.append("<span>企业档次:<select name=\"company_grade_id\">");
			List<CompanyGradeVO> companyGradeVOs= new ArrayList<CompanyGradeVO>();
			if(JedisUtil.getInstance().exists(RedisConst.COMPANY_GRADE)){
				companyGradeVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_GRADE)),new CompanyGradeVO(),JSONConst.JSON_CONFIG));
			}
			search_html.append("<option value=\"\">全部</option>");
			if (companyGradeVOs.size()>0) {
				for (CompanyGradeVO companyGradeVO : companyGradeVOs) {
					search_html.append("<option value=\""+companyGradeVO.getId()+"\">"+companyGradeVO.getName()+"</option>");
				}
				companyGradeVOs.clear();
			}
			companyGradeVOs = null;
			search_html.append("</select></span>");
			
			search_html.append("<span>企业类别:<select name=\"company_category_id\">");
			List<CompanyCategoryVO> companyCategoryVOs = new ArrayList<CompanyCategoryVO>();
			if(JedisUtil.getInstance().exists(RedisConst.COMPANY_CATEGORY)){
				companyCategoryVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_CATEGORY)),new CompanyCategoryVO(),JSONConst.JSON_CONFIG));
			}
			search_html.append("<option value=\"\">全部</option>");
			if (companyCategoryVOs.size()>0) {
				for (CompanyCategoryVO companyCategoryVO : companyCategoryVOs) {
					search_html.append("<option value=\""+companyCategoryVO.getId()+"\">"+companyCategoryVO.getName()+"</option>");
				}
				companyCategoryVOs.clear();
			}
			companyCategoryVOs = null;
			search_html.append("</select></span>");
			Page<CompanyManagerVO> page = companyServiceImpl.findCompanyManagerVOPage(companyManagerQuery,PageConst.PAGE_SIZE_TEN, pageNo);
			StringBuffer htmls = new StringBuffer("");
			htmls.append("<tfoot>");
			htmls.append("<tr>");
			htmls.append("<td colspan=\"10\">");
			htmls.append("<div class=\"bulk-actions align-left\">");
			htmls.append("</div>");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td>");
			htmls.append("</tr>");
			htmls.append("</tfoot>");
			htmls.append("<tbody>");
			List<CompanyManagerVO> lists = page.getDatas();
			if (null != lists && lists.size() > 0) {
				String id = null;
				StringBuffer privilegeHtml =new StringBuffer();
				AdminPrivilege adminPrivilege = null;
				if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){
					adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE);
					privilegeHtml.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id=" + PlaceholderConst.ID + "\">"+adminPrivilege.getName()+"</a>&nbsp;");
					adminPrivilege = null;
				}
				if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DETAILS)){	
					adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DETAILS);
					privilegeHtml.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id=" + PlaceholderConst.ID + "\">"+adminPrivilege.getName()+"</a>&nbsp;");
					adminPrivilege = null;
				}
				privilegeHtml.append("<!-- Icons --> <a class=\"button\" href=\"/product/findProduct.do?id=" + PlaceholderConst.ID + "\">产品管理</a>&nbsp;");
				if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_ADDRESS)){
					adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_ADDRESS);
					privilegeHtml.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id=" + PlaceholderConst.ID + "\">"+adminPrivilege.getName()+"</a>&nbsp;");
					adminPrivilege = null;
				}
				if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_GALLERY)){
					adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_GALLERY);
					privilegeHtml.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id=" + PlaceholderConst.ID + "\">"+adminPrivilege.getName()+"</a>&nbsp;");
					adminPrivilege = null;
				}
				if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_SERVE)){
					adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_SERVE);
					privilegeHtml.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id=" + PlaceholderConst.ID + "\">"+adminPrivilege.getName()+"</a>&nbsp;");
					adminPrivilege = null;
				}
				if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_FAVORABLE)){
					adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_FAVORABLE);
					privilegeHtml.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id=" + PlaceholderConst.ID + "\">"+adminPrivilege.getName()+"</a>&nbsp;");
					adminPrivilege = null;
				}
				if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_PHONE)){
					adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_PHONE);
					privilegeHtml.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id=" +PlaceholderConst.ID+ "\">"+adminPrivilege.getName()+"</a>&nbsp;");
					adminPrivilege = null;
				}
				if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_COMMENT)){
					adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_COMMENT);
					privilegeHtml.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?company_id=" + PlaceholderConst.ID + "\">"+adminPrivilege.getName()+"</a>&nbsp;");
					adminPrivilege = null;
				}
				if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_POINT)){
					adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_POINT);
					privilegeHtml.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id=" + PlaceholderConst.ID + "\">"+adminPrivilege.getName()+"</a>&nbsp;");
					adminPrivilege = null;
				}
				if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_USER_COLLECTIONS)){
					adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_USER_COLLECTIONS);
					privilegeHtml.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?companyId=" + PlaceholderConst.ID + "\">"+adminPrivilege.getName()+"</a>&nbsp;");
					adminPrivilege = null;
				}
				if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_COMPANY_USER)){
					adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_COMPANY_USER);
					privilegeHtml.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?companyId="+PlaceholderConst.ID+"\"  data-id=\""+PlaceholderConst.ID+"\" >"+adminPrivilege.getName()+"</a>&nbsp;");
					
				}
				if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_COMPANY_EXPENSE_CONFIG)){
					adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_COMPANY_EXPENSE_CONFIG);
					privilegeHtml.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?companyId="+PlaceholderConst.ID+"\"  data-id=\""+PlaceholderConst.ID+"\" >"+adminPrivilege.getName()+"</a>&nbsp;");
					adminPrivilege = null;
				}
				if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_COMPANY_USERPRIVILEGE_CONFIG)){
					adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_COMPANY_USERPRIVILEGE_CONFIG);
					privilegeHtml.append("<a  class=\"button\" href=\"" + adminPrivilege.getUrl() + "?id=" + PlaceholderConst.ID + "&companyId=" + PlaceholderConst.ID + "\"  >" + adminPrivilege.getName() + "</a>&nbsp;&nbsp; ");
					adminPrivilege = null;
				}
				if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_COMPANY_BALANCE_CONFIG)){
					adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_COMPANY_BALANCE_CONFIG);
					privilegeHtml.append("<a  class=\"button\" href=\"" + adminPrivilege.getUrl() + "?company_id=" + PlaceholderConst.ID + "\"  >" + adminPrivilege.getName() + "</a>&nbsp;&nbsp; ");
					adminPrivilege = null;
				}
				
				for (CompanyManagerVO companyManagerVO : lists) {
					id = companyManagerVO.getId();
					htmls.append("<tr>");
					htmls.append("<td><input type=\"checkbox\" /></td>");
					htmls.append("<td>" + companyManagerVO.getCode() + "</td>");
					htmls.append("<td>" + companyManagerVO.getAccount() + "</td>");
					htmls.append("<td>" + companyManagerVO.getName() + "</td>");
					htmls.append("<td>" + companyManagerVO.getCompany_category_name() + "</td>");
					htmls.append("<td>" + companyManagerVO.getCompany_grade_name() + "</td>");
					htmls.append("<td>" + DateUtil.DateToString(companyManagerVO.getLogin_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
					
					htmls.append("<td class=\"is_delete\">" + DeleteConst.IS_DELETE[companyManagerVO.getIs_delete()] + "</td>");
					htmls.append("<td>" + DateUtil.DateToString(companyManagerVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
					htmls.append("<td ><input style=\"width: 50% !important\" maxlength=\"11\"data-url=\"/company/updateCompanyOrder.do\" type=\"text\" class=\"text-input\" name=\"order\" value=\""+companyManagerVO.getOrder()+"\" data-id=\""+id+"\"></td>");
					htmls.append("<td>");
					//// 循环
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE_STATUS)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE_STATUS);
					    htmls.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl()+"\" data-id=\""+id+"\" data-name=\"status\" data-status=\""+StatusConst.BUTTON_STATUS_VALUE[companyManagerVO.getStatus()]+"\" />"+StatusConst.BUTTON_STATUS[companyManagerVO.getStatus()]+"</a>&nbsp;");
					}
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
						htmls.append("<a class=\"button\" data-name=\"is_delete\" href=\"javascript:;\"  name=\"is_delete\" data-url=\""+adminPrivilege.getUrl()+"\" data-id=\""+companyManagerVO.getId()+"\" data-status=\""+DeleteConst.BUTTON_DELETE_VALUE[companyManagerVO.getIs_delete()]+"\" />"+DeleteConst.BUTTON_DELETE[companyManagerVO.getIs_delete()]+"</a>&nbsp;");
						adminPrivilege = null;
					}
					htmls.append(privilegeHtml.toString().replace(PlaceholderConst.ID, id));
					htmls.append("</td>");
					htmls.append("</tr>");
				}
				lists.clear();
				lists = null;
			} else {
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"10\">暂无数据</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			request.setAttribute("search_html",search_html);
			request.setAttribute("gradeHtml", CompanyUtil.getCompanyGradeSelect());
			request.setAttribute("companyManagerQuery", companyManagerQuery);
			request.setAttribute("categoryHtml", CompanyUtil.getCompanyCategorySelect());
			request.setAttribute("htmls", htmls);
			AdminLogUtil.insertLog("查看企业", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查看企业[findCompanyManagerVO]错误",e);
			throw e;
		}
		return "/company/index";
	}
   
    /**
	 * 更新企业状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompanyStatus",method = RequestMethod.POST)
	public String updateCompanyStatus(@ModelAttribute("companyUser")CompanyUser companyUser) throws Exception {
		JSONObject obj=new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			boolean flag = companyUserService.updateCompanyUser(companyUser);
			int status = companyUser.getStatus();
			obj.put("status", flag);
			obj.put("value",StatusConst.BUTTON_STATUS_VALUE[status]);
			obj.put("text",StatusConst.BUTTON_STATUS[status]);
			AdminLogUtil.insertLog("更新企业状态", getAdminUser(), LogEnum.UPDATE);
		} catch(Exception e){
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	/**
	 * 更新企业排序号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompanyOrder",method = RequestMethod.POST)
	public String updateCompanyOrder(@ModelAttribute("company")Company company) throws Exception {
		JSONObject obj=new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			companyServiceImpl.updateCompanyOrder(company);
			obj.put("status", ErrorConst.STATUS_SUCCESS);
			AdminLogUtil.insertLog("更新企业排序", getAdminUser(), LogEnum.UPDATE);
		} catch(Exception e){
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 新增企业
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertCompany",method = RequestMethod.POST)
	public String insertCompany(@ModelAttribute("insertCompanyManagerParam")InsertCompanyManagerParam insertCompanyManagerParam) throws Exception {
		try {
			String id = IDUtil.getID();
			insertCompanyManagerParam.setId(id);
			if (CooperatorConst.COOPERATOR_TYPE_COMPANY == insertCompanyManagerParam.getCooperatory_type()) {
				insertCompanyManagerParam.setSalesman_id(null);
			}
			companyServiceImpl.insertCompanyManager(insertCompanyManagerParam);
			AdminLogUtil.insertLog("插入企业登录信息", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/company/findCompany.do";
	}
	
	/**
	 * 删除企业
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteCompany",method = RequestMethod.POST)
	public String deleteCompany(@ModelAttribute("companyUser") CompanyUser companyUser) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyServiceImpl.deleteCompanyManager(companyUser);
			int status = companyUser.getIs_delete();
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
	
	/**
	 * 更新企业
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompanyPassword",method = RequestMethod.POST)
	public String updateCompanyPassword(@ModelAttribute("companyUser")CompanyUser companyUser) throws Exception {
		try {
			this.companyUserService.updateCompanyUser(companyUser);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/company/findCompany.do";
	}
	
	/**
	 * 编辑企业密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCompanyPassword",method = RequestMethod.GET)
	public String findCompanyPassword(@RequestParam(value="id",required=false) String id) throws Exception {
		try {
			request.setAttribute("id", id);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/company/update";
	}
	
	@RequestMapping(value = "/verifyCompany", method = RequestMethod.POST)
	public String verifyCompany(@RequestParam(value="account",required=false)String account,@RequestParam(value="name",required=false)String name) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = this.companyUserService.findCompanyUserByAccount(account);
			if(!flag){
				obj.put("name", "account");
				obj.put("message",AdminErrorConst.ADMIN_USER_2);
				return null;
			}
			flag= this.companyServiceImpl.findCompanyByName(name);
			if(!flag){
				obj.put("name", "name");
				obj.put("message",CompanyErrorConst.COMPANY_0);
				return null;
			}
			obj.put("status",ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("企业账户验证【verifyCompany】:发生错误!",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	@RequestMapping(value = "/findPinYinByName", method = RequestMethod.POST)
	public String findPinYinByName(@RequestParam(value="name",required=false)String name)throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			obj.put("name", PinyinUtil.getPinYin(name));
			obj.put("status", StatusConst.STATUS_TRUE);
		} catch (Exception e) {
			new YuleException("得到企业账户拼音【findPinYinByName】出现异常",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
