/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack.protect;

import hack.MiniHacknet;
import hack.Protect;
import java.io.Serializable;
import static java.lang.System.exit;
import static javax.swing.UIManager.getCrossPlatformLookAndFeelClassName;
import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import javax.swing.UnsupportedLookAndFeelException;
import ru.epiclib.base.Base;
import static java.lang.Thread.sleep;
import static javax.swing.UIManager.setLookAndFeel;

/**
 *
 * @author Dima
 */
public class Proxy extends Protect implements Serializable{
    
    
    public Proxy() {
        needPower = 6000;
        id = 2;
        gateway = true;
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
            
            for (int i = needPower; i > 0; i = i - hacknet.user.powerCPU*2) {
                mh.print(Base.randomCombineString(16));
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    exit(1);
                }
            }
            mh.print("Complete hack of Proxy");
            hacknet.print("Complete hack of Proxy");
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
        return "Proxy : "+open;
    }
    
}
