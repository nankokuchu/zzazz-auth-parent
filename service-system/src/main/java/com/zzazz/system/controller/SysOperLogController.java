package com.zzazz.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzazz.common.result.R;
import com.zzazz.model.system.SysOperLog;
import com.zzazz.model.vo.SysOperLogQueryVo;
import com.zzazz.system.service.OperLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: SysOperLogController
 * Package: com.zzazz.system.controller
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 15:20
 * @Description: SysOperLogController
 * @Version: v1.0
 */

@Api(value = "SysOperLog管理", tags = "SysOperLog管理")
@RestController
@RequestMapping(value="/admin/system/sysOperLog")
public class SysOperLogController {
    @Autowired
    private OperLogService operLogService;

    @ApiOperation("ページネーション及び条件で検索")
    @GetMapping("{page}/{size}")
    public R index(@PathVariable Long page,
                   @PathVariable Long size,
                   SysOperLogQueryVo sysOperLogQueryVo){
        IPage<SysOperLog> pageModel = operLogService.selectPage(page,size,sysOperLogQueryVo);
        return R.ok(pageModel);
    }
}
