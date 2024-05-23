package com.zzazz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzazz.model.system.SysLoginLog;
import com.zzazz.model.vo.system.SysLoginLogQueryVo;

/**
 * ClassName: SysLoginLogService
 * Package: com.zzazz.system.service
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 12:29
 * @Description: ユーザが登録する際にログを作成
 * @Version: v1.0
 */
public interface SysLoginLogService {
    void recordLoginLog(String username, Integer status, String ipaddr, String message);


    IPage<SysLoginLog> selectPage(Long page, Long size, SysLoginLogQueryVo sysLoginLogQueryVo);

}
