package com.zzazz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.common.result.R;
import com.zzazz.model.system.SysUser;
import com.zzazz.model.vo.RouterVo;
import com.zzazz.model.vo.SysUserQueryVo;
import com.zzazz.system.mapper.SysUserMapper;
import com.zzazz.system.service.SysMenuService;
import com.zzazz.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SysUserServiceImpl
 * Package: com.zzazz.system.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/16 - 11:16
 * @Description: SysUserServiceImple
 * @Version: v1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public R getUserByPagination(Long page, Long size, SysUserQueryVo sysUserQueryVo) {
        Page<SysUser> pageParam = new Page<>(page, size);
        IPage<SysUser> pageModel = sysUserMapper.selectPage(pageParam, sysUserQueryVo);
        return R.ok(pageModel);
    }

    @Override
    public SysUser getUserInfoByUsername(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        return sysUserMapper.selectOne(wrapper);
    }

    @Override
    public Map<String, Object> getUserAllInfoByUsername(String username) {
        // ユーザー情報を取得
        SysUser sysUser = this.getUserInfoByUsername(username);
        Long userId = sysUser.getId();

        // メニューの権限データ
        List<RouterVo> routerVoList = sysMenuService.getUserMenuList(userId);

        // ボタンの権限データ
        List<String> permList = sysMenuService.getUserButtonList(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("name", username);
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles", "[admin]");
        // メニューの権限データ
        result.put("routers", routerVoList);
        //　ボタンの権限データ
        result.put("buttons", permList);
        return result;
    }
}
