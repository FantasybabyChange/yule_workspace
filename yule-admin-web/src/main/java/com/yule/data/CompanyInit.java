package com.yule.data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yule.admin.service.ICompanyCategoryService;
import com.yule.admin.service.ICompanyCommentCategoryService;
import com.yule.admin.service.ICompanyExpenseCategoryService;
import com.yule.admin.service.ICompanyGradeService;
import com.yule.admin.service.ICompanyPointCategoryService;
import com.yule.admin.service.ICompanyPrivilegeService;
import com.yule.admin.service.ICompanyServeService;
import com.yule.admin.service.IProductCategoryService;
import com.yule.constant.ConfigConst;
import com.yule.constant.CustomBeanConst;
import com.yule.constant.DeleteConst;
import com.yule.pojo.CompanyCategory;
import com.yule.pojo.CompanyCommentCategory;
import com.yule.pojo.CompanyExpenseCategory;
import com.yule.pojo.CompanyGrade;
import com.yule.pojo.CompanyPointCategory;
import com.yule.pojo.CompanyPrivilege;
import com.yule.pojo.CompanyServe;
import com.yule.pojo.ProductCategory;
import com.yule.util.CustomBeanFactory;
import com.yule.util.IDUtil;

public class CompanyInit {
	
	public static void main(String[] args) {
		try {
			initCompany();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public static void initCompany() throws Exception{
		ICompanyGradeService companyGradeServiceImpl = (ICompanyGradeService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyGradeServiceImpl");
		ICompanyCategoryService companyCategoryServiceImpl = (ICompanyCategoryService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyCategoryServiceImpl");
		ICompanyServeService companyServeServiceImpl = (ICompanyServeService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyServeServiceImpl");
		ICompanyPointCategoryService companyPointCategoryServiceImpl = (ICompanyPointCategoryService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyPointCategoryServiceImpl");
		ICompanyPrivilegeService companyPrivilegeServiceImpl = (ICompanyPrivilegeService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyPrivilegeServiceImpl");
		IProductCategoryService productCategoryServiceImpl = (IProductCategoryService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("productCategoryServiceImpl");
		ICompanyCommentCategoryService companyCommentCategoryServiceImpl = (ICompanyCommentCategoryService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyCommentCategoryServiceImpl");
		ICompanyExpenseCategoryService companyExpenseCategoryServiceImpl= (ICompanyExpenseCategoryService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyExpenseCategoryServiceImpl");
		
		companyPointCategoryServiceImpl.deleteCompanyPointCategoryAll();
		companyCommentCategoryServiceImpl.deleteCompanyPointCategoryAll();
		companyGradeServiceImpl.deleteCompanyGradeAll();
		companyCategoryServiceImpl.deleteCompanyCategoryAll();
		companyServeServiceImpl.deleteCompanyServeAll();
		companyPrivilegeServiceImpl.deleteCompanyPrivilegeByAll();
		productCategoryServiceImpl.deleteProductCategoryAll();
		companyExpenseCategoryServiceImpl.deleteALL();
		
		SAXReader sr = new SAXReader();
		InputStream in = CompanyInit.class.getClassLoader().getResourceAsStream(ConfigConst.COMPANY);
		Document doc = sr.read(in);
		Element root = doc.getRootElement();
		int i=0;
		List<Element> grades = root.elements("grade");
		CompanyGrade companyGrade = null;
		if(null!=grades&&grades.size()>0){
			List<CompanyGrade> companyGrades = new ArrayList<CompanyGrade>();
			for(Element e : grades){
				companyGrade = new CompanyGrade();
				companyGrade.setId(IDUtil.getID());
				companyGrade.setName(e.attributeValue("name"));
				companyGrade.setOrder(i);
				companyGrade.setIs_delete(DeleteConst.IS_DELETE_TRUE);
				companyGrades.add(companyGrade);
				i++;
			}
			companyGradeServiceImpl.batchInsertCompanyGrade(companyGrades);
			companyGrades.clear();
			companyGrades = null;
		}
		companyGradeServiceImpl = null;
		i=0;
		List<CompanyPrivilege> companyPrivileges = new ArrayList<CompanyPrivilege>();
		List<Element> categorys = root.elements("category");
		if(null!=categorys&&categorys.size()>0){
			List<ProductCategory> productCategorys = new ArrayList<ProductCategory>();
			List<CompanyCategory> companyCategorys = new ArrayList<CompanyCategory>();
			CompanyCategory companyCategory = null;
			ProductCategory productCategory = null;
			String companyCategoryId = null;
			int j =0;
			List<Element> pcategorys = null;
			for(Element e : categorys){
				companyCategoryId = IDUtil.getID();
				companyCategory = new CompanyCategory();
				companyCategory.setId(companyCategoryId);
				companyCategory.setName(e.attributeValue("name"));
				companyCategory.setOrder(i);
				companyCategory.setIs_delete(DeleteConst.IS_DELETE_TRUE);
				companyCategorys.add(companyCategory);
				pcategorys = e.elements("product_category");
				if(null!=pcategorys&&pcategorys.size()>0){
					for(Element pe : pcategorys){
						productCategory = new ProductCategory();
						productCategory.setId(IDUtil.getID());
						productCategory.setName(pe.attributeValue("name"));
						productCategory.setCompany_category_id(companyCategoryId);
						productCategory.setIs_delete(DeleteConst.IS_DELETE_TRUE);
						productCategory.setOrder(j);
						productCategorys.add(productCategory);
						j++;
					}
					pcategorys.clear();
					j=0;
				}
				companyPrivileges.addAll(getCompanyPrivilegeList(companyCategoryId, e.elements("privilege")));
				i++;
			}
			categorys.clear();
			categorys = null;
			pcategorys = null;
			companyCategoryServiceImpl.batchInsertCompanyCategory(companyCategorys);
			companyCategorys.clear();
			companyCategorys = null;
			productCategoryServiceImpl.batchInsertProductCategory(productCategorys);
			productCategorys.clear();
			productCategorys = null;
		}
		companyPrivileges.addAll(getCompanyPrivilegeList(null, root.elements("privilege")));
		companyPrivilegeServiceImpl.batchInsertCompanyPrivilege(companyPrivileges);
		companyPrivileges.clear();
		companyPrivileges = null;
		companyCategoryServiceImpl = null;
		productCategoryServiceImpl = null;
		i=0;
		List<Element> serves = root.elements("serve");
		List<CompanyServe> companyServes = new ArrayList<CompanyServe>();
		CompanyServe companyServe = null;
		if(null!=serves&&serves.size()>0){
			for(Element e : serves){
				companyServe = new CompanyServe();
				companyServe.setId(IDUtil.getID());
				companyServe.setName(e.attributeValue("name"));
				companyServe.setOrder(i);
				companyServe.setIs_delete(DeleteConst.IS_DELETE_TRUE);
				companyServes.add(companyServe);
				i++;
			}
			serves.clear();
			serves = null;
			companyServeServiceImpl.batchInsertCompanyServe(companyServes);
			companyServes.clear();
			companyServes = null;
		}
		companyServeServiceImpl = null;
		i=0;
		List<Element> pointCategorys = root.elements("point_category");
		List<CompanyPointCategory> companyPointCategorys = new ArrayList<CompanyPointCategory>();
		CompanyPointCategory companyPointCategory = null;
		if(null!=pointCategorys&&pointCategorys.size()>0){
			for(Element e : pointCategorys){
				companyPointCategory = new CompanyPointCategory();
				companyPointCategory.setId(IDUtil.getID());
				companyPointCategory.setName(e.attributeValue("name"));
				companyPointCategory.setPoint(Integer.valueOf(e.attributeValue("point")));
				companyPointCategory.setIs_delete(DeleteConst.IS_DELETE_TRUE);
				companyPointCategorys.add(companyPointCategory);
				i++;
			}
			pointCategorys.clear();
			pointCategorys = null;
			companyPointCategoryServiceImpl.batchInsertCompanyPointCategory(companyPointCategorys);
			companyPointCategorys.clear();
			companyPointCategorys = null;
		}
		companyPointCategoryServiceImpl = null;
		i=0;
		List<Element> commentCategorys = root.elements("comment_category");
		List<CompanyCommentCategory> companyCommentCategorys = new ArrayList<CompanyCommentCategory>();
		CompanyCommentCategory companyCommentCategory = null;
		if(null!=commentCategorys&&commentCategorys.size()>0){
			for(Element e : commentCategorys){
				companyCommentCategory = new CompanyCommentCategory();
				companyCommentCategory.setId(IDUtil.getID());
				companyCommentCategory.setName(e.attributeValue("name"));
				companyCommentCategory.setOrder(Integer.valueOf(e.attributeValue("order")));
				companyCommentCategory.setIs_delete(DeleteConst.IS_DELETE_TRUE);
				companyCommentCategorys.add(companyCommentCategory);
				i++;
			}
			commentCategorys.clear();
			commentCategorys = null;
			companyCommentCategoryServiceImpl.batchInsertCompanyCommentCategory(companyCommentCategorys);
			companyCommentCategorys.clear();
			companyCommentCategorys = null;
		}
		companyCommentCategoryServiceImpl = null;
		i=0;
		//企业消费
		List<Element> expense_categorys = root.elements("expense_category");
		List<Element> expense_category_twos = null;
		List<CompanyExpenseCategory> expenseCategories = new ArrayList<CompanyExpenseCategory>();
		CompanyExpenseCategory companyExpenseCategory=null;
		if (null!=expense_categorys&&expense_categorys.size()>0) {
			for (Element e1 : expense_categorys) {
				String parant_id = IDUtil.getID();
				companyExpenseCategory = new CompanyExpenseCategory();
				companyExpenseCategory.setId(parant_id);
				companyExpenseCategory.setName(e1.attributeValue("name"));
				expenseCategories.add(companyExpenseCategory);
				expense_category_twos = e1.elements("expense_category");
				for (Element e2 : expense_category_twos) {
					companyExpenseCategory = new CompanyExpenseCategory();
					companyExpenseCategory.setId(IDUtil.getID());
					companyExpenseCategory.setParent_id(parant_id);
					companyExpenseCategory.setName(e2.attributeValue("name"));
					expenseCategories.add(companyExpenseCategory);
				}
			}
			companyExpenseCategoryServiceImpl.batchInsertCompanyExpenseCategory(expenseCategories);
			expenseCategories.clear();
			expenseCategories=null;
		}
		companyExpenseCategoryServiceImpl = null;
		in.close();
		doc.clearContent();
	}
	
	@SuppressWarnings("unchecked")
	private static List<CompanyPrivilege> getCompanyPrivilegeList(String companyCategoryId,List<Element> privileges){
		List<CompanyPrivilege> companyPrivileges = new ArrayList<CompanyPrivilege>();
		if(null!=privileges&&privileges.size()>0){
			List<Element> privilegeTwos = null;
			List<Element> privilegeThrees = null;
			CompanyPrivilege companyPrivilege = null;
			String privilegeId = null;
			String privilegeTwoId = null;
			int j = 0,k = 0,i=0;
			for(Element privilege : privileges){
				privilegeId = IDUtil.getID();
				companyPrivilege = new CompanyPrivilege();
				companyPrivilege.setId(privilegeId);
				companyPrivilege.setCompany_category_id(companyCategoryId);
				companyPrivilege.setName(privilege.attributeValue("name"));
				companyPrivilege.setUrl(privilege.attributeValue("url"));
				companyPrivilege.setIs_show(Integer.parseInt(privilege.attributeValue("is_show")));
				companyPrivilege.setOrder(j);
				companyPrivileges.add(companyPrivilege);
				privilegeTwos = privilege.elements("privilege");
				if(null!=privilegeTwos&&privilegeTwos.size()>0){
					for(Element privilegeTwo : privilegeTwos){
						privilegeTwoId = IDUtil.getID();
						companyPrivilege = new CompanyPrivilege();
						companyPrivilege.setId(privilegeTwoId);
						companyPrivilege.setCompany_category_id(companyCategoryId);
						companyPrivilege.setParent_id(privilegeId);
						companyPrivilege.setName(privilegeTwo.attributeValue("name"));
						companyPrivilege.setUrl(privilegeTwo.attributeValue("url"));
						companyPrivilege.setIs_show(Integer.valueOf(privilegeTwo.attributeValue("is_show")));
						companyPrivilege.setOrder(k);
						companyPrivileges.add(companyPrivilege);
						privilegeThrees = privilegeTwo.elements("privilege");
						if(null!=privilegeThrees&&privilegeThrees.size()>0){
							for(Element privilegeThree : privilegeThrees){
								companyPrivilege = new CompanyPrivilege();
								companyPrivilege.setId(IDUtil.getID());
								companyPrivilege.setCompany_category_id(companyCategoryId);
								companyPrivilege.setParent_id(privilegeTwoId);
								companyPrivilege.setName(privilegeThree.attributeValue("name"));
								companyPrivilege.setUrl(privilegeThree.attributeValue("url"));
								companyPrivilege.setIs_show(Integer.parseInt(privilegeThree.attributeValue("is_show")));
								companyPrivilege.setOrder(i);
								companyPrivileges.add(companyPrivilege);
								i++;
							}
						}
						k++;
					}
					privilegeTwos.clear();
					k=0;
				}
				j++;
			}
			privileges.clear();
			j=0;
		}
		return companyPrivileges;
	}
}