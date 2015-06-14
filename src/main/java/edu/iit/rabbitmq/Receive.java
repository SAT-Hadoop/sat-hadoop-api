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
import static edu.iit.credentials.Credentials.QUEUENAME;
import static edu.iit.credentials.Credentials.RABBITMQ;
/**
 *
 * @author supramo
 */
public class Receive extends Queue{
    public String getMessage() throws Exception{
        Channel channel = getChannel();
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUENAME, true, consumer);
        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        String message = new String(delivery.getBody(),"UTF-8");        
        return message;
    }
    
}
