package com.study.activemq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JMSQueueListenerConsumer {

    public static void main(String[] args) throws IOException {
        recive();
    }
    public static void recive() throws IOException {
        ConnectionFactory factory =  new ActiveMQConnectionFactory("tcp://192.168.171.128:61616");
        Connection connection = null;
        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("myQueue");
            MessageConsumer consumer = session.createConsumer(destination);
            MessageListener listener = new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println( ((TextMessage) message).getText());
                        session.commit();

                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            };
            consumer.setMessageListener(listener);
            session.commit();
            System.in.read();
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
