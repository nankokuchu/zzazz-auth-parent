package com.zzazz.system.custom;

import com.zzazz.model.system.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * ClassName: CustomUser
 * Package: com.zzazz.custom
 *
 * @Author: zzazz
 * @Create: 2024/5/20 - 16:49
 * @Description: CustomUser; SpringSecurityのUserDetailsの拡張
 * @Version: v1.0
 */
public class CustomUser extends User {
    private SysUser sysUser;

    public CustomUser(SysUser sysUser, Collection<? extends GrantedAuthority> authorities) {
        super(sysUser.getUsername(), sysUser.getPassword(), authorities);
        this.sysUser = sysUser;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
