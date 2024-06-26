package com.zzazz.system.line.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzazz.model.line.Menu;
import com.zzazz.model.vo.line.MenuVo;

import java.util.List;

/**
 * ClassName: Menu
 * Package: com.zzazz.system.line.service
 *
 * @Author: zzazz
 * @Create: 2024/5/29 - 10:49
 * @Description: LINEMenuService
 * @Version: v1.0
 */
public interface MenuService extends IService<Menu> {

    // 全てのメニューを取得
    List<MenuVo> findMenuInfo();
}