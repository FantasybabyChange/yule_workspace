package com.yule.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.yule.constant.JSONConst;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.vo.CompanyCategoryVO;
import com.yule.vo.CompanyGradeVO;
import com.yule.vo.CompanyPointCategoryVO;


public class SearchCriteriaUtil {
	
	@SuppressWarnings("unchecked")
	public static String getCompanyCategory() throws Exception{
		List<CompanyCategoryVO> companyCategoryVOs = new ArrayList<CompanyCategoryVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_CATEGORY)){
			companyCategoryVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_CATEGORY)),new CompanyCategoryVO(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		if(companyCategoryVOs.size()>0){
			htmls.append("<div class=\"advanced-search-item\">");
			htmls.append("<div class=\"advanced-search-item-title\">娱乐类型</div>");
			htmls.append("<ul class=\"advanced-search-item-list\">");
			for(CompanyCategoryVO companyCategoryVO : companyCategoryVOs){
				htmls.append("<li class=\"b_checkbox\">");
				htmls.append("<label class=\"b_checkbox_label\">");
				htmls.append("<input type=\"checkbox\" name=\"company_category\" value=\""+companyCategoryVO.getId()+"\" class=\"b_checkbox_selector\">"+companyCategoryVO.getName());
				htmls.append("</label>");
				htmls.append("</li>");
			}
			htmls.append("</ul>");
			htmls.append("</div>");
			companyCategoryVOs.clear();
		}
		return htmls.toString();
	}

	@SuppressWarnings("unchecked")
	public static String getCompanyGrade() throws Exception{
		List<CompanyGradeVO> companyGradeVOs = new ArrayList<CompanyGradeVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_GRADE)){
			companyGradeVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_GRADE)),new CompanyGradeVO(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		if(companyGradeVOs.size()>0){
			htmls.append("<div class=\"advanced-search-item\">");
			htmls.append("<div class=\"advanced-search-item-title\">娱乐档次</div>");
			htmls.append("<ul class=\"advanced-search-item-list\">");
			for(CompanyGradeVO companyGradeVO : companyGradeVOs){
				htmls.append("<li class=\"b_checkbox\">");
				htmls.append("<label class=\"b_checkbox_label\">");
				htmls.append("<input type=\"checkbox\" name=\"company_grade\" value=\""+companyGradeVO.getId()+"\" class=\"b_checkbox_selector\">"+companyGradeVO.getName());
				htmls.append("</label>");
				htmls.append("</li>");
			}
			htmls.append("</ul>");
			htmls.append("</div>");
			companyGradeVOs.clear();
		}
		return htmls.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String getCompanyPointCategory() throws Exception{
		List<CompanyPointCategoryVO> companyPointCategoryVOs = new ArrayList<CompanyPointCategoryVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_POINT_CATEGORY)){
			companyPointCategoryVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_POINT_CATEGORY)),new CompanyPointCategoryVO(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		if(companyPointCategoryVOs.size()>0){
			htmls.append("<div class=\"advanced-search-item\">");
			htmls.append("<div class=\"advanced-search-item-title\">娱乐档次</div>");
			htmls.append("<ul class=\"advanced-search-item-list\">");
			for(CompanyPointCategoryVO companyPointCategoryVO : companyPointCategoryVOs){
				htmls.append("<li class=\"b_checkbox\">");
				htmls.append("<label class=\"b_checkbox_label\">");
				htmls.append("<input type=\"radio\" name=\"company_point_category\" value=\""+companyPointCategoryVO.getPoint()+"\" class=\"b_checkbox_selector\">"+companyPointCategoryVO.getName()+"("+companyPointCategoryVO.getPoint()+"分以上)");
				htmls.append("</label>");
				htmls.append("</li>");
			}
			htmls.append("</ul>");
			htmls.append("</div>");
			companyPointCategoryVOs.clear();
		}
		return htmls.toString();
	}
	
}
