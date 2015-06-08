/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
/**
 *
 * @author supramo
 */
public class Receive {
    public String getMessage(String queue) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("64.131.111.91");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queue, true, consumer);
        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
        String message = new String(delivery.getBody(),"UTF-8");
        System.out.println("This message is " + message);
        return message;
    }
    
}
