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
    
    public final String name;
    
    private int[] cpus;
    private int[] ram;
    
    public Plate(int cpusNumber, int ramNumber, String name) {
        cpus = new int[cpusNumber];
        ram = new int[ramNumber];
        
        this.name = name;
    }

    public int[] getCpus() {
        return cpus;
    }

    public void setCpus(int[] cpus) {
        this.cpus = cpus;
    }

    public int[] getRam() {
        return ram;
    }

    public void setRam(int[] ram) {
        this.ram = ram;
    }
    
    
    
}
