package com.minicloud.core.processer.consumes;

import cn.hutool.json.JSONUtil;
import com.minicloud.common.core.util.ResponseX;
import com.minicloud.common.log.config.SpringContextUtils;
import com.minicloud.goods.dto.GoodsItemSubDTO;
import com.minicloud.goods.feign.RemoteSimulateGoodsService;
import com.minicloud.order.feign.RemoteSimulateOrderService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @author alan.wang
 * 创建订单-消费者：主要是消费创建订单消息
 * 首先调用商品feign服务扣减库存
 * 成功：则调用订单feign服务更新订单状态为成功
 * 失败：则调用订单feign服务更新订单状态为失败
 * 报错：重试
 *
 * */
@Component
@RocketMQMessageListener(topic = "topic-create-order", consumerGroup = "consumes_group")
public class CreateOrderConsume implements RocketMQListener<String> {


    @Override
    public void onMessage(String orderJson) {


        //orderJson转对应订单dto
        List<GoodsItemSubDTO> goodsItemSubDTOS = JSONUtil.toList(JSONUtil.parseArray(orderJson), GoodsItemSubDTO.class);

        //获取订单id
        Long orderId = goodsItemSubDTOS.stream().map(GoodsItemSubDTO::getOrderId).findFirst().get();
//        RedisTemplate redisTemplate =(RedisTemplate) SpringContextUtils.getBean("redisTemplate");

        //获取RemoteSimulateGoodsService
        RemoteSimulateGoodsService remoteSimulateGoodsService = SpringContextUtils.getBean(RemoteSimulateGoodsService.class);

        //获取RemoteSimulateGoodsService
        RemoteSimulateOrderService remoteSimulateOrderService = SpringContextUtils.getBean(RemoteSimulateOrderService.class);

        //Boolean lock = redisTemplate.opsForValue().setIfAbsent("create-order-processing:" + orderId, "1", 30, TimeUnit.SECONDS);
        //ResponseX responseX = ResponseX.ok();
        //if (lock)

        //调用商品feign服务扣减库存
        ResponseX responseX = remoteSimulateGoodsService.goodsItemSub(orderId,goodsItemSubDTOS,"feign");

        //成功则更新订单状态为成功，失败则更新状态为失败
        if(responseX.getCode() ==0){
            remoteSimulateOrderService.statusToSuccess(orderId,"feign");
        }else {
            remoteSimulateOrderService.statusToFailed(orderId,responseX.getMsg(),"feign");
        }
    }
}

