package com.yule.action.company;


import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.exception.YuleException;
import com.yule.mongo.company.service.ICompanyCooperatorMongo;
import com.yule.mongo.pojo.CompanyCooperator;

@Controller
@Scope("prototype")
@RequestMapping("/companyCooperator")
public class CompanyCooperatorAction extends BaseAction{

	@Autowired
	private ICompanyCooperatorMongo companyCooperatorMongoImpl;
	@RequestMapping(value="/insertCompanyCooperator",method=RequestMethod.POST)
	public String insertCompanyCooperator(@ModelAttribute("companyCooperator")CompanyCooperator companyCooperator)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			boolean flag = companyCooperatorMongoImpl.insertCompanyCooperator(companyCooperator);
			jsonObject.put("flag", flag);
		} catch (Exception e) {
			new YuleException("新增企业合作出现【insertCompanyCooperator】异常", e);
			throw e;
		}finally{
			outputResult(jsonObject.toString());
		}
		return null;
	}
	
	
}
