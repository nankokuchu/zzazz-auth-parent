package com.zzazz.system.controller;

import com.zzazz.common.result.R;
import com.zzazz.model.system.SysRole;
import com.zzazz.model.vo.AssignRoleVo;
import com.zzazz.model.vo.SysRoleQueryVo;
import com.zzazz.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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


    /**
     * C
     *
     * @param sysRole SysRole
     * @return R
     */
    @ApiOperation("Roleを追加するAPI")
    @PostMapping("save")
    public R addRole(@RequestBody SysRole sysRole) {
        boolean isSave = sysRoleService.save(sysRole);
        if (isSave) {
            return R.ok();
        } else {
            return R.fail();
        }
    }

    /**
     * R
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "idでRoleを取得")
    @GetMapping("/get/{id}")
    public R get(@PathVariable Long id) {
        SysRole role = sysRoleService.getById(id);
        return R.ok(role);
    }

    @ApiOperation("全てのRoleを取得")
    @GetMapping("findAll")
    public R findAll() {
        List<SysRole> list = sysRoleService.list();
        return R.ok(list);
    }

    /**
     * R
     *
     * @param page
     * @param size
     * @param sysRoleQueryVo
     * @return R
     */
    @ApiOperation("ページネーションで探す")
    @GetMapping("{page}/{size}")
    public R findByPagination(@PathVariable Long page,
                              @PathVariable Long size,
                              SysRoleQueryVo sysRoleQueryVo) {
        return sysRoleService.findByPagination(page, size, sysRoleQueryVo);
    }


    /**
     * U
     *
     * @param role
     * @return R
     */
    @ApiOperation(value = "Roleをupdate")
    @PutMapping("/update")
    public R updateById(@RequestBody SysRole role) {
        sysRoleService.updateById(role);
        return R.ok();
    }

    /**
     * D
     *
     * @param id
     * @return
     */
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


    /**
     * D
     *
     * @param idList
     * @return
     */
    @ApiOperation(value = "配列で一括削除")
    @DeleteMapping("/batchRemove")
    public R batchRemove(@RequestBody List<Long> idList) {
        sysRoleService.removeByIds(idList);
        return R.ok();
    }

    /**
     * ユーザーIDで、該当するユーザーの全てのロールを取得
     *
     * @param userId ユーザーID
     * @return R
     */
    @ApiOperation(value = "ユーザーIDで、該当するユーザーの全てのロールを取得")
    @GetMapping("/toAssign/{userId}")
    public R toAssign(@PathVariable Long userId) {
        Map<String, Object> roleMap = sysRoleService.getRolesByUserId(userId);
        return R.ok(roleMap);
    }

    @ApiOperation(value = "ユーザにロールを割り当てる")
    @PostMapping("/doAssign")
    public R doAssign(@RequestBody AssignRoleVo assignRoleVo) {
        sysRoleService.doAssign(assignRoleVo);
        return R.ok();
    }

}
