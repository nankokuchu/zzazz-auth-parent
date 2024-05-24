package com.zzazz.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzazz.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ClassName: SysLoginLog
 * Package: com.zzazz.model.system
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 12:33
 * @Description: SysLoginLog
 * @Version: v1.0
 */
@Data
@ApiModel(description = "SysLoginLog")
@TableName("sys_login_log")
public class SysLoginLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ユーザーID")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "登録IP")
    @TableField("ipaddr")
    private String ipaddr;

    @ApiModelProperty(value = "登録状態（0成功 1失敗）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "メッセージ")
    @TableField("msg")
    private String msg;

    @ApiModelProperty(value = "アクセス時間")
    @TableField("access_time")
    private Date accessTime;

    @TableField(exist = false)
    private String statusStr;
}
