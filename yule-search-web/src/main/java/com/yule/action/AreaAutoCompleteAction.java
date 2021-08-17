package com.yule.action;

import java.io.File;
import java.io.IOException;

import net.sf.json.JSONArray;
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
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.FSDirectory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.LuceneConst;
import com.yule.exception.YuleException;

@Controller
@Scope("prototype")
public class AreaAutoCompleteAction extends BaseAction{
	
	@RequestMapping(value = "/findAreaAutoComplete",method = RequestMethod.GET)
	public String findAreaAutoComplete(@RequestParam(value="q",required=false)String search_name) throws Exception {
		JSONArray ja = new JSONArray();
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_CRITERIA_FILE_PATH)));
		IndexSearcher searcher = new IndexSearcher(reader);
		try {
			BooleanQuery booleanQuery=new BooleanQuery();
			BooleanQuery wildName=new BooleanQuery();
			if(!StringUtils.isEmpty(search_name)){
				Term term1=new Term("name","*"+search_name+"*");
				WildcardQuery wildcardquery1=new WildcardQuery(term1);
				wildName.add(wildcardquery1,BooleanClause.Occur.SHOULD);
				Term term=new Term("search_name","*"+search_name+"*");
				WildcardQuery wildcardquery=new WildcardQuery(term);
				wildName.add(wildcardquery,BooleanClause.Occur.SHOULD);
			}
			booleanQuery.add(wildName, BooleanClause.Occur.MUST);
			Sort sort = new Sort();
			sort.setSort(new SortField("name", Type.STRING));
			TopDocs topDocs=searcher.search(booleanQuery,reader.numDocs(),sort);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			for (ScoreDoc scoreDoc : scoreDocs) {
				Document doc = searcher.doc(scoreDoc.doc);
				JSONObject js = new JSONObject();
				js.put("id", doc.get("id"));
				js.put("name", doc.get("name"));
				js.put("type", doc.get("type"));
				js.put("search_name", doc.get("search_name"));
				ja.add(js);
			}
		} catch (Exception e) {
			new YuleException(e);
		}finally{
			outputResult(ja.toString());
		}
		return null;
	}
	public static void main(String[] args) throws IOException {
		String search_name = "西安,";
		JSONArray ja = new JSONArray();
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_CRITERIA_FILE_PATH)));
		IndexSearcher searcher = new IndexSearcher(reader);
		try {
			BooleanQuery booleanQuery=new BooleanQuery();
			BooleanQuery wildName=new BooleanQuery();
			if(!StringUtils.isEmpty(search_name)){
				Term term1=new Term("name","*"+search_name+"*");
				WildcardQuery wildcardquery1=new WildcardQuery(term1);
				wildName.add(wildcardquery1,BooleanClause.Occur.SHOULD);
				Term term=new Term("search_name","*"+search_name+"*");
				WildcardQuery wildcardquery=new WildcardQuery(term);
				wildName.add(wildcardquery,BooleanClause.Occur.SHOULD);
			}
			booleanQuery.add(wildName, BooleanClause.Occur.MUST);
			Sort sort = new Sort();
			sort.setSort(new SortField("search_name", Type.STRING));
			TopDocs topDocs=searcher.search(booleanQuery,reader.numDocs(),sort);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			for (ScoreDoc scoreDoc : scoreDocs) {
				Document doc = searcher.doc(scoreDoc.doc);
				JSONObject js = new JSONObject();
				js.put("id", doc.get("id"));
				js.put("name", doc.get("name"));
				js.put("type", doc.get("type"));
				System.out.println();
				ja.add(js);
			}
		} catch (Exception e) {
			new YuleException(e);
		}finally{
			System.out.println(ja.toString());
		}
	
	}
}