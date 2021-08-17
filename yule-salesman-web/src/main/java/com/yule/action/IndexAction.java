package com.yule.action;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.FileUploadConst;
import com.yule.exception.YuleException;
import com.yule.salesman.vo.SalesmanVO;

@Controller
@Scope("prototype")
public class IndexAction extends BaseAction {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() throws Exception{
		try{
		}catch(Exception e){
			new YuleException(e);
			e.printStackTrace();
		}finally{
			request.setAttribute("mainPath","/home.do");
		}
		return "/company/index";
	}
	
	@RequestMapping(value = "/findSalesman", method = RequestMethod.POST)
	public String findSalesman()throws Exception{
		JSONObject json = new JSONObject();
		try {
			SalesmanVO salesman = getSalesman();
			salesman.setImage_path(FileUploadConst.SALESMAN_IMAGE_PATH+salesman.getId()+"/"+FileUploadConst.PX_50_50+FileUploadConst.SALESMAN_HEAD+FileUploadConst.IMAGE_TYPE);
			json.put("image_path", salesman.getImage_path());
			json.put("account", salesman.getAccount());
		} catch (Exception e) {
			new YuleException("查询当前业务人员信息【findSalesman】出现异常",e);
			throw e;
		}finally{
			outputResult(json.toString());
		}
		return null;
	}
	
}
