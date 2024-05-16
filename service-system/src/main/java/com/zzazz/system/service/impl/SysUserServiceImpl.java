package com.zzazz.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.common.result.R;
import com.zzazz.model.system.SysUser;
import com.zzazz.model.vo.SysUserQueryVo;
import com.zzazz.system.mapper.SysUserMapper;
import com.zzazz.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public R getUserByPagination(Long page, Long size, SysUserQueryVo sysUserQueryVo) {
        Page<SysUser> pageParam = new Page<>(page, size);
        IPage<SysUser> pageModel = sysUserMapper.selectPage(pageParam, sysUserQueryVo);
        return R.ok(pageModel);
    }
}
