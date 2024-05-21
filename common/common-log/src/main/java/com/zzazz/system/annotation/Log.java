package com.zzazz.system.annotation;

import com.zzazz.system.enums.BusinessType;
import com.zzazz.system.enums.OperatorType;

import java.lang.annotation.*;

/**
 * ClassName: Log
 * Package: com.zzazz.system.annotation
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 13:58
 * @Description: Log
 * @Version: v1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    // モジュール
    public String title() default "";

    // 機能
    public BusinessType businessType() default BusinessType.OTHER;

    // 操作ユーザー
    public OperatorType operatorType() default OperatorType.MANAGE;

    // リスエストのParameterを保存するか
    public boolean isSaveRequestData() default true;

    // レスポンスのParameterを保存するか
    public boolean isSaveResponseData() default true;
}
