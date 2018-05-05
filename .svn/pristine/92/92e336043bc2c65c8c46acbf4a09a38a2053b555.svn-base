package com.easybuy.util;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtil {

	private static Logger log=Logger.getLogger(RedisUtil.class);
	private static JedisPool jedisPool;
	static{
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		jedisPool=(JedisPool)context.getBean("jedisPool");
	}
	
	//获取jedis实例 TODO 为什么要用同步？
	public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis jedis = jedisPool.getResource();
                return jedis;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Redis缓存获取Jedis实例 出错！", e);
            return null;
        }
    }
	
	//释放jedis资源
	public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
	
	//将String键与Object对象值，存入jedis
	public static boolean setObject(String key,Object obj){
		Jedis jedis=null;
		boolean flag=false;
		try {			
			jedis = getJedis();	
			if (jedis != null) {
				//将键和值都以byte[]数组的形式存入jedis
				byte [] bytes=SerializeUtil.serialize(obj);
				jedis.set(key.getBytes(),bytes);
				flag=true;
			}
		} catch (Exception e) {
			log.error("Redis缓存设置key值 出错！", e);
		}finally {
            returnResource(jedis);
        }
		return flag;
	}

	//使用String的键，获取Object对象
    public static Object getObject(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            byte[] bytes = jedis.get(key.getBytes());
            if(EmptyUtils.isNotEmpty(bytes)) {
                return SerializeUtil.unSerialize(bytes);
            }
        } catch (Exception e) {
            log.error("getObject获取redis键值异常:key=" + key + " cause:" + e.getMessage());
        } finally {
            returnResource(jedis);
        }
        return null;
    }
    
    //通过String key判断在redis中，是否存在Object对象
    public static boolean existsObject(String key){
    	boolean flag=false;
    	Jedis jedis=null;
    	try {
			jedis = getJedis();
			if (jedis.exists(key.getBytes())) {
				flag = true;
			}
		} catch (Exception e) {
			log.error(e);
		}finally{
			returnResource(jedis);
		}
		return flag;
    }
    
    //通过String key同时删除键值，释放对象和资源
    //TODO 返回值是long类型，有什么用？
    public static Long deleteKeyObject(String key){
    	boolean flag=true;
    	Jedis jedis=null;
    	long result=0;
    	try {
			jedis = getJedis();
			result=jedis.del(key.getBytes());
			log.info("清除缓存成功");
		} catch (Exception e) {
			log.error("清除缓存失败",e);
		} finally {
			returnResource(jedis);
		}
		return result;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
	
}
