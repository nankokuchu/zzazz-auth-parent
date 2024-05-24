package com.zzazz.system.process.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.model.vo.process.ProcessQueryVo;
import com.zzazz.model.vo.process.ProcessVo;
import com.zzazz.system.process.mapper.ProcessMapper;
import com.zzazz.system.process.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: Process
 * Package: com.zzazz.system.process.service.impl
 *
 * @Author: zzazz
 * @Create: 2024/5/24 - 16:15
 * @Description: Process
 * @Version: v1.0
 */
@Service
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process> implements ProcessService {

    private final ProcessMapper processMapper;

    @Autowired
    public ProcessServiceImpl(ProcessMapper processMapper) {
        this.processMapper = processMapper;
    }

    @Override
    public IPage<ProcessVo> selectPage(Long page, Long size, ProcessQueryVo processQueryVo) {

        Page<ProcessVo> pageParam = new Page<>(page, size);

        return processMapper.selectPage(pageParam, processQueryVo);
    }
}
