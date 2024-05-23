package com.zzazz.system.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.model.system.SysMenu;
import com.zzazz.system.auth.mapper.SysMenuMapper;
import com.zzazz.system.service.SysAuthMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: SysAuthMenuServiceImpl
 * Package: com.zzazz.system.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/22 - 10:59
 * @Description: SysAuthMenuServiceImpl
 * @Version: v1.0
 */
@Service
public class SysAuthMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysAuthMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<String> getUserButtonList(Long userId) {
        List<SysMenu> sysMenuList = null;
        if (userId == 1) {
            LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysMenu::getStatus, 1);
            sysMenuList = sysMenuMapper.selectList(wrapper);
        } else {
            sysMenuList = sysMenuMapper.findListByUserId(userId);
        }

        List<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if(sysMenu.getType() == 2){
                permissionList.add(sysMenu.getPerms());
            }
        }
        return permissionList;
    }
}
