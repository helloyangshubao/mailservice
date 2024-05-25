package com.payc.tool.service.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author siyong.dxh@taobao.com
 * @Date 2019/4/23 8:22 PM
 */
@Component
@Slf4j
public class RabbitConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("MQ-ConfirmCallback-数据:{}-结果:{}-原因:{}", correlationData, ack, cause);
    }
}
