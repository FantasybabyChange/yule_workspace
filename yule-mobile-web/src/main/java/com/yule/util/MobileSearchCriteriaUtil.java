package com.yule.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.yule.constant.JSONConst;
import com.yule.mobile.query.CompanySearchQuery;
import com.yule.pojo.AreaBusiness;
import com.yule.pojo.AreaCounty;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.vo.CompanyCategoryVO;
import com.yule.vo.CompanyGradeVO;
import com.yule.vo.CompanyPointCategoryVO;


public class MobileSearchCriteriaUtil {
	
	@SuppressWarnings("unchecked")
	public static String getCompanyCategory(CompanySearchQuery companyQuery,StringBuffer searchtabHtml) throws Exception{
		List<CompanyCategoryVO> companyCategoryVOs = new ArrayList<CompanyCategoryVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_CATEGORY)){
			companyCategoryVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_CATEGORY)),new CompanyCategoryVO(),JSONConst.JSON_CONFIG));
		}
		
		StringBuffer htmls = new StringBuffer();
		List<String> str = companyQuery.getCompany_category();
		htmls.append("<li class=\"mui-table-view-cell mui-collapse\">");
		htmls.append("<a class=\"mui-navigate-right\" href=\"javascript:;\">娱乐类型</a>");
		htmls.append("<ul data-query=\"\" class=\"mui-table-view mui-table-view-chevron\" data-type=\"company_category\">");
		if (null!=companyCategoryVOs&&companyCategoryVOs.size()>0) {
			for (CompanyCategoryVO companyCategoryVO : companyCategoryVOs) {
				boolean flag = false;
				if(null!=str&&str.size()>0){
					for (String string : str) {
						if(string.equals(companyCategoryVO.getId())){
							flag = true;
							searchtabHtml.append("<span name=\"company_category\" class=\"mui-badge mui-badge-yellow\" data-id=\""+companyCategoryVO.getId()+"\">&nbsp;&nbsp;"+companyCategoryVO.getName()+"<span class=\"mui-icon mui-icon-close\"></span></span>");
						}
					}	
				}
				if(flag){
					htmls.append("<li  data-val=\"\" class=\"mui-table-view-cell mui-checkbox\"><label>"+companyCategoryVO.getName()+"</label><input checked=\"checked\" name=\"company_category\" type=\"checkbox\" value=\""+companyCategoryVO.getId()+"\">");
				}else {
					htmls.append("<li  data-val=\"\" class=\"mui-table-view-cell mui-checkbox\"><label>"+companyCategoryVO.getName()+"</label><input name=\"company_category\" type=\"checkbox\" value=\""+companyCategoryVO.getId()+"\">");
				}
			}
		}
		htmls.append("</li>");
		htmls.append("</ul>");
		htmls.append("</li>");
		return htmls.toString();
	}

	@SuppressWarnings("unchecked")
	public static String getCompanyGrade(CompanySearchQuery companyQuery ,StringBuffer searchtabHtml) throws Exception{
		List<CompanyGradeVO> companyGradeVOs= new ArrayList<CompanyGradeVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_GRADE)){
			companyGradeVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_GRADE)),new CompanyGradeVO(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		List<String> str = companyQuery.getCompany_grade();
		htmls.append("<li class=\"mui-table-view-cell mui-collapse\">");
		htmls.append("<a class=\"mui-navigate-right\" href=\"javascript:;\">企业档次</a>");
		htmls.append("<ul data-query=\"\" class=\"mui-table-view mui-table-view-chevron\" data-type=\"company_grade\">");
		if (null!=companyGradeVOs&&companyGradeVOs.size()>0) {
			for (CompanyGradeVO companyGradeVO: companyGradeVOs) {
				boolean flag = false;
				if(null!=str&&str.size()>0){
					for (String string : str) {
						if(string.equals(companyGradeVO.getId())){
							flag = true;
							searchtabHtml.append("<span name=\"company_category\" class=\"mui-badge mui-badge-yellow\" data-id=\""+companyGradeVO.getId()+"\">&nbsp;&nbsp;"+companyGradeVO.getName()+"<span class=\"mui-icon mui-icon-close\"></span></span>");
						}
					}	
				}
				if(flag){
					htmls.append("<li  data-val=\"\" class=\"mui-table-view-cell mui-checkbox\"><label>"+companyGradeVO.getName()+"</label><input  checked=\"checked\" name=\"company_grade\" type=\"checkbox\" value=\""+companyGradeVO.getId()+"\">");
				}else {
					htmls.append("<li  data-val=\"\" class=\"mui-table-view-cell mui-checkbox\"><label>"+companyGradeVO.getName()+"</label><input name=\"company_grade\" type=\"checkbox\" value=\""+companyGradeVO.getId()+"\">");
				}
			}
		}
		htmls.append("</li>");
		htmls.append("</ul>");
		htmls.append("</li>");
		return htmls.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String getCompanyPointCategory(CompanySearchQuery companyQuery) throws Exception{
		List<CompanyPointCategoryVO> companyPointCategoryVOs = new ArrayList<CompanyPointCategoryVO>() ;
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_POINT_CATEGORY)){
			companyPointCategoryVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_POINT_CATEGORY)),new CompanyPointCategoryVO(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		htmls.append("<li class=\"mui-table-view-cell mui-collapse\">");
		htmls.append("<a class=\"mui-navigate-right\" href=\"javascript:;\">顾客评分</a>");
		htmls.append("<ul data-query=\"\" class=\"mui-table-view mui-table-view-chevron\" data-type=\"company_point_category\">");
		htmls.append("<li class=\"mui-table-view-cell mui-radio\" data-val=\"\">所有<input name=\"company_point_category\" type=\"radio\" value=\"\">");
		if (null!=companyPointCategoryVOs&&companyPointCategoryVOs.size()>0) {
			for (CompanyPointCategoryVO companyPointCategoryVO: companyPointCategoryVOs) {
				htmls.append("<li  data-val=\"\" class=\"mui-table-view-cell mui-radio\"><label>"+companyPointCategoryVO.getName()+"</label><input name=\"company_point_category\" type=\"radio\" value=\""+companyPointCategoryVO.getPoint()+"\">");
			}
			
		}
		htmls.append("</li>");
		htmls.append("</ul>");
		htmls.append("</li>");
		return htmls.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String getCompanyAreaCounty(String area_city) throws Exception{
		List<AreaCounty> areaCountys = new ArrayList<AreaCounty>();
		if(JedisUtil.getInstance().exists(RedisConst.AREA_COUNTY+area_city)){
			areaCountys.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_COUNTY+area_city)),new AreaCounty(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		htmls.append("<li class=\"mui-table-view-cell mui-collapse\">");
		htmls.append("<a class=\"mui-navigate-right\" href=\"javascript:;\" >地区</a>");
		htmls.append("<ul data-query=\"\" class=\"mui-table-view mui-table-view-chevron\" data-type=\"company_area_county\">");
		if (null!=areaCountys&&areaCountys.size()>0) {
			for (AreaCounty areaCounty: areaCountys) {
				htmls.append("<li data-val=\"\" class=\"mui-table-view-cell mui-checkbox\"><label>"+areaCounty.getName()+"</label><input name=\"area_county\" type=\"checkbox\" value=\""+areaCounty.getId()+"\">");
			}
		}
		htmls.append("</li>");
		htmls.append("</ul>");
		htmls.append("</li>");
		return htmls.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String getCompanyAreaBusiness(String area_city) throws Exception{
		List<AreaBusiness> areaBusinesses = new ArrayList<AreaBusiness>();
		if(JedisUtil.getInstance().exists(RedisConst.AREA_BUSINESS+area_city)){
			areaBusinesses.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_BUSINESS+area_city)),new AreaBusiness(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		htmls.append("<li class=\"mui-table-view-cell mui-collapse\">");
		htmls.append("<a class=\"mui-navigate-right\" href=\"javascript:;\">热门商区</a>");
		htmls.append("<ul data-query=\"\" class=\"mui-table-view mui-table-view-chevron\" data-type=\"company_area_business\">");
		if (null!=areaBusinesses&&areaBusinesses.size()>0) {
			for (AreaBusiness areaBusiness: areaBusinesses) {
				htmls.append("<li data-val=\"\" class=\"mui-table-view-cell mui-checkbox\"><label>"+areaBusiness.getName()+"</label><input name=\"area_business\" type=\"checkbox\" value=\""+areaBusiness.getId()+"\">");
			}
		}
		htmls.append("</li>");
		htmls.append("</ul>");
		htmls.append("</li>");
		return htmls.toString();
	}
	@SuppressWarnings("unchecked")
	public static String getCompanyCategorySelect() throws Exception{
		List<CompanyCategoryVO> companyCategoryVOs = new ArrayList<CompanyCategoryVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_CATEGORY)){
			companyCategoryVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_CATEGORY)),new CompanyCategoryVO(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		htmls.append("<option value=\"\">请选择</option>");
		if (null!=companyCategoryVOs&&companyCategoryVOs.size()>0) {
			for (CompanyCategoryVO companyCategoryVO : companyCategoryVOs) {
				htmls.append("<option value=\""+companyCategoryVO.getId()+"\">"+companyCategoryVO.getName()+"</option>");
			}
		}
		return htmls.toString();
	}

	@SuppressWarnings("unchecked")
	public static String getCompanyGradeSelect() throws Exception{
		List<CompanyGradeVO> companyGradeVOs= new ArrayList<CompanyGradeVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_GRADE)){
			companyGradeVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_GRADE)),new CompanyGradeVO(),JSONConst.JSON_CONFIG));
		}
		StringBuffer htmls = new StringBuffer();
		htmls.append("<option value=\"\">请选择</option>");
		if (null!=companyGradeVOs&&companyGradeVOs.size()>0) {
			for (CompanyGradeVO companyGradeVO: companyGradeVOs) {
				htmls.append("<option value=\""+companyGradeVO.getId()+"\">"+companyGradeVO.getName()+"</option>");
			}
		}
		return htmls.toString();
	}

}