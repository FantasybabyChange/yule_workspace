package com.yule.action.company;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.PageConst;
import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.salesman.query.CompanyManagerQuery;
import com.yule.salesman.service.ICompanyService;
import com.yule.util.PaginationUtil;
import com.yule.vo.CompanyManagerVO;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/company")
public class CompanyAction extends BaseAction{
	
	@Autowired
	private ICompanyService companyServiceImpl;
	@RequestMapping(value = "/findCompanyInfo")
	public String findCompanyInfo(CompanyManagerQuery companyManagerQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo)throws Exception{
		if(null==pageNo){
			return "/company/index";
		}
		if(null==companyManagerQuery){
			companyManagerQuery = new CompanyManagerQuery();
		}
		companyManagerQuery.setSalesman_id(getCookieValue());
		JSONObject obj = new JSONObject();
		try {
			Page<CompanyManagerVO> page = this.companyServiceImpl.findCompanyManagerVOPage(companyManagerQuery, PageConst.PAGE_SIZE_TEN, pageNo);
			StringBuffer tfoot = new StringBuffer();
			StringBuffer tbody = new StringBuffer();
			tfoot.append("<tr><td colspan=\"7\">");
			tfoot.append("<div class=\"bulk-actions align-left\"></div>");
			tfoot.append(PaginationUtil.getPaginationHtml(page));
			tfoot.append("<div class=\"clear\"></div>");
			tfoot.append("</td></tr>");
			if(page.getRowCount()>0){
				for(CompanyManagerVO companyManagerVO: page.getDatas()){
					tbody.append("<tr>");
					tbody.append("<td>" + companyManagerVO.getName() + "</td>");
					tbody.append("<td>" + companyManagerVO.getCompany_category_name() + "</td>");
					tbody.append("<td>" + companyManagerVO.getCompany_grade_name() + "</td>");
					tbody.append("<td>" + companyManagerVO.getCommision() + "</td>");
					if(companyManagerVO.getRebast() == null || companyManagerVO.getRebast() == 0){
						tbody.append("<td>优惠价</td>");	
					}else{
						tbody.append("<td>" + companyManagerVO.getRebast() + "</td>");
					}
					
					tbody.append("</td>");
					tbody.append("</tr>");					
				}
			}else{
				tbody.append("<tr>");
				tbody.append("<td colspan=\"7\">没有数据</td>");
				tbody.append("</tr>");	
			}
			obj.put("tfoot", tfoot.toString());
			obj.put("tbody", tbody.toString());
			outputResult(obj.toString());
			tfoot.setLength(0);
			tbody.setLength(0);
			obj.clear();
			obj = null;
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return null;
	}
	
	@RequestMapping(value = "/findCompanyList")
	public String findCompanyList() throws Exception{
		JSONObject  obj = new JSONObject();
		try {
			List<Company> companies = this.companyServiceImpl.findCompanyList(getCookieValue());
			if (null!=companies&&companies.size()>0) {
				JSONArray array = new JSONArray();
				JSONObject object = null;
				for (Company company:companies) {
					object = new JSONObject();
					object.put("label", company.getName());
					object.put("id", company.getId());
					object.put("pinyin", company.getPinyin());
					object.put("commision",company.getCommision());
					array.add(object);
				}
				obj.put("companies", array);
				companies.clear();
				companies = null;
				object.clear();
				object = null;
				array.clear();
				array = null;
			}
		} catch (Exception e) {
			new YuleException(e);	
		}finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
		}
		return null;
	}
}
