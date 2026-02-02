package com.itheima.publisher;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

@Test
    public void testSimpleQueues() {

    String queueName = "simple.queue";
    String message = "Hello World!113";
    //发送消息
    rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testWorkQueues() {

        String queueName = "work.queue";
        for (int i = 0; i < 50; i++) {
            String message = "Hello World!" + i;
             //发送消息
            rabbitTemplate.convertAndSend(queueName, message);
        }

    }

    @Test
    public void testFanoutQueues() {

        String ExchangeName = "hmall.fanout";
        String message = "Hello World!";
        //发送消息
        rabbitTemplate.convertAndSend(ExchangeName, null,message);
    }

    @Test
    public void testDirectQueues() {

        String ExchangeName = "hnmall.direct";
        String message = "黄色!";
        //发送消息
        rabbitTemplate.convertAndSend(ExchangeName, "yellow",message);
    }


    @Test
    public void testTopicQueues() {

        CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());
        cd.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("spring amqp 处理确认结果异常",ex);
            }

            @Override
            public void onSuccess(CorrelationData.Confirm result) {
                if (result.isAck()){
                    log.debug("消息发送成功");
                }else {
                    log.error("消息发送失败:reason:{}",result.getReason());
                }
            }
        });
        String ExchangeName = "hmall.topic";
        String message = "天气";
        //发送消息
        rabbitTemplate.convertAndSend(ExchangeName, "china.weather",message,cd);
    }

    @Test
    public void testSendObject() {
        HashMap<String, Object> msg = new HashMap<>();
        msg.put("name","jack");
        msg.put("age",18);
        //发送消息
        rabbitTemplate.convertAndSend("object.queue",msg);
    }

    @Test
    public void testSendDelayMessage() {
    rabbitTemplate.convertAndSend("normal.direct", "deal", "hi", message -> {
        // 设置延迟时间
         message.getMessageProperties().setExpiration("10000");
         return  message;
    });
    }
    @Test
    public void testSendDelayMessageByPlugin() {
        rabbitTemplate.convertAndSend("delay.direct", "deal", "hi", message -> {
            // 设置延迟时间
            message.getMessageProperties().setDelay(10000);
            return  message;
        });
    }
}