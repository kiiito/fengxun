package com.itheima.consumer.mq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

@Slf4j
@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "simple.queue")
    public void ListenSimple(String  message){
        log.info("监听到 message:{}",message);
    }


    @RabbitListener(queues = "work.queue")
    public void ListenWork1(String  message) throws InterruptedException {
        System.out.println("消费者1 接收到消息" + message + "," + LocalTime.now());
        Thread.sleep(25);
    }
    @RabbitListener(queues = "work.queue")
    public void ListenWork2(String  message) throws InterruptedException {
        System.err.println("消费者22222222 接收到消息" + message + "," + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void ListenQ1(String  message){
        log.info("消费者1 接收fanout.queue1到消息" + message);
    }
    @RabbitListener(queues = "fanout.queue2")
    public void ListenQ2(String  message){
        log.info("消费者2 接收fanout.queue2到消息" + message);
    }
    @RabbitListener(bindings = @QueueBinding(
          value =  @Queue(name = "direct.queue1", durable = "true"),
            exchange = @Exchange(name = "hmall.direct",type = ExchangeTypes.DIRECT),
            key = {"red","blue"}
    ))
    public void ListenQD1(String  message){
        log.info("消费者1 接收direct.queue1到消息" + message);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2", durable = "true"),
            exchange = @Exchange(name = "hmall.direct"),
            key = {"red","yellow"}
    ))
    public void ListenQD2(String  message){
        log.info("消费者2 接收direct.queue2到消息" + message);
    }

    @RabbitListener(queues = "topic.queue1")
    public void ListenQT1(String  message){
        log.info("消费者1 接收topic.queue1到消息" + message);
    }
    @RabbitListener(queues = "topic.queue2")
    public void ListenQT2(String  message){
        log.info("消费者2 接收topic.queue2到消息" + message);
    }
    @RabbitListener(queues = "object.queue")
    public void ListenObject(Message message){
        log.info("消费者2 接收object.queue到消息" , message);
        log.info("消息内容:{}",message.getBody());
        log.info("消息内容 id:{}",message.getMessageProperties().getMessageId());
    }
    @RabbitListener(bindings = @QueueBinding(
            value =  @Queue(name = "dlx.queue", durable = "true"),
            exchange = @Exchange(name = "dlx.direct",type = ExchangeTypes.DIRECT),
            key = {"deal"}
    ))
    public void ListenDlxQueue(String  message){
        log.info("消费者1 接收dlx.queue到消息{}" , message);
    }
    @RabbitListener(bindings = @QueueBinding(
            value =  @Queue(name = "delay.queue", durable = "true"),
            exchange = @Exchange(name = "delay.direct",delayed = "true"),
            key = {"deal"}
    ))
    public void ListenDelayQueue(String  message){
        log.info("消费者1 delay.queue到消息{}" , message);
    }
}
