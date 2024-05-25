package com.payc.tool.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author zhaodong
 * @date 2019/8/22 13:26
 */
@Data
@ApiModel(value = "DemoListRequestDTO", description = "查询列表")
public class DemoListRequestDTO {
    @ApiModelProperty(value = "关键字", required = true)
    private String keyWord;

    @ApiModelProperty(value = "状态", required = true)
    private Boolean used;
}
