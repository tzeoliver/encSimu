package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by juhanikula on 08/06/17.
 */
class CharacterTest {

    Character player;

    @BeforeEach
    void setUp() {
        player = new Character("player",11, 22, 6, 7);
    }

    @Test
    void takeDamage() {
        String condition;

        condition = player.takeDamage(20);
        assertTrue (condition.equals("conscious"));

        player.setHp(22);
        condition = player.takeDamage(22);
        assertTrue (condition.equals("unconscious"));

        player.setHp(22);
        condition = player.takeDamage(23);
        assertTrue (condition.equals("unconscious"));

        player.setHp(22);
        condition = player.takeDamage(44);
        assertTrue (condition.equals("dead"));

        player.setHp(22);
        condition = player.takeDamage(45);
        assertTrue (condition.equals("dead"));
    }

    @Test
    void rollDeathSavingThrowTest() {

        player.condition = "unconscious";

        for (int i=0; i < 6;i++) {
            if (player.condition.equals("unconscious")) {
                player.rollDeathSavingThrow();
            } else {
                break;
            }
        }

        if (player.condition.equals("conscious")) {
            assertTrue (player.hp == 1);
        } else {
            assertTrue (player.condition.equals("dead"));
        }
    }
}