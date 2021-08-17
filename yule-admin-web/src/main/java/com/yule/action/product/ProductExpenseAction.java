package com.yule.action.product;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.service.IProductExpenseService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.exception.YuleException;
import com.yule.param.InsertProductExpenseParam;
import com.yule.pojo.ProductExpense;

@Controller
@Scope("prototype")
@RequestMapping("/product/Expense")
public class ProductExpenseAction extends BaseAction {
	
	
	@Autowired
	private IProductExpenseService  productExpenseService;
	
	/**
	 * 获取产品最消费消费
	 * @Title: findProductExpenseById
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping(value="/findProductExpense",method=RequestMethod.GET)
	public String findProductExpenseById(@RequestParam(value="id",required=false) String id)throws Exception {
		try {
			StringBuffer htmls = new StringBuffer();
			List<ProductExpense> listProductExpense = productExpenseService.findProductExpenseListByProductId(id);
			htmls.append(" <form action=\"/product/updateProductExpense.do?id="+id+"\" method=\"post\">");
			int listProductExpenseLength=listProductExpense.size();
	        if(null != listProductExpense && listProductExpenseLength>0){
	        	for (int i = 0; i < listProductExpenseLength; i++) {
	        		htmls.append("<div class=\"add-type-part\">");
	        		htmls.append("<div class=\"add-type-close\"><a href=\"/product/deleteProductExpense.do?id="+listProductExpense.get(i).getId()+"&productid="+id+"\">×</a></div>");
		        	htmls.append("<div class=\"add-type-title\">消费名称</div>");
		        	htmls.append("<input type=\"text\" name=\"productExpense["+i+"].name\" class=\"text-input\" value=\""+listProductExpense.get(i).getName()+"\" />");
		        	htmls.append("<div class=\"add-type-title\">消费金额</div>");
		        	htmls.append("<input type=\"text\" name=\"productExpense["+i+"].min_expense\"  class=\"text-input\" value=\""+listProductExpense.get(i).getMin_expense()+"\" />");
		        	htmls.append(" </div>");
				}
	        	listProductExpense.clear();
	        	listProductExpense=null;
	        }else{
	        	htmls.append("<div class=\"add-type-part\">");
	        	htmls.append("<div class=\"add-type-close\">×</div>");
	        	htmls.append("<div class=\"add-type-title\">消费名称</div>");
	        	htmls.append("<input type=\"text\" name=\"productExpense[0].name\" class=\"text-input\" />");
	        	htmls.append("<div class=\"add-type-title\">消费金额</div>");
	        	htmls.append("<input type=\"text\" name=\"productExpense[0].min_expense\"  class=\"text-input\" />");
	        	htmls.append(" </div>");
	        }
	        htmls.append("<a href=\"javascript:void(0)\" class=\"add-type-btn button\">+ 添加</a>");
	        htmls.append("<input class=\"save-type-btn button\" type=\"submit\" value=\"√ 更新\" />");
	        htmls.append("</form>");
	        request.setAttribute("htmls", htmls);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		}
		return "product/expense/index";
	}
	 
	/**
	 * 修改产品最低消费信息
	 * @Title: updateProductExpense
	 * @Description: TODO
	 * @param productExpenseVo
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping(value = "/updateProductExpense",method = RequestMethod.POST)
	public String updateProductExpense(InsertProductExpenseParam InsertProductExpenseParam,@RequestParam(value="id",required=false) String id)throws Exception {
		InsertProductExpenseParam.setProductId(id);
		productExpenseService.updateproductExpense(InsertProductExpenseParam);
		return ActionReturnConst.REDIRECT+"/product/findProductExpense.do?id="+id;
	}
	
	/**
	 * 删除产品最低消费
	 * @Title: deleteProductExpense
	 * @Description: TODO
	 * @param id   最低消费id
	 * @param productid 产品id
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping(value = "/deleteProductExpense",method=RequestMethod.GET)
	public String deleteProductExpenseById(@RequestParam(value="id",required=false) String id,@RequestParam(value="productid",required=false) String productid)throws Exception {
		try {
			productExpenseService.deleteProductExpenseById(id);
		} catch (Exception e) {
			new YuleException("删除产品最低消费【deleteProductExpenseById】:发生错误!", e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/product/findProductExpense.do?id="+productid;
	}
	
}
