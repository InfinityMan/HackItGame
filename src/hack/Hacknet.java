/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack;

import static hack.User.load;
import static java.awt.EventQueue.invokeLater;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.logging.Logger;
import static javax.swing.UIManager.getCrossPlatformLookAndFeelClassName;
import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import javax.swing.UnsupportedLookAndFeelException;
import ru.epiclib.base.Base;
import static ru.epiclib.base.Base.deserData;
import static javax.swing.UIManager.setLookAndFeel;

/**
 *
 * @author 1234
 */
public final class Hacknet extends javax.swing.JFrame {
    
    public User user;
    
    public ArrayList<Computer> computers;
    
    public Computer currentTarget;
    
    public boolean loaded = false;

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
    
    /**
     *
     */
    public static void init() {

        try {
            // Set System L&F
            setLookAndFeel(getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            try {
                // Set cross-platform Java L&F (also called "Metal")
                setLookAndFeel(getCrossPlatformLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                // handle exception
            }
            // handle exception
            // handle exception
            // handle exception
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            // handle exception
        }
        // handle exception
        // handle exception

        invokeLater(() -> {
            new Hacknet().setVisible(true);
        });

    }
    
    /**
     *
     * @param s
     */
    public void print(String s) {
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

    private void CommandTypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CommandTypeKeyPressed
        if(evt.getKeyCode() == VK_ENTER) {
            
            if(!loaded) {
                print("Hello. This is only developer verison : 0.2");
                //user = load(this);
                user = new User("Dmig", "*******", 160, 17);
                loaded = true;
                /*for (int i = 0; i < computers.size(); i++) {
                Computer get = computers.get(i);
                System.out.println(get.print());
                }*/
            }
            
            String commandAll = CommandType.getText();
            print("> "+commandAll);
            CommandType.setText("");
            
            String[] command = commandAll.split(" ");
            
            if(command[0].equalsIgnoreCase("mail")) {
                mail();
            } else if(command[0].startsWith("connect")) {
                connect(command[1]);
            } else if(command[0].equalsIgnoreCase("scan")) {
                print(currentTarget.printScan());
            } else if(command[0].equalsIgnoreCase("list")) {
                for (int i = 0; i < computers.size(); i++) {
                    Computer get = computers.get(i);
                    System.out.println(get.print());
                }
            } else if(command[0].equalsIgnoreCase("auth")) {
                AuthWindow aw = new AuthWindow();
                aw.setVisible(true);
                currentTarget.aw = aw;
            } else if(command[0].startsWith("hack")) {
                hack(command[1]);
            } else if(command[0].equalsIgnoreCase("save")) {
                dc();
                user.save();
                Base.serData("CompsDataBase.comps", computers);
            } else if(command[0].equalsIgnoreCase("load")) {
                user = load(this);
            } else if(command[0].equalsIgnoreCase("exit")) {
                dc();
                user.save();
                Base.serData("CompsDataBase.comps", computers);
                exit(0);
            } else if(command[0].equalsIgnoreCase("stats")) {
                print(user.print());
            } else if(command[0].equalsIgnoreCase("missions")) {
                missions();
            } else if(command[0].equalsIgnoreCase("mission")) {
                genMission();
            } else if(command[0].equalsIgnoreCase("rm")) {
                rm(new GFile(command[1]));
            } else if(command[0].equalsIgnoreCase("dc")) {
                dc();
            } else if(command[0].startsWith("com")) {
                try {
                    if (Base.stringToInt(command[1]) > 0 && Base.stringToInt(command[1]) < user.gettedContractsNumber-1) {
                        if (user.searchForId(Base.stringToInt(command[1])).isComplited(user.ip)) { //TODO (Null)
                            //award
                            print("You successfully completed a contract " + command[1]);
                            user.currentContracts.remove(user.searchForId(Base.stringToInt(command[1])));
                        }
                    } else {
                        print("Nope, this contract can not be");
                    }
                } catch (NumberFormatException ex) {
                    print("Oh, please enter a number");
                }
            } else if(command[0].equalsIgnoreCase("virus")) {
                if(currentTarget.hacked) {
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
            } else {
                print("Invalid command");
            }
            
        }
    }//GEN-LAST:event_CommandTypeKeyPressed

    public void dc() {
        currentTarget = null;
    }
    
    public void rm(GFile file) {
        if(currentTarget != null) {
            if(currentTarget.hacked) {
                if(currentTarget.hasFile(file)) {
                    if(currentTarget.rmFile(file, user.ip)) {
                        print("File "+file+" deleted");
                    } else {
                        print("Super error");
                    }
                } else {
                    print("Oh, file is not exits");
                }
            } else {
                print("Computer is not hacked");
            }
        }
    }
    
    
    /**
     *
     */
    public void mail() {
        currentTarget.hacked = true;
    }
    
    private void missions() {
        Thread myThready = new Thread( () -> {
            ContractsList cl = new ContractsList(user,false);
            cl.setList(user.currentContracts);
            cl.setVisible(true);
        });
        myThready.start();
    }
    
    private void genMission() {
        Contract con = new Contract(Contract.Type.DESTROY, user);
        print(con.id+", "+con.target.ip+", "+con.missionShort);
        user.currentContracts.add(con); //EEEEEEEEEEEEEEEEEEEEE
        user.gettedContractsNumber++;
    }
    
    /**
     *
     * @param subject
     */
    public void hack(String subject) {
        switch (subject) {
            case "SPro":
                hack(1);
                break;
            case "Proxy":
                hack(2);
                break;
            case "HTTPPort":
                hack(7);
                break;
            case "SQLPort":
                hack(9);
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
    
    private void hack(int idOfProtect) {
        currentTarget.getProtect(idOfProtect).hackThis();
    }
    
    /**
     *
     * @param ip
     */
    public void connect(String ip) {
        
        Computer target = null;
        
        print("Connecting...");
        
        /*try {
        Thread.sleep(1000);
        } catch (InterruptedException ex) {
        System.err.println("Problem Prg.");
        System.exit(1);
        }*/
        
        for (int i = 0; i < computers.size(); i++) {
            Computer get = computers.get(i);
            if(get.ip.equals(ip)) target = get;
        }
        
        if(target != null) {
            target.loadHacknetToProtect(this);
            currentTarget = target;
            print("Connected to "+target.nameComputer);
            new AuthWindow().setVisible(true);
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
