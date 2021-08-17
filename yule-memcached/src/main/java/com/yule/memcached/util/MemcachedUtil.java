package com.yule.memcached.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.danga.MemCached.MemCachedClient;
import com.yule.constant.CodeConst;
import com.yule.exception.YuleException;
import com.yule.memcached.pool.MemcachedPool;
import com.yule.memcached.server.MemcachedServer;

public class MemcachedUtil {
	
	private static MemcachedUtil unique = new MemcachedUtil();

	static{
		MemcachedServer server = new MemcachedServer("memcached.yuleing.com", 11211, 5);  
        List<MemcachedServer> servers = new ArrayList<MemcachedServer>();  
        servers.add(server);  
        MemcachedPool pool = MemcachedPool.getInstance();  
        try {
			pool.initPool(servers);
		} catch (YuleException e) {
			e.printStackTrace();
		}
        servers.clear();
	}
	
	private MemcachedUtil() {
		init();
	}

	public static MemcachedUtil getInstance() {
		return unique;
	}

	private MemCachedClient client = new MemCachedClient();

	private void init() {
		client.setPrimitiveAsString(true);
//		client.setCompressEnable(true);
		client.setDefaultEncoding(CodeConst.UTF_8);
	}

	public boolean set(String key, Object value) {
		return client.set(key, value);
	}

	public boolean set(String key, Object value, long expired) {
		return client.set(key, value, new Date(expired));
	}

	public Object get(String key) {
		return client.get(key);
	}
	
	public boolean delete(String key) {
		return client.delete(key);
	}
	
	public boolean keyExists(String key) {
		return client.keyExists(key);
	}
	
	public boolean replace(String key, Object value) {
		return client.replace(key, value);
	}
	
	public boolean replace(String key, Object value,long expired) {
		return client.replace(key, value,new Date(expired));
	}
	
	@SuppressWarnings("rawtypes")
	public void printStat() {
		Map stats = client.stats();
		Set keys = stats.keySet();
		Iterator keyIter = keys.iterator();
		while (keyIter.hasNext()) {
			String key = (String) keyIter.next();
			Object value = stats.get(key);
			System.out.println(key + "=" + value);
		}
	}
}
