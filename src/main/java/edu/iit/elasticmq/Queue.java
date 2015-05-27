/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.elasticmq;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import edu.iit.credentials.Credentials;
import static edu.iit.credentials.Credentials.SENDQUEUENAMES;
import static edu.iit.credentials.Credentials.SQLURL;
import edu.iit.util.MathFunc;
import java.util.List;

/**
 *
 * @author supramo
 */
public class Queue extends Credentials{
    
    private final AmazonSQS sqs = new AmazonSQSClient(Credentials.getCreds());
    List<Message> messages;
    
    public void sendMessage(String message){
        int selection = MathFunc.randInt(0, ELASTICMQ.length-1);
        try{
            sqs.sendMessage(new SendMessageRequest(ELASTICMQ[selection], message));
        }
        catch(Exception e){
            System.out.println("could not send message");
        }
        
    }
    
    public boolean checkForMessages(String queuename){
        System.out.println("queuename is " + queuename);
        try {
            ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queuename);
        this.messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        //System.out.println(messages.get(0).getBody());
        System.out.println("size is " + messages.size());
        if (messages.isEmpty())
            return false;
        else
            return true;
        }
        catch(Exception e){
            return false;
        }
        
    }
    
    public Message getMessage(){
        return messages.get(0);
    }
    
}
