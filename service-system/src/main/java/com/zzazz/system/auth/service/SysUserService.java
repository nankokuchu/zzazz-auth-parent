package com.zzazz.system.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzazz.common.result.R;
import com.zzazz.model.system.SysUser;
import com.zzazz.model.vo.system.SysUserQueryVo;

import java.util.Map;

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

    SysUser getUserInfoByUsername(String username);

    Map<String, Object> getUserAllInfoByUsername(String username);

    // CurrentUser情報を取得
    Map<String, Object> getCurrentUser();
}
