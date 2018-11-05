package com.study.activemq.consumer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSQueueConsumer {

    public static void main(String[] args) {
        recive();
    }
    public static void recive(){
        ConnectionFactory factory =  new ActiveMQConnectionFactory("tcp://192.168.171.128:61616?jms.useAsyncSend=true");
        Connection connection = null;
        try {
            //异步发送
            // ((ActiveMQConnectionFactory)factory).setUseAsyncSend(true);
            connection = factory.createConnection();
            //异步发送
            //((ActiveMQConnection) connection).setUseAsyncSend(true);
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("myQueue");
            MessageConsumer consumer = session.createConsumer(destination);

            //Text   Map  Bytes  Stream  Object
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
