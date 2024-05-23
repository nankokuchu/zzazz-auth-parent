package com.zzazz.model.vo.process;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: ProcessQueryVo
 * Package: com.zzazz.model.vo.process
 *
 * @Author: zzazz
 * @Create: 2024/5/23 - 12:21
 * @Description: ProcessQueryVoBean
 * @Version: v1.0
 */
@Data
@ApiModel(description = "プロセスクエリ")
public class ProcessQueryVo {

    @ApiModelProperty(value = "キーワード")
    private String keyword;

    @ApiModelProperty(value = "ユーザーID")
    private Long userId;

    @ApiModelProperty(value = "承認テンプレートID")
    @TableField("process_template_id")
    private Long processTemplateId;

    @ApiModelProperty(value = "承認タイプID")
    private Long processTypeId;

    @ApiModelProperty(value = "開始作成時間")
    private String createTimeBegin;

    @ApiModelProperty(value = "終了作成時間")
    private String createTimeEnd;

    @ApiModelProperty(value = "ステータス（0：デフォルト 1：承認中 2：承認済み -1：却下）")
    private Integer status;
}

