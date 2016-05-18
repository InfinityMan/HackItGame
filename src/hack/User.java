/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.System.err;
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
import static ru.epiclib.base.FileWorker.read;
import static ru.epiclib.base.FileWorker.read;
import static ru.epiclib.base.FileWorker.read;
import static ru.epiclib.base.FileWorker.read;


public class User implements Serializable {
    
    private static final long serialVersionUID = 2L;
    
    //public Computer computer;
    
    
    public String userPass = "AU0001";

    
    public String nick = "-167458";
    
    public int powerCPU = 200;
    
    public int exp;
    
   
    public int gettedContractsNumber = 0;
    
 
    public ArrayList<Contract> availableContracts;

    public ArrayList<Contract> currentContracts;
    

    public User(Hacknet hacknet) {
        availableContracts = new ArrayList<>();
        currentContracts = new ArrayList<>();
    }
    
    public User(String nick,String userPass, int exp, int getCon) {
        this.nick = nick;
        this.userPass = userPass;
        this.exp = exp;
        this.gettedContractsNumber = getCon;
        
        availableContracts = new ArrayList<>();
        currentContracts = new ArrayList<>();
    }
    
    /**
     *
     * @param hacknet
     * @return
     */
    public static User load(Hacknet hacknet) {
        User loadedUser;
        try {
            loadedUser = (User) deserData("hAcKsave.hsf");
            hacknet.print("Save loaded: "+loadedUser.nick);
            
        } catch (IOException ex) {
            loadedUser = new User(hacknet);
            hacknet.print("Save files are not finded : Register");
            System.err.println(ex);
        }
        return loadedUser;
    }
    
    /**
     *
     */
    public void save() {
        
        for (int i = 0; i < currentContracts.size(); i++) {
            Contract get = currentContracts.get(i);
            for (int j = 0; j < get.target.defenseList.size(); j++) {
                Protect get1 = get.target.defenseList.get(j);
                
                get1.hacknet = null;
                
            }
        }
        
        serData("hAcKsave.hsf", this);
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