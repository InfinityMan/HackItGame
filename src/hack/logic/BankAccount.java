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
 *
 * @author Dima
 */
public class BankAccount implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;

    private Double money;

    private boolean blocked;

    public BankAccount(String name) {
        id = genId();
        this.name = name;
        money = 0d;
        blocked = false;
    }

    /**
     * Gen the id "cnnn-nnnn"
     *
     * @return id
     */
    public final String genId() {
        String c = Base.randomString(1,true,false,false);

        for (int i = 0; i < 8; i++) {
            if (i != 3) {
                c += Base.randomNumber(0, 9);
            } else {
                c += "-";
            }
        }

        return c;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Double getMoney() {
        return money;
    }

    public void addMoney(Double a) {
        money += a;
    }

    public void rmMoney(Double a) {
        money -= a;
    }

}
