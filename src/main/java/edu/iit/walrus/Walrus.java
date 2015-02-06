/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.walrus;

import edu.iit.credentials.Credentials;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author supramo
 */
public class Walrus extends Credentials{
    public void createBucket(String bucketName){
        try {
            Runtime r = Runtime.getRuntime();
            r.exec("s3cfg -c "+S3CFG+" mb "+bucketName).waitFor();
        } catch (IOException|InterruptedException ex) {
            Logger.getLogger(Walrus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void putObject(String bucketName,String filePath){
        try {
            Runtime r = Runtime.getRuntime();
            r.exec("s3cfg -c "+S3CFG+" put "+filePath+" s3://"+bucketName).waitFor();
        } catch (IOException|InterruptedException ex) {
            Logger.getLogger(Walrus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delObject(String bucketName,String fileName){
        try {
            Runtime r = Runtime.getRuntime();
            r.exec("s3cfg -c "+S3CFG+" del s3://"+bucketName+"/"+fileName).waitFor();
        } catch (IOException|InterruptedException ex) {
            Logger.getLogger(Walrus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
