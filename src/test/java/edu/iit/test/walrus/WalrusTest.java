/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.test.walrus;

import edu.iit.walrus.Walrus;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author supramo
 */
public class WalrusTest {
    
    public WalrusTest() {
    }
    @Test @Ignore
    public void hello() {
        Walrus walrus = new Walrus();
        //walrus.createBucket("sat-hadoop
        walrus.getObjects("sat-hadoop");
        walrus.downloadObject("sat-hadoop", "IssueBoardingPass-2.pdf");
    
    }
}
