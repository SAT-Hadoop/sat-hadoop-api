/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.credentials;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

/**
 *
 * @author supramo
 */
public abstract class Credentials {
    
    public static AWSCredentials getCreds(){
        AWSCredentials credentials;
        try {
            credentials = new ProfileCredentialsProvider().getCredentials();
        }
        catch(Exception e){
            credentials = new InstanceProfileCredentialsProvider().getCredentials();
        }
        
        return credentials;
    }
    
        public final AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();
        public final int NUM_WORKERS = 3; 
        public final String[] SENDQUEUENAMES = {"sai1","sai2","sai3"};
        public final String[] RECQUEUENAMES = {"pramod1"};
        public final String  ACCOUNTID = "961412573847";
        public final String SQLURL = "https://sqs.us-east-1.amazonaws.com/" + ACCOUNTID + "/";//https://sqs.us-east-1.amazonaws.com/961412573847/
}
