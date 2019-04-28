package com.shibo.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer1 {
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "comment.queue", containerFactory = "jmsListenerContainerQueue")
    public void receiveQueue(String text) {
        System.out.println("Consumer111收到的报文queue为:" + text);
    }

    @JmsListener(destination = "comment.topic", containerFactory = "jmsListenerContainerTopic")
    public void receiveTopic(String text) {
        System.out.println("Consumer111收到的topic报文为:" + text);
    }
}
