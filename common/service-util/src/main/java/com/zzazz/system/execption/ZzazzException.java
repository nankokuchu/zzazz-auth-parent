package com.zzazz.system.execption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * ClassName: ZzazzException
 * Package: com.zzazz.system.execption
 *
 * @Author: zzazz
 * @Create: 2024/5/14 - 17:09
 * @Description:
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZzazzException extends RuntimeException {
    private Integer code;
    private String message;
}
