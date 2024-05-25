package com.payc.tool.service.impl;

import com.mongodb.bulk.BulkWriteResult;
import com.payc.tool.constants.MongodbConstant;
import com.payc.tool.domain.PageResult;
import com.payc.tool.domain.PageRequest;
import com.payc.tool.domain.dto.DemoListRequestDTO;
import com.payc.tool.domain.entity.DemoMongoEntity;
import com.payc.tool.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangshubao
 * Created on 2022/11/22
 */
//@Service
@Service("demoService")
public class DemoServiceImpl implements DemoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Boolean insertDemo(List<DemoMongoEntity> demoMongoEntityList) {
        BulkOperations operations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, MongodbConstant.DEMO);
        operations.insert(demoMongoEntityList);
        BulkWriteResult result = operations.execute();
        return result != null;
    }

    @Override
    public List<DemoMongoEntity> getDemo(DemoListRequestDTO requestDTO) {
        Query query = new Query();
        query.addCriteria(Criteria.where("demo1").is("demo1"));
        List<DemoMongoEntity> list = mongoTemplate.find(query, DemoMongoEntity.class);
        return list;
    }

    @Override
    public PageResult<DemoMongoEntity> getDemoList(PageRequest<DemoListRequestDTO> request) {
        Query query = new Query();
//        query.addCriteria(Criteria.where("demo1").is("demo1"));
//        List<DemoMongoEntity> list = mongoTemplate.find(query, DemoMongoEntity.class);
//
//        int pageIndex = request.getPage() > 0 ? request.getPage() - 1 : request.getPage();
//        Pageable pageable = PageRequest.of(pageIndex, request.getSize());
//        query.with(pageable);
//        // 排序
////        query.with(Sort.by(Sort.Direction.DESC, MongoFieldsConstant.SIGNED_HOTEL_CODE_FIELD));
//        // 查询总数
//        int count = (int) mongoTemplate.count(query, DemoMongoEntity.class,
//                MongodbConstant.DEMO);
//        PageResult<DemoMongoEntity> pageResult = PageResult.success(list);
//        pageResult.setCurrent(request.getPage());
//        pageResult.setSize(request.getSize());
//        pageResult.setTotal(count);


        long total = this.mongoTemplate.count(query, DemoMongoEntity.class);
        Integer pages = (int)Math.ceil((double)total / (double)request.getSize());
        if (request.getSize() <= 0 || request.getSize() > pages) {
            request.setPage(1);
        }
        int skip = request.getSize() * (request.getPage() - 1);
        query.skip(skip).limit(request.getSize());
        List<DemoMongoEntity> list = mongoTemplate.find(query, DemoMongoEntity.class);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(total);
        pageResult.setPages(pages);
        pageResult.setPageSize(request.getPage());
        pageResult.setPageNum(request.getSize());
        pageResult.setList(list);
        return pageResult;
    }
}
