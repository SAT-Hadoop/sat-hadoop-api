package edu.iit.s3bucket;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration.Transition;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.MultiObjectDeleteException;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.Region;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.StorageClass;
import edu.iit.credentials.Credentials;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author supramo
 */
public class S3Bucket extends Credentials {

    AmazonS3 s3client = new AmazonS3Client(Credentials.getCreds());
    String bucketname;

    /**
     *
     * @return
     */
    public String getBucketName(){
        return bucketname;
    }

    /**
     *
     * @return
     */
    public boolean checkBucket() {
        try {
            s3client.getBucketLocation(this.bucketname);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     *
     */
    public void setRules() {
        Transition transToArchive = new Transition()
                .withDays(365)
                .withStorageClass(StorageClass.Glacier);

        BucketLifecycleConfiguration.Rule ruleArchiveAndExpire = new BucketLifecycleConfiguration.Rule()
                .withId("Archive and delete rule")
                .withTransition(transToArchive)
                .withExpirationInDays(3650)
                .withStatus(BucketLifecycleConfiguration.ENABLED.toString());

        List<BucketLifecycleConfiguration.Rule> rules = new ArrayList<BucketLifecycleConfiguration.Rule>();
        rules.add(ruleArchiveAndExpire);

        BucketLifecycleConfiguration configuration = new BucketLifecycleConfiguration()
                .withRules(rules);

// Save configuration.
        s3client.setBucketLifecycleConfiguration(this.bucketname, configuration);
    }

    /**
     *
     */
    public void createBucket() {

        s3client.createBucket(this.bucketname, Region.US_West);
        setRules();
    }

    /**
     *
     * @return
     */
    public boolean emptyBucket() {
        DeleteObjectsRequest multiObjectDeleteRequest = new DeleteObjectsRequest(this.bucketname);
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                .withBucketName(this.bucketname);
        List<KeyVersion> keys = new ArrayList<KeyVersion>();
        ObjectListing objectListing = s3client.listObjects(listObjectsRequest);
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            keys.add(new KeyVersion(objectSummary.getKey()));
        }
        multiObjectDeleteRequest.setKeys(keys);
        try {
            DeleteObjectsResult delObjRes = s3client.deleteObjects(multiObjectDeleteRequest);
            return true;

        } catch (MultiObjectDeleteException e) {
            return false;
        }
    }

    /**
     *
     * @param file
     */
    public void putObjectsToBucket(File file) {
        s3client.putObject(new PutObjectRequest(this.bucketname, file.getName(), file));
    }

    /**
     *
     * @param bucketname
     */
    public void setBucketname(String bucketname) {
        this.bucketname = bucketname + UUID.randomUUID();
    }

}
