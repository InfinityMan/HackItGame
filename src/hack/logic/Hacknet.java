/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack.logic;

import hack.gui.AuthWindow;
import hack.gui.ContractsList;
import hack.gui.AdminWindow;
import hack.gui.ListGUI;
import static hack.logic.User.load;
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

    public static final String GAME_VERSION = "0.6";

    public User user;

    public ArrayList<Computer> computers;

    public Computer currentTarget;

    public boolean loaded = false;
    public boolean debug = false;

    private String lastCommand = "";

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
        try {
            this.computers = (ArrayList<Computer>) deserData("CompsDataBase.comps");
        } catch (IOException ex) {
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
            JOptionPane.showMessageDialog(null, "Not loaded computers", "Error", JOptionPane.ERROR_MESSAGE);
            exit(1);
        }

        ArrayList<Computer> cmrs = new ArrayList<>();
        String[] comps = allComps.split("\n");
        for (String comp : comps) {
            String[] params = comp.split(",");
            cmrs.add(new Computer(Base.stringToInt(params[0]), params[1], params[2]));
        }

        Base.serData("CompsDataBase.comps", cmrs);
    }

    /**
     *
     */
    public static void init() {

        try {
            Util.setStyle();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("0");
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
        setMinimumSize(new java.awt.Dimension(400, 400));

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
        jScrollPane1.setViewportView(Console);

        jProgressBar1.setToolTipText("Trace");

        jProgressBar2.setToolTipText("RAM");

        jToggleButton1.setText("Pause");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CommandType, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
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
            user = load(this);
            LOG.log(Level.INFO, "User successfully loaded");
        } catch (IOException ex) {
            //user = new User("Dmig", "*******", 180, 17);
            updateBase();
            try {
                this.computers = (ArrayList<Computer>) deserData("CompsDataBase.comps");
            } catch (IOException e) {
                LOG.info("List of computers is not founded");
            }

            print("Save files are not finded : Register");
            registerUser(getPlates());
            print("User successfully registered");
            LOG.log(Level.WARNING, "IOException of load : {0}", ex.getMessage());
        }
        loaded = true;
        LOG.log(Level.INFO, "Hacknet inited");
    }
    
    private Plate[] getPlates() {
        
        ArrayList<Plate> plates = new ArrayList<>();

        String allPlates = "";
        
        try {
            allPlates = new Link().readRes("Plates.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Not loaded plates; exit from game");
            exit(1);
        }
        
        String[] platesStr = allPlates.split("\n");
        
        for (int i = 0; i < platesStr.length; i++) {
            String[] params = platesStr[i].split(",");
            int[] tempParams = new int[6];
            for (int j = 0; j < tempParams.length; j++) {
                tempParams[j] = Base.stringToInt(params[j]);
            }
            plates.add(new Plate(tempParams[0], tempParams[1], tempParams[2], tempParams[3], tempParams[4], tempParams[5], Base.stringToDouble(params[6]), params[7], Base.stringToInt(params[8])));
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
        } else if (command[0].startsWith("connect")) {
            if (currentTarget == null) {
                LOG.log(Level.INFO, "User try connect to {0}", command[1]);
                connect(command[1]);
            } else {
                print("You need to disconnect from current computer");
            }
        } else if (command[0].equalsIgnoreCase("scan")) {
            LOG.log(Level.INFO, "User scan the computer");
            print(currentTarget.printScan());
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
        } else if (command[0].startsWith("hack")) {
            if (command.length >= 2) {
                LOG.log(Level.INFO, "User want hack {0}", command[1]);
                hack(command[1]);
            } else {
                print("No found subject; type hack [tagret]");
            }
        } else if (command[0].equalsIgnoreCase("save")) {
            LOG.log(Level.INFO, "User save the game");
            dc();
            user.save();
            Base.serData("CompsDataBase.comps", computers);
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
            //test command
        } else if (command[0].equalsIgnoreCase("missions")) {
            missions();
        } else if (command[0].equalsIgnoreCase("mission")) {
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
        } else if (command[0].startsWith("finish")) {
            finishContract(command);
        } else if (command[0].equalsIgnoreCase("virus")) {
            virusTarget();
        } else {
            print("Invalid command");
        }
    }

    private void userExit() {
        LOG.log(Level.INFO, "User exit from game");
        dc();
        user.save();
        Base.serData("CompsDataBase.comps", computers);
        exit(0);
    }

    private void files() {
        if (currentTarget != null) {
            if (currentTarget.hacked) {
                Thread myThready = new Thread(() -> {
                    ListGUI c = new ListGUI(currentTarget, user);
                    c.setList();
                    c.setVisible(true);
                });
                myThready.start();
            } else {
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
                Logger.getLogger(Hacknet.class.getName()).log(Level.SEVERE, null, ex);
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
                    System.err.println(ex);
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
                    if (user.searchForId(userNumber).isComplited()) {
                        Contract completedCon = user.searchForId(Base.stringToInt(command[1]));
                        print("You successfully completed a contract #" + command[1] + ", your award:\n"
                                + " Contract task difficulty: " + completedCon.getPriceOfContract()[0] + "\n"
                                + " Computer hack difficulty: " + completedCon.getPriceOfContract()[1]);
                        double allMoney = completedCon.getPriceOfContract()[0] + completedCon.getPriceOfContract()[1];
                        user.accounts.get(user.currentMainAccount).addMoney(allMoney);
                        user.currentContracts.remove(completedCon);
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
            print("Please use a numbers (you can read about this command >help cmd");
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
            AdminWindow cl = new AdminWindow(user);
            cl.setVisible(true);
        });
        myThready.start();
    }

    private void reset() {
        user = new User("Dmig", "*******", 0, 0, getPlates());
        try {
            FileWorker.delete("hAcKsave.hsf");
        } catch (FileNotFoundException ex) {
            System.err.println(ex + " for reset save file");
        }
        print("Your save file successfully reseted");
    }

    public void dc() {
        currentTarget = null;
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

    /**
     *
     */
    public void mail() {
        currentTarget.hacked = true;
    }

    private void missions() {
        Thread myThready = new Thread(() -> {
            ContractsList cl = new ContractsList(user, false);
            cl.setList(user.currentContracts);
            cl.setVisible(true);
        });
        myThready.start();
    }

    private Computer findTargetOfConInList(Computer comp) {
        Computer ret = null;
        for (Computer computer : computers) {
            if (comp.equals(computer)) {
                ret = computer;
                break;
            }
        }
        return ret;
    }

    private void genMission() {
        Contract con = new Contract(Contract.Type.DESTROY, user);

        con.target = findTargetOfConInList(con.target);

        con.target.updateFileSys();

        if (con.type == Contract.Type.DESTROY || con.type == Contract.Type.COPY) {
            for (int i = 0; i < con.target.sizeOfListFiles(); i++) {
                System.out.println(con.target.getFile(i));
            }
            con.targetFile = con.target.getFile(Base.randomNumber(0, con.target.sizeOfListFiles() - 1));
            con.missionFull += "\n\n" + con.targetFile;
        }

        print(con.id + ", " + con.target.ip + ", " + con.missionShort);
        user.currentContracts.add(con); //EEEEEEEEEEEEEEEEEEEEE
        user.setGettedContractsNumber(user.getGettedContractsNumber() + 1);
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
            target.loadHacknetToProtect(this);
            currentTarget = target;
            print("Connected to " + target.nameComputer);
            AuthWindow aw = new AuthWindow();
            currentTarget.aw = aw;
            aw.setVisible(true);
        } else {
            print("Invalid ip");
        }

    }

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

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
