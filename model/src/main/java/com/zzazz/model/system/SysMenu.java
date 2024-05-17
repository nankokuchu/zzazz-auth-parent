package com.zzazz.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzazz.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ClassName: SysMenu
 * Package: com.zzazz.model.system
 *
 * @Author: zzazz
 * @Create: 2024/5/17 - 10:54
 * @Description: メニューBean
 * @Version: v1.0
 */
@Data
@ApiModel(description = "メニュー")
@TableName("sys_menu")
public class SysMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "所属上位")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "タイプ(1:メニュー,2:ボタン)")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "ルートアドレス")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "コンポーネントパス")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "権限識別")
    @TableField("perms")
    private String perms;

    @ApiModelProperty(value = "アイコン")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "ソート")
    @TableField("sort_value")
    private Integer sortValue;

    @ApiModelProperty(value = "状態(0:禁止,1:正常)")
    @TableField("status")
    private Integer status;

    // 下位リスト
    @TableField(exist = false)
    private List<SysMenu> children;
    // 選択されているか
    @TableField(exist = false)
    private boolean isSelect;

}
