package com.zzazz.model.vo.system;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * ClassName: SysRoleQueryVo
 * Package: com.zzazz.model.vo
 *
 * @Author: zzazz
 * @Create: 2024/5/14 - 14:27
 * @Description: SysRoleQueryVo
 * @Version: v1.0
 */

public class SysRoleQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}