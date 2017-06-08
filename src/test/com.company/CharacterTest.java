package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by juhanikula on 08/06/17.
 */
class CharacterTest {


    @Test
    void takeDamage() {

        Player player = new Player(11, 22, 6, 7);

        String condition;

        condition = player.takeDamage(20);
        assert (condition.equals("conscious"));

        player.setHp(22);

        condition = player.takeDamage(22);
        assert (condition.equals("unconscious"));

        player.setHp(22);
        condition = player.takeDamage(23);
        assert (condition.equals("unconscious"));

        player.setHp(22);
        condition = player.takeDamage(44);
        assert (condition.equals("dead"));

        player.setHp(22);
        condition = player.takeDamage(45);
        assert (condition.equals("dead"));
    }

    @Test
    void rollDeathSavingThrowTest() {

        Player player = new Player(11, 22, 6, 7);

        player.condition = "unconscious";

        for (int i=0; i < 6;i++) {
            if (player.condition.equals("unconscious")) {
                player.rollDeathSavingThrow();
            } else {
                break;
            }
        }
        assertFalse(player.condition.equals("unconscious"));
    }



}