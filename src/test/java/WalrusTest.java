/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.iit.doa.DOA;
import edu.iit.walrus.Walrus;
import java.util.ArrayList;
import java.util.List;
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
    public void displayObjects() {
        DOA doa = new DOA();
        doa.createTables();
    }
}
