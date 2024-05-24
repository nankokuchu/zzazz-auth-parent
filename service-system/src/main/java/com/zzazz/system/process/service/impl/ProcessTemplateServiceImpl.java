package com.zzazz.system.process.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.model.process.ProcessTemplate;
import com.zzazz.model.process.ProcessType;
import com.zzazz.system.process.mapper.ProcessTemplateMapper;
import com.zzazz.system.process.service.ProcessTemplateService;
import com.zzazz.system.process.service.ProcessTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ProcessTemplate
 * Package: com.zzazz.system.process.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 13:37
 * @Description: ProcessTemplate
 * @Version: v1.0
 */
@Service
public class ProcessTemplateServiceImpl extends ServiceImpl<ProcessTemplateMapper, ProcessTemplate> implements ProcessTemplateService {
    private static final Logger log = LoggerFactory.getLogger(ProcessTemplateServiceImpl.class);

    private final ProcessTemplateMapper processTemplateMapper;

    private final ProcessTypeService processTypeService;

    @Autowired
    public ProcessTemplateServiceImpl(ProcessTemplateMapper processTemplateMapper, ProcessTypeService processTypeService) {
        this.processTemplateMapper = processTemplateMapper;
        this.processTypeService = processTypeService;
    }

    @Override
    public IPage<ProcessTemplate> selectPage(Long page, Long size) {
        Page<ProcessTemplate> pageParam = new Page<>(page, size);
        IPage<ProcessTemplate> processTemplatePage = processTemplateMapper.selectPage(pageParam, null);
        List<ProcessTemplate> records = processTemplatePage.getRecords();
        for (ProcessTemplate record : records) {
            Long processTypeId = record.getProcessTypeId();
            LambdaQueryWrapper<ProcessType> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ProcessType::getId, processTypeId);
            ProcessType processType = processTypeService.getOne(wrapper);
            if (processType == null) {
                continue;
            }
            record.setProcessTypeName(processType.getName());

        }
        return processTemplatePage;
    }

    @Override
    public void publish(Long id) {
        ProcessTemplate processTemplate = this.getById(id);
        processTemplate.setStatus(1);
        this.updateById(processTemplate);

        //TODO 認証プロセスの設定
    }
}
