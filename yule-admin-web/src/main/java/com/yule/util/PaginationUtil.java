package com.yule.util;

import com.yule.vo.Page;


public class PaginationUtil {
	public static String getPaginationHtml(Page<?> page) {
		String url = "javascript:;";
		int pageNo = page.getPageNo();
		StringBuffer paginationHtmls = new StringBuffer();
		paginationHtmls.append("<div class=\"pagination\">");
//		paginationHtmls.append("<a href=\""+url + page.getFirstPage() + "\" >&laquo; 首页</a>");
		paginationHtmls.append("<a href=\""+url+"\" data-page=\""+page.getPreviousPage()+"\">&laquo; 上一页</a>");
		int pageCount = page.getPageCount();
		if(pageNo<10){
			if(pageCount>10){
				for (int i = 1; i <= 7; i++) {
					if (i == pageNo) {
						paginationHtmls.append("<a href=\""+url+"\" class=\"number current\" data-page=\""+i+"\">" + i + "</a>");
					} else {
						paginationHtmls.append("<a href=\""+url+"\" class=\"number\" data-page=\""+i+"\">" + i + "</a>");
					}
				}
				
				if(pageNo>=7){
					for (int i = 8; i <= 10; i++) {
						if (i == pageNo) {
							paginationHtmls.append("<a href=\""+url+"\" class=\"number current\" data-page=\""+i+"\">" + i + "</a>");
						} else {
							paginationHtmls.append("<a href=\""+url+"\" class=\"number\" data-page=\""+i+"\">" + i + "</a>");
						}
					}
				}
				paginationHtmls.append("...");
				paginationHtmls.append("<a href=\""+url+"\" class=\"number\" data-page=\""+pageCount+"\">" + pageCount + "</a>");
			}else{
				for (int i = 1; i <= pageCount; i++) {
					if (i == pageNo) {
						paginationHtmls.append("<a href=\""+url+"\" class=\"number current\" data-page=\""+i+"\">" + i + "</a>");
					} else {
						paginationHtmls.append("<a href=\""+url+"\" class=\"number\" data-page=\""+i+"\">" + i + "</a>");
					}
				}
			}
			
		}else if(pageNo>=(pageCount-6)){
			paginationHtmls.append("<a href=\""+url+"\" class=\"number\" data-page=\""+1+"\">" + 1 + "</a>");
			paginationHtmls.append("...");
			int startPageNo = pageCount - 6;
			if(pageNo==startPageNo){
				for (int i = pageNo-3; i < pageNo; i++) {
					if (i == pageNo) {
						paginationHtmls.append("<a href=\""+url+"\" class=\"number current\" data-page=\""+i+"\">" + i + "</a>");
					} else {
						paginationHtmls.append("<a href=\""+url+"\" class=\"number\" data-page=\""+i+"\">" + i + "</a>");
					}
				}
			}
			for (int i = startPageNo; i <= pageCount; i++) {
				if (i == pageNo) {
					paginationHtmls.append("<a href=\""+url+"\" class=\"number current\" data-page=\""+i+"\">" + i + "</a>");
				} else {
					paginationHtmls.append("<a href=\""+url+"\" class=\"number\" data-page=\""+i+"\">" + i + "</a>");
				}
			}
		}else{
			paginationHtmls.append("<a href=\""+url+"\" class=\"number\" data-page=\""+1+"\" >" + 1 + "</a>");
			paginationHtmls.append("...");
			int endPageNo = pageNo+3 >= pageCount ?  pageCount:pageNo+3;
			for (int i = pageNo-3; i <= endPageNo; i++) {
				if (i == pageNo) {
					paginationHtmls.append("<a href=\""+url+"\" class=\"number current\" data-page=\""+i+"\">" + i + "</a>");
				} else {
					paginationHtmls.append("<a href=\""+url+"\" class=\"number\" data-page=\""+i+"\">" + i + "</a>");
				}
			}
			paginationHtmls.append("...");
			paginationHtmls.append("<a href=\""+url+"\" class=\"number\" data-page=\""+pageCount+"\" >" + pageCount + "</a>");
		}		
		paginationHtmls.append("<a href=\""+url+"\" data-page=\""+page.getNextPage()+"\">下一页 &raquo;</a>");
//		paginationHtmls.append("<a href=\""+url + page.getLastPage() + "\" >末页 &raquo;</a>");
		paginationHtmls.append("</div> <!-- End .pagination -->");
		return paginationHtmls.toString();
	}
	
}
