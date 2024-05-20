package com.zzazz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.common.result.ResultCodeEnum;
import com.zzazz.model.system.SysMenu;
import com.zzazz.model.system.SysRoleMenu;
import com.zzazz.model.vo.AssignMenuVo;
import com.zzazz.model.vo.RouterVo;
import com.zzazz.system.exception.ZzazzException;
import com.zzazz.system.mapper.SysMenuMapper;
import com.zzazz.system.mapper.SysRoleMenuMapper;
import com.zzazz.system.service.SysMenuService;
import com.zzazz.system.util.MenuHelper;
import com.zzazz.system.util.RouterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
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

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

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

    @Override
    public List<SysMenu> findSysMenuByRoleId(Long roleId) {
        // 1,すべてのstatusが1の権限リストを取得
        LambdaQueryWrapper<SysMenu> sysMenuWrapper = new LambdaQueryWrapper<>();
        sysMenuWrapper.eq(SysMenu::getStatus, 1);
        List<SysMenu> sysMenus = sysMenuMapper.selectList(sysMenuWrapper);

        // 2,ロールIDに基づいてロールの権限を取得
        LambdaQueryWrapper<SysRoleMenu> sysRoleMenuWrapper = new LambdaQueryWrapper<>();
        sysRoleMenuWrapper.eq(SysRoleMenu::getRoleId, roleId);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(sysRoleMenuWrapper);

        // 3,そのロールに割り当てられたすべての権限IDを取得
        List<Long> roleMenus = new ArrayList<>();
        for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
            roleMenus.add(sysRoleMenu.getMenuId());
        }

        for (SysMenu sysMenu : sysMenus) {
            sysMenu.setSelect(roleMenus.contains(sysMenu.getId()));
        }
        // 4,権限リストを権限ツリーに変換
        return MenuHelper.buildTree(sysMenus);
    }

    @Override
    public void doAssign(AssignMenuVo assignMenuVo) {
        // 既に存在する関係を削除
        LambdaQueryWrapper<SysRoleMenu> sysRoleMenuWrapper = new LambdaQueryWrapper<>();
        sysRoleMenuWrapper.eq(SysRoleMenu::getRoleId, assignMenuVo.getRoleId());
        sysRoleMenuMapper.delete(sysRoleMenuWrapper);

        // 新しい関係を保存する
        List<Long> menuIdList = assignMenuVo.getMenuIdList();
        for (Long menuId : menuIdList) {
            if (menuId != null) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(assignMenuVo.getRoleId());
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }
    }

    // メニューの権限データ
    @Override
    public List<RouterVo> getUserMenuList(Long userId) {
        List<SysMenu> sysMenuList = null;
        // adminの場合全て権限を持つ
        if (1 == userId) {
            LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysMenu::getStatus, 1).orderByAsc(SysMenu::getSortValue);
            sysMenuList = sysMenuMapper.selectList(wrapper);
        } else {
            sysMenuList = sysMenuMapper.findListByUserId(userId);
        }

        // Tree形式に変更
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);

        return RouterHelper.buildRouters(sysMenuTreeList);
    }

    // ボタンの権限データ
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
