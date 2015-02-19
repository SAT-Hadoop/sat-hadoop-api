/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.walrus;

import edu.iit.credentials.Credentials;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
            r.exec("s3cmd -c "+S3CFG+" mb s3://"+bucketName).waitFor();
        } catch (IOException|InterruptedException ex) {
            Logger.getLogger(Walrus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void putObject(String bucketName,String filePath){
        try {
            Runtime r = Runtime.getRuntime();
            Logger.getLogger(Walrus.class.getName()).log(Level.INFO, "file path is " + filePath + "  bucketname is "+ bucketName);
            r.exec("s3cmd -c "+S3CFG+" put "+filePath+" s3://"+bucketName).waitFor();
        } catch (IOException|InterruptedException ex) {
            Logger.getLogger(Walrus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delObject(String bucketName,String fileName){
        try {
            Runtime r = Runtime.getRuntime();
            r.exec("s3cmd -c "+S3CFG+" del s3://"+bucketName+"/"+fileName).waitFor();
        } catch (IOException|InterruptedException ex) {
            Logger.getLogger(Walrus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void downloadObject(String bucketName,String filename){
        try {
            Runtime r = Runtime.getRuntime();
            
            r.exec("s3cmd -c "+S3CFG+" get s3://"+bucketName+"/"+filename ).waitFor();//+" | awk -F'/' '{print $4}'");
            r.exec("s3cmd -c "+S3CFG+ "cp filename "+"/tmp/inputfile").waitFor();           
            
        } catch (IOException|InterruptedException ex) {
            Logger.getLogger(Walrus.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }
    
    public List getObjects(String bucketName){
        List datasets = new ArrayList();
        try {
            Runtime r = Runtime.getRuntime();
            
            Process p = r.exec("s3cmd -c "+S3CFG+" ls s3://"+bucketName );//+" | awk -F'/' '{print $4}'");
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        String line = "";			
                        System.out.println("we are printing");
			while ((line = reader.readLine())!= null) {
                            String[] words = line.split("/");
                            
                            System.out.println("objects are " + words[words.length-1]) ;
				datasets.add(words[words.length-1]);
			}
        } catch (IOException|InterruptedException ex) {
            Logger.getLogger(Walrus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datasets;
    }
    

    
}
