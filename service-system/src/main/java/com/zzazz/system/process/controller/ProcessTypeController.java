package com.zzazz.system.process.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzazz.common.result.R;
import com.zzazz.model.process.ProcessType;
import com.zzazz.system.annotation.Log;
import com.zzazz.system.process.service.ProcessTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ProcessTypeController
 * Package: com.zzazz.system.process.controller
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 13:34
 * @Description: ProcessTypeController
 * @Version: v1.0
 */
@Api(value = "認証タイプ", tags = "認証タイプ")
@RestController
@RequestMapping(value = "/admin/process/processType")
public class ProcessTypeController {

    private final ProcessTypeService processTypeService;

    @Autowired
    public ProcessTypeController(ProcessTypeService processTypeService) {
        this.processTypeService = processTypeService;
    }

    // R->pagination
    @Log(title = "index")
    @ApiOperation("ページネーションで取得")
    @GetMapping("{page}/{size}")
    public R<IPage<ProcessType>> index(@PathVariable Long page,
                                       @PathVariable Long size) {
        IPage<ProcessType> pageModel = processTypeService.getPage(page, size);
        return R.ok(pageModel);
    }

    // R->One
    @ApiOperation("idでデータを取得")
    @RequestMapping("/get/{id}")
    public R<ProcessType> get(@PathVariable Long id) {
        ProcessType processType = processTypeService.getById(id);
        return R.ok(processType);
    }

    // C
    @ApiOperation("Insert")
    @PostMapping("/save")
    public R<Void> save(@RequestBody ProcessType processType) {
        return processTypeService.save(processType) ? R.ok() : R.fail();
    }

    // U
    @ApiOperation("Update")
    @PutMapping("/update")
    public R<Void> update(@RequestBody ProcessType processType) {
        return processTypeService.updateById(processType) ? R.ok() : R.fail();
    }

    // D
    @ApiOperation("DeleteById")
    @DeleteMapping("/remove/{id}")
    public R<Void> remove(@PathVariable Long id) {
        return processTypeService.removeById(id) ? R.ok() : R.fail();
    }
}
