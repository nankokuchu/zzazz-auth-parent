package com.zzazz.system.line.controller;

import com.zzazz.common.result.R;
import com.zzazz.model.line.Menu;
import com.zzazz.model.vo.line.MenuVo;
import com.zzazz.system.line.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: MenuController
 * Package: com.zzazz.system.line.controller
 *
 * @Author: zzazz
 * @Create: 2024/5/29 - 10:53
 * @Description: LINEMenuController
 * @Version: v1.0
 */
@Api(value = "LINEメニュー管理", tags = "LINEメニュー管理")
@RestController
@RequestMapping(value = "/admin/line/menu")
public class MenuController {
    private static final Logger log = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "全てのメニューを取得")
    @GetMapping("/findMenuInfo")
    public R<List<MenuVo>> findMenuInfo() {
        List<MenuVo> menuList = menuService.findMenuInfo();
        return R.ok(menuList);
    }

    @ApiOperation(value = "idから取得")
    @GetMapping("/getById/{id}")
    public R<Menu> getById(@PathVariable Long id) {
        Menu menu = menuService.getById(id);
        return R.ok(menu);
    }

    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public R<Void> save(@RequestBody Menu menu) {
        // todo log
        log.info("メニュー:::{}", menu);
        return menuService.save(menu) ? R.ok() : R.fail();
    }

    @ApiOperation(value = "更新")
    @PutMapping("/updateById")
    public R<Void> updateById(@RequestBody Menu menu) {
        // todo log
        log.info("メニュー:::{}", menu);
        return menuService.updateById(menu) ? R.ok() : R.fail();
    }

    @ApiOperation(value = "idから削除")
    @DeleteMapping("/removeById/{id}")
    public R<Void> removeById(@PathVariable Long id){
        return menuService.removeById(id) ? R.ok() : R.fail();
    }
}
