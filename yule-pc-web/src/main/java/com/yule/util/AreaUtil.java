package com.yule.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import com.yule.constant.CustomBeanConst;
import com.yule.constant.DoMainConst;
import com.yule.constant.IndexMemcachedConst;
import com.yule.constant.JSONConst;
import com.yule.constant.PageConst;
import com.yule.constant.TimeConst;
import com.yule.memcached.constant.MemcachedConst;
import com.yule.memcached.util.MemcachedUtil;
import com.yule.mongo.pc.service.IOrdersMongo;
import com.yule.mongo.pc.vo.OrdersHotAreaCityVO;
import com.yule.pc.service.IAreaCityService;
import com.yule.pojo.AreaCity;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.vo.CompanyCategoryCountVO;


public class AreaUtil {
	
//	public static void init() throws Exception{
//		initAreaCityHotNav();
//		initAreaCityNav();
//		initAreaCityHot();
//	}
	
	@SuppressWarnings("unchecked")
	public static void initAreaCityHot() throws Exception{
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		IOrdersMongo ordersMongoImpl = (IOrdersMongo)CustomBeanFactory.getContext(CustomBeanConst.PC_MONGO_PATHS).getBean("ordersMongoImpl");
		List<OrdersHotAreaCityVO> ordersHotAreaCityVOs = ordersMongoImpl.findOrdersHotAreaCityVOList(PageConst.PAGE_SIZE_FIVE, PageConst.PAGE_NO_DEFAULT);
		int num = PageConst.PAGE_SIZE_FIVE;
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer areaCityHot = new StringBuffer();
		List<CompanyCategoryCountVO> companyCategoryCountVOs = new ArrayList<CompanyCategoryCountVO>();
		if(null!=ordersHotAreaCityVOs&&ordersHotAreaCityVOs.size()>0){
			StringBuffer areaCityId = new StringBuffer("");
			for(OrdersHotAreaCityVO ordersHotAreaCityVO : ordersHotAreaCityVOs){
				areaCityId.append(ordersHotAreaCityVO.getArea_city_id());
				areaCityHot.append("<li class=\"hot-city-item clearfix\">");
				areaCityHot.append("<a target=\"_blank\" href=\""+DoMainConst.SEARCH_URL+"/search/searchCompany.do?area_city_id="+areaCityId+"&area_city_name="+ordersHotAreaCityVO.getArea_city_name()+"\"><img  src=\"http://images.yuleing.com/loading/loading.jpg\" data-original=\"http://images.yuleing.com/area/city/"+ordersHotAreaCityVO.getArea_city_id()+"/100_100city.jpg\"></a>");
				areaCityHot.append("<h3 class=\"hot-city_title\"><a target=\"_blank\" href=\"http://search.yuleing.com/search/searchCompany.do?area_city_id="+areaCityId+"&area_city_name="+ordersHotAreaCityVO.getArea_city_name()+"\">"+ordersHotAreaCityVO.getArea_city_name()+"</a></h3>");
//					List<CompanyCategoryHotVO> companyCategoryHotVOs = companyCategoryServiceImpl.findCompanyCategoryHotVOListByAreaCityId(areaCityHotVO.getArea_city_id());
				if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY_CATEGORY_COUNT+ordersHotAreaCityVO.getArea_city_id())){
					companyCategoryCountVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY_CATEGORY_COUNT+ordersHotAreaCityVO.getArea_city_id())),new CompanyCategoryCountVO(),JSONConst.JSON_CONFIG));
				}
				if(companyCategoryCountVOs.size()>0){
					for(CompanyCategoryCountVO companyCategoryCountVO:companyCategoryCountVOs){
						areaCityHot.append("<a target=\"blank\" href=\"http://search.yuleing.com/search/searchCompany.do?area_city_id="+areaCityId+"&area_city_name="+ordersHotAreaCityVO.getArea_city_name()+"&company_category="+companyCategoryCountVO.getId()+"\">"+companyCategoryCountVO.getCompany_count()+"家"+companyCategoryCountVO.getName()+"</a>&nbsp;");
					}
					companyCategoryCountVOs.clear();
				}
				areaCityHot.append("</li>");
				params.put(ordersHotAreaCityVO.getArea_city_id(), "");
				areaCityId.setLength(0);
			}
			num -= ordersHotAreaCityVOs.size();
			ordersHotAreaCityVOs.clear();
			ordersHotAreaCityVOs = null;
		}
		if(num>0){
			IAreaCityService areaCityServiceImpl = (IAreaCityService)CustomBeanFactory.getContext(CustomBeanConst.PC_SERVICE_PATHS).getBean("areaCityServiceImpl");
			List<AreaCity> areaCitys = areaCityServiceImpl.findAreaCityList(Integer.MAX_VALUE,PageConst.PAGE_NO_DEFAULT);
			if(null!=areaCitys&&areaCitys.size()>0){
				StringBuffer areaCityId = new StringBuffer("");
				for(AreaCity areaCity:areaCitys){
					if(num==0){
						break;
					}
					if(!params.containsKey(areaCity.getId())){
						areaCityId.append(areaCity.getId());
						areaCityHot.append("<li class=\"hot-city-item clearfix\">");
						areaCityHot.append("<a href=\"javascript:;\" data-area=\""+areaCityId+"\"><img src=\"http://images.yuleing.com/loading/loading.jpg\" data-original=\"http://images.yuleing.com/area/city/"+areaCityId+"/100_100city.jpg\"></a>");
						areaCityHot.append("<h3 class=\"hot-city_title\"><a href=\"javascript:;\" data-area=\""+areaCityId+"\">"+areaCity.getName()+"</a></h3>");
						areaCityHot.append("<p class=\"b_popular_acc_types\">");
						if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY_CATEGORY_COUNT+areaCity.getId())){
							companyCategoryCountVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY_CATEGORY_COUNT+areaCity.getId())),new CompanyCategoryCountVO(),JSONConst.JSON_CONFIG));
						}
						if(companyCategoryCountVOs.size()>0){
							for(CompanyCategoryCountVO companyCategoryCountVO:companyCategoryCountVOs){
								areaCityHot.append("<a target=\"_blank\" href=\"http://search.yuleing.com/search/searchCompany.do?area_city_id="+areaCityId+"&area_city_name="+areaCity.getName()+"&company_category="+companyCategoryCountVO.getId()+"\">"+companyCategoryCountVO.getCompany_count()+"家"+companyCategoryCountVO.getName()+"</a>&nbsp;");
							}
							companyCategoryCountVOs.clear();
						}
						areaCityHot.append("</p>");
						areaCityHot.append("</li>");
						areaCityId.setLength(0);
						num--;
					}
				}
				areaCitys.clear();
				areaCitys = null;
			}
		}
		memcachedUtil.set(IndexMemcachedConst.INDEX_HOT_CITY, areaCityHot.toString(),TimeConst.TWO_DAY);
		areaCityHot.setLength(0);
		areaCityHot = null;
	}
	
	@SuppressWarnings("unchecked")
	public static String initAreaCityHotNav(String areaProvinceId) throws Exception{
//		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		List<AreaCity> areaCitys = new ArrayList<AreaCity>();
		if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY+areaProvinceId)){
			areaCitys.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY+areaProvinceId)),new AreaCity(),JSONConst.JSON_CONFIG));
		}
//			List<AreaCityRecommendVO> lists = areaCityServiceImpl.findAreaCityRecommendVOListByAreaProvinceId(areaProvinceId,Integer.MAX_VALUE,PageConst.PAGE_NO_DEFAULT);
		StringBuffer areaCityHot = new StringBuffer("");
		if(areaCitys.size()>0){
			List<CompanyCategoryCountVO> companyCategoryCountVOs = new ArrayList<CompanyCategoryCountVO>();
			StringBuffer name = new StringBuffer();
			int companyCount = 0;
			int k=1;
			for(AreaCity areaCity:areaCitys){
				if(k==PageConst.PAGE_SIZE_FIVE){
					break;
				}
				k++;
				name.append(areaCity.getName());
				if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY_CATEGORY_COUNT+areaCity.getId())){
					companyCategoryCountVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY_CATEGORY_COUNT+areaCity.getId())),new CompanyCategoryCountVO(),JSONConst.JSON_CONFIG));
				}
				if(companyCategoryCountVOs.size()>0){
					for(CompanyCategoryCountVO companyCategoryCountVO:companyCategoryCountVOs){
						companyCount+=companyCategoryCountVO.getCompany_count();
					}
					companyCategoryCountVOs.clear();
				}
				areaCityHot.append("<li>");
				areaCityHot.append("<a href=\"javascript:;\" title=\""+name+" ("+companyCount+"娱乐场所)\" data-area=\""+areaCity.getId()+"\">"+name+"</a>");
				areaCityHot.append("<span class=\"hotel-num\">("+companyCount+")</span>");
				areaCityHot.append("</li>");
				name.setLength(0);
				companyCount = 0;
			}
			areaCitys.clear();
		}
//		memcachedUtil.set(IndexMemcachedConst.INDEX_NAV_HOT_CITY, areaCityHot.toString(),TimeConst.TWO_DAY);
		return areaCityHot.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static void initAreaCityNav() throws Exception{
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		StringBuffer areaCityNav = new StringBuffer();
		
		IAreaCityService areaCityServiceImpl = (IAreaCityService)CustomBeanFactory.getContext(CustomBeanConst.PC_SERVICE_PATHS).getBean("areaCityServiceImpl");
		List<AreaCity> areaCitys = areaCityServiceImpl.findAreaCityList(Integer.MAX_VALUE, PageConst.PAGE_NO_DEFAULT);
		
		StringBuffer hot = new StringBuffer("<div class=\"tab-panel\">");
		StringBuffer abcd = new StringBuffer("<div class=\"tab-panel\" style=\"display: none;\">");
		StringBuffer efghj = new StringBuffer("<div class=\"tab-panel\" style=\"display: none;\">");
		StringBuffer klmnp = new StringBuffer("<div class=\"tab-panel\" style=\"display: none;\">");
		StringBuffer qrstw = new StringBuffer("<div class=\"tab-panel\" style=\"display: none;\">");
		StringBuffer xyz = new StringBuffer("<div class=\"tab-panel\" style=\"display: none;\">");
		if(null!=areaCitys&&areaCitys.size()>0){
			IOrdersMongo ordersMongoImpl = (IOrdersMongo)CustomBeanFactory.getContext(CustomBeanConst.PC_MONGO_PATHS).getBean("ordersMongoImpl");
			List<OrdersHotAreaCityVO> ordersHotAreaCityVOs = ordersMongoImpl.findOrdersHotAreaCityVOList(PageConst.PAGE_SIZE_TEN, PageConst.PAGE_NO_DEFAULT);
			
			Set<String> sets = new HashSet<String>();
			int num = PageConst.PAGE_SIZE_TEN;
			if(null!=ordersHotAreaCityVOs&&ordersHotAreaCityVOs.size()>0){
				for(OrdersHotAreaCityVO ordersHotAreaCityVO:ordersHotAreaCityVOs){
					sets.add(ordersHotAreaCityVO.getArea_city_id());
				}
				num -= ordersHotAreaCityVOs.size();
				ordersHotAreaCityVOs.clear();
				ordersHotAreaCityVOs = null;
			}
			if(num>0){
				AreaCity areaCity = null;
				for(int i=0;i<num;i++){
					areaCity =  areaCitys.get(i);
					sets.add(areaCity.getId());
				}
			}
			StringBuffer a = new StringBuffer("");
			StringBuffer b = new StringBuffer("");
			StringBuffer c = new StringBuffer("");
			StringBuffer d = new StringBuffer("");
			StringBuffer e = new StringBuffer("");
			StringBuffer f = new StringBuffer("");
			StringBuffer g = new StringBuffer("");
			StringBuffer h = new StringBuffer("");
			StringBuffer j = new StringBuffer("");
			StringBuffer k = new StringBuffer("");
			StringBuffer l = new StringBuffer("");
			StringBuffer m = new StringBuffer("");
			StringBuffer n = new StringBuffer("");
			StringBuffer p = new StringBuffer("");
			StringBuffer q = new StringBuffer("");
			StringBuffer r = new StringBuffer("");
			StringBuffer s = new StringBuffer("");
			StringBuffer t = new StringBuffer("");
			StringBuffer w = new StringBuffer("");
			StringBuffer x = new StringBuffer("");
			StringBuffer y = new StringBuffer("");
			StringBuffer z = new StringBuffer("");
			
			
			hot.append("<div class=\"destination-area-item clearfix\"><ul class=\"destination-area pl20 clearfix\">");
			
			StringBuffer id = new StringBuffer();
			StringBuffer name = new StringBuffer();
			int companyCount = 0;
			List<CompanyCategoryCountVO> companyCategoryCountVOs = new ArrayList<CompanyCategoryCountVO>();
			for(AreaCity areaCity : areaCitys) {
				id.append(areaCity.getId());
				name.append(areaCity.getName());
				if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY_CATEGORY_COUNT+id)){
					companyCategoryCountVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY_CATEGORY_COUNT+id)),new CompanyCategoryCountVO(),JSONConst.JSON_CONFIG));
				}
				if(companyCategoryCountVOs.size()>0){
					for(CompanyCategoryCountVO companyCategoryCountVO:companyCategoryCountVOs){
						companyCount+=companyCategoryCountVO.getCompany_count();
					}
					companyCategoryCountVOs.clear();
				}
				if(sets.contains(areaCity.getId())){
					hot.append("<li>");
					hot.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					hot.append("</li>");
				}
				switch (areaCity.getPinyin().charAt(0)) {
				case 'a':
					a.append("<li>");
					a.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					a.append("</li>");
					break;
				case 'b':
					b.append("<li>");
					b.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					b.append("</li>");
					break;
				case 'c':
					c.append("<li>");
					c.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					c.append("</li>");
					break;
				case 'd':
					d.append("<li>");
					d.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					d.append("</li>");
					break;
				case 'e':
					e.append("<li>");
					e.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					e.append("</li>");
					break;
				case 'f':
					f.append("<li>");
					f.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					f.append("</li>");
					break;
				case 'g':
					g.append("<li>");
					g.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					g.append("</li>");
					break;
				case 'h':
					h.append("<li>");
					h.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					h.append("</li>");
					break;
				case 'j':
					j.append("<li>");
					j.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					j.append("</li>");
					break;
				case 'k':
					k.append("<li>");
					k.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					k.append("</li>");
					break;
				case 'l':
					l.append("<li>");
					l.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					l.append("</li>");
					break;
				case 'm':
					m.append("<li>");
					m.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					m.append("</li>");
					break;
				case 'n':
					n.append("<li>");
					n.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					n.append("</li>");
					break;
				case 'p':
					p.append("<li>");
					p.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					p.append("</li>");
					break;
				case 'q':
					q.append("<li>");
					q.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					q.append("</li>");
					break;
				case 'r':
					r.append("<li>");
					r.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					r.append("</li>");
					break;
				case 's':
					s.append("<li>");
					s.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					s.append("</li>");
					break;
				case 't':
					t.append("<li>");
					t.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					t.append("</li>");
					break;
				case 'w':
					w.append("<li>");
					w.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					w.append("</li>");
					break;
				case 'x':
					x.append("<li>");
					x.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					x.append("</li>");
					break;
				case 'y':
					y.append("<li>");
					y.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					y.append("</li>");
					break;
				case 'z':
					z.append("<li>");
					z.append("<a href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"</a><span class=\"hotel-num\">("+companyCount+")</span>");
					z.append("</li>");
					break;
				default:
					break;
				}
				name.setLength(0);
				id.setLength(0);
				companyCount = 0;
			}
			if(sets.size()>0){
				sets.clear();
			}
			hot.append("</ul></div>");
			if(a.length()>0){abcd.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">A</div><ul class=\"destination-area pl20 clearfix\">"+a+"</ul></div>");a.setLength(0);}
			if(b.length()>0){abcd.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">B</div><ul class=\"destination-area pl20 clearfix\">"+b+"</ul></div>");b.setLength(0);}
			if(c.length()>0){abcd.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">C</div><ul class=\"destination-area pl20 clearfix\">"+c+"</ul></div>");c.setLength(0);}
			if(d.length()>0){abcd.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">D</div><ul class=\"destination-area pl20 clearfix\">"+d+"</ul></div>");d.setLength(0);}
			if(e.length()>0){efghj.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">E</div><ul class=\"destination-area pl20 clearfix\">"+e+"</ul></div>");e.setLength(0);}
			if(f.length()>0){efghj.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">F</div><ul class=\"destination-area pl20 clearfix\">"+f+"</ul></div>");f.setLength(0);}
			if(g.length()>0){efghj.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">G</div><ul class=\"destination-area pl20 clearfix\">"+g+"</ul></div>");g.setLength(0);}
			if(h.length()>0){efghj.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">H</div><ul class=\"destination-area pl20 clearfix\">"+h+"</ul></div>");h.setLength(0);}
			if(j.length()>0){efghj.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">J</div><ul class=\"destination-area pl20 clearfix\">"+j+"</ul></div>");j.setLength(0);}
			if(k.length()>0){klmnp.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">K</div><ul class=\"destination-area pl20 clearfix\">"+k+"</ul></div>");k.setLength(0);}
			if(l.length()>0){klmnp.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">L</div><ul class=\"destination-area pl20 clearfix\">"+l+"</ul></div>");l.setLength(0);}
			if(m.length()>0){klmnp.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">M</div><ul class=\"destination-area pl20 clearfix\">"+m+"</ul></div>");m.setLength(0);}
			if(n.length()>0){klmnp.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">N</div><ul class=\"destination-area pl20 clearfix\">"+n+"</ul></div>");n.setLength(0);}
			if(p.length()>0){klmnp.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">P</div><ul class=\"destination-area pl20 clearfix\">"+p+"</ul></div>");p.setLength(0);}
			if(q.length()>0){qrstw.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">Q</div><ul class=\"destination-area pl20 clearfix\">"+q+"</ul></div>");q.setLength(0);}
			if(r.length()>0){qrstw.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">R</div><ul class=\"destination-area pl20 clearfix\">"+r+"</ul></div>");r.setLength(0);}
			if(s.length()>0){qrstw.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">S</div><ul class=\"destination-area pl20 clearfix\">"+s+"</ul></div>");s.setLength(0);}
			if(t.length()>0){qrstw.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">T</div><ul class=\"destination-area pl20 clearfix\">"+t+"</ul></div>");t.setLength(0);}
			if(w.length()>0){qrstw.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">W</div><ul class=\"destination-area pl20 clearfix\">"+w+"</ul></div>");w.setLength(0);}
			if(x.length()>0){xyz.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">X</div><ul class=\"destination-area pl20 clearfix\">"+x+"</ul></div>");x.setLength(0);}
			if(y.length()>0){xyz.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">Y</div><ul class=\"destination-area pl20 clearfix\">"+y+"</ul></div>");y.setLength(0);}
			if(z.length()>0){xyz.append("<div class=\"destination-area-item clearfix\"><div class=\"destination-area-title\">Z</div><ul class=\"destination-area pl20 clearfix\">"+z+"</ul></div>");z.setLength(0);}
			areaCitys.clear();
			areaCitys = null;
		}
		hot.append("</div>");
		abcd.append("</div>");efghj.append("</div>");klmnp.append("</div>");qrstw.append("</div>");xyz.append("</div>");
		areaCityNav.append(hot);areaCityNav.append(abcd);areaCityNav.append(efghj);areaCityNav.append(klmnp);
		areaCityNav.append(qrstw);areaCityNav.append(xyz);
		hot.setLength(0);abcd.setLength(0);efghj.setLength(0);klmnp.setLength(0);
		qrstw.setLength(0);xyz.setLength(0);
		memcachedUtil.set(MemcachedConst.INDEX_NAV_CITY, areaCityNav.toString(), TimeConst.TWO_DAY);
		areaCityNav.setLength(0);
		areaCityNav = null;
	}
	
}
