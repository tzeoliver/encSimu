package com.company;

import java.util.Random;

/**
 * Created by juhanikula on 05/06/17.
 */
public class Character implements Comparable<Character>{

    String type;
    String condition; //Conditions might be a list in the future?
    int ac;
    int maxHp;
    int hp;
    int toHit;
    int avgDmg;
    private int initiative;
    int initiativeMod;

    //Attack action


    //Player specific
    private int deathSaveSuccess;
    private int deathSaveFails;

    //ability scores
    int strength;
    int dexterity;
    int constitution;
    int intellect;
    int wisdom;
    int charisma;

    public Character(String type, int ac, int hp, int toHit, int avgDmg) {
        this(type,ac,hp,toHit,avgDmg,0);
    }

    public Character(String type, int ac, int hp, int toHit, int avgDmg, int initiativeMod) {
        super();
        this.type = type;
        this.ac = ac;
        this.hp = hp;
        this.maxHp = hp;
        this.toHit = toHit;
        this.avgDmg = avgDmg;
        this.condition = "conscious";
        this.initiativeMod = initiativeMod;
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

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int compareTo(Character compareCharacter) {

        int compareInitiative = ((Character) compareCharacter).getInitiative();

        //ascending order
        //return this.initiative - compareInitiative;

        //descending order
        return compareInitiative - this.initiative;
    }

    public String takeDamage(int dmg) {
        this.hp = this.hp-dmg;
        if (this.hp <= 0) {
            if (Math.abs(this.hp) >= this.maxHp) {
                this.condition = "dead";
            } else {
                this.condition = "unconscious";
            }
        }
        return this.condition;
    }

    public String rollDeathSavingThrow() {
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

        return this.condition;
    }

}
