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
import com.yule.mongo.cooperators.service.ICompanyCooperatorMongo;
import com.yule.mongo.pojo.CompanyCooperator;

@Controller
@Scope("prototype")
@RequestMapping("/companyCooperator")
public class CompanyCooperatorAction extends BaseAction{

	@Autowired
	private ICompanyCooperatorMongo companyCooperatorMongoImpl; 
	
	@RequestMapping(value = "/findCompanyCooperator", method = RequestMethod.GET)
	public String findCompanyCooperator() throws Exception{
		Object attribute = session.getAttribute("companyCooperator_status");
		if(attribute != null){
			if(attribute.toString().equals("success")){
				request.setAttribute("status", true);
			}
			session.removeAttribute("companyCooperator_status");
		}
		return "/company";
	}
	@RequestMapping(value = "/insertCompanyCooperator", method = RequestMethod.POST)
	public String insertCompanyCooperator(@ModelAttribute("companyCooperator")CompanyCooperator companyCooperator,@RequestParam(value="t",required=false)String t) throws Exception{
		boolean flag = false;
		try {
			if (session.getId().equals(t)) {
				flag = this.companyCooperatorMongoImpl.insertCompanyCooperator(companyCooperator);
				request.setAttribute("status", flag);
				session.setAttribute("companyCooperator_status","success");
			}
		} catch (Exception e) {
			new YuleException("新增企业合作出现【insertCompanyCooperator】异常", e);
		}
		return ActionReturnConst.REDIRECT+"findCompanyCooperator.do";
	}
}
