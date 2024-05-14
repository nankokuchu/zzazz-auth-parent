package com.zzazz.system.controller;

import com.zzazz.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @ApiOperation("ユーザーRoleを削除うするAPI")
    @DeleteMapping("remove/{id}")
    public boolean removeRole(@PathVariable Long id) {
        boolean b = sysRoleService.removeById(id);
        return b;
    }
}
