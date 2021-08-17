package com.yule.util;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.yule.vo.IPAddressVO;

public class IPAddressUtil {
	
	//淘宝IP服务
	private static final String IP_SERVICE = "http://ip.taobao.com/service/getIpInfo.php";
	
	/**
	 * 根据IP获取地址信息
	 */
	public static IPAddressVO getAddresses(String ip) throws Exception {
		return (IPAddressVO)JSONObject.toBean(JSONObject.fromObject(HttpRequestUtil.doPost(IP_SERVICE, "ip="+ip)).getJSONObject("data"),IPAddressVO.class);
	}
	
	public static String getRemortIP(HttpServletRequest request) { 
		String ip = request.getHeader("x-forwarded-for");
		if(ip!=null){
			ip = ip.split(",")[0].trim();
		}
		return ip;
	}   
	
	public static void main(String[] args) {
		try {
			System.out.println(getAddresses("42.121.43.140").getIsp());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
