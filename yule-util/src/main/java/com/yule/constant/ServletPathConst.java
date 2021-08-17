package com.yule.constant;

import java.util.HashMap;
import java.util.Map;

public class ServletPathConst {
	
	/**
	 * 系统后台无限制路径
	 */
	public static final Map<String,Object> ADMIN_MAPPING_URLS = new HashMap<String, Object>();
	
	/**
	 * 企业后台无限制路径
	 */
	public static final Map<String,Object> COMPANY_MAPPING_URLS = new HashMap<String, Object>();
	/**
	 * 销售人员系统无限制路径
	 */
	public static final Map<String,Object> SALESMAN_MAPPING_URLS = new HashMap<String, Object>();
	
	static{
		if(ADMIN_MAPPING_URLS.size()<=0){
			ADMIN_MAPPING_URLS.put("/login.do", "");
			ADMIN_MAPPING_URLS.put("/word/doFilter.do", "");
			ADMIN_MAPPING_URLS.put("/logout.do", "");
			ADMIN_MAPPING_URLS.put("/showLogin.do", "");
			ADMIN_MAPPING_URLS.put("/area/findProvince.do", "");
			ADMIN_MAPPING_URLS.put("/area/findCity.do", "");
			ADMIN_MAPPING_URLS.put("/area/findCounty.do", "");
			ADMIN_MAPPING_URLS.put("/area/findBusiness.do", "");
			ADMIN_MAPPING_URLS.put("/verifyLogin.do", "");
			ADMIN_MAPPING_URLS.put("/adminUser/verifyAdminUser.do", "");
			ADMIN_MAPPING_URLS.put("/companyUser/verifyCompanyUser.do", "");
			ADMIN_MAPPING_URLS.put("/company/verifyCompany.do", "");
			ADMIN_MAPPING_URLS.put("/company/findPinYinByName.do", "");
			ADMIN_MAPPING_URLS.put("/salesmanLogin/verifySalesmanLogin.do", "");
			ADMIN_MAPPING_URLS.put("/home.do", "");
			ADMIN_MAPPING_URLS.put("/adminUser/findAdminUserPassword.do", "");
			ADMIN_MAPPING_URLS.put("/adminUser/updateAdminUserPassword.do", "");
			ADMIN_MAPPING_URLS.put("/salesmanLogin/findSalesmanAjax.do", "");
			ADMIN_MAPPING_URLS.put("/initArea/initAreaRedis.do", "");
			ADMIN_MAPPING_URLS.put("/initCompany/initCompanyPointCategoryRedis.do", "");
			ADMIN_MAPPING_URLS.put("/initCompany/initCompanyCommentCategoryRedis.do", "");
			ADMIN_MAPPING_URLS.put("/initCompany/initCompanyGradeRedis.do", "");
			ADMIN_MAPPING_URLS.put("/initCompany/initCompanyCategoryRedis.do", "");
			ADMIN_MAPPING_URLS.put("/initPc/initIndexHtml.do", "");
		}
		if(COMPANY_MAPPING_URLS.size()<=0){
			COMPANY_MAPPING_URLS.put("/word/doFilter.do", "");
			COMPANY_MAPPING_URLS.put("/login.do", "");
			COMPANY_MAPPING_URLS.put("/sendMail.do", "");
			COMPANY_MAPPING_URLS.put("/logout.do", "");
			COMPANY_MAPPING_URLS.put("/verifyLogin.do", "");
			COMPANY_MAPPING_URLS.put("/showLogin.do", "");
			COMPANY_MAPPING_URLS.put("/area/findProvince.do", "");
			COMPANY_MAPPING_URLS.put("/area/findCity.do", "");
			COMPANY_MAPPING_URLS.put("/area/findCounty.do", "");
			COMPANY_MAPPING_URLS.put("/area/findBusiness.do", "");
			COMPANY_MAPPING_URLS.put("/companyCooperator/insertCompanyCooperator.do", "");
			COMPANY_MAPPING_URLS.put("/companyUser/updateCompanyUserPassword.do", "");
			COMPANY_MAPPING_URLS.put("/findNotReadInfo.do", "");
			COMPANY_MAPPING_URLS.put("/findMessageNotRead.do", "");
			COMPANY_MAPPING_URLS.put("/findOrderNotRead.do", "");
			COMPANY_MAPPING_URLS.put("/findTaskNotRead.do", "");
			COMPANY_MAPPING_URLS.put("/backPassword.do", "");
			COMPANY_MAPPING_URLS.put("/balance/findHistoryBalance.do", "");
			COMPANY_MAPPING_URLS.put("/company/findSameCompany.do", "");
			COMPANY_MAPPING_URLS.put("/init/findCompany.do", "");
			COMPANY_MAPPING_URLS.put("/init/updateCompany.do", "");
			COMPANY_MAPPING_URLS.put("/init/findCompanyAddress.do", "");
			COMPANY_MAPPING_URLS.put("/init/companyAddress.do", "");
			COMPANY_MAPPING_URLS.put("/init/updateCompanyAddress.do", "");
			COMPANY_MAPPING_URLS.put("/init/verifyCompany.do", "");
		}
		if(SALESMAN_MAPPING_URLS.size()<=0){
			SALESMAN_MAPPING_URLS.put("/word/doFilter.do", "");
			SALESMAN_MAPPING_URLS.put("/login.do", "");
			SALESMAN_MAPPING_URLS.put("/verifyLogin.do", "");
			SALESMAN_MAPPING_URLS.put("/showLogin.do", "");
			SALESMAN_MAPPING_URLS.put("/sendMail.do", "");
			SALESMAN_MAPPING_URLS.put("/backPassword.do", "");
			SALESMAN_MAPPING_URLS.put("/salesmanCooperator/insertSalesmanCooperator.do", "");
			
		}
	}

}
