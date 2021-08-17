package com.yule.redis.util;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;
import redis.clients.util.SafeEncoder;

import com.yule.redis.constant.RedisPoolConst;
import com.yule.redis.pool.JedisCommonPool;

public class Keys{
	/**
	 * 清空所有key
	 */
	public String flushAll() {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		String stata = jedis.flushAll();
		JedisCommonPool.getInstance().returnJedis(jedis);
		return stata;
	}

	/**
	 * 更改key
	 * 
	 * @param String
	 *            oldkey
	 * @param String
	 *            newkey
	 * @return 状态码
	 * */
	public String rename(String oldkey, String newkey) {
		return rename(SafeEncoder.encode(oldkey),
				SafeEncoder.encode(newkey));
	}

	/**
	 * 更改key,仅当新key不存在时才执行
	 * 
	 * @param String
	 *            oldkey
	 * @param String
	 *            newkey
	 * @return 状态码
	 * */
	public long renamenx(String oldkey, String newkey) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long status = jedis.renamenx(oldkey, newkey);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return status;
	}

	/**
	 * 更改key
	 * 
	 * @param String
	 *            oldkey
	 * @param String
	 *            newkey
	 * @return 状态码
	 * */
	public String rename(byte[] oldkey, byte[] newkey) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		String status = jedis.rename(oldkey, newkey);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return status;
	}

	/**
	 * 设置key的过期时间，以秒为单位
	 * 
	 * @param String
	 *            key
	 * @param 时间
	 *            ,已秒为单位
	 * @return 影响的记录数
	 * */
	public long expired(String key, int seconds) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long count = jedis.expire(key, seconds);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return count;
	}

	/**
	 * 设置key的过期时间,它是距历元（即格林威治标准时间 1970 年 1 月 1 日的 00:00:00，格里高利历）的偏移量。
	 * 
	 * @param String
	 *            key
	 * @param 时间
	 *            ,已秒为单位
	 * @return 影响的记录数
	 * */
	public long expireAt(String key, long timestamp) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long count = jedis.expireAt(key, timestamp);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return count;
	}

	/**
	 * 查询key的过期时间
	 * 
	 * @param String
	 *            key
	 * @return 以秒为单位的时间表示
	 * */
	public long ttl(String key) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		long len = sjedis.ttl(key);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return len;
	}

	/**
	 * 取消对key过期时间的设置
	 * 
	 * @param key
	 * @return 影响的记录数
	 * */
	public long persist(String key) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long count = jedis.persist(key);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return count;
	}

	/**
	 * 删除keys对应的记录,可以是多个key
	 * 
	 * @param String
	 *            ... keys
	 * @return 删除的记录数
	 * */
	public long del(String... keys) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long count = jedis.del(keys);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return count;
	}

	/**
	 * 删除keys对应的记录,可以是多个key
	 * 
	 * @param String
	 *            .. keys
	 * @return 删除的记录数
	 * */
	public long del(byte[]... keys) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long count = jedis.del(keys);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return count;
	}

	/**
	 * 判断key是否存在
	 * 
	 * @param String
	 *            key
	 * @return boolean
	 * */
	public boolean exists(String key) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		boolean exis = sjedis.exists(key);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return exis;
	}

	/**
	 * 对List,Set,SortSet进行排序,如果集合数据较大应避免使用这个方法
	 * 
	 * @param String
	 *            key
	 * @return List<String> 集合的全部记录
	 * **/
	public List<String> sort(String key) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		List<String> list = sjedis.sort(key);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return list;
	}

	/**
	 * 对List,Set,SortSet进行排序或limit
	 * 
	 * @param String
	 *            key
	 * @param SortingParams
	 *            parame 定义排序类型或limit的起止位置.
	 * @return List<String> 全部或部分记录
	 * **/
	public List<String> sort(String key, SortingParams parame) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		List<String> list = sjedis.sort(key, parame);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return list;
	}

	/**
	 * 返回指定key存储的类型
	 * 
	 * @param String
	 *            key
	 * @return String string|list|set|zset|hash
	 * **/
	public String type(String key) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		String type = sjedis.type(key);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return type;
	}

	/**
	 * 查找所有匹配给定的模式的键
	 * 
	 * @param String
	 *            key的表达式,*表示多个，？表示一个
	 * */
	public Set<String> keys(String pattern) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		Set<String> set = jedis.keys(pattern);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return set;
	}
	
	/**
	 * 设置过期时间
	 */
	public void expire(String key, int seconds) {
		if (seconds <= 0) {
			return;
		}
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		jedis.expire(key, seconds);
		JedisCommonPool.getInstance().returnJedis(jedis);
	}

	/**
	 * 设置默认过期时间
	 */
	public void expire(String key) {
		expire(key, RedisPoolConst.DEFAULT_EXPIRE);
	}
	
}
