//package com.yule.util;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.dom4j.Document;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//
//import com.yule.admin.service.ICompanyCategoryService;
//import com.yule.admin.service.ICompanyGradeService;
//import com.yule.admin.service.ICompanyPointCategoryService;
//import com.yule.admin.service.ICompanyPrivilegeService;
//import com.yule.admin.service.ICompanyServeService;
//import com.yule.admin.service.IProductCategoryService;
//import com.yule.constant.ConfigConst;
//import com.yule.constant.CustomBeanConst;
//import com.yule.constant.StatusConst;
//import com.yule.pojo.CompanyCategory;
//import com.yule.pojo.CompanyGrade;
//import com.yule.pojo.CompanyPointCategory;
//import com.yule.pojo.CompanyPrivilege;
//import com.yule.pojo.CompanyServe;
//import com.yule.pojo.ProductCategory;
//
//public class CopyOfCompanyUtil {
//	//企业分类SELECT
//	public static StringBuffer COMPANY_CATEGORY_SELECT = new StringBuffer();
//	//企业档次SELECT 
//	public static StringBuffer COMPANY_GRADE_SELECT = new StringBuffer();
//	//企业评分分类SELECT
//	public static StringBuffer COMPANY_POINT_CATEGORY_SELECT = new StringBuffer();
//	
//	public static String getCompanyGradeSelect() throws Exception{
//		if(COMPANY_GRADE_SELECT.length()<=0){
//			ICompanyGradeService companyGradeServiceImpl = (ICompanyGradeService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyGradeServiceImpl");
//			List<CompanyGrade> companyGrades = companyGradeServiceImpl.findCompanyGradeList();
//			if(null!=companyGrades&&companyGrades.size()>0){
//				StringBuffer gradeHtmls = new StringBuffer("");
//				gradeHtmls.append("<select id=\"company_grade_id\" name=\"company_grade_id\" class=\"small-input\" nullmsg=\"请选择企业档次!\" datatype=\"\" errormsg=\"\" >");
//				for(CompanyGrade companyGrade :companyGrades){
//					gradeHtmls.append("<option value=\""+companyGrade.getId()+"\">"+companyGrade.getName()+"</option>");
//				}
//				gradeHtmls.append("</select>");
//				COMPANY_GRADE_SELECT.append(gradeHtmls);
//	//			jedis.pexpire(ADMIN_COMPANY_GRADE_SELECT, ONE_DAY);
//				gradeHtmls.setLength(0);
//				gradeHtmls = null;
//			}
//			companyGradeServiceImpl = null;
//		}
//		return COMPANY_GRADE_SELECT.toString();
//	}
//
//	public static String getCompanyCategorySelect() throws Exception{
//		if(COMPANY_CATEGORY_SELECT.length()<=0){
//			ICompanyCategoryService companyCategoryServiceImpl = (ICompanyCategoryService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyCategoryServiceImpl");
//			List<CompanyCategory> companyCategorys = companyCategoryServiceImpl.findCompanyCategoryList();
//			StringBuffer categoryHtmls = new StringBuffer("");
//			if(null!=companyCategorys&&companyCategorys.size()>0){
//				categoryHtmls.append("<select id=\"company_category_id\" name=\"company_category_id\" class=\"small-input\" nullmsg=\"请选择企业类型!\" datatype=\"\" errormsg=\"\" >");
//				for(CompanyCategory companyCategory : companyCategorys){
//					categoryHtmls.append("<option value=\""+companyCategory.getId()+"\">"+companyCategory.getName()+"</option>");
//				}
//				categoryHtmls.append("</select>");
//				COMPANY_CATEGORY_SELECT.append(categoryHtmls);
//				categoryHtmls.setLength(0);
//				categoryHtmls = null;
//			}
//			companyCategoryServiceImpl = null;
//		}
//		return COMPANY_CATEGORY_SELECT.toString();
//	}
//	
//	@SuppressWarnings("unchecked")
//	public static void initCompany() throws Exception{
//		ICompanyGradeService companyGradeServiceImpl = (ICompanyGradeService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyGradeServiceImpl");
//		ICompanyCategoryService companyCategoryServiceImpl = (ICompanyCategoryService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyCategoryServiceImpl");
//		ICompanyServeService companyServeServiceImpl = (ICompanyServeService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyServeServiceImpl");
//		ICompanyPointCategoryService companyPointCategoryServiceImpl = (ICompanyPointCategoryService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyPointCategoryServiceImpl");
//		ICompanyPrivilegeService companyPrivilegeServiceImpl = (ICompanyPrivilegeService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyPrivilegeServiceImpl");
//		IProductCategoryService productCategoryServiceImpl = (IProductCategoryService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("productCategoryServiceImpl");
//		companyPointCategoryServiceImpl.deleteCompanyPointCategoryAll();
//		companyGradeServiceImpl.deleteCompanyGradeAll();
//		companyCategoryServiceImpl.deleteCompanyCategoryAll();
//		companyServeServiceImpl.deleteCompanyServeAll();
//		companyPrivilegeServiceImpl.deleteCompanyPrivilegeByAll();
//		productCategoryServiceImpl.deleteProductCategoryAll();
//		SAXReader sr = new SAXReader();
//		InputStream in = CopyOfCompanyUtil.class.getClassLoader().getResourceAsStream(ConfigConst.COMPANY);
//		Document doc = sr.read(in);
//		Element root = doc.getRootElement();
//		int i=0;
//		List<Element> grades = root.elements("grade");
//		List<CompanyGrade> companyGrades = new ArrayList<CompanyGrade>();
//		CompanyGrade companyGrade = null;
//		for(Element e : grades){
//			companyGrade = new CompanyGrade();
//			companyGrade.setId(e.attributeValue("id"));
//			companyGrade.setName(e.attributeValue("name"));
//			companyGrade.setOrder(i);
//			companyGrade.setIs_delete(StatusConst.IS_DELETE_TRUE);
//			companyGrades.add(companyGrade);
//			i++;
//		}
//		companyGradeServiceImpl.batchInsertCompanyGrade(companyGrades);
//		companyGrades.clear();
//		companyGrades = null;
//		companyGradeServiceImpl = null;
//		i=0;
//		List<Element> categorys = root.elements("category");
//		List<Element> pcategorys = null;
//		List<CompanyCategory> companyCategorys = new ArrayList<CompanyCategory>();
//		List<ProductCategory> productCategorys = new ArrayList<ProductCategory>();
//		List<CompanyPrivilege> companyPrivileges = new ArrayList<CompanyPrivilege>();
//		CompanyCategory companyCategory = null;
//		ProductCategory productCategory = null;
//		String companyCategoryId = null;
//		int j =0;
//		for(Element e : categorys){
//			companyCategoryId = e.attributeValue("id");
//			companyCategory = new CompanyCategory();
//			companyCategory.setId(companyCategoryId);
//			companyCategory.setName(e.attributeValue("name"));
//			companyCategory.setOrder(i);
//			companyCategory.setIs_delete(StatusConst.IS_DELETE_TRUE);
//			companyCategorys.add(companyCategory);
//			pcategorys = e.elements("product_category");
//			if(null!=pcategorys&&pcategorys.size()>0){
//				for(Element pe : pcategorys){
//					productCategory = new ProductCategory();
//					productCategory.setId(IDUtil.getID());
//					productCategory.setName(pe.attributeValue("name"));
//					productCategory.setCompany_category_id(companyCategoryId);
//					productCategory.setIs_delete(StatusConst.IS_DELETE_TRUE);
//					productCategory.setOrder(j);
//					productCategorys.add(productCategory);
//					j++;
//				}
//				pcategorys.clear();
//				j=0;
//			}
//			companyPrivileges.addAll(getCompanyPrivilegeList(companyCategoryId, e.elements("privilege")));
//			i++;
//		}
//		categorys.clear();
//		categorys = null;
//		pcategorys = null;
//		
//		companyPrivileges.addAll(getCompanyPrivilegeList(null, root.elements("privilege")));
//		
//		companyCategoryServiceImpl.batchInsertCompanyCategory(companyCategorys);
//		productCategoryServiceImpl.batchInsertProductCategory(productCategorys);
//		companyPrivilegeServiceImpl.batchInsertCompanyPrivilege(companyPrivileges);
//		companyCategorys.clear();
//		productCategorys.clear();
//		companyPrivileges.clear();
//		companyCategorys = null;
//		productCategorys = null;
//		companyPrivileges = null;
//		companyCategoryServiceImpl = null;
//		productCategoryServiceImpl = null;
//		i=0;
//		List<Element> serves = root.elements("serve");
//		List<CompanyServe> companyServes = new ArrayList<CompanyServe>();
//		CompanyServe companyServe = null;
//		if(null!=serves&&serves.size()>0){
//			for(Element e : serves){
//				companyServe = new CompanyServe();
//				companyServe.setId(IDUtil.getID());
//				companyServe.setName(e.attributeValue("name"));
//				companyServe.setOrder(i);
//				companyServe.setIs_delete(StatusConst.IS_DELETE_TRUE);
//				companyServes.add(companyServe);
//				i++;
//			}
//			serves.clear();
//			serves = null;
//		}
//		companyServeServiceImpl.batchInsertCompanyServe(companyServes);
//		companyServes.clear();
//		companyServes = null;
//		companyServeServiceImpl = null;
//		i=0;
//		List<Element> pointCategorys = root.elements("point_category");
//		List<CompanyPointCategory> companyPointCategorys = new ArrayList<CompanyPointCategory>();
//		CompanyPointCategory companyPointCategory = null;
//		if(null!=pointCategorys&&pointCategorys.size()>0){
//			for(Element e : pointCategorys){
//				companyPointCategory = new CompanyPointCategory();
//				companyPointCategory.setId(e.attributeValue("id"));
//				companyPointCategory.setName(e.attributeValue("name"));
//				companyPointCategory.setIs_delete(StatusConst.IS_DELETE_TRUE);
//				companyPointCategorys.add(companyPointCategory);
//				i++;
//			}
//			pointCategorys.clear();
//			pointCategorys = null;
//		}
//		companyPointCategoryServiceImpl.batchInsertCompanyPointCategory(companyPointCategorys);
//		companyPointCategorys.clear();
//		companyPointCategorys = null;
//		companyPointCategoryServiceImpl = null;
//		in.close();
//		doc.clearContent();
//	}
//	
//	@SuppressWarnings("unchecked")
//	private static List<CompanyPrivilege> getCompanyPrivilegeList(String companyCategoryId,List<Element> privileges){
//		List<CompanyPrivilege> companyPrivileges = new ArrayList<CompanyPrivilege>();
//		if(null!=privileges&&privileges.size()>0){
//			List<Element> privilegeTwos = null;
//			List<Element> privilegeThrees = null;
//			CompanyPrivilege companyPrivilege = null;
//			String privilegeId = null;
//			String privilegeTwoId = null;
//			int j = 0,k = 0,i=0;
//			for(Element privilege : privileges){
//				privilegeId = IDUtil.getID();
//				companyPrivilege = new CompanyPrivilege();
//				companyPrivilege.setId(privilegeId);
//				companyPrivilege.setCompany_category_id(companyCategoryId);
//				companyPrivilege.setName(privilege.attributeValue("name"));
//				companyPrivilege.setUrl(privilege.attributeValue("url"));
//				companyPrivilege.setIs_show(Integer.parseInt(privilege.attributeValue("is_show")));
//				companyPrivilege.setOrder(j);
//				companyPrivileges.add(companyPrivilege);
//				privilegeTwos = privilege.elements("privilege");
//				if(null!=privilegeTwos&&privilegeTwos.size()>0){
//					for(Element privilegeTwo : privilegeTwos){
//						privilegeTwoId = IDUtil.getID();
//						companyPrivilege = new CompanyPrivilege();
//						companyPrivilege.setId(privilegeTwoId);
//						companyPrivilege.setCompany_category_id(companyCategoryId);
//						companyPrivilege.setParent_id(privilegeId);
//						companyPrivilege.setName(privilegeTwo.attributeValue("name"));
//						companyPrivilege.setUrl(privilegeTwo.attributeValue("url"));
//						companyPrivilege.setIs_show(Integer.valueOf(privilegeTwo.attributeValue("is_show")));
//						companyPrivilege.setOrder(k);
//						companyPrivileges.add(companyPrivilege);
//						privilegeThrees = privilegeTwo.elements("privilege");
//						if(null!=privilegeThrees&&privilegeThrees.size()>0){
//							for(Element privilegeThree : privilegeThrees){
//								companyPrivilege = new CompanyPrivilege();
//								companyPrivilege.setId(IDUtil.getID());
//								companyPrivilege.setCompany_category_id(companyCategoryId);
//								companyPrivilege.setParent_id(privilegeTwoId);
//								companyPrivilege.setName(privilegeThree.attributeValue("name"));
//								companyPrivilege.setUrl(privilegeThree.attributeValue("url"));
//								companyPrivilege.setIs_show(Integer.parseInt(privilegeThree.attributeValue("is_show")));
//								companyPrivilege.setOrder(i);
//								companyPrivileges.add(companyPrivilege);
//								i++;
//							}
//						}
//						k++;
//					}
//					privilegeTwos.clear();
//					k=0;
//				}
//				j++;
//			}
//			privileges.clear();
//			j=0;
//		}
//		return companyPrivileges;
//	}
//	
//	public static void main(String[] args) {
//		try {
//			initCompany();
////			System.out.println(getCompanyCategorySelect());
////			System.out.println(getCompanyGradeSelect());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}