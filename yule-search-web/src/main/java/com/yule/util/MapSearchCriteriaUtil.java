package com.yule.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.yule.constant.JSONConst;
import com.yule.memcached.constant.MemcachedConst;
import com.yule.memcached.util.MemcachedUtil;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.vo.CompanyCategoryVO;
import com.yule.vo.CompanyGradeVO;
import com.yule.vo.CompanyPointCategoryVO;


public class MapSearchCriteriaUtil {
	@SuppressWarnings("unchecked")
	public static StringBuffer getCompanyCategory(List<String> companyCategory,StringBuffer names) throws Exception{
		List<CompanyCategoryVO> companyCategoryVOs = new ArrayList<CompanyCategoryVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_CATEGORY)){
			companyCategoryVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_CATEGORY)),new CompanyCategoryVO(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		if(companyCategoryVOs.size()>0){
			for(CompanyCategoryVO companyCategoryVO : companyCategoryVOs){
				htmls.append("<li style=\"cursor:pointer;\" check-li=\"category\">");
				htmls.append("<input name=\"company_category\" type=\"checkbox\" ");
				if (companyCategory != null) {
					if(companyCategory.contains(companyCategoryVO.getId())){
						htmls.append(" checked ");
						names.append(companyCategoryVO.getName()+",");
					}
				}
				htmls.append("data-value=\""+companyCategoryVO.getName()+"\"value=\""+companyCategoryVO.getId()+"\">"+companyCategoryVO.getName());
				htmls.append("</li>");
			}
			companyCategoryVOs.clear();
		}
		return htmls;
	}
	@SuppressWarnings("unchecked")
	public static StringBuffer getCompanyGrade(List<String> companyGrades,StringBuffer names) throws Exception{
		List<CompanyGradeVO> companyGradeVOs= new ArrayList<CompanyGradeVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_GRADE)){
			companyGradeVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_GRADE)),new CompanyGradeVO(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		if(companyGradeVOs.size()>0){
			for(CompanyGradeVO  companyGradeVO : companyGradeVOs){
				htmls.append("<li style=\"cursor:pointer;\" check-li=\"grade\">");
				htmls.append("<input name=\"company_grade\" type=\"checkbox\"" );
				if (companyGrades != null) {
					if(companyGrades.contains(companyGradeVO.getId())){
						htmls.append(" checked ");
						names.append(companyGradeVO.getName()+",");
					}
				}
				htmls.append(" data-value=\""+companyGradeVO.getName()+"\" value=\""+companyGradeVO.getId()+"\">"+companyGradeVO.getName()+"");
				htmls.append("</li>");
			}
			companyGradeVOs.clear();
		}
		return htmls;
	}
	@SuppressWarnings("unchecked")
	public static StringBuffer getCompanyPointCategory(Double companyPointValue,StringBuffer names) throws Exception{
		List<CompanyPointCategoryVO> companyPointCategoryVOs = new ArrayList<CompanyPointCategoryVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_POINT_CATEGORY)){
			companyPointCategoryVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_POINT_CATEGORY)),new CompanyPointCategoryVO(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		if(companyPointCategoryVOs.size()>0){
			htmls.append(" <li style=\"cursor:pointer;\" check-li=\"pointcategory\">");
			htmls.append("<input name=\"company_point_category\" type=\"radio\" data-value=\"不限\"value=\"\">不限");
			htmls.append("</li>");
			for(CompanyPointCategoryVO companyPointCategoryVO : companyPointCategoryVOs){
				htmls.append(" <li style=\"cursor:pointer;\" check-li=\"pointcategory\">");
				htmls.append("<input name=\"company_point_category\" type=\"radio\""); 
				if (companyPointValue != null && companyPointValue == Double.parseDouble(companyPointCategoryVO.getPoint())) {
					htmls.append("checked   ");
					names.append(companyPointCategoryVO.getPoint()+"分,");
				}
				htmls.append("data-value=\""+companyPointCategoryVO.getPoint()+"分\" value=\""+companyPointCategoryVO.getPoint()+"\">"+companyPointCategoryVO.getPoint()+"分&nbsp;&nbsp;"+companyPointCategoryVO.getName());
				htmls.append("</li>");
			}
			companyPointCategoryVOs.clear();
		}
		return htmls;
	}
	public static StringBuffer findAreaCityNav() throws Exception{
			StringBuffer htmls = new StringBuffer();
			MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
			htmls.append("<div class=\"destination-section abroad-destination\">");
			htmls.append("<div class=\"tab-header\">");
			htmls.append("热门城市");
			htmls.append("</div>");
			htmls.append("<ul class=\"tab-nav\">");
			htmls.append("<li class=\"tab-nav-item tab-nav-item-active\"><a href=\"javascript:;\">热门</a></li>");
			htmls.append("<li class=\"tab-nav-item\"><a href=\"javascript:;\">ABCD</a></li>");
			htmls.append("<li class=\"tab-nav-item\"><a href=\"javascript:;\">EGHJ</a></li>");
			htmls.append("<li class=\"tab-nav-item\"><a href=\"javascript:;\">KLMNP</a></li>");
			htmls.append("<li class=\"tab-nav-item\"><a href=\"javascript:;\">QRSTW</a></li>");
			htmls.append("<li class=\"tab-nav-item\"><a href=\"javascript:;\">XYZ</a></li>");
			htmls.append("</ul>");
			htmls.append("<div class=\"tab-content pane-wrapper\">");
			htmls.append(memcachedUtil.get(MemcachedConst.INDEX_NAV_CITY));
			htmls.append("<div>");
			return htmls;
	}
}
