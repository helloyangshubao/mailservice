package com.payc.tool.domain.entity;



import com.payc.tool.constants.MongodbConstant;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * demo
 *
 * @author yangshubao
 * @date 2020/2/15 10:08
 */
@Data
@Document(collection = MongodbConstant.DEMO)
public class DemoMongoEntity {

    /**
     * demo1
     */
    private String demo1;

    /**
     * demo2
     */
    private String demo2;

    /**
     * demo3
     */
    private String demo3;

}
