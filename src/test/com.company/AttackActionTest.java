package com.company;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by juhanikula on 21/06/17.
 */
class AttackActionTest {
    @Test
    void rollDamage() {

        ArrayList<Damage<Integer,String>> damage;
        int amount = 1;
        int dice = 1;
        int damageMod = 1;

        AttackAction attack = new AttackAction(amount,dice,damageMod,"bludgeoning",5);

        for (int i = 0; i < 100; i++) {
            damage = attack.rollDamage();
            assertTrue (amount+damageMod <= damage.get(0).getDamage());
            assertTrue (damage.get(0).getDamage() <= (amount*dice+damageMod));
        }

        amount = 1;
        dice = 8;
        damageMod = 2;

        attack.addNewDamagePart(amount,dice,damageMod,"slashing");

        for (int i = 0; i < 100; i++) {
            damage = attack.rollDamage();
            assertTrue ((amount+damageMod) <= damage.get(1).getDamage());
            assertTrue (damage.get(1).getDamage() <= (amount*dice+damageMod));
        }

        damage = attack.rollDamage();
        System.out.println(damage.toString());

    }


}