package com.tv189.interAction.cache;

import java.util.HashMap;
import java.util.Map;

import com.tv189.interAction.helper.CacheConfigHelper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisAPI {
    private static JedisPool pool = null;
    public static JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxActive(10000);
            config.setMaxIdle(300);
            config.setMaxWait(1000);
            config.setTestOnBorrow(true);
            pool = new JedisPool(config, CacheConfigHelper.getCacheIP(),
    				CacheConfigHelper.getCachePort());
        }
        return pool;
    }
    
    
    public static void returnResource(JedisPool pool, Jedis redis) {
        if (redis != null) {
            pool.returnResource(redis);
        }
    }
    
    public static String get(String key){
        String value = null;
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }
        
        return value;
    }
    
    public static void setex(String key,Integer seconds,String value){
        JedisPool pool = null;
        Jedis jedis = null;
        try {
        	pool = getPool();
            jedis = pool.getResource();
            jedis.setex(key, seconds, value);
		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
            e.printStackTrace();
		} finally {
            returnResource(pool, jedis);
        }
    }
    
    public static Map<String, String> hgetall(String key){
    	Map<String,String> value = new HashMap<String, String>();
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.hgetAll(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }
        
        return value;
    }
    
    public static String hget(String key,String field){
    	String value = null;
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.hget(key, field);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }
        
        return value;
    }
    
    public static void hsetex(String key,String field,String value, Integer timeOut){
    	JedisPool pool = null;
        Jedis jedis = null;
        try {
        	pool = getPool();
            jedis = pool.getResource();
            jedis.hset(key, field, value);
            jedis.expire(key, timeOut);
		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
            e.printStackTrace();
		} finally {
            returnResource(pool, jedis);
        }
    }
}
