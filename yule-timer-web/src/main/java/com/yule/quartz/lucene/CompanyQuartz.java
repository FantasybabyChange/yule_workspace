package com.yule.quartz.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.util.Version;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;
import org.springframework.util.StringUtils;

import com.yule.constant.CustomBeanConst;
import com.yule.constant.LuceneConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.timer.service.ICompanyCommentMongo;
import com.yule.mongo.timer.vo.CompanyCommentVO;
import com.yule.timer.service.ICompanyService;
import com.yule.timer.vo.CompanyLuceneVO;
import com.yule.util.CustomBeanFactory;
import com.yule.util.DateUtil;
import com.yule.util.LuceneIndexUtil;
import com.yule.util.TimerJobUtil;

public class CompanyQuartz extends StatefulMethodInvokingJob{
	
	public static void main(String[] args) {
		ICompanyService companyServiceImpl = (ICompanyService)CustomBeanFactory.getContext(CustomBeanConst.TIMER_SERVICE_PATHS).getBean("companyServiceImpl");
//		IOrdersMongo ordersMongoImpl = (IOrdersMongo)CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("ordersMongoImpl");
		ICompanyCommentMongo companyCommentMongoImpl = (ICompanyCommentMongo)CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("companyCommentMongoImpl");
		LuceneIndexUtil lucene = null;
		try {
			lucene = new LuceneIndexUtil(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH, Version.LUCENE_46);
		} catch (Exception e) {
			e.printStackTrace();
		}
        try{
			List<CompanyLuceneVO> companyVOs = companyServiceImpl.findCompanyLuceneVOList();
			if(null!=companyVOs&&companyVOs.size()>0){
				List<Document> docs = new ArrayList<Document>();
//				List<CompanyServeLuceneVO> companyServes = null;
				CompanyCommentVO companyCommentVO = null;
//				String companyId = null;
				Document doc = null;
				for(CompanyLuceneVO companyVO : companyVOs){
					doc = new Document();  
//					companyId = companyVO.getId();
					companyCommentVO = companyCommentMongoImpl.findCompanyCommentVO(companyVO.getId());
//					doc.add(new TextField("id", companyVO.getId(), Store.YES));  
			        doc.add(new StringField("id", companyVO.getId(), Store.YES));  
			        doc.add(new StringField("name", companyVO.getName(), Store.YES));
			        doc.add(new StringField("address_detail", companyVO.getAddress_detail(), Store.YES));  
			        doc.add(new StringField("area_business_id", companyVO.getArea_business_id(), Store.YES));
			        //doc.add(new StringField("area_business_name", companyVO.getArea_business_name(), Store.YES));  
			        doc.add(new StringField("area_city_id", companyVO.getArea_city_id(), Store.YES));  
			        doc.add(new StringField("area_city_name", companyVO.getArea_city_name(), Store.YES));  
			        doc.add(new StringField("area_county_id", companyVO.getArea_county_id(), Store.YES));  
			        doc.add(new StringField("area_county_name", companyVO.getArea_county_name(), Store.YES));  
			        doc.add(new StringField("area_province_id", companyVO.getArea_province_id(), Store.YES));  
			        doc.add(new StringField("area_province_name", companyVO.getArea_province_name(), Store.YES));  
			        doc.add(new StringField("company_category_id", companyVO.getCompany_category_id(), Store.YES));  
			        doc.add(new StringField("company_category_name", companyVO.getCompany_category_name(), Store.YES));  
			        doc.add(new StringField("company_grade_id", companyVO.getCompany_grade_id(), Store.YES));  
			        doc.add(new StringField("company_grade_name", companyVO.getCompany_grade_name(), Store.YES));  
			        doc.add(new StringField("hours", companyVO.getHours(), Store.YES));  
			        doc.add(new StringField("lat", companyVO.getLat(), Store.YES));  
			        doc.add(new StringField("lng", companyVO.getLng(), Store.YES));  
			        doc.add(new StringField("pinyin", companyVO.getPinyin(), Store.YES));
			        doc.add(new StringField("rebast", companyVO.getRebast().toString(), Store.YES));  
			        doc.add(new StringField("create_time", DateUtil.DateToString(companyVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN), Store.YES));
			        doc.add(new StringField("product_count", companyVO.getProduct_count(), Store.YES));
			        
			        //doc.add(new LongField("order_count", ordersMongoImpl.findOrdersCount(companyVO.getId()), Store.YES));
			        if(null!=companyCommentVO){
			        	doc.add(new LongField("company_comment_count", companyCommentVO.getCompany_comment_count(), Store.YES));
			        	doc.add(new DoubleField("company_comment_point", companyCommentVO.getPoint(), Store.YES));
			        }
//			        if(null!=companyServes&&companyServes.size()>0){
//			        	doc.add(new StringField("company_serves", JSONArray.fromObject(companyServes).toString(), Store.YES));
//			        	companyServes.clear();
//			        	companyServes = null;
//			        }
			        docs.add(doc);
				}
				lucene.createIndex(docs);  
			}
			
        } catch(Exception e){
        	new YuleException(e);
        } finally{
        	if(null!=lucene){
        		try {
					lucene.close();
				} catch (IOException e) {
					new YuleException(e);
				}
        	}
        	System.gc();
        }
	}
	
    
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		ICompanyService companyServiceImpl = (ICompanyService)CustomBeanFactory.getContext(CustomBeanConst.TIMER_SERVICE_PATHS).getBean("companyServiceImpl");
//		IOrdersMongo ordersMongoImpl = (IOrdersMongo)CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("ordersMongoImpl");
//		ICompanyCommentMongo companyCommentMongoImpl = (ICompanyCommentMongo)CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS).getBean("companyCommentMongoImpl");
		LuceneIndexUtil lucene = null;
		try {
			lucene = new LuceneIndexUtil(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH, Version.LUCENE_46);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        try{
			List<CompanyLuceneVO> companyVOs = companyServiceImpl.findCompanyLuceneVOList();
			if(null!=companyVOs&&companyVOs.size()>0){
				List<Document> docs = new ArrayList<Document>();
//				List<CompanyServeLuceneVO> companyServes = null;
//				CompanyCommentVO companyCommentVO = null;
//				String companyId = null;
				Document doc = null;
				for(CompanyLuceneVO companyVO : companyVOs){
					doc = new Document();  
//					companyId = companyVO.getId();
//					companyCommentVO = companyCommentMongoImpl.findCompanyCommentVO(companyId);
//					doc.add(new TextField("id", companyVO.getId(), Store.YES));  
			        doc.add(new StringField("id", companyVO.getId(), Store.YES));  
			        doc.add(new StringField("name", companyVO.getName(), Store.YES));
			        doc.add(new StringField("address_detail", companyVO.getAddress_detail(), Store.YES));  
			        if(!StringUtils.isEmpty(companyVO.getArea_business_id())&&!StringUtils.isEmpty(companyVO.getArea_business_name())){
			        	doc.add(new StringField("area_business_id", companyVO.getArea_business_id(), Store.YES));
			        	doc.add(new StringField("area_business_name", companyVO.getArea_business_name(), Store.YES)); 
			        }
			        doc.add(new StringField("area_city_id", companyVO.getArea_city_id(), Store.YES));  
			        doc.add(new StringField("area_city_name", companyVO.getArea_city_name(), Store.YES));  
			        doc.add(new StringField("area_county_id", companyVO.getArea_county_id(), Store.YES));  
			        doc.add(new StringField("area_county_name", companyVO.getArea_county_name(), Store.YES));  
			        doc.add(new StringField("area_province_id", companyVO.getArea_province_id(), Store.YES));  
			        doc.add(new StringField("area_province_name", companyVO.getArea_province_name(), Store.YES));  
			        doc.add(new StringField("company_category_id", companyVO.getCompany_category_id(), Store.YES));  
			        doc.add(new StringField("company_category_name", companyVO.getCompany_category_name(), Store.YES));  
			        doc.add(new StringField("company_grade_id", companyVO.getCompany_grade_id(), Store.YES));  
			        doc.add(new StringField("company_grade_name", companyVO.getCompany_grade_name(), Store.YES));  
			        doc.add(new StringField("hours", companyVO.getHours(), Store.YES));  
			        doc.add(new StringField("lat", companyVO.getLat(), Store.YES));  
			        doc.add(new StringField("lng", companyVO.getLng(), Store.YES));  
			        doc.add(new StringField("pinyin", companyVO.getPinyin(), Store.YES));
			        doc.add(new StringField("rebast", companyVO.getRebast().toString(), Store.YES));  
			        doc.add(new StringField("create_time", DateUtil.DateToString(companyVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN), Store.YES));
			        doc.add(new StringField("product_count", companyVO.getProduct_count(), Store.YES));
//			        doc.add(new LongField("order_count", ordersMongoImpl.findOrdersCount(companyVO.getId()), Store.YES));
//			        doc.add(new LongField("company_comment_count", companyCommentVO.getCompany_comment_count(), Store.YES));
//			        doc.add(new StringField("company_comment_point", companyCommentVO.getPoint(), Store.YES));
//			        if(null!=companyServes&&companyServes.size()>0){
//			        	doc.add(new StringField("company_serves", JSONArray.fromObject(companyServes).toString(), Store.YES));
//			        	companyServes.clear();
//			        	companyServes = null;
//			        }
			        docs.add(doc);
				}
				lucene.createIndex(docs);  
			}
			TimerJobUtil.updateTimerJob(jobDataMap.getString("id"));
			
        } catch(Exception e){
        	try {
				TimerJobUtil.updateExceptionTimerJob(jobDataMap.getString("id"),context.getTrigger().getGroup(),e.getMessage());
			} catch (Exception e1) {
				new YuleException(e1);
			}
        	new YuleException(e);
        } finally{
        	if(null!=lucene){
        		try {
					lucene.close();
				} catch (IOException e) {
					new YuleException(e);
				}
        	}
        }
	}
	
}
