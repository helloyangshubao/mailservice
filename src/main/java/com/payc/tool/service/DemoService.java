package com.payc.tool.service;

import com.payc.tool.domain.PageRequest;
import com.payc.tool.domain.PageResult;
import com.payc.tool.domain.dto.DemoListRequestDTO;
import com.payc.tool.domain.entity.DemoMongoEntity;

import java.util.List;

/**
 * @author yangshubao
 * Created on 2022/11/22
 */
public interface DemoService {

    /**
     * 批量插入
     *
     * @param demoMongoEntityList
     * @return
     */
    Boolean insertDemo(List<DemoMongoEntity> demoMongoEntityList);

    /**
     *查询
     * @return
     */
    List<DemoMongoEntity> getDemo(DemoListRequestDTO requestDTO);

    /**
     * 集合查询
     * @param request
     * @return
     */
    PageResult<DemoMongoEntity> getDemoList(PageRequest<DemoListRequestDTO> request);
}
