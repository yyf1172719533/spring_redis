package com.timain.redis;

import com.timain.redis.domain.Menu;
import com.timain.redis.service.MenuService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @author yyf
 * @version 1.0
 * @date 2020/4/20 22:24
 */
public class SpringRedisTest {

    public static void main(String[] args) {
        ApplicationContext context = 
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        MenuService menuService = context.getBean(MenuService.class);
        List<Menu> menuList = menuService.findAllMenu();
        for (Menu menu : menuList) {
            System.out.println(menu);
        }
    }
}
