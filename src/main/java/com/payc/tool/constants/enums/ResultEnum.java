package com.payc.tool.constants.enums;

/**
 * @author LanceYe
 * @title: ResultEnum
 * @description: 返回的结果集
 * @date 2019/4/28
 */
public enum ResultEnum {

    SUCCESS(200, "success"),
    ERROR(500, "failed");

    private int code;

    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
