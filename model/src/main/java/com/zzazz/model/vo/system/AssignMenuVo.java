package com.zzazz.model.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ClassName: AssignMenuVo
 * Package: com.zzazz.model.vo
 *
 * @Author: zzazz
 * @Create: 2024/5/17 - 15:23
 * @Description: AssignMenuVo
 * @Version: v1.0
 */
@ApiModel(description = "メニュー割り当て")
@Data
public class AssignMenuVo {
    @ApiModelProperty(value = "ロールID")
    private Long roleId;

    @ApiModelProperty(value = "メニューIDリスト")
    private List<Long> menuIdList;
}
