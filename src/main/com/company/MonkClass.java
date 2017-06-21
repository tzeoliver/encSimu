package com.company;

import java.util.ArrayList;

/**
 * Created by juhanikula on 19/06/17.
 */
public class MonkClass extends Character {

    int level;
    int ki;
    AttackAction bonusAttack;

    public MonkClass(int level, int ac, int hp, int toHit, int attackDmg, int multiattack, int dexterityMod) {
        super("player", ac, hp, toHit, attackDmg, multiattack, dexterityMod);
        this.level = level;
        this.ki = level;
        this.bonusAttack = new AttackAction(1,4,dexterityMod,"bludgeoning", toHit);
    }

    public ArrayList<Damage<Integer,String>> bonusAction() {

        //to select randomly by level rnd.nextInt(level)+1

        //Hit with fist
        return this.bonusAttack.rollDamage();

        //roll to attack

        //roll for damage

        //return int[] [attackRoll, damageRoll, type?]
    }
}
