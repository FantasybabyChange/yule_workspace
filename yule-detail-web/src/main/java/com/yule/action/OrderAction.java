package com.yule.action;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.vo.OrdersProductVO;
@Controller
@Scope("prototype")
@RequestMapping("/order")
public class OrderAction extends BaseAction{
	@RequestMapping(value="/insertOrderInfo",method=RequestMethod.POST)
	public String insertOrderInfo(@ModelAttribute(value="ordersProduct")OrdersProductVO ordersProduct)throws Exception{
		JSONObject object =new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			String uuId = getOrdersProduct(ordersProduct);
			if(!StringUtils.isEmpty(uuId)){
				object.put("uuId", uuId);
				object.put("status", ErrorConst.STATUS_SUCCESS);	
			}
		} catch (Exception e) {
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
}
