package com.hmall.trade.listerer;

import com.hmall.trade.domain.po.Order;
import com.hmall.trade.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PayStatusListener {

    private final IOrderService orderService;
    @RabbitListener(bindings =@QueueBinding(
            value =  @Queue(name = "trade.pay.success.queue",durable = "true"),
            exchange = @Exchange(name = "pay.direct"),
            key = {"pay.success"}
    ))
    public void listenPaySuccess(Long orderId){
        //查询订单
        Order byId = orderService.getById(orderId);
        //判断订单状态 是否为未支付
        if (byId == null || byId.getStatus() != 1){
            return;
        }
        orderService.markOrderPaySuccess(orderId);
    }
}
