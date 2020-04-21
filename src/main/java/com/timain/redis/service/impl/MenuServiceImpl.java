package com.timain.redis.service.impl;

import com.timain.redis.domain.Menu;
import com.timain.redis.mapper.MenuMapper;
import com.timain.redis.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yyf
 * @version 1.0
 * @date 2020/4/21 9:00
 */
@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired
    private MenuMapper menuMapper;
    
    public List<Menu> findAllMenu() {
        return menuMapper.queryAllMenu();
    }
}
