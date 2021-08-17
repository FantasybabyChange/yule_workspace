package com.yule.util;

import java.text.NumberFormat;
import java.util.List;

import com.yule.constant.FileUploadConst;
import com.yule.enumerate.DateStyle;
import com.yule.mongo.company.vo.CompanyCommentPointVO;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.mongo.pojo.CompanyCommentPoint;

public class CompanyCommentUtil {
	public static  void  getCommentHTML(StringBuffer tbody,CompanyComment companyComment,List<CompanyCommentPoint> companyCommentPoint){
		tbody.append("<tr><td colspan=\"7\">");
		tbody.append("<div class=\"itemdiv dialogdiv\">");
		tbody.append("<div class=\"user\">");
		tbody.append("<img alt=\""+companyComment.getUser_name()+"\" src=\""+FileUploadConst.USER_IMAGE_PATH+companyComment.getUser_id()+"/"+FileUploadConst.PX_50_50+FileUploadConst.USER_FACE+FileUploadConst.IMAGE_TYPE+"\" />");
		tbody.append("</div>");
		tbody.append("<div class=\"body\">");
		tbody.append("<div class=\"time\">");
		tbody.append("<i class=\"ace-icon fa fa-clock-o\"></i>");
		StringBuffer dateBuffer = new StringBuffer(DateUtil.DateToString(companyComment.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		tbody.append("<span class=\"green\"><abbr class=\"timeago\" title=\""+dateBuffer+"\">"+dateBuffer+"</abbr></span>");
		dateBuffer.setLength(0);
		tbody.append("</div>");
		tbody.append("<div class=\"name\">");
		tbody.append("<a href=\"#\">"+companyComment.getUser_name()+"</a>&nbsp;&nbsp;<span>订单号:</span>(<strong>"+companyComment.getOrder_num()+"</strong>)");
		tbody.append("</div>");
		tbody.append("<div>");
		if (companyCommentPoint != null && companyCommentPoint.size() > 0) {
			double pointCount = 0;
			for (CompanyCommentPoint c : companyCommentPoint) {
				pointCount += c.getPoint();
				tbody.append("<span class=\"badge badge-success\">");
				tbody.append(c.getCompany_comment_category_name()+"(<strong>"+c.getPoint()+"</strong>)");
				tbody.append("</span>");
			}
			tbody.append("<span class=\"label label-danger arrowed\">综合评分:"+formatDouble(pointCount/companyCommentPoint.size(),1)+"</span>");
		}
		tbody.append("</div>");
		tbody.append("<div class=\"text\"><span class=\"badge badge-success\">评论:</span>"+companyComment.getComment()+"</div>");
		tbody.append("<div class=\"text\"><span class=\"badge badge-danger\">建议:</span>"+companyComment.getAdvice()+"</div>");
		tbody.append("<div class=\"tools\">");
		tbody.append("<a href=\"#\" class=\"btn btn-minier btn-info\">");
		tbody.append("<i class=\"icon-only ace-icon fa fa-share\"></i>");
		tbody.append("</a>");
		tbody.append("</div>");
		tbody.append("</div>");
		tbody.append("</div>");
		tbody.append("</td></tr>");
	}
	public static  void  getCommentPointHTML(StringBuffer tbody,List<CompanyCommentPointVO> datas){
		for(CompanyCommentPointVO companyCommentPointVO:datas){
			tbody.append("<tr><td colspan=\"7\">");
			tbody.append("<div class=\"itemdiv dialogdiv\">");
			tbody.append("<div class=\"user\">");
			tbody.append("<img alt=\""+companyCommentPointVO.getUser_name()+"\" src=\""+FileUploadConst.USER_IMAGE_PATH+companyCommentPointVO.getUser_id()+"/"+FileUploadConst.PX_50_50+FileUploadConst.USER_FACE+FileUploadConst.IMAGE_TYPE+"\" />");
			tbody.append("</div>");
			tbody.append("<div class=\"body\">");
			tbody.append("<div class=\"time\">");
			tbody.append("<i class=\"ace-icon fa fa-clock-o\"></i>");
			StringBuffer dateBuffer = new StringBuffer(DateUtil.DateToString(companyCommentPointVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
			tbody.append("<span class=\"green\"><abbr class=\"timeago\" title=\""+dateBuffer+"\">"+dateBuffer+"</abbr></span>");
			dateBuffer.setLength(0);
			tbody.append("</div>");
			tbody.append("<div class=\"name\">");
			tbody.append("<a href=\"#\">"+companyCommentPointVO.getUser_name()+"</a>&nbsp;&nbsp;<span>订单号:</span>(<strong>"+companyCommentPointVO.getOrder_num()+"</strong>)&nbsp;&nbsp;<a href=\"#comment-wizard\" data-toggle=\"modal\"  modal-dialog=\""+companyCommentPointVO.getOrder_num()+"\">查看评论内容</a>");
			tbody.append("</div>");
			tbody.append("<div>");
			List<String> lists = companyCommentPointVO.getName();
			int i=0;
			List<Double> point = companyCommentPointVO.getPoint();
			double pointCount = 0;
			if(lists != null && lists.size() > 0&&point != null && point.size() > 0){
				for(String s :lists){
					if(i < point.size()){
					pointCount+=point.get(i);
					tbody.append("<span class=\"badge badge-success\">");
					tbody.append(s+"(<strong>"+point.get(i)+"</strong>)");
					tbody.append("</span>");	
					}
					i++;
				}
				tbody.append("<span class=\"label label-danger arrowed\">综合评分:"+formatDouble(pointCount/point.size(),1)+"</span>");
				lists.clear();
				lists=null;
				point.clear();
				point=null;
			}
			tbody.append("</div>");
			tbody.append("<div class=\"tools\">");
			tbody.append("<a href=\"#\" class=\"btn btn-minier btn-info\">");
			tbody.append("<i class=\"icon-only ace-icon fa fa-share\"></i>");
			tbody.append("</a>");
			tbody.append("</div>");
			tbody.append("</div>");
			tbody.append("</div>");
			tbody.append("</td></tr>");
		}
	}
	public  static String formatDouble(double num,int length){
		NumberFormat instance = NumberFormat.getInstance();
		instance.setMaximumFractionDigits(length); 
		return instance.format(num);
	}
}
