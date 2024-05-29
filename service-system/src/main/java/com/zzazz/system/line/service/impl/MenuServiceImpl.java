package com.zzazz.system.line.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.model.line.Menu;
import com.zzazz.model.vo.line.MenuVo;
import com.zzazz.system.line.mapper.MenuMapper;
import com.zzazz.system.line.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ClassName: Menu
 * Package: com.zzazz.system.line.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/29 - 10:50
 * @Description: LINEMenuServiceImpl
 * @Version: v1.0
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    // 全てのメニューを取得
    @Override
    public List<MenuVo> findMenuInfo() {
        List<MenuVo> data = new ArrayList<>();

        // 1,全てのメニューリストを取得
        List<Menu> topMenus = baseMapper.selectList(null);

        // 2,topメニューを探す
        List<Menu> topMenuList = topMenus.stream()
                .filter(menu -> menu.getParentId() == 0)
                .collect(Collectors.toList());

        // 3,MenuVo形式のtopめにゅーを取得
        for (Menu topMenu : topMenuList) {
            MenuVo topMenuVo = new MenuVo();
            // topMenu -> menuVo
            BeanUtils.copyProperties(topMenu, topMenuVo);

            // 4, 下層メニューを取得　menuId == parentId
            List<Menu> secondMenuList = topMenus.stream()
                    .filter(menu -> Objects.equals(menu.getParentId(), topMenu.getId()))
                    .collect(Collectors.toList());

            // 5, 下層メニューをtopメニューに入れる
            List<MenuVo> childrenMenu = new ArrayList<>();
            for (Menu secondMenu : secondMenuList) {
                MenuVo secondMenuVo = new MenuVo();
                // topMenu -> menuVo
                BeanUtils.copyProperties(secondMenu, secondMenuVo);
                childrenMenu.add(secondMenuVo);
            }
            topMenuVo.setChildren(childrenMenu);
            data.add(topMenuVo);
        }
        return data;
    }
}
