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
import hack.gui.AuthWindow;
import hack.gui.ContractsList;
import hack.gui.AdminWindow;
import hack.gui.GetMissionGUI;
import hack.gui.ListGUI;
import static hack.logic.User.load;
import hack.logic.exceptions.ComputerIsNotHackedException;
import hack.logic.hardware.*;
import hack.res.Link;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ru.epiclib.base.Base;
import static ru.epiclib.base.Base.deserData;
import ru.epiclib.base.FileWorker;
import ru.epiclib.gui.Util;
import ru.epiclib.logging.Logging;

/**
 *
 * @author 1234
 */
public final class Hacknet extends javax.swing.JFrame {

    public static final String GAME_VERSION = "0.7";
    
    public static boolean CHEAT = true;

    public User user;

    public ArrayList<Computer> computers;

    public Computer currentTarget;

    public boolean loaded = false;
    public boolean debug = false;

    private String lastCommand = "";
    
    //Init in constructor
    
    public CpuModule[] cpus;
    public CpuModule[] cpusUp;
    public RamModule[] rams;
    public RamModule[] ramsUp;
    public HardDriveModule[] hards;
    public InternetModule[] internets;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        init();
    }

    /**
     *
     */
    public Hacknet() {
        
        this.ramsUp = new RamModule[HardwareModule.DEFAULT_POWER_RAM_AND_CPU.length];
        this.cpusUp = new CpuModule[HardwareModule.DEFAULT_POWER_RAM_AND_CPU.length];
        this.cpus = new CpuModule[HardwareModule.DEFAULT_POWER_RAM_AND_CPU.length];
        this.rams = new RamModule[HardwareModule.DEFAULT_POWER_RAM_AND_CPU.length];
        this.hards = new HardDriveModule[HardwareModule.DEFAULT_POWER_HARD.length];
        this.internets = new InternetModule[HardwareModule.DEFAULT_POWER_INTERNET.length];
        
        try {
            this.computers = (ArrayList<Computer>) deserData("CompsDataBase.comps");
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            error("ClassNotFound in Hacknet()");
        }

        initComponents();

    }

    public static void updateBase() {

        try {
            FileWorker.delete("CompsDataBase.comps");
        } catch (FileNotFoundException ex) {
        }

        @SuppressWarnings("UnusedAssignment")
        String allComps = "";
        try {
            allComps = new Link().readRes("ComputersBase.txt");
        } catch (IOException ex) {
            error("Not loaded computers");
            exit(1);
        }

        ArrayList<Computer> cmrs = new ArrayList<>();
        String[] comps = allComps.split("\n");
        for (String comp : comps) {
            String[] params = comp.split(",");
            cmrs.add(new Computer(Base.stringToInt(params[0]), params[1], params[2]));
        }

        try {
            Base.serData("CompsDataBase.comps", cmrs);
        } catch (IOException ex) {
            error("IOException in Hacknet.updateBase()");
        }
    }
    
    public void installHard() {
        cpus = (CpuModule[]) HardwareModule.loadHardware(0, false);
        cpusUp = (CpuModule[]) HardwareModule.loadHardware(0, true);
        
        rams = (RamModule[]) HardwareModule.loadHardware(1, false);
        ramsUp = (RamModule[]) HardwareModule.loadHardware(1, true);
        
        hards = (HardDriveModule[]) HardwareModule.loadHardware(2, true);
        
        internets = (InternetModule[]) HardwareModule.loadHardware(3, true);
    }
    
    public static void init() {

        try {
            Util.setStyle();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            error("Style crashed");
        }

        Thread main = new Thread(() -> {
            Hacknet hacknet = new Hacknet();
            hacknet.setVisible(true);
        });
        main.start();

//        invokeLater(() -> {
//            new Hacknet().setVisible(true);
//        });
    }

    /**
     *
     * @param s
     */
    public void print(Object s) {
        Console.setText(Console.getText() + s + "\n");
    }

    public void printNoN(String s) {
        Console.setText(Console.getText() + s);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CommandType = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Console = new javax.swing.JTextArea();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HackIt");
        setMaximumSize(new java.awt.Dimension(400, 2147483647));
        setMinimumSize(new java.awt.Dimension(400, 400));
        setPreferredSize(new java.awt.Dimension(400, 600));

        CommandType.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        CommandType.setToolTipText("Type your command here");
        CommandType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CommandTypeKeyPressed(evt);
            }
        });

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Console.setEditable(false);
        Console.setColumns(20);
        Console.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 13)); // NOI18N
        Console.setRows(5);
        Console.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Console.setFocusable(false);
        jScrollPane1.setViewportView(Console);

        jProgressBar1.setToolTipText("Trace");

        jProgressBar2.setToolTipText("RAM");

        jToggleButton1.setText("????");
        jToggleButton1.setMaximumSize(new java.awt.Dimension(0, 0));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton1.setText("????");
        jButton1.setPreferredSize(new java.awt.Dimension(70, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("????");
        jButton2.setMinimumSize(new java.awt.Dimension(70, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CommandType)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CommandType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadUser() {
        LOG.addHandler(Logging.stndHandler());
        print("Hello. This is only developer verison : " + GAME_VERSION);
        LOG.log(Level.INFO, "This is only developer verison : " + GAME_VERSION);
        try {
            user = load();
            LOG.log(Level.INFO, "User successfully loaded");
        } catch (IOException ex) {
            //user = new User("Dmig", "*******", 180, 17);
            updateBase();
            try {
                this.computers = (ArrayList<Computer>) deserData("CompsDataBase.comps");
            } catch (IOException e) {
                LOG.info("List of computers is not founded");
            } catch (ClassNotFoundException ex1) {
                error("ClassNotFound in Hacknet.loadUser()");
            }

            print("Save files are not finded : Register");
            installHard();
            registerUser(getPlates());
            print("User successfully registered");
            LOG.log(Level.WARNING, "IOException of load : {0}", ex.getMessage());
        }
        loaded = true;
        LOG.log(Level.INFO, "Hacknet inited");
        
        
    }
    
    public void getAllHardwareText() {
        int[] lengths = {cpus.length,cpusUp.length,rams.length,ramsUp.length,hards.length,internets.length};
        String[][] hardwares = new String[6][];
        for (int i = 0; i < 6; i++) {
            hardwares[i] = new String[lengths[i]];
        }
        for (int n = 0; n < 6; n++) {
            switch(n) {
                case 0:
                    for (int i = 0; i < cpus.length; i++) {
                        CpuModule cpu = cpus[i];
                        hardwares[n][i] = cpu.toString();
                    }
                    break;
                case 1:
                    for (int i = 0; i < cpusUp.length; i++) {
                        CpuModule cpuUp = cpusUp[i];
                        hardwares[n][i] = cpuUp.toString();
                    }
                    break;
                case 2:
                    for (int i = 0; i < rams.length; i++) {
                        RamModule ram = rams[i];
                        hardwares[n][i] = ram.toString();
                    }
                    break;
                case 3:
                    for (int i = 0; i < ramsUp.length; i++) {
                        RamModule ramUp = ramsUp[i];
                        hardwares[n][i] = ramUp.toString();
                    }
                    break;
                case 4:
                    for (int i = 0; i < hards.length; i++) {
                        HardDriveModule hard = hards[i];
                        hardwares[n][i] = hard.toString();
                    }
                    break;
                case 5:
                    for (int i = 0; i < internets.length; i++) {
                        InternetModule internet = internets[i];
                        hardwares[n][i] = internet.toString();
                    }
                    break;
            }
        }
        for (int i = 0; i < hardwares.length; i++) {
            switch(i) {
                case 0:
                    print("CPUs:");
                    break;
                case 1:
                    print("Cool CPUs:");
                    break;
                case 2:
                    print("RAMs:");
                    break;
                case 3:
                    print("Cool RAMs:");
                    break;
                case 4:
                    print("Hardwares:");
                    break;
                case 5:
                    print("Internets:");
                    break;
                default:
                    print("ERROR");
            }
            for (String hardware : hardwares[i]) {
                print(hardware);
            }
        }
    }
    
    public void getUserHardware() {
        Plate userPlate = user.getPlate();
        int[] lengths = {userPlate.getCpus().length, userPlate.getUprgCpus().length, userPlate.getRamDDR3().length, userPlate.getRamDDR4().length, userPlate.getHardDrive().length, userPlate.getInternet().length};
        String[][] hardwares = new String[6][];
        for (int i = 0; i < 6; i++) {
            hardwares[i] = new String[lengths[i]];
        }
        for (int n = 0; n < 6; n++) {
            switch (n) {
                case 0:
                    for (int i = 0; i < userPlate.getCpus().length; i++) {
                        if (userPlate.getCpus()[i] != null) {
                            CpuModule cpu = userPlate.getCpus()[i];
                            hardwares[n][i] = "  #"+i+": " + cpu.toString();
                        } else {
                            hardwares[n][i] = "  #"+i+": Empty slot";
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < userPlate.getUprgCpus().length; i++) {
                        if (userPlate.getUprgCpus()[i] != null) {
                            CpuModule cpuUp = userPlate.getUprgCpus()[i];
                            hardwares[n][i] = "  #"+i+": " + cpuUp.toString();
                        } else {
                            hardwares[n][i] = "  #"+i+": Empty slot";
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < userPlate.getRamDDR3().length; i++) {
                        if (userPlate.getRamDDR3()[i] != null) {
                            RamModule ram = userPlate.getRamDDR3()[i];
                            hardwares[n][i] = "  #"+i+": " + ram.toString();
                        } else {
                            hardwares[n][i] = "  #"+i+": Empty slot";
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < userPlate.getRamDDR4().length; i++) {
                        if (userPlate.getRamDDR4()[i] != null) {
                            RamModule ramUp = userPlate.getRamDDR4()[i];
                            hardwares[n][i] = "  #"+i+": " + ramUp.toString();
                        } else {
                            hardwares[n][i] = "  #"+i+": Empty slot";
                        }
                    }
                    break;
                case 4:
                    for (int i = 0; i < userPlate.getHardDrive().length; i++) {
                        if (userPlate.getHardDrive()[i] != null) {
                            HardDriveModule hard = userPlate.getHardDrive()[i];
                            hardwares[n][i] = "  #"+i+": " + hard.toString();
                        } else {
                            hardwares[n][i] = "  #"+i+": Empty slot";
                        }
                    }
                    break;
                case 5:
                    for (int i = 0; i < userPlate.getInternet().length; i++) {
                        if (userPlate.getInternet()[i] != null) {
                            InternetModule internet = userPlate.getInternet()[i];
                            hardwares[n][i] = "  #"+i+": " + internet.toString();
                        } else {
                            hardwares[n][i] = "  #"+i+": Empty slot";
                        }
                    }
                    break;
            }
        }
        for (int i = 0; i < hardwares.length; i++) {
            
            switch (i) {
                case 0:
                    if (userPlate.getCpus().length > 0) {
                        print("CPUs:");
                    }
                    break;
                case 1:
                    if (userPlate.getUprgCpus().length > 0) {
                        print("Cool CPUs:");
                    }
                    break;
                case 2:
                    if (userPlate.getRamDDR3().length > 0) {
                        print("RAMs:");
                    }
                    break;
                case 3:
                    if (userPlate.getRamDDR4().length > 0) {
                        print("Cool RAMs:");
                    }
                    break;
                case 4:
                    if (userPlate.getHardDrive().length > 0) {
                        print("Hard drives:");
                    }
                    break;
                case 5:
                    if (userPlate.getInternet().length > 0) {
                        print("Internets:");
                    }
                    break;
                default:
                    print("ERROR");
            }
            for (String hardware : hardwares[i]) {
                print(hardware);
            }
        }

        print("Free hardware: ");

        if (!user.freeHardwares.isEmpty()) {
            for (int i = 0; i < user.freeHardwares.size(); i++) {
                HardwareModule get = user.freeHardwares.get(i);
                print("  #"+i+": " + get.toString());
            }
        } else {
            print("  No free hardware");
        }
    }
    
    private Plate[] getPlates() {
        
        ArrayList<Plate> plates = new ArrayList<>();

        String allPlates = "";
        
        try {
            allPlates = new Link().readRes("Plates.txt");
        } catch (IOException ex) {
            error("Not loaded plates");
            exit(1);
        }
        
        String[] platesStr = allPlates.split("\n");
        
        for (int i = 0; i < platesStr.length; i++) {
            String[] params = platesStr[i].split(",");
            int[] tempParams = new int[6];
            for (int j = 0; j < tempParams.length; j++) {
                tempParams[j] = Base.stringToInt(params[j]);
            }
            plates.add(new Plate(tempParams[0], tempParams[1], tempParams[2], tempParams[3], tempParams[4], tempParams[5], Base.stringToDouble(params[6]), params[7], Base.stringToInt(params[8]),this));
        }
        
        Plate[] platesRet = new Plate[plates.size()];
        
        for (int i = 0; i < plates.size(); i++) {
            platesRet[i] = plates.get(i);
        }
        
        return platesRet;
    }

    private void registerUser(Plate[] plates) throws HeadlessException {
        String name, pass;
        String tmp1 = JOptionPane.showInputDialog(null, "Enter your nickname", "Registration", JOptionPane.QUESTION_MESSAGE);
        while (tmp1.length() < 1) {
            tmp1 = JOptionPane.showInputDialog(null, "Enter your nickname", "Registration", JOptionPane.QUESTION_MESSAGE);
        }
        name = tmp1;
        String tmp2 = JOptionPane.showInputDialog(null, "Enter your password", "Registration", JOptionPane.QUESTION_MESSAGE);
        while (tmp2.length() < 1) {
            tmp2 = JOptionPane.showInputDialog(null, "Enter your password", "Registration", JOptionPane.QUESTION_MESSAGE);
        }
        pass = tmp2;
        user = new User(name, pass, 0, 0, plates);
        LOG.log(Level.INFO, "User successfully registered with name \"{0}\" and pass \"{1}\"", new Object[]{name, pass});
    }

    private void CommandTypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CommandTypeKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {

            String commandAll = CommandType.getText();
            print("> " + commandAll);
            LOG.log(Level.INFO, "User command > {0}", commandAll);
            CommandType.setText("");
            lastCommand = commandAll;

            String[] command = commandAll.split(" ");

            if (loaded) {
                scanCommand(command);
            } else if (command[0].equalsIgnoreCase("init")) {
                loadUser();
            } else {
                print("You need init the device");
            }

        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            CommandType.setText(lastCommand);
        }
    }//GEN-LAST:event_CommandTypeKeyPressed

    private void scanCommand(String[] command) {
        if (command[0].equalsIgnoreCase("mail")) {
            mail();
        } else if (command[0].equalsIgnoreCase("connect")) {
            if (currentTarget == null) {
                LOG.log(Level.INFO, "User try connect to {0}", command[1]);
                connect(command[1]);
            } else {
                print("You need to disconnect from current computer");
            }
        } else if (command[0].equalsIgnoreCase("scan")) {
            if (currentTarget != null) {
                currentTarget.scanned = true;
                LOG.log(Level.INFO, "User scan the computer");
                print(currentTarget.printScan());
            } else {
                print("Not finded target");
            }
        } else if (command[0].equalsIgnoreCase("list")) {
            print("List of computers: ");
            for (int i = 0; i < computers.size(); i++) {
                Computer get = computers.get(i);
                print(" " + get.print());
            }
        } else if (command[0].equalsIgnoreCase("auth")) {
            AuthWindow aw = new AuthWindow();
            aw.setVisible(true);
            currentTarget.aw = aw;
        } else if (command[0].equalsIgnoreCase("hack")) {
            if (command.length >= 2) {
                if(currentTarget.scanned) {
                    LOG.log(Level.INFO, "User want hack {0}", command[1]);
                    hack(command[1]);
                } else {
                   print("Computer is not scanned");
                }
            } else {
                print("No found subject; type hack [tagret]");
            }
        } else if (command[0].equalsIgnoreCase("save")) {
            LOG.log(Level.INFO, "User save the game");
            dc();
            user.save();
            try {
                Base.serData("CompsDataBase.comps", computers);
            } catch (IOException ex) {
                error("IOException in user command \"save\"");
            }
        } else if (command[0].equalsIgnoreCase("load")) {
            loadUser();
        } else if (command[0].equalsIgnoreCase("admin")) {
            admin();
        } else if (command[0].equalsIgnoreCase("money")) {
            money(command);
        } else if (command[0].equalsIgnoreCase("exit")) {
            userExit();
        } else if (command[0].equalsIgnoreCase("stats")) {
            LOG.log(Level.INFO, "User want know his stats");
            print(user.print());
        } else if (command[0].equalsIgnoreCase("teos")) {
            JOptionPane.showMessageDialog(null, "^_^");
        } else if (command[0].equalsIgnoreCase("missions")) {
            missions();
        } else if (command[0].equalsIgnoreCase("compinfo")) {
            print(user.getPlate().toString());
        } else if (command[0].equalsIgnoreCase("getmission")) {
            genMission();
        } else if (command[0].equalsIgnoreCase("files")) {
            files();
        } else if (command[0].equalsIgnoreCase("logs")) {
            if (!currentTarget.listOfLog.isEmpty()) {
                currentTarget.listOfLog.entrySet().stream().forEach((en) -> {
                    Integer key = en.getKey();
                    Log value = en.getValue();
                    print("@" + key + ": " + value.message);
                });
            } else {
                print("There are no logs");
            }
        } else if (command[0].equalsIgnoreCase("reset")) {
            reset();
        } else if (command[0].equalsIgnoreCase("help")) {
            help(command);
        } else if (command[0].equalsIgnoreCase("dc")) {
            dc();
        } else if (command[0].equalsIgnoreCase("finish")) {
            finishContract(command);
        } else if (command[0].equalsIgnoreCase("virus")) {
            virusTarget();
        } else if (command[0].equalsIgnoreCase("hardware")) {
            if(command[1].equalsIgnoreCase("my")) {
                getUserHardware();
            } else if(command[1].equalsIgnoreCase("all")) {
                getAllHardwareText();
            } else if(command[1].equalsIgnoreCase("buy")) {
                if(command[2].length() > 0 && command[3].length() > 0) {
                    int index = Base.stringToInt(command[3]);
                    HardwareModule hm = null;
                    switch(Base.stringToInt(command[2])) {
                        case 0:
                            hm = cpus[index];
                            break;
                        case 1:
                            hm = cpusUp[index];
                            break;
                        case 2:
                            hm = rams[index];
                            break;
                        case 3:
                            hm = ramsUp[index];
                            break;
                        case 4:
                            hm = hards[index];
                            break;
                        case 5:
                            hm = internets[index];
                            break;
                        default:
                            error("Error with buy of hardware");
                    }
                    if(user.accounts.get(user.currentMainAccount).getMoney() >= hm.price) {
                        user.freeHardwares.add(hm);
                        user.accounts.get(user.currentMainAccount).rmMoney(hm.price);
                        print("Hardware added to you list of free hardwares: "+hm.toString());
                    } else {
                        print("Not enought money");
                    }
                } else {
                    print("Invalid arguments; hardware buy [type] [index]");
                }
            }
        } else {
            print("Invalid command");
        }
        user.numberOfCommands++;
    }
    
    
    private void userExit() {
        LOG.log(Level.INFO, "User exit from game");
        if(currentTarget != null) {
            dc();
        }
        user.save();
        try {
            Base.serData("CompsDataBase.comps", computers);
        } catch (IOException ex) {
            error("IOException in Hacknet.userExit()");
        }
        exit(0);
    }

    private void files() {
        if (currentTarget != null) {
            try {
                currentTarget.doFilesGUI(user);
            } catch (ComputerIsNotHackedException ex) {
                print("Computer is not hacked");
            }
        } else {
            print("No target");
        }
    }

    private void help(String[] command) {
        if (command[1].equalsIgnoreCase("cmd")) {
            print("(Current version of game is " + GAME_VERSION + ")");
            String allHelp = "";
            try {
                allHelp = new Link().readRes("HelpCmd.txt");
            } catch (IOException ex) {
                error("IOException in load help cmd");
            }
            String[] help = allHelp.split("\n");
            for (String help1 : help) {
                print("  " + help1);
            }
            print("End of help");
        }
    }

    private void virusTarget() {
        if (currentTarget.hacked) {
            Thread tmpThread = new Thread(() -> {
                try {
                    print("Start virusing device...\n Copy from disk...");
                    Thread.sleep(1700);
                    print("Complited\nUpload to target...");
                    Thread.sleep(3000);
                    print("Complited\nLaunching...");
                    Thread.sleep(2100);
                    print("Complited. Computer successfully virused");
                    currentTarget.virused = true;
                } catch (InterruptedException ex) {
                    error("Interrupted error in virusing");
                    System.exit(1);
                }
            });
            tmpThread.start();
        } else {
            print("You need admin access");
        }
    }

    private void finishContract(String[] command) {
        try {
            boolean awarded = false;
            int userNumber = Base.stringToInt(command[1]);
            int[] currentContractsIds = new int[user.currentContracts.size()];
            for (int i = 0; i < user.currentContracts.size(); i++) {
                Contract get = user.currentContracts.get(i);
                currentContractsIds[i] = get.id;
            }
            for (int i = 0; i < currentContractsIds.length; i++) {
                int currentContractId = currentContractsIds[i];
                if (currentContractId == userNumber) {
                    if (user.searchForId(userNumber).isComplited(user)) {
                        Contract completedCon = user.searchForId(Base.stringToInt(command[1]));
                        print("You successfully completed a contract #" + command[1] + ", your award:\n"
                                + " Contract task difficulty: " + completedCon.getPriceOfContract()[0] + "\n"
                                + " Computer hack difficulty: " + completedCon.getPriceOfContract()[1]);
                        double allMoney = completedCon.getPriceOfContract()[0] + completedCon.getPriceOfContract()[1];
                        user.accounts.get(user.currentMainAccount).addMoney(allMoney);
                        user.currentContracts.remove(completedCon);
                        user.numberOfMoneyGetted += allMoney;
                        awarded = true;
                    } else {
                        print("You dont complete the mission");
                    }
                    break;
                }
            }
            if (!awarded) {
                print("Invalid number, check the missions page");
            }
        } catch (NumberFormatException ex) {
            print("Oh, please enter a number");
        }
    }

    private void money(String[] command) {
        try {
            if (command.length > 2) {
                if (command[1].equalsIgnoreCase("add")) {
                    user.addAccount(command[2]);
                } else if (command[1].equalsIgnoreCase("switch")) {
                    user.currentMainAccount = Base.stringToInt(command[2]);
                } else if (command.length > 4) {
                    if (command[1].equalsIgnoreCase("transfer")) {
                        moneyTransfer(command);
                    }
                }
            } else {
                print("Your accounts: ");
                int current = user.currentMainAccount;
                for (int i = 0; i < user.accounts.size(); i++) {
                    BankAccount get = user.accounts.get(i);
                    printNoN(" #" + i + " : " + get.getName() + " " + get.getId() + " " + get.getMoney() + "c ");
                    if (i == current) {
                        print("(current)");
                    } else {
                        print("");
                    }
                }
            }
        } catch (NumberFormatException ex) {
            print("Please use a numbers (you can read about this command >help cmd)");
        }
    }

    private void moneyTransfer(String[] command) throws NumberFormatException {
        int[] ids = new int[user.accounts.size()];
        boolean a = false, b = false;
        for (int i = 0; i < user.accounts.size(); i++) {
            ids[i] = i;
        }
        for (int i = 0; i < ids.length; i++) {
            if (Base.stringToInt(command[2]) == ids[i]) {
                a = true;
            }
        }
        for (int i = 0; i < ids.length; i++) {
            if (Base.stringToInt(command[3]) == ids[i]) {
                b = true;
            }
        }
        if (a && b) {
            if (user.accounts.get(Base.stringToInt(command[2])).getMoney() >= Base.stringToDouble(command[4])) {
                user.accounts.get(Base.stringToInt(command[3])).addMoney(Base.stringToDouble(command[4]));
                user.accounts.get(Base.stringToInt(command[2])).rmMoney(Base.stringToDouble(command[4]));
                print("Operation completed successfully");
            } else {
                print("Not enough credits");
            }
        } else {
            print("Invalid ids of accounts");
        }
    }

    private void admin() {
        Thread myThready = new Thread(() -> {
            AdminWindow cl = new AdminWindow(user,this);
            cl.setVisible(true);
        });
        myThready.start();
    }

    private void reset() {
        user = new User("Dmig", "*******", 0, 0, getPlates());
        try {
            FileWorker.delete("hAcKsave.hsf");
        } catch (FileNotFoundException ex) {
            error("Strange exception in Hacknet.reset()");
        }
        print("Your save file successfully reseted");
    }

    private void dc() {
        if (currentTarget != null) {
            currentTarget = null;
        }
    }
    
    private void mail() {
        currentTarget.hacked = true;
    }
    
    private void missions() {
        Thread myThready = new Thread(() -> {
            ContractsList cl = new ContractsList(user);
            cl.setList();
            cl.setVisible(true);
        });
        myThready.start();
    }

    private boolean scanForAH() { //SCAN FOR ANTIHACK IN COMPUTER (ADD GUI)
        boolean have = currentTarget.hasProtect(Protect.Type.ANTIHACK);
        if (have) {
            print("AntiHack finded");
        } else {
            print("AntiHack no finded");
        }
        return have;
    }

    protected Computer findTargetOfConInList(Computer comp) {
        Computer ret = null;
        for (Computer computer : computers) {
            if (comp.ipsEquals(computer)) {
                ret = computer;
                break;
            }
        }
        return ret;
    }
    
    protected void setTargetOfConInList(Computer comp) {
        for (Computer computer : computers) {
            if (comp.ipsEquals(computer)) {
                computer = comp;
                break;
            }
        }
    }

    private void genMission() {
        
        user.setListAvailableContracts(this);

        Thread myThready = new Thread(() -> {
            GetMissionGUI cl = new GetMissionGUI(user, this);
            cl.setList();
            cl.setVisible(true);
        });
        myThready.start();
        
//        Contract con = new Contract(user, this);
//        print(con.id + ", " + con.target.ip + ", " + con.missionShort);
//        user.currentContracts.add(con); //EEEEEEEEEEEEEEEEEEEEE
//        user.setGettedContractsNumber(user.getGettedContractsNumber() + 1);
    }

    /**
     *
     * @param subject
     */
    public void hack(String subject) {
        switch (subject) {
            case "SPro":
                hack(Protect.Type.SPRO);
                break;
            case "Proxy":
                hack(Protect.Type.PROXY);
                break;
            case "Firewall":
                hack(Protect.Type.FIREWALL);
                break;
            case "Alpha":
                hack(Protect.Type.ALPHA);
                break;
            case "Prote":
                hack(Protect.Type.PROTE);
                break;
            case "Zeus":
                hack(Protect.Type.ZEUS);
                break;
            case "AntiHack":
                if (scanForAH()) {
                    hack(Protect.Type.ANTIHACK);
                }
                break;
            case "Ello":
                hack(Protect.Type.ELLO);
                break;
            case "pass":
                if (currentTarget.userCanHackThis()) {
                    currentTarget.hack(user);
                    user.numberOfHackedComps++;
                } else {
                    print("Protects are not destroyed");
                }
                break;
            default:
                print("Not found subject");
        }
    }

    private boolean canHackProtect(Protect.Type type) {

        if (!new Protect(type).gateway) {
            return true;
        }

        switch (type) {
            case PROXY:
                if (currentTarget.protectHacked(Protect.Type.PROTE)) {
                    return true;
                }
            case FIREWALL:
                if (currentTarget.protectHacked(Protect.Type.PROXY)) {
                    return true;
                }
            case PROTE:
                if (currentTarget.protectHacked(Protect.Type.ZEUS)) {
                    return true;
                }
            case ZEUS:
                return true;
            case ANTIHACK:
                return true;
            default:
                return true;
        }
    }

    private void hack(Protect.Type type) {
        if (canHackProtect(type)) {
            if (currentTarget.hasProtect(type)) {
                currentTarget.getProtect(type).hackThis();
                user.numberOfProtectsHacked++;
            } else {
                print("Protect not finded");
            }
        } else {
            print("You can't hack this");
        }
    }

    /**
     *
     * @param ip
     */
    public void connect(String ip) {

        Computer target = null;

        print("Connecting...");

        for (int i = 0; i < computers.size(); i++) {
            Computer get = computers.get(i);
            if (get.ip.equals(ip)) {
                target = get;
            }
        }

        if (target != null) {
            boolean computerOnContract = false;
            for (int i = 0; i < user.currentContracts.size(); i++) {
                Computer get = user.currentContracts.get(i).target;
                if(get.ipsEquals(target)) computerOnContract = true;
            }
            if(!computerOnContract) {
                target.reloadComputer();
                setTargetOfConInList(target);
            }
            target.loadHacknetToProtect(this);
            currentTarget = target;
            print("Connected to " + target.nameComputer);
            AuthWindow aw = new AuthWindow();
            currentTarget.aw = aw;
            aw.setVisible(true);
            ListGUI filesGUI = new ListGUI(currentTarget, user);
            currentTarget.filesGUI = filesGUI;
        } else {
            print("Invalid ip");
        }

    }
    
    public static void error(String text) {
        JOptionPane.showMessageDialog(null, text, "Error", JOptionPane.ERROR_MESSAGE);
        LOG.warning("There are a error: "+text);
    }

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CommandType;
    private javax.swing.JTextArea Console;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(Hacknet.class.getName());
}
