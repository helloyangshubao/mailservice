package com.payc.tool.domain;


import lombok.Data;

import java.util.List;

/**
 * 分页返回结果
 *
 * @author yangshubao
 * @date 2022/11/22 13:30
 */
@Data
public class PageResult<T> {
    /**
     * 当前页
     */
    private Integer pageNum;
    /**
     * 每页的数量
     */
    private Integer pageSize;
    /**
     * 总共的条数
     */
    private Long total;
    /**
     * 总共的页数
     */
    private Integer pages;
    /**
     * 实体类集合
     */
    private List<T> list;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}


