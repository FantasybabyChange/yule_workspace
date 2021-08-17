package com.yule.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.exception.YuleException;
import com.yule.mongo.cooperators.service.ISalesmanCooperatorMongo;
import com.yule.mongo.pojo.SalesmanCooperator;

@Controller
@Scope("prototype")
@RequestMapping("/salesmanCooperator")
public class SalesmanCooperatorAction extends BaseAction{

	@Autowired
	private ISalesmanCooperatorMongo salesmanCooperatorMongoImpl;
	
	@RequestMapping(value = "/findSalesmanCooperator" ,method = RequestMethod.GET)
	public String findSalesmanCooperator() throws Exception{
		Object attribute = session.getAttribute("salesmanCooperator_status");
		if(attribute != null){
			if(attribute.toString().equals("success")){
				request.setAttribute("status", true);
			}
			session.removeAttribute("salesmanCooperator_status");
		}
		return "/salesman";
	}
	
	@RequestMapping(value = "/insertSalesmanCooperator" ,method = RequestMethod.POST)
	public String insertSalesmanCooperator(@ModelAttribute("salesmanCooperator")SalesmanCooperator salesmanCooperator,@RequestParam(value="t",required=false)String t) throws Exception{
		boolean flag = false;
		try {
			if (session.getId().equals(t)) {
			    flag = this.salesmanCooperatorMongoImpl.insertSalesmanCooperator(salesmanCooperator);
				request.setAttribute("status", flag);
				session.setAttribute("salesmanCooperator_status","success");
			}
		} catch (Exception e) {
			new YuleException("新增业务员合作出现【insertSalesmanCooperator】异常", e);
		}
		return ActionReturnConst.REDIRECT+"findSalesmanCooperator.do";
	}
}
