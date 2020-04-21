package com.timain.redis.cache;

import com.alibaba.fastjson.JSON;
import com.timain.redis.domain.Menu;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author yyf
 * @version 1.0
 * @date 2020/4/21 9:02
 */
@Component
@EnableAspectJAutoProxy
@Aspect
public class MenuAspectCache {
    
    //声明切面
    private final static String POINT_ALLMENU = "execution(* com.timain.redis.service.impl.MenuServiceImpl.findAllMenu(..))";
    
    private final static String ALL_MENU_KEY = "all_menu";
    
    @Autowired
    private JedisPool jedisPool;
    
    @Around(value = POINT_ALLMENU)
    public Object cacheAllMenu(ProceedingJoinPoint joinPoint) throws Throwable {
        //先从redis中取数据
        Jedis jedis = jedisPool.getResource();
        String json = jedis.get(ALL_MENU_KEY);
        if (null==json) {
            System.out.println("缓存中没有数据，去数据库查询数据");
            Object object = joinPoint.proceed();//放行方法去查询数据
            jedis.set(ALL_MENU_KEY, JSON.toJSONString(object));
            return object;
        } else {
            System.out.println("已从缓存中取出数据");
            return JSON.parseArray(json, Menu.class);
        }
    }
    
}
