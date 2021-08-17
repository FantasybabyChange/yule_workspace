package com.yule.action.company;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.company.service.ICompanyCommentMongo;
import com.yule.mongo.company.service.ICompanyCommentPointMongo;
import com.yule.mongo.company.vo.CompanyCommentPointVO;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.mongo.query.CompanyCommentQuery;
import com.yule.util.CompanyCommentUtil;
import com.yule.util.CompanyLogUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/companyComment")
public class CompanyCommentAction extends BaseAction{

	@Autowired
	private ICompanyCommentMongo companyCommentMongoImpl;
	@Autowired
	private ICompanyCommentPointMongo companyCommentPointMongoImpl;
	/**
	 * 查询企业评论
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCompanyComment")
	public String findCompanyComment(CompanyCommentQuery companyCommentQuery,@RequestParam(value="pageNo",required=false)Integer pageNo) throws Exception {
		if(null==pageNo){
			return "company/comment/index";
		}
		if(null==companyCommentQuery){
			companyCommentQuery = new CompanyCommentQuery();
		}
		companyCommentQuery.setCompany_id(getCompanyUser().getCompany_id());
		if(StringUtils.isEmpty(companyCommentQuery.getUser_name())){
			companyCommentQuery.setUser_name("");
		}
		JSONObject obj = new JSONObject();
		try {
			Page<CompanyCommentPointVO> page = this.companyCommentPointMongoImpl.findCompanyCommentPointList(companyCommentQuery,pageNo,PageConst.PAGE_SIZE_TEN);
			StringBuffer tfoot = new StringBuffer();
			StringBuffer tbody = new StringBuffer();
			tfoot.append("<tr><td colspan=\"7\">");
			tfoot.append("<div class=\"bulk-actions align-left\"></div>");
			tfoot.append(PaginationUtil.getPaginationHtml(page));
			tfoot.append("<div class=\"clear\"></div>");
			tfoot.append("</td></tr>");
			if(page.getRowCount()>0){
				CompanyCommentUtil.getCommentPointHTML(tbody, page.getDatas());
			}else{
				tbody.append("<tr>");
				tbody.append("<td colspan=\"7\">没有数据</td>");
				tbody.append("</tr>");	
			}
			obj.put("tfoot", tfoot.toString());
			obj.put("tbody", tbody.toString());
			outputResult(obj.toString());
			tfoot.setLength(0);
			tbody.setLength(0);
			obj.clear();
			obj = null;
			CompanyLogUtil.insertLog("查询评论", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return null;
	}
	@RequestMapping(value = "/findCommentByOrderNum",method = RequestMethod.POST)
	public String findOrdersComment(@RequestParam(value="order_num",required=false)String order_num)throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			StringBuffer tbody = new StringBuffer();
			CompanyComment companyComment = companyCommentMongoImpl.findCompanyCommentByOrderNum(order_num);
			if(companyComment != null){
				CompanyCommentUtil.getCommentHTML(tbody, companyComment,null);	
				obj.put("tbody", tbody.toString());
				obj.put("status", ErrorConst.STATUS_SUCCESS);
			}
			tbody.setLength(0);
		} catch (Exception e) {
			new YuleException("查询订单评论出现异常",e);
			throw e;
		}finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
