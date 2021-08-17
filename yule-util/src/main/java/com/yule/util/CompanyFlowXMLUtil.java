package com.yule.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yule.constant.ConfigConst;

public class CompanyFlowXMLUtil {
	private static List<Element> companyFlows = null;
	
	@SuppressWarnings("unchecked")
	public static List<Element> getCompanyFlowDom() throws Exception{
		if(null==companyFlows){
			companyFlows = new ArrayList<Element>();
			SAXReader sr = new SAXReader();
			try {
				InputStream in = CompanyFlowXMLUtil.class.getClassLoader().getResourceAsStream(ConfigConst.COMPANY_FLOW);
				Document doc = sr.read(in);
				Element root = doc.getRootElement();
				companyFlows.addAll(root.elements("flow"));
				in.close();
				doc.clearContent();
			} catch (Exception e) {
				throw e;
			}
		}
		return companyFlows;
	}
	public static void main(String[] args) {
		try {
			System.out.println(getCompanyFlowDom());
			System.out.println(getCompanyFlowDom());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
