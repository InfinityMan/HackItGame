/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack.logic;

import java.io.Serializable;
import ru.epiclib.base.Base;

/**
 * Class of game file
 * @author Dima
 */
public class GFile implements Serializable {
    
    /**
     * Text in file
     */
    public String text; 
    /**
     * Name of file withnout format
     */
    public String name; //like "hello"
    
    /**
     * Type of format enum
     */
    public enum Format {DLL,TXT,SYS,DAT};
    /**
     * Type of format of file
     */
    public Format format;
    
    /**
     * Standart generator for special files
     * @param text Text in file
     * @param nameWithFormat [NameOfFile].[Format]
     */
    
    public GFile(String text,String nameWithFormat) {
        this.text = text;
        String[] name;
        
        name = nameWithFormat.split(".");
        this.name = name[0];
        switch (name[1]) {
            case "dll":
                format = Format.DLL;
                break;
            case "txt":
                format = Format.TXT;
                break;
            case "sys":
                format = Format.SYS;
                break;
            case "dat":
                format = Format.DAT;
                break;
        }
    }
    
    /**
     * Constructor for basic files ([Prefix]-[Nums].dat)
     * @param prefix Prefix in file name
     */
    public GFile(String prefix) {
        
        final int minSymbolsInStroke = 12;
        final int maxSymbolsInStroke = 31;
        
        int type = Base.randomNumber(0, 3); //nums;letters;num+let;all
        int numOfStrokes = Base.randomNumber(7, 21);
        
        String numsInFileName = Base.randomNumber(101, 99999999) + "";
        
        String name = prefix + "-" + numsInFileName;
        
        String[] text = new String[numOfStrokes];
        
        for (int i = 0; i < numOfStrokes; i++) {
            switch (type) {
                case 0:
                    text[i] = Base.randomString(Base.randomNumber(minSymbolsInStroke, maxSymbolsInStroke), false, false, true);
                    break;
                case 1:
                    text[i] = Base.randomString(Base.randomNumber(minSymbolsInStroke, maxSymbolsInStroke), true, false, false);
                    break;
                case 2:
                    text[i] = Base.randomString(Base.randomNumber(minSymbolsInStroke, maxSymbolsInStroke), true, false, true);
                    break;
                case 3:
                    text[i] = Base.randomString(Base.randomNumber(minSymbolsInStroke, maxSymbolsInStroke), true, true, true);
                    break;
            }
        }
        
        String totalText = "";
        
        for (String string : text) {
            totalText += string + "\n";
        }
        
        this.text = totalText;
        this.name = name;
        this.format = Format.DAT;
    }
    
    
}
