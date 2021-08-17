package com.yule.action.orders;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.service.ICompanyService;
import com.yule.admin.service.IProductService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.OrdersConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.service.IOrdersMongo;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.query.OrdersQuery;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@RequestMapping("/orders")
public class OrdersAction extends BaseAction{

	@Autowired
	private IOrdersMongo ordersMongoImpl;
	
	@Autowired
	private IProductService productServiceImpl;
	
	@Autowired
	private ICompanyService companyServiceImpl;

	/**
	 * 查询订单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findOrders",method = RequestMethod.GET)
	public String findOrdersList(OrdersQuery ordersQuery,@RequestParam(value="pageNo",required=false)Integer pageNo) throws Exception {
		if(pageNo==null||pageNo<=0){
			pageNo = 1;
		}
		try {
			Page<Orders> page = ordersMongoImpl.findOrdersPage(ordersQuery,PageConst.PAGE_SIZE_TEN, pageNo);
			StringBuffer htmls = new StringBuffer("");
			htmls.append("<tfoot>");
			htmls.append("<tr>");
			htmls.append("<td colspan=\"8\">");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td>");
			htmls.append("</tr>");
			htmls.append("</tfoot>");		
			htmls.append("<tbody>");
			List<Orders> lists = page.getDatas();
			if(null!=lists&&lists.size()>0){
				for(Orders orders:lists){
					htmls.append("<tr>");
					htmls.append("<td><a href=\"/admin/findOrdersDetails.do?id="+orders.getId()+"\" >"+orders.getOrder_num()+"</a></td>");
					htmls.append("<td>"+orders.getUser_id()+"</td>");
					htmls.append("<td>"+orders.getCustomer_name()+"</td>");
					htmls.append("<td>"+orders.getCustomer_phone()+"</td>");
					htmls.append("<td>"+orders.getCompany_id()+"</td>");
					htmls.append("<td>"+orders.getCompany_name()+"</td>");
					htmls.append("<td>"+new BigDecimal(orders.getExpense_money()).toString()+"</td>");
					htmls.append("<td>"+orders.getUser_score()+"</td>");
					htmls.append("<td>"+DateUtil.DateToString(orders.getEstimate_arrive_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+"</td>");
					htmls.append("<td>"+DateUtil.DateToString(orders.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+"</td>");
					htmls.append("<td>"+OrdersConst.ORDERS[orders.getStatus()]+"</td>");
					/*htmls.append("<td>");
					htmls.append("<a class=\"button\" href=\"javascript:;\">"+OrdersConst.ORDERS[orders.getStatus()]+"</a>");
					htmls.append("</td>");*/
					htmls.append("</tr>");
				}
				page.getDatas().clear();
			}else{
				htmls.append("<tr>");
				htmls.append("<td class=\"td-center-style\" colspan=\"11\">暂无数据</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			request.setAttribute("ordersQuery", ordersQuery);
			request.setAttribute("orderHtmls", htmls);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return "/orders/index";
	}

	/**
	 * 更新订单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateOrders",method = RequestMethod.POST)
	public String updateOrders(@ModelAttribute("orders")Orders orders) throws Exception {
		try {
			ordersMongoImpl.updateOrders(orders);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/findOrders.do";
	}
	
	/**
	 * 查询订单详情
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findOrdersDetails",method = RequestMethod.GET)
	public String findOrdersDetails(@RequestParam(value="id",required=false)String id) throws Exception {
		try {
			Orders orders = ordersMongoImpl.findOrdersById(id);
//			Product product = productServiceImpl.findProductById(orders.getProduct_id());
//			Company company = companyServiceImpl.findCompanyById(orders.getCompany_id());
			request.setAttribute("orders",orders);
			request.setAttribute("product",null);
			request.setAttribute("company",null);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		}
		return "/orders/detail";
	}
	
}
