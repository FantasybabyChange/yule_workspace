package com.yule.quartz.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.util.Version;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

import com.yule.constant.CustomBeanConst;
import com.yule.constant.JSONConst;
import com.yule.constant.LuceneConst;
import com.yule.exception.YuleException;
import com.yule.pojo.AreaCity;
import com.yule.pojo.AreaProvince;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.timer.service.ICompanyService;
import com.yule.timer.vo.CompanySearchCriteriaLuceneVO;
import com.yule.util.CustomBeanFactory;
import com.yule.util.LuceneIndexUtil;
import com.yule.util.PinyinUtil;
import com.yule.util.TimerJobUtil;

public class CompanyCriteriaQuartz extends StatefulMethodInvokingJob{
	
	@SuppressWarnings("unchecked")
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		ICompanyService companyServiceImpl = (ICompanyService)CustomBeanFactory.getContext(CustomBeanConst.TIMER_SERVICE_PATHS).getBean("companyServiceImpl");
		LuceneIndexUtil lucene = null;
		try {
			lucene = new LuceneIndexUtil(LuceneConst.INDEX_SEARCH_CRITERIA_FILE_PATH, Version.LUCENE_46);
		} catch (Exception e) {
			new YuleException(e);
		}
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        try{
        	Document doc = null;
        	List<Document> docs = new ArrayList<Document>();
        	List<AreaProvince> areaProvinces = new ArrayList<AreaProvince>();
        	if(JedisUtil.getInstance().exists(RedisConst.AREA_PROVINCE)){
        		areaProvinces.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_PROVINCE)),new AreaProvince(),JSONConst.JSON_CONFIG));
    		}
        	if(areaProvinces.size()>0){
	        	for(AreaProvince areaProvince:areaProvinces){
			    	List<AreaCity> areaCitys = new ArrayList<AreaCity>();
			    	if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY+areaProvince.getId())){
			    		areaCitys.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY+areaProvince.getId())),new AreaCity(),JSONConst.JSON_CONFIG));
		    		}
		        	if(areaCitys.size()>0){
			        	for(AreaCity areaCity:areaCitys){
			        		doc = new Document();
			        		doc.add(new StringField("id", areaCity.getId(), Store.YES));  
					        doc.add(new StringField("name", areaProvince.getName()+","+areaCity.getName(), Store.YES));
					        doc.add(new StringField("type", "area_city", Store.YES));
					        String areaName = areaProvince.getName()+areaCity.getName();
					        String pinYin = PinyinUtil.getPinYin(areaName);
					        doc.add(new StringField("search_name",areaName + pinYin, Store.YES));
					        docs.add(doc);
			        	}
			        	areaCitys.clear();
		        	}
	        	}
	        	areaProvinces.clear();
        	}
        	
        	List<CompanySearchCriteriaLuceneVO> companySearchCriteriaVOs = companyServiceImpl.findCompanySearchCriteriaLuceneVOList();
        	if(null!=companySearchCriteriaVOs&&companySearchCriteriaVOs.size()>0){
        		for(CompanySearchCriteriaLuceneVO companySearchCriteriaLuceneVO:companySearchCriteriaVOs){
	        		doc = new Document();
	        		doc.add(new StringField("id", companySearchCriteriaLuceneVO.getId(), Store.YES));  
			        doc.add(new StringField("name", companySearchCriteriaLuceneVO.getName(), Store.YES));
			        doc.add(new StringField("type", "company_search_criteria", Store.YES));
			        String searName = companySearchCriteriaLuceneVO.getProvince_name()+companySearchCriteriaLuceneVO.getCity_name()+companySearchCriteriaLuceneVO.getName();
			        String pinYin = PinyinUtil.getPinYin(searName);
			        doc.add(new StringField("search_name",searName+pinYin, Store.YES));
			        docs.add(doc);
	        	}
        		companySearchCriteriaVOs.clear();
        		companySearchCriteriaVOs= null;
        	}
        	if(docs.size()>0){
        		lucene.createIndex(docs);
        		docs.clear();
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
			System.gc();
        }
        
	}

}
