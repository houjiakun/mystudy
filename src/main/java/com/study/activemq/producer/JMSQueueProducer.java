package com.study.activemq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSQueueProducer {
    public static void main(String[] args) {
        sendMsg("hello activeMQ Queue!");
    }

    public static void sendMsg(String msg){
        ConnectionFactory factory =  new ActiveMQConnectionFactory("tcp://192.168.171.128:61616");
        Connection connection = null;
        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("myQueue");
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(msg);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
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
