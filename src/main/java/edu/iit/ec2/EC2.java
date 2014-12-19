/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.ec2;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
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
        
        for (int i=0;i<NUM_WORKERS;i++){
            RunInstancesRequest runInstancesRequest = new RunInstancesRequest();
        	
            runInstancesRequest.withImageId("ami-4b814f22")
                     .withInstanceType("m1.small")
                     .withMinCount(1)
                     .withMaxCount(1)
                     .withKeyName("itmohadoop");
                     //.withSecurityGroups("my-security-group");
            RunInstancesResult runInstancesResult = amazonEC2Client.runInstances(runInstancesRequest);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(EC2.class.getName()).log(Level.SEVERE, null, ex);
        }
        DescribeInstancesResult describeInstancesRequest = amazonEC2Client.describeInstances();
            List<Reservation> reservations = describeInstancesRequest.getReservations();
            Set<Instance> instances = new HashSet<Instance>();
            // add all instances to a Set.
            for (Reservation reservation : reservations) {
             instances.addAll(reservation.getInstances());
            }
            
            System.out.println("You have " + instances.size() + " Amazon EC2 instance(s).");
            DOA doa = new DOA();
            for (Instance ins : instances){
                doa.updateEc2Queue(doa.getEc2queue(), ins.getPublicDnsName());
            }
    }
    
}
