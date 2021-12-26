package com.acm.web.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    ERROR(500, "服务器错误"),

    SUCCESS(200, "操作成功"),

    PARAM_ERROR(1, "参数错误"),

    NEED_LOGIN(2, "用户未登录，请先登录"),

    USERNAME_OR_PASSWORD_ERROR(3, "用户名或密码错误"),

    TOKEN_ERROR(4,"token不合法"),

    EXPIRED_ERROR(6,"token已过期或已注销"),

    UPLOAD_SUCCESS(7,"上传成功"),

    UPLOAD_ERROR(8,"上传失败"),

//    DOWNLOAD_SUCCESS(9,"下载成功"),
//
//    DOWNLOAD_ERROR(10,"下载失败"),

    ;

    Integer code;

    String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
