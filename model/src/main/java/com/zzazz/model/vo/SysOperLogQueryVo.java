package com.zzazz.model.vo;

import lombok.Data;

/**
 * ClassName: SysOperLogQueryVo
 * Package: com.zzazz.model.vo
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 15:23
 * @Description: SysOperLogQueryVo
 * @Version: v1.0
 */
@Data
public class SysOperLogQueryVo {
    private String title;
    private String operName;
    private String createTimeBegin;
    private String createTimeEnd;
}
