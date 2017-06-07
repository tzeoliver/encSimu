package com.company;

import java.util.Random;

/**
 * Created by juhanikula on 05/06/17.
 */
public class Character {

    String type;
    String condition; //Conditions might be a list in the future?
    int ac;
    int hp;
    int toHit;
    int avgDmg;
    private int deathSaveSuccess;
    private int deathSaveFails;

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

    public void rollDeathSavingThrow() {
        Random rnd = new Random();
        int deathSave = rnd.nextInt(20) + 1;

        if (deathSave == 1) {
            this.deathSaveFails += 2;
        } else if (deathSave < 10) {
            this.deathSaveFails++;
        } else if (deathSave == 20) {
            this.deathSaveSuccess += 2;
        } else {
            this.deathSaveSuccess++;
        }

        if (this.deathSaveFails >= 3) {
            this.condition = "dead";
        } else if (this.deathSaveSuccess >= 3) {
            this.condition = "conscious";
            this.hp = 1;
        }
    }

}
