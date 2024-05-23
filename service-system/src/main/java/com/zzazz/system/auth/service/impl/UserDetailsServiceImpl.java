package com.zzazz.system.auth.service.impl;

import com.zzazz.common.result.ResultCodeEnum;
import com.zzazz.system.auth.service.SysMenuService;
import com.zzazz.system.custom.CustomUser;
import com.zzazz.model.system.SysUser;
import com.zzazz.system.exception.ZzazzException;
import com.zzazz.system.auth.service.SysUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: UserDetailsServiceImpl
 * Package: com.zzazz.system.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/20 - 16:53
 * @Description: UserDetailsServiceImpl
 * @Version: v1.0
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // usernameでユーザーを取得
        SysUser sysUser = sysUserService.getUserInfoByUsername(username);
        // ユーザーを判断する
        if (sysUser == null) {
            throw new ZzazzException(ResultCodeEnum.LOGIN_AUTH);
        }
        // ユーザーを判断する
        if (sysUser.getStatus() == 0) {
            throw new ZzazzException(ResultCodeEnum.ACCOUNT_STOP);
        }

        // userIdからボタンの権限を取得する
        List<String> userButtonList = sysMenuService.getUserButtonList(sysUser.getId());

        // List<SimpleGrantedAuthority> authorities = new ArrayList<>(); SpringSecurityの形式
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String s : userButtonList) {
            authorities.add(new SimpleGrantedAuthority(s.trim()));
        }

        // カスタマイズした　CustomUser　extends User extends UserDetails であるため、CustomUserを返す。
        return new CustomUser(sysUser, authorities);
    }
}
