/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack.logic.exceptions;

/**
 *
 * @author Dima
 */
public class ComputerIsNotHackedException extends Exception {

    /**
     * Creates a new instance of <code>ComputerIsNotHackedException</code>
     * without detail message.
     */
    public ComputerIsNotHackedException() {
    }

    /**
     * Constructs an instance of <code>ComputerIsNotHackedException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ComputerIsNotHackedException(String msg) {
        super(msg);
    }
}
