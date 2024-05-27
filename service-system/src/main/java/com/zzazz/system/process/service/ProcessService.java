package com.zzazz.system.process.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzazz.model.process.Process;
import com.zzazz.model.vo.process.ProcessFormVo;
import com.zzazz.model.vo.process.ProcessQueryVo;
import com.zzazz.model.vo.process.ProcessVo;

/**
 * ClassName: Process
 * Package: com.zzazz.system.process.service
 *
 * @Author: zzazz
 * @Create: 2024/5/24 - 16:15
 * @Description: Process
 * @Version: v1.0
 */
public interface ProcessService extends IService<Process> {
    IPage<ProcessVo> selectPage(Long page, Long size, ProcessQueryVo processQueryVo);


    // プロセスの作成
    void deployByZip(String deployPath);

    void startUp(ProcessFormVo processFormVo);
}