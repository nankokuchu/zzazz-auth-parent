package com.zzazz.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzazz.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * ClassName: SysUser
 * Package: com.zzazz.model.system
 *
 * @Author: zzazz
 * @Create: 2024/5/16 - 11:10
 * @Description: SysUser
 * @Version: v1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "ユーザーモジュール")
@TableName("sys_user")
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ユーザー名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "パスワード")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "名前")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "電話")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "プロフィール画像URL")
    @TableField("head_url")
    private String headUrl;

    @ApiModelProperty(value = "部署ID")
    @TableField("dept_id")
    private Long deptId;

    @ApiModelProperty(value = "ポストID")
    @TableField("post_id")
    private Long postId;

    @ApiModelProperty(value = "説明")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "ステータス（1：正常 0：停止）")
    @TableField("status")
    private Integer status;

    @TableField(exist = false)
    private List<SysRole> roleList;
    // ポスト
    @TableField(exist = false)
    private String postName;
    // 部署
    @TableField(exist = false)
    private String deptName;
}
