package com.company;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by juhanikula on 06/06/17.
 */
class EncounterTest {

    Encounter enc;

    @Test
    void numberOfOccurrence() {

        List<Character> monsters = new ArrayList<>();
        monsters.add(new Character("monster", 13, 90, 5, 9));
        monsters.add(new Character("monster", 13, 90, 5, 9));

        List<Character> players = new ArrayList<>();
        players.add(new Character("player", 11, 22, 6, 7));
        players.add(new Character("player", 14, 34, 7, 10));
        players.add(new Character("player", 15, 34, 6, 10));
        players.add(new Character("player", 14, 38, 6, 10));

        enc = new Encounter(players,monsters);
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

}