/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack.logic;

import java.io.Serializable;

/**
 *
 * @author Dima
 */
public class Plate implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public final String name;
    
    private int[] cpus;
    private int[] uprgCpus;
    
    private int[] ramDDR3;
    private int[] ramDDR4;
    
    private int[] hardDrive;
    
    private int[] internet;
    
    private double price;
    
    public Plate(int cpusNum, int uprgCpusNum, int ram3Num, int ram4Num, int hardNum, int intNum, double price, String name) {
        cpus = new int[cpusNum];
        uprgCpus = new int[uprgCpusNum];
        
        ramDDR3 = new int[ram3Num];
        ramDDR4 = new int[ram4Num];
        
        hardDrive = new int[hardNum];
        internet = new int[intNum];
        
        this.name = name;
    }

    public int[] getCpus() {
        return cpus;
    }

    public void setCpus(int[] cpus) {
        this.cpus = cpus;
    }

    public int[] getUprgCpus() {
        return uprgCpus;
    }

    public void setUprgCpus(int[] uprgCpus) {
        this.uprgCpus = uprgCpus;
    }

    public int[] getRamDDR3() {
        return ramDDR3;
    }

    public void setRamDDR3(int[] ramDDR3) {
        this.ramDDR3 = ramDDR3;
    }

    public int[] getRamDDR4() {
        return ramDDR4;
    }

    public void setRamDDR4(int[] ramDDR4) {
        this.ramDDR4 = ramDDR4;
    }

    public int[] getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(int[] hardDrive) {
        this.hardDrive = hardDrive;
    }

    public int[] getInternet() {
        return internet;
    }

    public void setInternet(int[] internet) {
        this.internet = internet;
    }

}
