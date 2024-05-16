package com.zzazz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzazz.common.result.R;
import com.zzazz.model.system.SysUser;
import com.zzazz.model.vo.SysUserQueryVo;

/**
 * ClassName: SysUserService
 * Package: com.zzazz.system.service
 *
 * @Author: zzazz
 * @Create: 2024/5/16 - 11:16
 * @Description: SysUserService
 * @Version: v1.0
 */
public interface SysUserService extends IService<SysUser> {
    R getUserByPagination(Long page, Long size, SysUserQueryVo sysUserQueryVo);
}
