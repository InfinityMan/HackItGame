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

import hack.logic.Hacknet;
import java.io.Serializable;

/**
 *
 * @author Dima
 */
public class Plate implements Serializable {

    private static final long serialVersionUID = 2L;

    public static final String[] OS = {"Doors 95", "Doors 2000", "Iris 2", "Nickel 4", "Steel", "Tungusten Pro"};

    public final String name;
    public final int power;
    public final String os;

    private final double price;

    private CpuModule[] cpus;
    private CpuModule[] uprgCpus;

    private RamModule[] ramDDR3;
    private RamModule[] ramDDR4;

    private HardDiskModule[] hardDrive;

    private InternetModule[] internet;

    public Plate(int cpusNum, int uprgCpusNum, int ram3Num, int ram4Num, int hardNum, int intNum, double price, String name, int power, Hacknet hacknet) {
        cpus = new CpuModule[cpusNum];
        uprgCpus = new CpuModule[uprgCpusNum];

        ramDDR3 = new RamModule[ram3Num];
        ramDDR4 = new RamModule[ram4Num];

        hardDrive = new HardDiskModule[hardNum];
        internet = new InternetModule[intNum];

        this.name = name;

        this.power = power;
        this.os = OS[power];

        this.price = price;
        
        installDefaultHardware(hacknet);
    }
    
    public final void installDefaultHardware(Hacknet hacknet) {
        if(cpus.length > 0) {
            cpus[0] = hacknet.cpus[1];
        } else if(uprgCpus.length > 0) {
            uprgCpus[0] = hacknet.cpusUp[0];
        } else {
            Hacknet.error("Error with loading plates: Plate class");
        }
        
        if(ramDDR3.length > 0) {
            ramDDR3[0] = hacknet.rams[1];
        } else if(ramDDR4.length > 0) {
            ramDDR4[0] = hacknet.ramsUp[0];
        } else {
            Hacknet.error("Error with loading plates: Plate class");
        }
        
        if(hardDrive.length > 0) {
            hardDrive[0] = hacknet.hards[0];
        } else {
            Hacknet.error("Error with loading plates: Plate class");
        }
        
        if(internet.length > 0) {
            internet[0] = hacknet.internets[0];
        } else {
            Hacknet.error("Error with loading plates: Plate class");
        }
    }

    public CpuModule[] getCpus() {
        return cpus;
    }

    public void setCpus(CpuModule[] cpus) {
        this.cpus = cpus;
    }

    public CpuModule[] getUprgCpus() {
        return uprgCpus;
    }

    public void setUprgCpus(CpuModule[] uprgCpus) {
        this.uprgCpus = uprgCpus;
    }

    public RamModule[] getRamDDR3() {
        return ramDDR3;
    }

    public void setRamDDR3(RamModule[] ramDDR3) {
        this.ramDDR3 = ramDDR3;
    }

    public RamModule[] getRamDDR4() {
        return ramDDR4;
    }

    public void setRamDDR4(RamModule[] ramDDR4) {
        this.ramDDR4 = ramDDR4;
    }

    public HardDiskModule[] getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(HardDiskModule[] hardDrive) {
        this.hardDrive = hardDrive;
    }

    public InternetModule[] getInternet() {
        return internet;
    }

    public void setInternet(InternetModule[] internet) {
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
