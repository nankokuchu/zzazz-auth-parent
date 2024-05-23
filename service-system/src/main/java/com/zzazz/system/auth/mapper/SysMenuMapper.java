package com.zzazz.system.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzazz.model.system.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: SysMenuMapper
 * Package: com.zzazz.system.mapper
 *
 * @Author: zzazz
 * @Create: 2024/5/17 - 11:03
 * @Description: SysMenuMapper
 * @Version: v1.0
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> findListByUserId(Long userId);
}
