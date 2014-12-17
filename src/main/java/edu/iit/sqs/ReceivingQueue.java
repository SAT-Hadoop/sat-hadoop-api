/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sqs;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import edu.iit.credentials.Credentials;
import edu.iit.doa.DOA;
import java.util.List;
import java.util.Map;

/**
 *
 * @author supramo
 */
public class ReceivingQueue extends Credentials{
    
    private final AmazonSQS sqs = new AmazonSQSClient(Credentials.getCreds());
    List<Message> messages;
    String myQueueUrl;
    
    public void createQueue(){
        for (int i=0;i<RECQUEUENAMES.length;i++){
            CreateQueueRequest createQueueRequest = new CreateQueueRequest().withQueueName(RECQUEUENAMES[i]);
            myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();    
            Region usWest2 = Region.getRegion(Regions.US_WEST_2);
            sqs.setRegion(usWest2);
            DOA doa = new DOA();
            doa.addEc2Queue(myQueueUrl, "");
        }
    }
    public boolean checkIfQueuesExist(){
        int count = 0;
        if (sqs.listQueues().getQueueUrls().size() > 0){
            for (int i=0;i<sqs.listQueues().getQueueUrls().size();i++){
                if (sqs.listQueues().getQueueUrls().contains(RECQUEUENAMES[count]))
                    count++;
            }
        }
        if (count == (RECQUEUENAMES.length+1))
            return true;
        else
            return false;
    }
    public void printMessages(){
        for (int i=0;i<RECQUEUENAMES.length;i++){
            ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(RECQUEUENAMES[i]);
            messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
            for (Message message : messages) {
                System.out.println("  Message");
                System.out.println("    MessageId:     " + message.getMessageId());
                System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
                System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
                System.out.println("    Body:          " + message.getBody());
                for (Map.Entry<String, String> entry : message.getAttributes().entrySet()) {
                    System.out.println("  Attribute");
                    System.out.println("    Name:  " + entry.getKey());
                    System.out.println("    Value: " + entry.getValue());
                }
            }
            System.out.println();   
        }
    }
    
    
}
