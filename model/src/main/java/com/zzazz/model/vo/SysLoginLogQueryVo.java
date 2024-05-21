package com.zzazz.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: SysLoginLogQueryVo
 * Package: com.zzazz.model.vo
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 14:51
 * @Description: SysLoginLogQueryVo
 * @Version: v1.0
 */
@Data
public class SysLoginLogQueryVo {

    @ApiModelProperty(value = "ユーザーID")
    private String username;

    private String createTimeBegin;
    private String createTimeEnd;

}