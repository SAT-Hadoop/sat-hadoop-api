/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.rabbitmq;

import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author supramo
 */
public class Queue {
    
    @Test @Ignore
    public void sendMessage(){
        Send sendmessage = new Send();
        try {
            sendmessage.sendMessage("sai is awesome", "sai1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void getMessage(){
        Receive receivemessage = new Receive();
        try {
            System.out.println(receivemessage.getMessage("sai1"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
