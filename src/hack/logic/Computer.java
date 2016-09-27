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

import hack.gui.AuthWindow;
import hack.gui.LabelHack;
import hack.gui.ListGUI;
import hack.logic.exceptions.ComputerIsNotHackedException;
import java.io.Serializable;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.logging.Logger;
import ru.epiclib.base.Base;
import java.util.HashMap;
import static java.lang.Thread.sleep;
import java.util.Objects;
import static java.lang.Thread.sleep;

public final class Computer implements Serializable {

    private static final long serialVersionUID = 4L;

    public static final int[] CPUS_POWER = {100, 200, 400, 600, 800, 1600};
    public static final int[] EXPS = {7, 12, 20, 44, 64, 82};
    public static final int[] LVL_TO_COMP = {1, 8, 17, 24, 32, 43};
    public static final String[] TYPES = {"Old server", "Local server", "Small server", "Normal server", "Big server", "Super server"};
    public static final String[] SUFFIX_NAME = {"workstation", "station", "base", "frame", "mainframe", "grandframe"};

    public static final double[] GRANT_FOR_HACK = {80, 170, 340, 820, 1800, 3000};

    private ArrayList<Protect> defenseList;
    private ArrayList<String> files;

    public int trace, exp;

    public String ip, nameComputer;

    public String prefix;

    public boolean hacked = false, virused = false, scanned = false;

    transient public AuthWindow aw;
    transient public ListGUI filesGUI;
    transient public Hacknet hacknet;

    public HashMap<Integer, Log> listOfLog;

    //--------------------------------------------------------------------------
    
    public int getSizeFileArray() {
        return files.size();
    }
    
    public ArrayList<String> getFiles() {
        return files;
    }
     
    public String[] getFileArray() {

        String[] ret = new String[files.size()];

        for (int i = 0; i < files.size(); i++) {
            String get = files.get(i);
            ret[i] = get;
        }

        return ret;

    }
    
    public void addToFilesList(String str) {
        files.add(str);
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

    public boolean rmFile(String file, String userIP) {
        for (int i = 0; i < files.size(); i++) {
            String get = files.get(i);
            if (get.equals(file)) {
                listOfLog.put(listOfLog.size(), new Log(Log.Type.FILE_DELETED, userIP));
                files.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean hasProtect(Protect.Type type) {

        for (int i = 0; i < defenseList.size(); i++) {
            Protect get = defenseList.get(i);
            if (get.type == type) {
                return true;
            }
        }

        return false;
    }

    public boolean protectHacked(Protect.Type type) {
        if (hasProtect(type)) {
            Protect pro = getProtect(type);
            return pro.open;
        } else {
            return true;
        }
    }

    public void setDeletedToLog(int keyOfLog) {
        listOfLog.get(keyOfLog).setDeleted(true);
    }

    public void setUndeletedToLog(int keyOfLog) {
        listOfLog.get(keyOfLog).setDeleted(false);
    }

    public void addFile(String file) {
        files.add(file);
    }

    public String getFile(int idInCollection) {
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
        return needOpen == 0;
    }

    public String print() {
        return TYPES[getType()] + ", name: \"" + nameComputer + "\", ip: " + ip + ", hacked: " + hacked;
    }

    public int getType() {
        switch (exp) {
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
        if (type > 0) {
            df.add(new Protect(Protect.Type.PROXY));
        }
        if (type > 1) {
            df.add(new Protect(Protect.Type.FIREWALL));
            df.add(new Protect(Protect.Type.ANTIHACK));
        }
        if (type > 2) {
            df.add(new Protect(Protect.Type.ALPHA));
            df.add(new Protect(Protect.Type.PROTE));
        }
        if (type > 3) {
            df.add(new Protect(Protect.Type.ELLO));
        }
        if (type > 4) {
            df.add(new Protect(Protect.Type.ZEUS));
        }

        defenseList = df;

        listOfLog = new HashMap<>();

        switch (type) {
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

        ArrayList<String> gf = new ArrayList<>();

        gf.add("reboot.dll");
        gf.add("sys.dll");
        gf.add("OS.dll");
        for (int i = 0; i < Base.randomNumber(8, 11); i++) {
            gf.add(genFile());
        }

        files = gf;

        ip = genIP();

    }
    
    public void reloadComputer() {
        updateFileSys();
        for (int i = 0; i < defenseList.size(); i++) {
            Protect get = defenseList.get(i);
            get.open = false;
        }
        this.hacked = false;
        this.scanned = false;
        this.virused = false;
        
        if (aw != null && filesGUI != null) {
            this.aw.dispose();
            this.filesGUI.update();
            this.filesGUI.dispose();
        }
    }
    
    public void doFilesGUI(User user) throws ComputerIsNotHackedException {
        if (hacked) {
            filesGUI.update();
            filesGUI.setVisible(true);
        } else {
            throw new ComputerIsNotHackedException();
        }
    }

    public void updateFileSys() {
        rmAllFiles();
        files.add("reboot.dll");
        files.add("sys.dll");
        files.add("OS.dll");
        for (int i = 0; i < Base.randomNumber(4, 8); i++) {
            addFile(genFile());
        }
    }

    public void rmAllFiles() {
        files.clear();
    }

    public String genFile() {
        return prefix + "-" + Base.randomNumber(101, 99999999) + ".dat";
    }

    public Computer(int type, String nameComputer, String prefix, String ip) {
        this(type, nameComputer, prefix);
        this.ip = ip;
    }

    public static String genIP() {
        String ip = Base.randomString(2,true,true,true) + "." + Base.randomString(5,true,true,true);
        return ip;
    }

    public void loadHacknetToProtect(Hacknet hacknet) {
        for (int i = 0; i < defenseList.size(); i++) {
            Protect get = defenseList.get(i);
            get.hacknet = hacknet;
        }
    }

    public boolean hasFile(String file) {
        boolean ret = false;
        for (int i = 0; i < files.size(); i++) {
            String get = files.get(i);
            if (get.equals(file)) {
                ret = true;
            }
        }
        return ret;
    }

    public String printScan() {
        String first = "Result of scan: \n"
                + " Admin: " + hacked + "\n"
                + " Name of computer: " + nameComputer + "\n"
                + " IP adress: " + ip + "\n"
                + " Protect systems: \n";
        String second = "";
        for (int i = 0; i < defenseList.size(); i++) {
            Protect get = defenseList.get(i);
            if (get.type != Protect.Type.ANTIHACK) {
                second += "     " + get.printThis() + "\n";
            }
        }
        String third = " Trace: " + trace + "\n"
                + " Power: " + exp;

        return first + second + third;

    }
    
    public void hackCore(final User user, boolean cheat) {
        Thread myThready = new Thread(() -> {
            LabelHack lh;
            lh = new LabelHack();
            lh.setVisible(true);

            aw.setUsername("admin");
            if(!cheat) {
                lh.start(LabelHack.TypeOfChars.ALL, LabelHack.Difficulty.VERY_LOW, exp * 2);
            } else {
                lh.cheat(LabelHack.TypeOfChars.ALL, LabelHack.Difficulty.VERY_LOW);
            }
            aw.setPass(lh.key);

            hacked = true;
            user.setExp(user.getExp() + exp);

            try {
                if(!cheat) {
                    sleep(3_000);
                } else {
                    sleep(1_500);
                }
            } catch (InterruptedException ex) {
                exit(1);
            }
            aw.dispose();
            lh.dispose();
        });
        myThready.start();
    }
    
    public void hackCheat(final User user, Hacknet hacknet) {
        for (int i = 0; i < defenseList.size(); i++) {
            Protect get = defenseList.get(i);
            get.open = true;
            hacknet.print(" "+get.printThis()+" disabled!");
        }
        scanned = true;
        hackCore(user,true);
        hacknet.print("Hack of computer completed");
    }

    public void hack(final User user) {
        hackCore(user,false);
    }
    private static final Logger LOG = Logger.getLogger(Computer.class.getName());

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.defenseList);
        hash = 59 * hash + Objects.hashCode(this.files);
        hash = 59 * hash + this.trace;
        hash = 59 * hash + this.exp;
        hash = 59 * hash + Objects.hashCode(this.ip);
        hash = 59 * hash + Objects.hashCode(this.nameComputer);
        hash = 59 * hash + Objects.hashCode(this.prefix);
        hash = 59 * hash + (this.hacked ? 1 : 0);
        hash = 59 * hash + (this.virused ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.listOfLog);
        return hash;
    }
    
    public boolean ipsEquals(Computer computer) {
        final Computer other = computer;
        return Objects.equals(this.ip, other.ip);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Computer other = (Computer) obj;
        return Objects.equals(this.ip, other.ip);
    }

}
