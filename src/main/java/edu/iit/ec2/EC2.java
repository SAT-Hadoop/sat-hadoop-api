/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.ec2;

import com.amazonaws.services.ec2.AmazonEC2Client;
import edu.iit.credentials.Credentials;

/**
 *
 * @author supramo
 */
public class EC2 extends Credentials{
    AmazonEC2Client amazonEC2Client = new AmazonEC2Client(Credentials.getCreds());
    public void createInstances(){
        
        for (int i=0;i<NUM_WORKERS;i++){
            
        }
        
    }
    
}
