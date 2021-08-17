package com.yule.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.yule.constant.JSONConst;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.vo.CompanyCategoryVO;
import com.yule.vo.CompanyGradeVO;

public class CompanyUtil {
	
	@SuppressWarnings("unchecked")
	public static String getCompanyGradeSelect() throws Exception{
		StringBuffer gradeHtmls = new StringBuffer("");
		List<CompanyGradeVO> companyGradeVOs = new ArrayList<CompanyGradeVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_GRADE)){
			companyGradeVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_GRADE)),new CompanyGradeVO(),JSONConst.JSON_CONFIG));
		}
		if(companyGradeVOs.size()>0){
			gradeHtmls.append("<select id=\"company_grade_id\" name=\"company_grade_id\" class=\"small-input\" nullmsg=\"请选择企业档次!\" datatype=\"\" errormsg=\"\" >");
			for(CompanyGradeVO companyGradeVO :companyGradeVOs){
				gradeHtmls.append("<option value=\""+companyGradeVO.getId()+"\">"+companyGradeVO.getName()+"</option>");
			}
			gradeHtmls.append("</select>");
			companyGradeVOs.clear();
			companyGradeVOs = null;
		}
		return gradeHtmls.toString();
	}

	@SuppressWarnings("unchecked")
	public static String getCompanyCategorySelect() throws Exception{
		StringBuffer categoryHtmls = new StringBuffer("");
		List<CompanyCategoryVO> companyCategoryVOs = new ArrayList<CompanyCategoryVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_CATEGORY)){
			companyCategoryVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_CATEGORY)),new CompanyCategoryVO(),JSONConst.JSON_CONFIG));
		}
		if(companyCategoryVOs.size()>0){
			categoryHtmls.append("<select id=\"company_category_id\" name=\"company_category_id\" class=\"small-input\" nullmsg=\"请选择企业类型!\" datatype=\"\" errormsg=\"\" >");
			for(CompanyCategoryVO companyCategoryVO : companyCategoryVOs){
				categoryHtmls.append("<option value=\""+companyCategoryVO.getId()+"\">"+companyCategoryVO.getName()+"</option>");
			}
			categoryHtmls.append("</select>");
			companyCategoryVOs.clear();
			companyCategoryVOs = null;
		}
		return categoryHtmls.toString();
	}
	
}