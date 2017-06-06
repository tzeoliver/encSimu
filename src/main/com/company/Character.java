package com.company;

import java.util.Objects;

/**
 * Created by juhanikula on 05/06/17.
 */
public class Character {

    String type;
    String condition;
    int ac;
    int hp;
    int toHit;
    int avgDmg;

    public Character(String type, int ac, int hp, int toHit, int avgDmg) {
        this.type = type;
        this.ac = ac;
        this.hp = hp;
        this.toHit = toHit;
        this.avgDmg = avgDmg;
        this.condition = "conscious";
    }

    @Override
    public String toString() {
        return type + " with" +
                " AC=" + ac +
                ", HP=" + hp +
                ", toHit=" + toHit +
                ", avgDmg=" + avgDmg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getToHit() {
        return toHit;
    }

    public void setToHit(int toHit) {
        this.toHit = toHit;
    }

    public int getAvgDmg() {
        return avgDmg;
    }

    public void setAvgDmg(int avgDmg) {
        this.avgDmg = avgDmg;
    }

    public String takeDamage(int dmg) {
        this.hp = this.hp-dmg;
        if (this.hp <= 0) {
            this.condition = "unconscious";
        }
        return this.condition;
    }


}
