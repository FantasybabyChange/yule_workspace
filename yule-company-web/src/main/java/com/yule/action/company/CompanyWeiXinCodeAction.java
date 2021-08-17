package com.yule.action.company;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.exception.YuleException;

@Controller
@Scope("prototype")
@RequestMapping("/companyWeixin")
public class CompanyWeiXinCodeAction extends BaseAction{

	/**
	 * 查询企业选中服务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCompanyWeiXinCode",method = RequestMethod.GET)
	public String findCompanyCheckServe() throws Exception {
		try {
			request.setAttribute("id", getCompanyUser().getId());
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/company/weixin/code";
	}
	
}
