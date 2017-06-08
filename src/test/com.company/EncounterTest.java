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
    private ArrayList<Character> monsters;
    private ArrayList<Character> players;

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
    void numberOfOccurrenceTest() {

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
    void pickRandomOpponentForTest() {

        int indexNum = enc.pickRandomOpponentFor("player");
        assert (enc.participants.get(indexNum).type.equals("monster"));

        indexNum = enc.pickRandomOpponentFor("monster");
        assert (enc.participants.get(indexNum).type.equals("player"));
    }

    @Test
    void playersWinTest() {
        monsters.clear();
        boolean result = enc.fight();
        assert (result);
    }

    @Test
    void rollForInitiativeTest() {

        enc.rollForInitiative();

        //System.out.println(enc.participants.get(0).getInitiative());
        for (int i = 1; i < enc.participants.size(); i++) {
            //System.out.println(enc.participants.get(i).getInitiative());
            assert (enc.participants.get(i).getInitiative() <= enc.participants.get(i-1).getInitiative());
        }
    }
}