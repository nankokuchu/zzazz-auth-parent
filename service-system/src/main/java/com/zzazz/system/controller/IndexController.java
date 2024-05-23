package com.zzazz.system.controller;

import com.zzazz.common.result.R;
import com.zzazz.common.result.ResultCodeEnum;
import com.zzazz.common.util.JwtHelper;
import com.zzazz.common.util.MD5;
import com.zzazz.model.system.SysUser;
import com.zzazz.model.vo.system.LoginVo;
import com.zzazz.system.exception.ZzazzException;
import com.zzazz.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private SysUserService sysUserService;

    /**
     * データ形式
     * {
     * "code": 200,
     * "data": {
     * "token": "admin-token"
     * }
     * }
     *
     * @return R
     */
    @PostMapping("login")
    @ApiOperation(value = "ログインAPI")
    public R<Map<String, Object>> login(@RequestBody LoginVo loginVo) {
        // 1,usernameでDBから探す
        SysUser sysUser = sysUserService.getUserInfoByUsername(loginVo.getUsername());
        // 2,usernameで探した際にnullの場合
        if (sysUser == null) {
            throw new ZzazzException(ResultCodeEnum.ACCOUNT_ERROR);
        }
        // 3,passwordが一致するかをチェック
        String password = loginVo.getPassword();
        String md5Password = MD5.encrypt(password);
        if (!sysUser.getPassword().equals(md5Password)) {
            throw new ZzazzException(ResultCodeEnum.PASSWORD_ERROR);
        }
        // 4,ユーザーのステーターすを確認
        if (sysUser.getStatus() == 0) {
            throw new ZzazzException(ResultCodeEnum.ACCOUNT_STOP);
        }
        // 5,ユーザーIDとusernameでtokenを生成して、返す
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        return R.ok(data);
    }

    /**
     * データ形式
     * {
     * "code": 200,
     * "data": {
     * "roles": ["admin"],
     * "introduction": "I am a super administrator",
     * "avatar": "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
     * "name": "Super Admin"
     * }
     * }
     *
     * @return R
     */
    @ApiOperation(value = "ユーザー情報を取得API")
    @GetMapping("getInfo")
    public R<Map<String, Object>> getInfo(HttpServletRequest request) {
        // tokenを取得
        String token = request.getHeader("token");

        // tokenからidとusernameを取得
        String username = JwtHelper.getUsername(token);

        Map<String, Object> data = sysUserService.getUserAllInfoByUsername(username);
        // Map<String, Object> data = new HashMap<>();
        // data.put("roles", "[admin]");
        // data.put("introduction", "I am a super administrator");
        // data.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        // data.put("name", "Super Admin ZZAZZ");
        return R.ok(data);
    }
}
