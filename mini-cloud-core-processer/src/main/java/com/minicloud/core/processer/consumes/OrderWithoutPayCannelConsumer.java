package com.minicloud.core.processer.consumes;

//import cn.hutool.json.JSONUtil;
//import com.core.processer.dto.DelayQueneOrderDTO;
//import com.core.processer.mapper.TestOrderMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author alan.wang
 */
@Slf4j
@Component
@AllArgsConstructor
@RocketMQMessageListener(topic = "topic-order-without-pay", consumerGroup = "test_order_without_pay_group")
public class OrderWithoutPayCannelConsumer implements RocketMQListener<String> {


    @Override
    public void onMessage(String delayQueneOrderJson) {
//        RemoteSimulateOrderService remoteSimulateOrderService = SpringContextUtils.getBean(RemoteSimulateOrderService.class);
//        DelayQueneOrderDTO delayQueneOrderDTO = JSONUtil.toBean(delayQueneOrderJson, DelayQueneOrderDTO.class);
//        remoteSimulateOrderService.updateStatusToCancel(delayQueneOrderDTO.getOrderId());
//        System.out.println(delayQueneOrderJson+":"+ LocalDateTime.now());
    }


}
