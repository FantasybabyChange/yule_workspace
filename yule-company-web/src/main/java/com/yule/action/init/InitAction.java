package com.yule.action.init;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.company.service.ICompanyAddressService;
import com.yule.company.service.ICompanyService;
import com.yule.company.vo.CompanyUserVO;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.AuthConst;
import com.yule.constant.CompanyFlowConst;
import com.yule.constant.CompanySessionConst;
import com.yule.constant.ErrorConst;
import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.pojo.CompanyAddress;
import com.yule.vo.CompanyAddressVO;

@Controller
@Scope("prototype")
@RequestMapping("/init")
public class  InitAction extends BaseAction{

	@Autowired
	private ICompanyService companyServiceImpl;
	
	@Autowired
	private ICompanyAddressService companyAddressServiceImpl;
	
	/**
	 * 企业基本信息
	 */
	@RequestMapping(value="/findCompany.do",method = RequestMethod.GET)
	public String findCompany()throws Exception{
		try {
			CompanyUserVO companyUser = getCompanyUser();
    		request.setAttribute("company_id", companyUser.getCompany_id());
		} catch (Exception e) {
			throw e;
		}
		return "/init/company";
	}

	/**
	 * 更新企业基本信息
	 */
	@RequestMapping(value="/updateCompany",method = RequestMethod.POST)
	public String updateCompany(Company company)throws Exception{
		try {
			CompanyUserVO companyUser = getCompanyUser();
			company.setId(companyUser.getCompany_id());
			company.setIs_auth(AuthConst.IS_AUTH_TRUE);
			companyServiceImpl.updateCompany(company);
			companyUser.setInfo_is_auth(AuthConst.IS_AUTH_TRUE);
    		setCompanyUser(companyUser);
    		session.setAttribute(CompanySessionConst.COMPANY_FLOW+companyUser.getId(), CompanyFlowConst.COMPANY_FLOW_TWO);
		} catch (Exception e) {
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/init/findCompanyAddress.do";
	}
	
	/**
	 * 企业地理信息
	 */
	@RequestMapping(value="/findCompanyAddress.do",method = RequestMethod.GET)
	public String findCompanyAddress()throws Exception{
		try {
			CompanyAddressVO companyAddressVO = companyAddressServiceImpl.findCompanyAddressVOById(getCompanyUser().getCompany_id());
			CompanyUserVO companyUser = getCompanyUser();
			request.setAttribute("companyUser", companyUser);
			request.setAttribute("companyAddressVO", companyAddressVO);
		} catch (Exception e) {
			throw e;
		}
		return "/init/companyAddress";
	}

	/**
	 * 更新企业地理信息
	 */
	@RequestMapping(value="/companyAddress",method = RequestMethod.POST)
	public String companyAddressInfo()throws Exception{
		try {
			CompanyUserVO companyUserVO = getCompanyUser();
			CompanyAddress companyAddress = new CompanyAddress();
			companyAddress.setIs_auth(AuthConst.IS_AUTH_TRUE);
			companyAddress.setId(companyUserVO.getCompany_id());
			companyAddressServiceImpl.updateCompanyAddress(companyAddress);
			companyUserVO.setAddress_is_auth(AuthConst.IS_AUTH_TRUE);
			setCompanyUser(companyUserVO);
			session.setAttribute(CompanySessionConst.COMPANY_FLOW+companyUserVO.getId(), CompanyFlowConst.COMPANY_FLOW_THREE);
		} catch (Exception e) {
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/index.do";
	}
	
	/**
	 * 更新企业
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompanyAddress",method = RequestMethod.POST)
	public String updateCompanyAddress(@ModelAttribute("companyAddress")CompanyAddress companyAddress) throws Exception {
		JSONObject jsobObject = new JSONObject();
		jsobObject.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyAddressServiceImpl.updateCompanyAddress(companyAddress,getCompanyUser().getId());
			if (flag) {
				jsobObject.put("id",companyAddress.getId());
			}
			jsobObject.put("flag", flag);
			jsobObject.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("新增企业地址【updateCompanyAddress】出现异常",e);
		} finally{
			outputResult(jsobObject.toString());
		}
		return null;
	}
	
	/**
	 * 验证邮箱是否存在
	 */
	@RequestMapping(value="/verifyCompany",method = RequestMethod.POST)
	public String verifyCompany(Company company)throws Exception{
		JSONObject obj=new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			int count = this.companyServiceImpl.findCompanyCountByMailOrPhone(company);
			if (count>0) {
				if (null!=company.getMail()) {
					obj.put("message","该邮箱已存在");
				}
				if (null!=company.getPhone()) {
					obj.put("message","该手机号已存在");
				}
			}else{
				obj.put("status",ErrorConst.STATUS_SUCCESS);
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			outputResult(obj.toString());
			obj.clear();
			obj = null;
		}
		return null;
	}
	
}
