package com.payc.tool.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.payc.tool.domain.entity.MonthlyDiningEntity;

import java.util.List;

/**
 * <p>
 * 学生选餐表 服务类
 * </p>
 *
 * @author wrb
 * @since 2023-07-17
 */
public interface MonthlyDiningService extends IService<MonthlyDiningEntity> {

    List<MonthlyDiningEntity> queryList(Integer limit, Integer batchSize);

    void deleteBatch(List<String> list);

}
