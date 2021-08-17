package com.yule.action.sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.CalendarConstact;
import com.yule.constant.ErrorConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.query.OrdersQuery;
import com.yule.pojo.SalesmanLogin;
import com.yule.salesman.service.ISalesmanLoginService;
import com.yule.salesman.vo.SalesmanVO;
import com.yule.util.CalendarUtil;
import com.yule.util.DateUtil;
import com.yule.util.EchartsUtil;
import com.yule.util.EncryptUtil;
import com.yule.vo.EchartsOptionsVo;
import com.yule.vo.SaleDataVo;

@Controller
@Scope("prototype")
@RequestMapping("/sale")
public class SaleAction extends BaseAction {
	
	@Autowired
	private ISalesmanLoginService salesmanLoginServiceImpl;
	
	@RequestMapping(value = "/saleStatistics")
	public String saleStatistics(OrdersQuery ordersQuery) throws Exception {
		if (null==ordersQuery.getStart_time()) {
			int salesman_commision = getSalesman().getCommision();
			request.setAttribute("salesman_commision", salesman_commision);
			return "/sale/saleStatistics/index";
		}
		if (ordersQuery.getTime_type()==null) {
			ordersQuery.setTime_type(CalendarConstact.DAY);
		}
		if (StringUtils.isEmpty(ordersQuery.getStart_time())) {
			ordersQuery.setStart_time(CalendarUtil.fisrtDay());
		}
		if (StringUtils.isEmpty(ordersQuery.getEnd_time())) {
			ordersQuery.setEnd_time(DateUtil.DateToString(DateUtil.getCurrentDate(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		JSONObject jsonObject = new JSONObject();
		try {
			SaleDataVo saleData =  EchartsUtil.echarts(ordersQuery);
			if (null!=saleData) {
				EchartsOptionsVo chartOptions = new EchartsOptionsVo();
				Map<String, Object> legend = new HashMap<String, Object>();
				Map<String, Object> xAxis = new HashMap<String, Object>();
				List<Map<String, Object>> series = new ArrayList<Map<String, Object>>();
				Map<String,double[]> productExpenseCount = saleData.getProductExpenseCount();
				Map<String, int[]> productCount = saleData.getProductCount();
				xAxis.put("type", "category");
				xAxis.put("data", saleData.getDays());
				Set<String> product_nams = productExpenseCount.keySet();
				String[] legends=new String[product_nams.size()];
				int i =0 ;
				for (String string : product_nams) {
					Map<String, Object> seriesTmp = new HashMap<String, Object>();
					seriesTmp.put("name", string);
					seriesTmp.put("type", "line");
					seriesTmp.put("productCount", productCount.get(string));
					seriesTmp.put("data", productExpenseCount.get(string));
					series.add(seriesTmp);
					legends[i] = string;
					i++;
				}
				legend.put("data", legends);
				chartOptions.setLegend(legend);
				chartOptions.setSeries(series);
				chartOptions.setxAxis(xAxis);
				jsonObject.put("options", chartOptions);
				jsonObject.put("ordersCount", saleData.getOrdersCount());
				jsonObject.put("ordersExpenseCount", saleData.getOrdersExpenseCount());
				jsonObject.put("commision", 100-ordersQuery.getCommision());
				
				chartOptions=null;
				product_nams.clear();
				product_nams=null;
				productCount.clear();
				productCount= null;
				productExpenseCount.clear();
				productExpenseCount = null;
				saleData=null;
			}
		} catch (Exception e) {
			new YuleException("销售统计错误[/saleStatistics]", e);
		}finally{
			outputResult(jsonObject.toString());
			jsonObject.clear();
			jsonObject=null;
		}
		return null;
	}
	@RequestMapping(value = "/updateSalesmanPassword",method = RequestMethod.POST)
	public String updateSalesmanPassword(@RequestParam(value="oldPassword",required=false)String oldPassword,@RequestParam(value="password",required=false)String password)throws Exception{
		JSONObject json = new JSONObject();
		json.put("status", ErrorConst.STATUS_ERROR);
		try {
			SalesmanLogin salesman = new SalesmanLogin();
			String id = getCookieValue();
			salesman.setId(id);
			salesman.setPassword(password);
			SalesmanVO salesmanVO = this.salesmanLoginServiceImpl.findSalesmanLoginById(id);
			if (salesmanVO != null) {
				if (!StringUtils.isEmpty(oldPassword) && EncryptUtil.encryptToMD5(oldPassword).equals(salesmanVO.getPassword())) {
					boolean flag = this.salesmanLoginServiceImpl.updateSalesmanLogin(salesman);
					json.put("status", flag);	
				}else{
					json.put("message", "原密码不正确");
				}
			}
		} catch (Exception e) {
			new YuleException("修改密码【updateSalesmanPassword】出现异常",e);
			throw e;
		}finally{
			outputResult(json.toString());
		}
		return null;
	}
}
