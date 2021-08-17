package com.yule.action;

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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.FileUploadConst;
import com.yule.constant.LuceneConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DecimalEnum;
import com.yule.exception.YuleException;
import com.yule.util.DecimalUtil;

@Controller
@Scope("prototype")
public class CompanyAction extends BaseAction {
	
//	@Autowired
//	private IOrdersMongo ordersMongoImpl;
//	@Autowired
//	private ICompanyService companyServiceImpl;

	/*
	 * @RequestMapping(value = "/findAreaCityHot",method = RequestMethod.POST)
	 * public String findAreaCityHot() throws Exception{ MemcachedUtil
	 * memcachedUtil = MemcachedUtil.getInstance();
	 * memcachedUtil.delete(MobileMemcachedConst.MOBILE_HOT_CITY);
	 * if(!memcachedUtil.keyExists(MobileMemcachedConst.MOBILE_HOT_CITY)){
	 * AreaUtil.initAreaCityHot(); } Object indexHotCity =
	 * memcachedUtil.get(MobileMemcachedConst.MOBILE_HOT_CITY);
	 * if(null!=indexHotCity){
	 * outputResult(memcachedUtil.get(MobileMemcachedConst
	 * .MOBILE_HOT_CITY).toString()); } return null; }
	 */
	@RequestMapping(value = "/findCompanyHot", method = RequestMethod.POST)
	public String findCompanyHot() throws Exception {
		String areaCityId = getAreaCookieValue();
		StringBuffer htmls = new StringBuffer();
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(
				LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH)));
		IndexSearcher searcher = new IndexSearcher(reader);
		// ICompanyCommentMongo companyCommentMongoImpl = (ICompanyCommentMongo)
		// CustomBeanFactory.getContext(CustomBeanConst.PC_MONGO_PATHS).getBean("companyCommentMongoImpl");
		BooleanQuery booleanQuery = new BooleanQuery();
		if (!StringUtils.isEmpty(areaCityId)) {
			TermQuery query = new TermQuery(
					new Term("area_city_id", areaCityId));
			booleanQuery.add(query, BooleanClause.Occur.MUST);
		}
		// if(!StringUtils.isEmpty(companyCategoryId)){
		// TermQuery query=new TermQuery(new Term("company_category_id",
		// companyCategoryId));
		// booleanQuery.add(query, BooleanClause.Occur.MUST);
		// }
		try{
			Sort sort = new Sort(new SortField("create_time", Type.SCORE, true));
			TopDocs topDocs = searcher.search(booleanQuery,PageConst.PAGE_SIZE_FIVE, sort);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			if (null != scoreDocs && scoreDocs.length > 0) {
//				OrdersHotVO ordersHotVO = null;
				Document document = null;
				StringBuffer id = new StringBuffer("");
				StringBuffer areaCityName = new StringBuffer("");
//				StringBuffer companyGradeName = new StringBuffer("");
//				StringBuffer timeAgo = new StringBuffer("");
				for (int i = 0; i < scoreDocs.length; i++) {
					document = searcher.doc(scoreDocs[i].doc);
					id.append(document.get("id"));
					areaCityName.append(document.get("area_city_name"));
//					companyGradeName.append(document.get("company_grade_name"));
//					ordersHotVO = ordersMongoImpl.findOrdersHotVO(id.toString());
					htmls.append("<li class=\"mui-index-item\" style=\"background-image: url("+FileUploadConst.COMPANY_IMAGE_PATH+ id+ "/"+ FileUploadConst.PX_150_150+ FileUploadConst.COMPANY_FACE+ FileUploadConst.IMAGE_TYPE + ");\">");
					htmls.append("<a href=\"/companyDetails/findCompanyDetails.do?id="+id+"\">");
					htmls.append("<p class=\"mui-index-place-name\">"+ document.get("name") + "</p>");
					htmls.append("<p class=\"mui-index-place-count\">");
					htmls.append(DecimalUtil.parseFloat(document.get("rebast"),DecimalEnum.FLOAT) + "折");
					htmls.append("</p>");
					htmls.append("<i class=\"icon-chevron-right icon-pull-right icon-large\"></i>");
					htmls.append("</a>");
					htmls.append("</li>");
					id.setLength(0);
					areaCityName.setLength(0);
//					companyGradeName.setLength(0);
//					timeAgo.setLength(0);
				}
				// companyCommentVO =
				// companyCommentMongoImpl.findCompanyCommentVO(companyHotVO.getId());
				
			}
		}catch(Exception e){
			new YuleException(e);
		}finally{
			outputResult(htmls.toString());
		}
		return null;
	}

}