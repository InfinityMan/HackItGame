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
package hack.logic.hardware;

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
