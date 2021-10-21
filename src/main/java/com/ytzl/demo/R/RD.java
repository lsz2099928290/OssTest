package com.ytzl.demo.R;

/**
 * @Author: Grf9
 * @Creation: 2021/9/15 13:42
 * @Desc: 统一返回的实体类型
 */
public class RD {
    public static <T> R ok(CodeResultEnums codeResultEnums,T data) {
        return new R<T>(CodeResultEnums.LOGIN_ISFALG, data);
    }

    public static <T> R ok(T data) {
        return new R<T>(CodeResultEnums.SUCCESS, data);
    }

    public static <T> R buildSuccess(T data) {
        return new R<T>(CodeResultEnums.SUCCESS, data);
    }

    public static R buildSuccess() {
        return new R(CodeResultEnums.SUCCESS);
    }

    public static R buildSuccess(String msg) {
        return new R(CodeResultEnums.SUCCESS.getCode(), msg);
    }

    public static R buildSuccess(Integer code, String msg) {
        return new R(code, msg);
    }

    public static <T> R buildSuccess(Integer code, String msg, T data) {
        return new R<T>(code, msg, data);
    }

    public static R buildSuccess(CodeResultEnums CodeResultEnums) {
        return new R(CodeResultEnums);
    }

    public static <T> R buildError(T data) {
        return new R<T>(CodeResultEnums.ERROR, data);
    }

    public static R buildError() {
        return new R(CodeResultEnums.ERROR);
    }

    public static R buildError(String msg) {
        return new R(CodeResultEnums.ERROR.getCode(), msg);
    }

    public static R buildError(Integer code, String msg) {
        return new R(code, msg);
    }

    public static <T> R buildError(Integer code, String msg, T data) {
        return new R<T>(code, msg, data);
    }

    public static R buildError(CodeResultEnums CodeResultEnums) {
        return new R(CodeResultEnums);

    }
}
