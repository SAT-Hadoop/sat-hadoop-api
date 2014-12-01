/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.scheduler;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

/**
 *
 * @author supramo
 */
public class Schedule {
    AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();
    AmazonSQS sqs = new AmazonSQSClient(credentials);
    String myQueueUrl = null;
    public void createQueue(){
        CreateQueueRequest createQueueRequest = new CreateQueueRequest().withQueueName("MyQueue");
        myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();
        
    }
    public void printAllQueues(){
        for (String queueUrl : sqs.listQueues().getQueueUrls()) {
                System.out.println("  QueueUrl: " + queueUrl);
            }
            System.out.println();
    }
    public void sendMessage(){
        sqs.sendMessage(new SendMessageRequest(myQueueUrl, "This is my message text."));
    }
    
}
