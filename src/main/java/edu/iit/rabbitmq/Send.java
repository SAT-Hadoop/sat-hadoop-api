/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.rabbitmq;

/**
 *
 * @author supramo
 */
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {


    /**
     *
     * @param message
     * @param queue
     * @throws Exception
     */
    public void sendMessage(String message,String queue) throws Exception {
        
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("64.131.111.91");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        try {
            channel.queueDeclare(queue, false, false, false, null);
        }
        catch(Exception e){
            System.out.println("Queue already exists, moving on");
        }
        
        channel.basicPublish("", queue, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
