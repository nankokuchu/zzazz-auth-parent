package com.zzazz.system.controller;

import com.zzazz.common.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: IndexController
 * Package: com.zzazz.system.controller
 *
 * @Author: zzazz
 * @Create: 2024/5/15 - 11:21
 * @Description: ログイン機能
 * @Version: v1.0
 */
@Api(tags = "ユーザー登録API")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    /**
     * データ形式
     * {
     *      "code": 200,
     *      "data": {
     *          "token": "admin-token"
     *      }
     * }
     *
     * @return R
     */
    @PostMapping("login")
    @ApiOperation(value = "ログインAPI")
    public R login() {
        Map<String, Object> data = new HashMap<>();
        data.put("token", "admin-token");
        return R.ok(data);
    }

    /**
     * データ形式
     * {
     *      "code": 200,
     *      "data": {
     *          "roles": ["admin"],
     *          "introduction": "I am a super administrator",
     *          "avatar": "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
     *          "name": "Super Admin"
     *      }
     * }
     *
     * @return R
     */
    @ApiOperation(value = "ユーザー情報を取得API")
    @GetMapping("getInfo")
    public R getInfo() {
        Map<String, Object> data = new HashMap<>();
        data.put("roles", "[admin]");
        data.put("introduction", "I am a super administrator");
        data.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.put("name", "Super Admin ZZAZZ");
        return R.ok(data);
    }
}
