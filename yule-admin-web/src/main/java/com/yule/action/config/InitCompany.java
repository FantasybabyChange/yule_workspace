package com.yule.action.config;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;

@Controller
@Scope("prototype")
@RequestMapping("/initCompany")
public class InitCompany extends BaseAction{

	@RequestMapping(value = "/initCompanyCategoryRedis")
	public String initCompanyCategoryRedis()throws Exception{
		JSONObject obj=new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			com.yule.cached.CompanyInit.initCompanyCategory();
			obj.put("status", ErrorConst.STATUS_SUCCESS);
			obj.put("message", "初始化企业分类readis成功");
		} catch (Exception e) {
			obj.put("message", "初始化企业分类readis失败");
			throw e;
		}finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
		}
		return null;
	}
	@RequestMapping(value = "/initCompanyCommentCategoryRedis")
	public String initCompanyCommentCategoryRedis()throws Exception{
		JSONObject obj=new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			com.yule.cached.CompanyInit.initCompanyCategory();
			obj.put("status", ErrorConst.STATUS_SUCCESS);
			obj.put("message", "初始化企业评论分类readis成功");
		} catch (Exception e) {
			obj.put("message", "初始化企业评论分类readis失败");
			throw e;
		}finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
		}
		return null;
	}
	@RequestMapping(value = "/initCompanyGradeRedis")
	public String initCompanyGradeRedis()throws Exception{
		JSONObject obj=new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			com.yule.cached.CompanyInit.initCompanyGrade();
			obj.put("status", ErrorConst.STATUS_SUCCESS);
			obj.put("message", "初始化企业档次分类readis成功");
		} catch (Exception e) {
			obj.put("message", "初始化企业档次分类readis失败");
			throw e;
		}finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
		}
		return null;
	}
	@RequestMapping(value = "/initCompanyPointCategoryRedis")
	public String initCompanyPointCategoryRedis()throws Exception{
		JSONObject obj=new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			com.yule.cached.CompanyInit.initCompanyPointCategory();
			obj.put("status",ErrorConst.STATUS_SUCCESS);
			obj.put("message", "初始化企业评分分类readis成功");
		} catch (Exception e) {
			obj.put("message", "初始化企业评分分类readis失败");
			throw e;
		}finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
		}
		return null;
	}
	
}
