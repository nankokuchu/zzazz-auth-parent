package com.zzazz.system.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzazz.common.result.R;
import com.zzazz.model.system.SysLoginLog;
import com.zzazz.model.vo.system.SysLoginLogQueryVo;
import com.zzazz.system.service.SysLoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: SysLoginLogController
 * Package: com.zzazz.system.controller
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 14:40
 * @Description: SysLoginLogController
 * @Version: v1.0
 */
@Api(value = "SysLoginLog管理", tags = "SysLoginLog管理")
@RequestMapping("/admin/system/sysLoginLog")
@RestController
public class SysLoginLogController {
    private static final Logger log = LoggerFactory.getLogger(SysLoginLogController.class);

    @Autowired
    private SysLoginLogService sysLoginLogService;

    @ApiOperation("ページネーション及び条件で検索")
    @GetMapping("{page}/{size}")
    public R index(@PathVariable Long page,
                   @PathVariable Long size,
                   SysLoginLogQueryVo sysLoginLogQueryVo){
        IPage<SysLoginLog> pageModel = sysLoginLogService.selectPage(page,size,sysLoginLogQueryVo);
        return R.ok(pageModel);
    }
}
