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
import com.yule.constant.StatusConst;
import com.yule.exception.YuleException;
import com.yule.mobile.service.IProductService;
import com.yule.vo.ProductVO;
@Controller
@Scope("prototype")
@RequestMapping("/productDetails")
public class ProductDetailsAction extends BaseAction{
	
	@Autowired
	private IProductService productServiceImpl;
	
	@RequestMapping(value="/findProduct",method=RequestMethod.POST)
	public String findProduct(@RequestParam(value="companyId",required=false)String companyId)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			 List<ProductVO> productVOs = this.productServiceImpl.findProductVOList(companyId);
			 StringBuffer productInfoHTMLs = new StringBuffer();
			 if (productVOs != null && productVOs.size() > 0) {
				for (ProductVO productVO : productVOs) {
					productInfoHTMLs.append("<li class=\"mui-table-view-cell mui-clearfix\">");
//					productInfoHTMLs.append("<a href=\"#\"><img src=\""+FileUploadConst.COMPANY_IMAGE_PATH+companyId+"/"+FileUploadConst.PRODUCT+"/"+productVO.getId()+"/"+FileUploadConst.PX_50_50+FileUploadConst.PRODUCT_FACE+FileUploadConst.IMAGE_TYPE+"\" width=\"60\" ></a>");
					productInfoHTMLs.append("<h5 class=\"mui-room-title\" data-type=\"product_name\" data-value=\""+productVO.getName()+"\" >"+productVO.getName()+"</h5>");
					productInfoHTMLs.append("<div class=\"mui-col-xs-5 mui-pull-left\">");
//					productInfoHTMLs.append("<p class=\"mui-room-tips\">免费取消 - 入住时付款</p>");
					productInfoHTMLs.append("<p class=\"mui-price\" data-type=\"min_expense\" data-value=\""+productVO.getMin_expense()+"\" >"+productVO.getMin_expense()+"元</p></div>");
					productInfoHTMLs.append("<div class=\"mui-col-xs-3 mui-pull-left mui-text-center\">");
					productInfoHTMLs.append("<p class=\"mui-person-max\">可容纳人数</p>");
					productInfoHTMLs.append("<p class=\"mui-person-num\" data-type=\"person_num\" data-value=\""+productVO.getPerson_num()+"\" >"+productVO.getPerson_num()+"人</p></div>");
					/*productInfoHTMLs.append("<div class=\"mui-col-xs-3 mui-pull-left mui-text-center\">");
					productInfoHTMLs.append("<p class=\"mui-person-max\">有房/无房</p>");
					productInfoHTMLs.append("<p class=\"mui-person-num\" >"+StatusConst.PRODUCT_STATUS[productVO.getStatus()]+"</p></div>");*/
					if (productVO.getStatus() == StatusConst.PRODUCT_STATUS_TRUE) {
						productInfoHTMLs.append("<div class=\"mui-col-xs-4 mui-pull-left mui-text-center\">");
						productInfoHTMLs.append("<button class=\"mui-btn mui-btn-search \">立即预订</button></div>");
					}else{
						productInfoHTMLs.append("<div class=\"mui-col-xs-4 mui-pull-left mui-text-center\">");
						productInfoHTMLs.append("暂无空闲包间</div>");
					}
				}
			}else{
				productInfoHTMLs.append("<li class=\"mui-table-view-cell mui-clearfix\"><h5 class=\"mui-room-title\">暂无包间</h5></li>");
			}
			 object.put("productInfoHTMLs", productInfoHTMLs.toString());
				productInfoHTMLs.setLength(0);
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
