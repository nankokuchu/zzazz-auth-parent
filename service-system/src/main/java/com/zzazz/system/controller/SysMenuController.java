package com.zzazz.system.controller;

import com.zzazz.common.result.R;
import com.zzazz.model.system.SysMenu;
import com.zzazz.model.vo.system.AssignMenuVo;
import com.zzazz.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: SysMenuController
 * Package: com.zzazz.system.controller
 *
 * @Author: zzazz
 * @Create: 2024/5/17 - 10:59
 * @Description: メニューController
 * @Version: v1.0
 */
@Api(tags = "メニューを管理するAPI")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    // R->List
    @ApiOperation(value = "全てのメニューをTREEの形式で取得")
    @GetMapping("/getMenu")
    public R<List<SysMenu>> getMenu() {
        List<SysMenu> list = sysMenuService.getMenuByNodes();
        return R.ok(list);
    }

    // C
    @ApiOperation(value = "メニューを保存")
    @PostMapping("/saveMenu")
    public R<Void> saveMenu(@RequestBody SysMenu sysMenu) {
        boolean save = sysMenuService.save(sysMenu);
        if (save) {
            return R.ok();
        }
        return R.fail();
    }

    // U
    @ApiOperation(value = "メニューを更新")
    @PutMapping("/updateMenu")
    public R<Void> updateMenu(@RequestBody SysMenu sysMenu) {
        boolean isUpdate = sysMenuService.updateById(sysMenu);
        if (isUpdate) {
            return R.ok();
        }
        return R.fail();
    }

    // D
    @ApiOperation(value = "メニューを削除")
    @DeleteMapping("/removeMenu/{id}")
    public R<Void> removeMenu(@PathVariable Long id) {
        boolean isRemove = sysMenuService.removeById(id);
        if (isRemove) {
            return R.ok();
        }
        return R.fail();
    }

    // ロールからメニューを取得
    @ApiOperation(value = "ロールからメニューを取得")
    @GetMapping("toAssign/{roleId}")
    public R<List<SysMenu>> toAssign(@PathVariable Long roleId) {
        List<SysMenu> list = sysMenuService.findSysMenuByRoleId(roleId);
        return R.ok(list);
    }

    @ApiOperation(value = "メニューにロールを付与する")
    @PostMapping("/doAssign")
    public R<Void> doAssign(@RequestBody AssignMenuVo assignMenuVo) {
        sysMenuService.doAssign(assignMenuVo);
        return R.ok();
    }
}
