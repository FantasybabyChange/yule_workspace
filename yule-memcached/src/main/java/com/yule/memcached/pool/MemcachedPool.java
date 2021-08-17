package com.yule.memcached.pool;

import java.util.List;
import java.util.Properties;

import com.danga.MemCached.SockIOPool;
import com.yule.exception.YuleException;
import com.yule.memcached.server.MemcachedServer;

public class MemcachedPool {
	
//    private static Properties POOL_DEFAULT_VALUE = new PoolDefaultProperties();  
      
    private static MemcachedPool pool = new MemcachedPool();  
      
    private MemcachedPool() {}  
      
    public static MemcachedPool getInstance() {  
        return pool;  
    }  
      
    public void initPool(List<MemcachedServer> servers) throws YuleException {  
        initPool(servers, null);  
    }  
      
    public void initPool(List<MemcachedServer> servers, Properties props) throws YuleException {  
        SockIOPool sockIOPool = SockIOPool.getInstance();  
          
        //server & weight  
        sockIOPool.setServers(getServer(servers));  
        sockIOPool.setWeights(getWeight(servers));
        
        sockIOPool.setInitConn(10);//设置开始时每个cache服务器的可用连接数
        sockIOPool.setMinConn(10);//设置每个服务器最少可用连接数
        sockIOPool.setMaxConn(20);//设置每个服务器最大可用连接数
        sockIOPool.setMaxIdle(1000 * 60 * 30);//设置连接池维护线程的睡眠时间,设置为0，维护线程不启动.
        sockIOPool.setMaintSleep(1000 * 5);//主线程的睡眠时间
        sockIOPool.setNagle(false);
        sockIOPool.setSocketTO(1000*3);//设置socket的读取等待超时值
        sockIOPool.setSocketConnectTO(1000*3);//设置socket的连接等待超时值
//        Set keys = props.keySet();  
//        Iterator keyIter = keys.iterator();  
//        while (keyIter.hasNext()) {  
//            String key = (String)keyIter.next();  
//            String value = props.getProperty(key);  
//            if (value == null) {  
//                value = POOL_DEFAULT_VALUE.getProperty(key);  
//            }  
//            try {  
//                Class type = PropertyUtils.getPropertyType(sockIOPool, key);  
//                logger.debug("Type=" + type + ";Key=" + key + ";Value=" + value);  
//                Object val = ConvertUtils.convert(value, type);  
//                PropertyUtils.setSimpleProperty(sockIOPool, key, val);  
//            } catch (Exception e) {  
//            	throw new YuleException("初始化连接池失败", e);  
//            }  
//        }
        sockIOPool.initialize();  
    }  
      
    private Integer[] getWeight(List<MemcachedServer> weigths) {  
        Integer[] w = new Integer[weigths.size()];  
        for (int i = 0; i < weigths.size(); i++) {  
            w[i] = weigths.get(i).getWeight();  
        }  
        return w;  
    }  
      
    private String[] getServer(List<MemcachedServer> servers) {  
        String[] s = new String[servers.size()];  
        for (int i = 0; i < servers.size(); i++) {  
            MemcachedServer server = servers.get(i);  
            s[i] = server.getAddress() + ":" + server.getPort();  
        }  
        return s;  
    }  
}
