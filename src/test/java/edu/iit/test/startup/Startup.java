package edu.iit.test.startup;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.iit.doa.DOA;
import edu.iit.ec2.EC2;
import edu.iit.sqs.SendQueue;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author supramo
 */
public class Startup {
    
    public Startup() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test  
    public void kickStart() {
    
        DOA doa = new DOA();
        doa.createTables();
        SendQueue sendqueue = new SendQueue();
        if (!sendqueue.checkIfQueuesExist()){
            System.out.println("Queues dont exist my friend");
            sendqueue.createQueue();
        }
        //EC2 ec2 = new EC2();
        //ec2.createInstances();
        
    }
    
    @Test @Ignore
    public void getQueueNames(){
        DOA doa = new DOA();
        System.out.println(doa.getEc2SendQueue());
        SendQueue sendqueue = new SendQueue();
        sendqueue.checkIfQueuesExist();
    }
}
