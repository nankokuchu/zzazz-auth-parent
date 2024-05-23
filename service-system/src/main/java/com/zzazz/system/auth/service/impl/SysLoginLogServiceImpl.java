package com.zzazz.system.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzazz.model.system.SysLoginLog;
import com.zzazz.model.vo.system.SysLoginLogQueryVo;
import com.zzazz.system.auth.mapper.SysLoginLogMapper;
import com.zzazz.system.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * ClassName: SysLoginLogServiceImpl
 * Package: com.zzazz.system.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 12:35
 * @Description: SysLoginLogServiceImpl
 * @Version: v1.0
 */
@Service
public class SysLoginLogServiceImpl implements SysLoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public void recordLoginLog(String username, Integer status, String ipaddr, String message) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUsername(username);
        sysLoginLog.setIpaddr(ipaddr);
        sysLoginLog.setMsg(message);
        // ログの状態
        sysLoginLog.setStatus(status);
        sysLoginLogMapper.insert(sysLoginLog);
    }

    @Override
    public IPage<SysLoginLog> selectPage(Long page, Long size, SysLoginLogQueryVo sysLoginLogQueryVo) {
        Page<SysLoginLog> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<SysLoginLog> wrapper = new LambdaQueryWrapper<>();
        String username = sysLoginLogQueryVo.getUsername();
        String createTimeBegin = sysLoginLogQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysLoginLogQueryVo.getCreateTimeEnd();

        if (!StringUtils.isEmpty(username)){
            wrapper.eq(SysLoginLog::getUsername, username);
        }

        if (!StringUtils.isEmpty(createTimeBegin)){
            wrapper.ge(SysLoginLog::getCreateTime, createTimeBegin);
        }

        if (!StringUtils.isEmpty(createTimeEnd)){
            wrapper.le(SysLoginLog::getCreateTime, createTimeEnd);
        }

        return sysLoginLogMapper.selectPage(pageParam, wrapper);
    }
}
