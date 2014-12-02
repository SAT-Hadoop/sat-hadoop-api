/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.scheduler;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import edu.iit.credentials.Credentials;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author supramo
 */
public class Schedule implements Credentials{
    private final AmazonSQS sqs = new AmazonSQSClient(credentials);
    List<Message> messages;
    String myQueueUrl;
    
    public void createQueue(){
        CreateQueueRequest createQueueRequest = new CreateQueueRequest().withQueueName("MyQueue");
        myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();    
        Region usWest2 = Region.getRegion(Regions.US_WEST_2);
        sqs.setRegion(usWest2);
    }
    
    public void printAllQueues(){
        for (String queueUrl : sqs.listQueues().getQueueUrls()) {
                System.out.println("  QueueUrl: " + queueUrl);
            }
            System.out.println();
    }
    
    public boolean checkIfQueuesExist(){
        if (sqs.listQueues().getQueueUrls().size() > 0)
            return true;
        else 
            return false;
    }
 
    public void printMessages(){
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
            messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
            for (Message message : messages) {
                System.out.println("  Message");
                System.out.println("    MessageId:     " + message.getMessageId());
                System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
                System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
                System.out.println("    Body:          " + message.getBody());
                for (Entry<String, String> entry : message.getAttributes().entrySet()) {
                    System.out.println("  Attribute");
                    System.out.println("    Name:  " + entry.getKey());
                    System.out.println("    Value: " + entry.getValue());
                }
            }
            System.out.println();
    }
    public void sendMessage(Object obj){
        sqs.sendMessage(new SendMessageRequest(myQueueUrl, obj.toString()));
    }
    
    public void deleteMessage(){
        String messageRecieptHandle = messages.get(0).getReceiptHandle();
        sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl, messageRecieptHandle));
    }
    
}