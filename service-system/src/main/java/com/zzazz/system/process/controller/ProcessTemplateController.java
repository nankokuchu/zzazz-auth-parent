package com.zzazz.system.process.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.core.io.ResourceUtil;
import com.zzazz.common.result.R;
import com.zzazz.model.process.ProcessTemplate;
import com.zzazz.system.process.service.ProcessTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @ApiOperation(value = "公開")
    @GetMapping("/publish/{id}")
    public R<Void> publish(@PathVariable Long id) {
        processTemplateService.publish(id);
        return R.ok();
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

    // zipファイルのアップロード
    @ApiOperation(value = "上传流程定义")
    @PostMapping("/uploadProcessDefinition")
    public R<Object> uploadProcessDefinition(MultipartFile file) throws FileNotFoundException {
        // D:\Program_Files\Java_Project\zzazz-auth-parent\service-system\target\classes
        String path = new File(ResourceUtils.getURL("classpath:").getPath())
                .getAbsolutePath();

        // アップロードしたファイルを保存するフォルダを設定
        File tempFile = new File(path + "/processes/");
        if (!tempFile.exists()) {
            tempFile.mkdir(); //フォルダを作成
        }

        String fileName = file.getOriginalFilename();
        File imageFile = new File(path + "/processes/" + fileName);
        // アップしたファイルを保存
        try {
            file.transferTo(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            return R.fail("ファイルアップロード失敗");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("processDefinitionPath", "processes/" + fileName);
        assert fileName != null;
        map.put("processDefinitionKey", fileName.substring(0, fileName.lastIndexOf(".")));
        return R.ok(map);
    }
}
