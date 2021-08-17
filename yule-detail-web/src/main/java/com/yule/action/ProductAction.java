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
import com.yule.constant.FileUploadConst;
import com.yule.constant.StatusConst;
import com.yule.detail.service.IProductService;
import com.yule.exception.YuleException;
import com.yule.vo.ProductVO;
@Controller
@Scope("prototype")
@RequestMapping("/product")
public class ProductAction extends BaseAction{
	
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
					productInfoHTMLs.append("<tr><td class=\"detail-room-table-pic\">");
					productInfoHTMLs.append("<a href=\"#\"><img src=\"http://images.yuleing.com/loading/loading.jpg\" data-original=\""+FileUploadConst.COMPANY_IMAGE_PATH+companyId+"/"+FileUploadConst.PRODUCT+"/"+productVO.getId()+"/"+FileUploadConst.PX_50_50+FileUploadConst.PRODUCT_FACE+FileUploadConst.IMAGE_TYPE+"\" width=\"60\" ></a>");
					productInfoHTMLs.append("<a href=\"#\" class=\"detail-room-table-pic-m\" data-type=\"product_name\" data-value=\""+productVO.getName()+"\" >"+productVO.getName()+"</a></td>");
					productInfoHTMLs.append("<td class=\"detail-room-table-person-num\" data-type=\"person_num\" data-value=\""+productVO.getPerson_num()+"\" >"+productVO.getPerson_num()+"</td>");
					productInfoHTMLs.append("<td class=\"detail-room-table-price\" data-type=\"min_expense\" data-value=\""+productVO.getMin_expense()+"\" >"+productVO.getMin_expense()+"</td>");
					productInfoHTMLs.append("<td class=\"detail-room-table-person-num\">"+StatusConst.PRODUCT_STATUS[productVO.getStatus()]+"</td>");
					if (productVO.getStatus() == StatusConst.PRODUCT_STATUS_TRUE) {
						productInfoHTMLs.append("<td class=\"detail-room-table-price\"><select name=\"\"><option value=\"1\">1间</option></select></td>");
						productInfoHTMLs.append(" <td class=\"detail-room-table-ctrl\"><a  target=\"_blank\" data-href=\"http://orders.yuleing.com/orders/findOrders.do\"  data-type=\"product_id\" data-value=\""+productVO.getId()+"\" class=\"b-button\">预订</a></td>");	
					}else{
						productInfoHTMLs.append("<td class=\"detail-room-table-price\"><select name=\"\"><option value=\"0\">0间</option></select></td>");
						productInfoHTMLs.append(" <td class=\"detail-room-table-ctrl\"></td>");
					}
						productInfoHTMLs.append("</tr>");
					 
				}
			}else{
				productInfoHTMLs.append("<tr><td colspan=\"6\">暂无包间</td></tr>");
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
