package com.yule.action.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.company.service.ICompanyServeService;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.CheckConst;
import com.yule.exception.YuleException;
import com.yule.vo.CompanyServeVO;

@Controller
@Scope("prototype")
@RequestMapping("/companyServe")
public class CompanyServeAction extends BaseAction{

	@Autowired
	private ICompanyServeService companyServeServiceImpl;
	
	/**
	 * 查询企业选中服务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCompanyServe",method = RequestMethod.GET)
	public String findCompanyCheckServe() throws Exception {
		try {
			List<CompanyServeVO> companyServeVOs = companyServeServiceImpl.findCompanyAndServeList(getCompanyUser().getCompany_id());
			StringBuffer htmls = new StringBuffer();
			if(null != companyServeVOs && companyServeVOs.size()>0){
				htmls.append("<div class=\"checkbox\">");
				int count = 1;
				for (CompanyServeVO companyServeVO : companyServeVOs) {
					htmls.append("<label class=\"block\" style=\"float:left;\">");

					if(CheckConst.IS_CHECK_TRUE==companyServeVO.getIs_check()){
						htmls.append("<input name=\"company_serve_id\" checked=\"checked\" type=\"checkbox\" class=\"ace\" value=\""+companyServeVO.getId()+"\" />");
					}else{
						htmls.append("<input name=\"company_serve_id\" type=\"checkbox\" class=\"ace\" value=\""+companyServeVO.getId()+"\" />");
					}
					htmls.append("<span class=\"lbl\"> "+companyServeVO.getName()+"</span>");
					if(count % 10 != 0){
					htmls.append("&nbsp;&nbsp;&nbsp;&nbsp;");
					}
					count++;
					htmls.append("</label>");
				}
				htmls.append("</div>");
				companyServeVOs.clear();
				companyServeVOs=null;
			}
			request.setAttribute("htmls", htmls);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/company/serve/index";
	}
	
	/**
	 * 更新企业分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompanyServe",method = RequestMethod.POST)
	public String updateCompanyCheckServe(@RequestParam(value="company_serve_id",required=false)List<String> companyServeIds) throws Exception {
		try {
			String id = getCompanyUser().getId();
			companyServeServiceImpl.updateCompanyCheckServe(id, companyServeIds);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/companyServe/findCompanyServe.do";
	}
	
}
