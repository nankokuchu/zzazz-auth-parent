package com.zzazz.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzazz.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: SysRoleMenu
 * Package: com.zzazz.model.system
 *
 * @Author: zzazz
 * @Create: 2024/5/17 - 10:57
 * @Description: SysRoleMenuBean
 * @Version: v1.0
 */
@Data
@ApiModel(description = "ロールメニュー")
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ロールID")
    @TableField("role_id")
    private String roleId;

    @ApiModelProperty(value = "メニューID")
    @TableField("menu_id")
    private String menuId;

}
