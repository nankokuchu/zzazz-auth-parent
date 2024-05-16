package com.zzazz.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ClassName: AssignRoleVo
 * Package: com.zzazz.model.vo
 *
 * @Author: zzazz
 * @Create: 2024/5/16 - 16:32
 * @Description: AssignRoleVo
 * @Version: v1.0
 */
@ApiModel(description = "ロールを割り当てる")
@Data
public class AssignRoleVo {
    @ApiModelProperty(value = "ユーザーID")
    private Long userId;

    @ApiModelProperty(value = "ロールID配列")
    private List<Long> roleIdList;
}
