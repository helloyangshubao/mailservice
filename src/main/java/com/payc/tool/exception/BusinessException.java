package com.payc.tool.exception;

import com.payc.tool.domain.Result;
import lombok.Data;

/**
 * @Author: yangshbuao
 * @Date: 2021/11/23 14:18
 */
@Data
public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;
    private String msg;
    private String msgCN;

    public BusinessException(Result response) {
        this.errorCode = new ErrorCode() {
            @Override
            public Integer getCode() {
                return response.getCode();
            }

            @Override
            public String getDesc() {
                return response.getMsg();
            }

            @Override
            public String getDescCN() {
                return response.getMsg();
            }
        };
        this.msg = response.getMsg();
        this.msgCN = response.getMsg();
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDesc());
        this.errorCode = errorCode;
        this.msg = errorCode.getDesc();
        this.msgCN = errorCode.getDescCN();
    }

    public BusinessException(ErrorCode errorCode, String msg) {
        super(errorCode.getDesc());
        this.errorCode = errorCode;
        this.msg = msg;
        this.msgCN = msg;
    }

    public BusinessException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
        this.msg = errorCode.getDesc();
        this.msgCN = errorCode.getDescCN();
    }


    public BusinessException(Throwable cause, ErrorCode errorCode, String msg) {
        super(cause);
        this.errorCode = errorCode;
        this.msg = msg;
        this.msgCN = msg;
    }
}
