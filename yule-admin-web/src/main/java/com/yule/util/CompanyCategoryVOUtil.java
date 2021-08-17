package com.yule.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.grouping.GroupDocs;
import org.apache.lucene.search.grouping.GroupingSearch;
import org.apache.lucene.search.grouping.TopGroups;
import org.apache.lucene.store.FSDirectory;

import com.yule.constant.JSONConst;
import com.yule.constant.LuceneConst;
import com.yule.exception.YuleException;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.vo.CompanyCategoryCountVO;
import com.yule.vo.CompanyCategoryVO;

public class CompanyCategoryVOUtil {
	
	public static List<CompanyCategoryCountVO> getCompanyCategoryCountVOListByAreaProvinceId(String areaProvinceId) throws Exception{
		List<CompanyCategoryCountVO> companyCategoryCountVOs = new ArrayList<CompanyCategoryCountVO>();
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH)));
		IndexSearcher searcher = new IndexSearcher(new MultiReader(reader));
		try{
			Query query=new WildcardQuery(new Term("area_province_id",areaProvinceId));
			getCompanyCategoryCountVOList(query, searcher, companyCategoryCountVOs);
		}catch(Exception e){
			new YuleException(e);
		}finally{
			if(null!=reader){
				reader.close();
			}
		}
		return companyCategoryCountVOs;
	}
	
	public static List<CompanyCategoryCountVO> getCompanyCategoryCountVOListByAreaCityId(String areaCityId) throws Exception{
		List<CompanyCategoryCountVO> companyCategoryCountVOs = new ArrayList<CompanyCategoryCountVO>();
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH)));
		IndexSearcher searcher = new IndexSearcher(new MultiReader(reader));
		try{
			Query query=new WildcardQuery(new Term("area_city_id",areaCityId));
			getCompanyCategoryCountVOList(query, searcher, companyCategoryCountVOs);
		}catch(Exception e){
			new YuleException(e);
		}finally{
			if(null!=reader){
				reader.close();
			}
		}
		return companyCategoryCountVOs;
	}
	
	public static List<CompanyCategoryCountVO> getCompanyCategoryCountVOListByAreaCountyId(String areaCountyId) throws Exception{
		List<CompanyCategoryCountVO> companyCategoryCountVOs = new ArrayList<CompanyCategoryCountVO>();
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH)));
		IndexSearcher searcher = new IndexSearcher(new MultiReader(reader));
		try{
			Query query=new WildcardQuery(new Term("area_county_id",areaCountyId));
			getCompanyCategoryCountVOList(query, searcher, companyCategoryCountVOs);
		}catch(Exception e){
			new YuleException(e);
		}finally{
			if(null!=reader){
				reader.close();
			}
		}
		return companyCategoryCountVOs;
	}
	
	public static List<CompanyCategoryCountVO> getCompanyCategoryCountVOListByAreaBusinessId(String areaBusinessId) throws Exception{
		List<CompanyCategoryCountVO> companyCategoryCountVOs = new ArrayList<CompanyCategoryCountVO>();
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH)));
		IndexSearcher searcher = new IndexSearcher(new MultiReader(reader));
		try{
			Query query=new WildcardQuery(new Term("area_business_id",areaBusinessId));
			getCompanyCategoryCountVOList(query, searcher, companyCategoryCountVOs);
		}catch(Exception e){
			new YuleException(e);
		}finally{
			if(null!=reader){
				reader.close();
			}
		}
		return companyCategoryCountVOs;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void getCompanyCategoryCountVOList(Query query,IndexSearcher searcher,List<CompanyCategoryCountVO> companyCategoryCountVOs) throws IOException{
		GroupingSearch  gSearch = new GroupingSearch("company_category_id");
		TopGroups topGroups=gSearch.search(searcher, query, 0, 10);//设置返回数据
		GroupDocs[] groupDocs = topGroups.groups;
//		JSONArray array = JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_CATEGORY));
		
		List<CompanyCategoryVO> companyCategoryVOs = new ArrayList<CompanyCategoryVO>();
		if(JedisUtil.getInstance().exists(RedisConst.COMPANY_CATEGORY)){
			companyCategoryVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_CATEGORY)),new CompanyCategoryVO(),JSONConst.JSON_CONFIG));
		}
		if(null!=groupDocs&&groupDocs.length>0){
			Document document = null;
			Map<String,Integer> maps = new HashMap<String, Integer>();
			ScoreDoc[] scoreDoc = null;
            for (int i = 0; i < groupDocs.length; i++) {
            	scoreDoc =groupDocs[i].scoreDocs;
            	document = searcher.doc(scoreDoc[0].doc);
            	String companyCategoryId = document.get("company_category_id");
//            		System.out.println(companyCategoryVOs.indexOf(companyCategoryId));
    			maps.put(companyCategoryId, groupDocs[i].totalHits);
            }
            CompanyCategoryCountVO companyCategoryCountVO = null;
            for(CompanyCategoryVO companyCategoryVO:companyCategoryVOs){
            	companyCategoryCountVO = new CompanyCategoryCountVO();
            	companyCategoryCountVO.setId(companyCategoryVO.getId());
            	companyCategoryCountVO.setName(companyCategoryVO.getName());
            	if(maps.containsKey(companyCategoryVO.getId())){
            		companyCategoryCountVO.setCompany_count(maps.get(companyCategoryVO.getId()));
            	}else{
            		companyCategoryCountVO.setCompany_count(0);
            	}
            	companyCategoryCountVOs.add(companyCategoryCountVO);
            }
            maps.clear();
		}else{
			CompanyCategoryCountVO companyCategoryCountVO = null;
			for(CompanyCategoryVO companyCategoryVO:companyCategoryVOs){
				companyCategoryCountVO = new CompanyCategoryCountVO();
        		companyCategoryCountVO.setId(companyCategoryVO.getId());
    			companyCategoryCountVO.setName(companyCategoryVO.getName());
				if(!companyCategoryCountVOs.contains(companyCategoryCountVO)){
					companyCategoryCountVO.setCompany_count(0);
    				companyCategoryCountVOs.add(companyCategoryCountVO);
				}
			}
		}
		companyCategoryVOs.clear();
	}
	public static void main(String[] args) throws Exception {
		getCompanyCategoryCountVOListByAreaCityId("610100");
//		testGroup(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH, "company_category_id", "");
	}
//	
//	public static List<HashMap<String, String>> testGroup(String indexPath,String groupField,String sumField){
//        List<HashMap<String, String>> map=new ArrayList<HashMap<String,String>>();
//        Directory d1=null; 
//         IndexReader read1=null;
//        try{
//         d1=FSDirectory.open(new File(indexPath));//磁盘索引
//          read1=DirectoryReader.open(d1);//打开流
//       IndexSearcher sear=new IndexSearcher(new MultiReader(read1));//MultiReader此类可以多份索引的读入
//       //但是得保证各个索引的字段结构一致
//        GroupingSearch  gSearch=new GroupingSearch(groupField);//分组查询按照place分组
//        Query q=new WildcardQuery(new Term(groupField,"*"));//查询所有数据
//          TopGroups t=gSearch.search(sear, q, 0, 10);//设置返回数据
//          GroupDocs[] g=t.groups;//获取分组总数
//          System.out.println("总数据数"+t.totalHitCount);
//          System.out.println("去重复后的数量:"+g.length);
//         for(int i=0;i<g.length;i++){
//               ScoreDoc []sd=g[i].scoreDocs;
//               String str  =sear.doc(sd[0].doc).get(groupField);
//           //System.out.println("place:"+str+"===>"+"个数:"+g[i].totalHits+);
//           System.out.println("place:"+str+"===>"+"个数:"+g[i].totalHits);
//               HashMap<String, String> m=new HashMap<String, String>();
//               m.put("word", str);
//               m.put("wx_total", "10000");
//               map.add(m);
//         }
//         read1.close();//关闭资源
//           d1.close(); 
//        }catch(Exception e){
//            e.printStackTrace();
//        } 
//        return map;
//    }
	
}
