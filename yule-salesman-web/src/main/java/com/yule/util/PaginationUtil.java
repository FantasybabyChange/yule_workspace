package com.yule.util;

import com.yule.vo.Page;


public class PaginationUtil {
	public static String getPaginationHtml(Page<?> page) {
		String url = "javascript:;";
		int pageNo = page.getPageNo();
		StringBuffer paginationHtmls = new StringBuffer();	
		paginationHtmls.append("<ul class=\"pagination pull-right no-margin\">");
		if(pageNo>1){
			paginationHtmls.append("<li><a href=\""+url+"\" data-page=\""+page.getPreviousPage()+"\" ><i class=\"ace-icon fa fa-angle-double-left\"></i></a></li>");
		}else{
			paginationHtmls.append("<li class=\"prev disabled\"><a href=\""+url+"\" data-page=\""+page.getFirstPage()+"\" ><i class=\"ace-icon fa fa-angle-double-left\"></i></a></li>");
		}
		int pageCount = page.getPageCount();
		if(pageNo<10){
			if(pageCount>10){
				for (int i = 1; i <= 7; i++) {
					if (i == pageNo) {
						paginationHtmls.append("<li class=\"active\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
					} else {
						paginationHtmls.append("<li><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
					}
				}
				
				if(pageNo>=7){
					for (int i = 8; i <= 10; i++) {
						if (i == pageNo) {
							paginationHtmls.append("<li class=\"active\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
						} else {
							paginationHtmls.append("<li><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
						}
					}
				}
				paginationHtmls.append("<li class=\"disabled\"><a href=\""+url+"\">...</a></li>");
				paginationHtmls.append("<li><a href=\""+url+"\" data-page=\""+pageCount+"\">"+pageCount+"</a></li>");
			}else{
				for (int i = 1; i <= pageCount; i++) {
					if (i == pageNo) {
						paginationHtmls.append("<li class=\"active\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
					} else {
						paginationHtmls.append("<li><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
					}
				}
			}
			
		}else if(pageNo>=(pageCount-6)){
			paginationHtmls.append("<li><a href=\""+url+"\" data-page=\""+page.getFirstPage()+"\">"+page.getFirstPage()+"</a></li>");
			paginationHtmls.append("<li class=\"disabled\"><a href=\""+url+"\">...</a></li>");
			int startPageNo = pageCount - 6;
			if(pageNo==startPageNo){
				for (int i = pageNo-3; i < pageNo; i++) {
					if (i == pageNo) {
						paginationHtmls.append("<li class=\"active\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
					} else {
						paginationHtmls.append("<li><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
					}
				}
			}
			for (int i = startPageNo; i <= pageCount; i++) {
				if (i == pageNo) {
					paginationHtmls.append("<li class=\"active\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
				} else {
					paginationHtmls.append("<li><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
				}
			}
		}else{
			paginationHtmls.append("<li><a href=\""+url+"\" data-page=\""+page.getFirstPage()+"\">"+page.getFirstPage()+"</a></li>");
			paginationHtmls.append("<li class=\"disabled\"><a href=\""+url+"\">...</a></li>");
			int endPageNo = pageNo+3 >= pageCount ?  pageCount:pageNo+3;
			for (int i = pageNo-3; i <= endPageNo; i++) {
				if (i == pageNo) {
					paginationHtmls.append("<li class=\"active\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
				} else {
					paginationHtmls.append("<li><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
				}
			}
			paginationHtmls.append("<li class=\"disabled\"><a href=\""+url+"\">...</a></li>");
			paginationHtmls.append("<li><a href=\""+url+"\" data-page=\""+pageCount+"\">"+pageCount+"</a></li>");
		}		
		if(pageNo<pageCount){
			paginationHtmls.append("<li class=\"next\"><a href=\""+url+"\" data-page=\""+page.getNextPage()+"\"><i class=\"ace-icon fa fa-angle-double-right\"></i></a></li>");
		}else{
			paginationHtmls.append("<li class=\"next disabled\"><a href=\""+url+"\" data-page=\""+pageCount+"\"><i class=\"ace-icon fa fa-angle-double-right\"></i></a></li>");
		}
		paginationHtmls.append("</ul>");
		return paginationHtmls.toString();
	}
	
}
