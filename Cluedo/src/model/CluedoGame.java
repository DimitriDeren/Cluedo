package model;/*
 * Team name: Duxk (Team 54)
 * Team members: Jerome Skelton, Dimitri Ariadi, Gianpaolo Cigaral
 */

import model.*;


import java.util.*;

public class CluedoGame {

    private Board board;

    private boolean gameOver = false;
    private int numPlayers = 0;
    private String separate = "===========================================\n";
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Item> murderPocket;

    private ArrayList<Weapon> weapons = new ArrayList<>(Arrays.asList(
            new Weapon("Candlestick"), new Weapon("Dagger"), new Weapon("Lead Pipe"),
            new Weapon("Revolver"), new Weapon("Rope"), new Weapon("Spanner")));

    private ArrayList<Suspects> suspects = new ArrayList<>(Arrays.asList(
            new Suspects("Miss Scarlett"), new Suspects("Colonel Mustard"), new Suspects("Mrs. White"),
            new Suspects("Mr. Green"), new Suspects("Mrs. Peacock"), new Suspects("Professor Plum")
    ));

    private ArrayList<Room> rooms = new ArrayList<>(Arrays.asList(
            new Room("Kitchen"), new Room("Ball model.Room"), new Room("Conservatory"),
            new Room("Dinning model.Room"), new Room("Billiard model.Room"), new Room("Library"),
            new Room("Lounge"), new Room("Hall"), new Room("Study")
    ));

    private void play() {
        setup();
        playGame();
    }

    private void setup() {
        board = new Board();

        while (numPlayers < 2 || numPlayers > 6){
            System.out.print("(INSTRUCTIONS : This game only accepts inputs from 0-9)\nHow many players are there (2 - 6)? ");
            numPlayers = Utility.checkIntInput();
            System.out.println(separate);
        }

        ArrayList<Suspects> susCopy = new ArrayList<>(suspects);
        ArrayList<Cell> startingPositions = new ArrayList<>(Arrays.asList(
                board.getCells()[0][5], board.getCells()[23][5], board.getCells()[23][12],
                board.getCells()[16][24], board.getCells()[7][24], board.getCells()[0][19]));

        for (int i = 0; i < numPlayers; i++) {
            int choice = -1;
            System.out.println("model.Player " + (i + 1) + ", which character will you be?\n");
            for (int j = 0; j < susCopy.size(); j++) {
                System.out.println((j + 1) + ". " + susCopy.get(j).name);
            }
            while (choice < 0 || choice > susCopy.size() - 1) {
                choice = Utility.checkIntInput() - 1;
            }

            System.out.println(separate);

            //create player object
            players.add(new Player(String.valueOf(i + 1), suspects.get(choice), startingPositions.get(i), suspects, weapons, rooms));

            //update player cell position types
            startingPositions.get(i).setOccupant(i + 1);

            susCopy.remove(choice);
        }

        // shuffle decks
        Collections.shuffle(weapons);
        Collections.shuffle(suspects);
        Collections.shuffle(rooms);

        // Pick one random from each to be in the murderPocket
        murderPocket = new ArrayList<>();
        murderPocket.add(suspects.get(0));
        suspects.remove(0);
        murderPocket.add(weapons.get(0));
        weapons.remove(0);
        murderPocket.add(rooms.get(0));
        rooms.remove(0);

        System.out.println(murderPocket.get(0).getName() + " " + murderPocket.get(1).getName() + " " + murderPocket.get(2).getName());

        // Rest in to deck and shuffle deck
        Stack<Item> deck = new Stack<>();
        deck.addAll(weapons);
        deck.addAll(suspects);
        deck.addAll(rooms);
        Collections.shuffle(deck);

        // Deal cards to players hand
        int count = 0;
        while (!deck.isEmpty()) {
            if (count == players.size()) {
                count = 0;
            }

            players.get(count).addCard(deck.pop());
            count++;
        }
    }

    private void playGame() {
        int count = 0;
        int totalLost = 0;
        while (!gameOver) {
            if (totalLost == players.size()) {
                System.out.println("Sorry all players have been unable to deduce the correct murder scene.");
                System.out.println(murderPocket.get(0).getName() + " used the " + murderPocket.get(1).getName() + " to kill someone in the " + murderPocket.get(2).getName() + " and successfully escaped");
                break;
            }

            // model.Player rolls dice, and moves
            Player curr = players.get(count % players.size());

            // skip player if they have already lost
            if (curr.isLost()) {
                count++;
                continue;
            }

            board.draw();
            System.out.println("model.Player " + curr.getName() + "\'s turn");
            int roll = rollDice();
            System.out.println("You rolled a " + roll + "\n");

            // handle player actions
            for (int i = 0; i < roll; i++) {
                int choice = 0;
                while (choice < 1 || choice > ((curr.getPos().getType() > 0 && curr.getPos().getType() <= 10) ? 7 : 6)) {
                    System.out.println("model.Player " + curr.getName() + " - What would you like to do?\n" + (roll - i) + " turns left\n");
                    System.out.println("1. Move North\n2. Move East\n3. Move South\n4. Move West\n5. Check Clues\n6. Make Accusation");
                    if (curr.getPos().getType() > 0 && curr.getPos().getType() <= 10) {
                        System.out.println("7. Make Suggestion");
                    }
                    choice = Utility.checkIntInput();
                    System.out.println(separate);
                }

                // Move logic
                if (choice <= 4) {
                    roll += movementController(curr, choice);
                } else if (choice == 5) {
                    curr.checkClues();
                    roll++;
                } else if (choice == 6) {
                    accusationController(curr);
                    totalLost++;
                    break;
                } else {
                    suggestionController(curr, (count % players.size()));
                }
            }
            count++;
        }
    }


    private int movementController(Player curr, int choice) {
        //update new cell pos
        int change = 0;

        int[] newCoord = curr.move(choice);
        if (newCoord[0] == -1 || newCoord[1] == -1) {
            System.out.println("Not a valid movement");
            change = 1;
            return change;
        }

        Cell newPos = board.getCells()[newCoord[0]][newCoord[1]];

        //update old cell pos
        Cell oldPos = curr.getPos();
        if(oldPos.getType() != 0){

        } else {
            oldPos.setType(0);
        }

        oldPos.setOccupant(0);

        if(newPos.getType() == 13 || newPos.getOccupant() > 0){
            System.out.println("Not a valid movement");
            newPos = oldPos; //set newPos to current pos if its an invalid movement
            change = 1; //stops taking a turn away from an invalid move
        } else {
            curr.setPos(newPos);
        }

        //newPos.setOccupant(curr.getName());
        board.draw();

        return change;
    }


    private void accusationController(Player curr) {
        ArrayList<Item> accusation = curr.accuse();
        boolean accSuspect = false;
        boolean accWeapon = false;
        boolean accRoom = false;

        if (accusation.get(0).getName().equals(murderPocket.get(0).getName())) { accSuspect = true; }
        if (accusation.get(1).getName().equals(murderPocket.get(1).getName())) { accWeapon = true; }
        if (accusation.get(2).getName().equals(murderPocket.get(2).getName())) { accRoom = true; }

        if (accSuspect && accWeapon && accRoom) {
            gameOver = true;
            System.out.println("Congratulations! model.Player " + curr.getName() + " has successfully deduced that ");
            System.out.println(murderPocket.get(0).getName() + " used the " + murderPocket.get(1).getName() + " to kill someone in the " + murderPocket.get(2).getName());
        } else {
            curr.setLost(true);
            curr.getPos().setOccupant(0);
            System.out.println("Sorry you have failed to deduce the correct murder scene.");
            System.out.println(separate);
        }
    }

    private void suggestionController(Player curr, int currIndex) {
        ArrayList<Item> suggestion = curr.suggest();
        for (int i = 0; i < players.size(); i++) {
            if (i == currIndex) {
                System.out.println("model.Player index = " + i);
                continue;
            }

            //Item refutationItem = players.get(i).refute(suggestion, curr.getName());

            //if (!(refutationItem == null)) {
            //    curr.receiveClues(refutationItem);
            //}
        }
    }

    private int rollDice() {
        Random rand = new Random();
        int roll = rand.nextInt(13);

        if (roll == 0) {
            roll = 1;
        }

        return roll;
    }

//    public static void main(String[] args) {
//        model.CluedoGame game = new model.CluedoGame();
//        game.play();
//    }
}
