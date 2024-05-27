package com.zzazz.system.process.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzazz.model.process.ProcessRecord;

/**
 * ClassName: ProcessRecord
 * Package: com.zzazz.system.process.service
 *
 * @Author: zzazz
 * @Create: 2024/5/27 - 16:52
 * @Description: ProcessRecord
 * @Version: v1.0
 */
public interface ProcessRecordService extends IService<ProcessRecord> {

    void record(Long processId, Integer status, String description);
}