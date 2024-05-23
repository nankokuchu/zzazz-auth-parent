package com.zzazz.model.process;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzazz.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: ProcessTemplate
 * Package: com.zzazz.model.process
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 12:12
 * @Description: ProcessTemplateBean
 * @Version: v1.0
 */
@Data
@ApiModel(description = "プロセステンプレート")
@TableName("oa_process_template")
public class ProcessTemplate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "テンプレート名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "アイコンパス")
    @TableField("icon_url")
    private String iconUrl;

    @ApiModelProperty(value = "プロセスタイプID")
    @TableField("process_type_id")
    private Long processTypeId;

    @ApiModelProperty(value = "フォームプロパティ")
    @TableField("form_props")
    private String formProps;

    @ApiModelProperty(value = "フォームオプション")
    @TableField("form_options")
    private String formOptions;

    @ApiModelProperty(value = "説明")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "プロセス定義キー")
    @TableField("process_definition_key")
    private String processDefinitionKey;

    @ApiModelProperty(value = "プロセス定義アップロードパス")
    @TableField("process_definition_path")
    private String processDefinitionPath;

    @ApiModelProperty(value = "プロセス定義モデルID")
    @TableField("process_model_id")
    private String processModelId;

    @ApiModelProperty(value = "ステータス")
    @TableField("status")
    private Integer status;

    @TableField(exist = false)
    private String processTypeName;
}
