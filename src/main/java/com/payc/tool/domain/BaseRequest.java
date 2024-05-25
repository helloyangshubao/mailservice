package com.payc.tool.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jin.tuo
 * @version 1.0
 * @date 2019/6/21 13:39
 * @description 请求基础类, 所有的对外请求都要继承
 */
@Data
@ApiModel("base")
public class BaseRequest implements Serializable {
    /**
     * 请求id
     */
    @ApiModelProperty(hidden = true)
    private String requestId;

    /**
     * 请求时间
     */
    @ApiModelProperty(hidden = true)
    private Long requestTime;

}


