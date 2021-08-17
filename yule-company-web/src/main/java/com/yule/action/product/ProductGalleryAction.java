package com.yule.action.product;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yule.common.BaseAction;
import com.yule.constant.FileUploadConst;
import com.yule.constant.PageConst;
import com.yule.exception.YuleException;
import com.yule.mongo.company.service.IProductGalleryMongo;
import com.yule.mongo.param.InsertProductGalleryParam;
import com.yule.mongo.pojo.ProductGallery;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/productGallery")
public class ProductGalleryAction extends BaseAction{

	@Autowired
	private IProductGalleryMongo productGalleryServiceImpl;
	
	/**
	 * 查询企业分类
	 */
	@RequestMapping(value = "/findProductGallery",method = RequestMethod.GET)
	public String findProductGalleryList(@RequestParam(value = "id", required = false) String productId) throws Exception {
		try {
			Page<ProductGallery> page = productGalleryServiceImpl.findProductGalleryPageByProductId(productId,Integer.MAX_VALUE, PageConst.PAGE_NO_DEFAULT);
			StringBuffer htmls = new StringBuffer("");
			List<ProductGallery> lists = page.getDatas();
			if(null!=lists&&lists.size()>0){
				StringBuffer path = new StringBuffer("");
				StringBuffer system_name = new StringBuffer("");
				for(ProductGallery productGallery:lists){
					path.append(productGallery.getPath());
					system_name.append(productGallery.getSystem_name());
					htmls.append("<li>");
					htmls.append("<a href=\""+path+system_name+"\" data-rel=\"colorbox\">");
					htmls.append("<img alt=\"150x150\" src=\""+path+FileUploadConst.PX_150_150+system_name+"\" data-original=\""+path+FileUploadConst.PX_150_150+system_name+"\" />");
					htmls.append("<div class=\"text\">");
					htmls.append("<div class=\"inner\">"+productGallery.getName()+"</div>");
					htmls.append("</div>");
					htmls.append("</a>");
					htmls.append("<div class=\"tools tools-bottom\">");
					htmls.append("<a href=\"javascript:;\" data-remove=\"\" data-id=\""+productGallery.getId()+"\">");
					htmls.append("<i class=\"ace-icon fa fa-times red\"></i>");
					htmls.append("</a>");
					htmls.append("</div>");
					htmls.append("</li>");
					path.setLength(0);
					system_name.setLength(0);
				}
				lists.clear();
				lists=null;
			}
			request.setAttribute("productGalleryHtmls", htmls);
			request.setAttribute("productId",productId);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/product/gallery/index";
	}
	
	/**
	 * 添加企业分类
	 */
	@RequestMapping(value = "/batchInsertProductGallery",method = RequestMethod.POST)
	@ResponseBody
	public String batchInsertProductGallery(InsertProductGalleryParam insertProductGalleryParam) throws Exception {
		String objs;
		try {
			String id = insertProductGalleryParam.getProduct_id();
			insertProductGalleryParam.setProduct_id(id);
			objs = productGalleryServiceImpl.batchInsertProductGallery(insertProductGalleryParam);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return objs;
	}
	
	/**
	 * 删除企业分类
	 */
	@RequestMapping(value = "/deleteProductGallery",method = RequestMethod.POST)
	@ResponseBody
	public String deleteProductGallery(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			boolean flag = productGalleryServiceImpl.deleteProductGalleryById(id);
			jsonObject.put("status", flag);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return jsonObject.toString();
	}
	
}
