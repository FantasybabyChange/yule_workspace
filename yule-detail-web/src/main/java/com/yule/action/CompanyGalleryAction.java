package com.yule.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.PageConst;
import com.yule.exception.YuleException;
import com.yule.mongo.detail.service.ICompanyGalleryMongo;
import com.yule.mongo.pojo.CompanyGallery;
import com.yule.vo.Page;
@Controller
@Scope("prototype")
@RequestMapping("/companyGallery")
public class CompanyGalleryAction extends BaseAction{
	
	@Autowired
	private ICompanyGalleryMongo companyGalleryMongoImpl;
	
	@RequestMapping(value="/findCompanyGallery",method=RequestMethod.POST)
	public String findCompanyGallery(@RequestParam(value="id",required=false)String id)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			 Page<CompanyGallery> companyGalleryPage = this.companyGalleryMongoImpl.findCompanyGalleryPageByCompanyId(id, Integer.MAX_VALUE,PageConst.PAGE_NO_DEFAULT);
			 List<CompanyGallery> datas = companyGalleryPage.getDatas();
			 StringBuffer bigImageHTMLs = new StringBuffer();
			 if (datas != null && datas.size() > 0) {
				 StringBuffer smallImageHTMLs = new StringBuffer();
				 StringBuffer path = new StringBuffer();
				 StringBuffer system_name = new StringBuffer();
				for (CompanyGallery companyGallery : datas) {
					path.append(companyGallery.getPath());
					system_name.append(companyGallery.getSystem_name());
					bigImageHTMLs.append("<div class=\"item\"><img alt=\""+companyGallery.getName()+"\"src=\""+path+FileUploadConst.PX_860_460+system_name+"\" height=\"460\"width=\"860\"/> </div>");
					smallImageHTMLs.append("<li><a href=\"#\"><img alt=\"50*50\"src=\""+path+FileUploadConst.PX_50_50+system_name+"\" width=\"55\"/></a></li>");
					path.setLength(0);
					system_name.setLength(0);
				}
				object.put("smallImage", smallImageHTMLs.toString());
				smallImageHTMLs.setLength(0);
			}else{
				bigImageHTMLs.append("<div class=\"item\">暂无图片</div>");
			}
			object.put("bigImage", bigImageHTMLs.toString());
			object.put("status", ErrorConst.STATUS_SUCCESS);
			bigImageHTMLs.setLength(0);
		} catch (Exception e) {
			new YuleException("查询企业详细信息【findCompanyDetails】出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
}
