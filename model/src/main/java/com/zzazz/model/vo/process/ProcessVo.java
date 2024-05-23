package com.zzazz.model.vo.process;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ClassName: ProcessVo
 * Package: com.zzazz.model.vo.process
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 12:21
 * @Description: ProcessVoBean
 * @Version: v1.0
 */
@Data
@ApiModel(description = "プロセス")
public class ProcessVo {

    private Long id;

    private Date createTime;

    @ApiModelProperty(value = "承認コード")
    private String processCode;

    @ApiModelProperty(value = "ユーザーID")
    private Long userId;
    private String name;

    @ApiModelProperty(value = "承認テンプレートID")
    @TableField("process_template_id")
    private Long processTemplateId;
    private String processTemplateName;

    @ApiModelProperty(value = "承認タイプID")
    private Long processTypeId;
    private String processTypeName;

    @ApiModelProperty(value = "タイトル")
    private String title;

    @ApiModelProperty(value = "説明")
    private String description;

    @ApiModelProperty(value = "フォームプロパティ")
    private String formProps;

    @ApiModelProperty(value = "フォームオプション")
    private String formOptions;

    @ApiModelProperty(value = "フォーム値")
    private String formValues;

    @ApiModelProperty(value = "プロセスインスタンスID")
    private String processInstanceId;

    @ApiModelProperty(value = "現在の承認者")
    private String currentAuditor;

    @ApiModelProperty(value = "ステータス（0：デフォルト 1：承認中 2：承認済み -1：却下）")
    private Integer status;

    private String taskId;
}

