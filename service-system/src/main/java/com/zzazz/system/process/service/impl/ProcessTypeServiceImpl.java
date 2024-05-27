package com.zzazz.system.process.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.model.process.ProcessTemplate;
import com.zzazz.model.process.ProcessType;
import com.zzazz.system.process.mapper.ProcessTypeMapper;
import com.zzazz.system.process.service.ProcessTemplateService;
import com.zzazz.system.process.service.ProcessTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ProcessType
 * Package: com.zzazz.system.process.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 13:42
 * @Description:
 * @Version: v1.0
 */
@Service
public class ProcessTypeServiceImpl extends ServiceImpl<ProcessTypeMapper, ProcessType> implements ProcessTypeService {
    private static final Logger log = LoggerFactory.getLogger(ProcessTypeServiceImpl.class);
    @Autowired
    private ProcessTypeMapper processTypeMapper;

    @Autowired
    private ProcessTemplateService processTemplateService;

    // @Autowired
    // public ProcessTypeServiceImpl(ProcessTypeMapper processTypeMapper, ProcessTemplateService processTemplateService) {
    //     this.processTypeMapper = processTypeMapper;
    //     this.processTemplateService = processTemplateService;
    // }


    @Override
    public IPage<ProcessType> getPage(Long page, Long size) {
        Page<ProcessType> pagePram = new Page<>(page, size);
        return processTypeMapper.selectPage(pagePram, null);
    }

    @Override
    public List<ProcessType> findProcessType() {
        // TODO log削除
        // 1,全てタイプを探す
        List<ProcessType> processTypes = baseMapper.selectList(null);
        for (ProcessType processType : processTypes) {
            Long processTypeId = processType.getId();
            LambdaQueryWrapper<ProcessTemplate> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ProcessTemplate::getProcessTypeId,processTypeId);
            List<ProcessTemplate> processTemplates = processTemplateService.list(wrapper);
            processType.setProcessTemplateList(processTemplates);
            log.info("プロセスタイプは: {}", processType);
        }
        return processTypes;
    }
}
