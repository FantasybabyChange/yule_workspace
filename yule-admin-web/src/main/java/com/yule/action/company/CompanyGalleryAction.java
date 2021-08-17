package com.yule.action.company;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.service.ICompanyGalleryMongo;
import com.yule.mongo.param.InsertCompanyGalleryParam;
import com.yule.mongo.pojo.CompanyGallery;
import com.yule.pojo.AdminPrivilege;
import com.yule.util.AdminLogUtil;
import com.yule.util.StringUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/companyGallery")
public class CompanyGalleryAction extends BaseAction{

	@Autowired
	private ICompanyGalleryMongo companyGalleryMongoImpl;
	
	/**
	 * 查询企业分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCompanyGallery",method = RequestMethod.GET)
	public String findCompanyGalleryList(@RequestParam(value="id",required=false)String companyId) throws Exception {
		if(companyId==null){
			return "error";
		}
		try {
			Page<CompanyGallery> page = companyGalleryMongoImpl.findCompanyGalleryPageByCompanyId(companyId,Integer.MAX_VALUE, 1);
			StringBuffer htmls = new StringBuffer("");
			List<CompanyGallery> lists = page.getDatas();
			if(null!=lists&&lists.size()>0){
				htmls.append("<ul  class=\"clearfix\">");
				AdminPrivilege adminPrivilege = null;
				for(CompanyGallery companyGallery:lists){
					htmls.append("<li>");
					htmls.append("<a href=\"javascript:;\" ><img src=\""+companyGallery.getPath()+FileUploadConst.PX_150_150+companyGallery.getSystem_name()+"\" /></a>");
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)) {
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
						htmls.append("<div class=\"ablum-del\"><a class=\"button\" gallery-delete=\"\" href=\"javascript:;\"  data-url=\""+adminPrivilege.getUrl()+"\" data-id=\""+companyGallery.getId()+"\" />"+adminPrivilege.getName()+"</a>&nbsp;</div>");
					}
					htmls.append("<div class=\"title\">");
					htmls.append(StringUtil.cut(companyGallery.getName(), 8)+"</div>");
					htmls.append("</li>");
				}
				htmls.append("</ul>");
				lists.clear();
				lists=null;
			}
			request.setAttribute("companyId", companyId);
			request.setAttribute("companyGalleryHtmls", htmls);
			AdminLogUtil.insertLog("查询企业图册", getAdminUser(), LogEnum.QUERY);
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
	public String batchInsertCompanyGallery(InsertCompanyGalleryParam insertCompanyGalleryParam) throws Exception {
		try {
			companyGalleryMongoImpl.batchInsertCompanyGallery(insertCompanyGalleryParam);
			AdminLogUtil.insertLog("批量新增图册", getAdminUser(), LogEnum.BATCH);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/companyGallery/findCompanyGallery.do?id="+insertCompanyGalleryParam.getCompany_id();
	}
	
	/**
	 * 删除企业图册
	 */
	@RequestMapping(value = "/deleteCompanyGallery",method = RequestMethod.POST)
	public String deleteCompanyGallery(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject obj=new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			boolean flag = this.companyGalleryMongoImpl.deleteCompanyGalleryById(id);
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
