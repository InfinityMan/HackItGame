/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack;

import hack.res.Link;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;
import ru.epiclib.base.Base;
import static ru.epiclib.base.Base.deserData;
import ru.epiclib.base.FileWorker;

/**
 *
 * @author 1234
 */
public final class Contract implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public static final double[] GRANT_FOR_DIFFICULTY = {20,40,20}; 

    public Computer target;

    public String author;
    public String missionFull;
    public String missionShort;
    
    public int id;
    public int needLvl;
    
    public enum Type {DESTROY,COPY,VIRUS};
    
    public Type type;
    
    public String targetFile;
    
    public Contract(Type type, User user) {
        
        this.type = type;
        
        int intType = 0;
        
        switch(type) {
            case DESTROY :
                intType = 0;
                break;
            case COPY :
                intType = 1;
                break;
            case VIRUS :
                intType = 2;
                break;
        }
        
        String allMissions = "";
        String allAuthors = "";
        try {
            allMissions = new Link().readRes("Missions.txt");
            allAuthors = new Link().readRes("names.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "I'm have a problem! Not loaded missions or authors: "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        
        String[] mission = allMissions.split("\n")[intType].split(";");
        String[] authors = allAuthors.split(";");
        
        if(Base.chance(62, 0)) {
            author = "Unknown";
        } else {
            author = authors[Base.randomNumber(0, authors.length-1)];
        }
        
        missionFull = "ID of this mission: " + id + "\nAuthor: " + author + "\n\n" + mission[0];
        missionShort = mission[Base.randomNumber(1, mission.length)];
        
        id = user.getGettedContractsNumber();
        
        int typeOfTarget = 0;
        if(User.levelNum(user.getExp()) >= Computer.LVL_TO_COMP[1]) typeOfTarget = 1;
        if(User.levelNum(user.getExp()) >= Computer.LVL_TO_COMP[2]) typeOfTarget = 2;
        if(User.levelNum(user.getExp()) >= Computer.LVL_TO_COMP[3]) typeOfTarget = 3;
        if(User.levelNum(user.getExp()) >= Computer.LVL_TO_COMP[4]) typeOfTarget = 4;
        if(User.levelNum(user.getExp()) >= Computer.LVL_TO_COMP[5]) typeOfTarget = 5;
        
        ArrayList<Computer> computers = new ArrayList<>();
        
        try {
            computers = (ArrayList<Computer>) deserData("CompsDataBase.comps");   
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Not loaded computers","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        
        
        ArrayList<Computer> lvlComps = new ArrayList<>();
        
        for (int i = 0; i < computers.size(); i++) {
            Computer get = computers.get(i);
            if(get.getType() == typeOfTarget) {
                lvlComps.add(get);
            }
        }
        
        target = lvlComps.get(Base.randomNumber(0, lvlComps.size()));
        
    }
    
    public boolean isComplited(String userIp) {
        if(type == Type.DESTROY) {
            boolean ret = false;
            for (Map.Entry<Integer, Log> a : target.listOfLog.entrySet()) {
                Integer key = a.getKey();
                Log value = a.getValue();
                if(value.equals(new Log(Log.Type.FILE_DELETED,userIp))) {
                    ret = true;
                    break;
                }
            }
            return ret;
        } else {
            return false;
        }
    }
    
    public boolean isComplited() {
        if(type == Type.DESTROY) {
            return !target.hasFile(targetFile);
        } else {
            return false;
        }
    }
    
    /**
     * Get price of this contract
     * @return first: basic price of contract ; second: price for difficulty
     */
    
    public double[] getPriceOfContract() {
        double priceOfContract = 0;
        switch(type) {
            case DESTROY:
                priceOfContract = GRANT_FOR_DIFFICULTY[0];
                break;
            case COPY:
                priceOfContract = GRANT_FOR_DIFFICULTY[1];
                break;
            case VIRUS:
                priceOfContract = GRANT_FOR_DIFFICULTY[2];
                break;
        }
        double priceOfTarget = Computer.GRANT_FOR_HACK[target.getType()];
        double[] ret = {priceOfContract, priceOfTarget};
        return ret;
    }

    @Override
    public String toString() {
        return "Contract{" + "target=" + target + ", missionFull=" + missionFull + ", missionShort=" + missionShort + ", id=" + id + ", needLvl=" + needLvl + ", type=" + type + ", targetFile=" + targetFile + '}';
    }
    
    
    
}
