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
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.company.service.IProductService;
import com.yule.constant.CalendarConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.query.OrdersQuery;
import com.yule.util.CalendarUtil;
import com.yule.util.CompanyLogUtil;
import com.yule.util.DateUtil;
import com.yule.util.EchartsUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.EchartsOptionsVo;
import com.yule.vo.Page;
import com.yule.vo.ProductVO;
import com.yule.vo.SaleDataVo;

@Controller
@Scope("prototype")
@RequestMapping("/sale")
public class SaleAction extends BaseAction {
	
	@Autowired
	private IProductService productServiceImpl;
	
	@RequestMapping(value = "/saleStatistics")
	public String saleStatistics(OrdersQuery ordersQuery) throws Exception {
		if (null==ordersQuery.getStart_time()) {
			return "/sale/saleStatistics/index";
		}
		if (ordersQuery.getTime_type()==null) {
			ordersQuery.setTime_type(CalendarConst.DAY);
		}
		if (StringUtils.isEmpty(ordersQuery.getStart_time())) {
			ordersQuery.setStart_time(CalendarUtil.fisrtDay());
		}
		if (StringUtils.isEmpty(ordersQuery.getEnd_time())) {
			ordersQuery.setEnd_time(DateUtil.DateToString(DateUtil.getCurrentDate(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		JSONObject jsonObject = new JSONObject();
		ordersQuery.setCompany_id(getCompanyUser().getCompany_id());
		try {
			SaleDataVo saleData =  EchartsUtil.echarts(ordersQuery);
			if (null!=saleData) {
				EchartsOptionsVo chartOptions = new EchartsOptionsVo();
				Map<String, Object> legend = new HashMap<String, Object>();
				Map<String, Object> xAxis = new HashMap<String, Object>();
				List<Map<String, Object>> series = new ArrayList<Map<String, Object>>();
				ordersQuery.setCompany_id(getCompanyUser().getCompany_id());
				Map<String,double[]> productExpenseCount = saleData.getProductExpenseCount();
				Map<String, int[]> productCount = saleData.getProductCount();
				xAxis.put("type", "category");
				xAxis.put("data",  saleData.getDays());
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
				chartOptions.setCalculable(true);
				jsonObject.put("options", chartOptions);
				jsonObject.put("ordersCount", saleData.getOrdersCount());
				jsonObject.put("ordersExpenseCount", saleData.getOrdersExpenseCount());
				jsonObject.put("commision", 100-getCompanyUser().getCommision());
				chartOptions=null;
				product_nams.clear();
				product_nams=null;
				productCount.clear();
				productCount= null;
				productExpenseCount.clear();
				productExpenseCount = null;
				saleData=null;
			}
			CompanyLogUtil.insertLog("查询销售统计", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("销售统计错误[/saleStatistics]", e);
		}finally{
			outputResult(jsonObject.toString());
			jsonObject.clear();
			jsonObject=null;
		}
		return null;
	}
	/**
	 * 
	 * @param company_name 其他公司 名
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/comparePrice")
	public String comparePrice(@RequestParam(value = "company_name", required = false) String company_name,@RequestParam(value = "pageNo", required = false) Integer pageNo)  throws Exception{
		if(null==pageNo){
			return "/sale/comparePrice/index";
		}
		JSONObject obj = new JSONObject();
		StringBuffer tfoot = new StringBuffer();
		StringBuffer tbody = new StringBuffer();
		try {
			Page<ProductVO> page = this.productServiceImpl.findOtherCompanyProductPage(company_name,getCompanyUser().getCompany_id() ,PageConst.PAGE_SIZE_TEN, pageNo);
			tfoot.append("<tr><td colspan=\"7\">");
			tfoot.append("<div class=\"bulk-actions align-left\"></div>");
			tfoot.append(PaginationUtil.getPaginationHtml(page));
			tfoot.append("<div class=\"clear\"></div>");
			tfoot.append("</td></tr>");
			if(page.getRowCount()>0){
				for(ProductVO productVO : page.getDatas()){
					tbody.append("<tr>");
					tbody.append("<td>"+productVO.getCompany_name()+"</td>");
					tbody.append("<td>"+productVO.getName()+"</td>");
					tbody.append("<td>"+productVO.getPerson_num()+"</td>");
					tbody.append("<td>"+productVO.getMin_expense()+"</td>");
					tbody.append("</td>");
					tbody.append("</tr>");					
				}
			page.cleanDatas();	
			page = null;
			}else{
				tbody.append("<tr>");
				tbody.append("<td colspan=\"7\">没有数据</td>");
				tbody.append("</tr>");	
			}
			obj.put("tfoot", tfoot.toString());
			obj.put("tbody", tbody.toString());
			CompanyLogUtil.insertLog("查询价格对比", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("价格对比错误[/comparePrice]", e);
			throw e;
		} finally{
			outputResult(obj.toString());
			tfoot.setLength(0);
			tbody.setLength(0);
			obj.clear();
			obj = null;
		}
		return null;
		
	}
	
}
