package com.zzazz.model.vo.line;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ClassName: MenuVo
 * Package: com.zzazz.model.vo.line
 *
 * @Author: zzazz
 * @Create: 2024/5/29 - 11:03
 * @Description: LINEMenuVo
 * @Version: v1.0
 */
@Data
@ApiModel(description = "LINEメニュー")
public class MenuVo {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "id")
    private Long parentId;

    @ApiModelProperty(value = "メニュー名")
    private String name;

    @ApiModelProperty(value = "タイプ")
    private String type;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "メニューKEY")
    private String menuKey;

    @ApiModelProperty(value = "ソート")
    private Integer sort;

    @ApiModelProperty(value = "下層メニュー")
    @TableField(exist = false)
    private List<MenuVo> children;
}
