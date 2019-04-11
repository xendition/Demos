package com.michael.demo.rabbitmq.service;

import com.michael.demo.rabbitmq.conf.RabbitConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Michael
 */
@Component
@RabbitListener(queues = RabbitConfiguration.QUEUE_A)
public class MsgReceiver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String content) {
        this.logger.info("处理器1接收处理队列A当中的消息： " + content);
    }

}
