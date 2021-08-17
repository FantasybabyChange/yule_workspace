package com.yule.action.browse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.DoMainConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.mongo.user.service.IUserBrowseMongo;
import com.yule.mongo.user.vo.UserBrowseVO;
import com.yule.util.DateUtil;

@Controller
@Scope("prototype")
public class BrowseAction extends BaseAction{
	
	@Autowired
	private IUserBrowseMongo userBrowseMongoImpl;
	
	@RequestMapping(value = "/findBrowse",method = RequestMethod.POST)
	public String findUserBrowse() throws Exception {
		List<UserBrowseVO> lists = userBrowseMongoImpl.findUserBrowseListByUserId(getCookieValue(), PageConst.PAGE_SIZE_TEN, PageConst.PAGE_NO_DEFAULT);
		StringBuffer htmls = new StringBuffer("");
		if(null!=lists&&lists.size()>0){
			for(UserBrowseVO userBrowse:lists){
				htmls.append("<li class=\"history-list-item clearfix\">");
//				htmls.append("<span class=\"history-list-del\"></span>");
				htmls.append("<a href=\""+DoMainConst.DETAIL_URL+"/company/findCompanyDetails.do?id="+userBrowse.getCompany_id()+"\">");
				htmls.append("<img src=\""+FileUploadConst.COMPANY_IMAGE_PATH+userBrowse.getCompany_id()+"100_100company_face"+FileUploadConst.IMAGE_TYPE+"\" data-trigger=\"preview\" data-href=\"\" width=\"80\" height=\"80\">");
				htmls.append("</a>");
				htmls.append("<p><a href=\""+DoMainConst.DETAIL_URL+"/company/findCompanyDetails.do?id="+userBrowse.getCompany_id()+"\" class=\"history-list-title\" target=\"_blank\">"+userBrowse.getCompany_name()+"【"+userBrowse.getCompany_grade_name()+"】</a></p>");
				htmls.append("<span class=\"feataddress\">"+userBrowse.getAddress_datail()+", "+userBrowse.getArea_county_name()+", "+userBrowse.getArea_city_name()+"</span>");
				htmls.append("<span class=\"feataddress\">浏览次数:"+userBrowse.getCount()+"</span>");
				htmls.append("<span class=\"feataddress\">最后浏览日期:"+DateUtil.DateToString(userBrowse.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_EN)+"</span>");
				htmls.append("</li>");
			}
			lists.clear();
		}
		request.setAttribute("result", htmls.toString());
		return "return/browse";
	}
	
	@RequestMapping(value = "/deleteBrowse",method = RequestMethod.POST)
	public String deleteBrowseRecord(@RequestParam(value="id",required=false)String id) throws Exception {
//		request.setAttribute("htmls", htmls.toString());
		return null;
	}
	
}