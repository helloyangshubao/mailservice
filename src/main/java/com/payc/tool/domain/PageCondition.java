package com.payc.tool.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yangshubao
 * Created on 2021/7/5
 */
@ApiModel
public class PageCondition implements Serializable {

    @ApiModelProperty(
            value = "当前页码",
            dataType = "Integer",
            example = "1"
    )
    private Integer pageNo;
    @ApiModelProperty(
            value = "每页显示数量",
            dataType = "Integer",
            example = "10"
    )
    private Integer pageSize;

    /**
     * 页码，为非必传参数，默认值为 1
     */
    public Integer getPageNo() {
        return pageNo == null ? 1 : pageNo;
    }

    public void setPageNo(Integer page) {
        this.pageNo = page;
    }

    /**
     * 大小，非必传参数，默认值为 10
     */
    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }

    public void setPageSize(Integer size) {
        this.pageSize = size;
    }
}
