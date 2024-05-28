package com.zzazz.system.process.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzazz.model.process.Process;
import com.zzazz.model.process.ProcessRecord;
import com.zzazz.model.process.ProcessTemplate;
import com.zzazz.model.system.SysUser;
import com.zzazz.model.vo.process.ApprovalVo;
import com.zzazz.model.vo.process.ProcessFormVo;
import com.zzazz.model.vo.process.ProcessQueryVo;
import com.zzazz.model.vo.process.ProcessVo;
import com.zzazz.system.auth.service.SysUserService;
import com.zzazz.system.custom.LoginUserInfoHelper;
import com.zzazz.system.process.mapper.ProcessMapper;
import com.zzazz.system.process.service.ProcessRecordService;
import com.zzazz.system.process.service.ProcessService;
import com.zzazz.system.process.service.ProcessTemplateService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        process.setDescription(StringUtils.join(nameList.toString(), ",") + "の認証を待ち中です");

        // 7,承認プロセスと業務を結合する
        processMapper.updateById(process);

        // 8,プロセスの操作履歴を保存
        processRecordService.record(process.getId(), 1, "認証を申請しています");
    }

    @Override
    public List<ProcessVo> findPending() {
        // 1,登録しているusernameでTaskを探す
        String username = LoginUserInfoHelper.getUsername();
        TaskQuery taskQuery = taskService.createTaskQuery()
                .taskAssignee(username)
                .orderByTaskCreateTime()
                .desc();
        List<Task> taskList = taskQuery.list();

        // 2,List<Task>をList<ProcessVo>形式に変換
        // TODO log
        List<ProcessVo> processVoList = new ArrayList<>();
        for (Task task : taskList) {
            log.info("task:{}", task);
            // taskからprocessInstanceIdを取得
            String processInstanceId = task.getProcessInstanceId();
            // processInstanceIdからprocessInstanceを取得
            ProcessInstance processInstance =
                    runtimeService.createProcessInstanceQuery()
                            .processInstanceId(processInstanceId)
                            .singleResult();
            // processInstanceのbusinessKeyを取得
            String businessKey = processInstance.getBusinessKey();
            if (businessKey == null) {
                continue;
            }
            // businessKeyからProcessを取得
            Long processId = Long.parseLong(businessKey);
            Process process = baseMapper.selectById(processId);
            log.info("process::::{}", process);
            // ProcessをProcessVoに値を入れる
            ProcessVo processVo = new ProcessVo();
            BeanUtils.copyProperties(process, processVo);
            processVo.setTaskId(task.getId());
            log.info("processVo::::{}", processVo);
            processVoList.add(processVo);
        }

        return processVoList;
    }

    // プロセスのidから詳細情報を取得
    @Override
    public Map<String, Object> show(Long id) {
        // 1,ProcessId(param id)からProcessを取得
        Process process = baseMapper.selectById(id);

        // 2,processIdからprocessRecodeを取得
        LambdaQueryWrapper<ProcessRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProcessRecord::getProcessId, id);
        List<ProcessRecord> processRecordList = processRecordService.list(wrapper);

        // 3,processからTemplateを取得
        Long processTemplateId = process.getProcessTemplateId();
        ProcessTemplate processTemplate = processTemplateService.getById(processTemplateId);

        // 4,カレントユーザーが承認権限があるかをチェック（重複承認も防ぐ）
        boolean isApprove = false;
        String processInstanceId = process.getProcessInstanceId();
        List<Task> currentTaskList = this.getCurrentTaskList(processInstanceId);
        for (Task task : currentTaskList) {
            // task中の承認ユーザーがカレントユーザーであるかをチェック
            String username = LoginUserInfoHelper.getUsername();
            if (task.getAssignee().equals(username)) {
                isApprove = true;
            }
        }

        // 5,データを返す
        Map<String, Object> map = new HashMap<>();
        map.put("process", process);
        map.put("processRecordList", processRecordList);
        map.put("processTemplate", processTemplate);
        map.put("isApprove", isApprove);
        return map;
    }

    // 承認チェック、1は承認、-1は否認
    @Override
    public void approve(ApprovalVo approvalVo) {
        // 1,approvalVoからプロセス情報を取得
        String taskId = approvalVo.getTaskId();
        Map<String, Object> variables = taskService.getVariables(taskId);
        variables.forEach((key, value) -> {
            log.info("key::{}", key);
            log.info("value::{}", value);
        });

        // 2,認証ステータスを判断　1は認証、-1は否認
        Integer status = approvalVo.getStatus();
        if (status == 1) {
            // 追加でデータを保存することができる。nullでも
            Map<String, Object> tempVariables = new HashMap<>();
            taskService.complete(taskId, tempVariables);
        } else {
            this.endTask(taskId);
        }

        // 3,承認の流れを保存
        Long processId = approvalVo.getProcessId();
        String description = status == 1 ? "認証済み" : "否認";
        processRecordService.record(processId, status, description);

        // 4,次の認証者を取得
        // 5,ProcessのDBを更新
        Process process = baseMapper.selectById(processId);
        List<Task> currentTaskList = this.getCurrentTaskList(process.getProcessInstanceId());
        log.info("currentTaskList:{}", currentTaskList);
        if (!CollectionUtils.isEmpty(currentTaskList)) {
            List<String> assignList = new ArrayList<>();
            for (Task task : currentTaskList) {
                String assignee = task.getAssignee();
                SysUser sysUser = sysUserService.getUserInfoByUsername(assignee);
                assignList.add(sysUser.getName());
                // TODO メッセージを送る
            }
            process.setDescription(StringUtils.join(assignList.toArray(), ",") + "の承認を待ちます");
            process.setStatus(1);
        } else {
            if (status == 1) {
                process.setDescription("認証済み");
                process.setStatus(2);
            } else {
                process.setDescription("否認");
                process.setStatus(-1);
            }
        }

        baseMapper.updateById(process);
    }

    private void endTask(String taskId) {
        // 1,taskIdから現在のTaskを取得
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        // 2,定義したプロセスのモデルを取得(activiti)BpmnModel
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());

        // 3,BpmnModelのEndポイントを取得
        List<EndEvent> endEventList =
                bpmnModel.getMainProcess().findFlowElementsOfType(EndEvent.class);
        if (CollectionUtils.isEmpty(endEventList)) {
            return;
        }
        FlowNode endFlowNode = (FlowNode) endEventList.get(0);

        // 4,現在のポイントを取得
        FlowNode currentFlowNode =
                (FlowNode) bpmnModel.getMainProcess().getFlowElement(task.getTaskDefinitionKey());

        // tempで本来の流れを保存する
        List<org.activiti.bpmn.model.SequenceFlow> originalSequenceFlowList = new ArrayList<>();
        originalSequenceFlowList.addAll(currentFlowNode.getOutgoingFlows());
        // TODO log
        log.info("本来の流れ:{}", originalSequenceFlowList);
        // 5,本来のBpmnModelの流れをクリアする(削除する)
        currentFlowNode.getOutgoingFlows().clear();

        // 6,新しいBpmnModelの流れを作製する。
        SequenceFlow newSequenceFlow = new SequenceFlow();
        newSequenceFlow.setId("newSequenceFlow");
        newSequenceFlow.setSourceFlowElement(currentFlowNode);
        newSequenceFlow.setTargetFlowElement(endFlowNode);

        // 7,現在のポイントとEndポイントを結ぶ
        List<SequenceFlow> newSequenceFlowList = new ArrayList<>();
        newSequenceFlowList.add(newSequenceFlow);
        currentFlowNode.setOutgoingFlows(newSequenceFlowList);

        // 8,Taskを完成する
        taskService.complete(taskId);
    }

    // Taskを取得
    private List<Task> getCurrentTaskList(String processInstanceId) {
        return taskService.createTaskQuery().processInstanceId(processInstanceId).list();
    }
}
