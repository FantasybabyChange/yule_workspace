package com.yule.mongo.company.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.bson.types.ObjectId;
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
import com.yule.mongo.company.service.ICompanyGalleryMongo;
import com.yule.mongo.param.InsertCompanyGalleryParam;
import com.yule.mongo.pojo.CompanyGallery;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Service("companyGalleryMongoImpl")
public class CompanyGalleryMongoImpl implements ICompanyGalleryMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;
	
	public String batchInsertCompanyGallery(InsertCompanyGalleryParam insertCompanyGalleryParam)throws YuleException {
		JSONArray array = new JSONArray();
		try {
			List<String> names = insertCompanyGalleryParam.getName();
			List<String> system_names = insertCompanyGalleryParam.getSystem_name();
			List<String> paths = insertCompanyGalleryParam.getPath();
			List<String> types = insertCompanyGalleryParam.getType();
			List<String> sizes  = insertCompanyGalleryParam.getSize();
			String company_id =  insertCompanyGalleryParam.getCompany_id();
			List<CompanyGallery> companyGallerys=new ArrayList<CompanyGallery>();
			CompanyGallery companyGallery =null;
			if (null!=names&&names.size()>0) {
				ObjectId id = null;
				JSONObject obj = new JSONObject();
				for (int i = 0; i < names.size(); i++) {
					id = new ObjectId();
					companyGallery = new CompanyGallery();
					obj.put("id", id.toString());
					obj.put("name", names.get(i));
					obj.put("system_name", system_names.get(i));
					array.add(obj);
					companyGallery.setName(names.get(i));
					companyGallery.setCompany_id(company_id);
					companyGallery.setCreate_time(DateUtil.getCurrentDate());
					companyGallery.setIs_delete(DeleteConst.IS_DELETE_TRUE);
					companyGallery.setSize(sizes.get(i));
					companyGallery.setPath(paths.get(i));
					companyGallery.setSystem_name(system_names.get(i));
					companyGallery.setType(types.get(i));
					companyGallery.setId(id);
					companyGallerys.add(companyGallery);
				}
				this.mongoTemplate.insertAll(companyGallerys);
				companyGallerys.clear();
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
			}
			company_id = null;
			insertCompanyGalleryParam = null;			
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return array.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(new ObjectId());
	}

	
	public boolean deleteCompanyGalleryById(String id) throws YuleException {
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("id").is(id));  
        Update update = new Update();  
		update.set("is_delete",DeleteConst.IS_DELETE_FALSE);  
		update.set("update_time", DateUtil.getCurrentDate());
		try{
			this.mongoTemplate.updateFirst(query, update, CompanyGallery.class);  
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

	public CompanyGallery findCompanyGalleryById(String id) throws YuleException {
		return this.mongoTemplate.findById(new ObjectId(id), CompanyGallery.class);
	}

	public Page<CompanyGallery> findCompanyGalleryPageByCompanyId(String companyId,int pageSize, int pageNo) throws YuleException {
		Query query=new Query();
		if (!StringUtils.isEmpty(companyId)) {
			query.addCriteria(new Criteria("company_id").is(companyId));
		}
		query.addCriteria(new Criteria("is_delete").is(DeleteConst.IS_DELETE_TRUE));
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<CompanyGallery> page = new Page<CompanyGallery>();
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		page.setDatas(this.mongoTemplate.find(query, CompanyGallery.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, CompanyGallery.class)));
		return page;
	}

}
