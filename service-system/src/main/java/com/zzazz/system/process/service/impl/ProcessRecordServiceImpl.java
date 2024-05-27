package com.zzazz.system.process.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.model.process.ProcessRecord;
import com.zzazz.model.system.SysUser;
import com.zzazz.system.auth.service.SysUserService;
import com.zzazz.system.custom.LoginUserInfoHelper;
import com.zzazz.system.process.mapper.ProcessRecordMapper;
import com.zzazz.system.process.service.ProcessRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: ProcessRecord
 * Package: com.zzazz.system.process.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/27 - 16:52
 * @Description: ProcessRecord
 * @Version: v1.0
 */
@Service
public class ProcessRecordServiceImpl extends ServiceImpl<ProcessRecordMapper, ProcessRecord> implements ProcessRecordService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ProcessRecordMapper processRecordMapper;

    @Override
    public void record(Long processId, Integer status, String description) {
        Long userId = LoginUserInfoHelper.getUserId();
        SysUser sysUser = sysUserService.getById(userId);
        ProcessRecord processRecord = new ProcessRecord();
        processRecord.setProcessId(processId);
        processRecord.setStatus(status);
        processRecord.setDescription(description);
        processRecord.setOperateUser(sysUser.getName());
        processRecord.setOperateUserId(userId);
        processRecordMapper.insert(processRecord);
    }
}
