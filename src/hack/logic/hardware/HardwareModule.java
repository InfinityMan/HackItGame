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
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dima
 */
public abstract class HardwareModule implements Serializable {
    
    public static final double[] DEFAULT_POWER_RAM_AND_CPU = {0.5,1,2,5,10,21,40,82};
    public static final double[] DEFAULT_PRICE_RAM_AND_CPU = {40,100,250,600,1300,2800,6000,20000};
    
    public static final double[] DEFAULT_POWER_HARD = {0.5,1,2,4};
    public static final double[] DEFAULT_PRICE_HARD = {50,150,500,2000};
    
    public static final double[] DEFAULT_POWER_INTERNET = {1.5,4,10,20};
    public static final double[] DEFAULT_PRICE_INTERNET = {100,400,1500,4000};

    public Double price;
    public Double power;

    public String name;
    
    /**
     * Load a hardware array
     * @param type Type of hardware; 0 = CPU, 1 = RAM, 2 = HHD, 3 = internet
     * @param upgraded For ram and cpu; for anything else ignored
     * @return Hardware array
     */
    
    public static final HardwareModule[] loadHardware(int type, boolean upgraded) {
        String textOfFile = "-1";
        try {
            textOfFile = new hack.res.Link().readRes("Hardware.txt");
        } catch (IOException ex) {
            Hacknet.error("Error in HardwareModule; Unable to load hardware");
        }
        String[] typesOfModulesNames = textOfFile.split("\n\n");
        String[] names;
        boolean doubleModules = false;
        if(type == 0 || type == 1) {
            String[] clusters = typesOfModulesNames[type].split("\n");
            if(upgraded) {
                names = clusters[1].split(";");
            } else {
                names = clusters[0].split(";");
            }
        } else {
            names = typesOfModulesNames[type].split(";");
        }
        
        HardwareModule[] ret = null;
        
        switch(type) {
            case 0:
                ret = new CpuModule[DEFAULT_POWER_RAM_AND_CPU.length/2];
                for (int i = 0; i < ret.length; i++) {
                    int index = i;
                    if(upgraded) index += 4;
                    ret[i] = new CpuModule(names[i], DEFAULT_POWER_RAM_AND_CPU[index], DEFAULT_PRICE_RAM_AND_CPU[index], upgraded);
                }
                break;
            case 1:
                ret = new RamModule[DEFAULT_POWER_RAM_AND_CPU.length/2];
                for (int i = 0; i < ret.length; i++) {
                    int index = i;
                    if(upgraded) index += 4;
                    ret[i] = new RamModule(names[i], DEFAULT_POWER_RAM_AND_CPU[index], DEFAULT_PRICE_RAM_AND_CPU[index], upgraded);
                }
                break;
            case 2:
                ret = new HardDriveModule[DEFAULT_POWER_HARD.length];
                for (int i = 0; i < ret.length; i++) {
                    ret[i] = new HardDriveModule(names[i],DEFAULT_POWER_HARD[i],DEFAULT_PRICE_HARD[i]);
                }
                break;
            case 3:
                ret = new InternetModule[DEFAULT_POWER_INTERNET.length];
                for (int i = 0; i < ret.length; i++) {
                    ret[i] = new InternetModule(names[i],DEFAULT_POWER_INTERNET[i],DEFAULT_PRICE_INTERNET[i]);
                }
                break;
            default:
                Hacknet.error("Error with loading hardware in HardwareModule");
                System.exit(1);
        }
        
        return ret;
    }

    @Override
    public String toString() {
        return name+";Price: "+price+";Power: "+power;
    }
    
    

}
