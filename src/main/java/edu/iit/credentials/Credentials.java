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

    /**
     *
     * @return
     */
    public static AWSCredentials getCreds() {
        AWSCredentials credentials;
        try {
            credentials = new ProfileCredentialsProvider().getCredentials();
        } catch (Exception e) {
            credentials = new InstanceProfileCredentialsProvider().getCredentials();
        }

        return credentials;
    }

    
    public static final String KEY_NAME = "itmo562";
    
    public static final String IAM_PROFILE_NAME = "developer";
    /**
     *
     */
    public static final int NUM_WORKERS = 3;

    /**
     *
     */
    public static final String[] SENDQUEUENAMES = {"sai4", "sai2", "sai3"};

    /**
     *
     */
    public static final String[] RECQUEUENAMES = {"pramod1"};

    /**
     *
     */
    public static final String ACCOUNTID = "961412573847";

    /**
     *
     */
    public static final String SQLURL = "https://sqs.us-east-1.amazonaws.com/" + ACCOUNTID + "/";
    
    //public final String S3CFG = "/root/s3cfg";\
    public static final String S3CFG = "/root/s3cfg";
    
    public static final String THEPATH = "/vol-01/";
    
    public static final String[] ELASTICMQ = {"http://64.131.111.91:9324", "http://64.131.111.91:9324","http://64.131.111.91:9324"};
    
}
