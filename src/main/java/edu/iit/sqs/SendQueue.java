/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import edu.iit.credentials.Credentials;
import edu.iit.doa.DOA;
import edu.iit.util.MathFunc;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author supramo
 */
public class SendQueue extends Credentials{
    private final AmazonSQS sqs = new AmazonSQSClient(Credentials.getCreds());
    List<Message> messages;
    String myQueueUrl;
    
    /**
     *
     */
    public void createQueue(){
        try {
            int queuetocreate = SENDQUEUENAMES.length;// - sqs.listQueues().getQueueUrls().size();
            
            for (int i=0;i<queuetocreate;i++){
            System.out.println("creating queue : " + SENDQUEUENAMES[i]);
            CreateQueueRequest createQueueRequest = new CreateQueueRequest().withQueueName(SENDQUEUENAMES[i]);
            myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();    
            //Region usWest2 = Region.getRegion(Regions.US_WEST_2);
            //sqs.setRegion(usWest2);
            DOA doa = new DOA();
            doa.addEc2Queue("", SENDQUEUENAMES[i]);
            }
        }
        catch(Exception e){
            System.out.println("Could not create queue, please try again");
            System.exit(1);
        }
        
        
    }
    
    /**
     *
     */
    public void printAllQueues(){
        for (String queueUrl : sqs.listQueues().getQueueUrls()) {
                System.out.println("  QueueUrl: " + queueUrl);
            }
            System.out.println();
    }
    
    /**
     *
     * @return
     */
    public boolean checkIfQueuesExist(){
        int count = 0;
        if (sqs.listQueues().getQueueUrls().size() > 0){
            System.out.println("The size is " + sqs.listQueues().getQueueUrls().size());
            for (int i=0;i<sqs.listQueues().getQueueUrls().size();i++){
                for (int j=0;j<SENDQUEUENAMES.length;j++){
                    if (sqs.listQueues().getQueueUrls().get(i).contains(SENDQUEUENAMES[j]))
                        count++;
                }
                
            }
        }
        System.out.println("The count is "+ count);
        if (count == (SENDQUEUENAMES.length))
            return true;
        else
            return false;
    }
 
    /**
     *
     */
    public void printMessages(){
        for (int i=0;i<SENDQUEUENAMES.length;i++){
            ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(SENDQUEUENAMES[i]);
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
    }

    /**
     *
     * @param message
     */
    public void sendMessage(String message){
        int selection = MathFunc.randInt(0, SENDQUEUENAMES.length-1);
        try{
            sqs.sendMessage(new SendMessageRequest(SQLURL+SENDQUEUENAMES[selection], message));
        }
        catch(Exception e){
            System.out.println("could not send message");
        }
        
    }
    
    /**
     *
     * @param message
     */
    public void deleteMessage(Message message, String queue){
        String messageRecieptHandle = message.getReceiptHandle();
        sqs.deleteMessage(new DeleteMessageRequest(queue, messageRecieptHandle));
    }
    
    public Message getMessage(){
        return messages.get(0);
    }
    
    /**
     *
     * @param queuename
     * @return
     */
    public boolean checkForMessages(String queuename){
        System.out.println("queuename is " + queuename);
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queuename);
        this.messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        //System.out.println(messages.get(0).getBody());
        System.out.println("size is " + messages.size());
        if (messages.isEmpty())
            return false;
        else
            return true;
    }
    
}