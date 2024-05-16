package com.zzazz.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: SysUserQueryVo
 * Package: com.zzazz.model.vo
 *
 * @Author: zzazz
 * @Create: 2024/5/16 - 11:14
 * @Description: SysUserQueryVo
 * @Version: v1.0
 */
@Data
public class SysUserQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String keyword;
    private String createTimeBegin;
    private String createTimeEnd;
    private Long roleId;
    private Long postId;
    private Long deptId;
}