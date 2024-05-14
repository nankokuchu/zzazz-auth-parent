package com.zzazz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.common.result.R;
import com.zzazz.model.system.SysRole;
import com.zzazz.model.vo.SysRoleQueryVo;
import com.zzazz.system.mapper.SysRoleMapper;
import com.zzazz.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: SysRoleServiceImple
 * Package: com.zzazz.system.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/14 - 12:14
 * @Description: SysRoleServiceImple
 * @Version: v1.0
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public R findByPagination(Long page, Long size, SysRoleQueryVo sysRoleQueryVo) {

        // 条件を設定
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(sysRoleQueryVo.getRoleName()), SysRole::getRoleName, sysRoleQueryVo.getRoleName());

        // ページネーション
        IPage<SysRole> sysRolePage = new Page<>(page, size);

        IPage<SysRole> iPage = sysRoleMapper.selectPage(sysRolePage, sysRoleQueryVo);

        return R.ok(iPage);
    }
}
