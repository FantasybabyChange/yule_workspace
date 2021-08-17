package com.yule.action.salesman;


import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.exception.YuleException;
import com.yule.mongo.pojo.SalesmanCooperator;
import com.yule.mongo.salesman.service.ISalesmanCooperatorMongo;

@Controller
@Scope("prototype")
@RequestMapping("/salesmanCooperator")
public class SalesmanCooperatorAction extends BaseAction{
	
	@Autowired
	private ISalesmanCooperatorMongo salesmanCooperatorMongo;
	
	@RequestMapping(value="/insertSalesmanCooperator",method=RequestMethod.POST)
	public String insertSalesmanCooperator(@ModelAttribute("salesmanCooperator")SalesmanCooperator salesmanCooperator)throws Exception{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = salesmanCooperatorMongo.insertSalesmanCooperator(salesmanCooperator);
			jsonObject.put("flag", flag);
		} catch (Exception e) {
			new YuleException("新增用户合作出现【insertCompanyCooperator】异常", e);
			throw e;
		}finally{
			outputResult(jsonObject.toString());
		}
		return null;
	}
}
