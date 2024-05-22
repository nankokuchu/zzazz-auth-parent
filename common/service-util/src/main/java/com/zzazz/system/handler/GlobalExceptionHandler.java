package com.zzazz.system.handler;

import com.zzazz.common.result.R;
import com.zzazz.common.result.ResultCodeEnum;
import com.zzazz.system.exception.ZzazzException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.zzazz.system.handler
 *
 * @Author: zzazz
 * @Create: 2024/5/14 - 17:00
 * @Description: GlobalExceptionHandler
 * @Version: v1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.fail().message("異常が発生しました。");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.fail().message("特定の異常が発生しました");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public R error(AccessDeniedException e) throws AccessDeniedException {
        e.printStackTrace();
        return R.build(null, ResultCodeEnum.PERMISSION);
    }

    @ExceptionHandler({ZzazzException.class})
    @ResponseBody
    public R error(ZzazzException e) {
        e.printStackTrace();
        return R.fail().message(e.getMessage());
    }
}
