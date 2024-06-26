package com.zzazz.model.vo.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: RouterVo
 * Package: com.zzazz.model.vo
 *
 * @Author: zzazz
 * @Create: 2024/5/20 - 10:32
 * @Description: RouterVo
 * @Version: v1.0
 */
@Data
public class RouterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    // routerの名前
    //private String name;

    // routerのpath
    private String path;

    // routerを表示するか？trueの場合メニューに表示されない
    private boolean hidden;

    // componentのpath
    private String component;

    private Boolean alwaysShow;

    private MetaVo meta;

    // 子のrouter
    private List<RouterVo> children;
}
