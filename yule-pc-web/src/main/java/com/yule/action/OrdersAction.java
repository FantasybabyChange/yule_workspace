package com.yule.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.FileUploadConst;
import com.yule.constant.PageConst;
import com.yule.mongo.pc.service.IOrdersMongo;
import com.yule.mongo.pojo.Orders;

@Controller
@Scope("prototype")
public class OrdersAction extends BaseAction{
	
	@Autowired
	private IOrdersMongo ordersMongoImpl;

	@RequestMapping(value = "/orders",method = RequestMethod.POST)
	public String orders() throws Exception {
		String areaProvinceId = getAreaCookieValue();
		areaProvinceId = areaProvinceId.substring(0,3)+"000";
		List<Orders> orderss = ordersMongoImpl.findOrdersList(areaProvinceId,PageConst.PAGE_SIZE_TEN, PageConst.PAGE_NO_DEFAULT);
		if(null!=orderss&&orderss.size()>0){
			StringBuffer htmls = new StringBuffer("");
			for(Orders orders : orderss){
				htmls.append("<li class=\"book\">");
				htmls.append("<div class=\"activity_box\">");
				htmls.append("<div class=\"image\">");
				htmls.append("<img src=\"http://images.yuleing.com/loading/loading.jpg\" data-original=\""+FileUploadConst.COMPANY_IMAGE_PATH+orders.getCompany_id()+"/"+FileUploadConst.PX_50_50+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE+"\">");
				htmls.append("</div>");
				htmls.append("<p class=\"booker_from\">1位来自"+orders.getUser_area_city_name()+"的客人</p>刚刚预订了<a target=\"_blank\" href=\"http://detail.yuleing.com/company/findCompanyDetails.do?id="+orders.getCompany_id()+"\">"+orders.getCompany_name()+"</a>");
				htmls.append("</div>");
				htmls.append("</li>");
			}
			outputResult(htmls.toString());
		}
		return null;
	}
	
}