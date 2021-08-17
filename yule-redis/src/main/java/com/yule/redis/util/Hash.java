package com.yule.redis.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.yule.redis.pool.JedisCommonPool;

public class Hash{

	/**
	 * 从hash中删除指定的存储
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储的名字
	 * @return 状态码，1成功，0失败
	 * */
	public long hdel(String key, String fieid) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long s = jedis.hdel(key, fieid);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}

	public long hdel(String key) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long s = jedis.del(key);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}

	/**
	 * 测试hash中指定的存储是否存在
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储的名字
	 * @return 1存在，0不存在
	 * */
	public boolean hexists(String key, String fieid) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		boolean s = sjedis.hexists(key, fieid);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return s;
	}

	/**
	 * 返回hash中指定存储位置的值
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储的名字
	 * @return 存储对应的值
	 * */
	public String hget(String key, String fieid) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		String s = sjedis.hget(key, fieid);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return s;
	}

	public byte[] hget(byte[] key, byte[] fieid) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		byte[] s = sjedis.hget(key, fieid);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return s;
	}

	/**
	 * 以Map的形式返回hash中的存储和值
	 * 
	 * @param String
	 *            key
	 * @return Map<Strinig,String>
	 * */
	public Map<String, String> hgetAll(String key) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		Map<String, String> map = sjedis.hgetAll(key);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return map;
	}

	/**
	 * 添加一个对应关系
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid
	 * @param String
	 *            value
	 * @return 状态码 1成功，0失败，fieid已存在将更新，也返回0
	 * **/
	public long hset(String key, String fieid, String value) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long s = jedis.hset(key, fieid, value);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}

	public long hset(String key, String fieid, byte[] value) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long s = jedis.hset(key.getBytes(), fieid.getBytes(), value);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}

	/**
	 * 添加对应关系，只有在fieid不存在时才执行
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid
	 * @param String
	 *            value
	 * @return 状态码 1成功，0失败fieid已存
	 * **/
	public long hsetnx(String key, String fieid, String value) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long s = jedis.hsetnx(key, fieid, value);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}

	/**
	 * 获取hash中value的集合
	 * 
	 * @param String
	 *            key
	 * @return List<String>
	 * */
	public List<String> hvals(String key) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		List<String> list = sjedis.hvals(key);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return list;
	}

	/**
	 * 在指定的存储位置加上指定的数字，存储位置的值必须可转为数字类型
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储位置
	 * @param String
	 *            long value 要增加的值,可以是负数
	 * @return 增加指定数字后，存储位置的值
	 * */
	public long hincrby(String key, String fieid, long value) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long s = jedis.hincrBy(key, fieid, value);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}

	/**
	 * 返回指定hash中的所有存储名字,类似Map中的keySet方法
	 * 
	 * @param String
	 *            key
	 * @return Set<String> 存储名称的集合
	 * */
	public Set<String> hkeys(String key) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		Set<String> set = sjedis.hkeys(key);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return set;
	}

	/**
	 * 获取hash中存储的个数，类似Map中size方法
	 * 
	 * @param String
	 *            key
	 * @return long 存储的个数
	 * */
	public long hlen(String key) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		long len = sjedis.hlen(key);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return len;
	}

	/**
	 * 根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            ... fieids 存储位置
	 * @return List<String>
	 * */
	public List<String> hmget(String key, String... fieids) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		List<String> list = sjedis.hmget(key, fieids);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return list;
	}

	public List<byte[]> hmget(byte[] key, byte[]... fieids) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		List<byte[]> list = sjedis.hmget(key, fieids);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return list;
	}

	/**
	 * 添加对应关系，如果对应关系已存在，则覆盖
	 * 
	 * @param Strin
	 *            key
	 * @param Map
	 *            <String,String> 对应关系
	 * @return 状态，成功返回OK
	 * */
	public String hmset(String key, Map<String, String> map) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		String s = jedis.hmset(key, map);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}

	/**
	 * 添加对应关系，如果对应关系已存在，则覆盖
	 * 
	 * @param Strin
	 *            key
	 * @param Map
	 *            <String,String> 对应关系
	 * @return 状态，成功返回OK
	 * */
	public String hmset(byte[] key, Map<byte[], byte[]> map) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		String s = jedis.hmset(key, map);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}
}
