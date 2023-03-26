package com.minicloud.core.processer.consumes;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author alan.wang
 */
@Component
@RocketMQMessageListener(topic = "test-topic", consumerGroup = "test_group")
public class TestConsume implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
       System.out.println(s);
    }
}
