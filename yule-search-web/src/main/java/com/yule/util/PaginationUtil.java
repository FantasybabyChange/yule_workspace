package com.yule.util;



public class PaginationUtil {
	public static String getMapPaginationHtml(int pageNo,int pageCount) {
		String url = "javascript:;";
		StringBuffer paginationHtmls = new StringBuffer();
		paginationHtmls.append("<div class=\"c_page_mini\">");
		if(pageNo == 1){
			paginationHtmls.append("<a href=\"javascript:void(0);\"  class=\"c_page_mini_current\">&lt;-</a>");	
		}else{
			paginationHtmls.append("<a href=\"javascript:void(0);\" data-page=\""+(pageNo -1)+"\" class=\"c_up_nocurrent\">&lt;-</a>");
		}
		if(pageNo >= pageCount){
			pageNo = pageCount;
		}
		
		if(pageNo<10){
			if(pageCount>10){
				for (int i = 1; i <= 7; i++) {
					if (i == pageNo) {
						paginationHtmls.append("<a href=\""+url+"\" class=\"c_page_mini_current\" data-page=\""+i+"\">"+i+"</a>");
					} else {
						paginationHtmls.append("<a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a>");
					}
				}
				if(pageNo>=7){
					for (int i = 8; i <= 10; i++) {
						if (i == pageNo) {
							paginationHtmls.append("<a href=\""+url+"\" class=\"c_page_mini_current\" data-page=\""+i+"\">"+i+"</a>");
						} else {
							paginationHtmls.append("<a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a>");
						}
					}
				}
				paginationHtmls.append("<span class=\"c_page_ellipsis\">...</span>");
				paginationHtmls.append("<a href=\""+url+"\" data-page=\""+pageCount+"\">"+pageCount+"</a>");
			}else{
				for (int i = 1; i <= pageCount; i++) {
					if (i == pageNo) {
						paginationHtmls.append("<a href=\""+url+"\" class=\"c_page_mini_current\" data-page=\""+i+"\">"+i+"</a>");
					} else {
						paginationHtmls.append("<a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a>");
					}
				}
			}
			
		}else if(pageNo>=(pageCount-6)){
			paginationHtmls.append("<a href=\""+url+"\" data-page=\""+1+"\">"+1+"</a>");
			paginationHtmls.append("<span class=\"c_page_ellipsis\">...</span>");
			int startPageNo = pageCount - 6;
			if(pageNo==startPageNo){
				for (int i = pageNo-3; i < pageNo; i++) {
					if (i == pageNo) {
						paginationHtmls.append("<a href=\""+url+"\" class=\"c_page_mini_current\"data-page=\""+i+"\">"+i+"</a>");
					} else {
						paginationHtmls.append("<a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a>");
					}
				}
			}
			for (int i = startPageNo; i <= pageCount; i++) {
				if (i == pageNo) {
					paginationHtmls.append("<a href=\""+url+"\" class=\"c_page_mini_current\"data-page=\""+i+"\">"+i+"</a>");
				} else {
					paginationHtmls.append("<a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a>");
				}
			}
		}else{
			paginationHtmls.append("<a href=\""+url+"\" data-page=\""+1+"\">"+1+"</a>");
			paginationHtmls.append("<span class=\"c_page_ellipsis\">...</span>");
			int endPageNo = pageNo+3 >= pageCount ?  pageCount:pageNo+3;
			for (int i = pageNo-3; i <= endPageNo; i++) {
				if (i == pageNo) {
					paginationHtmls.append("<a href=\""+url+"\" class=\"c_page_mini_current\" data-page=\""+i+"\">"+i+"</a>");
				} else {
					paginationHtmls.append("<a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a>");
				}
			}
			paginationHtmls.append("<span class=\"c_page_ellipsis\">...</span>");
			paginationHtmls.append("<a href=\""+url+"\" data-page=\""+pageCount+"\">"+pageCount+"</a>");
		}		
		if(pageNo<pageCount){
			paginationHtmls.append("<a href=\""+url+"\" data-page=\""+(pageNo+1)+"\"></a>");
		}else{
			paginationHtmls.append("<a href=\""+url+"\" data-page=\""+pageCount+"\"></a>");
		}
		if (pageNo >= pageCount) {
			paginationHtmls.append("<a href=\"javascript:void(0);\" class=\"c_page_mini_current\">-&gt;</a>");
		}else{
			paginationHtmls.append("<a href=\"javascript:void(0);\" data-page=\""+(pageNo +1)+"\"class=\"c_down\">-&gt;</a>");			
		}
		paginationHtmls.append("</div>");
		return paginationHtmls.toString();
	}
	
	public static String getPaginationHtml(LucenePage page) {
		String url = "javascript:;";
		int pageNo = page.getPageNo();
		StringBuffer paginationHtmls = new StringBuffer();	
		if(pageNo>1){
			paginationHtmls.append("<a class=\"paging-previous\" href=\""+url+"\" data-page=\""+page.getPreviousPage()+"\" >上一页<i class=\"paging-start\"></i></a>");
		}else{
			paginationHtmls.append("<span class=\"paging-start\">上一页</span>");
		}
		int pageCount = page.getPageCount();
		paginationHtmls.append("<ul class=\"x-list\">");
		if(pageNo<10){
			if(pageCount>10){
				for (int i = 1; i <= 7; i++) {
					if (i == pageNo) {
						paginationHtmls.append("<li  class=\"sr_pagination_item current\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
					} else {
						paginationHtmls.append("<li class=\"sr_pagination_item\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
					}
				}
				
				if(pageNo>=7){
					for (int i = 8; i <= 10; i++) {
						if (i == pageNo) {
							paginationHtmls.append("<li  class=\"sr_pagination_item current\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
						} else {
							paginationHtmls.append("<li class=\"sr_pagination_item\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
						}
					}
				}
				paginationHtmls.append("<li class=\"disabled\"><a href=\""+url+"\">...</a></li>");
				
				
				paginationHtmls.append("<li><a href=\""+url+"\" data-page=\""+pageCount+"\">"+pageCount+"</a></li>");
			}else{
				for (int i = 1; i <= pageCount; i++) {
					if (i == pageNo) {
						paginationHtmls.append("<li  class=\"sr_pagination_item current\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
					} else {
						paginationHtmls.append("<li class=\"sr_pagination_item\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
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
						paginationHtmls.append("<li  class=\"sr_pagination_item current\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
					} else {
						paginationHtmls.append("<li class=\"sr_pagination_item\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
					}
				}
			}
			for (int i = startPageNo; i <= pageCount; i++) {
				if (i == pageNo) {
					paginationHtmls.append("<li  class=\"sr_pagination_item current\" ><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
				} else {
					paginationHtmls.append("<li class=\"sr_pagination_item\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
				}
			}
		}else{
			paginationHtmls.append("<li><a href=\""+url+"\" data-page=\""+page.getFirstPage()+"\">"+page.getFirstPage()+"</a></li>");
			paginationHtmls.append("<li class=\"disabled\"><a href=\""+url+"\">...</a></li>");
			int endPageNo = pageNo+3 >= pageCount ?  pageCount:pageNo+3;
			for (int i = pageNo-3; i <= endPageNo; i++) {
				if (i == pageNo) {
					paginationHtmls.append("<li class=\"sr_pagination_item\"><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
				} else {
					paginationHtmls.append("<li><a href=\""+url+"\" data-page=\""+i+"\">"+i+"</a></li>");
				}
			}
			paginationHtmls.append("<li class=\"disabled\"><a href=\""+url+"\">...</a></li>");
			paginationHtmls.append("<li class=\"sr_pagination_item\"><a href=\""+url+"\" data-page=\""+pageCount+"\">"+pageCount+"</a></li>");
		}
		paginationHtmls.append("</ul>");
		if(pageNo<pageCount){
			paginationHtmls.append("<a  class=\"paging-next ga_sr_gotopage_2_21\" href=\""+url+"\" data-page=\""+page.getNextPage()+"\">下一页<i class=\"ace-icon fa fa-angle-double-right\"></i></a>");
		}else{
			paginationHtmls.append("<span class=\"paging-end\">下一页</span>");
		}
		return paginationHtmls.toString();
	}
}
