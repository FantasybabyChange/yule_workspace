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

import com.yule.admin.param.InsertProductCategoryParam;
import com.yule.admin.service.ICompanyCategoryService;
import com.yule.admin.service.IProductCategoryService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.CompanyCategory;
import com.yule.pojo.ProductCategory;
@Controller
@Scope("prototype")
@RequestMapping("productCategory")
public class ProductCategoryAction extends BaseAction {

	@Autowired
	private IProductCategoryService productCategoryService;
	
	@Autowired
	private ICompanyCategoryService companyCategoryService;
	
	/**
	 * 产品分类分页信息
	 */
	@RequestMapping(value = "/findProductCategory",method=RequestMethod.GET)
	public String findProductCategoryList(@RequestParam(value="companyCategoryId",required = false) String companyCategoryId)throws Exception {
		try {
			List<CompanyCategory> companyCategorys = companyCategoryService.findCompanyCategoryList();
			StringBuffer companyCategoryHtmls = new StringBuffer();
			if(null != companyCategorys && companyCategorys.size()>0){
				if(StringUtils.isEmpty(companyCategoryId)){
					companyCategoryId=companyCategorys.get(0).getId();
				}
				companyCategoryHtmls.append("<form action=\"/productCategory/findProductCategory.do\">");
				companyCategoryHtmls.append("<select name=\"companyCategoryId\" onchange=\"this.form.submit();\" class=\"small-input\" >");
				for (CompanyCategory companyCategory : companyCategorys) {
					if(companyCategoryId.equals(companyCategory.getId())){					
						companyCategoryHtmls.append("<option value=\""+companyCategory.getId()+"\"  selected >"+companyCategory.getName()+"</option>");
					}else{
						companyCategoryHtmls.append("<option value=\""+companyCategory.getId()+"\"  >"+companyCategory.getName()+"</option>");
					}
				}
				companyCategoryHtmls.append("</select>");
				companyCategoryHtmls.append("</form>");
				companyCategorys.clear();
				companyCategorys=null;
			}
			
			request.setAttribute("companyCategoryHtmls", companyCategoryHtmls);
			
			List<ProductCategory> lists= productCategoryService.findProductCategoryList(companyCategoryId);
			AdminPrivilege adminPrivilege = null;
			StringBuffer operatorHtml = new StringBuffer("");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				operatorHtml.append("<a class=\"button\" href=\"javascript:;\" data-add-row=\"\" />新增一行</a>&nbsp;");
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_BATCH_UPDATE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_BATCH_UPDATE);
				operatorHtml.append("<input class=\"button\" type=\"submit\" value=\""+adminPrivilege.getName()+"\">");
				adminPrivilege = null;
			}
			
			StringBuffer privilegeHtml = new StringBuffer("");
			
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE);
				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-update=\"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
//			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
//				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
//				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-delete=\"\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>");
//				adminPrivilege = null;
//			}
			
			StringBuffer rowHtml = new StringBuffer("");
			rowHtml.append("<td><input type=\"hidden\" name=\"id\" value=\""+PlaceholderConst.ID+"\"  />");
			rowHtml.append("<input class=\"text-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入名称!\" datatype=\"\" errormsg=\"\"  value=\""+PlaceholderConst.NAME+"\"/></td>");
			rowHtml.append("<td><input class=\"text-input\" type=\"text\"  name=\"order\"  nullmsg=\"请输入排序号!\" datatype=\"n\" errormsg=\"\"  value=\""+PlaceholderConst.ORDER+"\"/> </td>");
			
			StringBuffer htmls = new StringBuffer("");
			
			if(null!=lists && lists.size()>0){
				StringBuffer id = new StringBuffer("");
				for (ProductCategory productCategory : lists) {
					id.append(productCategory.getId());
					htmls.append("<tr>");
					htmls.append(rowHtml.toString()
							.replace(PlaceholderConst.ID, id)
							.replace(PlaceholderConst.NAME, productCategory.getName())
							.replace(PlaceholderConst.ORDER, String.valueOf(productCategory.getOrder())));
					htmls.append("<td>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)+"</td>");
					htmls.append("</tr>");
					id.setLength(0);
				}
				lists.clear();
				lists=null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"5\">暂无数据(点击新增一行添加数据)</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			rowHtml.append("<td>");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT);
				rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-insert=\"\" data-param={\"company_category_id\":\""+companyCategoryId+"\"} data-url=\""+adminPrivilege.getUrl()+"\">"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-del-row=\"\" >删除</a>");
			rowHtml.append("</td>");
			request.setAttribute("rowHtml", rowHtml.toString().replace(PlaceholderConst.ID, "").replace(PlaceholderConst.NAME, ""));
			request.setAttribute("privilegeHtml", privilegeHtml);
			request.setAttribute("operatorHtml", operatorHtml);
			request.setAttribute("companyCategoryId", companyCategoryId);
			request.setAttribute("htmls", htmls);
		} catch (Exception e) {
			new YuleException("产品分类分页信息【findProductCategoryInit】发生错误:",e);
			throw e;
		}
		return "product/category/index";
	}
	
	@RequestMapping(value = "/batchUpdateProductCategory", method = RequestMethod.POST)
	public String batchUpdateProductCategory(InsertProductCategoryParam insertProductCategoryParam)throws Exception {
		try {
			this.productCategoryService.batchInsertAndUpdateProductCategory(insertProductCategoryParam);
		} catch (Exception e) {
			new YuleException("批量更新产品分类信息【insertProductCategory】:发生错误!", e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/productCategory/findProductCategory.do?companyCategoryId="+insertProductCategoryParam.getCompany_category_id();
	}
	
	/**
	 * 添加产品分类
	 */
	@RequestMapping(value = "/insertProductCategory", method = RequestMethod.POST)
	public String insertProductCategory(@ModelAttribute("productCategory") ProductCategory productCategory)throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
        	boolean flag = productCategoryService.insertProductCategory(productCategory);
        	obj.put("id", productCategory.getId());
        	obj.put("status", flag);
		} catch (Exception e) {
			new YuleException("添加产品分类信息【insertProductCategory】:发生错误!", e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
		
		
	/**
	 * 修改产品分类
	 */
	@RequestMapping(value = "/updateProductCategory", method = RequestMethod.POST)
	public String updateProductCategory(@ModelAttribute("productCategory") ProductCategory productCategory) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = productCategoryService.updateProductCategory(productCategory);
        	obj.put("status", flag);
		} catch (Exception e) {
			new YuleException("修改产品分类信息 【updateProductCategory】:发生错误!", e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	@RequestMapping(value = "/deleteProductCategory",method=RequestMethod.POST)
	public String deleteProductCategoryById(@RequestParam(value="id",required=false) String id) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = productCategoryService.deleteProductCategoryById(id);
        	obj.put("status", flag);
		} catch (Exception e) {
			new YuleException("删除产品分类信息【deleteProductCategoryById】:发生错误!", e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
}
