/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.scheduler;

import edu.iit.sqs.SendQueue;

/**
 *
 * @author supramo
 */
public class Scheduler {
    
    /**
     *
     * @param jobid
     * @return
     */
    public static boolean submitJob(String jobid){
        SendQueue sendmessage = new SendQueue();
        //sendmessage.createQueue();
        sendmessage.sendMessage(jobid);
        return true;
    }
    
}
