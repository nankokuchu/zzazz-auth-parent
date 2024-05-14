package com.zzazz.common.result;

import lombok.Getter;

/**
 * ClassName: ResultCodeEnum
 * Package: com.zzazz.common.result
 *
 * @Author: zzazz
 * @Create: 2024/5/14 - 13:34
 * @Description: ResultCodeEnum
 * @Version: v1.0
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(200,"成功"),
    FAIL(201, "失敗"),
    SERVICE_ERROR(2012, "SERVICE異常"),
    DATA_ERROR(204, "データ異常"),
    ILLEGAL_REQUEST(205, "リクエスト異常"),
    REPEAT_SUBMIT(206, "重複提出"),
    ARGUMENT_VALID_ERROR(210, "Parameterバリデーション異常"),

    LOGIN_AUTH(208, "未登録"),
    PERMISSION(209, "権限なし"),
    ACCOUNT_ERROR(214, "IDが間違っている"),
    PASSWORD_ERROR(215, "パスワードが間違っている"),
    LOGIN_MOBLE_ERROR( 216, "IDが間違っている"),
    ACCOUNT_STOP( 217, "ID利用不可"),
    NODE_ERROR( 218, "削除不可")
    ;

    private final Integer code;

    private final String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
