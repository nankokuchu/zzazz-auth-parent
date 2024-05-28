package com.zzazz.system.process.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzazz.model.process.Process;
import com.zzazz.model.vo.process.ApprovalVo;
import com.zzazz.model.vo.process.ProcessFormVo;
import com.zzazz.model.vo.process.ProcessQueryVo;
import com.zzazz.model.vo.process.ProcessVo;

import java.util.List;
import java.util.Map;

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

    IPage<ProcessVo> findPending(Page<Process> pageParam);

    // プロセスのidから詳細情報を取得
    Map<String, Object> show(Long id);

    // 承認チェック、1は承認、-1は否認
    void approve(ApprovalVo approvalVo);

    IPage<ProcessVo> findProcessed(Page<Process> pageParam);

    IPage<ProcessVo> findStarted(Page<ProcessVo> pageParam);
}