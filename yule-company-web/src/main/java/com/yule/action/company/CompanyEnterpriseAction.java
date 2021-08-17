package com.yule.action.company;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.company.service.ICompanyEnterpriseService;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyEnterprise;
import com.yule.util.CompanyLogUtil;

@Controller
@Scope("prototype")
@RequestMapping("/companyEnterprise")
public class CompanyEnterpriseAction extends BaseAction{

	@Autowired
	private ICompanyEnterpriseService companyEnterpriseServiceImpl;
	
	/**
	 * 更新企业公司
	 */
	@RequestMapping(value = "/updateCompanyEnterprise",method = RequestMethod.POST)
	public String updateCompanyExpensePrice(@ModelAttribute("companyEnterprise")CompanyEnterprise companyEnterprise) throws Exception {
		JSONObject obj= new JSONObject();
		try {
			companyEnterprise.setId(getCompanyUser().getCompany_id());
			boolean flag =this.companyEnterpriseServiceImpl.updateCompanyEnterprise(companyEnterprise);
			obj.put("flag", flag);
			CompanyLogUtil.insertLog("更新企业公司", getCompanyUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新企业公司[updateCompanyEnterprise]错误",e);
			throw e;
		}finally{
			outputResult(obj.toString());
			obj.clear();
			obj = null;
		}
		return null;
	}
}
