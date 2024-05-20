package com.zzazz.system.service.impl;

import com.zzazz.common.result.ResultCodeEnum;
import com.zzazz.custom.CustomUser;
import com.zzazz.model.system.SysUser;
import com.zzazz.system.exception.ZzazzException;
import com.zzazz.system.service.SysUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

/**
 * ClassName: UserDetailsServiceImpl
 * Package: com.zzazz.system.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/20 - 16:53
 * @Description: UserDetailsServiceImpl
 * @Version: v1.0
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private SysUserService sysUserService;

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

        // TODO 要確認
        logger.info(sysUser.toString());
        // カスタマイズした　CustomUser　extends User extends UserDetails であるため、CustomUserを返す。
        return new CustomUser(sysUser, Collections.emptyList());
    }
}
