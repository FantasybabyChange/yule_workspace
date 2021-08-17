//package com.yule.test;
//
//import redis.clients.jedis.Jedis;
//
//import com.yule.redis.util.JedisUtil;
//
//public class JedisTest {
//
//	public static void main(String[] args) throws Exception {
//		// Strings strings = new Strings();
//		// strings.set("nnn", "nnnn");
//		// System.out.println("-----" + strings.get("nnn"));
//		Jedis jedis = JedisUtil.getInstance().getJedis();
//        //添加
//        jedis.sadd("company_privilege_url_925bb45f-6bc9-44b2-b63d-91abebf5bff1","minxr");
//        jedis.sadd("company_privilege_url_925bb45f-6bc9-44b2-b63d-91abebf5bff1","jarorwar");
//        jedis.sadd("company_privilege_url_925bb45f-6bc9-44b2-b63d-91abebf5bff1","闵晓荣");
////        jedis.sadd("sanme","noname");
////        //移除noname
////        jedis.srem("sname","noname");
////        jedis.pexpire("sname", 1000*60*60);
//        System.out.println(jedis.exists("company_privilege_url_925bb45f-6bc9-44b2-b63d-91abebf5bff1"));//获取所有加入的value
//        System.out.println(jedis.sismember("company_privilege_url_925bb45f-6bc9-44b2-b63d-91abebf5bff1", "/orders/findOrders.do"));//判断 minxr 是否是sname集合的元素
//        System.out.println(jedis.srandmember("company_privilege_url_925bb45f-6bc9-44b2-b63d-91abebf5bff1"));
//        System.out.println(jedis.smembers("company_privilege_url_925bb45f-6bc9-44b2-b63d-91abebf5bff1"));
//        System.out.println(jedis.scard("company_privilege_url_925bb45f-6bc9-44b2-b63d-91abebf5bff1"));//返回集合的元素个数
//		JedisUtil.getInstance().returnJedis(jedis);
//	}
//}
