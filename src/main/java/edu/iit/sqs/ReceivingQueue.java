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
import edu.iit.credentials.Credentials;
import static edu.iit.credentials.Credentials.credentials;
import java.util.List;

/**
 *
 * @author supramo
 */
public class ReceivingQueue implements Credentials{
    
    private final AmazonSQS sqs = new AmazonSQSClient(credentials);
    List<Message> messages;
    String myQueueUrl;
    
    public void createQueue(){
        for (int i=0;i<RECQUEUENAMES.length;i++){
            CreateQueueRequest createQueueRequest = new CreateQueueRequest().withQueueName(RECQUEUENAMES[i]);
            myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();    
            Region usWest2 = Region.getRegion(Regions.US_WEST_2);
            sqs.setRegion(usWest2);
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
    
    
    
}
