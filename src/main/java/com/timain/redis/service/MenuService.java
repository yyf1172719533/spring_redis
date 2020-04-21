package com.timain.redis.service;

import com.timain.redis.domain.Menu;

import java.util.List;

/**
 * @author yyf
 * @version 1.0
 * @date 2020/4/21 9:00
 */
public interface MenuService {
    
    List<Menu> findAllMenu();
}
