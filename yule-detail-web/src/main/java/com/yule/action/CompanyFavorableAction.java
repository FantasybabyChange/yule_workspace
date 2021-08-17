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
import com.yule.detail.service.ICompanyFavorableService;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyFavorable;
@Controller
@Scope("prototype")
@RequestMapping("/companyFavorable")
public class CompanyFavorableAction extends BaseAction{
	
	@Autowired
	private ICompanyFavorableService companyFavorableServiceImpl;
	
	@RequestMapping(value="/findCompanyFavorable",method=RequestMethod.POST)
	public String findCompanyFavorable(@RequestParam(value="id",required=false)String id)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			 List<CompanyFavorable> companyFavorables = this.companyFavorableServiceImpl.findCompanyFavorableByCompanyId(id);
			 StringBuffer companyFavorableHTMLs = new StringBuffer();
			 if (companyFavorables != null && companyFavorables.size() > 0) {
				for (CompanyFavorable companyFavorable : companyFavorables) {
					companyFavorableHTMLs.append("<div class=\"description clearfix\" >");
					companyFavorableHTMLs.append("<div class=\"d-name\">"+companyFavorable.getName()+"</div>");	
					companyFavorableHTMLs.append("<div class=\"d-info\">"+companyFavorable.getContent()+"(优惠价格:"+companyFavorable.getPrice()+")</div>");
					companyFavorableHTMLs.append("</div>");
				}
			}else{
				companyFavorableHTMLs.append("<div class=\"description clearfix\" >暂无优惠</div>");
			}
			 object.put("companyFavorableHTMLs", companyFavorableHTMLs.toString());
			 companyFavorableHTMLs.setLength(0);
			 object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业优惠【findCompanyFavorable】出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
}
