package com.yule.action.company;


import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.service.ICompanyCategoryService;
import com.yule.admin.service.ICompanyPrivilegeService;
import com.yule.common.BaseAction;
import com.yule.constant.DoMainConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.constant.ShowConst;
import com.yule.constant.StatusConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.CompanyCategory;
import com.yule.pojo.CompanyPrivilege;
import com.yule.runnable.CompanyPrivilegeRunnable;
import com.yule.util.AdminLogUtil;

@Controller
@Scope("prototype")
@RequestMapping("/companyPrivilege")
public class CompanyPrivilegeAction extends BaseAction{
	
	@Autowired
	private ICompanyPrivilegeService companyPrivilegeServiceImpl;
	@Autowired
	private ICompanyCategoryService companyCategoryService;
	
	@RequestMapping(value="/findCompanyPrivilege",method=RequestMethod.GET)
	public String findCompanyPrivilege(@RequestParam(value="companyCategoryId",required = false) String companyCategoryId,@RequestParam(value="parentId",required = false) String parentId,@RequestParam(value="gradeNo",required = false) Integer gradeNo) throws Exception{
		try {
			if (gradeNo == null) {
				gradeNo = 0;
			}
			List<CompanyCategory> companyCategorys = companyCategoryService.findCompanyCategoryList();
			StringBuffer companyCategoryHtmls = new StringBuffer();
			if(null != companyCategorys && companyCategorys.size()>0){
				companyCategoryHtmls.append("<form action=\"/companyPrivilege/findCompanyPrivilege.do\">");
				companyCategoryHtmls.append("<strong>????????????:</strong><select id = \"companyCategory\" name=\"companyCategoryId\" onchange=\"this.form.submit();\" class=\"small-input\" >");
				if(StringUtils.isEmpty(companyCategoryId)){
					companyCategoryId = "";
					companyCategoryHtmls.append("<option value=\"\" selected>????????????	</option>");
				}else{
					companyCategoryHtmls.append("<option value=\"\">????????????</option>");
				}
				for (CompanyCategory companyCategory : companyCategorys) {
					if(!StringUtils.isEmpty(companyCategoryId)&&companyCategoryId.equals(companyCategory.getId())){					
						companyCategoryHtmls.append("<option value=\""+companyCategory.getId()+"\"  selected >"+companyCategory.getName()+"</option>");
					}else{
						companyCategoryHtmls.append("<option value=\""+companyCategory.getId()+"\"  >"+companyCategory.getName()+"</option>");
					}
				}
				companyCategoryHtmls.append("</select>");
				companyCategoryHtmls.append("</form>");
				companyCategorys.clear();
				companyCategorys=null;
			}
			request.setAttribute("companyCategoryHtmls", companyCategoryHtmls);
			StringBuffer backHtml = new StringBuffer();
			String goBackPanrentId = null;
			if (!StringUtils.isEmpty(parentId)) {
				goBackPanrentId=companyPrivilegeServiceImpl.findCompanyPrivilegeById(parentId).getParent_id();
				if(null==goBackPanrentId){
					backHtml.append(" <a class=\"goback-btn button\" href=\"/companyPrivilege/findCompanyPrivilege.do?companyCategoryId="+companyCategoryId+"&gradeNo="+(gradeNo-1)+"\">???????????????</a>");
				}else{
					backHtml.append(" <a class=\"goback-btn button\" href=\"/companyPrivilege/findCompanyPrivilege.do?parentId="+goBackPanrentId+"&companyCategoryId="+companyCategoryId+"&gradeNo="+(gradeNo-1)+"\">???????????????</a>");
				}
			}
			request.setAttribute("backHtml", backHtml);
			List<CompanyPrivilege> list = this.companyPrivilegeServiceImpl.findCompanyPrivilegeList(companyCategoryId,parentId);
			AdminPrivilege adminPrivilege = null;
			//???????????????html
			StringBuffer insertHtmls = new StringBuffer();
			if(!StringUtils.isEmpty(companyCategoryId)){
				insertHtmls.append("<input type=\"hidden\"name=\"company_category_id\" value=\""+companyCategoryId+"\"/>&nbsp;&nbsp;");	
			}
			if(!StringUtils.isEmpty(parentId)){
				insertHtmls.append("<input type=\"hidden\"name=\"parent_id\" value=\""+parentId+"\"/>&nbsp;&nbsp;");
			}
			insertHtmls.append("<span>????????????:<input class=\"text-input small-input\" type=\"text\"name=\"name\" nullmsg=\"?????????????????????!\" datatype=\"\" errormsg=\"\" /></span>");
			Integer orderNum = 0;
			if (list != null && list.size() >0) {
				orderNum = list.size();
			}
			insertHtmls.append("<span>??????:<input class=\"text-input small-input\" type=\"text\"name=\"order\" value=\""+(orderNum)+"\" nullmsg=\"???????????????!\" datatype=\"n\" errormsg=\"?????????????????????\"/></span></br>");
			insertHtmls.append("<span>????????????:<input class=\"text-input small-input\" type=\"text\"name=\"url\" nullmsg=\"?????????????????????!\" datatype=\"\" errormsg=\"\"/></span>&nbsp;&nbsp");
			insertHtmls.append("<span>????????????:<select name=\"is_show\" id=\"is_show\"><option value=\"0\">??????</option><option value=\"1\">?????????</option></select></span>");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT);
				insertHtmls.append("<a  class=\"button\" href=\"javascript:;\" data-query-insert=\"\"  data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>&nbsp;&nbsp; ");
			}
			StringBuffer htmls = new StringBuffer();
			htmls.append("<tfoot><tr><td colspan=\"7\">");
			htmls.append("<div class=\"bulk-actions align-left\"></div>");
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody id=\"list\">");
			htmls.append("<tr data-progress=\"\" style=\"display:none;\">");
			htmls.append("<td  style=\"text-align: center;\" colspan=\"4\"><image src=\""+DoMainConst.ADMIN_STATIC_URL+"/images/loading2.gif\"/><td>");
			htmls.append("</tr>");
			StringBuffer rowHtmls = new StringBuffer(); 
			rowHtmls.append("<tr>");
			rowHtmls.append("<td>" + PlaceholderConst.NAME + "</td>");
			rowHtmls.append("<td>" + PlaceholderConst.URL + "</td>");
			rowHtmls.append("<td>" + PlaceholderConst.IS_SHOW + "</td>");
			rowHtmls.append("<td>" + PlaceholderConst.ORDER + "</td>");
			rowHtmls.append("<td>");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
				rowHtmls.append("<a  class=\"button\" href=\"javascript:;\" data-delete=\"\" data-id=\""+PlaceholderConst.ID+"_"+PlaceholderConst.CATEGORY_ID+"\" data-url=\""+adminPrivilege.getUrl()+"\" >??????</a>&nbsp;&nbsp; ");
			}
			rowHtmls.append("<a  class=\"button\"  href=\""+request.getServletPath()+"?parentId="+PlaceholderConst.ID+"&companyCategoryId="+PlaceholderConst.CATEGORY_ID+"&gradeNo="+(gradeNo-1)+"\" >"+"???????????????"+"</a>&nbsp;");
			rowHtmls.append("</td></tr>");
			if(list != null && list.size() > 0 ){
				for (CompanyPrivilege companyPrivilege : list) {
					if (companyPrivilege.getIs_show() == null) {
						companyPrivilege.setIs_show(StatusConst.STATUS_FALSE);
					}
					htmls.append(rowHtmls.toString().replace(PlaceholderConst.NAME, companyPrivilege.getName())
							.replace(PlaceholderConst.URL, companyPrivilege.getUrl())
							.replace(PlaceholderConst.ID, companyPrivilege.getId())
							.replace(PlaceholderConst.CATEGORY_ID, companyCategoryId)
							.replace(PlaceholderConst.IS_SHOW,ShowConst.IS_SHOW[companyPrivilege.getIs_show()])
							.replace(PlaceholderConst.ORDER, String.valueOf(companyPrivilege.getOrder()))
							);
				}
				list.clear();
				list = null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td class=\"td-center-style\" colspan=\"8\">????????????</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			request.setAttribute("insertHtmls", insertHtmls);
			request.setAttribute("htmls", htmls);
			request.setAttribute("rowHtmls", rowHtmls);
			request.setAttribute("gradeNo", gradeNo);
			insertHtmls = null;
			htmls = null;
			rowHtmls = null;
			companyCategoryHtmls = null;
			backHtml = null;
			AdminLogUtil.insertLog("????????????????????????", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("?????????????????????findCompanyPrivilege???????????????",e);
			throw e;
		}
		return "company/privilege/index";
	}
	@RequestMapping(value = "/insertCompanyPrivilege", method = RequestMethod.POST)
	public String insertCompanyPrivilege(@ModelAttribute("companyPrivilege")CompanyPrivilege companyPrivilege) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = this.companyPrivilegeServiceImpl.insertCompanyPrivilege(companyPrivilege);
			if (flag) {
				Thread t =new Thread(new CompanyPrivilegeRunnable(companyPrivilege.getCompany_category_id(), CompanyPrivilegeRunnable.IS_CATEGORY));
				t.start();
			}
			obj.put("status", flag);
			obj.put("id", companyPrivilege.getId());
			if (StringUtils.isEmpty(companyPrivilege.getCompany_category_id())) {
				obj.put("categoryId","");	
			}else{
				obj.put("categoryId", companyPrivilege.getCompany_category_id());
			}
			
			obj.put("is_show_text", ShowConst.IS_SHOW[companyPrivilege.getIs_show()]);
			
			AdminLogUtil.insertLog("?????????????????????", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("????????????????????????insertCompanyPrivilege???????????????",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	@RequestMapping(value="/deleteCompanyPrivilege",method = RequestMethod.POST)
	public String deleteCompanyPrivilege(@RequestParam(value="id",required=false)String id)throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			String[] ids = id.split("_");
			StringBuffer sb = new StringBuffer();
			if (ids.length <= 1) {
				sb.append("");
			}else{
				sb.append(ids[1]);
			}
			boolean flag = this.companyPrivilegeServiceImpl.deleteCompanyPrivilegeById(ids[0]);
			ids = null;
			if (flag) {
				Thread t =new Thread(new CompanyPrivilegeRunnable(sb.toString(), CompanyPrivilegeRunnable.IS_CATEGORY));
				t.start();
			}
			sb.setLength(0);
			obj.put("status", flag);
			AdminLogUtil.insertLog("?????????????????????", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("????????????????????????deleteCompanyPrivilege???????????????",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
}
