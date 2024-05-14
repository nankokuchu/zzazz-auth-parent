package com.zzazz.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private String id;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableLogic  //ロジック削除 デフォルト 0 未削除 1 削除済
    @TableField("is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private Map<String, Object> param = new HashMap<>();

    public BaseEntity() {
    }

    public BaseEntity(String id, Date createTime, Date updateTime, Integer isDeleted, Map<String, Object> param) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDeleted = isDeleted;
        this.param = param;
    }
}
