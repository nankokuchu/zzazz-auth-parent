package com.zzazz.system.process.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzazz.common.result.R;
import com.zzazz.model.process.ProcessTemplate;
import com.zzazz.system.process.service.ProcessTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ProcessTemplateController
 * Package: com.zzazz.system.process.controller
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 13:34
 * @Description: ProcessTemplateController
 * @Version: v1.0
 */
@Api(value = "承認テンプレート管理", tags = "承認テンプレート管理")
@RequestMapping(value = "/admin/process/processTemplate")
@RestController
public class ProcessTemplateController {

    private final ProcessTemplateService processTemplateService;

    @Autowired
    public ProcessTemplateController(ProcessTemplateService processTemplateService) {
        this.processTemplateService = processTemplateService;
    }

    // R->pagination
    @ApiOperation("ページネーションで取得")
    @GetMapping("{page}/{size}")
    public R<IPage<ProcessTemplate>> index(@PathVariable Long page,
                                           @PathVariable Long size) {

        IPage<ProcessTemplate> pageModel = processTemplateService.selectPage(page, size);
        return R.ok(pageModel);
    }

    // R->One
    @ApiOperation("idでデータを取得")
    @RequestMapping("/get/{id}")
    public R<ProcessTemplate> get(@PathVariable Long id) {
        ProcessTemplate processTemplate = processTemplateService.getById(id);
        return R.ok(processTemplate);
    }

    // C
    @ApiOperation("Insert")
    @PostMapping("/save")
    public R<Void> save(@RequestBody ProcessTemplate processTemplate) {
        return processTemplateService.save(processTemplate) ? R.ok() : R.fail();
    }

    // U
    @ApiOperation("Update")
    @PutMapping("/update")
    public R<Void> update(@RequestBody ProcessTemplate processTemplate) {
        return processTemplateService.updateById(processTemplate) ? R.ok() : R.fail();
    }

    // D
    @ApiOperation("DeleteById")
    @DeleteMapping("/remove/{id}")
    public R<Void> remove(@PathVariable Long id) {
        return processTemplateService.removeById(id) ? R.ok() : R.fail();
    }
}
