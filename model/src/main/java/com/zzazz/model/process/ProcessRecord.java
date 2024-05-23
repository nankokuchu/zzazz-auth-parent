package com.zzazz.model.process;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzazz.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: ProcessRecord
 * Package: com.zzazz.model.process
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 12:13
 * @Description: ProcessRecordBean
 * @Version: v1.0
 */
@Data
@ApiModel(description = "プロセスレコード")
@TableName("oa_process_record")
public class ProcessRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "承認プロセスID")
    @TableField("process_id")
    private Long processId;

    @ApiModelProperty(value = "承認の説明")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "ステータス")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "操作ユーザーID")
    @TableField("operate_user_id")
    private Long operateUserId;

    @ApiModelProperty(value = "操作ユーザー")
    @TableField("operate_user")
    private String operateUser;
}
