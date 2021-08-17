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
import com.yule.common.BaseAction;
import com.yule.company.service.ICompanyPhoneService;
import com.yule.constant.CompanyPhoneConst;
import com.yule.constant.PlaceholderConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPhone;
import com.yule.util.CompanyLogUtil;



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
	public String findCompanyPhoneList() throws Exception {
		try {
			List<CompanyPhone> lists = companyPhoneServiceImpl.findCompanyPhoneListByCompanyId(getCompanyUser().getCompany_id());
			StringBuffer htmls = new StringBuffer("");
			if(null!=lists&&lists.size()>0){
				StringBuffer id = new StringBuffer();
				for(CompanyPhone companyPhone:lists) {
					id.append(companyPhone.getId());
					htmls.append("<tr><td><input id=\"id\" type=\"hidden\" name=\"id\" value=\""+id+"\" />");
					htmls.append("<p><input type=\"text\"  name=\"name\"  nullmsg=\"请输入优惠名称!\" datatype=\"\" errormsg=\"\"  value=\""+companyPhone.getName()+"\"/></p></td>");
					htmls.append("<td><p><input type=\"text\"  name=\"phone\"  nullmsg=\"请输入价格!\" datatype=\"n\" errormsg=\"\"  value=\""+companyPhone.getPhone()+"\"/></p></td>");
					htmls.append("<td>"+CompanyPhoneConst.company_phone_type[companyPhone.getType()]+"</td>");
					htmls.append("<td><div>");
					htmls.append("<button type=\"button\" class=\"btn btn-xs btn-success\"  data-update=\"\" data-id=\""+id+"\" data-url=\""+"/companyPhone/updateCompanyPhone.do"+"\">"+"更新"+"</button>&nbsp;");
					htmls.append("<button  type=\"button\" class=\"btn btn-xs btn-success\"  data-id=\""+id+"\" data-url=\""+"/companyPhone/deleteCompanyPhone.do"+"\" data-delete=\"\" >删除</button>");
					htmls.append("</div></td>");
					htmls.append("</tr>");
					id.setLength(0);
				}
				lists.clear();
				lists=null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"4\">暂无数据(点击新增一行添加数据)</td>");
				htmls.append("</tr>");
			}
			StringBuffer rowHtml = new StringBuffer("");
			rowHtml.append("<tr><td><input id=\"id\" type=\"hidden\" name=\"id\" value=\""+PlaceholderConst.ID+"\" />");
			rowHtml.append("<input class=\"text-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入名称!\" datatype=\"\" errormsg=\"\"   value=\""+PlaceholderConst.NAME+"\"/></td>");
			rowHtml.append("<td><input class=\"text-input\" type=\"text\"  name=\"phone\"  nullmsg=\"请输入电话!\" datatype=\"phone\" errormsg=\"\"  value=\""+PlaceholderConst.PHONE+"\"/> </td>");
			rowHtml.append("<td>"+PlaceholderConst.TYPE+"</td>");
			rowHtml.append("<td><div>");
			rowHtml.append("<button type=\"button\" class=\"btn btn-xs btn-success\"   data-update=\"\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+"/companyPhone/updateCompanyPhone.do"+"\">"+"更新"+"</button>&nbsp;");
			rowHtml.append("<button type=\"button\" class=\"btn btn-xs btn-success\"  data-id=\""+PlaceholderConst.ID+"\" data-url=\""+ "/companyPhone/deleteCompanyPhone.do"+"\" data-delete=\"\" >删除</button>");
			rowHtml.append("</div></td>");
			rowHtml.append("</tr>");
			
			request.setAttribute("rowHtml", rowHtml);
			request.setAttribute("htmls", htmls);
			CompanyLogUtil.insertLog("查询企业联系方式", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查询企业联系方式[findCompanyPhone]错误",e);
			throw e;
		} 
		return "/company/phone/index";
	}
	
	@RequestMapping(value = "/batchUpdateCompanyPhone",method = RequestMethod.POST)
	public String batchUpdateCompanyPhone(InsertCompanyPhoneParam insertCompanyPhoneParam) throws Exception {
		try {
			this.companyPhoneServiceImpl.batchUpdateCompanyPhone(insertCompanyPhoneParam);
			CompanyLogUtil.insertLog("批量更新企业联系方式", getCompanyUser(), LogEnum.BATCH);
		} catch (Exception e) {
			new YuleException("批量更新企业联系方式[batchUpdateCompanyPhone]错误",e);
			throw e;
		} 
		return null;
	}
	
	/**
	 * 添加企业联系方式
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertCompanyPhone",method = RequestMethod.POST)
	public String insertCompanyPhone(@ModelAttribute("companyPhone")CompanyPhone companyPhone) throws Exception {
		JSONObject obj = new JSONObject();
		try {
			companyPhone.setCompany_id(getCompanyUser().getCompany_id());
			boolean flag = companyPhoneServiceImpl.insertCompanyPhone(companyPhone);
			obj.put("id", companyPhone.getId());
			obj.put("status", flag);
			CompanyLogUtil.insertLog("新增企业联系方式", getCompanyUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("新增企业联系方式[insertCompanyPhone]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
			obj.clear();
			obj = null;
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
		try {
			boolean flag = companyPhoneServiceImpl.deleteCompanyPhoneById(id);
			obj.put("status", flag);
			CompanyLogUtil.insertLog("删除企业联系方式", getCompanyUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除企业联系方式[deleteCompanyPhone]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
			obj.clear();
			obj = null;
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
		try {
			boolean flag = companyPhoneServiceImpl.updateCompanyPhone(companyPhone);
			obj.put("status", flag);
			CompanyLogUtil.insertLog("更新企业联系方式", getCompanyUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新企业联系方式[updateCompanyPhone]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
			obj.clear();
			obj = null;
		}
		return null;
	}
	
}
