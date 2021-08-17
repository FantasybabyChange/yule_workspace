/*package com.yule.test.admin;

import java.io.FileWriter;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.context.ApplicationContext;

import com.yule.admin.service.IAreaCityService;
import com.yule.admin.service.IAreaCountyService;
import com.yule.admin.service.IAreaProvinceService;
import com.yule.constant.CustomBeanConst;
import com.yule.pojo.AreaCity;
import com.yule.pojo.AreaCounty;
import com.yule.pojo.AreaProvince;
import com.yule.util.CustomBeanFactory;
import com.yule.util.PinyinUtil;

public class AreaCityServiceTest extends TestCase {

	private IAreaCityService areaCityServiceImpl;
	
	private IAreaProvinceService areaProvinceServiceImpl;
	
	private IAreaCountyService areaCountyServiceImpl;

	public AreaCityServiceTest(String name) {
		super(name);
		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS);
		areaCityServiceImpl = (IAreaCityService) context.getBean("areaCityServiceImpl");
		areaProvinceServiceImpl = (IAreaProvinceService) context.getBean("areaProvinceServiceImpl");
		areaCountyServiceImpl = (IAreaCountyService) context.getBean("areaCountyServiceImpl");
	}
	
	public static Test suite() {
		TestSuite test = new TestSuite("AreaCityServiceTest接口测试");
		test.addTest(new AreaCityServiceTest("createXML"));
		return test;
	}
	
	public void createXML() throws Exception{
		try {
			 Document document = DocumentHelper.createDocument();
			 List<AreaProvince> areaProvinces = this.areaProvinceServiceImpl.findAreaProvinceList();
			 //根节点
			 Element areaElement = document.addElement("area");
			 for (AreaProvince areaProvince : areaProvinces) {
				 Element areaProvinceElement = areaElement.addElement("province");
				 areaProvinceElement.addAttribute("id", areaProvince.getId());
				 areaProvinceElement.addAttribute("name", areaProvince.getName());
				 List<AreaCity> areaCities = this.areaCityServiceImpl.findAreaCityList(areaProvince.getId());
				 for (AreaCity areaCity : areaCities) {
					 Element areaCityElement = areaProvinceElement.addElement("city");
					 areaCityElement.addAttribute("id", areaCity.getId());
					 areaCityElement.addAttribute("name", areaCity.getName());
					 areaCityElement.addAttribute("pinyin", areaCity.getPinyin());
					 List<AreaCounty> areaCounties =this.areaCountyServiceImpl.findAreaCountyList(areaCity.getId());
					 for (AreaCounty areaCounty : areaCounties) {
						 Element areaCountyElement =areaCityElement.addElement("county");
						 areaCountyElement.addAttribute("id", areaCounty.getId());
						 areaCountyElement.addAttribute("name", areaCounty.getName());
					}
				}
			}
			 OutputFormat format = OutputFormat.createPrettyPrint();
		     format.setEncoding("utf-8");    // 指定XML编码       
		     XMLWriter writer = new XMLWriter(new FileWriter("d://output.xml"),format);
		     writer.write(document);
		     writer.close();
		} catch (Exception e) {
			System.err.println("error");
			e.printStackTrace();
			throw e;
		}
	}
	public void findAreaCityList(){
		try {
			List<AreaCity> areaCities= this.areaCityServiceImpl.findAreaCityList(null);
			for (AreaCity areaCity : areaCities) {
				areaCity.setPinyin(PinyinUtil.getPinYin(areaCity.getName()));
				//this.areaCityServiceImpl.updateAreaCity(areaCity);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
*/