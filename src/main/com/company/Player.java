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
}
