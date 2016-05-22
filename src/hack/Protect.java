/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack;

import java.io.Serializable;
import static java.lang.System.exit;
import static java.lang.Thread.sleep;
import static javax.swing.UIManager.getCrossPlatformLookAndFeelClassName;
import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;
import javax.swing.UnsupportedLookAndFeelException;
import ru.epiclib.base.Base;

/**
 *
 * @author 1234
 */
public class Protect implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public int needPower;
    
    public boolean gateway;

    transient public Hacknet hacknet;
    
    public boolean open = false;
    
    public enum Type {ALPHA,ANTIHACK,ELLO,FIREWALL,PROTE,PROXY,SPRO,ZEUS};
    public Type type;
    
    //------------------------------
    
    public Protect(Type type, Hacknet hacknet) {
        this(type);
        this.hacknet = hacknet;
    }
    
    public Protect(Type type) {
        
        this.type = type;
        
        int needSeconds;
        
        switch(type) {
            case ALPHA :
                gateway = false;
                needSeconds = 60;
                break;
            case ANTIHACK :
                gateway = true;
                needSeconds = 45;
                break;
            case ELLO :
                gateway = false;
                needSeconds = 120;
                break;
            case FIREWALL :
                gateway = true;
                needSeconds = 45;
                break;
            case PROTE :
                gateway = true;
                needSeconds = 70;
                break;
            case PROXY :
                gateway = true;
                needSeconds = 30;
                break;
            case SPRO :
                gateway = false;
                needSeconds = 20;
                break;
            case ZEUS :
                gateway = true;
                needSeconds = 180;
                break;
            default :
                needSeconds = 0;
        }
        
        needPower = needSeconds*Computer.CPUS_POWER[1];
        
    }
    
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
            
            switch (type) {
                case ALPHA:
                    break;
                case ANTIHACK:
                    break;
                case ELLO:
                    break;
                case FIREWALL:
                    break;
                case PROTE:
                    break;
                case PROXY:
                    for (int i = needPower; i > 0; i = i - hacknet.user.powerCPU * 2) {
                        mh.print(Base.randomCombineString(16));
                        try {
                            sleep(500);
                        } catch (InterruptedException ex) {
                            exit(1);
                        }
                    }
                    break;
                case SPRO:
                    for (int i = needPower; i > 0; i = i - hacknet.user.powerCPU) {
                        mh.print(Base.randomBinary(16));
                        try {
                            sleep(250);
                        } catch (InterruptedException ex) {
                            exit(1);
                        }
                    }
                    break;
                case ZEUS:
                    break;
                default:
                    throw new Error("Protect.hack.switch:default!!!");
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
    
    public String printThis() {
        switch(type) {
            case ALPHA :
                return "Alpha";
            case ANTIHACK :
                return "Antihack";
            case ELLO :
                return "Ello";
            case FIREWALL :
                return "Firewall";
            case PROTE :
                return "Prote";
            case PROXY :
                return "Proxy";
            case SPRO :
                return "SPro";
            case ZEUS :
                return "Zeus";
            default :
                return "Unknown type";
        }
    }
    
}
