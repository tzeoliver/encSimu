package com.company;

/**
 * Created by juhanikula on 08/06/17.
 */
public class Weapon {

    boolean melee;
    boolean ranged;
    boolean magical;

    String damagetype;
    int[] dice;

    public Weapon(boolean melee, String damagetype, int[] dice){
        this(melee,false,false, damagetype, dice);
    }

    public Weapon(boolean melee, boolean ranged, boolean magical, String damagetype, int[] dice) {
        this.melee = melee;
        this.ranged = ranged;
        this.magical = magical;
        this.damagetype = damagetype;
        this.dice = dice;
    }
}
