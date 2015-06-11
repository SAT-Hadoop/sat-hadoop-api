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
    
    @Test 
    public void sendMessage(){
        Send sendmessage = new Send();
        try {
            sendmessage.sendMessage("jeremy is awesome");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Test 
    public void getMessage(){
        Receive receivemessage = new Receive();
        try {
            System.out.println();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Test @Ignore
    public void deleteMessage(){
        
    }
    
}
