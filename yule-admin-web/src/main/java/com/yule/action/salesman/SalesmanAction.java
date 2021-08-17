package com.yule.action.salesman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.service.ISalesmanService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.FileUploadConst;
import com.yule.exception.YuleException;
import com.yule.pojo.Salesman;
@Controller
@Scope("prototype")
@RequestMapping("/salesman")
public class SalesmanAction extends BaseAction {
	
	@Autowired
	private ISalesmanService salesmanServiceImpl;
	@RequestMapping(value = "/findSalesman", method = RequestMethod.GET)
	public String findSalesman(@RequestParam(value="id",required=false) String id) throws Exception{
		try {
			Salesman salesman = this.salesmanServiceImpl.findSalesmanById(id);
			salesman.setImage_path(FileUploadConst.SALESMAN_IMAGE_PATH+salesman.getId()+"/"+FileUploadConst.PX_150_150+FileUploadConst.SALESMAN_HEAD+FileUploadConst.IMAGE_TYPE);
			salesman.setIdentity_card_image_path(FileUploadConst.SALESMAN_IMAGE_PATH+salesman.getId()+"/"+FileUploadConst.PX_150_150+FileUploadConst.SALESMAN_IDCARD+FileUploadConst.IMAGE_TYPE);
			request.setAttribute("salesman", salesman);
		} catch (Exception e) {
			new YuleException("查找业务员【findSalesman】出现异常",e);
			throw e;
		}
		return "/salesman/salesmanInfo";
	}
	/**
	 * 更新企业
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSalesman",method = RequestMethod.POST)
	public String updateCompany(@ModelAttribute("salesman")Salesman salesman) throws Exception {
		try {
			this.salesmanServiceImpl.updateSalesman(salesman);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/salesman/findSalesman.do?id="+salesman.getId();
	}
	
	
}
