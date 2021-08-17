//package com.yule.util;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.Consts;
//import org.apache.http.impl.cookie.DateUtils;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.Term;
//import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
//import org.apache.lucene.queryparser.classic.ParseException;
//import org.apache.lucene.sandbox.queries.regex.RegexQuery;
//import org.apache.lucene.search.BooleanClause;
//import org.apache.lucene.search.BooleanQuery;
//import org.apache.lucene.search.Filter;
//import org.apache.lucene.search.FuzzyQuery;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.MultiPhraseQuery;
//import org.apache.lucene.search.MultiTermQuery;
//import org.apache.lucene.search.NumericRangeFilter;
//import org.apache.lucene.search.PhraseQuery;
//import org.apache.lucene.search.PrefixQuery;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.TermQuery;
//import org.apache.lucene.search.TermRangeQuery;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.search.WildcardQuery;
//import org.apache.lucene.search.highlight.Formatter;
//import org.apache.lucene.search.highlight.Fragmenter;
//import org.apache.lucene.search.highlight.Highlighter;
//import org.apache.lucene.search.highlight.QueryScorer;
//import org.apache.lucene.search.highlight.SimpleFragmenter;
//import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
//import org.apache.lucene.store.FSDirectory;
//import org.apache.lucene.store.LockFactory;
//import org.apache.lucene.util.Version;
//import org.junit.Test;
//import org.springframework.util.NumberUtils;
//
//import com.yule.constant.LuceneConst;
//
//
//public class LuceneQueryUtil {
//	
//	 /**
//     * 词条搜索(单个关键字查找)
//     * 
//     * 主要对象是TermQuery，调用方式如下：<br/>
//     * Term term=new Term(字段名, 搜索关键字);<br/>
//     * Query query=new TermQuery(term);<br/>
//     * Hits hits=searcher.search(query);<br/>
//     * @throws Exception
//     */
//    @Test
//    public void termQuery() throws Exception {
//        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//        IndexSearcher searcher = new IndexSearcher(reader);
//        
//        //Term term=new Term("ids", "1");
//        //Term term=new Term("ages", "20");
//        //Term term=new Term("birthdays", "2008-06-12");
//        //Term term=new Term("name", "张三");
//        Term term=new Term("city", "厦门");
//        
//        Query query=new TermQuery(term);
//        TopDocs topDocs=searcher.search(query, 1000);
//        System.out.println("共检索出 " + topDocs.totalHits + " 条记录");
//        
//        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//        for (ScoreDoc scDoc : scoreDocs) {
//            Document document = searcher.doc(scDoc.doc);
//            String id = document.get("id");
//            String name = document.get("name");
//            String age = document.get("age");
//            String city = document.get("city");
//            String birthday = document.get("birthday");
//            float score = scDoc.score; //相似度
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, 相关度:%s.", id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//    
//    /**
//     * 组合搜索(允许多个关键字组合搜索)<br/><br/>
//     * 
//     * 主要对象是BooleanQuery，调用方式如下：<br/>
//     * Term term1=new Term(字段名, 搜索关键字);<br/>
//     * TermQuery query1=new TermQuery(term1);<br/><br/>
//     * 
//     * Term term2=new Term(字段名, 搜索关键字);<br/>
//     * TermQuery query2=new TermQuery(term2);<br/><br/>
//     * 
//     * BooleanQuery booleanQuery=new BooleanQuery();<br/>
//     * booleanQuery.add(query1, 参数);<br/>
//     * booleanQuery.add(query2, 参数);<br/><br/>
//     * 
//     * Hits hits=searcher.search(booleanquery);<br/>
//     * 此方法中的核心在BooleanQuery的add方法上，其第二个参数有三个可选值，对应着逻辑上的与或非关系。<br/><br/>
//     * 
//     * 参数如下：<br/>
//     * BooleanClause.Occur.MUST：必须包含，类似于逻辑运算的与<br/>
//     * BooleanClause.Occur.MUST_NOT：必须不包含，类似于逻辑运算的非<br/>
//     * BooleanClause.Occur.SHOULD：可以包含，类似于逻辑运算的或<br/>
//     * 这三者组合，妙用无穷。<br/>
//     * @throws Exception
//     */
//    @Test
//    public void booleanQuery() throws Exception {
//    	IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//        IndexSearcher searcher = new IndexSearcher(reader);
//        
//        //组合条件：
//        //年龄(或)：10、20、30、40
//        //名字(与): 四
//        //城市(非): 莆田
//        TermQuery ageQuery10=new TermQuery(new Term("ages", "10"));
//        TermQuery ageQuery20=new TermQuery(new Term("ages", "20"));
//        TermQuery ageQuery30=new TermQuery(new Term("ages", "30"));
//        TermQuery ageQuery40=new TermQuery(new Term("ages", "40"));
//        
//        TermQuery nameQuery=new TermQuery(new Term("name", "四"));
//        
//        TermQuery cityQuery=new TermQuery(new Term("city", "莆田"));
//        BooleanQuery booleanQuery=new BooleanQuery();
//        booleanQuery.add(ageQuery10, BooleanClause.Occur.SHOULD);
//        booleanQuery.add(ageQuery20, BooleanClause.Occur.SHOULD);
//        booleanQuery.add(ageQuery30, BooleanClause.Occur.SHOULD);
//        booleanQuery.add(ageQuery40, BooleanClause.Occur.SHOULD);
//        booleanQuery.add(nameQuery, BooleanClause.Occur.MUST);
//        booleanQuery.add(cityQuery, BooleanClause.Occur.MUST_NOT); 
//        
//        TopDocs topDocs=searcher.search(booleanQuery, 1000);
//        System.out.println("共检索出 " + topDocs.totalHits + " 条记录");
//        System.out.println();
//        
//        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//        for (ScoreDoc scDoc : scoreDocs) {
//            Document document = searcher.doc(scDoc.doc);
//            String id = document.get("id");
//            String name = document.get("name");
//            String age = document.get("age");
//            String city = document.get("city");
//            String birthday = document.get("birthday");
//            float score = scDoc.score; //相似度
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, 相关度:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//    
//    /**
//     * 范围搜索(允许搜索指定范围内的关键字结果)<br/><br/>
//     * 
//     * 主要对象是TermRangeQuery，调用方式如下：<br/>
//     * TermRangeQuery rangequery=new TermRangeQuery(字段名, 起始值, 终止值, 起始值是否包含边界, 终止值是否包含边界); <br/><br/>
//     * 
//     * Hits hits=searcher.search(rangequery);<br/>
//     * 此方法中的参数是Boolean类型的，表示是否包含边界 。<br/>
//     * true 包含边界<br/>
//     * false不包含边界<br/>
//     * @throws Exception
//     */
//    @Test
//    public void rangeQuery() throws Exception {
//    	IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//        IndexSearcher searcher = new IndexSearcher(reader);
//        
//        TermRangeQuery idQuery=new TermRangeQuery("ids", "1", "3", true, true);  
//        TermRangeQuery ageQuery=new TermRangeQuery("ages", "10", "30", true, true);  
//        TermRangeQuery timeQuery=new TermRangeQuery("birthdays", "2011-03-09", "2013-01-07", true, true);
//        
//        TopDocs topDocs=searcher.search(timeQuery, 1000);
//        System.out.println("共检索出 " + topDocs.totalHits + " 条记录");
//        System.out.println();
//        
//        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//        for (ScoreDoc scDoc : scoreDocs) {
//            Document document = searcher.doc(scDoc.doc);
//            String id = document.get("id");
//            String name = document.get("name");
//            String age = document.get("age");
//            String city = document.get("city");
//            String birthday = document.get("birthday");
//            float score = scDoc.score; //相似度
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, 相关度:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//    
//    /**
//     * 前缀搜索(搜索起始位置符合要求的结果)<br/><br/>
//     * 
//     * 主要对象是PrefixQuery，调用方式如下：<br/>
//     * Term term=new Term(字段名, 搜索关键字);<br/>
//     * PrefixQuery prefixquery=new PrefixQuery(term);<br/>
//     * Hits hits=searcher.search(prefixquery);<br/>
//     * 
//     * @throws Exception
//     */
//    @Test
//    public void prefixQuery() throws Exception {
//    	IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//        IndexSearcher searcher = new IndexSearcher(reader);
//        
//        Term term=new Term("name", "王"); 
//        PrefixQuery prefixquery=new PrefixQuery(term);
//        
//        TopDocs topDocs=searcher.search(prefixquery, 1000);
//        System.out.println("共检索出 " + topDocs.totalHits + " 条记录");
//        System.out.println();
//        
//        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//        for (ScoreDoc scDoc : scoreDocs) {
//            Document document = searcher.doc(scDoc.doc);
//            String id = document.get("id");
//            String name = document.get("name");
//            String age = document.get("age");
//            String city = document.get("city");
//            String birthday = document.get("birthday");
//            float score = scDoc.score; //相似度
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, 相关度:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//    
//    /**
//     * 短语搜索(根据零碎的短语组合成新的词组进行搜索)<br/><br/>
//     * 
//     * 主要对象是PhraseQuery，调用方式如下：<br/>
//     * Term term1=new Term(字段名, 搜索关键字);<br/>
//     * Term term2=new Term(字段名, 搜索关键字);<br/><br/>
//     * 
//     * PhraseQuery phrasequery=new PhraseQuery();<br/>
//     * phrasequery.setSlop(参数);<br/>
//     * phrasequery.add(term1);<br/>
//     * phrasequery.add(term2);<br/>
//     * Hits hits=searcher.search(phrasequery);<br/>
//     * 其中setSlop的参数是设置两个关键字之间允许间隔的最大值。<br/>
//     * @throws Exception
//     */
//    @Test
//    public void phraseQuery() throws Exception {
//    	IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//        IndexSearcher searcher = new IndexSearcher(reader);
//        
//        Term term1=new Term("name", "林"); 
//        Term term2=new Term("name", "钦"); 
//        
//        PhraseQuery phrasequery=new PhraseQuery(); 
//        phrasequery.setSlop(100); 
//        phrasequery.add(term1); 
//        phrasequery.add(term2); 
//        
//        TopDocs topDocs=searcher.search(phrasequery, 1000);
//        System.out.println("共检索出 " + topDocs.totalHits + " 条记录");
//        System.out.println();
//        
//        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//        for (ScoreDoc scDoc : scoreDocs) {
//            Document document = searcher.doc(scDoc.doc);
//            String id = document.get("id");
//            String name = document.get("name");
//            String age = document.get("age");
//            String city = document.get("city");
//            String birthday = document.get("birthday");
//            float score = scDoc.score; //相似度
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, 相关度:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//    
//    /**
//     * 多短语搜索(先指定一个前缀关键字，然后其他的关键字加在此关键字之后，组成词语进行搜索)<br/><br/>
//     * 
//     * 主要对象是MultiPhraseQuery，调用方式如下：<br/>
//     * 
//     * Term term=new Term(字段名,前置关键字);<br/>
//     * Term term1=new Term(字段名,搜索关键字);<br/>
//     * Term term2=new Term(字段名,搜索关键字);<br/><br/>
//     * 
//     * MultiPhraseQuery multiPhraseQuery=new MultiPhraseQuery();<br/><br/>
//     * 
//     * multiPhraseQuery.add(term);<br/>
//     * multiPhraseQuery.add(new Term[]{term1, term2});<br/><br/>
//     * 
//     * Hits hits=searcher.search(multiPhraseQuery);<br/>
//     * @throws Exception
//     */
//    @Test
//    public void multiPhraseQuery() throws Exception {
//    	IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//        IndexSearcher searcher = new IndexSearcher(reader);
//        
//        //查询“计张”、“计钦”组合的关键词，先指定一个前缀关键字，然后其他的关键字加在此关键字之后，组成词语进行搜索
//        Term term=new Term("name", "计"); //前置关键字
//        Term term1=new Term("name", "张"); //搜索关键字
//        Term term2=new Term("name", "钦"); //搜索关键字
//        
//        MultiPhraseQuery multiPhraseQuery=new MultiPhraseQuery();
//        multiPhraseQuery.add(term);
//        multiPhraseQuery.add(new Term[]{term1, term2});
//        
//        
//        TopDocs topDocs=searcher.search(multiPhraseQuery, 1000);
//        System.out.println("共检索出 " + topDocs.totalHits + " 条记录");
//        System.out.println();
//        
//        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//        for (ScoreDoc scDoc : scoreDocs) {
//            Document document = searcher.doc(scDoc.doc);
//            String id = document.get("id");
//            String name = document.get("name");
//            String age = document.get("age");
//            String city = document.get("city");
//            String birthday = document.get("birthday");
//            float score = scDoc.score; //相似度
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, 相关度:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//    
//    /**
//     * 模糊搜索(顾名思义)<br/><br/>
//     *
//     * 主要对象是FuzzyQuery，调用方式如下：<br/><br/>
//     *
//     * Term term=new Term(字段名, 搜索关键字);<br/>
//     * FuzzyQuery fuzzyquery=new FuzzyQuery(term,参数);<br/>
//     * Hits hits=searcher.search(fuzzyquery);<br/>
//     * 此中的参数是表示模糊度，是小于1的浮点小数，比如0.5f
//     * @throws Exception
//     */
//    @Test
//    public void fuzzyQuery() throws Exception {
//    	IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//        IndexSearcher searcher = new IndexSearcher(reader);
//        
//        Term term=new Term("name", "三张");
//        FuzzyQuery fuzzyquery=new FuzzyQuery(term, 0.5f); 
//        
//        TopDocs topDocs=searcher.search(fuzzyquery, 1000);
//        System.out.println("共检索出 " + topDocs.totalHits + " 条记录");
//        System.out.println();
//        
//        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//        for (ScoreDoc scDoc : scoreDocs) {
//            Document document = searcher.doc(scDoc.doc);
//            String id = document.get("id");
//            String name = document.get("name");
//            String age = document.get("age");
//            String city = document.get("city");
//            String birthday = document.get("birthday");
//            float score = scDoc.score; //相似度
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, 相关度:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//
//    /**
//     * 通配符搜索（顾名思义）<br/><br/>
//     * 
//     * 主要对象是：WildcardQuery，调用方式如下：<br/><br/>
//     * 
//     * Term term=new Term(字段名,搜索关键字+通配符);<br/>
//     * WildcardQuery wildcardquery=new WildcardQuery(term);<br/>
//     * Hits hits=searcher.search(wildcardquery);<br/><br/>
//     * 
//     * 其中的通配符分两种，即*和？<br/>
//     * * 表示任意多的自负<br/>
//     * ？表示任意一个字符
//     * @throws Exception
//     */
//    @Test
//    public void wildcardQuery() throws Exception {
//    	IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//        IndexSearcher searcher = new IndexSearcher(reader);
//        
//        Term term=new Term("name", "三?");
//        WildcardQuery wildcardQuery=new WildcardQuery(term);
//
//        TopDocs topDocs=searcher.search(wildcardQuery, 1000);
//        System.out.println("共检索出 " + topDocs.totalHits + " 条记录");
//        System.out.println();
//        
//        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//        for (ScoreDoc scDoc : scoreDocs) {
//            Document document = searcher.doc(scDoc.doc);
//            String id = document.get("id");
//            String name = document.get("name");
//            String age = document.get("age");
//            String city = document.get("city");
//            String birthday = document.get("birthday");
//            float score = scDoc.score; //相似度
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, 相关度:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//    
//    /**
//     * 正则表达式搜索（顾名思义，这个类引入lucene-queries-3.5.0.jar包）<br/><br/>
//     * 
//     * 主要对象是：RegexQuery，调用方式如下 <br/>
//     * String regex = ".*"; <br/>
//     * Term term = new Term (search_field_name, regex); <br/>
//     * RegexQuery query = new RegexQuery (term); <br/>
//     * TopDocs hits = searcher.search (query, 100); <br/>
//     * @throws Exception
//     */
//    @Test
//    public void regexQuery() throws Exception {
//    	IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//        IndexSearcher searcher = new IndexSearcher(reader);
//         
//        String regex = "林*"; 
//        Term term=new Term("name", regex);
//        RegexQuery query = new RegexQuery(term);
//        
//        TopDocs topDocs=searcher.search(query, 1000);
//        System.out.println("共检索出 " + topDocs.totalHits + " 条记录");
//        System.out.println();
//        
//        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//        for (ScoreDoc scDoc : scoreDocs) {
//            Document document = searcher.doc(scDoc.doc);
//            String id = document.get("id");
//            String name = document.get("name");
//            String age = document.get("age");
//            String city = document.get("city");
//            String birthday = document.get("birthday");
//            float score = scDoc.score; //相似度
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, 相关度:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//
//    /**
//     * 数值范围过滤器，如：int、long、float类型等
//     * 
//     * @throws Exception
//     */
//    @Test
//    public void numericFilter() throws Exception{ //CustomScoreQuery
//        //Filter filter = NumericRangeFilter.newLongRange("id", 1l, 3l, true, true);
//        Filter filter = NumericRangeFilter.newIntRange("age", 1, 39, true, true);
//        List<Person> persons=search(filter, new String[]{"name","city"}, "厦门");
//        for(Person person : persons){
//            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s.", 
//                    person.getId(), person.getName(), person.getAge(), person.getCity(), DateUtils.dateToString(person.getBirthday(), Consts.FORMAT_SHORT)));
//        }
//    }
//    
//    /**
//     * 时间范围过滤器
//     * @throws Exception 
//     */
//    @Test
//    public void dateFilter() throws Exception{
//        //2008-06-12
//        long min=DateUtils.stringToDate("2008-06-12", Consts.FORMAT_SHORT).getTime();
//        //2013-01-07
//        long max=DateUtils.stringToDate("2013-01-07", Consts.FORMAT_SHORT).getTime();
//        Filter filter = NumericRangeFilter.newLongRange("birthday", min, max, true, true);
//        List<Person> persons=search(filter, new String[]{"name","city"}, "厦门");
//        for(Person person : persons){
//            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s.", 
//                    person.getId(), person.getName(), person.getAge(), person.getCity(), DateUtils.dateToString(person.getBirthday(), Consts.FORMAT_SHORT)));
//        }
//    }
//    
//    /**
//     * 创建索引
//     * 
//     * @throws Exception
//     */
//    @Test
//    public void createIndex() throws Exception {
//        List<Document> docs = new ArrayList<Document>();
//        for (Person person : getPersons()) {
//            Document doc = new Document();
//            //声明为NumericField的字段，只能用NumericRangeFilter对象范围查询，不能用作关键字查询。
//            //NumericField不推荐，统一用Field
//            doc.add(new NumericField("id", Field.Store.YES, true).setLongValue(person.getId()));
//            doc.add(new NumericField("age", Field.Store.YES, true).setIntValue(person.getAge()));
//            doc.add(new NumericField("birthday", Field.Store.YES, true).setLongValue(person.getBirthday().getTime()));
//            
//            doc.add(new Field("ids", person.getId()+"", Field.Store.YES, Field.Index.NOT_ANALYZED));
//            doc.add(new Field("ages", person.getAge()+"", Field.Store.YES, Field.Index.NOT_ANALYZED));
//            doc.add(new Field("birthdays", DateUtils.dateToString(person.getBirthday(), Consts.FORMAT_SHORT), 
//                    Field.Store.YES, Field.Index.NOT_ANALYZED));
//            doc.add(new Field("name", person.getName(), Field.Store.YES, Field.Index.ANALYZED));
//            doc.add(new Field("city", person.getCity(), Field.Store.YES, Field.Index.ANALYZED));
//            
//            docs.add(doc);
//        } 
//        LuceneUtil.createIndex(docs);
//    }
//    
//    private List<Person> search(Filter filter, String[] fields, String keyword) {
//        List<Person> result = new ArrayList<Person>();
//        
//        IndexSearcher indexSearcher = null;
//        TopDocs topDocs = null;
//        try {
//            // 创建索引搜索器,且只读
//        	IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//            indexSearcher = new IndexSearcher(indexReader);
//
//            MultiFieldQueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_46,
//                    fields, new IKAnalyzer());
//            Query query = queryParser.parse(keyword);
//
//            // 返回前number条记录
//            if(filter == null){
//                topDocs=indexSearcher.search(query, 100000);
//            }else {
//                topDocs=indexSearcher.search(query, filter, 100000);
//            }
//            
//            // 信息展示
//            int totalCount = topDocs.totalHits;
//            System.out.println("共检索出 " + totalCount + " 条记录");
//
//            //高亮显示
//            Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
//            QueryScorer fragmentScorer = new QueryScorer(query);
//            Highlighter highlighter = new Highlighter(formatter, fragmentScorer);
//            Fragmenter fragmenter = new SimpleFragmenter(100);
//            highlighter.setTextFragmenter(fragmenter);
//
//            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//
//            for (ScoreDoc scDoc : scoreDocs) {
//                Document document = indexSearcher.doc(scDoc.doc);
//                String id = document.get("id");
//                String name = document.get("name");
//                String age = document.get("age");
//                String city = document.get("city");
//                String birthday = document.get("birthday");
//                float score = scDoc.score; //相似度
//                System.out.println("相似度："+score);
//
//                String lighterName = highlighter.getBestFragment(new IKAnalyzer(), "name", name);
//                if (null == lighterName) {
//                    lighterName = name;
//                }
//
//                String lighterAge = highlighter.getBestFragment(new IKAnalyzer(), "age", age);
//                if (null == lighterAge) {
//                    lighterAge = age;
//                }
//                
//                String lighterCity= highlighter.getBestFragment(new IKAnalyzer(), "city", city);
//                if (null == lighterCity) {
//                    lighterCity = city;
//                }
//                
//                String lighterBirthday = highlighter.getBestFragment(new IKAnalyzer(), "birthday", birthday);
//                if (null == lighterBirthday) {
//                    lighterBirthday = birthday;
//                }
//
//                Person person = new Person();
//                person.setId(Long.parseLong(id));
//                person.setName(lighterName);
//                person.setAge(NumberUtils.toInt(age));
//                person.setCity(lighterCity);
//                person.setBirthday(DateUtils.longToDate(Long.parseLong(lighterBirthday)));
//                result.add(person);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                indexSearcher.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return result;
//    }
//    
//  
//}
