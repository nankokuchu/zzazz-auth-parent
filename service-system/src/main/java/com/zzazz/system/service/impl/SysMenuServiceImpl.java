package com.zzazz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.common.result.ResultCodeEnum;
import com.zzazz.model.system.SysMenu;
import com.zzazz.system.exception.ZzazzException;
import com.zzazz.system.mapper.SysMenuMapper;
import com.zzazz.system.service.SysMenuService;
import com.zzazz.system.util.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: SysMenuServiceImpl
 * Package: com.zzazz.system.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/17 - 11:01
 * @Description:
 * @Version: v1.0
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getMenuByNodes() {

        // 1,全てのメニューを探す。
        List<SysMenu> sysMenuList = this.list();
        // 2,sysMenuListがnullの場合直接nullをreturnする。
        if (CollectionUtils.isEmpty(sysMenuList)) return null;

        return MenuHelper.buildTree(sysMenuList);
    }

    @Override
    public boolean removeById(Serializable id) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getParentId, id);
        long count = this.count(wrapper);
        if (count > 0) {
            throw new ZzazzException(ResultCodeEnum.NODE_ERROR);
        }
        int row = sysMenuMapper.deleteById(id);
        return row > 0;
    }
}
