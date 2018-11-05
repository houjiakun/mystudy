package com.study.activemq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSTopicConsumer1 {

    public static void main(String[] args) {
        recive();
    }
    public static void recive(){
        ConnectionFactory factory =  new ActiveMQConnectionFactory("tcp://192.168.171.128:61616");
        Connection connection = null;
        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("myTopic");
            MessageConsumer consumer = session.createConsumer(destination);

            TextMessage receive = (TextMessage) consumer.receive();
            System.out.println(receive.getText());
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
