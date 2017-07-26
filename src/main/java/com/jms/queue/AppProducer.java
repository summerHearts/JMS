package com.jms.queue;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppProducer {
	private static final String url="tcp://10.253.177.16:61616";
	private static final String queueName="queue-test";

	public static void main(String[] args) throws JMSException {
		//1.创建连接工程ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		
		//2. 创建Connection
		Connection connection = connectionFactory.createConnection();
		
		//3.启动连接
		connection.start();
		
		//4.创建会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//5.创建目的地
		 //Queue createQueue = session.createQueue(queueName);
		Destination destination = session.createQueue(queueName);
		
		//6.创建生产者
		MessageProducer producer = session.createProducer(destination);
		
		//7.发送消息
		for (int i = 0; i < 100; i++) {
			//1.创建消息
			TextMessage textMessage = session.createTextMessage("test queue message"+ i);		
			//2.发送消息
			producer.send(textMessage);
			//log
			System.out.println("发送消息 ：" + textMessage.getText());
			
		}		
		//8.关闭连接
		session.close();
		
	}
}
