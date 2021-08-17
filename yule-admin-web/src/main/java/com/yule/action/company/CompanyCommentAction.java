package com.yule.action.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.service.ICompanyCommentMongo;
import com.yule.mongo.admin.service.ICompanyCommentPointMongo;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.mongo.pojo.CompanyCommentPoint;
import com.yule.mongo.query.CompanyCommentQuery;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/companyComment")
public class CompanyCommentAction extends BaseAction{

	@Autowired
	private ICompanyCommentMongo companyCommentMongoImpl;
	
	@Autowired
	private ICompanyCommentPointMongo companyCommentPointMongo;
	
	/**
	 * 查询企业评论
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCompanyComment",method = RequestMethod.GET)
	public String findCompanyCommentList(CompanyCommentQuery companyCommentQuery,@RequestParam(value="pageNo",required=false)Integer pageNo) throws Exception {
		if(pageNo==null||pageNo<=0){
			pageNo = 1;
		}
		if(null==companyCommentQuery){
			companyCommentQuery = new CompanyCommentQuery();
		}
		try {
			Page<CompanyComment> page = companyCommentMongoImpl.findCompanyCommentPage(companyCommentQuery,PageConst.PAGE_SIZE_TEN, pageNo);
			StringBuffer htmls = new StringBuffer("");
			htmls.append("<tfoot>");
			htmls.append("<tr>");
			htmls.append("<td colspan=\"4\">");
			htmls.append("<div class=\"bulk-actions align-left\">");
			htmls.append("</div>");
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td>");
			htmls.append("</tr>");
			htmls.append("</tfoot>");
			htmls.append("<tbody id=\"list\">");
			List<CompanyComment> lists = page.getDatas();
			if(null!=lists&&lists.size()>0){
				for(CompanyComment companyComment:lists) {
					htmls.append("<tr>");
					htmls.append("<td>"+companyComment.getTitle()+"</td>");
					htmls.append("<td>"+companyComment.getUser_name()+"</td>");
					List<CompanyCommentPoint> cp = companyCommentPointMongo.findCompanyCommentPoint(companyComment.getOrder_num());
					htmls.append("<td>");
					if (cp != null) {
						for (CompanyCommentPoint companyPoint : cp) {
							htmls.append("<span>"+companyPoint.getCompany_comment_category_name()+":<strong>"+companyPoint.getPoint()+"</strong></span>");	
						}
					}
					htmls.append("</td>");
					htmls.append("<td>"+DateUtil.DateToString(companyComment.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+"</td>");
					htmls.append("</tr>");
				}
				lists.clear();
				lists=null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"5\">暂无数据</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			request.setAttribute("companyCommentHtmls", htmls);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/company/comment/index";
	}
}
