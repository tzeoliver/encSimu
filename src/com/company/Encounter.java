package com.company;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.*;

/**
 * Created by juhanikula on 05/06/17.
 */
public class Encounter {

    List<Character> participants;
    List<Character> players;
    List<Character> monsters;
    int roundNumber;

    public Encounter(List<Character> participants) {
        this.participants = participants;
        this.roundNumber = 0;
    }

    public Encounter(List<Character> players, List<Character> monsters) {
        this.players = players;
        this.monsters = monsters;
        this.participants = new ArrayList<Character>();
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

    public boolean fight() {

        long seed = System.nanoTime();
        Random rnd = new Random();
        Collections.shuffle(this.participants, new Random(seed));

        int monst = numberOfOccurances(this.participants,"monster");
        System.out.println(monst);

        while (this.monsters.size() > 0 && this.players.size() > 0) {
            System.out.println("Round " + this.roundNumber);
            //System.out.println(printEncounterStats());
            System.out.println(this.monsters.toString() + "AND"+ this.players.toString() );

            for (int i=0; i < this.participants.size(); i++) {
                Character item = this.participants.get(i);
                if (this.monsters.size() <= 0 || this.players.size() <= 0) {
                    break;
                } else if (item.condition.equals("unconscious")) {
                    continue;
                }

                int attackRoll = rnd.nextInt(20) + 1;

                if (item.type.equals("monster")) {
                    int hitIndex = rnd.nextInt(this.players.size());

                    if (attackRoll == 1) {
                        System.out.println("CRIT FAIL");
                    } else if (attackRoll == 20) {
                        System.out.println("NATURAL 20");
                        //Roll dmg
                        //item.avgDmg*2;
                        if (this.players.get(hitIndex).takeDamage(item.avgDmg * 2).equals("unconscious")) {
                            this.players.remove(hitIndex);
                        }
                        System.out.println("Monster CRITTED dealing " + item.avgDmg * 2 + " points of damage!");
                    } else {
                        System.out.println(item.type + " rolled " + (attackRoll + item.toHit));
                        if (attackRoll + item.toHit >= this.players.get(hitIndex).ac) {
                            //Roll dmg
                            //item.avgDmg;
                            if (this.players.get(hitIndex).takeDamage(item.avgDmg).equals("unconscious")) {
                                System.out.println(this.players.get(hitIndex).hp);
                                this.players.remove(hitIndex);
                            }
                            System.out.println("Monster hit dealing " + item.avgDmg + " points of damage!");
                        }
                    }
                } else if (item.type.equals("player")) {
                    int hitIndex = rnd.nextInt(this.monsters.size());

                    if (attackRoll == 1) {
                        System.out.println("CRIT FAIL");
                    } else if (attackRoll == 20) {
                        System.out.println("NATURAL 20");
                        //Roll dmg
                        //item.avgDmg*2;
                        if (this.monsters.get(hitIndex).takeDamage(item.avgDmg * 2).equals("unconscious")) {
                            this.monsters.remove(hitIndex);
                        }
                        System.out.println("Player CRITTED dealing " + item.avgDmg * 2 + " points of damage!");
                    } else {
                        System.out.println(item.type + " rolled " + (attackRoll + item.toHit));
                        if (attackRoll + item.toHit >= this.monsters.get(hitIndex).ac) {
                            //Roll dmg
                            //item.avgDmg;
                            if (this.monsters.get(hitIndex).takeDamage(item.avgDmg).equals("unconscious")) {
                                System.out.println(this.monsters.get(hitIndex).hp);
                                this.monsters.remove(hitIndex);
                            }
                            System.out.println("Player hit dealing " + item.avgDmg + " points of damage!");
                        }
                    }
                }
            }
            this.roundNumber++;
            System.out.println("---");
        }

        //System.out.println("Monsters " + this.monsters.size());
        //System.out.println("Players " + this.players.size());

        if (this.players.size() == 0) {
            return false;
        } else {
            return true;
        }

    }

    public int numberOfOccurances(List<Character> list, String type) {

        Map<String,Integer> occur = new HashMap<String, Integer>();

        list.forEach(item->{
            if (occur.containsKey(item.type)) {
                occur.replace(item.type,occur.get(item.type)+1);
            } else {
                occur.put(item.type,1);
            }


        });



        return Collections.frequency(list,type);
    }


}
