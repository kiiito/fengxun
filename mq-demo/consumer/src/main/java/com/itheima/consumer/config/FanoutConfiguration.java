package com.itheima.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfiguration {
    //交换机的声明
    @Bean
    public FanoutExchange fanoutExchange(){
//        return new FanoutExchange("hmall.fanout");
        return ExchangeBuilder.fanoutExchange("hmall.fanout").build();
    }
    //队列的声明
    @Bean
    public Queue fanoutQueue1(){
//        return new Queue("fanout.queue1");
        return QueueBuilder.durable("fanout.queue1").build();
    }
    //绑定关系
    @Bean
    public Binding fanoutQueue1Binding(Queue fanoutQueue1, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    //队列的声明
    @Bean
    public Queue fanoutQueue2(){
//        return new Queue("fanout.queue1");
        return QueueBuilder.durable("fanout.queue2").build();
    }
    //绑定关系
    @Bean
    public Binding fanoutQueue2Binding(Queue fanoutQueue2, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }
}
