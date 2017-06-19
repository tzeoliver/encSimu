package com.company;

/**
 * Created by juhanikula on 19/06/17.
 */
public class MonkClass extends Character {

    int level;
    int ki;

    public MonkClass(int level, int ac, int hp, int toHit, int attackDmg, int multiattack, int dexterityMod) {
        super("player", ac, hp, toHit, attackDmg, multiattack, dexterityMod);
        this.level = level;
    }

    public void bonusAction() {
        //Hit with fist

        //roll to attack

        //roll for damage

        //return int[] [attackRoll, damageRoll, type?]
    }
}
