package com.yule.action.product;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.service.IProductService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.service.IProductGalleryMongo;
import com.yule.mongo.param.InsertProductGalleryParam;
import com.yule.mongo.pojo.ProductGallery;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.Product;
import com.yule.util.AdminLogUtil;
import com.yule.util.StringUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/productGallery")
public class ProductGalleryAction extends BaseAction{

	
	@Autowired
	private IProductGalleryMongo productGalleryMongoImpl;
	
	@Autowired
	private IProductService productServiceImpl;
	
	/**
	 * 查询企业分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findProductGallery",method = RequestMethod.GET)
	public String findProductGalleryList(@RequestParam(value = "id", required = false)String productId) throws Exception {
		try {
			Product product = productServiceImpl.findProductById(productId);
			if(null!=product){
				Page<ProductGallery> page = productGalleryMongoImpl.findProductGalleryPageByProductId(productId,Integer.MAX_VALUE, 1);
				StringBuffer htmls = new StringBuffer("");
				List<ProductGallery> lists = page.getDatas();
				if(null!=lists&&lists.size()>0){
					htmls.append("<ul  class=\"clearfix\">");
					AdminPrivilege adminPrivilege = null;
					for(ProductGallery productGallery:lists){
						htmls.append("<li>");
						htmls.append("<a href=\"javascript:;\" ><img src=\""+productGallery.getPath()+FileUploadConst.PX_150_150+productGallery.getSystem_name()+"\" /></a>");
						if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)) {
							adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
							htmls.append("<div class=\"ablum-del\"><a class=\"button\" gallery-delete=\"\" href=\"javascript:;\"  data-url=\""+adminPrivilege.getUrl()+"\" data-id=\""+productGallery.getId()+"\"  />"+adminPrivilege.getName()+"</a>&nbsp;</div>");
						}
						htmls.append("<div class=\"title\">");
						htmls.append(StringUtil.cut(productGallery.getName(), 8)+"</div>");
						
						htmls.append("</li>");
					}
					htmls.append("</ul>");
					lists.clear();
					lists=null;
				}
				request.setAttribute("htmls", htmls);
				request.setAttribute("product", product);
			}
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/product/gallery/index";
	}
	
	/**
	 * 添加企业分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/batchInsertProductGallery",method = RequestMethod.POST)
	public String insertProductGallery(InsertProductGalleryParam insertProductGalleryParam) throws Exception {
		try {
			productGalleryMongoImpl.batchInsertProductGallery(insertProductGalleryParam);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/productGallery/findProductGallery.do?id="+insertProductGalleryParam.getProduct_id();
	}
	
	/**
	 * 删除产品图册
	 */
	@RequestMapping(value = "/deleteProductGallery",method = RequestMethod.POST)
	public String deleteProductGallery(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject obj=new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			boolean flag = this.productGalleryMongoImpl.deleteProductGalleryById(id);
			obj.put("status", flag);
			AdminLogUtil.insertLog("删除企业图册", getAdminUser(), LogEnum.DELETE);
		} catch(Exception e){
			new YuleException("删除企业图册[deleteCompanyGallery]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	

	
}
