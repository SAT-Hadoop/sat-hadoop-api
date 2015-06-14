/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import static edu.iit.credentials.Credentials.QUEUENAME;
import static edu.iit.credentials.Credentials.RABBITMQ;

/**
 *
 * @author supramo
 */
public abstract class Queue{
    
    /**
     *
     * @return
     */
    protected Channel getChannel(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ);
        Channel channel = null;
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUENAME, false, false, false, null);
        }
        catch(Exception e){
            System.out.println("Queue already exists, moving on");
        }
        return channel;
    }
    
}
