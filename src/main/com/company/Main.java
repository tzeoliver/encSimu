package com.company;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args) {

        Encounter enc;
        int playerWins = 0;
        int fights = 100;
        List<Integer> playerDeaths = new ArrayList<>();
        List<Integer> playersUnconscious = new ArrayList<>();

        //System.out.println(enc.printEncounterStats());
        for (int i = 0; i < fights; i++) {
            enc = new Encounter(getPlayers(),getMonsters());
            if (enc.fight()) {
                playerWins++;
                playerDeaths.add(enc.getDeadPlayers());
                playersUnconscious.add(getPlayers().size()-enc.consciousPlayers);
                System.out.println("consciouspl:"+enc.consciousPlayers );
            }
        }

        analyzeResults(playerDeaths,playersUnconscious,playerWins,fights);
        //System.out.println(playerWins);
        //System.out.println(enc.printEncounterStats());

    }

    private static ArrayList<Character> getPlayers() {

        ArrayList<Character> players = new ArrayList<>();
        //Character player = new Character("player", 14, 18, 5, 6);
        players.add(new Character("player", 11, 22, 6, 7));
        players.add(new Character("player", 14, 34, 7, 10));
        players.add(new Character("player", 15, 34, 6, 10));
        players.add(new Character("player", 14, 38, 6, 10));
        players.add(new Character("player", 12, 38, 6, 10));

        return players;
    }

    private static ArrayList<Character> getMonsters() {

        ArrayList<Character> monsters = new ArrayList<>();
        //Character monster = new Character("monster", 13, 25, 3, 4);
        monsters.add(new Character("monster", 13, 90, 5,9,2,3));
        monsters.add(new Character("monster", 13, 90, 5, 9,2,3));
        //monsters.add(new Character("monster", 13, 90, 5, 5));
        //monsters.add(new Character("monster", 13, 20, 5, 5));
        //monsters.add(new Character("monster", 13, 20, 5, 5));

        return monsters;
    }

    private static void analyzeResults(List<Integer> playerDeaths, List<Integer> playerUnconscious,int playerWins,int fights) {

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.HALF_UP);

        System.out.println("Players won " + playerWins + "/" + fights + ".");
        if (playerWins > 0) {
            System.out.println("In battle where the payers won:");
            System.out.println("By average " + df.format(calculateAverage(playerUnconscious)) + "/4 went unconscious till the end.");
            System.out.println(df.format(calculateAverage(playerDeaths)) + "/4 died.");
        } else {
            System.out.println("Monster won every match");
        }
    }
    private static double calculateAverage(List <Integer> marks) {
        Integer sum = 0;
        if(!marks.isEmpty()) {
            for (Integer mark : marks) {
                sum += mark;
            }
            return sum.doubleValue() / marks.size();
        }

        return sum;
    }
}