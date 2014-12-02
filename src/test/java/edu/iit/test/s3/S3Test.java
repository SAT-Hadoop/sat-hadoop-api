/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.test.s3;

import edu.iit.s3bucket.S3Bucket;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author supramo
 */
public class S3Test {
    
    public S3Test() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testS3() {
        S3Bucket sb1 = new S3Bucket();
        sb1.setBucketname(UUID.randomUUID() + "sai");
        if (!sb1.checkBucket()){
            sb1.createBucket();
        }    
    }
}
