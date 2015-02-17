/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.test.walrus;

import edu.iit.walrus.Walrus;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author supramo
 */
public class WalrusTest {
    
    public WalrusTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        Walrus walrus = new Walrus();
        walrus.createBucket("sat-hadoop");
    
    }
}
