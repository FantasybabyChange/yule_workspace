package com.yule.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.yule.constant.JSONConst;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.vo.CompanyPointCategoryVO;


public class CompanyPointCategoryUtil {
	
	@SuppressWarnings("unchecked")
	public static String getCompanyPointName(float point) {
		List<CompanyPointCategoryVO> companyPointCategorys = new ArrayList<CompanyPointCategoryVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_POINT_CATEGORY)){
			companyPointCategorys.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_POINT_CATEGORY)),new CompanyPointCategoryVO(),JSONConst.JSON_CONFIG));
		}
		StringBuffer pointName = new StringBuffer("");
		if(companyPointCategorys.size()>0){
			for(CompanyPointCategoryVO companyPointCategoryVO:companyPointCategorys){
				if(Float.parseFloat(companyPointCategoryVO.getPoint())==Math.floor(point)){
					pointName.append(companyPointCategoryVO.getName());
					break;
				}
			}
			companyPointCategorys.clear();
		}
		return pointName.toString();
	}
	
}
