package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by juhanikula on 05/06/17.
 */
public class Encounter {

    List<Character> participants;
    List<Character> players;
    List<Character> monsters;
    int roundNumber;
    int consciousPlayers;
    int consciousMonsters;

    public Encounter(List<Character> participants) {
        this.participants = participants;
        this.roundNumber = 0;
    }

    public Encounter(List<Character> players, List<Character> monsters) {
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

        long seed = System.nanoTime();
        Random rnd = new Random();
        Collections.shuffle(this.participants, new Random(seed));

        while (this.consciousPlayers > 0 && this.consciousMonsters > 0) {
            //System.out.println("Round " + this.roundNumber);
            ////System.out.println(printEncounterStats());
            ////System.out.println(this.monsters.toString() + "AND"+ this.players.toString() );

            for (int i=0; i < this.participants.size(); i++) {
                if (this.consciousMonsters <= 0 || this.consciousPlayers <= 0) {
                    return (this.consciousPlayers != 0);
                }

                Character item = this.participants.get(i);

                if (item.condition.equals("unconscious")) {
                    //If player; roll death saving throw
                    if (item.type.equals("player")) {
                        item.rollDeathSavingThrow();
                    }
                    continue;
                } else if (item.condition.equals("dead")) {
                    continue;
                }

                int attackRoll = rnd.nextInt(20) + 1;
                int opponentIndex = pickRandomOpponentFor(item.type);

                if (attackRoll == 1) {
                    ////System.out.println("CRIT FAIL");
                } else if (attackRoll == 20) {
                    ////System.out.println("NATURAL 20");
                    //Roll dmg
                    //item.avgDmg*2;
                    if (this.participants.get(opponentIndex).takeDamage(item.avgDmg*2).equals("unconscious")) {
                        if (this.participants.get(opponentIndex).type.equals("player")) {
                            this.consciousPlayers--;
                        } else {
                            this.consciousMonsters--;
                        }
                    }
                    //System.out.println("Monster CRITTED dealing " + item.avgDmg * 2 + " points of damage!");
                } else {
                    //System.out.println(item.type + " rolled " + (attackRoll + item.toHit));
                    if (attackRoll + item.toHit >= this.participants.get(opponentIndex).ac) {
                        //Roll dmg
                        //item.avgDmg;
                        if (this.participants.get(opponentIndex).takeDamage(item.avgDmg).equals("unconscious")) {
                            //System.out.println(this.players.get(hitIndex).hp);
                            if (this.participants.get(opponentIndex).type.equals("player")) {
                                this.consciousPlayers--;
                            } else {
                                this.consciousMonsters--;
                            }
                        }
                        //System.out.println("Monster hit dealing " + item.avgDmg + " points of damage!");
                    }
                }
            }
            this.roundNumber++;
            //System.out.println("---");
        }

        ////System.out.println("Monsters " + this.monsters.size());
        ////System.out.println("Players " + this.players.size());

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

    //Returns index number of random opponent from participants. Opponent being opposing type.
    public int pickRandomOpponentFor(String type) {

        Random rnd = new Random();
        int indexNum = 0;
        String opponent = type;

        while (type.equals(opponent)) {
            indexNum = rnd.nextInt(this.participants.size());
            opponent = this.participants.get(indexNum).type;
        }

        return indexNum;
    }


}
