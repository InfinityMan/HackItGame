/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack.logic;

import ru.epiclib.base.Base;

/**
 *
 * @author Dima
 */
public class BankAccount {

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
        String c = Base.randomString(1);

        for (int i = 0; i < 8; i++) {
            if (i != 3) {
                c += Base.randomNumber(0, 10);
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
        money += a;
    }

}
