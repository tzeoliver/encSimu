package com.company;

import com.sun.tools.hat.internal.util.ArraySorter;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by juhanikula on 05/06/17.
 */
public class Encounter {

    ArrayList<Character> participants;
    ArrayList<Character> players;
    ArrayList<Character> monsters;
    int roundNumber;
    int consciousPlayers;
    int consciousMonsters;

    public Encounter(ArrayList<Character> participants) {
        this.participants = participants;
        this.roundNumber = 0;
    }

    public Encounter(ArrayList<Character> players, ArrayList<Character> monsters) {
        this.players = players;
        this.consciousPlayers = players.size();
        this.monsters = monsters;
        this.consciousMonsters = monsters.size();
        this.participants = new ArrayList<>();
        this.participants.addAll(players);
        this.participants.addAll(monsters);
        this.roundNumber = 1;
    }

    public void addPlayer(Character character) {
        this.players.add(character);
        this.participants.add(character);
    }

    public void addMonster(Character character) {
        this.monsters.add(character);
        this.participants.add(character);
    }

    public String printEncounterStats() {
        return this.participants.toString();
    }

    /*
        Returns true if players win.
     */
    public boolean fight() {

        //long seed = System.nanoTime();
        //Collections.shuffle(this.participants, new Random(seed));
        Random rnd = new Random();

        rollForInitiative();

        while (this.consciousPlayers > 0 && this.consciousMonsters > 0) {
            for (int i=0; i < this.participants.size(); i++) {
                if (this.consciousMonsters <= 0 || this.consciousPlayers <= 0) {
                    return (this.consciousPlayers != 0);
                }

                Character item = this.participants.get(i);

                if (item.condition.equals("unconscious")) {
                    //If player; roll death saving throw
                    if (item.type.equals("player")) {
                        if (!item.rollDeathSavingThrow().equals("conscious")) {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else if (item.condition.equals("dead")) {
                    continue;
                }

                int attackRoll = rnd.nextInt(20) + 1;
                int opponentIndex = pickRandomOpponentFor(item.type);

                if (attackRoll == 1) {
                    //MISS
                } else if (attackRoll == 20) {
                    //CRIT, double the dice
                    successfulHit(opponentIndex,item.avgDmg*2);
                } else if (attackRoll + item.toHit >= this.participants.get(opponentIndex).ac) {
                    successfulHit(opponentIndex,item.avgDmg);
                }
            }
            this.roundNumber++;
        }
        return (this.consciousPlayers != 0);
    }

    public Map<String,Integer> numberOfOccurrence() {

        Map<String,Integer> occur = new HashMap<>();

        this.participants.forEach(item->{
            if (occur.containsKey(item.type)) {
                occur.replace(item.type,occur.get(item.type)+1);
            } else {
                occur.put(item.type,1);
            }
        });

        return occur;
    }

    //Returns index number of random opponent from participants.
    //Opponent must be opposing type and conscious
    public int pickRandomOpponentFor(String type) {

        Random rnd = new Random();
        int indexNum = 0;
        String opponent = type;

        while (type.equals(opponent)) {
            indexNum = rnd.nextInt(this.participants.size());
            if (this.participants.get(indexNum).condition.equals("conscious")) {
                opponent = this.participants.get(indexNum).type;
            }
        }
        return indexNum;
    }

    //Rolls initiative and sorts participants accordingly
    public void rollForInitiative() {
        Random rnd = new Random();

        this.participants.forEach(item->{
            item.setInitiative(rnd.nextInt(20)+1+item.initiativeMod);
        });

        Collections.sort(this.participants);
    }

    public void successfulHit(int index,int damage) {
        if (this.participants.get(index).takeDamage(damage).equals("unconscious")) {
            if (this.participants.get(index).type.equals("player")) {
                this.consciousPlayers--;
            } else {
                this.consciousMonsters--;
            }
        }
    }

    public int getDeadPlayers() {

        int deadPlayers = 0;

        for (Character item : this.participants ) {
            if (item.type.equals("player") && item.condition.equals("dead")) {
                deadPlayers++;
            }
        }
        return deadPlayers;
    }

    public int getDeadMonsters() {

        int deadMonsters = 0;

        for (Character item : this.participants ) {
            if (item.type.equals("monster") && item.condition.equals("dead")) {
                deadMonsters++;
            }
        }
        return deadMonsters;
    }

}
