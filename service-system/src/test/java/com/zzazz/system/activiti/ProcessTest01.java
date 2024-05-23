package com.zzazz.system.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * ClassName: ProcessTest01
 * Package: com.zzazz.system.activiti
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 10:29
 * @Description: xmlとpngによる操作
 * @Version: v1.0
 */
@SpringBootTest
public class ProcessTest01 {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    /**
     * 1, Processをデプロイ　repositoryService
     * 2, Processのインスタンスを作成　runtimeService
     * 3, Processのインスタンス探す　+　完成　taskService　+　historicTaskInstances
     * 4, Processのインスタンス次のステップに移動　taskService
     * 5, Processのインスタンスが完了
     * 6, Processを一時的に全てdownする。
     */

    @Test
    void deployProcess() {
        // processのデプロイ
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/yasumi.bpmn20.xml")
                .addClasspathResource("process/yasumi.png")
                .name("有給取得流れ")
                .deploy();
        System.out.println("processのID = " + deploy.getId());
        // processのID = b4ade387-18a5-11ef-805e-32c9ab539985
        System.out.println("processの名前 = " + deploy.getName());
        // processの名前 = 有給取得流れ
    }

    @Test
    void startUpProcess() {
        // processInstanceの取得
        ProcessInstance yasumi = runtimeService.startProcessInstanceByKey("yasumi");
        System.out.println("該当プロセスのID = " + yasumi.getProcessDefinitionId());
        System.out.println("該当プロセスのインスタンスID = " + yasumi.getId());
        System.out.println("該当プロセスの活動しているID = " + yasumi.getActivityId());
        System.out.printf("開始時間%s", yasumi.getStartTime());
    }

    // activitiのDBと実際業務のDBを関連する
    @Test
    void startUpProcessAndBusinessKey() {
        String businessKey = "1001";
        String processKey = "yasumi";
        ProcessInstance yasumi = runtimeService.startProcessInstanceByKey(processKey, businessKey);
        System.out.println("yasumi.getBusinessKey() = " + yasumi.getBusinessKey());
    }

    @Test
    void getTask() {
        // 下記のtaskはtanakaのtask内容
        String assignee = "tanaka";
        // tanakaのtaskが完了していないと次のステップに入らない。つまり、bossには現在taskがない
        // String assignee = "boss";
        List<Task> tanakaList = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();

        for (Task task : tanakaList) {
            System.out.println("task.getProcessInstanceId() = " + task.getProcessInstanceId()); // c671da99-18a7-11ef-9476-32c9ab539985
            System.out.println("task.getId() = " + task.getId()); // c677ce0d-18a7-11ef-9476-32c9ab539985
            System.out.println("task.getAssignee() = " + task.getAssignee()); // tanaka
            System.out.println("task.getName() = " + task.getName()); //管理者認証
        }
    }

    // 該当のtaskを処理する
    @Test
    void completeTask() {
        String assignee = "tanaka";
        // String assignee = "boss"

        // 責任者が処理すべきのtaskを探す。
        Task task = taskService.createTaskQuery().taskAssignee(assignee).singleResult();
        // 複数を探す
        // List<Task> taskList = taskService.createTaskQuery().taskAssignee(assignee).list();

        // taskを処理する
        taskService.complete(task.getId());
    }

    // 処理が終わったtaskを探す
    @Test
    void findProcessedTaskList() {
        String assignee = "tanaka";
        // String assignee = "boss"
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(assignee)
                .finished().list();
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            // c671da99-18a7-11ef-9476-32c9ab539985
            System.out.println("historicTaskInstance.getProcessInstanceId() = " + historicTaskInstance.getProcessInstanceId());

            // c677ce0d-18a7-11ef-9476-32c9ab539985
            System.out.println("historicTaskInstance.getId() = " + historicTaskInstance.getId());

            // tanaka
            System.out.println("historicTaskInstance.getAssignee() = " + historicTaskInstance.getAssignee());

            // 管理者認証
            System.out.println("historicTaskInstance.getName() = " + historicTaskInstance.getName());
        }
    }

    // 全てのProcessをdownもしくはup
    @Test
    void suspendProcessInstanceAll() {
        // 全てのProcessを取得
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("yasumi").list();

        for (ProcessDefinition processDefinition : processDefinitions) {
            // falseの場合UPしている状況、　trueの場合DOWNしている状況
            boolean suspended = processDefinition.isSuspended();

            if (suspended) {
                /*
                    引数1: Processのid
                    引数2: true
                    引数3: 時間
                 */
                // UPする
                repositoryService.activateProcessDefinitionById(processDefinition.getId(), true, null);
            }else {
                // DOWNする
                repositoryService.suspendProcessDefinitionById(processDefinition.getId(), true, null);
            }
        }
    }
}
