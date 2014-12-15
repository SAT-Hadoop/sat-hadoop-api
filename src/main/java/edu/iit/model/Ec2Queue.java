/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.model;

/**
 *
 * @author supramo
 */
public class Ec2Queue {
    
    private String ec2ip;
    private String queuename;

    public String getEc2ip() {
        return ec2ip;
    }

    public void setEc2ip(String ec2ip) {
        this.ec2ip = ec2ip;
    }

    public String getQueuename() {
        return queuename;
    }

    public void setQueuename(String queuename) {
        this.queuename = queuename;
    }
    
    
}
