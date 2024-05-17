package com.zzazz.system.test;

import com.zzazz.model.system.SysMenu;
import com.zzazz.system.mapper.SysMenuMapper;
import com.zzazz.system.service.SysMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * ClassName: SysMenuService
 * Package: com.zzazz.system.test
 *
 * @Author: zzazz
 * @Create: 2024/5/17 - 11:43
 * @Description: SysMenuServiceTest
 * @Version: v1.0
 */
@SpringBootTest
public class SysMenuServiceTest {

    @Autowired
    private SysMenuService sysMenuService;

    @Test
    void getMenuByNodes (){
        List<SysMenu> menuByNodes = sysMenuService.getMenuByNodes();
        System.out.println("menuByNodes = " + menuByNodes);
    }

    @Test
    void removeById(){
        boolean b = sysMenuService.removeById(3L);
    }

    @Test
    void findSysMenuByRoleId(){
        List<SysMenu> sysMenus = sysMenuService.findSysMenuByRoleId(3L);
    }
}
