package com.payc.tool.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;

/**
 * @author jin.tuo
 * @version 1.0
 * @date 2019/6/21 13:39
 * @description 请求通用
 */

@Data
public class Request<T> extends BaseRequest {
    /**
     * 业务参数对象
     */
    @ApiModelProperty(value = "业务入参对象")
    @Valid
    protected T model;
}
