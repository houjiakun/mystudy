package com.study.activemq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSTopicConsumer2 {

    public static void main(String[] args) {
        JMSTopicConsumer1.recive();
    }

}
