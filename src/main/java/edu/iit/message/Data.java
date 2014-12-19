/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.message;

/**
 *
 * @author supramo
 */
public class Data {
    
    private String numberOfNodes;
    private String linkToInputS3;
    private String linkToOutputS3;
    private String Id;

    /**
     *
     * @return
     */
    public String getId() {
        return Id;
    }

    /**
     *
     * @param Id
     */
    public void setId(String Id) {
        this.Id = Id;
    }
    
    /**
     *
     * @return
     */
    public String getNumberOfNodes() {
        return numberOfNodes;
    }

    /**
     *
     * @param numberOfNodes
     */
    public void setNumberOfNodes(String numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }

    /**
     *
     * @return
     */
    public String getLinkToInputS3() {
        return linkToInputS3;
    }

    /**
     *
     * @param linkToInputS3
     */
    public void setLinkToInputS3(String linkToInputS3) {
        this.linkToInputS3 = linkToInputS3;
    }

    /**
     *
     * @return
     */
    public String getLinkToOutputS3() {
        return linkToOutputS3;
    }

    /**
     *
     * @param linkToOutputS3
     */
    public void setLinkToOutputS3(String linkToOutputS3) {
        this.linkToOutputS3 = linkToOutputS3;
    }

    @Override
    public String toString() {
        return "Data{" + "numberOfNodes=" + numberOfNodes + ", linkToInputS3=" + linkToInputS3 + ", linkToOutputS3=" + linkToOutputS3 + ", Id=" + Id + '}';
    }
    
    
    
}
