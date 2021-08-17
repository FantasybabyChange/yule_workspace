package com.yule.admin.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.IProductPriceDao;
import com.yule.admin.param.InsertProductPriceParam;
import com.yule.admin.service.IProductPriceService;
import com.yule.exception.YuleException;
import com.yule.pojo.ProductPrice;
import com.yule.util.IDUtil;
@Service("productPriceServiceImpl")
public class ProductPriceServiceImpl implements IProductPriceService {
	
	@Autowired
	private IProductPriceDao productPriceDao;
	
	public List<ProductPrice> findProductPriceListByProductId(String productId) throws YuleException {
		return this.productPriceDao.findProductPriceList(productId);
	}

	public boolean insertProductPrice(ProductPrice productPrice) throws YuleException {
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(productPrice.getId())){
				productPrice.setId(IDUtil.getID());
			}
			this.productPriceDao.insertProductPrice(productPrice);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteProductPriceById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.productPriceDao.deleteProductPriceById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	
	}

	public boolean updateProductPrice(ProductPrice productPrice) throws YuleException {
		boolean flag = false;
		try {
			this.productPriceDao.updateProductPrice(productPrice);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	
	}

	public boolean batchInsertAndUpdateProductPrice(InsertProductPriceParam insertProductPriceParam) throws YuleException {
		boolean flag =false;
		try {
			List<String> lists = insertProductPriceParam.getId();
			String productId = insertProductPriceParam.getProduct_id();
			if(null != lists && lists.size()>0){
				List<ProductPrice> insertProductPrice = new ArrayList<ProductPrice>();
				List<ProductPrice> updateProductPrice = new ArrayList<ProductPrice>();
				//区间段
				List<String> times = insertProductPriceParam.getTime();
				//星期一到星期日
				List<BigDecimal> mondays = insertProductPriceParam.getMonday();
				List<BigDecimal> tuesdays = insertProductPriceParam.getTuesday();
				List<BigDecimal> wednesdays = insertProductPriceParam.getWednesday();
				List<BigDecimal> thursdays = insertProductPriceParam.getThursday();
				List<BigDecimal> fridays = insertProductPriceParam.getFriday();
				List<BigDecimal> saturdays = insertProductPriceParam.getSaturday();
				List<BigDecimal> sundays = insertProductPriceParam.getSunday();
				int i =0;
				ProductPrice ProductPrice = null;
				for (String id:lists) {
					ProductPrice = new ProductPrice();
					ProductPrice.setTime(times.get(i));
					ProductPrice.setMonday(mondays.get(i));
					ProductPrice.setThursday(thursdays.get(i));
					ProductPrice.setTuesday(tuesdays.get(i)); 
					ProductPrice.setWednesday(wednesdays.get(i)); 
					ProductPrice.setFriday(fridays.get(i)); 
					ProductPrice.setSaturday(saturdays.get(i)); 
					ProductPrice.setSunday(sundays.get(i));
					if(StringUtils.isEmpty(id)){
						ProductPrice.setId(IDUtil.getID());
						ProductPrice.setProduct_id(productId);
						insertProductPrice.add(ProductPrice);
					}else{
						ProductPrice.setId(id);
						updateProductPrice.add(ProductPrice);
					}
					i++;
				}
				lists.clear();
				times.clear();
				mondays.clear();
				tuesdays.clear();
				wednesdays.clear();
				thursdays.clear();
				fridays.clear();
				saturdays.clear();
				sundays.clear();
				lists = null;
				times = null;
				mondays = null;
				tuesdays = null;
				wednesdays = null;
				thursdays = null;
				fridays = null;
				saturdays = null;
				sundays = null;
				if(insertProductPrice.size()>0){
					   this.productPriceDao.batchInsertProductPrice(insertProductPrice);	
					   insertProductPrice.clear();
				}
				insertProductPrice=null;
				if(updateProductPrice.size()>0){
					   this.productPriceDao.batchUpdateProductPrice(updateProductPrice);	
					   updateProductPrice.clear();
				}
				updateProductPrice=null;
				flag = true;
			}
			insertProductPriceParam=null;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	
	}

}
