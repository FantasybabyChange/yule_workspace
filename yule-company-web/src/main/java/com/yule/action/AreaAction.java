package com.yule.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
			JSONArray array = JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_PROVINCE));
			JSONArray newArray = new JSONArray();
			JSONObject obj = null;
			JSONObject newObj = new JSONObject();
			for(int i =0;i<array.size();i++){
				obj = array.getJSONObject(i);
				newObj.put("id",obj.get("id"));
				newObj.put("text",obj.get("name"));
				newArray.add(newObj);
			}
			outputResult(newArray.toString());
		}catch(Exception e){
			new YuleException(e);
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/findCity", method = RequestMethod.POST)
	public String findCity(@RequestParam(value="provinceId",required=false)Integer provinceId) throws Exception {
		try{
			String key = RedisConst.AREA_CITY+provinceId;
			JSONArray array = JSONArray.fromObject(JedisUtil.getInstance().get(key));
			JSONArray newArray = new JSONArray();
			JSONObject obj = null;
			JSONObject newObj = new JSONObject();
			for(int i =0;i<array.size();i++){
				obj = array.getJSONObject(i);
				newObj.put("id",obj.get("id"));
				newObj.put("text",obj.get("name"));
				newArray.add(newObj);
			}
			outputResult(newArray.toString());
		}catch(Exception e){
			new YuleException(e);
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/findCounty", method = RequestMethod.POST)
	public String findCounty(@RequestParam(value="cityId",required=false)Integer cityId) throws Exception {
		try{
			String key = RedisConst.AREA_COUNTY+cityId;
			JSONArray array = JSONArray.fromObject(JedisUtil.getInstance().get(key));
			JSONArray newArray = new JSONArray();
			JSONObject obj = null;
			JSONObject newObj = new JSONObject();
			for(int i =0;i<array.size();i++){
				obj = array.getJSONObject(i);
				newObj.put("id",obj.get("id"));
				newObj.put("text",obj.get("name"));
				newArray.add(newObj);
			}
			outputResult(newArray.toString());
		}catch(Exception e){
			new YuleException(e);
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/findBusiness", method = RequestMethod.POST)
	public String findBusiness(@RequestParam(value="cityId",required=false)Integer cityId) throws Exception {
		try{
			String key = RedisConst.AREA_BUSINESS+cityId;
			JSONArray array = JSONArray.fromObject(JedisUtil.getInstance().get(key));
			JSONArray newArray = new JSONArray();
			JSONObject obj = null;
			JSONObject newObj = new JSONObject();
			for(int i =0;i<array.size();i++){
				obj = array.getJSONObject(i);
				newObj.put("id",obj.get("id"));
				newObj.put("text",obj.get("name"));
				newArray.add(newObj);
			}
			outputResult(newArray.toString());
		}catch(Exception e){
			new YuleException(e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}