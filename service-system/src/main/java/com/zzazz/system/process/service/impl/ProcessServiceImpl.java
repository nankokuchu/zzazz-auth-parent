package com.zzazz.system.process.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.model.vo.process.ProcessQueryVo;
import com.zzazz.model.vo.process.ProcessVo;
import com.zzazz.system.process.mapper.ProcessMapper;
import com.zzazz.system.process.service.ProcessService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

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
    private static final Logger log = LoggerFactory.getLogger(ProcessServiceImpl.class);

    private final ProcessMapper processMapper;
    private final RepositoryService repositoryService;

    @Autowired
    public ProcessServiceImpl(ProcessMapper processMapper, RepositoryService repositoryService) {
        this.processMapper = processMapper;
        this.repositoryService = repositoryService;
    }

    @Override
    public IPage<ProcessVo> selectPage(Long page, Long size, ProcessQueryVo processQueryVo) {

        Page<ProcessVo> pageParam = new Page<>(page, size);

        return processMapper.selectPage(pageParam, processQueryVo);
    }

    @Override
    public void deployByZip(String deployPath) {
        // zipのinputStream
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(deployPath);
        assert inputStream != null;
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        // プロセースの定義
        Deployment deploy = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .deploy();
        // TODO logを削除する必要あり
        log.info("プロセスID：{}", deploy.getId());
        log.info("プロセスName：{}", deploy.getName());
    }
}
