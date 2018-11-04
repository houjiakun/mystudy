package com.study.activemq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSTopicProducer {
    public static void main(String[] args) {
        sendMsg("hello activeMQ Topic!");
    }

    public static void sendMsg(String msg){
        ConnectionFactory factory =  new ActiveMQConnectionFactory("tcp://192.168.171.128:61616");
        Connection connection = null;
        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
            Destination destination = session.createTopic("myTopic");
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(msg);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            producer.send(message);
            session.commit();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
