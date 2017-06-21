package com.company;

/**
 * Created by juhanikula on 21/06/17.
 */
public class Damage<D,T> {

    final private D damage;
    final private T type;

    public Damage(D damage, T type) {
        this.damage = damage;
        this.type = type;
    }

    public D getDamage() {
        return damage;
    }

    public T getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Damage{" +
                "damage=" + damage +
                ", type=" + type +
                '}';
    }
}
