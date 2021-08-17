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
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.detail.service.ICompanyCommentMongo;
import com.yule.mongo.detail.service.ICompanyCommentPointMongo;
import com.yule.mongo.detail.vo.CompanyCommentPointVO;
import com.yule.mongo.detail.vo.CompanyCommentVO;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.util.DateUtil;
import com.yule.util.NumberUtil;
import com.yule.vo.Page;
@Controller
@Scope("prototype")
@RequestMapping("/companyComment")
public class CompanyCommentAction extends BaseAction{
	
	@Autowired
	private ICompanyCommentMongo companyCommentMongoImpl;
	
	@Autowired
	private ICompanyCommentPointMongo companyCommentPointMongoImpl;
	
	@RequestMapping(value="/findcompanyComment",method=RequestMethod.POST)
	public String findcompanyComment(@RequestParam(value="company_id",required=false)String company_id)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			List<CompanyComment> companyComments = this.companyCommentMongoImpl.findCompanyComment(company_id, PageConst.PAGE_SIZE_FIVE, PageConst.PAGE_NO_DEFAULT);
			StringBuffer companyCommentHtml = new StringBuffer();
			if (companyComments != null && companyComments.size() > 0) {
				companyCommentHtml.append("<h3>最新的评论</h3>");
				companyCommentHtml.append("<ul class=\"history-list-2\">");
				StringBuffer sb = new StringBuffer();
				for (CompanyComment companyComment : companyComments) {
					companyCommentHtml.append("<li class=\"history-list-item history-list-item-list clearfix\">");	
					companyCommentHtml.append("<p>"+companyComment.getTitle()+"(<strong>"+NumberUtil.formatDouble(companyComment.getPoint(),1)+"</strong>分)</p>");    
					sb.append(DateUtil.DateToString(companyComment.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
					companyCommentHtml.append("<p><abbr class=\"timeago lastbooking\" title=\""+sb+"\">"+sb+"</abbr></p></li>");
					sb.setLength(0);
				}
				companyCommentHtml.append("<ul>");
				companyCommentHtml.append("<a href=\"#commentPanel\" data-click=\"changeCommentPanel\"class=\"detail_highlights-jump\">查看所有的评论</a>");	
			}else{
				companyCommentHtml.append("<h3>暂无评论</h3>");
			}
			object.put("companyCommentHtml", companyCommentHtml.toString());
			companyCommentHtml.setLength(0);
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业详细信息【findCompanyDetails】出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
	
	@RequestMapping(value="/findPointNum",method=RequestMethod.POST)
	public String findPointNum(@RequestParam(value="company_id",required=false)String company_id)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			CompanyCommentVO companyCommentVO = this.companyCommentMongoImpl.findCompanyCommentVO(company_id);
			object.put("commentNum",companyCommentVO.getCount());
			if (companyCommentVO.getCount() > 0) {
				object.put("hightPoint",companyCommentVO.getPoint());	
			}
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业详细信息【findCompanyDetails】出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
	
	@RequestMapping(value="/findCompanyCommentPage",method=RequestMethod.POST)
	public String findCompanyCommentPage(@RequestParam(value="company_id",required=false)String company_id,@RequestParam(value="pageNo",required=false)Integer pageNo,@RequestParam(value="type",required=false)Integer type)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			if(null==pageNo || pageNo <= 0){
				pageNo = 1;
			}
			Page<CompanyComment> page = this.companyCommentMongoImpl.findCompanyCommentPage(company_id,type, PageConst.PAGE_SIZE_FIVE, pageNo);
			StringBuffer companyCommentHtmls = new StringBuffer();
			object.put("pageCount", page.getPageCount());
			object.put("pageNo", pageNo);
			if (page.getRowCount() > 0) {
				List<CompanyComment> datas = page.getDatas();
				for (CompanyComment companyComment : datas) {
					companyCommentHtmls.append("<li class=\"comment_item clearfix\">");
					companyCommentHtmls.append("<div class=\"comment_item_commenter\">"); 
					companyCommentHtmls.append( "<h4> "+companyComment.getUser_name()+"</h4></div>");
					companyCommentHtmls.append("<div class=\"comment_item_comment\">");
					companyCommentHtmls.append(" <div class=\"comment_item_comment_container lang_ltr\">");
					companyCommentHtmls.append("<div class=\"comment_item_comment_header\">");
					companyCommentHtmls.append("<div class=\"comment_item_header_score_container\">");
					companyCommentHtmls.append("<div class=\"comment_item_comment_score\">"+companyComment.getPoint()+" </div></div>");
					companyCommentHtmls.append("<div class=\"comment_item_header_content_container\">");
					companyCommentHtmls.append("<div class=\"comment_item_header_content\">");
					companyCommentHtmls.append(companyComment.getTitle()+"</div></div>");
					companyCommentHtmls.append("<div class=\"comment_item_header_date_container\">");
					companyCommentHtmls.append("<div class=\"comment_item_header_date\">");
					companyCommentHtmls.append(DateUtil.DateToString(companyComment.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN));
					companyCommentHtmls.append("</div></div></div>");
					companyCommentHtmls.append("<div class=\"comment_item_comment_content\">");
					companyCommentHtmls.append("<p class=\"comment_pos\">");
					companyCommentHtmls.append("<span>评论：</span>");
					companyCommentHtmls.append(companyComment.getComment());
					companyCommentHtmls.append("</p><p class=\"comment_neg\">");
					companyCommentHtmls.append("<span>建议：</span>");
					companyCommentHtmls.append(companyComment.getAdvice());
					companyCommentHtmls.append("</p></div>");
					companyCommentHtmls.append("<span class=\"clearfix\"></span></div></div>");
					companyCommentHtmls.append("<div class=\"comment_item_feedback\">");
					companyCommentHtmls.append("<p>有帮助？</p>");
					companyCommentHtmls.append(" <input type=\"hidden\" name=\"hotel_id\" value=\"324929\">");
					companyCommentHtmls.append(" <input type=\"hidden\" name=\"object_id\" value=\"989574969\">");
					companyCommentHtmls.append("<input type=\"submit\" value=\"是\">");
					companyCommentHtmls.append("</div>");
					companyCommentHtmls.append("</li>");
				}
			}else{
				companyCommentHtmls.append("<li class=\"comment_item clearfix\">");
				companyCommentHtmls.append("<div class=\"comment_item_comment\">");
				companyCommentHtmls.append("<div class=\"comment_item_comment_container lang_ltr\">");
				companyCommentHtmls.append("<div class=\"comment_item_comment_header\"><p>");
				companyCommentHtmls.append("<br> <center>暂无评论</center><br><br></p></div></div></div></li>");
			}
			object.put("companyCommentHtmls", companyCommentHtmls.toString());
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业详细信息【findCompanyDetails】出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
	
	@RequestMapping(value="/findCompanyCommentPointAVG",method=RequestMethod.POST)
	public String findCompanyCommentPointAVG(@RequestParam(value="company_id",required=false)String company_id)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
		List<CompanyCommentPointVO> findCompanyPointAvg = this.companyCommentPointMongoImpl.findCompanyPointAvg(company_id);
			StringBuffer companyCommentPointHtmls = new StringBuffer();
			if (findCompanyPointAvg.size() > 0) {
				float count = 0;
				for (CompanyCommentPointVO companyCommentPointVO : findCompanyPointAvg) {
					float point = companyCommentPointVO.getPoint();
					count += point;
					companyCommentPointHtmls.append("<li class=\"clearfix\">");
					companyCommentPointHtmls.append("<p class=\"comment_score_name\">"+companyCommentPointVO.getName()+"</p>");
					companyCommentPointHtmls.append("<p class=\"comment_score_value\">"+NumberUtil.formatDouble(point,1)+"</p>");
					companyCommentPointHtmls.append("<div class=\"score_bar\">");
					companyCommentPointHtmls.append("<div class=\"score_bar_value\" style=\"width: "+NumberUtil.getPrecentStr(point, NumberUtil.SUM_SCORE)+";\"></div>");
					companyCommentPointHtmls.append("</div></li>");
				}
				object.put("avgScore", NumberUtil.formatDouble(count/findCompanyPointAvg.size(),1));
				findCompanyPointAvg.clear();
				findCompanyPointAvg = null;
			}else{
				companyCommentPointHtmls.append("无评分");
			}
			object.put("companyCommentHtmls", companyCommentPointHtmls.toString());
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业详细信息【findCompanyDetails】出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
}
