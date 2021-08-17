//package com.yule.redis.dao;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.util.Assert;
//
//import com.yule.redis.common.AbstractBaseRedisDao;
//import com.yule.redis.vo.Province;
//
//public class ProvinceDao extends AbstractBaseRedisDao<String, Province>{
//	
//	/**  
//     * 新增 
//     *<br>------------------------------<br> 
//     * @param Province 
//     * @return 
//     */  
//    public boolean add(final Province province) {  
//        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
//            public Boolean doInRedis(RedisConnection connection)  
//                    throws DataAccessException {  
//                RedisSerializer<String> serializer = getRedisSerializer();  
//                byte[] key  = serializer.serialize(province.getId());  
//                byte[] name = serializer.serialize(province.getName());  
//                return connection.setNX(key, name);  
//            }  
//        });  
//        return result;  
//    }  
//      
//    /** 
//     * 批量新增 使用pipeline方式   
//     *<br>------------------------------<br> 
//     *@param list 
//     *@return 
//     */  
//    public boolean add(final List<Province> list) {  
//        Assert.notEmpty(list);  
//        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
//            public Boolean doInRedis(RedisConnection connection)  
//                    throws DataAccessException {  
//                RedisSerializer<String> serializer = getRedisSerializer();  
//                for (Province Province : list) {  
//                    byte[] key  = serializer.serialize(Province.getId());  
//                    byte[] name = serializer.serialize(Province.getName());  
//                    connection.setNX(key, name);  
//                }  
//                return true;  
//            }  
//        }, false, true);  
//        return result;  
//    }  
//      
//    /**  
//     * 删除 
//     * <br>------------------------------<br> 
//     * @param key 
//     */  
//    public void delete(String key) {  
//        List<String> list = new ArrayList<String>();  
//        list.add(key);  
//        delete(list);  
//    }  
//  
//    /** 
//     * 删除多个 
//     * <br>------------------------------<br> 
//     * @param keys 
//     */  
//    public void delete(List<String> keys) {  
//        redisTemplate.delete(keys);  
//    }  
//  
//    /** 
//     * 修改  
//     * <br>------------------------------<br> 
//     * @param Province 
//     * @return  
//     */  
//    public boolean update(final Province Province) {  
//        String key = Province.getId();  
//        if (get(key) == null) {  
//            throw new NullPointerException("数据行不存在, key = " + key);  
//        }  
//        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
//            public Boolean doInRedis(RedisConnection connection)  
//                    throws DataAccessException {  
//                RedisSerializer<String> serializer = getRedisSerializer();  
//                byte[] key  = serializer.serialize(Province.getId());  
//                byte[] name = serializer.serialize(Province.getName());  
//                connection.set(key, name);  
//                return true;  
//            }  
//        });  
//        return result;  
//    }  
//  
//    /**  
//     * 通过key获取 
//     * <br>------------------------------<br> 
//     * @param keyId 
//     * @return 
//     */  
//    public Province get(final String keyId) {  
//        Province result = redisTemplate.execute(new RedisCallback<Province>() {  
//            public Province doInRedis(RedisConnection connection)  
//                    throws DataAccessException {  
//                RedisSerializer<String> serializer = getRedisSerializer();  
//                byte[] key = serializer.serialize(keyId);  
//                byte[] value = connection.get(key);  
//                if (value == null) {  
//                    return null;  
//                }  
//                String name = serializer.deserialize(value);  
//                return new Province(keyId, name);  
//            }  
//        });  
//        return result;  
//    }  
//	
//}
