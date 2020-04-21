package com.timain.redis.mapper;

import com.timain.redis.domain.Menu;

import java.util.List;

/**
 * @author yyf
 * @version 1.0
 * @date 2020/4/21 8:54
 */
public interface MenuMapper {
    
    List<Menu> queryAllMenu();
}
