package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Encounter enc;

        //System.out.println(enc.printEncounterStats());
        int playerWins = 0;
        for (int i = 0; i < 100; i++) {
            enc = new Encounter(getPlayers(),getMonsters());
            if (enc.fight()) {
                playerWins++;
            }
        }
        System.out.println(playerWins);
        //System.out.println(enc.printEncounterStats());

    }

    private static ArrayList<Character> getPlayers() {

        ArrayList<Character> players = new ArrayList<>();
        //Character player = new Character("player", 14, 18, 5, 6);
        players.add(new Character("player", 11, 22, 6, 7));
        players.add(new Character("player", 14, 34, 7, 10));
        players.add(new Character("player", 15, 34, 6, 10));
        players.add(new Character("player", 14, 38, 6, 10));
        //players.add(new Character("player", 12, 38, 6, 10));

        return players;
    }

    private static ArrayList<Character> getMonsters() {

        ArrayList<Character> monsters = new ArrayList<>();
        //Character monster = new Character("monster", 13, 25, 3, 4);
        monsters.add(new Character("monster", 13, 90, 5, 9));
        monsters.add(new Character("monster", 13, 90, 5, 9));
        //monsters.add(new Character("monster", 13, 90, 5, 5));
        //monsters.add(new Character("monster", 13, 20, 5, 5));
        //monsters.add(new Character("monster", 13, 20, 5, 5));

        return monsters;
    }

}
