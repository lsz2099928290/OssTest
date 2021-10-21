package com.ytzl.demo.R;

/**
 * @Author: Grf9
 * @Creation: 2021/9/15 11:20
 * @Desc: 状态码管理
 */
public enum CodeResultEnums {
    //通用的状态码
    SUCCESS(200, "请求成功"),
    ERROR(400, "请求的语法错误或参数错误"),
    UNAUTHORIZED(401, "认证失败"),
    FORBIDDEN(403, "请求资源被拒绝"),
    NOTFOUND(404, "无法找到请求资源"),
    USER_ALL(50100, "未找到"),
    LOGIN_ERROR(50010, "用户登录信息有误"),
    USER_INSERT(50020, "注册失败"),
    USER_UPD(50030, "修改失败"),
    USER_DEL(50040, "删除失败"),
    USER_NAME(50200, "用户名已存在"),
    USER_LOGIN_ERROR4(1107, "验证码失效,请重新发送"),
    USER_LOGIN_ISA(1105,"账号未激活"),
    USER_CODE_ERROR(1103, "验证码不正确"),
    sms_send_error(1110, "验证码发送失败"),
    OSS_DEl_ERROR(60010, "图片删除失败"),
    USER_ISNAME(50500, "用户不存在"),
    USER_CODE_DVERROR(1104, "验证码不正确,验证码不存在"),
    LOGIN_ISFALG(200, "登陆成功"),
    PWD_ISFALG(900, "原始密码不正确"),
    Hotel_STSTEM_ERROR(20003, "系统异常,获取失败"),
    Hotel_LIST_ID(900, "城市id不能为空"),
    //新增常用联系人的状态码
    ADD_USERLINKUSER_EXIST(100411 ,"新增常用联系人失败"),
    ADD_USERLINKUSER_IS_NULL(100412 ,"不能提交空，请填写常用联系人信息"),
    USER_TOKEN_IS_NULL(100000 ,"token失效，请重登录"),
    //删除常用联系人的状态码
    USER_LINK_USER_OK(100431,"删除成功"),
    USER_LINK_USER_DEL(100432 ,"删除常用联系人失败"),
    USER_LINK_USER_DEL_IS_NULL(100433 ,"请选择要删除的常用联系人"),
    TOKEN_ERROR(7100, "用户信息已过期,请重新登陆");
    private Integer code;
    private String massage;


    CodeResultEnums(Integer code, String massage) {
        this.code = code;
        this.massage = massage;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
