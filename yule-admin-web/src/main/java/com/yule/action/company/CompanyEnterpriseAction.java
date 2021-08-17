package com.yule.action.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.admin.service.ICompanyEnterpriseService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyEnterprise;
import com.yule.util.AdminLogUtil;

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
		try {
			this.companyEnterpriseServiceImpl.updateCompanyEnterprise(companyEnterprise);
			AdminLogUtil.insertLog("更新企业公司", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新企业公司[updateCompanyEnterprise]错误",e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/company/findCompanyById.do?id="+companyEnterprise.getId();
	}
}
