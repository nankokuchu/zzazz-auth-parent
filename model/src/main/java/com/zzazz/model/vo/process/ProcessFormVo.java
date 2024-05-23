package com.zzazz.model.vo.process;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: ProcessFormVo
 * Package: com.zzazz.model.vo.process
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 12:20
 * @Description: ProcessFormVoBean
 * @Version: v1.0
 */
@Data
@ApiModel(description = "プロセスフォーム")
public class ProcessFormVo {

    @ApiModelProperty(value = "承認テンプレートID")
    private Long processTemplateId;

    @ApiModelProperty(value = "承認タイプID")
    private Long processTypeId;

    @ApiModelProperty(value = "フォーム値")
    private String formValues;
}
