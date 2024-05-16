package com.zzazz.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzazz.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: SysUserRole
 * Package: com.zzazz.model.system
 *
 * @Author: zzazz
 * @Create: 2024/5/16 - 16:00
 * @Description: SysUserRole
 * @Version: v1.0
 */
@Data
@ApiModel(description = "ユーザーとロールの関係")
@TableName("sys_user_role")
public class SysUserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ロールid")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "ユーザid")
    @TableField("user_id")
    private Long userId;
}