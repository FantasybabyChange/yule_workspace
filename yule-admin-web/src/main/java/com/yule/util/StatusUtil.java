package com.yule.util;

import com.yule.constant.StatusConst;

public class StatusUtil {
	public static String getStatusHtml() {
		StringBuffer htmls = new StringBuffer("");
		htmls.append("<select id=\"status\" name=\"status\">");
		String[] status = StatusConst.STATUS;
		for (int i = 0; i < status.length; i++) {
			htmls.append("<option value=\"" + i + "\">" + status[i]+ "</option>");
		}
		htmls.append("</select>");
		return htmls.toString();
	}
}
