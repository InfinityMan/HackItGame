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

import java.util.logging.Logger;

/**
 *
 * @author Dima
 */
public final class CommandDistributor {

    Hacknet hacknet;
    
    String[] commandAll;
    String command;
    
    public CommandDistributor(String[] commandAll) {
        
    }
    
    private static final Logger LOG = Logger.getLogger(Hacknet.class.getName());
    
}
