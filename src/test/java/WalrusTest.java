/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.iit.doa.DOA;
import edu.iit.elasticmq.Queue;
import edu.iit.walrus.Walrus;
import java.net.Inet4Address;
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
        String ipaddress,queuename;
        try {
            ipaddress = Inet4Address.getLocalHost().getHostAddress();
            queuename = doa.getEc2Queue(ipaddress);
            Queue queue = new Queue();
            queue.sendMessage("sai is awesome");
            if (queue.checkForMessages(queuename)){
                System.out.println(queue.getMessage().getBody());
            }
            else
                System.out.println("could not fetch anything");
        }
        catch(Exception e){
            System.out.println("Somethig is wrong");
        }
        //doa.createTables();
    }
}
