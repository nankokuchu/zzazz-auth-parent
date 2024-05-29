package com.zzazz.system.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzazz.common.result.R;
import com.zzazz.common.util.MD5;
import com.zzazz.model.system.SysUser;
import com.zzazz.model.vo.system.SysUserQueryVo;
import com.zzazz.system.auth.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ClassName: SysUserController
 * Package: com.zzazz.system.controller
 *
 * @Author: zzazz
 * @Create: 2024/5/16 - 11:15
 * @Description: SysUserController
 * @Version: v1.0
 */
@Api(tags = "ユーザーを管理するAPI")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    // R->pagination

    /**
     * ページネーションで探す
     *
     * @param page           currentPage
     * @param size           表示するデータ数
     * @param sysUserQueryVo bean
     * @return R
     */
    @ApiOperation("ページネーションで探す")
    @GetMapping("{page}/{size}")
    public R<IPage<SysUser>> getUserByPagination(
            @ApiParam(name = "page", value = "currentPage", required = true)
            @PathVariable Long page,

            @ApiParam(name = "size", value = "表示するデータ数", required = true)
            @PathVariable Long size,

            @ApiParam(name = "userQueryVo", value = "検索条件")
                    SysUserQueryVo sysUserQueryVo) {
        return sysUserService.getUserByPagination(page, size, sysUserQueryVo);
    }

    // R->One
    @ApiOperation(value = "IDでユーザーを取得")
    @GetMapping("get/{id}")
    public R<SysUser> getUserById(@PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        return R.ok(user);
    }

    // C
    @ApiOperation(value = "ユーザーを保存")
    @PostMapping("/save")
    public R<Void> save(@RequestBody SysUser user) {
        String encrypt = MD5.encrypt(user.getPassword());
        user.setPassword(encrypt);
        boolean isSuccess = sysUserService.save(user);
        if (isSuccess) {
            return R.ok();
        } else {
            return R.fail();
        }
    }

    // U
    @ApiOperation(value = "ユーザーを更新")
    @PutMapping("/update")
    public R<Void> updateById(@RequestBody SysUser user) {
        sysUserService.updateById(user);
        return R.ok();
    }

    // D
    @ApiOperation(value = "ユーザーIDで削除")
    @DeleteMapping("/remove/{id}")
    public R<Void> removeUserById(@PathVariable Long id) {
        sysUserService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "CurrentUser情報を取得")
    @GetMapping("/getCurrentUser")
    public R<Map<String,Object>> getCurrentUser(){
        Map<String,Object> map = sysUserService.getCurrentUser();
        return R.ok(map);
    }
}
