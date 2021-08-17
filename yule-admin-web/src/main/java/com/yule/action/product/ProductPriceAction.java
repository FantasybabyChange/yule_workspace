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

import com.yule.admin.param.InsertProductPriceParam;
import com.yule.admin.service.IProductPriceService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.ProductPrice;
import com.yule.util.AdminLogUtil;

@Controller
@Scope("prototype")
@RequestMapping("/productPrice")
public class ProductPriceAction extends BaseAction{
	@Autowired
	private IProductPriceService productPriceService;
	
	/**
	 * 通过产品ID查询所有区间价格
	 */
	@RequestMapping(value="/findProductPrice",method=RequestMethod.GET)
	public String findProductPriceList(@RequestParam(value="productId",required=false)String productId)throws Exception{
		List<ProductPrice> lists = null;
		try {
			lists = productPriceService.findProductPriceListByProductId(productId);
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
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-delete=\"\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>");
				adminPrivilege = null;
			}
			
			StringBuffer htmls = new StringBuffer();
			
			if(null!=lists&&lists.size()>0){
				StringBuffer id = new StringBuffer();
				for (ProductPrice productPrice : lists) {
					id.append(productPrice.getId());
					htmls.append("<tr>");
					htmls.append("<td><input type=\"hidden\" name=\"id\" value=\""+id+"\"  />");
					htmls.append("<input class=\"text-input\" type=\"text\"  name=\"time\"  nullmsg=\"请输入时间区间!\" datatype=\"\" errormsg=\"\"  value=\"" + productPrice.getTime() + "\"/></td>");
					htmls.append("<td><input class=\"text-input small-input\" type=\"text\"  name=\"monday\"  nullmsg=\"请输入金额!\" datatype=\"\" errormsg=\"\"  value=\"" + productPrice.getMonday() + "\"/> </td>");
					htmls.append("<td><input class=\"text-input small-input\" type=\"text\"  name=\"tuesday\"  nullmsg=\"请输入金额!\" datatype=\"\" errormsg=\"\"  value=\"" + productPrice.getTuesday() + "\"/> </td>");
					htmls.append("<td><input class=\"text-input small-input\" type=\"text\"  name=\"wednesday\"  nullmsg=\"请输入金额!\" datatype=\"\" errormsg=\"\"  value=\"" + productPrice.getWednesday() +"\"/> </td>");
					htmls.append("<td><input class=\"text-input small-input\" type=\"text\"  name=\"thursday\"  nullmsg=\"请输入金额!\" datatype=\"\" errormsg=\"\"  value=\"" + productPrice.getThursday() + "\"/> </td>");
					htmls.append("<td><input class=\"text-input small-input\" type=\"text\"  name=\"friday\"  nullmsg=\"请输入金额!\" datatype=\"\" errormsg=\"\"  value=\"" + productPrice.getFriday() + "\"/> </td>");
					htmls.append("<td><input class=\"text-input small-input\" type=\"text\"  name=\"saturday\"  nullmsg=\"请输入金额!\" datatype=\"\" errormsg=\"\"  value=\"" + productPrice.getSaturday() + "\"/> </td>");
					htmls.append("<td><input class=\"text-input small-input\" type=\"text\"  name=\"sunday\"  nullmsg=\"请输入金额!\" datatype=\"\" errormsg=\"\"  value=\"" + productPrice.getSunday() + "\"/> </td>");
					htmls.append("<td>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)+"</td>");
					htmls.append("</tr>");
					id.setLength(0);
				}
				lists.clear();
				lists = null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"5\">暂无数据(点击新增一行添加数据)</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			StringBuffer rowHtml = new StringBuffer("");
			rowHtml.append("<td><input type=\"hidden\" name=\"id\"  />");
			rowHtml.append("<input class=\"text-input\" type=\"text\"  name=\"time\"  nullmsg=\"请输入时间区间!\" datatype=\"\" errormsg=\"\" /></td>");
			rowHtml.append("<td><input class=\"text-input small-input\" type=\"text\"  name=\"monday\"  nullmsg=\"请输入金额!\" datatype=\"\"  /> </td>");
			rowHtml.append("<td><input class=\"text-input small-input\" type=\"text\"  name=\"tuesday\"  nullmsg=\"请输入金额!\" datatype=\"\" /> </td>");
			rowHtml.append("<td><input class=\"text-input small-input\" type=\"text\"  name=\"wednesday\"  nullmsg=\"请输入金额!\" datatype=\"\" /> </td>");
			rowHtml.append("<td><input class=\"text-input small-input\" type=\"text\"  name=\"thursday\"  nullmsg=\"请输入金额!\" datatype=\"\" /> </td>");
			rowHtml.append("<td><input class=\"text-input small-input\" type=\"text\"  name=\"friday\"  nullmsg=\"请输入金额!\" datatype=\"\" /> </td>");
			rowHtml.append("<td><input class=\"text-input small-input\" type=\"text\"  name=\"saturday\"  nullmsg=\"请输入金额!\" datatype=\"\" /> </td>");
			rowHtml.append("<td><input class=\"text-input small-input\" type=\"text\"  name=\"sunday\"  nullmsg=\"请输入金额!\" datatype=\"\" /> </td>");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT);
				rowHtml.append("<td><a class=\"button\" href=\"javascript:;\" data-insert=\"\" data-param={\"product_id\":\""+productId+"\"} data-url=\""+adminPrivilege.getUrl()+"\">"+adminPrivilege.getName()+"</a></td>");
				adminPrivilege = null;
			}
			request.setAttribute("productId", productId);
			request.setAttribute("privilegeHtml", privilegeHtml);
			request.setAttribute("operatorHtml", operatorHtml);
			request.setAttribute("rowHtml", rowHtml);
			request.setAttribute("htmls", htmls);
			AdminLogUtil.insertLog("查询产品价格", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("获取产品价格[findProductPrice]发生异常",e);
			throw e;
		}
	
		return "/product/price/index";
	}
	
	/**
	 * 插入产品价格
	 */
	@RequestMapping(value="/insertProductPrice",method=RequestMethod.POST)
	public String insertProductPrice(@ModelAttribute("productPrice")ProductPrice productPrice) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = productPriceService.insertProductPrice(productPrice);
			obj.put("id", productPrice.getId());
			obj.put("status", flag);
			AdminLogUtil.insertLog("插入产品价格", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("插入产品价格[insertProductPrice]出现异常",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	/**
	 * 删除产品价格
	 */
	@RequestMapping(value="/deleteProductPrice",method=RequestMethod.POST)
	public String deleteProductPrice(@RequestParam(value="id",required=false)String id) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = productPriceService.deleteProductPriceById(id);
			obj.put("status", flag);
			AdminLogUtil.insertLog("删除产品价格", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除产品价格[deleteProductPrice]出现异常",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	
	/**
	 * 更新产品价格
	 */
	@RequestMapping(value="/updateProductPrice",method=RequestMethod.POST)
	public String updateProductPrice(@ModelAttribute("productPrice")ProductPrice productPrice)throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = productPriceService.updateProductPrice(productPrice);
			obj.put("status", flag);
			AdminLogUtil.insertLog("更新产品价格", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新产品价格[updateProductPrice]出现异常",e);
			throw e;
		}  finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 批量更新和插入
	 * @param companyGradeQuery
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/batchUpdateProductPrice",method = RequestMethod.POST)
	public String batchUpdateProductPrice(InsertProductPriceParam insertProductPriceParam) throws Exception {
		try {
			this.productPriceService.batchInsertAndUpdateProductPrice(insertProductPriceParam);
			AdminLogUtil.insertLog("批量更新产品价格", getAdminUser(), LogEnum.BATCH);
		} catch (Exception e) {
			new YuleException("批量更新出现[batchUpdateProductPrice]异常",e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/productPrice/findProductPrice.do?productId="+insertProductPriceParam.getProduct_id();
	}
	

}
