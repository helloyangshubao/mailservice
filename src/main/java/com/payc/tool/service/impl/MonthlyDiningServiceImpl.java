package com.payc.tool.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payc.tool.dao.MonthlyDiningMapper;
import com.payc.tool.domain.entity.MonthlyDiningEntity;
import com.payc.tool.service.MonthlyDiningService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学生选餐表 服务实现类
 * </p>
 *
 * @author wrb
 * @since 2023-07-17
 */
@Service
@Slf4j
public class MonthlyDiningServiceImpl extends ServiceImpl<MonthlyDiningMapper, MonthlyDiningEntity> implements MonthlyDiningService {
    @Override
    public List<MonthlyDiningEntity> queryList(Integer limit, Integer batchSize) {

        QueryWrapper<MonthlyDiningEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("month","2023-05");
        queryWrapper.eq("id","000001e4-bfed-77c9-85f9-6d95fc0e8a43");
//        queryWrapper.last("LIMIT " + (limit) + ", " + batchSize);
        MonthlyDiningEntity batchData = getOne(queryWrapper);
        log.info("safdsafd,{}", JSONObject.toJSONString(batchData));
//        List<MonthlyDiningEntity> batchData = getOne(queryWrapper);
        return null;
    }

    @Override
    public void deleteBatch(List<String> list) {
        this.removeByIds(list);
    }
}
