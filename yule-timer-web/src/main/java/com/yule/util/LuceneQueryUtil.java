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
//     * ????????????(?????????????????????)
//     * 
//     * ???????????????TermQuery????????????????????????<br/>
//     * Term term=new Term(?????????, ???????????????);<br/>
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
//        //Term term=new Term("name", "??????");
//        Term term=new Term("city", "??????");
//        
//        Query query=new TermQuery(term);
//        TopDocs topDocs=searcher.search(query, 1000);
//        System.out.println("???????????? " + topDocs.totalHits + " ?????????");
//        
//        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//        for (ScoreDoc scDoc : scoreDocs) {
//            Document document = searcher.doc(scDoc.doc);
//            String id = document.get("id");
//            String name = document.get("name");
//            String age = document.get("age");
//            String city = document.get("city");
//            String birthday = document.get("birthday");
//            float score = scDoc.score; //?????????
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, ?????????:%s.", id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//    
//    /**
//     * ????????????(?????????????????????????????????)<br/><br/>
//     * 
//     * ???????????????BooleanQuery????????????????????????<br/>
//     * Term term1=new Term(?????????, ???????????????);<br/>
//     * TermQuery query1=new TermQuery(term1);<br/><br/>
//     * 
//     * Term term2=new Term(?????????, ???????????????);<br/>
//     * TermQuery query2=new TermQuery(term2);<br/><br/>
//     * 
//     * BooleanQuery booleanQuery=new BooleanQuery();<br/>
//     * booleanQuery.add(query1, ??????);<br/>
//     * booleanQuery.add(query2, ??????);<br/><br/>
//     * 
//     * Hits hits=searcher.search(booleanquery);<br/>
//     * ????????????????????????BooleanQuery???add??????????????????????????????????????????????????????????????????????????????????????????<br/><br/>
//     * 
//     * ???????????????<br/>
//     * BooleanClause.Occur.MUST?????????????????????????????????????????????<br/>
//     * BooleanClause.Occur.MUST_NOT????????????????????????????????????????????????<br/>
//     * BooleanClause.Occur.SHOULD?????????????????????????????????????????????<br/>
//     * ?????????????????????????????????<br/>
//     * @throws Exception
//     */
//    @Test
//    public void booleanQuery() throws Exception {
//    	IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//        IndexSearcher searcher = new IndexSearcher(reader);
//        
//        //???????????????
//        //??????(???)???10???20???30???40
//        //??????(???): ???
//        //??????(???): ??????
//        TermQuery ageQuery10=new TermQuery(new Term("ages", "10"));
//        TermQuery ageQuery20=new TermQuery(new Term("ages", "20"));
//        TermQuery ageQuery30=new TermQuery(new Term("ages", "30"));
//        TermQuery ageQuery40=new TermQuery(new Term("ages", "40"));
//        
//        TermQuery nameQuery=new TermQuery(new Term("name", "???"));
//        
//        TermQuery cityQuery=new TermQuery(new Term("city", "??????"));
//        BooleanQuery booleanQuery=new BooleanQuery();
//        booleanQuery.add(ageQuery10, BooleanClause.Occur.SHOULD);
//        booleanQuery.add(ageQuery20, BooleanClause.Occur.SHOULD);
//        booleanQuery.add(ageQuery30, BooleanClause.Occur.SHOULD);
//        booleanQuery.add(ageQuery40, BooleanClause.Occur.SHOULD);
//        booleanQuery.add(nameQuery, BooleanClause.Occur.MUST);
//        booleanQuery.add(cityQuery, BooleanClause.Occur.MUST_NOT); 
//        
//        TopDocs topDocs=searcher.search(booleanQuery, 1000);
//        System.out.println("???????????? " + topDocs.totalHits + " ?????????");
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
//            float score = scDoc.score; //?????????
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, ?????????:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//    
//    /**
//     * ????????????(?????????????????????????????????????????????)<br/><br/>
//     * 
//     * ???????????????TermRangeQuery????????????????????????<br/>
//     * TermRangeQuery rangequery=new TermRangeQuery(?????????, ?????????, ?????????, ???????????????????????????, ???????????????????????????); <br/><br/>
//     * 
//     * Hits hits=searcher.search(rangequery);<br/>
//     * ????????????????????????Boolean???????????????????????????????????? ???<br/>
//     * true ????????????<br/>
//     * false???????????????<br/>
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
//        System.out.println("???????????? " + topDocs.totalHits + " ?????????");
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
//            float score = scDoc.score; //?????????
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, ?????????:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//    
//    /**
//     * ????????????(???????????????????????????????????????)<br/><br/>
//     * 
//     * ???????????????PrefixQuery????????????????????????<br/>
//     * Term term=new Term(?????????, ???????????????);<br/>
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
//        Term term=new Term("name", "???"); 
//        PrefixQuery prefixquery=new PrefixQuery(term);
//        
//        TopDocs topDocs=searcher.search(prefixquery, 1000);
//        System.out.println("???????????? " + topDocs.totalHits + " ?????????");
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
//            float score = scDoc.score; //?????????
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, ?????????:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//    
//    /**
//     * ????????????(??????????????????????????????????????????????????????)<br/><br/>
//     * 
//     * ???????????????PhraseQuery????????????????????????<br/>
//     * Term term1=new Term(?????????, ???????????????);<br/>
//     * Term term2=new Term(?????????, ???????????????);<br/><br/>
//     * 
//     * PhraseQuery phrasequery=new PhraseQuery();<br/>
//     * phrasequery.setSlop(??????);<br/>
//     * phrasequery.add(term1);<br/>
//     * phrasequery.add(term2);<br/>
//     * Hits hits=searcher.search(phrasequery);<br/>
//     * ??????setSlop??????????????????????????????????????????????????????????????????<br/>
//     * @throws Exception
//     */
//    @Test
//    public void phraseQuery() throws Exception {
//    	IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//        IndexSearcher searcher = new IndexSearcher(reader);
//        
//        Term term1=new Term("name", "???"); 
//        Term term2=new Term("name", "???"); 
//        
//        PhraseQuery phrasequery=new PhraseQuery(); 
//        phrasequery.setSlop(100); 
//        phrasequery.add(term1); 
//        phrasequery.add(term2); 
//        
//        TopDocs topDocs=searcher.search(phrasequery, 1000);
//        System.out.println("???????????? " + topDocs.totalHits + " ?????????");
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
//            float score = scDoc.score; //?????????
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, ?????????:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//    
//    /**
//     * ???????????????(????????????????????????????????????????????????????????????????????????????????????????????????????????????)<br/><br/>
//     * 
//     * ???????????????MultiPhraseQuery????????????????????????<br/>
//     * 
//     * Term term=new Term(?????????,???????????????);<br/>
//     * Term term1=new Term(?????????,???????????????);<br/>
//     * Term term2=new Term(?????????,???????????????);<br/><br/>
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
//        //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
//        Term term=new Term("name", "???"); //???????????????
//        Term term1=new Term("name", "???"); //???????????????
//        Term term2=new Term("name", "???"); //???????????????
//        
//        MultiPhraseQuery multiPhraseQuery=new MultiPhraseQuery();
//        multiPhraseQuery.add(term);
//        multiPhraseQuery.add(new Term[]{term1, term2});
//        
//        
//        TopDocs topDocs=searcher.search(multiPhraseQuery, 1000);
//        System.out.println("???????????? " + topDocs.totalHits + " ?????????");
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
//            float score = scDoc.score; //?????????
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, ?????????:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//    
//    /**
//     * ????????????(????????????)<br/><br/>
//     *
//     * ???????????????FuzzyQuery????????????????????????<br/><br/>
//     *
//     * Term term=new Term(?????????, ???????????????);<br/>
//     * FuzzyQuery fuzzyquery=new FuzzyQuery(term,??????);<br/>
//     * Hits hits=searcher.search(fuzzyquery);<br/>
//     * ?????????????????????????????????????????????1????????????????????????0.5f
//     * @throws Exception
//     */
//    @Test
//    public void fuzzyQuery() throws Exception {
//    	IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//        IndexSearcher searcher = new IndexSearcher(reader);
//        
//        Term term=new Term("name", "??????");
//        FuzzyQuery fuzzyquery=new FuzzyQuery(term, 0.5f); 
//        
//        TopDocs topDocs=searcher.search(fuzzyquery, 1000);
//        System.out.println("???????????? " + topDocs.totalHits + " ?????????");
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
//            float score = scDoc.score; //?????????
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, ?????????:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//
//    /**
//     * ?????????????????????????????????<br/><br/>
//     * 
//     * ??????????????????WildcardQuery????????????????????????<br/><br/>
//     * 
//     * Term term=new Term(?????????,???????????????+?????????);<br/>
//     * WildcardQuery wildcardquery=new WildcardQuery(term);<br/>
//     * Hits hits=searcher.search(wildcardquery);<br/><br/>
//     * 
//     * ?????????????????????????????????*??????<br/>
//     * * ????????????????????????<br/>
//     * ???????????????????????????
//     * @throws Exception
//     */
//    @Test
//    public void wildcardQuery() throws Exception {
//    	IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//        IndexSearcher searcher = new IndexSearcher(reader);
//        
//        Term term=new Term("name", "????");
//        WildcardQuery wildcardQuery=new WildcardQuery(term);
//
//        TopDocs topDocs=searcher.search(wildcardQuery, 1000);
//        System.out.println("???????????? " + topDocs.totalHits + " ?????????");
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
//            float score = scDoc.score; //?????????
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, ?????????:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//    
//    /**
//     * ??????????????????????????????????????????????????????lucene-queries-3.5.0.jar??????<br/><br/>
//     * 
//     * ??????????????????RegexQuery????????????????????? <br/>
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
//        String regex = "???*"; 
//        Term term=new Term("name", regex);
//        RegexQuery query = new RegexQuery(term);
//        
//        TopDocs topDocs=searcher.search(query, 1000);
//        System.out.println("???????????? " + topDocs.totalHits + " ?????????");
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
//            float score = scDoc.score; //?????????
//            
////            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s, ?????????:%s.", 
////                    id, name, age, city, DateUtils.longToString(Long.parseLong(birthday), Consts.FORMAT_SHORT), score));
//        }
//        
//        reader.close();
//    }
//
//    /**
//     * ??????????????????????????????int???long???float?????????
//     * 
//     * @throws Exception
//     */
//    @Test
//    public void numericFilter() throws Exception{ //CustomScoreQuery
//        //Filter filter = NumericRangeFilter.newLongRange("id", 1l, 3l, true, true);
//        Filter filter = NumericRangeFilter.newIntRange("age", 1, 39, true, true);
//        List<Person> persons=search(filter, new String[]{"name","city"}, "??????");
//        for(Person person : persons){
//            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s.", 
//                    person.getId(), person.getName(), person.getAge(), person.getCity(), DateUtils.dateToString(person.getBirthday(), Consts.FORMAT_SHORT)));
//        }
//    }
//    
//    /**
//     * ?????????????????????
//     * @throws Exception 
//     */
//    @Test
//    public void dateFilter() throws Exception{
//        //2008-06-12
//        long min=DateUtils.stringToDate("2008-06-12", Consts.FORMAT_SHORT).getTime();
//        //2013-01-07
//        long max=DateUtils.stringToDate("2013-01-07", Consts.FORMAT_SHORT).getTime();
//        Filter filter = NumericRangeFilter.newLongRange("birthday", min, max, true, true);
//        List<Person> persons=search(filter, new String[]{"name","city"}, "??????");
//        for(Person person : persons){
//            System.out.println(String.format("id:%s, name:%s, age:%s, city:%s, birthday:%s.", 
//                    person.getId(), person.getName(), person.getAge(), person.getCity(), DateUtils.dateToString(person.getBirthday(), Consts.FORMAT_SHORT)));
//        }
//    }
//    
//    /**
//     * ????????????
//     * 
//     * @throws Exception
//     */
//    @Test
//    public void createIndex() throws Exception {
//        List<Document> docs = new ArrayList<Document>();
//        for (Person person : getPersons()) {
//            Document doc = new Document();
//            //?????????NumericField?????????????????????NumericRangeFilter???????????????????????????????????????????????????
//            //NumericField?????????????????????Field
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
//            // ?????????????????????,?????????
//        	IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_COMPANY_FILE_PATH)));
//            indexSearcher = new IndexSearcher(indexReader);
//
//            MultiFieldQueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_46,
//                    fields, new IKAnalyzer());
//            Query query = queryParser.parse(keyword);
//
//            // ?????????number?????????
//            if(filter == null){
//                topDocs=indexSearcher.search(query, 100000);
//            }else {
//                topDocs=indexSearcher.search(query, filter, 100000);
//            }
//            
//            // ????????????
//            int totalCount = topDocs.totalHits;
//            System.out.println("???????????? " + totalCount + " ?????????");
//
//            //????????????
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
//                float score = scDoc.score; //?????????
//                System.out.println("????????????"+score);
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
