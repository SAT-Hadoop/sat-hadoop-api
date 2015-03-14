/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.test.doa;

import edu.iit.doa.DOA;
import edu.iit.model.User_Jobs;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
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
        List slaves = doa.getSlaves(1);
        System.out.println(slaves.get(0));
        
        
    }
}
