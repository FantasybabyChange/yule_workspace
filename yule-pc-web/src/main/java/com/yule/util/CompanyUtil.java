package com.yule.util;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.springframework.util.StringUtils;

import com.yule.constant.CustomBeanConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.LuceneConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.DecimalEnum;
import com.yule.mongo.pc.service.IOrdersMongo;
import com.yule.mongo.pc.vo.OrdersHotVO;

public class CompanyUtil {
	
	public static String companyHot(String areaCityId,String companyCategoryId) throws Exception{
		StringBuffer htmls = new StringBuffer();
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH)));
		IndexSearcher searcher = new IndexSearcher(reader);
		IOrdersMongo ordersMongoImpl = (IOrdersMongo) CustomBeanFactory.getContext(CustomBeanConst.PC_MONGO_PATHS).getBean("ordersMongoImpl");
//		ICompanyCommentMongo companyCommentMongoImpl = (ICompanyCommentMongo) CustomBeanFactory.getContext(CustomBeanConst.PC_MONGO_PATHS).getBean("companyCommentMongoImpl");
		BooleanQuery booleanQuery=new BooleanQuery();
		if(!StringUtils.isEmpty(areaCityId)){
			TermQuery query=new TermQuery(new Term("area_city_id", areaCityId));
			booleanQuery.add(query, BooleanClause.Occur.MUST);
		}
		if(!StringUtils.isEmpty(companyCategoryId)){
			TermQuery query=new TermQuery(new Term("company_category_id", companyCategoryId));
			booleanQuery.add(query, BooleanClause.Occur.MUST);
		}
		Sort sort = new Sort(new SortField("create_time",Type.SCORE,true));
		TopDocs topDocs=searcher.search(booleanQuery,PageConst.PAGE_SIZE_FIVE,sort);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		if(null!=scoreDocs&&scoreDocs.length>0){
			OrdersHotVO ordersHotVO = null;
			Document document = null;
			StringBuffer id = new StringBuffer("");
			StringBuffer areaCityName = new StringBuffer("");
			StringBuffer companyGradeName = new StringBuffer("");
			StringBuffer timeAgo = new StringBuffer("");
            for (int i = 0; i < scoreDocs.length; i++) {
            	document = searcher.doc(scoreDocs[i].doc);
            	id.append(document.get("id"));
            	areaCityName.append(document.get("area_city_name"));
            	companyGradeName.append(document.get("company_grade_name"));
            	ordersHotVO = ordersMongoImpl.findOrdersHotVO(id.toString());
//				companyCommentVO = companyCommentMongoImpl.findCompanyCommentVO(companyHotVO.getId());
				htmls.append("<li class=\"recommendation-item clearfix\">");
				htmls.append("<a target=\"_blank\" href=\"http://detail.yuleing.com/company/findCompanyDetails.do?id="+id+"\">");
				htmls.append("<img src=\"http://images.yuleing.com/loading/loading.jpg\" ");
				htmls.append("data-original=\""+FileUploadConst.COMPANY_IMAGE_PATH+id+"/"+FileUploadConst.PX_100_100+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE+"\" ");
				htmls.append("data-href=\""+FileUploadConst.COMPANY_IMAGE_PATH+id+"/"+FileUploadConst.PX_300_200+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE+"\" ");
				htmls.append("data-trigger=\"preview\" data-title=\""+areaCityName+" "+companyGradeName+"\" alt=\""+areaCityName+" "+companyGradeName+"\" title=\"位于"+areaCityName+" "+companyGradeName+"\" width=\"80\" height=\"80\">");
				htmls.append("</a>");
				htmls.append("<div class=\"recommendation-item-info\">");
				htmls.append("<a target=\"_blank\"  href=\"http://detail.yuleing.com/company/findCompanyDetails.do?id="+id+"\" class=\"history-list-title\">"+document.get("name")+"【"+document.get("company_grade_name")+"】</a>");
				htmls.append("<span class=\"feataddress\">"+document.get("address_detail")+", "+document.get("area_county_name")+"</span>");
				if(null!=ordersHotVO){
					htmls.append("<p>已有"+ordersHotVO.getOrders_count()+"人预订</p>");
					timeAgo.append(DateUtil.DateToString(ordersHotVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
					htmls.append("<span class=\"lastbooking\">最新的预订：<abbr class=\"timeago\" title=\""+timeAgo+"\">"+timeAgo+"</abbr></span>");
					timeAgo.setLength(0);
				}
//				htmls.append("<p>1位客人正在浏览这家酒店。</p>");
				htmls.append("</div>");
				htmls.append("<div class=\"recommendation-item-right\">");
				htmls.append("<p class=\"f20 index-scorewordspan\">"+DecimalUtil.parseFloat(document.get("rebast"), DecimalEnum.FLOAT)+"折</p>");
				htmls.append("<a target=\"_blank\"href=\"http://detail.yuleing.com/company/findCompanyDetails.do?id="+id+"\" class=\"b-button index-order-btn\">立即预订</a>");
				htmls.append("</div>");
				htmls.append("</li>");
				id.setLength(0);
				areaCityName.setLength(0);
				companyGradeName.setLength(0);
				timeAgo.setLength(0);
            }
		}
//		List<CompanyHotVO> companyHotVOs = companyServiceImpl.findCompanyHotVOList(areaCityId,companyCategoryId, PageConst.PAGE_SIZE_FIVE, PageConst.PAGE_NO_DEFAULT);
//		if(null!=companyHotVOs&&companyHotVOs.size()>0){
//			OrdersHotVO ordersHotVO = null;
////			CompanyCommentVO companyCommentVO = null;
//			StringBuffer timeAgo = new StringBuffer();
//			for(CompanyHotVO companyHotVO : companyHotVOs){
//				ordersHotVO = ordersMongoImpl.findOrdersHotVO(companyHotVO.getId());
////				companyCommentVO = companyCommentMongoImpl.findCompanyCommentVO(companyHotVO.getId());
//				htmls.append("<li class=\"recommendation-item clearfix\">");
//				htmls.append("<a target=\"_blank\" href=\"http://detail.yuleing.com/company/findCompanyDetails.do?id="+companyHotVO.getId()+"\">");
//				htmls.append("<img src=\"http://images.yuleing.com/company/"+companyHotVO.getId()+"/100_100"+companyHotVO.getId()+".jpg\" data-trigger=\"preview\" data-href=\"http://images.yuleing.com/company/"+companyHotVO.getId()+"/300_200"+companyHotVO.getId()+".jpg\" data-title=\""+companyHotVO.getArea_city_name()+" "+companyHotVO.getCompany_grade_name()+"\" alt=\""+companyHotVO.getArea_city_name()+" "+companyHotVO.getCompany_grade_name()+"\" title=\"位于"+companyHotVO.getArea_city_name()+" "+companyHotVO.getCompany_grade_name()+"\" width=\"80\" height=\"80\">");
//				htmls.append("</a>");
//				htmls.append("<div class=\"recommendation-item-info\">");
//				htmls.append("<a target=\"_blank\"  href=\"http://detail.yuleing.com/company/findCompanyDetails.do?id="+companyHotVO.getId()+"\" class=\"history-list-title\">"+companyHotVO.getName()+"</a>");
//				htmls.append("<span class=\"feataddress\">"+companyHotVO.getAddress_detail()+", "+companyHotVO.getArea_county_name()+", "+companyHotVO.getArea_city_name()+"</span>");
//				if(null!=ordersHotVO){
//					htmls.append("<p>已有"+ordersHotVO.getOrders_count()+"人预订</p>");
//					timeAgo.append(DateUtil.DateToString(ordersHotVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
//					htmls.append("<span class=\"lastbooking\">最新的预订：<abbr class=\"timeago\" title=\""+timeAgo+"\">"+timeAgo+"</abbr></span>");
//					timeAgo.setLength(0);
//				}
////				htmls.append("<p>1位客人正在浏览这家酒店。</p>");
//				htmls.append("</div>");
//				htmls.append("<div class=\"recommendation-item-right\">");
//				htmls.append("<p class=\"f20 index-scorewordspan\">"+companyHotVO.getRebast()+"折</p>");
//				htmls.append("<a target=\"_blank\"href=\"http://detail.yuleing.com/company/findCompanyDetails.do?id="+companyHotVO.getId()+"\" class=\"b-button index-order-btn\">立即预订</a>");
//				htmls.append("</div>");
//				htmls.append("</li>");
//			}
//			companyHotVOs.clear();
//			companyHotVOs = null;
//		}
		return htmls.toString();
	}
}
