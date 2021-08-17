package com.yule.redis.util;

import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.yule.redis.pool.JedisCommonPool;

public class SortSet{
	/**
	 * 向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重
	 * 
	 * @param String
	 *            key
	 * @param double score 权重
	 * @param String
	 *            member 要加入的值，
	 * @return 状态码 1成功，0已存在member的值
	 * */
	public long zadd(String key, double score, String member) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long s = jedis.zadd(key, score, member);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}

	public long zadd(String key, Map<Double, String> scoreMembers) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long s = jedis.zadd(key, scoreMembers);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}

	/**
	 * 获取集合中元素的数量
	 * 
	 * @param String
	 *            key
	 * @return 如果返回0则集合不存在
	 * */
	public long zcard(String key) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		long len = sjedis.zcard(key);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return len;
	}

	/**
	 * 获取指定权重区间内集合的数量
	 * 
	 * @param String
	 *            key
	 * @param double min 最小排序位置
	 * @param double max 最大排序位置
	 * */
	public long zcount(String key, double min, double max) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		long len = sjedis.zcount(key, min, max);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return len;
	}

	/**
	 * 获得set的长度
	 * 
	 * @param key
	 * @return
	 */
	public long zlength(String key) {
		long len = 0;
		Set<String> set = zrange(key, 0, -1);
		len = set.size();
		return len;
	}

	/**
	 * 权重增加给定值，如果给定的member已存在
	 * 
	 * @param String
	 *            key
	 * @param double score 要增的权重
	 * @param String
	 *            member 要插入的值
	 * @return 增后的权重
	 * */
	public double zincrby(String key, double score, String member) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		double s = jedis.zincrby(key, score, member);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}

	/**
	 * 返回指定位置的集合元素,0为第一个元素，-1为最后一个元素
	 * 
	 * @param String
	 *            key
	 * @param int start 开始位置(包含)
	 * @param int end 结束位置(包含)
	 * @return Set<String>
	 * */
	public Set<String> zrange(String key, int start, int end) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		Set<String> set = sjedis.zrange(key, start, end);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return set;
	}

	/**
	 * 返回指定权重区间的元素集合
	 * 
	 * @param String
	 *            key
	 * @param double min 上限权重
	 * @param double max 下限权重
	 * @return Set<String>
	 * */
	public Set<String> zrangeByScore(String key, double min, double max) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		Set<String> set = sjedis.zrangeByScore(key, min, max);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return set;
	}

	/**
	 * 获取指定值在集合中的位置，集合排序从低到高
	 * 
	 * @see zrevrank
	 * @param String
	 *            key
	 * @param String
	 *            member
	 * @return long 位置
	 * */
	public long zrank(String key, String member) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		long index = sjedis.zrank(key, member);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return index;
	}

	/**
	 * 获取指定值在集合中的位置，集合排序从高到低
	 * 
	 * @see zrank
	 * @param String
	 *            key
	 * @param String
	 *            member
	 * @return long 位置
	 * */
	public long zrevrank(String key, String member) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		long index = sjedis.zrevrank(key, member);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return index;
	}

	/**
	 * 从集合中删除成员
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            member
	 * @return 返回1成功
	 * */
	public long zrem(String key, String member) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long s = jedis.zrem(key, member);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}

	/**
	 * 删除
	 * 
	 * @param key
	 * @return
	 */
	public long zrem(String key) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long s = jedis.del(key);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}

	/**
	 * 删除给定位置区间的元素
	 * 
	 * @param String
	 *            key
	 * @param int start 开始区间，从0开始(包含)
	 * @param int end 结束区间,-1为最后一个元素(包含)
	 * @return 删除的数量
	 * */
	public long zremrangeByRank(String key, int start, int end) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long s = jedis.zremrangeByRank(key, start, end);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}

	/**
	 * 删除给定权重区间的元素
	 * 
	 * @param String
	 *            key
	 * @param double min 下限权重(包含)
	 * @param double max 上限权重(包含)
	 * @return 删除的数量
	 * */
	public long zremrangeByScore(String key, double min, double max) {
		Jedis jedis = JedisCommonPool.getInstance().getJedis();
		long s = jedis.zremrangeByScore(key, min, max);
		JedisCommonPool.getInstance().returnJedis(jedis);
		return s;
	}

	/**
	 * 获取给定区间的元素，原始按照权重由高到低排序
	 * 
	 * @param String
	 *            key
	 * @param int start
	 * @param int end
	 * @return Set<String>
	 * */
	public Set<String> zrevrange(String key, int start, int end) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		Set<String> set = sjedis.zrevrange(key, start, end);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		return set;
	}

	/**
	 * 获取给定值在集合中的权重
	 * 
	 * @param String
	 *            key
	 * @param memeber
	 * @return double 权重
	 * */
	public double zscore(String key, String memebr) {
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = JedisCommonPool.getInstance().getJedis();
		Double score = sjedis.zscore(key, memebr);
		JedisCommonPool.getInstance().returnJedis(sjedis);
		if (score != null)
			return score;
		return 0;
	}
}
