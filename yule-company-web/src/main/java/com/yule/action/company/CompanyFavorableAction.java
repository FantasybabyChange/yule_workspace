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

import com.yule.common.BaseAction;
import com.yule.company.service.ICompanyFavorableService;
import com.yule.constant.PlaceholderConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.param.InsertCompanyFavorableParam;
import com.yule.pojo.CompanyFavorable;
import com.yule.util.CompanyLogUtil;

@Controller
@Scope("prototype")
@RequestMapping("/companyFavorable")
public class CompanyFavorableAction extends BaseAction{
	
	@Autowired
	private ICompanyFavorableService companyFavorableServiceImpl;
	
	@RequestMapping(value="findCompanyFavorable",method=RequestMethod.GET)
	public String findCompanyFavorable() throws Exception{
		try {
			 StringBuffer htmls = new StringBuffer("");
			 List<CompanyFavorable> listsCompanyFavorable = this.companyFavorableServiceImpl.findCompanyFavorableByCompanyId(getCompanyUser().getCompany_id());
			 if(null != listsCompanyFavorable && listsCompanyFavorable.size()>0){
				 for (CompanyFavorable companyFavorable : listsCompanyFavorable) {
					 htmls.append("<tr><td><input id=\"id\" type=\"hidden\" name=\"id\" value=\""+companyFavorable.getId()+"\" />");
					 htmls.append("<p><input type=\"text\"  name=\"name\"  nullmsg=\"请输入优惠名称!\" datatype=\"\" errormsg=\"\"  value=\""+companyFavorable.getName()+"\"/></p></td>");
					 htmls.append("<td><p><input type=\"text\"  name=\"price\"  nullmsg=\"请输入价格!\" datatype=\"n\" errormsg=\"\"  value=\""+companyFavorable.getPrice()+"\"/></p></td>");
					 htmls.append("<td><p><input type=\"text\"  name=\"content\"  nullmsg=\"请输入优惠内容!\" datatype=\"\" errormsg=\"\" value=\""+companyFavorable.getContent()+"\"/></p></td>");
					 htmls.append("<td><div>");
					 htmls.append("<button type=\"button\"  class=\"btn btn-xs btn-success\" data-url=\"/companyFavorable/updateCompanyFavorable.do\" data-update=\"\" data-id=\"" + companyFavorable.getId() + "\">更新</button>&nbsp;");
					 htmls.append("<button type=\"button\"  class=\"btn btn-xs btn-success\" data-url=\"/companyFavorable/deleteCompanyFavorable.do\" data-delete=\"\" data-id=\"" + companyFavorable.getId() + "\">删除</button>&nbsp;");
					 htmls.append("</div></td>");
					 htmls.append("</tr>");
				 }
			 }else {
					htmls.append("<tr>");
					htmls.append("<td style=\"text-align: center;\" colspan=\"4\">暂无数据(点击新增一行添加数据)</td>");
					htmls.append("</tr>");
			}
			 StringBuffer rowHtmls =new StringBuffer("");
			 rowHtmls.append("<tr><td><input id=\"id\" type=\"hidden\" name=\"id\" value=\""+PlaceholderConst.ID+"\" />");
			 rowHtmls.append("<p><input type=\"text\"  name=\"name\"  nullmsg=\"请输入优惠名称!\" datatype=\"\" errormsg=\"\"  value=\""+PlaceholderConst.NAME+"\"/></p></td>");
			 rowHtmls.append("<td><p><input type=\"text\"  name=\"price\"  nullmsg=\"请输入价格!\" datatype=\"n\" errormsg=\"\"  value=\""+PlaceholderConst.PRICE+"\"/></p></td>");
			 rowHtmls.append("<td><p><input type=\"text\"  name=\"content\"  nullmsg=\"请输入优惠内容!\" datatype=\"\" errormsg=\"\" value=\""+PlaceholderConst.CONTENT+"\"/></p></td>");
			 rowHtmls.append("<td><div>");
			 rowHtmls.append("<button type=\"button\" class=\"btn btn-xs btn-success\" data-url=\"/companyFavorable/updateCompanyFavorable.do\" data-update=\"\" data-id=\"" + PlaceholderConst.ID  + "\">更新</button>&nbsp;");
			 rowHtmls.append("<button type=\"button\" class=\"btn btn-xs btn-success\" data-url=\"/companyFavorable/deleteCompanyFavorable.do\" data-delete=\"\" data-id=\"" +PlaceholderConst.ID + "\">删除</button>");
			 rowHtmls.append("</div></td>");
			 rowHtmls.append("</tr>");
			 request.setAttribute("rowHtml",rowHtmls);
			 request.setAttribute("htmls",htmls);
			CompanyLogUtil.insertLog("查询企业优惠政策", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查询企业优惠政策[findCompanyFavorable]错误",e);
			throw e;
		} 
		return "/company/favorable/index";
	}
	
	@RequestMapping(value = "/batchUpdateCompanyFavorable",method = RequestMethod.POST)
	public String batchUpdateCompanyFavorable(InsertCompanyFavorableParam companyFavorableQuery,@RequestParam(value="companyId",required=false)String companyId) throws Exception {
		try {
			this.companyFavorableServiceImpl.batchUpdateCompanyFavorable(companyFavorableQuery);
			CompanyLogUtil.insertLog("批量更新企业优惠政策", getCompanyUser(), LogEnum.BATCH);
		} catch (Exception e) {
			new YuleException("批量更新企业优惠政策[batchUpdateCompanyFavorable]错误",e);
			throw e;
		}  
		return null;
	}
	
	@RequestMapping(value="updateCompanyFavorable",method=RequestMethod.POST)
	public String updateCompanyFavorable(@ModelAttribute("companyFavorable")CompanyFavorable companyFavorable) throws Exception{
		JSONObject obj = new JSONObject();
		try {
			boolean flag = companyFavorableServiceImpl.updateCompanyFavorable(companyFavorable);
			obj.put("status", flag);
			CompanyLogUtil.insertLog("批量更新企业优惠政策", getCompanyUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新企业优惠政策[updateCompanyFavorable]错误",e);
			throw e;
		}  finally{
			outputResult(obj.toString());
			obj.clear();
			obj = null;
		}
		return null;
	}
	
	@RequestMapping(value="insertCompanyFavorable",method=RequestMethod.POST)
	public String insertCompanyFavorable(@ModelAttribute("companyFavorable")CompanyFavorable companyFavorable) throws Exception{
		JSONObject obj = new JSONObject();
		try {
			companyFavorable.setCompany_id(getCompanyUser().getCompany_id());
			boolean flag = companyFavorableServiceImpl.insertCompanyFavorable(companyFavorable);
			obj.put("id", companyFavorable.getId());
			obj.put("status", flag);
			CompanyLogUtil.insertLog("新增企业优惠政策", getCompanyUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("新增企业优惠政策[insertCompanyFavorable]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
			obj.clear();
			obj = null;
		}
		return null;
	}
	
	@RequestMapping(value="deleteCompanyFavorable",method=RequestMethod.POST)
	public String deleteCompanyFavorable(@RequestParam(value="id",required=false) String id) throws Exception{
		JSONObject obj = new JSONObject();
		try {
			boolean flag = companyFavorableServiceImpl.deleteCompanyFavorableById(id);
			obj.put("status", flag);
			CompanyLogUtil.insertLog("删除企业优惠政策", getCompanyUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除企业优惠政策[deleteCompanyFavorable]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
			obj.clear();
			obj = null;
		}
		return null;
	}
	
	
}
