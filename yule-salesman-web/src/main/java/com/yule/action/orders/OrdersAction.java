package com.yule.action.orders;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.util.StringUtils;
import com.yule.common.BaseAction;
import com.yule.constant.OrdersConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.query.OrdersQuery;
import com.yule.mongo.salesman.service.IOrdersMongo;
import com.yule.pojo.Company;
import com.yule.salesman.service.ICompanyService;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;
@Controller
@Scope("prototype")
@RequestMapping("/orders")
public class OrdersAction extends BaseAction{
	@Autowired
	private IOrdersMongo ordersMongoImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	/**
	 * 查询订单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findOrders")
	public String findOrders(OrdersQuery ordersQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception {
		if(null==pageNo){
			return "/orders/index";
		}
		if (ordersQuery == null) {
			ordersQuery = new OrdersQuery();
		}
		//如果企业名称为空的话 默认查所有的企业
		if (StringUtils.isEmpty(ordersQuery.getCompany_name())) {
			List<Company> companyIds = this.companyServiceImpl.findCompanyList(getCookieValue());
			List<String> companyIdList = new ArrayList<String>();  
			if (companyIds != null && companyIds.size() > 0) {
				for (Company company : companyIds) {
					companyIdList.add(company.getId());
				}
				ordersQuery.setCompanyIds(companyIdList);
				companyIds.clear();
				companyIds = null;
			}
		}
		JSONObject obj = new JSONObject();
		try {
			List<String> orderCompanyIds = ordersQuery.getCompanyIds();
			Page<Orders> page = null;
			if (orderCompanyIds != null && orderCompanyIds.size() > 0) {
				page = ordersMongoImpl.findOrdersByCompany(ordersQuery,PageConst.PAGE_SIZE_TEN, pageNo);	
			}
			StringBuffer tfoot = new StringBuffer();
			StringBuffer tbody = new StringBuffer();
			tfoot.append("<tr><td colspan=\"7\">");
			tfoot.append("<div class=\"bulk-actions align-left\"></div>");
			tfoot.append(PaginationUtil.getPaginationHtml(page));
			tfoot.append("<div class=\"clear\"></div>");
			tfoot.append("</td></tr>");
			if(page != null && page.getRowCount()>0){
				for(Orders orders : page.getDatas()){
					tbody.append("<tr>");
					tbody.append("<td><a href=\"#order-wizard\" modal-order=\"\"  data-toggle=\"modal\"  modal-dialog=\""+orders.getOrder_num()+"\">"+orders.getOrder_num()+"</a></td>");
					tbody.append("<td>"+orders.getProduct_name()+"</td>");
					tbody.append("<td>"+orders.getCompany_name()+"</td>");
					tbody.append("<td>"+orders.getExpense_money()+"</td>");
					tbody.append("<td><span class=\"label label-sm label-success\" status-change=\"\">"+OrdersConst.ORDERS[orders.getStatus()]+"</span></td>");
					tbody.append("<td>"+DateUtil.DateToString(orders.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+"</td>");
					tbody.append("</tr>");					
				}
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
		} catch (Exception e) {
			new YuleException("查询【findOrders】订单出错",e);
			throw e;
		} 
		return null;
	}

}
