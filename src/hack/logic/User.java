/*
 * Copyright (C) 2016 Dmitry Tsvetkovsky
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package hack.logic;

import hack.logic.hardware.Plate;
import hack.res.Link;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.epiclib.base.Base;
import static ru.epiclib.base.Base.deserData;
import static ru.epiclib.base.Base.serData;
import static ru.epiclib.base.Base.stringToInt;

public class User implements Serializable {

    private static final long serialVersionUID = 7L;
    
    public static Plate[] PLATES;

    //computer:
    public ArrayList<Software> soft = new ArrayList<>();
    public ArrayList<String> files = new ArrayList<>();
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

    public ArrayList<BankAccount> accounts;
    public int currentMainAccount = 0;
    
    private Plate plate;
    
    //Utility
    
    public int numberOfHackedComps = 0;
    public int numberOfProtectsHacked = 0;
    public Double numberOfMoneyGetted = 0.0;
    public int numberOfCommands = 0;
    
    
    
    public void setListAvailableContracts(Hacknet hacknet) {
        availableContracts.clear();
        for (int i = 0; i < Base.randomNumber(8, 12); i++) {
            availableContracts.add(new Contract(this, hacknet));
        }
    }
    
    public void addAccount(String name) {
        accounts.add(new BankAccount(name));
    }

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
            if (powerCPU == powerCPUtemp) {
                canBeSetted = true;
            }
            break;
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
        this.ip = ip;
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
        if (exp >= 0) {
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

    public User(String nick, String userPass, int exp, int getCon, Plate[] plates) {
        this.nick = nick;
        this.userPass = userPass;
        this.exp = exp;
        this.gettedContractsNumber = getCon;

        ip = Computer.genIP();

        availableContracts = new ArrayList<>();
        currentContracts = new ArrayList<>();
        accounts = new ArrayList<>();
        
        accounts.add(new BankAccount("Main"));
        accounts.get(0).addMoney(1000d);
        
        PLATES = plates;
        plate = PLATES[0];
    }

    public static User load() throws IOException {
        User loadedUser = null;

        try {
            loadedUser = (User) deserData("hAcKsave.hsf");
        } catch (ClassNotFoundException ex) {
            Hacknet.error("ClassNotFound in User.load()");
        }


        return loadedUser;
    }

    public void save() {
        try {
            serData("hAcKsave.hsf", this);
        } catch (IOException ex) {
            Hacknet.error("IOException in User.save()");
        }
    }

    public void rmCurrentContract(int id) {
        Iterator it = currentContracts.iterator();
        while (it.hasNext()) {
            Contract cont = (Contract) it.next();
            if (cont.id == id) {
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
            if (get.id == id) {
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
        int[] expToLevel = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < expToLevelStr.length; i++) {
            expToLevel[i] = stringToInt(expToLevelStr[i]);
        }
        int level = 0, currentExp = exp;
        while (currentExp >= expToLevel[level] && level != 58) {
            currentExp -= expToLevel[level];
            level++;
        }

        int retForMethodToNextLevelExp = 0;
        if (level != 58) {
            retForMethodToNextLevelExp = expToLevel[level] - currentExp;
        }

        if (levelNum) {
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
        return nameLevel[level - 1];

    }

    /**
     *
     * @return
     */
    public String print() {
        return "Nickname: " + getNick() + "\n"
                + "Password: " + getUserPass() + "\n"
                + "Level: " + levelText(getExp()) + ", to next level: " + toNextLevelExp(getExp()) + "\n"
                + "Contracts gets: " + getGettedContractsNumber() + "\n"
                + "Computers hacked: " + numberOfHackedComps + "\n"
                + "Protects hacked: "+ numberOfProtectsHacked + "\n"
                + "Commands entered: "+ numberOfCommands + "\n"
                + "Money got: " + numberOfMoneyGetted;
    }

    public String printCurrentContracts() {
        if (!currentContracts.isEmpty()) {
            String ret = "Current missions: \n";
            for (int i = 0; i < currentContracts.size(); i++) {
                Contract get = currentContracts.get(i);
                ret += "IP: " + get.target.ip + ",Mission: " + get.missionShort + "\n";
            }
            return ret;
        } else {
            return "No current missions";
        }
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }
    
    

    private static final Logger LOG = Logger.getLogger(User.class.getName());

}
