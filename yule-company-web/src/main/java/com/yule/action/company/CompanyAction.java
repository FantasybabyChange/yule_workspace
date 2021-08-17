package com.yule.action.company;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yule.common.BaseAction;
import com.yule.company.service.ICompanyService;
import com.yule.constant.FileUploadConst;
import com.yule.constant.PayConst;
import com.yule.enumerate.DecimalEnum;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.util.CompanyLogUtil;
import com.yule.util.DecimalUtil;
import com.yule.vo.CompanyVO;

@Controller
@Scope("prototype")
@RequestMapping("/company")
public class CompanyAction extends BaseAction{

	@Autowired
	private ICompanyService companyServiceImpl;
	
	@RequestMapping(value = "/findCompany",method = RequestMethod.GET)
	public String findCompany() throws Exception {
    	try {
        	CompanyVO companyVO = companyServiceImpl.findCompanyVOById(getCompanyUser().getCompany_id());
           	companyVO.setBusiness_license_image(FileUploadConst.COMPANY_IMAGE_PATH+companyVO.getId()+"/"+FileUploadConst.PX_150_150+FileUploadConst.BUSINESS_LICENSE+FileUploadConst.IMAGE_TYPE);
        	companyVO.setLegal_person_image(FileUploadConst.COMPANY_IMAGE_PATH+companyVO.getId()+"/"+FileUploadConst.PX_150_150+FileUploadConst.LEGAL_PERSON+FileUploadConst.IMAGE_TYPE);
        	companyVO.setImage_path(FileUploadConst.COMPANY_IMAGE_PATH+companyVO.getId()+"/"+FileUploadConst.PX_150_150+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE);
        	if (!StringUtils.isEmpty(companyVO.getPay_type())) {
        		companyVO.setPay_type_value(PayConst.PAY_TYPE[companyVO.getPay_type()]);
			}else{
				companyVO.setPay_type_value("暂无选择支付方式");
			}
        	if (null!=companyVO.getRebast()) {
    			companyVO.setRebast(DecimalUtil.parseFloat(companyVO.getRebast(), DecimalEnum.FLOAT)+"折");
    		}else{
    			companyVO.setRebast("优惠价");
    		}
        	request.setAttribute("company", companyVO);
        	
        	CompanyLogUtil.insertLog("根据企业id查询企业信息", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("根据企业id查询企业信息[findCompany]错误",e);
			throw e;
		} 
    	return "/company/index";
	}
	
	/**
	 * 更新企业
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompany",method = RequestMethod.POST)
	public String updateCompany(@ModelAttribute("company")Company company) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			company.setId(getCompanyUser().getCompany_id());
			boolean flag=companyServiceImpl.updateCompany(company);
			jsonObject.put("flag", flag);
			outputResult(jsonObject.toString());
			CompanyLogUtil.insertLog("更新企业", getCompanyUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新企业[updateCompany]错误",e);
			throw e;
		} 
		return null;
	}

	/**
	 * 查询同市同种企业
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findSameCompany",method = RequestMethod.POST)
	@ResponseBody
	public String findSameCompany() throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			List<Company> companies=this.companyServiceImpl.findCompanySame(getCompanyUser().getCompany_id());
			if (null!=companies&&companies.size()>0) {
				JSONArray array = new JSONArray();
				JSONObject object = null;
				for (Company company :companies) {
					object = new JSONObject();
					if (!StringUtils.isEmpty(company.getName())) {
						object.put("label",company.getName());	
					}
					object.put("id", company.getId());
					object.put("pinyin", company.getPinyin());
					array.add(object);
					object.clear();
				}
				object.put("companies", array);
				outputResult(object.toString());
				companies.clear();
				companies= null;
				array.clear();
				array = null;
				object.clear();
				object =null;
			}
		} catch (Exception e) {
			new YuleException("查询同市同种企业[findSameCompany]错误",e);
			throw e;
		} 
		return jsonObject.toString();
	}
	
}