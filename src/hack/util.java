/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.epiclib.base.Base;
import ru.epiclib.base.FileWorker;

/**
 *
 * @author Dima
 */
public class util {
    public static void main(String[] args) {
        updateBase();
    }
    
    public static void updateBase() {
        try {
            FileWorker.delete("CompsDataBase.comps");
        } catch (FileNotFoundException ex) {}
        ArrayList<Computer> cmrs = new ArrayList<>();
        cmrs.add(new Computer(4, "Crystal", "Crys"));
        cmrs.add(new Computer(1, "Red", "Red"));
        cmrs.add(new Computer(5, "Mios", "Mi"));
        cmrs.add(new Computer(2, "Hato", "Hato"));
        cmrs.add(new Computer(0, "Andre", "An"));
        cmrs.add(new Computer(3, "Bino", "Bin"));
        cmrs.add(new Computer(1, "Fero", "Fer"));
        cmrs.add(new Computer(2, "Kir", "Kir"));
        cmrs.add(new Computer(0, "Ivan", "Ivan"));
        cmrs.add(new Computer(4, "Mirt", "Mirt"));
        cmrs.add(new Computer(2, "Fios", "Fios"));
        cmrs.add(new Computer(1, "Alt", "Alt"));
        cmrs.add(new Computer(3, "Steos", "Ste"));
        cmrs.add(new Computer(5, "Goegle", "Gog"));
        cmrs.add(new Computer(0, "Tea", "Tea"));
        Base.serData("CompsDataBase.comps", cmrs);
    }
}
