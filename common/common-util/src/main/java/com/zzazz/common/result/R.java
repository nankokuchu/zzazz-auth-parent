package com.zzazz.common.result;

import lombok.Data;

/**
 * ClassName: R
 * Package: com.zzazz.common.result
 *
 * @Author: zzazz
 * @Create: 2024/5/14 - 13:29
 * @Description: レスポンシブで返す統一データ形式
 * @Version: v1.0
 */
@Data
public class R<T> {
    //返すコード
    private Integer code;

    //返すメッセージ
    private String message;

    //返すデータ
    private T data;

    public R(){}

    protected static <T> R<T> build(T data) {
        R<T> result = new R<T>();
        if (data != null)
            result.setData(data);
        return result;
    }

    public static <T> R<T> build(T body, Integer code, String message) {
        R<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> R<T> build(T body, ResultCodeEnum resultCodeEnum) {
        R<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }


    /**
     * 成功した時かつデータがない時
     * @param <T>
     * @return
     */
    public static<T> R<T> ok(){
        return R.ok(null);
    }

    /**
     * 操作が成功した時且つデータがある時
     * @param data  baseCategory1List
     * @param <T>
     * @return
     */
    public static<T> R<T> ok(T data){
        // R<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static<T> R<T> fail(){
        return R.fail(null);
    }

    /**
     * 操作が失敗した時かつデータがある時
     * @param data
     * @param <T>
     * @return
     */
    public static<T> R<T> fail(T data){
        // R<T> result = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    public R<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public R<T> code(Integer code){
        this.setCode(code);
        return this;
    }
}
