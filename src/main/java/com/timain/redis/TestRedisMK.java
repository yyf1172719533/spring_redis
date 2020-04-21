package com.timain.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * redis整合商品秒杀
 * @author yyf
 * @version 1.0
 * @date 2020/4/20 22:24
 */
public class TestRedisMK {
    
    private static final String PRODUCTID = "productId";

    public static void main(String[] args) {
        ApplicationContext context = 
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        final JedisPool jedisPool = context.getBean(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        jedis.flushAll();
        //向redis中存入商品
        for (int i = 1; i <= 100; i++) {
            jedis.lpush(PRODUCTID, "商品ID：" + i);
        }
        jedis.close();
        
        //模拟线程抢购商品
        for (int i = 1; i <= 1000; i++) {
            new Thread(new Runnable() {
                public void run() {
                    Jedis jedis1 = jedisPool.getResource();
                    String lpop = jedis1.lpop(PRODUCTID);
                    if (null==lpop) {
                        System.out.println(Thread.currentThread().getName() + "抢购失败");
                    } else {
                        System.out.println(Thread.currentThread().getName() + "抢购成功，商品ID：" + lpop);
                    }
                    jedis1.close();
                }
            },"张三" + i).start();
        }
    }
}
