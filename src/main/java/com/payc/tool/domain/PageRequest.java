package com.payc.tool.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;

/**
 * 包含分页的请求体
 *
 * @author zhaodong
 * @date 2019/7/11 18:22
 */
@Data
public class PageRequest<T> extends Request<T> {
    @ApiModelProperty(value = "当前页码")
    protected Integer page = 1;

    @ApiModelProperty(value = "每页展示数")
    protected Integer size = 20;
}
