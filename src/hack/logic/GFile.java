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
