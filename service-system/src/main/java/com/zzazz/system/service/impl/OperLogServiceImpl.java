package com.zzazz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzazz.model.system.SysOperLog;
import com.zzazz.model.vo.system.SysOperLogQueryVo;
import com.zzazz.system.mapper.OperLogMapper;
import com.zzazz.system.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * ClassName: OperLogServiceImpl
 * Package: com.zzazz.system.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 14:26
 * @Description: OperLogServiceImpl
 * @Version: v1.0
 */
@Service
public class OperLogServiceImpl implements OperLogService {

    @Autowired
    private OperLogMapper operLogMapper;

    @Override
    public void saveSysLog(SysOperLog sysOperLog) {
        operLogMapper.insert(sysOperLog);
    }

    @Override
    public IPage<SysOperLog> selectPage(Long page, Long size, SysOperLogQueryVo sysOperLogQueryVo) {
        Page<SysOperLog> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<SysOperLog> wrapper = new LambdaQueryWrapper<>();
        String operName = sysOperLogQueryVo.getOperName();
        String title = sysOperLogQueryVo.getTitle();
        String createTimeBegin = sysOperLogQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysOperLogQueryVo.getCreateTimeEnd();

        if (!StringUtils.isEmpty(operName)){
            wrapper.eq(SysOperLog::getOperName, operName);
        }

        if (!StringUtils.isEmpty(title)){
            wrapper.eq(SysOperLog::getTitle, title);
        }

        if (!StringUtils.isEmpty(createTimeBegin)){
            wrapper.ge(SysOperLog::getCreateTime, createTimeBegin);
        }

        if (!StringUtils.isEmpty(createTimeEnd)){
            wrapper.le(SysOperLog::getCreateTime, createTimeEnd);
        }

        return operLogMapper.selectPage(pageParam, wrapper);
    }
}
