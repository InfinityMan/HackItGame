/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack.logic;

import hack.res.Link;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;
import static ru.epiclib.base.Base.deserData;
import static ru.epiclib.base.Base.serData;
import static ru.epiclib.base.Base.stringToInt;


public class User implements Serializable {
    
    private static final long serialVersionUID = 4L;
    
    //computer:
    
    public ArrayList<Software> soft;
    public ArrayList<String> files;
    private int powerCPU = Computer.CPUS_POWER[1];
    
    private String ip;
    
    //end
    
    //user txts:
    
    private String userPass = "********";
    private String nick = "Dmig";
    
    //end
    
    private int exp;
    
    private int gettedContractsNumber = 0;
    
    public ArrayList<Contract> availableContracts;
    public ArrayList<Contract> currentContracts;
    
    private double money = 0;

    /**
     * @return the powerCPU
     */
    public int getPowerCPU() {
        return powerCPU;
    }

    /**
     * @param powerCPU the powerCPU to set
     */
    public void setPowerCPU(int powerCPU) {
        boolean canBeSetted = false;
        
        for (int powerCPUtemp : Computer.CPUS_POWER) {
            if(powerCPU == powerCPUtemp) canBeSetted = true; break;
        }
        
        if (canBeSetted) {
            this.powerCPU = powerCPU;
        }
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        if (ip.length() == 5) {
            this.ip = ip;
        }
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        if(money >= 0) {
            this.money = money;
        } else throw new IllegalArgumentException();
    }

    /**
     * @return the userPass
     */
    public String getUserPass() {
        return userPass;
    }

    /**
     * @param userPass the userPass to set
     */
    public void setUserPass(String userPass) {
        if (userPass != null || !userPass.isEmpty()) {
            this.userPass = userPass;
        }
    }

    /**
     * @return the nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * @param nick the nick to set
     */
    public void setNick(String nick) {
        if (userPass != null || !userPass.isEmpty()) {
            this.nick = nick;
        }
    }

    /**
     * @return the exp
     */
    public int getExp() {
        return exp;
    }

    /**
     * @param exp the exp to set
     */
    public void setExp(int exp) {
        if(exp >= 0) {
            this.exp = exp;
        }
    }

    /**
     * @return the gettedContractsNumber
     */
    public int getGettedContractsNumber() {
        return gettedContractsNumber;
    }

    /**
     * @param gettedContractsNumber the gettedContractsNumber to set
     */
    public void setGettedContractsNumber(int gettedContractsNumber) {
        if (gettedContractsNumber >= 0) {
            this.gettedContractsNumber = gettedContractsNumber;
        }
    }
    
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
        hacknet.print("Save file are finded : " + loadedUser.getNick());
        hacknet.print("Save loaded: " + loadedUser.getNick());

        return loadedUser;
    }
    
    public void save() {
        serData("hAcKsave.hsf", this);
    }
    
    public void rmCurrentContract(int id) {
        Iterator it = currentContracts.iterator();
        while (it.hasNext()) {
            Contract cont = (Contract) it.next();
            if(cont.id == id) {
                it.remove();
            }
        }
    }
    
    public void rmCurrentContract(Contract con) {
        rmCurrentContract(con.id);
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
    
    public static int levelNum(int exp) {
        return levelOperations(exp, true);
    }
    
    private static int levelOperations(int exp, boolean levelNum) {
        String[] expToLevelStr = null;
        try {
            expToLevelStr = new Link().readRes("level.txt").split("\n")[0].split(" ");
        } catch (FileNotFoundException ex) {
            exit(1);
        } catch (IOException ex) {
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
        
        int retForMethodToNextLevelExp = 0;
        if(level != 58) {
            retForMethodToNextLevelExp = expToLevel[level]-currentExp;
        }
        
        if(levelNum) {
            return level;
        } else {
            return retForMethodToNextLevelExp;
        }
        
    }

    public static int toNextLevelExp(int exp) {
        return levelOperations(exp, false);
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
            nameLevel = new Link().readRes("level.txt").split("\n")[1].split(" ");
        } catch (IOException ex) {
            exit(1);
        }
        return nameLevel[level-1];
    
    }
 
    /**
     *
     * @return
     */
    public String print() {
        return "Nickname: "+getNick()+"\n"
                + "Password: "+getUserPass()+"\n"
                + "Level: "+levelText(getExp())+", to next level: "+toNextLevelExp(getExp())+"\n"
                + "Contracts gets: "+getGettedContractsNumber();
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
