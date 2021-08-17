package com.yule.action.company;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.service.ICompanyAddressService;
import com.yule.admin.service.ICompanyService;
import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.constant.FileUploadConst;
import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.pojo.CompanyAddress;
import com.yule.vo.CompanyAddressVO;

@Controller
@Scope("prototype")
@RequestMapping("/companyAddress")
public class CompanyAddressAction extends BaseAction{

	@Autowired
	private ICompanyAddressService companyAddressServiceImpl;
	@Autowired 
	private ICompanyService companyServiceImpl;
    @RequestMapping(value = "/findCompanyAddress",method = RequestMethod.GET)
    public String findCompanyAddress(@RequestParam(value="id",required=false)String id) throws Exception{
    	CompanyAddressVO companyAddressVO = companyAddressServiceImpl.findCompanyAddressVOById(id);
    	Company company = companyServiceImpl.findCompanyById(id);
    	String image_path = FileUploadConst.COMPANY_IMAGE_PATH+id+"/"+FileUploadConst.PX_50_50+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE;
    	request.setAttribute("image_path", image_path);
    	request.setAttribute("companyUser", company);
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
			boolean flag = companyAddressServiceImpl.updateCompanyAddress(companyAddress);
			if (flag) {
				jsobObject.put("id",companyAddress.getId());
			}
			jsobObject.put("flag", flag);
			jsobObject.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("新增企业地址【insertCompanyAddress】出现异常",e);
			throw e;
		} finally{
			outputResult(jsobObject.toString());
		}
		return null;
	}
	
	

}