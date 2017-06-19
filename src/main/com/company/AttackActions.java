package com.company;

/**
 * Created by juhanikula on 08/06/17.
 */
public class AttackActions {

    int multiAttack;
    int attackDamage;
    int toHit;

    public AttackActions(int multiAttack, int toHit, int attackDamage) {
        this.multiAttack = multiAttack;
        this.toHit = toHit;
        this.attackDamage = attackDamage;
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

    public void addAttack() {
        this.multiAttack++;
    }
}
