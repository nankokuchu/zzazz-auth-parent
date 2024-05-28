package com.zzazz.system.process.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzazz.common.result.R;
import com.zzazz.model.process.ProcessTemplate;
import com.zzazz.model.process.ProcessType;
import com.zzazz.model.vo.process.ProcessFormVo;
import com.zzazz.model.vo.process.ProcessQueryVo;
import com.zzazz.model.vo.process.ProcessVo;
import com.zzazz.system.process.service.ProcessService;
import com.zzazz.system.process.service.ProcessTemplateService;
import com.zzazz.system.process.service.ProcessTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
@CrossOrigin
public class ProcessController {
    private static final Logger log = LoggerFactory.getLogger(ProcessController.class);
    @Autowired
    private ProcessService processService;

    @Autowired
    private ProcessTypeService processTypeService;

    @Autowired
    private ProcessTemplateService processTemplateService;

    // @Autowired
    // public ProcessController(ProcessService processService, ProcessTypeService processTypeService) {
    //     this.processService = processService;
    //     this.processTypeService = processTypeService;
    // }

    // R->pagination
    @ApiOperation(value = "ページネーションで探す")
    @GetMapping("{page}/{size}")
    public R index(@PathVariable Long page,
                   @PathVariable Long size,
                   ProcessQueryVo processQueryVo) {
        IPage<ProcessVo> pageModel = processService.selectPage(page, size, processQueryVo);
        return R.ok(pageModel);
    }

    /**
     * 全てのプロセスタイプとプロセステンプレートを取得
     *
     * @return R<List < ProcessType>>
     */
    @ApiOperation(value = "全てのプロセスタイプとプロセステンプレートを取得")
    @GetMapping("findProcessType")
    public R<List<ProcessType>> findProcessType() {
        List<ProcessType> list = processTypeService.findProcessType();
        return R.ok(list);
    }

    /**
     * 個別プロセステンプレートのデータを取得
     *
     * @param processTemplateId プロセステンプレートID
     * @return R<ProcessTemplate>
     */
    @ApiOperation(value = "個別プロセステンプレートのデータを取得")
    @GetMapping("getProcessTemplate/{processTemplateId}")
    public R<ProcessTemplate> getProcessTemplate(@PathVariable Long processTemplateId) {
        ProcessTemplate processTemplate = processTemplateService.getById(processTemplateId);
        log.info("プロセステンプレートは:{}", processTemplate);
        return R.ok(processTemplate);
    }

    /**
     * プロセスの作成
     *
     * @param processFormVo ParameterのBean
     * @return R
     */
    @ApiOperation(value = "プロセスを作成する")
    @PostMapping("/startUp")
    public R<Void> startUp(@RequestBody ProcessFormVo processFormVo) {
        // todo log削除
        log.info("ProcessFormVoは:{}", processFormVo);
        processService.startUp(processFormVo);
        return R.ok();
    }

    /**
     * 処理待ち状態のプロセスを取得
     *
     * @return R<List < Process>>
     */
    @ApiOperation(value = "処理待ち状態のプロセスを取得")
    @GetMapping("/findPending")
    public R<List<ProcessVo>> findPending() {
        List<ProcessVo> processVoList = processService.findPending();
        assert processVoList != null;
        for (ProcessVo processVo : processVoList) {
            // todo log削除
            log.info("processVoは：{}", processVo);
        }
        return R.ok(processVoList);
    }

    /**
     * プロセスのidから詳細情報を取得
     * @param id プロセスid
     * @return R<Map<String, Object>>
     */
    @ApiOperation(value = "プロセスのidから詳細情報を取得")
    @GetMapping("/show/{id}")
    public R<Map<String, Object>> show(@PathVariable Long id){
        Map<String, Object> map = processService.show(id);
        return R.ok(map);
    }
}
