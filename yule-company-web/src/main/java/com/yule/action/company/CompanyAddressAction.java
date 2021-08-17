package com.yule.action.company;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.company.service.ICompanyAddressService;
import com.yule.company.vo.CompanyUserVO;
import com.yule.constant.ErrorConst;
import com.yule.constant.FileUploadConst;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyAddress;
import com.yule.vo.CompanyAddressVO;

@Controller
@Scope("prototype")
@RequestMapping("/companyAddress")
public class CompanyAddressAction extends BaseAction{

	@Autowired
	private ICompanyAddressService companyAddressServiceImpl;
	
    @RequestMapping(value = "/findCompanyAddress",method = RequestMethod.GET)
    public String findCompanyAddress() throws Exception{
    	CompanyAddressVO companyAddressVO = companyAddressServiceImpl.findCompanyAddressVOById(getCompanyUser().getCompany_id());
    	CompanyUserVO companyUser = getCompanyUser();
    	String image_path = FileUploadConst.COMPANY_IMAGE_PATH+companyUser.getCompany_id()+"/"+FileUploadConst.PX_50_50+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE;
    	companyUser.setImage_path(image_path);
    	request.setAttribute("companyUser", companyUser);
    	request.setAttribute("companyAddressVO", companyAddressVO);
    	return "/company/address/index";
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
			new YuleException("新增企业地址【insertCompanyAddress】出现异常",e);
		} finally{
			outputResult(jsobObject.toString());
		}
		return null;
	}
	
	

}