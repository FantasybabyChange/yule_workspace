package com.yule.redis.pool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.yule.redis.constant.RedisPoolConst;

public class JedisCommonPool {

	private static JedisPool jedisPool = null;
	
	private static JedisCommonPool jedisCommonPool = new JedisCommonPool();
	
	static {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(RedisPoolConst.MAX_ACTIVE);
		config.setMaxIdle(RedisPoolConst.MAX_IDLE);
		config.setMaxWait(RedisPoolConst.MAX_WAIT);
		config.setTestOnBorrow(RedisPoolConst.TEST_ON_BORROW);
		config.setTestOnReturn(RedisPoolConst.TEST_ON_RETURN);
		jedisPool = new JedisPool(config, RedisPoolConst.HOST, RedisPoolConst.PORT, RedisPoolConst.TIME_OUT, RedisPoolConst.PASSWORD);
	}

	public JedisPool getPool() {
		return jedisPool;
	}

	/**
	 * 从jedis连接池中获取获取jedis对象
	 */
	public Jedis getJedis() {
		return jedisPool.getResource();
	}
	
	/**
	 * 获取JedisUtil实例
	 */
	public static JedisCommonPool getInstance() {
		return jedisCommonPool;
	}

	/**
	 * 回收jedis
	 */
	public void returnJedis(Jedis jedis) {
		if(null!=jedis){
			jedisPool.returnResource(jedis);
		}
	}
	
//	/**
//	 * 释放被损坏的jedis
//	 */
//	public void returnBrokenResource(Jedis jedis) {
//		if(null!=jedis){
//			jedisPool.returnBrokenResource(jedis);
//			jedis = null;
//		}
//	}

}
