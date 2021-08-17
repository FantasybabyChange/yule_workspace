package com.yule.action.company;

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
import com.yule.exception.YuleException;
import com.yule.mongo.company.service.ICompanyGalleryMongo;
import com.yule.mongo.param.InsertCompanyGalleryParam;
import com.yule.mongo.pojo.CompanyGallery;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/companyGallery")
public class CompanyGalleryAction extends BaseAction{

	@Autowired
	private ICompanyGalleryMongo companyGalleryServiceImpl;
	
	/**
	 * 查询企业分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCompanyGallery",method = RequestMethod.GET)
	public String findCompanyGalleryList() throws Exception {
		try {
			Page<CompanyGallery> page = companyGalleryServiceImpl.findCompanyGalleryPageByCompanyId(getCompanyUser().getCompany_id(),Integer.MAX_VALUE, 1);
			StringBuffer htmls = new StringBuffer("");
			List<CompanyGallery> lists = page.getDatas();
			if(null!=lists&&lists.size()>0){
				StringBuffer path = new StringBuffer("");
				StringBuffer system_name = new StringBuffer("");
				for(CompanyGallery companyGallery:lists){
					path.append(companyGallery.getPath());
					system_name.append(companyGallery.getSystem_name());
					htmls.append("<li>");
					htmls.append("<a href=\""+path+system_name+"\" data-rel=\"colorbox\">");
					htmls.append("<img alt=\"150x150\" src=\""+path+FileUploadConst.PX_150_150+system_name+"\" data-original=\""+path+FileUploadConst.PX_150_150+system_name+"\" />");
					htmls.append("<div class=\"text\">");
					htmls.append("<div class=\"inner\">"+companyGallery.getName()+"</div>");
					htmls.append("</div>");
					htmls.append("</a>");
					htmls.append("<div class=\"tools tools-bottom\">");
					htmls.append("<a href=\"javascript:;\" data-remove=\"\" data-id=\""+companyGallery.getId()+"\">");
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
			request.setAttribute("company_id", getCompanyUser().getCompany_id());
			request.setAttribute("companyGalleryHtmls", htmls);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/company/gallery/index";
	}
	
	/**
	 * 添加企业分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/batchInsertCompanyGallery",method = RequestMethod.POST)
	@ResponseBody
	public String batchInsertCompanyGallery(InsertCompanyGalleryParam insertCompanyGalleryParam) throws Exception {
		String objs;
		try {
			String id = getCompanyUser().getCompany_id();
			insertCompanyGalleryParam.setCompany_id(id);
			objs = companyGalleryServiceImpl.batchInsertCompanyGallery(insertCompanyGalleryParam);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return objs;
	}
	
	/**
	 * 删除企业分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteCompanyGallery",method = RequestMethod.POST)
	@ResponseBody
	public String deleteCompanyGallery(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			boolean flag = companyGalleryServiceImpl.deleteCompanyGalleryById(id);
			jsonObject.put("status", flag);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return jsonObject.toString();
	}
	
}
