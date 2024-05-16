package com.zzazz.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.model.system.SysUser;
import com.zzazz.system.mapper.SysUserMapper;
import com.zzazz.system.service.SysUserService;
import org.springframework.stereotype.Service;

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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {
}
