package com.payc.tool.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Bean复制工具类
 * 基于Dozer和Jackson
 *
 * @author v-zhufeng001 on 2020/3/13 11:40
 */
@Slf4j
public class BeanUtil {
    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);
    private static final ObjectMapper objectMapper = createObjectMapper();

    private static final Mapper dozer = DozerBeanMapperBuilder.buildDefault();

    /**
     * 构建Jackson转换对象
     *
     * @return Jackson转换对象
     */
    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        //反序列化的时候如果多了其他属性,不抛出异常  
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //如果是空对象的时候,不抛异常  
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 值null的字段不作序列化
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    public static <T> T sourceToTarget(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }
        T targetObject = null;
        try {
            targetObject = target.newInstance();
            BeanUtils.copyProperties(source, targetObject);
        } catch (Exception e) {
            logger.error("convert error ", e);
        }
        return targetObject;
    }

    /**
     * 将源对象转换为指定类的实例
     * 基于Dozer
     *
     * @param source           源对象
     * @param destinationClass 目标对象Class
     * @return 目标对象实例
     */
    public static <T> T mapByDozer(Object source, Class<T> destinationClass) {
        if (null == source || null == destinationClass) {
            return null;
        }
        return dozer.map(source, destinationClass);
    }

    /**
     * 将源对象转换为指定类的实例队列
     * 基于Dozer
     *
     * @param sourceList       源对象队列
     * @param destinationClass 目标对象Class
     * @return 目标对象实例列表
     */
    public static <T, R> List<T> mapListByDozer(Collection<R> sourceList, Class<T> destinationClass) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Collections.emptyList();
        }

        ArrayList<T> destinationList = new ArrayList<>(sourceList.size());

        T destinationObject;
        for (Object sourceObject : sourceList) {
            destinationObject = dozer.map(sourceObject, destinationClass);
            if (destinationObject != null) {
                destinationList.add(destinationObject);
            }
        }

        return destinationList;
    }

    /**
     * 将源对象字段复制到目标对象对应字段上
     * 源对象和目标对象不可为null
     * 基于Dozer
     *
     * @param source            源对象
     * @param destinationObject 目标对象
     */
    public static void copyByDozer(Object source, Object destinationObject) {
        if (null == source || null == destinationObject) {
            return;
        }
        dozer.map(source, destinationObject);
    }


    /**
     * 将源对象转换为指定类的实例
     * 基于Jackson
     * <p>
     * 备注: 当source的类型和destinationClass一致时
     * 本函数会直接返回source, 此种情况下请考虑使用mapByDozer
     *
     * @param source           源对象
     * @param destinationClass 目标对象Class
     * @return 目标对象实例
     */
    @Deprecated
    public static <T> T mapByJson(Object source, Class<T> destinationClass) {
        if (null == source || null == destinationClass) {
            return null;
        }
        return objectMapper.convertValue(source, destinationClass);
    }


    /**
     * 将源对象集合转换为目标对象集合
     *
     * @param sourceList    源对象集合
     * @param typeReference 目标对象集合Class
     * @return 目标对象集合
     */
    @Deprecated
    public static <T extends Collection<?>, R> T mapCollectionByJson(Collection<R> sourceList,
                                                                     TypeReference<T> typeReference) {
        if (null == sourceList || null == typeReference) {
            return null;
        }
        return objectMapper.convertValue(sourceList, typeReference);
    }

}
