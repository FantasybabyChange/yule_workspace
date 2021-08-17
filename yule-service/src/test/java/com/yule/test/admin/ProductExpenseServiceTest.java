//package com.yule.test.admin;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.admin.service.IProductExpenseService;
//import com.yule.constant.CustomBeanConst;
//import com.yule.pojo.ProductExpense;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.IDUtil;
//
//public class ProductExpenseServiceTest extends TestCase {
//	private IProductExpenseService productExpenseServiceImpl;
//
//	public ProductExpenseServiceTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
//		productExpenseServiceImpl = (IProductExpenseService) context.getBean("productExpenseServiceImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("ProductExpenseServiceTest接口测试");
//		test.addTest(new ProductExpenseServiceTest("findProductExpenseList"));
//		test.addTest(new ProductExpenseServiceTest("findProductExpenseCount"));
//		test.addTest(new ProductExpenseServiceTest("findProductExpenseById"));
//		test.addTest(new ProductExpenseServiceTest("findProductExpenseListByProductId"));
//		test.addTest(new ProductExpenseServiceTest("updateProductExpense"));
//		test.addTest(new ProductExpenseServiceTest("insertProductExpense"));
//		test.addTest(new ProductExpenseServiceTest("deleteProductExpenseById"));
//		test.addTest(new ProductExpenseServiceTest("batchInsertProductExpense"));
//		return test;
//	}
//
//	 public void batchInsertProductExpense() throws YuleException{
//	      	  List<ProductExpense> list  = new ArrayList<ProductExpense>();
//	      	  for (int i = 0; i < 3; i++) {
//	      		ProductExpense  p = new ProductExpense();
//	      		p.setId(IDUtil.getID());
//	      		p.setIs_delete("0");
//	      		p.setMin_expense(new BigDecimal(0));
//	      		p.setName("0000");
//	      		list.add(p);
//			}
//	      System.out.println(productExpenseServiceImpl.batchInsertProductExpense(list));
//	 }
//	
//	public void findProductExpenseListByProductId()  throws YuleException{
//		List<ProductExpense> lists = productExpenseServiceImpl.findProductExpenseListByProductId(IDUtil.getID());
//		System.out.println(lists.size());
//		for (ProductExpense productExpense : lists) {
//			System.out.println(productExpense.getName());
//		}
//	}
//	
//	
//	public void findProductExpenseList() throws YuleException {
//		List<ProductExpense> lists = productExpenseServiceImpl.findProductExpenseList();
//		System.out.println(lists.size());
////		for (ProductExpense ProductExpense : lists) {
////			System.out.println(ProductExpense.getName());
////		}
//	}
//	public void findProductExpenseCount()  throws YuleException{
//		System.out.println(productExpenseServiceImpl.findProductExpenseCount());
//	}
//	public void findProductExpenseById() throws YuleException {
//		ProductExpense address = productExpenseServiceImpl.findProductExpenseById(IDUtil.getID());
//		System.out.println(address);
//	}
//	public void insertProductExpense() throws YuleException {
//		ProductExpense productExpense = new ProductExpense();
//		productExpense.setId(IDUtil.getID());
//		productExpense.setMin_expense(new BigDecimal(1));
//		productExpense.setIs_delete("1");
//		try {
//			System.out.println(productExpenseServiceImpl.insertProductExpense(productExpense));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public void updateProductExpense() throws YuleException {
//		ProductExpense productExpense = new ProductExpense();
//		productExpense.setId(IDUtil.getID());
//		productExpense.setMin_expense(new BigDecimal(2));
//		productExpense.setIs_delete("1");
//		try {
//			System.out.println(productExpenseServiceImpl.updateProductExpense(productExpense));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public void deleteProductExpenseById() {
//		try {
//			System.out.println(productExpenseServiceImpl.deleteProductExpenseById(IDUtil.getID()));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
