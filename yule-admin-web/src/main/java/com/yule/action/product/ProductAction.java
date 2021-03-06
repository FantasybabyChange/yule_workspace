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

import com.yule.admin.service.ICompanyService;
import com.yule.admin.service.IProductService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.constant.StatusConst;
import com.yule.exception.YuleException;
import com.yule.param.InsertProductParam;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.Product;
import com.yule.vo.ProductVO;

@Controller
@Scope("prototype")
@RequestMapping("/product")
public class ProductAction extends BaseAction {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICompanyService  companyService;
	
	@RequestMapping(value="/findProduct",method=RequestMethod.GET)
	public String findProduct(@RequestParam(value="id",required=false)String companyId)throws Exception {
		try {
		AdminPrivilege adminPrivilege = null;
		StringBuffer htmls = new StringBuffer();
		htmls.append("<tfoot><tr><td colspan=\"11\">");
		htmls.append("<div class=\"bulk-actions align-left\">");
		if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_BATCH_UPDATE)){
			adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_BATCH_UPDATE);
			htmls.append("<input class=\"button\" type=\"submit\" value=\""+adminPrivilege.getName()+"\">");
			adminPrivilege = null;
		}
		htmls.append("<div class=\"clear\"></div>");
		htmls.append("</td></tr></tfoot>");
		htmls.append("<tbody>");
			List<ProductVO> lists = productService.findProductVOList(companyId);
			StringBuffer privilegeHtml = new StringBuffer("");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_GALLERY)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_GALLERY);
				privilegeHtml.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id="+ PlaceholderConst.ID +"\" >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){	
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE);
				privilegeHtml.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl()+"\" data-update=\"\" >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE_STATUS)){	
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE_STATUS);
				privilegeHtml.append("<a class=\"button\" href=\"javascript:;\" data-id=\""+PlaceholderConst.ID+"\" data-name=\"status\" data-status=\""+PlaceholderConst.STATUS+"\" data-url=\""+adminPrivilege.getUrl()+"\"  >"+PlaceholderConst.STATUS_NAME+"</a>&nbsp;");
				adminPrivilege = null;
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
				privilegeHtml.append("<a class=\"button\" href=\"javascript:;\" onclick=\"deleteProduct(this)\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+adminPrivilege.getUrl()+"\"  >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_PRICE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_PRICE);
				privilegeHtml.append("<a class=\"button\"  href=\""+adminPrivilege.getUrl()+"?productId="+PlaceholderConst.ID+"\"  >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			StringBuffer insertPrivilegeHtml = new StringBuffer("");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT);
				insertPrivilegeHtml.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl()+"\" data-insert=\"\" data-param={\"company_id\":\""+companyId+"\"} >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			if(null != lists  && lists.size()>0){
				StringBuffer rowHtml = new StringBuffer("");
				rowHtml.append("<td>");
				rowHtml.append("<input class=\"text-input\" type=\"text\" name=\"person_num\" value=\""+PlaceholderConst.PERSON_NUM+"\" nullmsg=\"?????????????????????!\" datatype=\"n\" errormsg=\"???????????????!\" />");
				rowHtml.append("</td>");
				rowHtml.append("<td><input class=\"text-input\" type=\"text\"  name=\"min_expense\" value=\"" + PlaceholderConst.MIN_EXPENSE+ "\"  nullmsg=\"?????????????????????!\" datatype=\"n\" errormsg=\"???????????????!\" /></td>");
				String id = null;
		        for (ProductVO productVO:lists) {
		        	id = productVO.getId();
					htmls.append("<tr>");
					htmls.append("<td>");
					if(!StringUtils.isEmpty(id)){
						htmls.append("<input name=\"id\" type=\"hidden\" value=\""+id+"\" />");
					}else{
						htmls.append("<input name=\"id\" type=\"hidden\" value=\"\" />");
					}
					htmls.append("<input id=\"product_category_id\" name=\"product_category_id\" type=\"hidden\" value=\""+productVO.getProduct_category_id()+"\" />" + productVO.getName());
					htmls.append("</td>");
					if(!StringUtils.isEmpty(id)){
						htmls.append(rowHtml.toString().replace(PlaceholderConst.PERSON_NUM, String.valueOf(productVO.getPerson_num()))
								.replace(PlaceholderConst.MIN_EXPENSE, String.valueOf(productVO.getMin_expense())));
						htmls.append("<td>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)
								.replace(PlaceholderConst.STATUS, String.valueOf(StatusConst.BUTTON_PRODUCT_STATUS_VALUE[productVO.getStatus()]))
								.replace(PlaceholderConst.STATUS_NAME, StatusConst.BUTTON_PRODUCT_STATUS[productVO.getStatus()]));
					}else{
						htmls.append(rowHtml.toString().replace(PlaceholderConst.PERSON_NUM, "")
								.replace(PlaceholderConst.MIN_EXPENSE, ""));
						htmls.append("<td>"+insertPrivilegeHtml.toString()+"</td>");
					}
					htmls.append("</tr>");
				}
				lists.clear();
			    lists = null;
			}else{
				htmls.append("<tr>");
					htmls.append("<td class=\"td-center-style\" colspan=\"11\">????????????(????????????????????????????????????)</td>");
				htmls.append("</tr>");
			}
			request.setAttribute("privilegeHtml", privilegeHtml.toString()
					.replace(PlaceholderConst.STATUS, String.valueOf(StatusConst.PRODUCT_STATUS_TRUE))
					.replace(PlaceholderConst.STATUS_NAME, StatusConst.PRODUCT_STATUS[StatusConst.PRODUCT_STATUS_TRUE]));
			request.setAttribute("insertPrivilegeHtml", insertPrivilegeHtml);
			htmls.append("</tbody>");
			request.setAttribute("companyId", companyId);
			request.setAttribute("htmls", htmls);
		} catch (Exception e) {
			new YuleException("???????????????????????????findProduct???????????????:",e);
			throw e;
		}
		return "product/index";
	}
	
	@RequestMapping(value = "/batchUpdateProduct", method = RequestMethod.POST)
	public String batchUpdateProduct(@ModelAttribute("productQuery")InsertProductParam insertProductParam)throws Exception {
		String companyId = insertProductParam.getCompany_id();
		try {
			this.productService.batchInsertAndUpdateProduct(insertProductParam);
		} catch (Exception e) {
			new YuleException("???????????????????????????batchUpdateProduct???????????????:",e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/product/findProduct.do?id="+companyId;
	}
	
	/**
	 * ??????????????????
	 */
	@RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product)throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = productService.updateProduct(product);
			obj.put("status", flag);
		} catch (Exception e) {
			new YuleException("?????????????????????updateProductSeat???????????????:",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * ??????????????????
	 */
	@RequestMapping(value = "/updateProductStatus",method = RequestMethod.POST)
	public String updateProductSeat(@ModelAttribute("product") Product product)throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			boolean flag = productService.updateProduct(product);
			int status = product.getStatus();
			obj.put("status", flag);
			obj.put("value", StatusConst.BUTTON_PRODUCT_STATUS_VALUE[status]);
			obj.put("text", StatusConst.BUTTON_PRODUCT_STATUS[status]);
		} catch (Exception e) {
			new YuleException("???????????????????????????updateProductSeat???????????????:",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * ????????????id????????????
	 */
	@RequestMapping(value = "/deleteProduct",method = RequestMethod.POST)
	public String deleteProduct(@RequestParam(value="id",required=false) String id)throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
        	boolean flag = productService.deleteProductById(id);
        	obj.put("status", flag);
		} catch (Exception e) {
			new YuleException("???????????????????????????deleteProductCategoryById???:????????????!", e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * ????????????
	 */
	@RequestMapping(value = "/insertProduct",method = RequestMethod.POST)
	public String insertProduct(@ModelAttribute("product") Product product)throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
        	boolean flag = productService.insertProduct(product);
        	obj.put("id", product.getId());
        	obj.put("status", flag);
		} catch (Exception e) {
			new YuleException("???????????????????????????insertProductCategory???:????????????!", e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
