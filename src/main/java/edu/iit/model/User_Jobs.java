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
public class User_Jobs {
    
    private String userid;
    private String inputurl;
    private String outputurl;
    private String jobid;
    private String jobstatus;
    private String nodes;
    private String jobname;

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }
    
    

    /**
     *
     * @return
     */
    public String getUserid() {
        return userid;
    }

    /**
     *
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     *
     * @return
     */
    public String getInputurl() {
        return inputurl;
    }

    /**
     *
     * @param inputurl
     */
    public void setInputurl(String inputurl) {
        this.inputurl = inputurl;
    }

    /**
     *
     * @return
     */
    public String getOutputurl() {
        return outputurl;
    }

    /**
     *
     * @param outputurl
     */
    public void setOutputurl(String outputurl) {
        this.outputurl = outputurl;
    }

    /**
     *
     * @return
     */
    public String getJobid() {
        return jobid;
    }

    /**
     *
     * @param jobid
     */
    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    /**
     *
     * @return
     */
    public String getJobstatus() {
        return jobstatus;
    }

    /**
     *
     * @param jobstatus
     */
    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    @Override
    public String toString() {
        return "User_Jobs{" + "userid=" + userid + ", inputurl=" + inputurl + ", outputurl=" + outputurl + ", jobid=" + jobid + ", jobstatus=" + jobstatus + '}';
    }
    
    
    
}
