//package com.yule.test.admin;
//import java.util.ArrayList;
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.admin.query.InsertProductCategoryQuery;
//import com.yule.admin.service.IProductCategoryService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.ProductCategory;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class ProductCategoryServiceTest extends TestCase {
//	private IProductCategoryService productCategoryServiceImpl;
//
//	public ProductCategoryServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		productCategoryServiceImpl = (IProductCategoryService) context.getBean("productCategoryServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("ProductCategoryServiceTest接口测试");
////		test.addTest(new ProductCategoryServiceTest("findProductCategoryList"));
////		test.addTest(new ProductCategoryServiceTest("findProductCategoryCount"));
////		test.addTest(new ProductCategoryServiceTest("findProductCategoryById"));
////		test.addTest(new ProductCategoryServiceTest("findProductCategoryPage"));
////		test.addTest(new ProductCategoryServiceTest("updateProductCategory"));
////		test.addTest(new ProductCategoryServiceTest("insertProductCategory"));
////		test.addTest(new ProductCategoryServiceTest("deleteProductCategoryById"));
//		test.addTest(new ProductCategoryServiceTest("batchInsertAndUpdateProductCategory"));
//		return test;
//	}
//
//	public void batchInsertAndUpdateProductCategory() throws YuleException{
//		InsertProductCategoryQuery productCategoryQuery = new InsertProductCategoryQuery();
//		List<String> id = new ArrayList<String>();
//		id.add("06b84f48-2e90-456f-be28-619dcc2b1764");
//		List<String> company_category_id = new ArrayList<String>();
//		company_category_id.add("690cd0b6-92d3-4239-ac53-8b4c43500fa0");
//		List<String> name = new ArrayList<String>();
//		name.add("test");
//		List<Integer> order = new ArrayList<Integer>();
//		order.add(5);
//		productCategoryQuery.setId(id);
//		productCategoryQuery.setName(name);
//		productCategoryQuery.setOrder(order);
//		productCategoryQuery.setCompany_category_id(company_category_id);
//		this.productCategoryServiceImpl.batchInsertAndUpdateProductCategory(productCategoryQuery);
//	}
//	
//	public void findProductCategoryPage() throws YuleException{
//		List<ProductCategory> lists = productCategoryServiceImpl.findProductCategoryList("");
//		System.out.println(lists.size());
//		for (ProductCategory productCategory : lists) {
//			System.out.println(productCategory.getName());
//		}
//	}
//	
//	public void findProductCategoryList() throws YuleException {
//		List<ProductCategory> lists = productCategoryServiceImpl.findProductCategoryList("8");
//		System.out.println(lists.size());
//		for (ProductCategory ProductCategory : lists) {
//			System.out.println(ProductCategory.getName());
//		}
//	}
//	
//	public void insertProductCategory() {
//		ProductCategory productCategory = new ProductCategory();
//		productCategory.setId(IDUtil.getID());
//		productCategory.setName("insert");
//		//productCategory.setParent_id(1);
//		productCategory.setIs_delete("1");
//		try {
//			System.out.println(productCategoryServiceImpl.insertProductCategory(productCategory));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	public void updateProductCategory() {
//		ProductCategory productCategory = new ProductCategory();
//		productCategory.setId(IDUtil.getID());
//		productCategory.setName("update");
//		productCategory.setIs_delete("1");
//		try {
//			System.out.println(productCategoryServiceImpl.updateProductCategory(productCategory));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public void deleteProductCategoryById() {
//		try {
//			System.out.println(productCategoryServiceImpl.deleteProductCategoryById(IDUtil.getID()));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
