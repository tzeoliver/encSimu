package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by juhanikula on 21/06/17.
 */

class DamageDice {

    int amount;
    int dice;
    int damageMod;
    String type;

    public DamageDice(int amount, int dice, int damageMod, String type) {
        this.amount = amount;
        this.dice = dice;
        this.damageMod = damageMod;
        this.type = type;
    }
}

public class AttackAction {

    //DamageDice {[2,4,bludgeoning],[1,8,poison]}
    List<DamageDice> attack;
    int toHit; //

    public AttackAction(int amount, int dice, int damageMod, String type, int toHit) {
        this.attack = new ArrayList<>();
        this.attack.add(new DamageDice(amount,dice,damageMod,type));
        this.toHit = toHit;
    }

    public void addNewDamagePart(int amount, int dice, int damageMod, String type) {
        this.attack.add(new DamageDice(amount,dice,damageMod,type));
    }

    public ArrayList<Damage<Integer,String>> rollDamage() {

        ArrayList<Damage<Integer,String>> damageDone = new ArrayList<>();
        Random rnd = new Random();

        this.attack.forEach(item->{
            int damage = 0;
            for(int i = 0; i < item.amount; i++) {
                damage += rnd.nextInt(item.dice) +1+item.damageMod;
            }

            damageDone.add(new Damage<>(damage,item.type));
        });

        return damageDone;
    }

}
