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

import hack.gui.MiniHacknet;
import java.io.Serializable;
import static java.lang.System.exit;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.epiclib.base.Base;
import ru.epiclib.gui.Util;
import static java.lang.Thread.sleep;

/**
 *
 * @author 1234
 */
public class Protect implements Serializable {

    private static final long serialVersionUID = 1L;

    public int needPower;

    public boolean gateway;

    transient public Hacknet hacknet;

    public boolean open = false; //or "hacked"

    public enum Type {
        ALPHA, ANTIHACK, ELLO, FIREWALL, PROTE, PROXY, SPRO, ZEUS
    };
    public Type type;

    //------------------------------
    public Protect(Type type, Hacknet hacknet) {
        this(type);
        this.hacknet = hacknet;
    }

    public Protect(Type type) {

        this.type = type;

        int needSeconds;

        switch (type) {
            case ALPHA:
                gateway = false;
                needSeconds = 60;
                break;
            case ANTIHACK:
                gateway = true;
                needSeconds = 45;
                break;
            case ELLO:
                gateway = false;
                needSeconds = 120;
                break;
            case FIREWALL:
                gateway = true;
                needSeconds = 45;
                break;
            case PROTE:
                gateway = true;
                needSeconds = 70;
                break;
            case PROXY:
                gateway = true;
                needSeconds = 30;
                break;
            case SPRO:
                gateway = false;
                needSeconds = 20;
                break;
            case ZEUS:
                gateway = true;
                needSeconds = 180;
                break;
            default:
                gateway = false;
                needSeconds = 0;
        }

        needPower = needSeconds * Computer.CPUS_POWER[1];

    }

    public void hackThis() {

        try {
            Util.setStyle();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Protect.class.getName()).log(Level.SEVERE, null, ex);
        }

        Thread myThready = new Thread(() -> {
            MiniHacknet mh;
            mh = new MiniHacknet();
            mh.setVisible(true);

            switch (type) {
                case ALPHA:
                    break;
                case ANTIHACK:
                    break;
                case ELLO:
                    break;
                case FIREWALL:
                    break;
                case PROTE:
                    break;
                case PROXY:
                    for (int i = needPower; i > 0; i = i - hacknet.user.getPowerCPU() * 2) {
                        mh.print(Base.randomString(16,true,false,true));
                        try {
                            sleep(500);
                        } catch (InterruptedException ex) {
                            exit(1);
                        }
                    }
                    break;
                case SPRO:
                    for (int i = needPower; i > 0; i = i - hacknet.user.getPowerCPU()) {
                        mh.print(Base.randomBinary(16));
                        try {
                            sleep(250);
                        } catch (InterruptedException ex) {
                            exit(1);
                        }
                    }
                    break;
                case ZEUS:
                    break;
                default:
                    throw new Error("Protect.hack.switch:default!!!");
            }

            mh.print("Complete hack of " + printThis());
            hacknet.print("Complete hack of " + printThis());
            open = true;
            try {
                sleep(1_500);
            } catch (InterruptedException ex) {
                exit(1);
            }
            mh.dispose();
        });
        myThready.start();
    }

    public String printThis() {
        switch (type) {
            case ALPHA:
                return "Alpha";
            case ANTIHACK:
                return "Antihack";
            case ELLO:
                return "Ello";
            case FIREWALL:
                return "Firewall";
            case PROTE:
                return "Prote";
            case PROXY:
                return "Proxy";
            case SPRO:
                return "SPro";
            case ZEUS:
                return "Zeus";
            default:
                return "Unknown type";
        }
    }

}
