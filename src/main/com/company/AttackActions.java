package com.company;

import java.util.ArrayList;

/**
 * Created by juhanikula on 08/06/17.
 */

//TODO This is obsolete. Just use ArrayList<AttackAction> in characters or monsters
public class AttackActions {

    int multiAttack;
    int attackDamage;
    int toHit;
    ArrayList<AttackAction> attacks;

    public AttackActions(int multiAttack, int toHit, int attackDamage) {
        this.multiAttack = multiAttack;
        this.toHit = toHit;
        this.attackDamage = attackDamage;
        this.attacks = new ArrayList<>();
    }

    public int getToHit() {
        return this.toHit;
    }

    public int rollDamage(int attackNumber) {
        return this.attackDamage;
    }

    public int rollDamage() {
        return this.attackDamage;
    }
}
