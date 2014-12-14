/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.test.doa;

import edu.iit.doa.DOA;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author supramo
 */
public class DOAtest {
    
    public DOAtest() {
    }
    @Test
    public void testDOA() {
        DOA d = new DOA();
        d.makeConnection();
        d.createTables();
    }
}
