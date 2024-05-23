package com.zzazz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzazz.model.system.SysMenu;
import com.zzazz.model.vo.system.AssignMenuVo;
import com.zzazz.model.vo.system.RouterVo;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: SysMenuService
 * Package: com.zzazz.system.service
 *
 * @Author: zzazz
 * @Create: 2024/5/17 - 11:00
 * @Description: SysMenuService
 * @Version: v1.0
 */
public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> getMenuByNodes();

    boolean removeById(Serializable id);

    List<SysMenu> findSysMenuByRoleId(Long roleId);

    void doAssign(AssignMenuVo assignMenuVo);

    // メニューの権限データ
    List<RouterVo> getUserMenuList(Long userId);

    // ボタンの権限データ
    List<String> getUserButtonList(Long userId);
}
