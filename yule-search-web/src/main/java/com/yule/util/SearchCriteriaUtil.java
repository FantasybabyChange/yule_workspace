package com.yule.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.yule.constant.JSONConst;
import com.yule.pojo.AreaBusiness;
import com.yule.pojo.AreaCounty;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.search.query.CompanySearchQuery;
import com.yule.vo.CompanyCategoryVO;
import com.yule.vo.CompanyGradeVO;
import com.yule.vo.CompanyPointCategoryVO;


public class SearchCriteriaUtil {
	
	@SuppressWarnings("unchecked")
	public static String getCompanyCategory(CompanySearchQuery companyQuery) throws Exception{
		List<CompanyCategoryVO> companyCategoryVOs = new ArrayList<CompanyCategoryVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_CATEGORY)){
			companyCategoryVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_CATEGORY)),new CompanyCategoryVO(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		htmls.append("<div class=\"filterbox\">");
		htmls.append("<div data-trigger=\"collapse\" class=\"filterbox-title active\"><i class=\"arrow_icon use_sprites\"></i>娱乐类型分类</div>");
		htmls.append("<div class=\"collapsible\" style=\"display: none;\">");
		if(companyCategoryVOs.size()>0){
			for(CompanyCategoryVO companyCategoryVO : companyCategoryVOs){
				boolean flag= false;
				List<String> strs = companyQuery.getCompany_category();
				if (null!=strs&&strs.size()>0) {
					for(String str :strs){
						if (companyCategoryVO.getId().equals(str)) {
							flag = true;
							break;
						}
					}
				} 
				
				if (flag) {
					htmls.append("<a query=\"\" href=\"javascript:;\" class=\"filterelement active\">");
					htmls.append("<div class=\"filter_item css-checkbox\">");
					htmls.append("<input type=\"checkbox\" data-query=\"\" checked=\"checked\" name=\"company_category\" value=\""+companyCategoryVO.getId()+"\" class=\"filter_label\">"+companyCategoryVO.getName());
					htmls.append("</div>");
					htmls.append("</a>");
				} else {
					htmls.append("<a query=\"\" href=\"javascript:;\" class=\"filterelement\">");
					htmls.append("<div class=\"filter_item css-checkbox\">");
					htmls.append("<input type=\"checkbox\" data-query=\"\" name=\"company_category\" value=\""+companyCategoryVO.getId()+"\" class=\"filter_label\">"+companyCategoryVO.getName());
					htmls.append("</div>");
					htmls.append("</a>");
				}
			}
			companyCategoryVOs.clear();
		}
		htmls.append("</div>");
		htmls.append("</div>");
		return htmls.toString();
	}

	@SuppressWarnings("unchecked")
	public static String getCompanyGrade(CompanySearchQuery companyQuery) throws Exception{
		List<CompanyGradeVO> companyGradeVOs= new ArrayList<CompanyGradeVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_GRADE)){
			companyGradeVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_GRADE)),new CompanyGradeVO(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		htmls.append("<div class=\"filterbox\" >");
		htmls.append("<div data-trigger=\"collapse\" class=\"filterbox-title active\"><i class=\"arrow_icon use_sprites\"></i>娱乐档次</div>");
		htmls.append("<div class=\"collapsible\" style=\"display: block;\">");
		if(companyGradeVOs.size()>0){
			for(CompanyGradeVO  companyGradeVO : companyGradeVOs){
				boolean flag= false;
					List<String> strs = companyQuery.getCompany_grade();
					if (null!=strs&&strs.size()>0) {
						for(String str :strs){
							if (companyGradeVO.getId().equals(str)) {
								flag = true;
								break;
							}
						}
				}
				if (flag) {
					htmls.append("<a query=\"\" href=\"javascript:;\" class=\"filterelement active\">");
					htmls.append("<div class=\"filter_item css-checkbox\">");
					htmls.append("<input type=\"checkbox\" data-query=\"\" checked=\"checked\" name=\"company_grade\" value=\""+companyGradeVO.getId()+"\" class=\"filter_label\">"+companyGradeVO.getName());
					htmls.append("</div>");
					htmls.append("</a>");
				}else  {
					htmls.append("<a query=\"\" href=\"javascript:;\" class=\"filterelement\">");
					htmls.append("<div class=\"filter_item css-checkbox\">");
					htmls.append("<input type=\"checkbox\" data-query=\"\" name=\"company_grade\" value=\""+companyGradeVO.getId()+"\" class=\"filter_label\">"+companyGradeVO.getName());
					htmls.append("</div>");
					htmls.append("</a>");
				}
			}
			companyGradeVOs.clear();
		}
		htmls.append("</div>");
		htmls.append("</div>");
		return htmls.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String getCompanyPointCategory(CompanySearchQuery companyQuery) throws Exception{
		List<CompanyPointCategoryVO> companyPointCategoryVOs = new ArrayList<CompanyPointCategoryVO>() ;
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_POINT_CATEGORY)){
			companyPointCategoryVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_POINT_CATEGORY)),new CompanyPointCategoryVO(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		htmls.append("<div class=\"filterbox\" >");
		htmls.append("<div data-trigger=\"collapse\" class=\"filterbox-title active\"><i class=\"arrow_icon use_sprites\"></i>企业评分分类</div>");
		htmls.append("<div class=\"collapsible\" style=\"display: none;\">");
		htmls.append("<a query=\"\" href=\"javascript:;\" class=\"filterelement\">");
		htmls.append("<div class=\"filter_item css-checkbox\">");
		htmls.append("<input type=\"radio\" name=\"company_point_category\" value=\"\" class=\"filter_label\">所有");
		htmls.append("</div>");
		htmls.append("</a>");	
		if(companyPointCategoryVOs.size()>0){
			for(CompanyPointCategoryVO companyPointCategoryVO : companyPointCategoryVOs){
				if (null!=companyQuery.getCompany_point_category()) {
					double str = companyQuery.getCompany_point_category();
					if (str==Double.valueOf(companyPointCategoryVO.getPoint())) {
						htmls.append("<a query=\"\" href=\"javascript:;\" class=\"filterelement active\">");
						htmls.append("<div class=\"filter_item css-checkbox\">");
						htmls.append("<input type=\"radio\" data-query=\"\" checked=\"checked\" name=\"company_point_category\" value=\""+companyPointCategoryVO.getPoint()+"\" class=\"filter_label\">"+companyPointCategoryVO.getName()+" "+companyPointCategoryVO.getPoint()+"分");
						htmls.append("</div>");
						htmls.append("</a>");
					}else{
						htmls.append("<a  query=\"\" href=\"javascript:;\" class=\"filterelement\">");
						htmls.append("<div class=\"filter_item css-checkbox\">");
						htmls.append("<input type=\"radio\" data-query=\"\" name=\"company_point_category\" value=\""+companyPointCategoryVO.getPoint()+"\" class=\"filter_label\">"+companyPointCategoryVO.getName()+" "+companyPointCategoryVO.getPoint()+"分");
						htmls.append("</div>");
						htmls.append("</a>");					
					}
				}else{
					htmls.append("<a query=\"\" href=\"javascript:;\" class=\"filterelement\">");
					htmls.append("<div class=\"filter_item css-checkbox\">");
					htmls.append("<input type=\"radio\" data-query=\"\" name=\"company_point_category\" value=\""+companyPointCategoryVO.getPoint()+"\" class=\"filter_label\">"+companyPointCategoryVO.getName()+" "+companyPointCategoryVO.getPoint()+"分");
					htmls.append("</div>");
					htmls.append("</a>");		
				}

			}
			companyPointCategoryVOs.clear();
		}
		htmls.append("</div>");
		htmls.append("</div>");
		return htmls.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String getCompanyAreaCounty(CompanySearchQuery companyQuery) throws Exception{
		List<AreaCounty> areaCountys = new ArrayList<AreaCounty>();
		if(JedisUtil.getInstance().exists(RedisConst.AREA_COUNTY+companyQuery.getArea_city_id())){
			areaCountys.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_COUNTY+companyQuery.getArea_city_id())),new AreaCounty(),JSONConst.JSON_CONFIG));
		}
		String area_county = null;
		if (null!=companyQuery.getArea_county()&&companyQuery.getArea_county().size()>0) {
			area_county= companyQuery.getArea_county().get(0);
		}
		
		StringBuffer htmls = new StringBuffer();
		htmls.append("<div class=\"filterbox\"  >");
		htmls.append("<div data-trigger=\"collapse\" class=\"filterbox-title  active\"><i class=\"arrow_icon use_sprites\"></i>"+"所在地区"+"</div>");
		htmls.append("<div class=\"collapsible\" style=\"display: none;\">");
		if(areaCountys.size()>0){
			for(AreaCounty areaCounty : areaCountys){
				if (null!=areaCounty) {
					if (areaCounty.getId().equals(area_county)) {
						htmls.append("<a query=\"\" href=\"javascript:;\" class=\"filterelement active\">");
						htmls.append("<div class=\"filter_item css-checkbox\">");
						htmls.append("<input type=\"checkbox\" name=\"area_county\" checked=\"checked\"  value=\""+areaCounty.getId()+"\" class=\"filter_label\">"+areaCounty.getName());
						htmls.append("</div>");
						htmls.append("</a>");
					} else {
						htmls.append("<a query=\"\" href=\"javascript:;\" class=\"filterelement\">");
						htmls.append("<div class=\"filter_item css-checkbox\">");
						htmls.append("<input type=\"checkbox\" name=\"area_county\" value=\""+areaCounty.getId()+"\" class=\"filter_label\">"+areaCounty.getName());
						htmls.append("</div>");
						htmls.append("</a>");
					}
				}
			}
			areaCountys.clear();
		}
		htmls.append("</div>");
		htmls.append("</div>");
		return htmls.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String getCompanyAreaBusiness(String area_city) throws Exception{
		List<AreaBusiness> areaBusinesses = new ArrayList<AreaBusiness>();
		if(JedisUtil.getInstance().exists(RedisConst.AREA_BUSINESS+area_city)){
			areaBusinesses.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_BUSINESS+area_city)),new AreaBusiness(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		htmls.append("<div class=\"filterbox\"  >");
		htmls.append("<div data-trigger=\"collapse\" class=\"filterbox-title  active\"><i class=\"arrow_icon use_sprites\"></i>"+"热门商区"+"</div>");
		htmls.append("<div class=\"collapsible\" style=\"display: none;\">");
		if(areaBusinesses.size()>0){
			for(AreaBusiness areaBusiness : areaBusinesses){
				if (null!=areaBusiness) {
					htmls.append("<a query=\"\" href=\"javascript:;\" class=\"filterelement\">");
					htmls.append("<div class=\"filter_item css-checkbox\">");
					htmls.append("<input type=\"checkbox\" name=\"area_business\" value=\""+areaBusiness.getId()+"\" class=\"filter_label\">"+areaBusiness.getName());
					htmls.append("</div>");
					htmls.append("</a>");
				}
			}
			areaBusinesses.clear();
		}
		htmls.append("</div>");
		htmls.append("</div>");
		return htmls.toString();
	}
}
