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

    public static final String[] OS = {"Doors 95", "Doors 2000", "Iris 2", "Nickel 4", "Steel", "Tungusten Pro"};

    public final String name;
    public final int power;
    public final String os;

    private final double price;

    private int[] cpus;
    private int[] uprgCpus;

    private int[] ramDDR3;
    private int[] ramDDR4;

    private int[] hardDrive;

    private int[] internet;

    public Plate(int cpusNum, int uprgCpusNum, int ram3Num, int ram4Num, int hardNum, int intNum, double price, String name, int power) {
        cpus = new int[cpusNum];
        uprgCpus = new int[uprgCpusNum];

        ramDDR3 = new int[ram3Num];
        ramDDR4 = new int[ram4Num];

        hardDrive = new int[hardNum];
        internet = new int[intNum];

        this.name = name;

        this.power = power;
        this.os = OS[power];

        this.price = price;
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

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Plate{" + "name=" + name + ", power=" + power + ", os=" + os + ", price=" + price + ", cpus=" + cpus.length + ", uprgCpus=" + uprgCpus.length + ", ramDDR3=" + ramDDR3.length + ", ramDDR4=" + ramDDR4.length + ", hardDrive=" + hardDrive.length + ", internet=" + internet.length + '}';
    }
    
    

}
