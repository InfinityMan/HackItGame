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
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ru.epiclib.base.Base;
import static ru.epiclib.base.Base.deserData;

/**
 *
 * @author 1234
 */
public final class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final double[] GRANT_FOR_DIFFICULTY = {20, 40, 20};

    public Computer target;

    public String author;
    public String missionFull;
    public String missionShort;

    public int id;
    public int needLvl;

    public enum Type {
        DESTROY_FILE, DESTROY_MULTFILE, DESTROY, COPY, VIRUS
    };

    public Type type;

    public String targetFile;
    public String[] targetFiles;

    public Contract(User user, Hacknet hacknet) {
        
        int intType = Base.randomNumber(0, 4);
        switch(intType) {
            case 0:
                type = Type.DESTROY_FILE;
                break;
            case 1:
                type = Type.DESTROY_MULTFILE;
                break;
            case 2:
                type = Type.DESTROY;
                break;
            case 3:
                type = Type.COPY;
                break;
            case 4:
                type = Type.VIRUS;
                break;
        }

        String allMissions = "";
        String allAuthors = "";
        try {
            allMissions = new Link().readRes("Missions.txt");
            allAuthors = new Link().readRes("Authors.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "I'm have a problem! Not loaded missions or authors: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        String[] mission = allMissions.split("\n")[intType].split(";");
        String[] authors = allAuthors.split(";");

        if (Base.chance(62, 0)) {
            author = "Unknown";
        } else {
            author = authors[Base.randomNumber(0, authors.length - 1)];
        }
        
        id = user.getGettedContractsNumber();

        int typeOfTarget = 0;
        if (User.levelNum(user.getExp()) >= Computer.LVL_TO_COMP[1]) {
            typeOfTarget = 1;
        }
        if (User.levelNum(user.getExp()) >= Computer.LVL_TO_COMP[2]) {
            typeOfTarget = 2;
        }
        if (User.levelNum(user.getExp()) >= Computer.LVL_TO_COMP[3]) {
            typeOfTarget = 3;
        }
        if (User.levelNum(user.getExp()) >= Computer.LVL_TO_COMP[4]) {
            typeOfTarget = 4;
        }
        if (User.levelNum(user.getExp()) >= Computer.LVL_TO_COMP[5]) {
            typeOfTarget = 5;
        }

        ArrayList<Computer> computers = new ArrayList<>();

        try {
            computers = (ArrayList<Computer>) deserData("CompsDataBase.comps");
        } catch (FileNotFoundException ex) {
            Hacknet.error("CompBase not found in Contract()");
        } catch (IOException ex) {
            Hacknet.error("IOException in Contract()");
        } catch (ClassNotFoundException ex) {
            Hacknet.error("O my god! There are a very strange error! in Contract()");
        }

        ArrayList<Computer> lvlComps = new ArrayList<>();

        for (int i = 0; i < computers.size(); i++) {
            Computer get = computers.get(i);
            if (get.getType() == typeOfTarget) {
                lvlComps.add(get);
            }
        }

        target = lvlComps.get(Base.randomNumber(0, lvlComps.size()-1));
        
        target = hacknet.findTargetOfConInList(target);
        
        target.reloadComputer();
        hacknet.setTargetOfConInList(target);
        
        if (type == Contract.Type.DESTROY_FILE || type == Contract.Type.COPY) {
            targetFile = target.getFile(Base.randomNumber(0, target.sizeOfListFiles() - 1));
        } else if(type ==  Contract.Type.DESTROY_MULTFILE) {
            int randomFiles  = Base.randomNumber(2, 4);
            ArrayList<String> files2 = target.getFiles();
            for (int i = 0; i < randomFiles; i++) {
                targetFiles = new String[target.getSizeFileArray()];
                int random = Base.randomNumber(0, files2.size() - 1);
                targetFiles[i] = files2.get(random);
                files2.remove(random);
            }
        } else if(type == Contract.Type.DESTROY) {
            targetFiles = new String[target.getSizeFileArray()];
            for (int i = 0; i < target.getSizeFileArray(); i++) {
                targetFiles[i] = target.getFile(i);
            }
        }
        
        missionFull = "ID of this mission: " + id + "\nAuthor: " + author + "\nTarget: " + target.ip + "\n\n" + mission[0] + "\n\n" + targetFile;
        missionShort = mission[Base.randomNumber(1, mission.length-1)];

        target.hacked = false;
        
        

    }

    public boolean isComplited(User user) {
        if (null != type) {
            switch (type) {
                case DESTROY_FILE:
                    return !target.hasFile(targetFile);
                case DESTROY_MULTFILE:
                case DESTROY:
                    boolean completed = true;
                    for (String targetFile1 : targetFiles) {
                        if (target.hasFile(targetFile1)) {
                            completed = false;
                        }
                    }
                    return completed;
                case VIRUS:
                    return target.virused;
                case COPY:
                    return user.files.contains(targetFile);
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Get price of this contract
     *
     * @return first: basic price of contract ; second: price for difficulty
     */
    public double[] getPriceOfContract() {
        double priceOfContract = 0;
        switch (type) {
            case DESTROY_FILE:
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
