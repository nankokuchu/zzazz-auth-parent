package com.zzazz.system.line.controller;

import com.zzazz.common.result.R;
import com.zzazz.model.vo.line.MenuVo;
import com.zzazz.system.line.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping(value = "/line/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "全てのメニューを取得")
    @GetMapping("findMenuInfo")
    public R<List<MenuVo>> findMenuInfo() {
        List<MenuVo> menuList = menuService.findMenuInfo();
        return R.ok(menuList);
    }
}
