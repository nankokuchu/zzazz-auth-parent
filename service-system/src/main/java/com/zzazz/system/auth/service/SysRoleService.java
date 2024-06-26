package com.zzazz.system.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzazz.common.result.R;
import com.zzazz.model.system.SysRole;
import com.zzazz.model.vo.system.AssignRoleVo;
import com.zzazz.model.vo.system.SysRoleQueryVo;

import java.util.Map;

/**
 * ClassName: SysRoleService
 * Package: com.zzazz.system.service
 *
 * @Author: zzazz
 * @Create: 2024/5/14 - 12:14
 * @Description: SysRoleService
 * @Version: v1.0
 */
public interface SysRoleService extends IService<SysRole> {
    R findByPagination(Long page, Long size, SysRoleQueryVo sysRoleQueryVo);

    Map<String, Object> getRolesByUserId(Long userId);

    void doAssign(AssignRoleVo assignRoleVo);
}
