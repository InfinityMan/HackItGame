/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack.protect;

import hack.Protect;
import hack.MiniHacknet;
import java.io.Serializable;
import static java.lang.System.exit;
import java.util.logging.Logger;
import static javax.swing.UIManager.getCrossPlatformLookAndFeelClassName;
import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import javax.swing.UnsupportedLookAndFeelException;
import static ru.epiclib.base.Base.randomBinary;
import static java.lang.Thread.sleep;
import static javax.swing.UIManager.setLookAndFeel;

/**
 *
 * @author 1234
 */
public class SPro extends Protect implements Serializable{

    /**
     *
     */
    public SPro() {
        needPower = 4000;
        id = 1;
        gateway = false;
    }
    
    

    @Override
    public void hackThis() {
        
        try {
            // Set System L&F
            setLookAndFeel(getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            try {
                // Set cross-platform Java L&F (also called "Metal")
                setLookAndFeel(getCrossPlatformLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                // handle exception
            }
            // handle exception
            // handle exception
            // handle exception
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            // handle exception
        }
        // handle exception
        // handle exception
        
        

        Thread myThready = new Thread( () -> {
            MiniHacknet mh;
            mh = new MiniHacknet();
            mh.setVisible(true);
            
            for (int i = needPower; i > 0; i = i - hacknet.user.powerCPU) {
                mh.print(randomBinary(16));
                try {
                    sleep(500);
                } catch (InterruptedException ex) {
                    exit(1);
                }
            }
            mh.print("Complete hack of SPro");
            hacknet.print("Complete hack of SPro");
            open = true;
            try {
                sleep(1_500);
            } catch (InterruptedException ex) {
                exit(1);
            }
            mh.dispose();
        });
        myThready.start();
    }

    @Override
    public String printThis() {
        return "SPro : "+open;
    }
    private static final Logger LOG = Logger.getLogger(SPro.class.getName());
    
}
