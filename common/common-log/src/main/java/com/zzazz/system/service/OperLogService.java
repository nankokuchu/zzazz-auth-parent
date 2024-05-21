package com.zzazz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzazz.model.system.SysOperLog;
import com.zzazz.model.vo.SysOperLogQueryVo;

/**
 * ClassName: OperLogService
 * Package: com.zzazz.system.service
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 14:24
 * @Description: OperLogService
 * @Version: v1.0
 */
public interface OperLogService {
    public void saveSysLog(SysOperLog sysOperLog);

    IPage<SysOperLog> selectPage(Long page, Long size, SysOperLogQueryVo sysOperLogQueryVo);
}
