package com.payc.tool.domain;


import com.alibaba.druid.support.json.JSONUtils;
import com.payc.tool.constants.enums.ResultEnum;
import lombok.Data;

/**
 * @author LanceYe
 * @title: Result
 * @description: http结果集封装
 * @date 2019/4/28
 */
@Data
public class Result<T> {

    public Result() {
    }

    /**
     * ResultEnum
     * 错误码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体的内容
     */
    private T data;

    /**
     * 成功时候的调用
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    /**
     * 根据返回的状态对象， 构建返回结果
     *
     * @param resultEnum
     * @param <T>
     * @return
     */
    public static <T> Result<T> build(ResultEnum resultEnum) {
        return new Result<>(resultEnum);
    }

    /**
     * 根据 code， 和  msg 构建返回结果
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> build(int code, String msg) {
        return new Result<T>(code, msg);
    }

    /**
     * 根据 code， 和  msg, 和 data 构建返回结果
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> build(int code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }

    /**
     * 失败的调用
     *
     * @param codeMsg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String codeMsg) {
        return new Result<>(codeMsg);
    }

    /**
     * 失败的调用 将返回结果传入
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(T data) {
        return new Result<>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg(), data);
    }

    private Result(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    private Result(String msg) {
        this.code = ResultEnum.ERROR.getCode();
        this.data = null;
        this.msg = msg;
    }

    private Result(ResultEnum ResultEnum) {
        this.code = ResultEnum.getCode();
        this.msg = ResultEnum.getMsg();
    }

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "";
    }

    /**
     * 判断当前请求是否处理完毕
     * 此次处理请求正确处理完毕,不代表一定有数据返回
     *
     * @return
     */
    public boolean succeeded() {
        if (ResultEnum.SUCCESS.getCode() == this.code) {
            return true;
        }
        return false;
    }
}