package com.yule.action.product;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.company.service.IProductService;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.StatusConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.param.InsertProductParam;
import com.yule.pojo.Product;
import com.yule.util.CompanyLogUtil;
import com.yule.vo.ProductVO;

@Controller
@Scope("prototype")
@RequestMapping("/product")
public class ProductAction extends BaseAction {
	
	@Autowired
	private IProductService productService;
	
	@RequestMapping(value="/findProduct",method=RequestMethod.GET)
	public String findProduct()throws Exception {
		try {
			StringBuffer htmls = new StringBuffer();
			htmls.append("<tfoot><tr><td colspan=\"11\">");
			htmls.append("<div class=\"bulk-actions align-left\">");
			
			//htmls.append("<input class=\"button\" type=\"submit\" data-url=\"\" value=\""+adminPrivilege.getName()+"\">");

			htmls.append("<div class=\"clear\"></div>");
			
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody id=\"list\">");
			List<ProductVO> lists = productService.findProductVOList(getCompanyUser().getCompany_id());
			StringBuffer privilegeHtml = new StringBuffer("");
			privilegeHtml.append("<a class=\"btn btn-xs btn-success\" href=\""+"/productGallery/findProductGallery.do"+"?id="+ PlaceholderConst.ID +"\" >"+"图册管理"+"</a>&nbsp;");
			privilegeHtml.append("<a class=\"btn btn-xs btn-success\" product-face=\"\" href=\"#product-face-wizard\" data-toggle=\"modal\"  class=\"btn btn-xs btn-success\"  modal-dialog=\""+PlaceholderConst.ID+"\">"+"图册封面"+"</a>&nbsp;");
			privilegeHtml.append("<button type=\"button\" class=\"btn btn-xs btn-success\"   data-url=\""+"/product/updateProduct.do"+"\" data-update=\"\" >"+"更新"+"</button>&nbsp;");
			privilegeHtml.append("<button type=\"button\" class=\"btn btn-xs btn-success\"  data-id=\""+PlaceholderConst.ID+"\" data-name=\"status\" data-status=\""+PlaceholderConst.STATUS+"\" data-url=\""+"/product/updateProductStatus.do"+"\"  >"+PlaceholderConst.STATUS_NAME+"</button>&nbsp;");
			privilegeHtml.append("<button type=\"button\" class=\"btn btn-xs btn-success\" onclick=\"deleteProduct(this);\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+"/product/deleteProduct.do"+"\"  >"+"删除"+"</button>&nbsp;");
			StringBuffer insertPrivilegeHtml = new StringBuffer("");
			insertPrivilegeHtml.append("<button type=\"button\" class=\"btn btn-xs btn-success\"  data-url=\""+"/product/insertProduct.do"+"\" data-insert=\"\" data-param={\"company_id\":\""+getCompanyUser().getCompany_id()+"\"} >"+"新增"+"</button>&nbsp;");
			if(null != lists  && lists.size()>0){
				StringBuffer rowHtml = new StringBuffer("");
				rowHtml.append("<td>");
				rowHtml.append("<input class=\"text-input\" type=\"text\" name=\"person_num\" value=\""+PlaceholderConst.PERSON_NUM+"\" nullmsg=\"请输入容纳人数!\" datatype=\"n1-2\" errormsg=\"请输入数字!\" />");
				rowHtml.append("</td>");
				rowHtml.append("<td><input class=\"text-input\" type=\"text\"  name=\"min_expense\" value=\"" + PlaceholderConst.MIN_EXPENSE+ "\"  nullmsg=\"请输入最低消费!\" datatype=\"n\" errormsg=\"请输入数字!\" /></td>");
				String id = null;
		        for (ProductVO productVO:lists) {
		        	id = productVO.getId();
					htmls.append("<tr>");
					htmls.append("<td>");
					if(!StringUtils.isEmpty(id)){
						htmls.append("<input id=\"id\" name=\"id\" type=\"hidden\" value=\""+id+"\" />");
					}else{
						htmls.append("<input id=\"id\" name=\"id\" type=\"hidden\" value=\"\" />");
					}
					htmls.append("<input id=\"product_category_id\" name=\"product_category_id\" type=\"hidden\" value=\""+productVO.getProduct_category_id()+"\" />" + productVO.getName());
					htmls.append("</td>");
					if(!StringUtils.isEmpty(id)){
						htmls.append(rowHtml.toString().replace(PlaceholderConst.PERSON_NUM, String.valueOf(productVO.getPerson_num()))
								.replace(PlaceholderConst.MIN_EXPENSE, String.valueOf(productVO.getMin_expense())));
						htmls.append("<td><div>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)
								.replace(PlaceholderConst.STATUS, String.valueOf(StatusConst.BUTTON_PRODUCT_STATUS_VALUE[productVO.getStatus()]))
								.replace(PlaceholderConst.STATUS_NAME, StatusConst.BUTTON_PRODUCT_STATUS[productVO.getStatus()])+"</div></td>");
					}else{
						htmls.append(rowHtml.toString().replace(PlaceholderConst.PERSON_NUM, "")
								.replace(PlaceholderConst.MIN_EXPENSE, ""));
						htmls.append("<td><div>"+insertPrivilegeHtml.toString()+"</div></td>");
					}
					htmls.append("</tr>");
				}
				lists.clear();
			    lists = null;
			}else{
				htmls.append("<tr>");
					htmls.append("<td class=\"td-center-style\" colspan=\"11\">暂无数据(请联系管理员维护产品分类)</td>");
				htmls.append("</tr>");
			}
			request.setAttribute("privilegeHtml", privilegeHtml.toString()
					.replace(PlaceholderConst.STATUS, String.valueOf(StatusConst.PRODUCT_STATUS_TRUE))
					.replace(PlaceholderConst.STATUS_NAME, StatusConst.BUTTON_PRODUCT_STATUS[StatusConst.PRODUCT_STATUS_TRUE]));
			request.setAttribute("insertPrivilegeHtml", insertPrivilegeHtml);
			htmls.append("</tbody>");
			request.setAttribute("htmls", htmls);
			request.setAttribute("companyId", getCompanyUser().getCompany_id());
			CompanyLogUtil.insertLog("获取产品分页信息", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("获取产品分页信息【findProduct】发生错误:", e);
			throw e;
		}
		return "product/index";
	}
	
	/**
	 * 修改产品信息 
	 */
	@RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product)throws Exception {
		JSONObject obj = new JSONObject();
		try {
			boolean flag = productService.updateProduct(product);
			obj.put("status", flag);
			CompanyLogUtil.insertLog("更改产品信息", getCompanyUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("修改产品信息【updateProduct】发生错误:", e);
			throw e;
		} finally{
			outputResult(obj.toString());
        	obj.clear();
        	obj=null;
		}
		return null;
	}
	
	
	/**
	 * 修改产品信息
	 */
	@RequestMapping(value = "/updateProductStatus",method = RequestMethod.POST)
	public String updateProductStatue(@ModelAttribute("product") Product product)throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("static", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = productService.updateProduct(product);
			int status = product.getStatus();
			obj.put("status", flag);
			obj.put("value", StatusConst.BUTTON_PRODUCT_STATUS_VALUE[status]);
			obj.put("text", StatusConst.BUTTON_PRODUCT_STATUS[status]);
		} catch (Exception e) {
			new YuleException("修改产品信息【updateProductStatus】发生错误:", e);
			throw e;
		} finally{
			outputResult(obj.toString());
        	obj.clear();
        	obj=null;
		}
		return null;
	}
	
	@RequestMapping(value = "/batchUpdateProduct", method = RequestMethod.POST)
	public String batchInsertProduct(@ModelAttribute("productQuery")InsertProductParam productQuery)throws Exception {
		StringBuffer obj = new StringBuffer("");
		try {
			productQuery.setCompany_id(getCompanyUser().getCompany_id());
			obj.append(productService.batchInsertAndUpdateProduct(productQuery));
			CompanyLogUtil.insertLog("批量更新产品", getCompanyUser(), LogEnum.BATCH);
		} catch (Exception e) {
			new YuleException("添加产品信息【batchInsert】发生错误:", e);
			throw e;
		}finally{
			outputResult(obj.toString());
        	obj = null;
		}
		return null;
	}
	
	/**
	 * 根据产品id删除产品
	 */
	@RequestMapping(value = "/deleteProduct",method = RequestMethod.POST)
	public String deleteProduct(@RequestParam(value="id",required=false) String id)throws Exception {
		JSONObject obj = new JSONObject();
		try {
			boolean flag= productService.deleteProductById(id);
        	obj.put("status", flag);
        	CompanyLogUtil.insertLog("根据产品id删除产品", getCompanyUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除产品分类信息【deleteProduct】:发生错误!", e);
			throw e;
		} finally{
			outputResult(obj.toString());
        	obj.clear();
        	obj=null;
		}
		return null;
	}
	
	/**
	 * 新增产品
	 */
	@RequestMapping(value = "/insertProduct",method = RequestMethod.POST)
	public String insertProduct(@ModelAttribute("product") Product product)throws Exception {
		JSONObject obj = new JSONObject();
		try {
			product.setCompany_id(getCompanyUser().getCompany_id());
        	boolean flag = productService.insertProduct(product);
        	obj.put("id", product.getId());
        	obj.put("status", flag);
        	CompanyLogUtil.insertLog("新增产品", getCompanyUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("添加产品分类信息【insertProduct:发生错误!", e);
			throw e;
		}  finally{
			outputResult(obj.toString());
        	obj.clear();
        	obj=null;
		}
		return null;
	}
}
