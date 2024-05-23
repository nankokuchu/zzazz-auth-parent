package com.zzazz.system.process.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzazz.model.process.ProcessTemplate;

/**
 * ClassName: ProcessTemplate
 * Package: com.zzazz.system.process.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 13:35
 * @Description: ProcessTemplate
 * @Version: v1.0
 */
public interface ProcessTemplateService extends IService<ProcessTemplate> {
    IPage<ProcessTemplate> selectPage(Long page, Long size);
}