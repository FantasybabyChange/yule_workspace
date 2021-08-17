package com.yule.action.details;

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
import com.yule.mongo.mobile.service.ICompanyGalleryMongo;
import com.yule.mongo.pojo.CompanyGallery;
import com.yule.vo.Page;
@Controller
@Scope("prototype")
@RequestMapping("/companyGallery")
public class CompanyDetailsGalleryAction extends BaseAction{
	
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
				 StringBuffer path = new StringBuffer();
				 StringBuffer system_name = new StringBuffer();
				 bigImageHTMLs.append("<div class=\"mui-slider-group\">");
				for (CompanyGallery companyGallery : datas) {
					path.append(companyGallery.getPath());
					system_name.append(companyGallery.getSystem_name());
					bigImageHTMLs.append("<div class=\"mui-slider-item\">");
					bigImageHTMLs.append("<a href=\"javascript:;\">");
					bigImageHTMLs.append("<img alt=\""+companyGallery.getName()+"\"src=\""+path+FileUploadConst.PX_600_400+system_name+"\" /> ");
				    bigImageHTMLs.append("</a>");
					bigImageHTMLs.append("</div>");
					path.setLength(0);
					system_name.setLength(0);
				}
				bigImageHTMLs.append("</div>");
				bigImageHTMLs.append("<div class=\"mui-slider-indicator\">");
				bigImageHTMLs.append("<span class=\"mui-action mui-action-previous mui-icon mui-icon-back\"></span>");
				bigImageHTMLs.append("<div class=\"mui-number\">");
				bigImageHTMLs.append("<span>1</span> /"+datas.size());
				bigImageHTMLs.append("</div>");
				bigImageHTMLs.append("<span class=\"mui-action mui-action-next mui-icon mui-icon-forward\"></span></div>");
				datas.clear();
				datas = null;
			}else{
				bigImageHTMLs.append("<div class=\"mui-slider-group\">");
				bigImageHTMLs.append("<div class=\"mui-slider-item\">");
				bigImageHTMLs.append("<span style=\"color:white;\">暂无图片</span> ");
				bigImageHTMLs.append("</div>");
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
