package com.zzazz.system.process.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzazz.common.result.R;
import com.zzazz.model.process.Process;
import com.zzazz.model.vo.process.ProcessQueryVo;
import com.zzazz.model.vo.process.ProcessVo;
import com.zzazz.system.process.service.ProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ProcessController
 * Package: com.zzazz.system.process.controller
 *
 * @Author: zzazz
 * @Create: 2024/5/24 - 16:17
 * @Description: ProcessController
 * @Version: v1.0
 */
@Api(tags = "認証管理")
@RestController
@RequestMapping(value = "/admin/process")
public class ProcessController {
    private final ProcessService processService;

    @Autowired
    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    // R->pagination
    @ApiOperation(value = "ページネーションで探す")
    @GetMapping("{page}/{size}")
    public R index(@PathVariable Long page,
                   @PathVariable Long size,
                   ProcessQueryVo processQueryVo) {
        IPage<ProcessVo> pageModel = processService.selectPage(page, size, processQueryVo);
        return R.ok(pageModel);
    }


    // R->One

    // C

    // U

    // D
}
