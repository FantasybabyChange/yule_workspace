package com.yule.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yule.constant.CustomBeanConst;
import com.yule.mongo.query.OrdersQuery;
import com.yule.mongo.salesman.service.IOrdersMongo;
import com.yule.mongo.salesman.vo.SaleVo;
import com.yule.salesman.service.IProductService;
import com.yule.vo.ProductVO;
import com.yule.vo.SaleDataVo;

public class EchartsUtil {

	public static SaleDataVo echarts( OrdersQuery ordersQuery) throws Exception {
		
		List<String> days = CalendarUtil.findAreaDates(ordersQuery.getStart_time(),ordersQuery.getEnd_time(),ordersQuery.getTime_type());
		if(null==days||days.size()<1){
			return null;
		}
		Map<String, int[]> productCount = new HashMap<String, int[]>();
		Map<String, double[]> productExpenseCount = new HashMap<String, double[]>();
		//订单总数
		int ordersCount = 0;
		//订单金额总计
		double ordersExpenseCount = 0;
		//得到该公司所有产品
		 IProductService productServiceImpl = (IProductService) CustomBeanFactory.getContext(CustomBeanConst.SALESMAN_SERVICE_PATHS).getBean("productServiceImpl");
		 IOrdersMongo ordersMongoImpl  = (IOrdersMongo) CustomBeanFactory.getContext(CustomBeanConst.SALESMAN_MONGO_PATHS).getBean("ordersMongoImpl");
		 try {
			List<ProductVO> productVOs = productServiceImpl.findSimpleProductVOList(ordersQuery.getCompany_id());
			List<SaleVo> saleVos = ordersMongoImpl.findOrdersToStatistics(ordersQuery);
			
			if (null!=productVOs&&productVOs.size()>0) {
				for (ProductVO productVO : productVOs) {
					productCount.put(productVO.getName(),new int[days.size()]);
					productExpenseCount.put(productVO.getName(),new double[days.size()]);
				}
				productVOs.clear();
				productVOs = null;
				if (null!=saleVos&&saleVos.size()>0) {
					for (SaleVo saleVo : saleVos) {	
						ordersCount += saleVo.getProduct_order_count();
						ordersExpenseCount +=saleVo.getExpense_count();
						for (int i = 0; i <days.size(); i++) {
							if (saleVo.getDay().equals(days.get(i))) {
								productCount.get(saleVo.getProduct_name())[i] = saleVo.getProduct_order_count();
								productExpenseCount.get(saleVo.getProduct_name())[i] =saleVo.getExpense_count();
								break;
							}
						}
					}
					saleVos.clear();
					saleVos = null;
				}
			}
		 } catch (Exception e) {
			e.printStackTrace();
		}

		SaleDataVo saleData = new SaleDataVo();
		saleData.setProductExpenseCount(productExpenseCount);
		saleData.setProductCount(productCount);
		saleData.setDays(days);
		saleData.setOrdersCount(ordersCount);
		saleData.setOrdersExpenseCount(ordersExpenseCount);
/*		productCount.clear();
		productCount=null;
		productExpenseCount.clear();
		productExpenseCount=null;*/
		return saleData;
	}

}
