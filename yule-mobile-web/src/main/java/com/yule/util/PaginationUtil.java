package com.yule.util;



public class PaginationUtil {

	
	public static String getPaginationHtml(int nowPage,int rowCount ,int pageSize ) {
		StringBuffer paginationHtmls =new StringBuffer();
		int pageCount = 0;
		if (rowCount % pageSize == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = rowCount / pageSize + 1;
		}
		if(pageCount>1){
			if(nowPage==1&&nowPage<pageCount){
				paginationHtmls.append("<li class=\"mui-disabled\"><span> 上一页 </span></li>");
				paginationHtmls.append("<li><a href=\"javascript:;\" data-page=\"2\">下一页</a></li>");
			}
			if (nowPage>1&&nowPage<pageCount) {
				 paginationHtmls.append("<li><a href=\"javascript:;\" data-page=\""+(nowPage-1)+"\">上一页</a></li>");
				 paginationHtmls.append("<li><a href=\"javascript:;\" data-page=\""+(nowPage+1)+"\">下一页</a></li>");
			}
			 if (nowPage>1&&nowPage==pageCount) {
				 paginationHtmls.append("<li><a href=\"javascript:;\" data-page=\""+(nowPage-1)+"\">上一页</a></li>");
				 paginationHtmls.append("<li class=\"mui-disabled\"><span> 下一页 </span></li>");
			 }
		}else{
			paginationHtmls.append("<li class=\"mui-disabled\"><span> 上一页 </span></li>");
			paginationHtmls.append("<li class=\"mui-disabled\"><span> 下一页 </span></li>");

		}
		return paginationHtmls.toString();
	}
}
