package com.zzazz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzazz.model.system.SysMenu;

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
public interface SysAuthMenuService extends IService<SysMenu> {
    // ボタンの権限データ
    List<String> getUserButtonList(Long userId);
}
