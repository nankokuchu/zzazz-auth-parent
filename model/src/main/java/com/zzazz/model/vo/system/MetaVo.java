package com.zzazz.model.vo.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: MetaVo
 * Package: com.zzazz.model.vo
 *
 * @Author: zzazz
 * @Create: 2024/5/20 - 10:35
 * @Description: MetaVo
 * @Version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaVo implements Serializable {

    private static final long serialVersionUID = 1L;

    // サイドメーニューの名前
    private String title;

    // アイコンのpath src/assets/icons/svg
    private String icon;

    // public MetaVo() {
    // }
    //
    // public MetaVo(String title, String icon) {
    //     this.title = title;
    //     this.icon = icon;
    // }
}
