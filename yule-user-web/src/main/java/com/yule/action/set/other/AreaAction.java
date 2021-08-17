package com.yule.action.set.other;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.exception.YuleException;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;

@Controller
@Scope("prototype")
@RequestMapping("/area")
public class AreaAction extends BaseAction {
	
	@RequestMapping(value = "/findProvince", method = RequestMethod.POST)
	public String findProvince() throws Exception {
		try{
			outputResult(JedisUtil.getInstance().get(RedisConst.AREA_PROVINCE));
		}catch(Exception e){
			new YuleException(e);
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/findCity", method = RequestMethod.POST)
	public String findCity(@RequestParam(value="provinceId",required=false)String provinceId) throws Exception {
		try{
			String key = RedisConst.AREA_CITY+provinceId;
			outputResult(JedisUtil.getInstance().get(key));
		}catch(Exception e){
			new YuleException(e);
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/findCounty", method = RequestMethod.POST)
	public String findCounty(@RequestParam(value="cityId",required=false)String cityId) throws Exception {
		try{
			String key = RedisConst.AREA_COUNTY+cityId;
			outputResult(JedisUtil.getInstance().get(key));
		}catch(Exception e){
			new YuleException(e);
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/findBusiness", method = RequestMethod.POST)
	public String findBusiness(@RequestParam(value="cityId",required=false)String cityId) throws Exception {
		try{
			String key = RedisConst.AREA_BUSINESS+cityId;
			outputResult(JedisUtil.getInstance().get(key));
		}catch(Exception e){
			new YuleException(e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}