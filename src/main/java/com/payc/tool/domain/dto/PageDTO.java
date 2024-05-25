package com.payc.tool.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 表示分页的包装器。
 * spring提供了一个实现本功能的非常好的代码{@link org.springframework.data.domain.Page}，但是这个类有点小问题：
 * 1、它的实现者{@link org.springframework.data.domain.PageImpl}没有默认构造函数，导致无法从json反序列化
 * 2、它的页码是从0开始的，这与我们的直觉不一样，虽然从0开始对于程序计算来说会简单一点，但违反直觉是不能忍受的。
 * 另外，它的实现者包含了太多信息，不适合直接返回给客户端。
 *
 * @author dyy
 */
@Data
@NoArgsConstructor
public class PageDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页，从1开始，1表示第1页，2表示第2页，以此类推
     */
    private Integer page;

    /**
     * 每页数据量
     */
    private Integer size;

    /**
     * 总数据量
     */
    private Long total;

    /**
     * 总页数
     */
    private Long totalPages;

    /**
     * 数据
     */
    private List<T> list;

}
