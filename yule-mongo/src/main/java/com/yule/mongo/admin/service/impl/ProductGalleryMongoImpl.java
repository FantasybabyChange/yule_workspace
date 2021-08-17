package com.yule.mongo.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.service.IProductGalleryMongo;
import com.yule.mongo.param.InsertProductGalleryParam;
import com.yule.mongo.pojo.ProductGallery;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Service("productGalleryMongoImpl")
public class ProductGalleryMongoImpl implements IProductGalleryMongo{
	
	@Autowired  
    private MongoTemplate mongoTemplate;
	
	public boolean insertProductGallery(ProductGallery productGallery)throws YuleException {
		boolean flag = false;
		try{
			if(null==productGallery.getCreate_time()){
				productGallery.setCreate_time(DateUtil.getCurrentDate());
			}
			this.mongoTemplate.save(productGallery);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

	public boolean batchInsertProductGallery(InsertProductGalleryParam insertProductGalleryParam)throws YuleException {
		boolean flag = false;
		try {
			List<String> names = insertProductGalleryParam.getName();
			List<String> system_names = insertProductGalleryParam.getSystem_name();
			List<String> paths = insertProductGalleryParam.getPath();
			List<String> types = insertProductGalleryParam.getType();
			List<String> sizes  = insertProductGalleryParam.getSize();
			String product_id =  insertProductGalleryParam.getProduct_id();
			List<ProductGallery> productGallerys = new ArrayList<ProductGallery>();
			ProductGallery productGallery =null;
			if (null!=names&&names.size()>0) {
				for (int i = 0; i < names.size(); i++) {
					productGallery = new ProductGallery();
					productGallery.setProduct_id(product_id);
					productGallery.setCreate_time(DateUtil.getCurrentDate());
					productGallery.setIs_delete(DeleteConst.IS_DELETE_TRUE);
					productGallery.setSize(sizes.get(i));
					productGallery.setPath(paths.get(i));
					productGallery.setSystem_name(system_names.get(i));
					productGallery.setType(types.get(i));
					productGallerys.add(productGallery);
				}
				mongoTemplate.insertAll(productGallerys);
				productGallerys.clear();
				names.clear();
				names = null;
				system_names.clear();
				system_names = null;
				paths.clear();
				paths = null;
				types.clear();
				types = null;
				sizes.clear();
				sizes = null;
				flag = true;
			}
		} catch (Exception e) {
			throw new YuleException(e);
		}
		
		return flag;
	}


	public boolean deleteProductGalleryById(String id) throws YuleException {
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("id").is(id));  
        Update update = new Update();  
		update.set("is_delete", DeleteConst.IS_DELETE_FALSE);  
		update.set("update_time", DateUtil.getCurrentDate());
		try{
			this.mongoTemplate.updateFirst(query, update, ProductGallery.class);  
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}
	
	public Page<ProductGallery> findProductGalleryPageByProductId(String product_id, int pageSize, int pageNo)throws YuleException {
		Query query=new Query();
		if (!StringUtils.isEmpty(product_id)) {
			query.addCriteria(new Criteria("product_id").is(product_id));
		}
		query.addCriteria(new Criteria("is_delete").is(DeleteConst.IS_DELETE_TRUE));
		query.skip((pageNo-1)*pageSize).limit(pageSize);   
		Page<ProductGallery> page = new Page<ProductGallery>();
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		page.setDatas(this.mongoTemplate.find(query, ProductGallery.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, ProductGallery.class)));
		return page;
	}

}
