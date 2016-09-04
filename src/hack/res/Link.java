/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack.res;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Dima
 */
public final class Link {

    public String[] readResArray(String file_name) throws IOException, FileNotFoundException {
        return readRes(file_name).split("\n");
    }

    public String readRes(String file_name) throws IOException, FileNotFoundException {
        InputStream is = this.getClass().getResourceAsStream(file_name);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bf = new BufferedReader(isr);

        String txtOfFile = "";

        while (bf.ready()) {
            txtOfFile += bf.readLine() + "\n";
        }

        return txtOfFile;
    }
}
