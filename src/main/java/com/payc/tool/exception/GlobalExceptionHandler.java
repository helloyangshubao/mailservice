package com.payc.tool.exception;


import com.payc.tool.constants.enums.ErrorCodeEnum;
import com.payc.tool.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: yangshbuao
 * @Date: 2021/07/13 14:23
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result businessException(BusinessException e) {
        log.error(e.getMessage(), e);
        Result Result = new Result();
        Result.setCode(e.getErrorCode().getCode());
        Result.setMsg(e.getMsgCN());
        return Result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result bindExceptionException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        Result response = new Result();
        response.setCode(ErrorCodeEnum.PARAM_ERROR.getCode());
        response.setMsg(e.getBindingResult().getFieldError().getDefaultMessage());
        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        Result response = new Result();
        response.setCode(ErrorCodeEnum.PARAM_ERROR.getCode());
        response.setMsg(e.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(e.getMessage(), e);
        Result response = new Result();
        response.setCode(ErrorCodeEnum.SYSTEM_ERROR.getCode());
        response.setMsg(ErrorCodeEnum.SYSTEM_ERROR.getDescCN());
        return response;
    }
}
