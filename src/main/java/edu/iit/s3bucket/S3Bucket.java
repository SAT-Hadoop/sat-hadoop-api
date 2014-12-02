package edu.iit.s3bucket;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.Region;
import edu.iit.credentials.Credentials;
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author supramo
 */
public class S3Bucket implements Credentials{
    
    AmazonS3 s3client = new AmazonS3Client(credentials);            
    String bucketname;
    public boolean checkBucket(){
        if (s3client.getBucketLocation(this.bucketname) != null)
            return true;
        else
            return false;
    }
    
    public void createBucket(){
        s3client.createBucket(this.bucketname, Region.US_West);
    }
    
    public void putObjectsToBucket(File file){
        s3client.putObject(new PutObjectRequest(this.bucketname, file.getName(), file));
    }

    public void setBucketname(String bucketname) {
        this.bucketname = bucketname;
    }
    
    
}
