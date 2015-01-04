/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.test.doa;

import edu.iit.doa.DOA;
import edu.iit.model.User_Jobs;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author supramo
 */
public class DOAtest {
    
    public DOAtest() {
    }
    @Test @Ignore
    public void testDOA() {
        DOA doa = new DOA();
        User_Jobs userjob = new User_Jobs();
                userjob.setInputurl("");
                userjob.setOutputurl("");
                userjob.setUserid("sai");
                userjob.setJobstatus("INITIAL");
                String randomId = UUID.randomUUID().toString();
                userjob.setJobid(randomId);
                System.out.println(userjob.toString());
                doa.addJob(userjob);
        
    }
}
