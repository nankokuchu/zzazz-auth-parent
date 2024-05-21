package com.zzazz.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzazz.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ClassName: SysOperLog
 * Package: com.zzazz.model.system
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 14:10
 * @Description: SysOperLogBean
 * @Version: v1.0
 */
@Data
@ApiModel(description = "SysOperLog")
@TableName("sys_oper_log")
public class SysOperLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "モジュールタイトル")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "機能タイプ（0その他、 1Create 2Update 3Delete）")
    @TableField("business_type")
    private String businessType;

    @ApiModelProperty(value = "メソッド名前")
    @TableField("method")
    private String method;

    @ApiModelProperty(value = "HTTPメソッド")
    @TableField("request_method")
    private String requestMethod;

    @ApiModelProperty(value = "操作（0その他 1管理者 2モバイル）")
    @TableField("operator_type")
    private String operatorType;

    @ApiModelProperty(value = "操作した人")
    @TableField("oper_name")
    private String operName;

    @ApiModelProperty(value = "部門名称")
    @TableField("dept_name")
    private String deptName;

    @ApiModelProperty(value = "リクエストURL")
    @TableField("oper_url")
    private String operUrl;

    @ApiModelProperty(value = "IP")
    @TableField("oper_ip")
    private String operIp;

    @ApiModelProperty(value = "リクエストParam")
    @TableField("oper_param")
    private String operParam;

    @ApiModelProperty(value = "レスポンスParam")
    @TableField("json_result")
    private String jsonResult;

    @ApiModelProperty(value = "操作ステーターす（0正常 1異常）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "エラーメッセージ")
    @TableField("error_msg")
    private String errorMsg;

    @ApiModelProperty(value = "操作時間")
    @TableField("oper_time")
    private Date operTime;

}
