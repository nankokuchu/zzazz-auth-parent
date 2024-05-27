package com.zzazz.system.process.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.model.process.Process;
import com.zzazz.model.process.ProcessTemplate;
import com.zzazz.model.system.SysUser;
import com.zzazz.model.vo.process.ProcessFormVo;
import com.zzazz.model.vo.process.ProcessQueryVo;
import com.zzazz.model.vo.process.ProcessVo;
import com.zzazz.system.auth.service.SysUserService;
import com.zzazz.system.custom.LoginUserInfoHelper;
import com.zzazz.system.process.mapper.ProcessMapper;
import com.zzazz.system.process.service.ProcessRecordService;
import com.zzazz.system.process.service.ProcessService;
import com.zzazz.system.process.service.ProcessTemplateService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private SysUserService sysUserService;

    @Autowired
    private ProcessTemplateService processTemplateService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProcessRecordService processRecordService;

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

    @Override
    public void startUp(ProcessFormVo processFormVo) {
        // 1,ユーザーidからユーザー情報を取得
        Long userId = LoginUserInfoHelper.getUserId();
        SysUser sysUser = sysUserService.getById(userId);
        log.info("sysUser:{}", sysUser);

        // 2,承認テンプレートIDから承認テンプレートを取得
        Long processTemplateId = processFormVo.getProcessTemplateId();
        ProcessTemplate processTemplate = processTemplateService.getById(processTemplateId);

        // 3,DB_processに保存する
        Process process = new Process();
        BeanUtils.copyProperties(processFormVo, process);
        String workNo = System.currentTimeMillis() + "";
        process.setProcessCode(workNo);
        process.setUserId(LoginUserInfoHelper.getUserId());
        process.setFormValues(processFormVo.getFormValues());
        process.setTitle(sysUser.getName() + "が" + processTemplate.getName() + "申請をしています");
        process.setStatus(1);
        baseMapper.insert(process);

        // 4-1,プロセスインスタンスのkeyを設定
        String processDefinitionKey = processTemplate.getProcessDefinitionKey();
        // 4-2,プロセスインスタンスのBusinessKeyを設定 DB_processのID
        String businessKey = String.valueOf(process.getId());
        // 4-3,プロセスインスタンスのParameterを設定
        String formValues = processFormVo.getFormValues();
        JSONObject jsonObject = JSON.parseObject(formValues);
        JSONObject formData = jsonObject.getJSONObject("formData");
        log.info("formData:{}", formData);
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : formData.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        Map<String, Object> variables = new HashMap<>();
        variables.put("data", map);
        // 4,プロセスのインスタンスを作成する。activiti -> RuntimeService
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);

        // 5,次の承認者を取得
        String processInstanceId = processInstance.getId();
        List<Task> taskList = this.getCurrentTaskList(processInstanceId);
        List<String> nameList = new ArrayList<>();
        for (Task task : taskList) {
            String assigneeName = task.getAssignee();
            SysUser assigneeUser = sysUserService.getUserInfoByUsername(assigneeName);
            String assigneeUserName = assigneeUser.getName();
            nameList.add(assigneeUserName);
            // TODO 6メッセージを送る
        }
        process.setProcessInstanceId(processInstanceId);
        process.setDescription(StringUtils.join(nameList.toString(),",")+"の認証を待ち中です");

        // 7,承認プロセスと業務を結合する
        processMapper.updateById(process);

        // 8,プロセスの操作履歴を保存
        processRecordService.record(process.getId(),1,"認証を申請しています");
    }

    // Taskを取得
    private List<Task> getCurrentTaskList(String processInstanceId) {
        return taskService.createTaskQuery().processInstanceId(processInstanceId).list();
    }
}
