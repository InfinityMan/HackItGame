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
package hack.gui;

import java.util.ArrayList;
import java.util.Arrays;
import ru.epiclib.base.Base;

/**
 *
 * @author Dima
 */
public class LabelHack extends javax.swing.JFrame {

    boolean[] elementsCompleted = {false, false, false, false, false, false, false, false};

    public String key = "";

    public enum TypeOfChars {
        ONLY_SMALL, ONLY_BIG, ONLY_NUMBERS, SMALL_AND_NUMBERS, BIG_AND_NUMBERS, BIG_AND_SMALL, ALL
    };

    public enum Difficulty {
        VERY_LOW, LOW, NORMAL, HIGH, VERY_HIGH
    };

    public LabelHack() {
        initComponents();
    }
    
    public void cheat(TypeOfChars type, Difficulty difficulty) {
        start(type,difficulty,1);
    }

    public void start(TypeOfChars type, Difficulty difficulty, int allTimeInSeconds) {

        double temp = allTimeInSeconds / 8;

        Double d = Math.floor(temp);

        int secondsForElement = d.intValue();
        int extraTime = allTimeInSeconds % 8;
        int timesInSeconds = 0;

        switch (difficulty) {
            case VERY_LOW:
                timesInSeconds = 10;
                break;
            case LOW:
                timesInSeconds = 8;
                break;
            case NORMAL:
                timesInSeconds = 5;
                break;
            case HIGH:
                timesInSeconds = 2;
                break;
            case VERY_HIGH:
                timesInSeconds = 1;
                break;
        }
        
        for (int i = 0; i < 8; i++) {
            int a = secondsForElement;
            if(i == 0) a += extraTime;
            jetElement(a, timesInSeconds, type, i);
        }

    }

    private void jetElement(int seconds, int timesInSecond, TypeOfChars typeOfChars, int number) {

        int waitInSeconds = 1000 / timesInSecond;
        ArrayList<String> aviaStrings = new ArrayList<>();

        switch (typeOfChars) {
            case ONLY_NUMBERS:
                addNumbers(aviaStrings);
                break;
            case SMALL_AND_NUMBERS:
                addNumbers(aviaStrings);
                addSmall(aviaStrings);
                break;
            case ONLY_SMALL:
                addSmall(aviaStrings);
                break;
            case ONLY_BIG:
                addBig(aviaStrings);
                break;
            case BIG_AND_SMALL:
                addSmall(aviaStrings);
                addBig(aviaStrings);
                break;
            case BIG_AND_NUMBERS:
                addBig(aviaStrings);
                addNumbers(aviaStrings);
                break;
            case ALL:
                addNumbers(aviaStrings);
                addSmall(aviaStrings);
                addBig(aviaStrings);
                break;
        }

        javax.swing.JLabel label = new javax.swing.JLabel();

        switch (number) {
            case 0:
                label = jLabel1;
                break;
            case 1:
                label = jLabel2;
                break;
            case 2:
                label = jLabel3;
                break;
            case 3:
                label = jLabel4;
                break;
            case 4:
                label = jLabel5;
                break;
            case 5:
                label = jLabel6;
                break;
            case 6:
                label = jLabel7;
                break;
            case 7:
                label = jLabel8;
                break;
        }

        int iterations = 0;

        try {
            Thread.sleep(seconds * 1000);
            String charTemp = aviaStrings.get(Base.randomNumber(0, aviaStrings.size() - 1));
            label.setText(charTemp);
            key += charTemp;

//        for (int i = 0; i < seconds; i++) {
//            for (int j = 0; j < timesInSecond; j++) {
//                label.setText(aviaStrings.get(iterations));
//                try {
//                    Thread.sleep(waitInSeconds);
//                } catch (InterruptedException ex) {
//                    System.err.println("InterCOR");
//                    System.exit(1);
//                }
//                
//            }
//        }
        } catch (InterruptedException ex) {
            System.err.println("InterCOR");
            System.exit(1);
        }

    }

    private void addNumbers(ArrayList<String> start) {
        for (int i = 0; i < 10; i++) {
            start.add(i + "");
        }
    }

    private void addSmall(ArrayList<String> start) {
        start.addAll(Arrays.asList(Base.ENGALPHAVET));
    }

    private void addBig(ArrayList<String> start) {
        start.addAll(Arrays.asList(Base.ENGALPHAVETCAPS));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Hacking");
        setMinimumSize(new java.awt.Dimension(462, 72));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("?");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("?");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("?");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("?");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("?");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("?");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("?");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("?");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
