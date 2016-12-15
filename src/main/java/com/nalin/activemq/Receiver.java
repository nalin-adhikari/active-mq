/**
 * 
 */
package com.nalin.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author Nalin Adhikari
 * @since December 2016
 *
 */
public class Receiver {

	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;
	
	public Receiver() {}
	
	public void receiveMessage() {
		
		try {
			factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("SAMPLEQUEUE");
			consumer = session.createConsumer(destination);
			Message message = consumer.receive();
	
			if (message instanceof TextMessage) {
				TextMessage text = (TextMessage) message;
				System.out.println("Message is : " + text.getText());
			}
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

		public static void main(String[] args) {
			Receiver receiver = new Receiver();
			receiver.receiveMessage();
		}
}
