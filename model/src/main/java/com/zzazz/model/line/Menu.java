package com.zzazz.model.line;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzazz.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: Menu
 * Package: com.zzazz.model.line
 *
 * @Author: zzazz
 * @Create: 2024/5/29 - 10:38
 * @Description: LineMenu
 * @Version: v1.0
 */
@Data
@ApiModel(description = "LINEメニュー")
@TableName("line_menu")
public class Menu extends BaseEntity {
    @ApiModelProperty(value = "id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "メニュー名")
    private String name;

    @ApiModelProperty(value = "タイプ")
    private String type;

    @ApiModelProperty(value = "ウェブリンク、ユーザーがメニューをクリックするとリンクを開くことができる")
    private String url;

    @ApiModelProperty(value = "メニューKEY値、メッセージインターフェースプッシュ用")
    @TableField("menu_key")
    private String menuKey;

    @ApiModelProperty(value = "ソート")
    private Integer sort;
}
