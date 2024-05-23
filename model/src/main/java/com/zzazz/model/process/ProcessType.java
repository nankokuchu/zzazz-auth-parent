package com.zzazz.model.process;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzazz.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ClassName: ProcessType
 * Package: com.zzazz.model.process
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 12:12
 * @Description: ProcessTypeBean
 * @Version: v1.0
 */
@Data
@ApiModel(description = "プロセスタイプ")
@TableName("oa_process_type")
public class ProcessType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "タイプ名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "説明")
    @TableField("description")
    private String description;

    @TableField(exist = false)
    private List<ProcessTemplate> processTemplateList;
}
