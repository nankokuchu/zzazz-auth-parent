package com.zzazz.system.controller;

import com.zzazz.common.result.R;
import com.zzazz.model.system.SysRole;
import com.zzazz.model.vo.SysRoleQueryVo;
import com.zzazz.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: SysRoleController
 * Package: com.zzazz.system.controller
 *
 * @Author: zzazz
 * @Create: 2024/5/14 - 12:12
 * @Description: SysRoleController
 * @Version: v1.0
 */
@Api(tags = "ユーザーRoleを管理するAPI")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("ページネーションで探す")
    @GetMapping("{page}/{size}")
    public R findByPagination(@PathVariable Long page,
                              @PathVariable Long size,
                              SysRoleQueryVo sysRoleQueryVo) {
        return sysRoleService.findByPagination(page, size, sysRoleQueryVo);
    }

    @ApiOperation("ユーザーRoleを削除うするAPI")
    @DeleteMapping("remove/{id}")
    public R removeRole(@PathVariable Long id) {
        boolean isRemove = sysRoleService.removeById(id);
        if (isRemove) {
            return R.ok();
        } else {
            return R.fail();
        }
    }

    @ApiOperation("全てのRoleを返す")
    @GetMapping("findAll")
    public R findAll() {
        List<SysRole> sysRoles = sysRoleService.list();
        return R.ok(sysRoles);
    }
}
