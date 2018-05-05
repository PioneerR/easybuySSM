package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

	public static void main(String[] args) {
		
		//test1();
		//test2();
		//test3();
		//test4();
		test5();
	}
	
	//test1：String
	public static void test1(){
		Jedis redis=new Jedis("localhost");
		redis.set("flag", "HelloRedis");
		System.out.println(redis.get("flag"));
		//redis.ping()的值若为PONG，则ping通成功
		System.out.println(redis.ping()+"---");
		redis.disconnect();
	}
	
	//test2：List集合
	public static void test2(){
		Jedis redis=new Jedis("localhost");
		List<String> list=new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		redis.lpush("list","1");
		redis.lpush("list","2");
		redis.lpush("list","3");
		
		List<String> list1=redis.lrange("list", 0,2);
		for(String s:list1){
			System.out.println(s+"----");
		}
	}
	
	//test3:set集合
	public static void test3() {
		
        Jedis jedis = new Jedis("localhost");

        //拿到redis中的所有的key值
        Set<String> keys = jedis.keys("*"); 
        Iterator<String> it=keys.iterator() ;   
        while(it.hasNext()){   
            String key = it.next();   
            System.out.println(key);   
        }
	}
	
	//test4:JedisPoolConfig与JedisPool
	public static void test4() {
		 // 获得连接池的配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大连接数
        jedisPoolConfig.setMaxActive(30);
        // 设置最大空闲连接数
        jedisPoolConfig.setMaxIdle(10);
        // 获得连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"localhost",6379);
        // 通过连接池获得连接
        Jedis jedis = jedisPool.getResource();
        // 设置数据
        jedis.set("name","张三");
        // 获取数据
        String value = jedis.get("name");
        System.out.println(value);
        // 释放资源
        jedis.disconnect();
        // 释放连接池
        jedisPool.destroy();
	}
	
	//test5:spring配置JedisPool与JedisPoolConfig
	public static void test5() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");	
		JedisPool jedisPool=(JedisPool)context.getBean("jedisPool");
		
		Jedis jedis = jedisPool.getResource();
        // 设置数据
        jedis.set("user","王五");
        // 获取数据
        String value = jedis.get("user");
        System.out.println(value);
        // 释放资源
        jedis.disconnect();
        // 释放连接池
        jedisPool.destroy();

	}
	
	
	
	

}
