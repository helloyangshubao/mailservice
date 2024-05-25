package com.payc.tool.service.rabbit;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author siyong.dxh@taobao.com
 * @Date 2019/4/23 8:22 PM
 */
@Component
@Slf4j
public class RabbitReturnCallback implements RabbitTemplate.ReturnsCallback {
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.info("message return message:{}", JSONObject.toJSONString(returnedMessage));
    }
}
