package com.zzazz.system.process.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzazz.model.process.ProcessType;

import java.util.List;

/**
 * ClassName: ProcessType
 * Package: com.zzazz.system.process.service
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 13:42
 * @Description:
 * @Version: v1.0
 */
public interface ProcessTypeService extends IService<ProcessType> {
    IPage<ProcessType> getPage(Long page, Long size);

    List<ProcessType> findProcessType();
}