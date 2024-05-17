package com.zzazz.system.exception;

import com.zzazz.common.result.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: ZzazzException
 * Package: com.zzazz.system
 *
 * @Author: zzazz
 * @Create: 2024/5/14 - 17:23
 * @Description: ZzazzException
 * @Version: v1.0
 */
@Data
@NoArgsConstructor
public class ZzazzException extends RuntimeException {
    private Integer code;
    private String message;

    public ZzazzException(ResultCodeEnum nodeError) {
        this.code = nodeError.getCode();
        this.message = nodeError.getMessage();
    }
}
