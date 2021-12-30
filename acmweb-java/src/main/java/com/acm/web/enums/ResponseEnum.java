package com.acm.web.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    ERROR(500, "服务器错误"),

    SUCCESS(200, "操作成功"),

    PARAM_ERROR(1, "参数错误"),

    NEED_LOGIN(2, "用户未登录，请先登录"),

    USERNAME_OR_PASSWORD_ERROR(3, "用户名或密码错误"),

    TOKEN_ERROR(4, "token不合法"),

    EXPIRED_ERROR(6, "token已过期或已注销"),

    UPLOAD_SUCCESS(7, "上传成功"),

    UPLOAD_ERROR(8, "上传失败"),

    PASSWORD_INCONSISTENT(9, "两次密码不一致"),

    OLD_PASSWORD_ERROR(10, "旧密码错误"),

    USER_NONEXISTENT(11, "用户不存在"),

    PASSWORD_CONSISTENT(12, "新旧密码一致"),

    DELETE_ERROR(13, "删除失败"),

    NEED_PARAM(14,"参数不能为空"),



    UPLOAD_TYPE_ILLEGAL(16, "上传类型不合法"),

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
