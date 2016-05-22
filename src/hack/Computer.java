/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack;

import static hack.User.levelText;
import java.io.FileNotFoundException;
import java.io.Serializable;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.logging.Logger;
import ru.epiclib.base.Base;
import java.util.HashMap;
import static java.lang.Thread.sleep;
import static ru.epiclib.base.FileWorker.read;
import static java.lang.Thread.sleep;
import static ru.epiclib.base.FileWorker.read;
import static java.lang.Thread.sleep;
import static ru.epiclib.base.FileWorker.read;
import static java.lang.Thread.sleep;
import static ru.epiclib.base.FileWorker.read;


public class Computer implements Serializable {
    
    private static final long serialVersionUID = 3L;
    
    public static final int[] CPUS_POWER = {100,200,400,600,800,1600};
    public static final int[] EXPS = {8,14,22,48,80,190};
    public static final int[] LVL_TO_COMP = {1,8,17,24,32,43};
    public static final String[] TYPES = {"Old server","Local server","Small server","Normal server","Big server","Super server"};
    public static final String[] SUFFIX_NAME = {"workstation","station","base","frame","mainframe","grandframe"};
    
    private ArrayList<Protect> defenseList;
    private ArrayList<GFile> files;
    
    public int trace,exp;
    
    public String ip, nameComputer;
    
    public String prefix;
    
    public boolean hacked = false, virused = false;
    
    transient public AuthWindow aw;
    
    public HashMap<Integer,Log> listOfLog;
    
    //--------------------------------------------------------------------------
    
    public void addToFilesList(GFile file) {
        files.add(file);
    }
    
    public void addToDefenseList(Protect protect) {
        defenseList.add(protect);
    }
    
    public Protect getProtect(Protect.Type type) {
        
        int id = -1;

        for (int i = 0; i < defenseList.size(); i++) {
            Protect get = defenseList.get(i);
            if (get.type == type) {
                id = i;
                break;
            }
        }

        if (id != -1) {
            return defenseList.get(id);
        } else {
            return null;
        }
        
    }
    
    public boolean rmFile(GFile file, String userIP) {
        for (int i = 0; i < files.size(); i++) {
            GFile get = files.get(i);
            if(get.equals(file)) {
                listOfLog.put(listOfLog.size()-1, new Log(Log.Type.FILE_DELETED, userIP));
                files.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public void rmFile(String nameOfFile, String userIp) {
        rmFile(new GFile(nameOfFile), userIp);
    }
    
    public boolean hasProtect(int idOfProtect) {
        return false;
    }
    
    public boolean hasFile(String name) {
        return true;
    }
    
    public void setDeletedToLog(int keyOfLog) {
        listOfLog.get(keyOfLog).setDeleted(true);
    }
    public void setUndeletedToLog(int keyOfLog) {
        listOfLog.get(keyOfLog).setDeleted(false);
    }
    
    public void addFile(GFile file) {
        files.add(file);
    }
    
    public GFile getFile(int idInCollection) {
        return files.get(idInCollection);
    }
    
    public int sizeOfListFiles() {
        return files.size();
    }
    
    public boolean userCanHackThis() {
        int needOpen = defenseList.size();
        for (int i = 0; i < defenseList.size(); i++) {
            Protect get = defenseList.get(i);
            if (get.open || get.gateway) {
                needOpen--;
            }
        }
        if (needOpen == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public String print() {
        return TYPES[getType()] + ", name: \""+nameComputer+"\", ip: "+ip+", hacked: "+hacked;
    }
    
    public int getType() {
        switch(exp) {
            case 8:
                return 0;
            case 14:
                return 1;
            case 22:
                return 2;
            case 48:
                return 3;
            case 80:
                return 4;
            case 190:
                return 5;
            default:
                return 0;
        }
    }
    
    public Computer(int type, String nameComputer, String prefix) {
        exp = EXPS[type];
        ArrayList<Protect> df = new ArrayList<>();
        df.add(new Protect(Protect.Type.SPRO));
        if(type > 0) df.add(new Protect(Protect.Type.PROXY));
        if(type > 1) {
            df.add(new Protect(Protect.Type.FIREWALL));
            df.add(new Protect(Protect.Type.ANTIHACK));
        }
        if(type > 2) {
            df.add(new Protect(Protect.Type.ALPHA));
            df.add(new Protect(Protect.Type.PROTE));
        }
        if(type > 3) df.add(new Protect(Protect.Type.ELLO));
        if(type > 4) df.add(new Protect(Protect.Type.ZEUS));
        
        defenseList = df;
        
        listOfLog = new HashMap<>();
        
        switch(type) {
            case 0:
                trace = 0;
                break;
            case 1:
                trace = 240;
                break;
            case 2:
                trace = 120;
                break;
            case 3:
                trace = 60;
                break;
            case 4:
                trace = 30;
                break;
            case 5:
                trace = 10;
                break;
        }
        
        this.nameComputer = nameComputer + " " + SUFFIX_NAME[type];
        this.prefix = prefix;
        
        ArrayList<GFile> gf = new ArrayList<>();
        
        for (int i = 0; i < Base.randomNumber(7, 17); i++) {
            gf.add(new GFile(prefix+"-"+Base.randomNumber(101, 99999999)+".dat"));
        }
        
        files = gf;
        
        ip = genIP();
        
    }
    
    public Computer(int type, String nameComputer, String prefix, String ip) {
        this(type,nameComputer,prefix);
        this.ip = ip;
    }
    
    public static String genIP() {
        return Base.randomCombineString(5);
    }
    

    public void loadHacknetToProtect(Hacknet hacknet) {
        for (int i = 0; i < defenseList.size(); i++) {
            Protect get = defenseList.get(i);
            get.hacknet = hacknet;
        }
    }
    
    public boolean hasFile(GFile file) {
        boolean ret = false;
        for (int i = 0; i < files.size(); i++) {
            GFile get = files.get(i);
            if(get.equals(file)) {
                ret = true;
            }
        }
        return ret;
    }
    
    
    
    public String printScan() {
        String first = "Result of scan: \n"
                + " Admin: "+hacked+"\n"
                + " Name of computer: "+nameComputer+"\n"
                + " IP adress: "+ip+"\n"
                + " Protect systems: \n";
        String second = "";
        for (int i = 0; i < defenseList.size(); i++) {
            Protect get = defenseList.get(i);
            second += "     " + get.printThis() + "\n";
        }
        String third = " Trace: "+trace+"\n"
                + " Power: "+exp;
        
        return first + second + third;
        
    }

    public void hack(final User user) {

        Thread myThready = new Thread(() -> {
            MiniHacknet mh;
            mh = new MiniHacknet();
            mh.setVisible(true);
            
            aw.setUsername("admin");
            
            for (int i = exp * 2; i > 0; i--) {
                try {
                    String a = read("words.txt").split(" ")[i];
                    mh.print(a);
                    aw.setUsername("admin");
                    aw.setPass(a);
                } catch (FileNotFoundException ex) {
                    exit(1);
                }
                try {
                    sleep(500);
                } catch (InterruptedException ex) {
                    exit(1);
                }
            }
            mh.print("Complete hack of computer");
            hacked = true;
            user.exp += exp;
            mh.print("You get " + exp + " exp, and your level now : " + levelText(user.exp));
            
            try {
                sleep(3_000);
            } catch (InterruptedException ex) {
                exit(1);
            }
            mh.dispose();
            aw.dispose();
        });
        myThready.start();
        
    }
    private static final Logger LOG = Logger.getLogger(Computer.class.getName());
    
    
    
}
