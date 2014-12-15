/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.scheduler;

import edu.iit.message.Data;
import edu.iit.sqs.SendQueue;

/**
 *
 * @author supramo
 */
public class Scheduler {
    
    public boolean submitJob(Data data){
        SendQueue sendmessage = new SendQueue();
        if (!sendmessage.checkIfQueuesExist())
            sendmessage.createQueue();
        sendmessage.sendMessage(data);
        return true;
    }
    
}
