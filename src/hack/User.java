/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.logging.Logger;
import static ru.epiclib.base.Base.deserData;
import static ru.epiclib.base.Base.serData;
import static ru.epiclib.base.Base.stringToInt;
import static ru.epiclib.base.FileWorker.read;
import static ru.epiclib.base.FileWorker.read;
import static ru.epiclib.base.FileWorker.read;
import static ru.epiclib.base.FileWorker.read;


public class User implements Serializable {
    
    private static final long serialVersionUID = 3L;
    
    //computer:
    
    public ArrayList<Software> soft;
    public ArrayList<GFile> files;
    public int powerCPU = Computer.CPUS_POWER[1];
    
    public String ip;
    
    //end
    
    //user txts:
    
    public String userPass = "AU0001";
    public String nick = "-167458";
    
    //end
    
    public int exp;
    
    public int gettedContractsNumber = 0;
    
    public ArrayList<Contract> availableContracts;
    public ArrayList<Contract> currentContracts;
    
    public User(String nick,String userPass, int exp, int getCon) {
        this.nick = nick;
        this.userPass = userPass;
        this.exp = exp;
        this.gettedContractsNumber = getCon;
        
        ip = Computer.genIP();
        
        availableContracts = new ArrayList<>();
        currentContracts = new ArrayList<>();
    }

    public static User load(Hacknet hacknet) throws IOException {
        User loadedUser;

        loadedUser = (User) deserData("hAcKsave.hsf");
        hacknet.print("Save loaded: " + loadedUser.nick);

        return loadedUser;
    }
    
    public void save() {
        serData("hAcKsave.hsf", this);
    }
    
    public Contract searchForId(int id) {
        for (int i = 0; i < currentContracts.size(); i++) {
            Contract get = currentContracts.get(i);
            if(get.id == id) {
                return get;
            }
        }
        return null;
    }
    
    public void addMission(Contract contr) {
        currentContracts.add(contr);
    }
    
    /**
     *
     * @param exp
     * @return
     */
    public static int levelNum(int exp) {
        String[] expToLevelStr = null;
        try {
            expToLevelStr = read("level.txt").split("\n")[0].split(" ");
        } catch (FileNotFoundException ex) {
            exit(1);
        }
        int[] expToLevel = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for (int i = 0; i < expToLevelStr.length; i++) {
            expToLevel[i] = stringToInt(expToLevelStr[i]);  
        }
        int level = 0, currentExp = exp;
        while(currentExp >= expToLevel[level] && level != 58) {
            currentExp -= expToLevel[level];
            level++;
        }
        
        return level;
    }
    
    /**
     *
     * @param exp
     * @return
     */
    public static int toNextLevelExp(int exp) {
        String[] expToLevelStr = null;
        try {
            expToLevelStr = read("level.txt").split("\n")[0].split(" ");
        } catch (FileNotFoundException ex) {
            exit(1);
        }
        int[] expToLevel = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for (int i = 0; i < expToLevelStr.length; i++) {
            expToLevel[i] = stringToInt(expToLevelStr[i]);  
        }
        int level = 0, currentExp = exp;
        while(currentExp >= expToLevel[level] && level != 58) {
            currentExp -= expToLevel[level];
            level++;
        }
        
        int ret = 0;
        if(level != 58) {
            ret = expToLevel[level]-currentExp;
        }
        
        return ret;
    }
    
    /**
     *
     * @param exp
     * @return
     */
    public static String levelText(int exp) {
        int level = levelNum(exp);
        String[] nameLevel = {};
        
        //{"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""}
        
        try {
            nameLevel = read("level.txt").split("\n")[1].split(" ");
        } catch (FileNotFoundException ex) {
            exit(1);
        }
        return nameLevel[level-1];
    
    }
 
    /**
     *
     * @return
     */
    public String print() {
        return "Nickname: "+nick+"\n"
                + "Password: "+userPass+"\n"
                + "Level: "+levelText(exp)+", to next level: "+toNextLevelExp(exp)+"\n"
                + "Contracts gets: "+gettedContractsNumber;
    }
    
    public String printCurrentContracts() {
        if(!currentContracts.isEmpty()) {
            String ret = "Current missions: \n";
            for (int i = 0; i < currentContracts.size(); i++) {
                Contract get = currentContracts.get(i);
                ret += "IP: "+get.target.ip+",Mission: "+get.missionShort+"\n";
            }
            return ret;
        } else {
            return "No current missions";
        }
    }
    
    private static final Logger LOG = Logger.getLogger(User.class.getName());
     
}
