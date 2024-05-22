package com.zzazz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.common.result.R;
import com.zzazz.model.system.SysRole;
import com.zzazz.model.system.SysUserRole;
import com.zzazz.model.vo.AssignRoleVo;
import com.zzazz.model.vo.SysRoleQueryVo;
import com.zzazz.system.mapper.SysRoleMapper;
import com.zzazz.system.mapper.SysUserRoleMapper;
import com.zzazz.system.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

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

    /**
     * @param userId ユーザID
     * @return R
     */
    @Override
    public Map<String, Object> getRolesByUserId(Long userId) {
        // 全てのRole
        List<SysRole> roles = sysRoleMapper.selectList(null);

        // 条件設定
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId, userId);

        // 該当する全てのロールを取得
        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(queryWrapper);

        List<Long> userRoleIds = new ArrayList<>();
        // ロールIDのみ取得する。
        for (SysUserRole userRole : userRoles) {
            Long roleId = userRole.getRoleId();
            userRoleIds.add(roleId);
        }

        Map<String, Object> rMap = new HashMap<>();
        rMap.put("allRoles", roles);
        rMap.put("userRoleIds", userRoleIds);

        return rMap;
    }

    /**
     * @param assignRoleVo javaBean
     */
    @Override
    public void doAssign(AssignRoleVo assignRoleVo) {
        // 1, ユーザーIDで、該当するユーザーの全てのロールを取得
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId, assignRoleVo.getUserId());

        //2, 元のロールIDを削除する
        sysUserRoleMapper.delete(queryWrapper);

        //3, 新しいロールIDを保存する
        List<Long> roleIdList = assignRoleVo.getRoleIdList();
        for (Long roleId : roleIdList) {
            if (roleId != null) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(assignRoleVo.getUserId());
                // 保存
                sysUserRoleMapper.insert(sysUserRole);
            }
        }

    }
}
