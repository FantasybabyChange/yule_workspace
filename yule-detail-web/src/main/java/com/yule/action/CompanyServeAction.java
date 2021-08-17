package com.yule.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.detail.service.ICompanyServeService;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyServe;
@Controller
@Scope("prototype")
@RequestMapping("/companyServe")
public class CompanyServeAction extends BaseAction{
	
	@Autowired
	private ICompanyServeService companyServeServiceImpl;
	
	@RequestMapping(value="/findCompanyServer",method=RequestMethod.POST)
	public String findCompanyServer(@RequestParam(value="id",required=false)String id)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			 List<CompanyServe> companyServes = this.companyServeServiceImpl.findCompanyServeByCompanyId(id);
			 StringBuffer companyServeHTMLs = new StringBuffer();
			 if (companyServes != null && companyServes.size() > 0) {
				for (CompanyServe productVO : companyServes) {
					companyServeHTMLs.append(productVO.getName()+",");
				}
				if (companyServeHTMLs.length() > 0) {
					companyServeHTMLs.setLength(companyServeHTMLs.length() - 1 );
				}
			}else{
				companyServeHTMLs.append("<tr><td colspan=\"6\">暂无服务设施</td></tr>");
			}
			 object.put("companyServeHTMLs", companyServeHTMLs.toString());
			companyServeHTMLs.setLength(0);
			 object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询产品详细信息【findProduct】出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
}
