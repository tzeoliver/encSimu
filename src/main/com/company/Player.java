package com.company;

import java.util.Random;

/**
 * Created by juhanikula on 07/06/17.
 */
public class Player extends Character {

    private int deathSaveSuccess;
    private int deathSaveFails;

    public Player(int ac, int hp, int toHit, int avgDmg) {
        super("player", ac, hp, toHit, avgDmg);

        this.deathSaveFails = 0;
        this.deathSaveSuccess = 0;
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
