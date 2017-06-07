package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by juhanikula on 06/06/17.
 */
class EncounterTest {

    private Encounter enc;
    private List<Character> monsters;
    private List<Character> players;

    @BeforeEach
    void setUp() {
        monsters = new ArrayList<>();
        monsters.add(new Character("monster", 13, 90, 5, 9));
        monsters.add(new Character("monster", 13, 90, 5, 9));

        players = new ArrayList<>();
        players.add(new Character("player", 11, 22, 6, 7));
        players.add(new Character("player", 14, 34, 7, 10));
        players.add(new Character("player", 15, 34, 6, 10));
        players.add(new Character("player", 14, 38, 6, 10));

        enc = new Encounter(players,monsters);
    }

    @Test
    void numberOfOccurrence() {

        Map<String,Integer>  occurrence = enc.numberOfOccurrence();
        assert (occurrence.get("player") == 4);
        assert (occurrence.get("monster") == 2);

        monsters.clear();
        players.clear();
        enc = new Encounter(players,monsters);
        occurrence = enc.numberOfOccurrence();
        assert (occurrence.get("player") == 0);
        assert (occurrence.get("monster") == 0);
    }

    @Test
    void pickRandomOpponentFor() {

        int indexNum = enc.pickRandomOpponentFor("player");
        assert (enc.participants.get(indexNum).type.equals("monster"));

        indexNum = enc.pickRandomOpponentFor("monster");
        assert (enc.participants.get(indexNum).type.equals("player"));
    }

    @Test
    void deathSavingThrows() {

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

    @Test
    void playersWin() {
        monsters.clear();
        boolean result = enc.fight();
        assert (result);
    }
}