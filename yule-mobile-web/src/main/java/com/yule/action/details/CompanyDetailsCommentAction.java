package com.yule.action.details;

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
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.mobile.service.ICompanyCommentMongo;
import com.yule.mongo.mobile.service.ICompanyCommentPointMongo;
import com.yule.mongo.mobile.vo.CompanyCommentPointVO;
import com.yule.mongo.mobile.vo.CompanyCommentVO;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.util.DateUtil;
import com.yule.util.NumberUtil;
import com.yule.vo.Page;
@Controller
@Scope("prototype")
@RequestMapping("/companyDetailsComment")
public class CompanyDetailsCommentAction extends BaseAction{
	
	@Autowired
	private ICompanyCommentMongo companyCommentMongoImpl;
	
	@Autowired
	private ICompanyCommentPointMongo companyCommentPointMongoImpl;
	
	@RequestMapping(value="/findPointNum",method=RequestMethod.POST)
	public String findPointNum(@RequestParam(value="company_id",required=false)String company_id)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			CompanyCommentVO companyCommentVO = this.companyCommentMongoImpl.findCompanyCommentVO(company_id);
			object.put("commentNum",companyCommentVO.getCount());
			if (companyCommentVO.getCount() > 0) {
				object.put("hightPoint",companyCommentVO.getPoint());	
			}
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业详细信息【findCompanyDetails】出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
	
	@RequestMapping(value="/findCompanyCommentPage",method=RequestMethod.POST)
	public String findCompanyCommentPage(@RequestParam(value="company_id",required=false)String company_id,@RequestParam(value="pageNo",required=false)Integer pageNo,@RequestParam(value="type",required=false)Integer type)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			if(null==pageNo || pageNo <= 0){
				pageNo = 1;
			}
			Page<CompanyComment> page = this.companyCommentMongoImpl.findCompanyCommentPage(company_id,type, PageConst.PAGE_SIZE_FIVE, pageNo);
			StringBuffer companyCommentHtmls = new StringBuffer();
			companyCommentHtmls.append("<ul class=\"mui-table-view mui-comment-list\">");
			if (page.getRowCount() > 0) {
				List<CompanyComment> datas = page.getDatas();
				for (CompanyComment companyComment : datas) {
					companyCommentHtmls.append("<li class=\"mui-table-view-cell mui-clearfix\">");
					companyCommentHtmls.append("<h5 class=\"mui-comments-user\"><span class=\"mui-badge mui-badge-yellow pull-right\">"+companyComment.getPoint()+"</span> <span class=\"mui-icon mui-icon-person\"></span>"+companyComment.getUser_name()+" <span>"+DateUtil.DateToString(companyComment.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN)+"</span></h5>");
					companyCommentHtmls.append("<div class=\"comments\">");
					companyCommentHtmls.append("<p class=\"mui-comments-r\"> <i class=\"icon-ok\"></i>"+companyComment.getComment()+"</p>");
					companyCommentHtmls.append("<p class=\"mui-comments-w\"> <i class=\"icon-remove\"></i>"+companyComment.getAdvice()+"</p></div></li>	");
				}
				companyCommentHtmls.append("</ul>");
				companyCommentHtmls.append("<ul class=\"mui-pager\">");
				if(pageNo <= 1){
					companyCommentHtmls.append("<li class=\"mui-disabled\">");
					companyCommentHtmls.append("<span> 上一页 </span>");
					companyCommentHtmls.append("</li>");	
				}else{
					companyCommentHtmls.append("<li>");
					companyCommentHtmls.append("<a data-page=\""+(pageNo-1)+"\"  href=\"javascript:;\">");
					companyCommentHtmls.append("上一页");
					companyCommentHtmls.append("</a>");
					companyCommentHtmls.append("</li>");	
				}
				if(pageNo >= page.getPageCount()){
					companyCommentHtmls.append("<li class=\"mui-disabled\">");
					companyCommentHtmls.append("<span> 下一页</span>");
					companyCommentHtmls.append("</li>");	
				}else{
					companyCommentHtmls.append("<li>");
					companyCommentHtmls.append("<a data-page=\""+(pageNo+1)+"\" href=\"javascript:;\">");
					companyCommentHtmls.append("下一页");
					companyCommentHtmls.append("</a>");
					companyCommentHtmls.append("</li>");
				}
				companyCommentHtmls.append("</ul>");
			}else{
				companyCommentHtmls.append("<li class=\"mui-table-view-cell mui-clearfix\">");
				companyCommentHtmls.append("<h5 class=\"mui-comments-user\">暂无评论</h5>");
				companyCommentHtmls.append("</li>");
				companyCommentHtmls.append("</ul>");
			}
			object.put("companyCommentHtmls", companyCommentHtmls.toString());
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业详细信息【findCompanyDetails】出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
	
	@RequestMapping(value="/findCompanyCommentPointAVG",method=RequestMethod.POST)
	public String findCompanyCommentPointAVG(@RequestParam(value="company_id",required=false)String company_id)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
		List<CompanyCommentPointVO> findCompanyPointAvg = this.companyCommentPointMongoImpl.findCompanyPointAvg(company_id);
			StringBuffer companyCommentPointHtmls = new StringBuffer();
			if (findCompanyPointAvg.size() > 0) {
				float count = 0;
				for (CompanyCommentPointVO companyCommentPointVO : findCompanyPointAvg) {
					float point = companyCommentPointVO.getPoint();
					count += point;
					companyCommentPointHtmls.append("<p class=\"mui-score-list\">");
					companyCommentPointHtmls.append("<label class=\"key\">"+companyCommentPointVO.getName()+"</label>");
					companyCommentPointHtmls.append("<span class=\"value\">"+NumberUtil.formatDouble(point,1)+"</span>");
					companyCommentPointHtmls.append("</p>");
				}
				object.put("avgScore", NumberUtil.formatDouble(count/findCompanyPointAvg.size(),1));
				findCompanyPointAvg.clear();
				findCompanyPointAvg = null;
			}else{
				companyCommentPointHtmls.append("<h4>无评分</h4>");
			}
			object.put("companyCommentHtmls", companyCommentPointHtmls.toString());
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业详细信息【findCompanyDetails】出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
}
