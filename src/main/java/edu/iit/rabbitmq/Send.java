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
import static edu.iit.credentials.Credentials.QUEUENAME;
import static edu.iit.credentials.Credentials.RABBITMQ;

public class Send {


    /**
     *
     * @param message
     * @throws Exception
     */
    public void sendMessage(String message) throws Exception {
        
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        try {
            channel.queueDeclare(QUEUENAME, false, false, false, null);
        }
        catch(Exception e){
            System.out.println("Queue already exists, moving on");
        }
        
        channel.basicPublish("", QUEUENAME, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
    
}
