package com.zzazz.model.vo.process;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: ApprovalVo
 * Package: com.zzazz.model.vo.process
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 12:19
 * @Description: ApprovalVoBean
 * @Version: v1.0
 */
@Data
public class ApprovalVo {

    @ApiModelProperty(value = "プロセスID")
    private Long processId;

    @ApiModelProperty(value = "タスクID")
    private String taskId;

    @ApiModelProperty(value = "ステータス")
    private Integer status;

    @ApiModelProperty(value = "承認の説明")
    private String description;
}

