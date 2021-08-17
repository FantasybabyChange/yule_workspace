package com.yule.util;

import java.util.ArrayList;
import java.util.List;

public class IPUtil {
	
	public static List<String> ips = new ArrayList<String>();
	
	static{
		ips.add("10.168.69.193");
		ips.add("10.168.180.130");
		ips.add("192.168.1.223");
		ips.add("192.168.1.200");
		ips.add("192.168.1.201");
		ips.add("192.168.1.202");
		ips.add("127.0.0.1");
	}
	public static void main(String[] args) {
		System.out.println(ips.contains("10.168.180.130"));
	}
}
