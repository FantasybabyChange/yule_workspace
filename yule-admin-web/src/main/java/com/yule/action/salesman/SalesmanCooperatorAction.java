package com.yule.action.salesman;

import java.util.List;

import net.sf.json.JSONObject;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.CooperatorConst;
import com.yule.constant.DeleteConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PageConst;
import com.yule.constant.PrivilegeConst;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.query.SalesmanCooperatorQuery;
import com.yule.mongo.admin.service.ISalesmanCooperatorMongo;
import com.yule.mongo.pojo.SalesmanCooperator;
import com.yule.pojo.AdminPrivilege;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/salesmanCooperator")
public class SalesmanCooperatorAction extends BaseAction{

	@Autowired
	private ISalesmanCooperatorMongo salesmanCooperatorMongoImpl;
	
	/**
	 * 查询合作企业
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findSalesmanCooperator.do",method = RequestMethod.GET)
	public String findCompanyCooperator(SalesmanCooperatorQuery salesmanCooperatorQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception {
		 if(null == pageNo){
			 pageNo=1;
		 }
		 try{
		StringBuffer htmls = new StringBuffer("");
        Page<SalesmanCooperator> page= this.salesmanCooperatorMongoImpl.findSalesmanCooperatorPage(salesmanCooperatorQuery,PageConst.PAGE_SIZE_TEN, pageNo);
		List<SalesmanCooperator> lists = page.getDatas();
		htmls.append("<tfoot>");
		htmls.append("<tr>");
		htmls.append("<td colspan=\"6\">");
		htmls.append("<div class=\"bulk-actions align-left\">");
		htmls.append("</div>");
		htmls.append(PaginationUtil.getPaginationHtml(page));
		htmls.append("<div class=\"clear\"></div>");
		htmls.append("</td>");
		htmls.append("</tr>");
		htmls.append("</tfoot>");
		htmls.append("<tbody>");
		if (null != lists && lists.size() > 0) {
			AdminPrivilege adminPrivilege = null;
			int status;
			for (SalesmanCooperator salesmanCooperator : lists) {
				status = salesmanCooperator.getStatus();
				htmls.append("<tr>");
				htmls.append("<td>" + salesmanCooperator.getAccount()+ "</td>");
				htmls.append("<td>" + salesmanCooperator.getPhone() + "</td>");
				htmls.append("<td>" + salesmanCooperator.getMail() + "</td>");
				htmls.append("<td class=\"is_delete\">" + CooperatorConst.COOPERATOR[status] + "</td>");
				htmls.append("<td>");
				if(CooperatorConst.IS_COOPERATOR_FALSE==status){
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE_STATUS)){	
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE_STATUS);
						htmls.append("&nbsp;&nbsp;<a class=\"button\" data-name=\"is_delete\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl()+"\" data-id=\"" + salesmanCooperator.getId() + "\" data-status=\""+CooperatorConst.IS_COOPERATOR_TRUE+"\" >"+CooperatorConst.BUTTON_COOPERATOR[CooperatorConst.IS_COOPERATOR_FALSE]+"</a>");
					}
				}
				htmls.append("</td>");
				htmls.append("</tr>");
			}
			lists.clear();
			lists = null;
		} else {
			htmls.append("<tr>");
			htmls.append("<td style=\"text-align: center;\" colspan=\"8\">暂无数据</td>");
			htmls.append("</tr>");
		}
		htmls.append("</tbody>");
		request.setAttribute("salesmanCooperatorQuery", salesmanCooperatorQuery);
		request.setAttribute("htmls", htmls);
	} catch (Exception e) {
		new YuleException(e);
		throw e;
	}
	return "/salesman/cooperator/index";
	}
	
	@RequestMapping(value = "/updateSalesmanCooperator")
	public String updateCompanyCooperatorStatus(@RequestParam(value="is_delete",required=false)Integer status,@RequestParam(value="id",required=false)String id) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			SalesmanCooperator salesmanCooperator =  new SalesmanCooperator(); 
			salesmanCooperator.setId(new ObjectId(id));
			salesmanCooperator.setStatus(status);
			boolean flag = salesmanCooperatorMongoImpl.updateSalesmanCooperator(salesmanCooperator);
			obj.put("status", flag);
			obj.put("value",DeleteConst.BUTTON_DELETE_VALUE[status]);
			obj.put("is_delete_text",CooperatorConst.COOPERATOR[status]);
			obj.put("text",CooperatorConst.BUTTON_COOPERATOR[status]);
		} catch (Exception e) {
			new YuleException("更新合作关系【updateCompanyCooperatorStatus】", e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
