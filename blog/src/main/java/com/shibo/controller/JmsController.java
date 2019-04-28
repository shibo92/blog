package com.shibo.controller;

import com.shibo.jms.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Destination;

/**
 * @author shibo
 */
@Controller
@Slf4j
@RequestMapping(value = "jms")
public class JmsController {
    @Autowired
    private Producer producer;

    @GetMapping(value = {"/test/queue"})
    @ResponseBody
    public String queue() {
        Destination destination = new ActiveMQQueue("comment.queue");
        for (int i = 0; i < 5; i++) {
            producer.sendMsg(destination,"send queue number is : " + i);
        }
        return "success";
    }

    @GetMapping(value = {"/test/topic"})
    @ResponseBody
    public String topic() {
        Destination destination = new ActiveMQTopic("comment.topic");
        for (int i = 0; i < 5; i++) {
            producer.sendMsg(destination,"send topic number is : " + i);
        }
        return "success";
    }
}