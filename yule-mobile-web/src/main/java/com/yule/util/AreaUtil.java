package com.yule.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import com.yule.constant.CityNumberConst;
import com.yule.constant.CustomBeanConst;
import com.yule.constant.JSONConst;
import com.yule.constant.MobileMemcachedConst;
import com.yule.constant.PageConst;
import com.yule.constant.TimeConst;
import com.yule.memcached.util.MemcachedUtil;
import com.yule.mobile.service.IAreaCityService;
import com.yule.mobile.vo.AreaCityVO;
import com.yule.mongo.mobile.service.IOrdersMongo;
import com.yule.mongo.mobile.vo.OrdersHotAreaCityVO;
import com.yule.pojo.AreaCity;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.vo.CompanyCategoryCountVO;


public class AreaUtil {
	
	@SuppressWarnings("unchecked")
	public static void initAreaCityHot() throws Exception{
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		IOrdersMongo ordersMongoImpl = (IOrdersMongo)CustomBeanFactory.getContext(CustomBeanConst.MOBILE_MONGO_PATHS).getBean("ordersMongoImpl");
		int num = PageConst.PAGE_SIZE_EIGHT;
		List<OrdersHotAreaCityVO> ordersHotAreaCityVOs = ordersMongoImpl.findOrdersHotAreaCityVOList(num, PageConst.PAGE_NO_DEFAULT);
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer areaCityHot = new StringBuffer();
		List<CompanyCategoryCountVO> companyCategoryCountVOs = new ArrayList<CompanyCategoryCountVO>();
		if(null!=ordersHotAreaCityVOs&&ordersHotAreaCityVOs.size()>0){
			StringBuffer areaCityId = new StringBuffer("");
			for(OrdersHotAreaCityVO ordersHotAreaCityVO : ordersHotAreaCityVOs){
				areaCityId.append(ordersHotAreaCityVO.getArea_city_id());
				areaCityHot.append("<li >");
				areaCityHot.append("<a class=\"react\" data-area=\""+ordersHotAreaCityVO.getArea_city_id()+"\">"+ordersHotAreaCityVO.getArea_city_name()+"");
				if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY_CATEGORY_COUNT+ordersHotAreaCityVO.getArea_city_id())){
					companyCategoryCountVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY_CATEGORY_COUNT+ordersHotAreaCityVO.getArea_city_id())),new CompanyCategoryCountVO(),JSONConst.JSON_CONFIG));
				}
				if(companyCategoryCountVOs.size()>0){
					int companyCount = 0;
					for(CompanyCategoryCountVO companyCategoryCountVO:companyCategoryCountVOs){
						companyCount += companyCategoryCountVO.getCompany_count();
					}
					areaCityHot.append("("+companyCount+")&nbsp;&nbsp;");
					companyCategoryCountVOs.clear();
				}
				areaCityHot.append("</a>");
				areaCityHot.append("</li>");
				params.put(ordersHotAreaCityVO.getArea_city_id(), "");
				areaCityId.setLength(0);
			}
			num -= ordersHotAreaCityVOs.size();
			ordersHotAreaCityVOs.clear();
			ordersHotAreaCityVOs = null;
		}
		if(num>0){
			IAreaCityService areaCityServiceImpl = (IAreaCityService)CustomBeanFactory.getContext(CustomBeanConst.MOBILE_SERVICE_PATHS).getBean("areaCityServiceImpl");
			List<AreaCity> areaCitys = areaCityServiceImpl.findAreaCityList(Integer.MAX_VALUE,PageConst.PAGE_NO_DEFAULT);
			if(null!=areaCitys&&areaCitys.size()>0){
				StringBuffer areaCityId = new StringBuffer("");
				for(AreaCity areaCity:areaCitys){
					if(num==0){
						break;
					}
					if(!params.containsKey(areaCity.getId())){
						areaCityId.append(areaCity.getId());
						areaCityHot.append("<li >");
						areaCityHot.append("<a data-area=\""+areaCity.getId()+"\" class=\"react\""+">"+areaCity.getName()+"");
						if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY_CATEGORY_COUNT+areaCity.getId())){
							companyCategoryCountVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY_CATEGORY_COUNT+areaCity.getId())),new CompanyCategoryCountVO(),JSONConst.JSON_CONFIG));
						}
						if(companyCategoryCountVOs.size()>0){
							int companyCount = 0;
							for(CompanyCategoryCountVO companyCategoryCountVO:companyCategoryCountVOs){
								companyCount += companyCategoryCountVO.getCompany_count();
							}
							areaCityHot.append("("+companyCount+")&nbsp;&nbsp;");
							companyCategoryCountVOs.clear();
						}
						areaCityHot.append("</a>");
						areaCityHot.append("</li>");
						areaCityId.setLength(0);
						num--;
					}
				}
				areaCitys.clear();
				areaCitys = null;
			}
		}
		memcachedUtil.set(MobileMemcachedConst.MOBILE_HOT_CITY, areaCityHot.toString(),TimeConst.TWO_DAY);
	}
	
	
	@SuppressWarnings("unchecked")
	public static void initAreaCityNav() throws Exception{
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		StringBuffer areaCityNav = new StringBuffer();
		StringBuffer html = new StringBuffer();
		IAreaCityService areaCityServiceImpl = (IAreaCityService)CustomBeanFactory.getContext(CustomBeanConst.MOBILE_SERVICE_PATHS).getBean("areaCityServiceImpl");
		List<AreaCity> areaCitys = areaCityServiceImpl.findAreaCityList(Integer.MAX_VALUE, PageConst.PAGE_NO_DEFAULT);
		if(null!=areaCitys&&areaCitys.size()>0){
			IOrdersMongo ordersMongoImpl = (IOrdersMongo)CustomBeanFactory.getContext(CustomBeanConst.MOBILE_MONGO_PATHS).getBean("ordersMongoImpl");
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
			StringBuffer abc = new StringBuffer("");
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
			String div_end = "</ul></div>";
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
				switch (areaCity.getPinyin().charAt(0)) {
				case 'a':
					abc_append(a, "A",areaCity, name, companyCount);
					break;
				case 'b':
					abc_append(b, "B",areaCity, name, companyCount);
					break;
				case 'c':
					abc_append(c, "C",areaCity, name, companyCount);
					break;
				case 'd':
					abc_append(d, "D",areaCity, name, companyCount);
					break;
				case 'e':
					abc_append(e, "E",areaCity, name, companyCount);
					break;
				case 'f':
					abc_append(f, "F",areaCity, name, companyCount);
					break;
				case 'g':
					abc_append(g, "G",areaCity, name, companyCount);
					break;
				case 'h':
					abc_append(h, "H",areaCity, name, companyCount);
					break;
				case 'j':
					abc_append(j, "J",areaCity, name, companyCount);
					break;
				case 'k':
					abc_append(k, "K",areaCity, name, companyCount);
					break;
				case 'l':
					abc_append(l, "L",areaCity, name, companyCount);
					break;
				case 'm':
					abc_append(m, "M",areaCity, name, companyCount);
					break;
				case 'n':
					abc_append(n,"N", areaCity, name, companyCount);
					break;
				case 'p':
					abc_append(p, "P",areaCity, name, companyCount);
					break;
				case 'q':
					abc_append(q, "Q",areaCity, name, companyCount);
					break;
				case 'r':
					abc_append(r,"R", areaCity, name, companyCount);
					break;
				case 's':
					abc_append(s, "S",areaCity, name, companyCount);
					break;
				case 't':
					abc_append(t, "T",areaCity, name, companyCount);
					break;
				case 'w':
					abc_append(w,"W", areaCity, name, companyCount);
					break;
				case 'x':
					abc_append(x,"X", areaCity, name, companyCount);
					break;
				case 'y':
					abc_append(y, "Y",areaCity, name, companyCount);
					break;
				case 'z':
					abc_append(z,"Z", areaCity, name, companyCount);
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
			if(a.length()>0){
				abc.append("<li><a href=\"#A\" class=\"react\">A</a></li>");
				a.append(div_end);
			}
			if(b.length()>0){
				abc.append("<li><a href=\"#B\" class=\"react\">B</a></li>");
				b.append(div_end);
			}
			if(c.length()>0){
				abc.append("<li><a href=\"#C\" class=\"react\">C</a></li>");
				c.append(div_end);
			}
			if(d.length()>0){
				abc.append("<li><a href=\"#D\" class=\"react\">D</a></li>");
				d.append(div_end);
			}
			if(e.length()>0){
				abc.append("<li><a href=\"#E\" class=\"react\">E</a></li>");
				e.append(div_end);
			}
			if(f.length()>0){
				abc.append("<li><a href=\"#F\" class=\"react\">F</a></li>");
				f.append(div_end);
			}
			if(g.length()>0){
				abc.append("<li><a href=\"#G\" class=\"react\">G</a></li>");
				g.append(div_end);
			}
			if(h.length()>0){
				abc.append("<li><a href=\"#H\" class=\"react\">H</a></li>");
				h.append(div_end);
			}
/*			if(i.length()>0){
				i=div_start.append(i).append(div_end);
			}
*/			if(j.length()>0){
				abc.append("<li><a href=\"#J\" class=\"react\">J</a></li>");	
				j.append(div_end);
			}
			if(k.length()>0){
				abc.append("<li><a href=\"#K\" class=\"react\">K</a></li>");	
				k.append(div_end);
			}
			if(l.length()>0){
				abc.append("<li><a href=\"#L\" class=\"react\">L</a></li>");	
				l.append(div_end);
			}
			if(m.length()>0){
				abc.append("<li><a href=\"#M\" class=\"react\">M</a></li>");	
				m.append(div_end);
			}
			if(n.length()>0){
				abc.append("<li><a href=\"#N\" class=\"react\">N</a></li>");	
				n.append(div_end);
			}
/*			if(o.length()>0){
				o=div_start.append(l).append(div_end);
			}
*/			if(p.length()>0){
				abc.append("<li><a href=\"#P\" class=\"react\">P</a></li>");	
				p.append(div_end);
			}
			if(q.length()>0){
				abc.append("<li><a href=\"#Q\" class=\"react\">Q</a></li>");	
				q.append(div_end);
			}
			if(r.length()>0){
				abc.append("<li><a href=\"#R\" class=\"react\">R</a></li>");	
				r.append(div_end);
			}
			if(s.length()>0){
				abc.append("<li><a href=\"#S\" class=\"react\">S</a></li>");	
				s.append(div_end);
			}
			if(t.length()>0){
				abc.append("<li><a href=\"#T\" class=\"react\">T</a></li>");	
				t.append(div_end);
			}
/*			if(u.length()>0){
				u=div_start.append(t).append(div_end);
			}
*/			if(w.length()>0){
				w.append(div_end);
				abc.append("<li><a href=\"#W\" class=\"react\">W</a></li>");	
			}
			if(x.length()>0){
				x.append(div_end);
				abc.append("<li><a href=\"#X\" class=\"react\">X</a></li>");	
			}
			if(y.length()>0){
				y.append(div_end);
				abc.append("<li><a href=\"#Y\" class=\"react\">Y</a></li>");	
			}
			if(z.length()>0){
				abc.append("<li><a href=\"#Z\" class=\"react\">Z</a></li>");	
				z.append(div_end);
			}
			areaCitys.clear();
			areaCityNav.append(a).append(b).append(c).append(d).append(e).append(f).append(g).append(h).append(j).append(k).append(l).append(m).append(n).append(p).append(q).append(r).append(s).append(t).append(w).append(x).append(y).append(z);
			areaCitys = null;
			html.append("<h5>全部城市</h5><div class=\"box\"><ul class=\"charlist mui-clearfix\">");
			html.append(abc);
			html.append("</ul>");
			html.append(areaCityNav);
			html.append("</div>");
		}
		memcachedUtil.set(MobileMemcachedConst.MOBILE_INDEX_CITY, html.toString(), TimeConst.TWO_DAY);
	}
	
	private  static void abc_append(StringBuffer abc,String initial,AreaCity areaCity,StringBuffer name ,int companyCount){
		if(abc.length()==0){
			abc.append("<div class=\"abc\"><ul class=\"table\"><h4 id=\""+initial+"\">"+initial+"</h4>");
		}
		if(-1!=abc.indexOf("更多")){
			return;
		}
		if(abc.length()>CityNumberConst.len_500){
			abc.append("<li><a class=\"react\" href=\"javascript:;\" data-pinyin=\"Z\" >更多</a><li>");
			return;
		}
		abc.append("<li>");
		abc.append("<a class=\"react\" href=\"javascript:;\" data-area=\""+areaCity.getId()+"\" title=\""+name+" ("+companyCount+"家娱乐场所)\" >"+name+"("+companyCount+")</a>");
		abc.append("</li>");
	}
	
	public static void initAreaCityInitial(String initial) throws Exception{
		IAreaCityService areaCityServiceImpl = (IAreaCityService)CustomBeanFactory.getContext(CustomBeanConst.MOBILE_SERVICE_PATHS).getBean("areaCityServiceImpl");
		List<AreaCityVO> areaCityVOs = areaCityServiceImpl.findAreaCityByInitial(initial);
		StringBuffer html  =new StringBuffer();
		if (null!=areaCityVOs&&areaCityVOs.size()>0) {
			for (AreaCityVO areaCityVO : areaCityVOs) {
				html.append("<li><a class=\"react\" data-area=\""+areaCityVO.getId()+"\">"+areaCityVO.getName()+"</a></li>");
			}
		}
		JedisUtil.getInstance().set(RedisConst.AREA_BUSINESS+initial, html.toString(),TimeConst.TWO_DAY);
	}
	
}
