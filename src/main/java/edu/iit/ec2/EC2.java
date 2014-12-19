/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.ec2;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.IamInstanceProfile;
import com.amazonaws.services.ec2.model.IamInstanceProfileSpecification;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceState;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import edu.iit.credentials.Credentials;
import edu.iit.doa.DOA;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author supramo
 */
public class EC2 extends Credentials{
    AmazonEC2Client amazonEC2Client = new AmazonEC2Client(Credentials.getCreds());
    
    /**
     *
     */
    public void createInstances(){       
        amazonEC2Client.setEndpoint("ec2.us-west-2.amazonaws.com");
        Set<Instance> instances = new HashSet<Instance>();
        for (int i=0;i<NUM_WORKERS;i++){
            RunInstancesRequest runInstancesRequest = new RunInstancesRequest();
        	
            runInstancesRequest.withImageId("ami-b5a7ea85")
                     .withInstanceType("t2.micro")
                     .withMinCount(1)
                     .withMaxCount(1)
                     .withKeyName(KEY_NAME)
                    .withIamInstanceProfile( new IamInstanceProfileSpecification()
                    .withName( IAM_PROFILE_NAME ) );
                     //.withSecurityGroups("my-security-group");
            RunInstancesResult runInstancesResult = amazonEC2Client.runInstances(runInstancesRequest);
            try {
               Thread.sleep(5000);
               instances.addAll(runInstancesResult.getReservation().getInstances());
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            
        }
        
        //DescribeInstancesResult describeInstancesRequest = amazonEC2Client.describeInstances();
            //List<Reservation> reservations = describeInstancesRequest.getReservations();
            /*
            // add all instances to a Set.
            for (Reservation reservation : reservations) {
             instances.addAll(reservation.getInstances());
            }*/
            
            System.out.println("You have " + instances.size() + " Amazon EC2 instance(s).");
            DOA doa = new DOA();
            for (Instance ins : instances){
                if (ins.getKeyName().equals(KEY_NAME)){
                    System.out.println("DNS name is "+ ins.getPrivateIpAddress());
                    System.out.println("queue name is "+doa.getEc2SendQueue());
                    doa.updateEc2Queue(ins.getPrivateIpAddress(),doa.getEc2SendQueue());
                }
                
            }
    }
    
}
