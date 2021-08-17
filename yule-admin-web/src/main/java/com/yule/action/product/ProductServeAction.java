package com.yule.action.product;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.param.InsertProductServeParam;
import com.yule.admin.service.IProductServeService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.ProductServe;
import com.yule.util.AdminLogUtil;

@Controller
@Scope("prototype")
@RequestMapping("/productServe")
public class ProductServeAction extends BaseAction{

	
	@Autowired
	private IProductServeService productServeServiceImpl;
	
	/**
	 * 查询企业服务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findProductServe",method = RequestMethod.GET)
	public String findProductServe() throws Exception {
		try {
			List<ProductServe> lists = productServeServiceImpl.findProductServeList();
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
				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl()+"\" data-update=\"\" >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+adminPrivilege.getUrl()+"\" data-delete=\"\" >"+adminPrivilege.getName()+"</a>");
				adminPrivilege = null;
			}
			StringBuffer rowHtml = new StringBuffer("");
			rowHtml.append("<td><input id=\"id\" type=\"hidden\" name=\"id\" value=\""+PlaceholderConst.ID+"\"  />");
			rowHtml.append("<input class=\"text-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入名称!\" datatype=\"\" errormsg=\"\" repeatmsg=\"名称重复!\" value=\""+PlaceholderConst.NAME+"\"/></td>");
			rowHtml.append("<td><input class=\"text-input\" type=\"text\"  name=\"order\"  nullmsg=\"请输入排序号!\" datatype=\"n\" errormsg=\"\"  value=\""+PlaceholderConst.ORDER+"\"/> </td>");
			
			StringBuffer htmls = new StringBuffer("");
			
			if(null!=lists&&lists.size()>0){
				StringBuffer id = new StringBuffer("");
				for(ProductServe ProductServe:lists){
					id.append(ProductServe.getId());
					htmls.append("<tr>");
					htmls.append(rowHtml.toString()
							.replace(PlaceholderConst.ID, id)
							.replace(PlaceholderConst.NAME, ProductServe.getName())
							.replace(PlaceholderConst.ORDER, String.valueOf(ProductServe.getOrder())));
					htmls.append("<td>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)+"</td>");
					htmls.append("</tr>");
					id.setLength(0);
				}
				id = null;
				lists.clear();
				lists=null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"5\">暂无数据(点击新增一行添加数据)</td>");
				htmls.append("</tr>");
			}
			rowHtml.append("<td>");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT);
				rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl()+"\" data-insert=\"\">"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-del-row=\"\" >删除</a></td>");
			rowHtml.append("</td>");
			request.setAttribute("rowHtml", rowHtml.toString().replace(PlaceholderConst.ID, "").replace(PlaceholderConst.NAME, ""));
			request.setAttribute("privilegeHtml", privilegeHtml);
			request.setAttribute("operatorHtml", operatorHtml);
			request.setAttribute("htmls", htmls);
			AdminLogUtil.insertLog("查询产品服务", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查询产品服务[findProductServe]错误",e);
			throw e;
		} 
		return "/product/serve/index";
	}
	

	/**
	 * 	批量更新
	 * @param productServeParam
	 */
	@RequestMapping(value = "/batchUpdateProductServe",method = RequestMethod.POST)
	public String batchUpdateProductServe(InsertProductServeParam productServeParam) throws Exception {
		try {
			this.productServeServiceImpl.batchUpdateAndInsertProductServe(productServeParam);
			AdminLogUtil.insertLog("批量更新产品服务", getAdminUser(), LogEnum.BATCH);
		} catch (Exception e) {
			new YuleException("批量更新产品服务[batchUpdateProductServe]错误",e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/productServe/findProductServe.do";
	}
	
	
	/**
	 * 删除企业服务
	 */
	@RequestMapping(value = "/deleteProductServe",method = RequestMethod.POST)
	public String deleteProductServe(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = productServeServiceImpl.deleteProductServeById(id);
			obj.put("status", flag);
			AdminLogUtil.insertLog("删除产品服务", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除产品服务[deleteProductServe]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 更新企业服务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateProductServe",method = RequestMethod.POST)
	public String updateProductServe(@ModelAttribute("productServe")ProductServe productServe) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = productServeServiceImpl.updateProductServe(productServe);
			obj.put("status", flag);
			AdminLogUtil.insertLog("更新产品服务", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新产品服务[updateProductServe]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 新增产品服务设施
	 */
	@RequestMapping(value = "/insertProductServe",method = RequestMethod.POST)
	public String insertProductServe(@ModelAttribute("productServe")ProductServe productServe) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = productServeServiceImpl.insertProductServe(productServe);
			obj.put("id", productServe.getId());
			obj.put("status", flag);
			AdminLogUtil.insertLog("新增产品服务", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("新增产品服[insertProductServe]务错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
