package com.ytzl.demo.R;

import lombok.Data;

/**
 * @Author: Grf9
 * @Creation: 2021/9/15 12:22
 * @Desc: 控制器返回值
 */
@Data
public class R<T> {

    private Integer code;
    private String message;
    private T data;

    //1.构造函数->自定义状态码
    public R() {
    }

    public R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    //2.构造函数->引用枚举做状态码
    public R(CodeResultEnums codeResultEnums) {
        this.code = codeResultEnums.getCode();
        this.message = codeResultEnums.getMassage();
    }

    public R(CodeResultEnums codeResultEnums, T data) {
        this.code = codeResultEnums.getCode();
        this.message = codeResultEnums.getMassage();
        this.data = data;
    }
}
