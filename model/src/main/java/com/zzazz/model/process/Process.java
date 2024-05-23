package com.zzazz.model.process;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzazz.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: Process
 * Package: com.zzazz.model.process
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 12:10
 * @Description: ProcessBean
 * @Version: v1.0
 */
@Data
@ApiModel(description = "プロセス")
@TableName("oa_process")
public class Process extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "承認コード")
    @TableField("process_code")
    private String processCode;

    @ApiModelProperty(value = "ユーザーID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "承認テンプレートID")
    @TableField("process_template_id")
    private Long processTemplateId;

    @ApiModelProperty(value = "承認タイプID")
    @TableField("process_type_id")
    private Long processTypeId;

    @ApiModelProperty(value = "タイトル")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "説明")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "フォーム値")
    @TableField("form_values")
    private String formValues;

    @ApiModelProperty(value = "プロセスインスタンスID")
    @TableField("process_instance_id")
    private String processInstanceId;

    @ApiModelProperty(value = "現在の承認者")
    @TableField("current_auditor")
    private String currentAuditor;

    @ApiModelProperty(value = "ステータス（0：デフォルト 1：承認中 2：承認済み -1：却下）")
    @TableField("status")
    private Integer status;
}
