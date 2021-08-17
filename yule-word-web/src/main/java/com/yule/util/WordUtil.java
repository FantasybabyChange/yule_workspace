package com.yule.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class WordUtil {
	
	private static List<String> words = new ArrayList<String>();
	
	private static final String WORD_RESOURCE = "word.properties";
	
	@SuppressWarnings("rawtypes")
	public static List<String> getWord() throws Exception{
		if(words.size()<=0){
			Properties word = new Properties();
			InputStream in = null;
			try{
				in = WordUtil.class.getClassLoader().getResourceAsStream(WORD_RESOURCE);
				word.load(in);
				Enumeration enu = word.propertyNames();
				while(enu.hasMoreElements()){
					words.add(enu.nextElement().toString());
				}
			} catch(Exception e){
				throw e;
			} finally{
				if(null!=in){
					in.close();
				}
			}
		}
		return words;
	}
	
	
	public static String doFilter(String str) throws Exception{
		List<String> lists = getWord();
		String word = null;
		for(String s : lists){
			if(str.indexOf(s)>-1){
				word = s;
				break;
			}
		}
		return word;
	}

}
