package com.yule.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.IndexMemcachedConst;
import com.yule.memcached.constant.MemcachedConst;
import com.yule.memcached.util.MemcachedUtil;
import com.yule.util.AreaUtil;

@Controller
@Scope("prototype")
public class AreaAction extends BaseAction{
	
	@RequestMapping(value = "/findAreaCityNav",method = RequestMethod.POST)
	public String findAreaCityNav() throws Exception{
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		memcachedUtil.delete(MemcachedConst.INDEX_NAV_CITY);
		if(!memcachedUtil.keyExists(MemcachedConst.INDEX_NAV_CITY)){
			AreaUtil.initAreaCityNav();
		}
		String areaProvinceId = getAreaCookieValue();
		areaProvinceId = areaProvinceId.substring(0,3)+"000";
		String navHot = AreaUtil.initAreaCityHotNav(areaProvinceId);
		StringBuffer htmls = new StringBuffer();
		if(!StringUtils.isEmpty(navHot)){
			htmls.append("<div class=\"destination-section domestic-destination has-no-tab\">");
			htmls.append("<div class=\"tab-header\">");
			htmls.append("精选城市");
			htmls.append("<div class=\"destination-tab-clear\"></div>");
	        htmls.append("</div>");
			htmls.append("<div class=\"tab-content clearfix\">");
			htmls.append("<div class=\"tab-panel tab-panel-active\">");
			htmls.append("<ul class=\"destination-area\">");
			htmls.append(navHot.toString());
			htmls.append("</ul>");
			htmls.append("</div>");
			htmls.append("</div>");
			htmls.append("</div>");
		}
		htmls.append("<div class=\"destination-section abroad-destination\">");
		htmls.append("<div class=\"tab-header\">");
		htmls.append("热门城市");
		htmls.append("</div>");
		htmls.append("<ul class=\"tab-nav\">");
		htmls.append("<li class=\"tab-nav-item tab-nav-item-active\"><a href=\"javascript:;\">热门</a></li>");
		htmls.append("<li class=\"tab-nav-item \"><a href=\"javascript:;\">ABCD</a></li>");
		htmls.append("<li class=\"tab-nav-item \"><a href=\"javascript:;\">EGHJ</a></li>");
		htmls.append("<li class=\"tab-nav-item \"><a href=\"javascript:;\">KLMNP</a></li>");
		htmls.append("<li class=\"tab-nav-item \"><a href=\"javascript:;\">QRSTW</a></li>");
		htmls.append("<li class=\"tab-nav-item \"><a href=\"javascript:;\">XYZ</a></li>");
		htmls.append("</ul>");
		htmls.append("<div class=\"tab-content pane-wrapper\">");
		htmls.append(memcachedUtil.get(MemcachedConst.INDEX_NAV_CITY));
		htmls.append("</div>");
		htmls.append("</div>");
		outputResult(htmls.toString());
		htmls.setLength(0);
	    return null;
	}
	
	@RequestMapping(value = "/findAreaCityHot",method = RequestMethod.POST)
	public String findAreaCityHot() throws Exception{
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		memcachedUtil.delete(IndexMemcachedConst.INDEX_HOT_CITY);
		if(!memcachedUtil.keyExists(IndexMemcachedConst.INDEX_HOT_CITY)){
			AreaUtil.initAreaCityHot();
		}
		Object indexHotCity = memcachedUtil.get(IndexMemcachedConst.INDEX_HOT_CITY);
		if(null!=indexHotCity){
			outputResult(indexHotCity.toString());
		}
	    return null;
	}
	
}