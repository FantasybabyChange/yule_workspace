package com.yule.action;


import java.io.File;

import net.sf.json.JSONObject;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.DoMainConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.LuceneConst;
import com.yule.constant.PageConst;
import com.yule.exception.YuleException;
import com.yule.search.service.ICompanyService;

@Controller
@Scope("prototype")
@RequestMapping("/company")
public class CompanyAction extends BaseAction {
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	@RequestMapping(value="/findOtherCompany",method=RequestMethod.POST)
	public String findOtherCompany(@RequestParam(value="area_city_id",required=false)String area_city_id)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH)));
			IndexSearcher searcher = new IndexSearcher(reader);
			BooleanQuery booleanQuery=new BooleanQuery();
			if (!StringUtils.isEmpty(area_city_id)) {
				TermQuery query=new TermQuery(new Term("area_city_id", area_city_id));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
			}
		Sort sort = new Sort(new SortField("create_time",Type.SCORE,true));
		int end = PageConst.PAGE_SIZE_THREE*PageConst.PAGE_NO_DEFAULT;
		TopDocs topDocs=searcher.search(booleanQuery,end,sort);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			 StringBuffer otherCompanyHTMLs = new StringBuffer();
				if(null!=scoreDocs&&scoreDocs.length>0){
					int start=0;  
					if(end > scoreDocs.length){
						end = scoreDocs.length;
					}
					Document document = null;
					StringBuffer companyId = new StringBuffer(); 
		            for (int i = start; i < end; i++) {
			            document = searcher.doc(scoreDocs[i].doc);
			            companyId.append(document.get("id"));
						otherCompanyHTMLs.append(" <div class=\"nha_promote_type_search\">");
						otherCompanyHTMLs.append(" <a href=\""+DoMainConst.DETAIL_URL+"/company/findCompanyDetails.do?id="+companyId+"\" class=\"nha_filter_photo\">");
						otherCompanyHTMLs.append("<img width=\"200\" height=\"150\" src=\""+FileUploadConst.COMPANY_IMAGE_PATH+companyId+"/"+FileUploadConst.PX_200_150+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE+"\">");
						otherCompanyHTMLs.append( "</a>"+document.get("name")+" </div>");
						companyId.setLength(0);
					}
				}
			 object.put("otherCompanyHTMLs", otherCompanyHTMLs.toString());
			 otherCompanyHTMLs.setLength(0);
			 object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询其他企业[findOtherCompany]出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
}
