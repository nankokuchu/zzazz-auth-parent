package com.zzazz.system.line.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.model.line.Menu;
import com.zzazz.system.line.mapper.MenuMapper;
import com.zzazz.system.line.service.MenuService;
import org.springframework.stereotype.Service;

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
}
