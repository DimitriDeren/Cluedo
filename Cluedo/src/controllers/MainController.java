package controllers;

import com.sun.tools.javac.Main;
import model.*;
import views.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainController {

    /*
     * TODO: Store logic for stuff like action listeners here
     *  - logic directly manipulating data in Models should be in relevant model class (e.g. handling coordinate movement of players)
     * TODO: Store references to model here (use - import models.*)
     * TODO: Update (or call update methods) of views here
     */

    private static MainView mainView;

    private static Board board = new Board();

    public void accuseMethod() {
        //========================= Initialize the Buttons and PopUp Menu =========================
        JFrame frame = new JFrame("Accusation!");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLocation(430, 100);

        JPanel panel = new JPanel();

        frame.add(panel);

        String[] suspect = { "SUSPECTS:","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};
        String[] weapon = { "WEAPONS:","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};
        String[] room = { "ROOMS:","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};

        final JComboBox<String> selChoice = new JComboBox<String>(suspect);
        final JComboBox<String> selWeap = new JComboBox<String>(weapon);
        final JComboBox<String> selRoom = new JComboBox<String>(room);

        selChoice.setVisible(true);
        selWeap.setVisible(true);
        selRoom.setVisible(true);
        panel.add(selChoice);
        panel.add(selWeap);
        panel.add(selRoom);

        JButton btn = new JButton("OK");
        panel.add(btn);

        JButton cancelBtn = new JButton("CANCEL");
        panel.add(cancelBtn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Close popup window when "OK" button is pressed.
                Object susChoice = selChoice.getSelectedItem(); //gets suspect item from drop-down menu
                Object weapChoice = selWeap.getSelectedItem(); //gets weapon item from drop-down menu
                Object roomChoice = selRoom.getSelectedItem(); //gets room item from drop-down menu
                System.out.println(susChoice);
                frame.dispose();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        //TODO: Get accusation method once "OK" button is pressed

    }

    public void suggestMethod() {

        //========================= Initialize the Buttons and PopUp Menu =========================

        JFrame frame = new JFrame("Accusation!");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(200, 150);
        frame.setLocation(430, 100);


        JPanel panel = new JPanel();

        frame.add(panel);

        String[] suspect = { "SUSPECTS:","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};
        String[] weapon = { "WEAPONS:","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};

        final JComboBox<String> selChoice = new JComboBox<String>(suspect);
        final JComboBox<String> selWeap = new JComboBox<String>(weapon);

        selChoice.setVisible(true);
        selWeap.setVisible(true);
        panel.add(selChoice);
        panel.add(selWeap);

        JButton btn = new JButton("OK");
        panel.add(btn);

        JButton cancelBtn = new JButton("CANCEL");
        panel.add(cancelBtn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Close popup window when "OK" button is pressed.
                Object choice = selChoice.getSelectedItem(); //gets item from drop-down menu
                System.out.println(choice);
                frame.dispose();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });


        //========================= Initialize the Buttons and PopUp Menu =========================

    }

    /*
     * TODO: Store logic for stuff like action listeners here
     *  - logic directly manipulating data in Models should be in relevant model class (e.g. handling coordinate movement of players)
     * TODO: Store references to model here (use - import models.*)
     * TODO: Update (or call update methods) of views here
     */

    private ArrayList<Weapon> weapons = new ArrayList<>(Arrays.asList(
            new Weapon("Candlestick"), new Weapon("Dagger"), new Weapon("Lead Pipe"),
            new Weapon("Revolver"), new Weapon("Rope"), new Weapon("Spanner")));

    private ArrayList<Suspects> suspects = new ArrayList<>(Arrays.asList(
            new Suspects("Miss Scarlett"), new Suspects("Colonel Mustard"), new Suspects("Mrs. White"),
            new Suspects("Mr. Green"), new Suspects("Mrs. Peacock"), new Suspects("Professor Plum")
    ));

    private ArrayList<Room> rooms = new ArrayList<>(Arrays.asList(
            new Room("Kitchen"), new Room("Ball Room"), new Room("Conservatory"),
            new Room("Dinning Room"), new Room("Billiard Room"), new Room("Library"),
            new Room("Lounge"), new Room("Hall"), new Room("Study")
    ));

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Item> murderPocket;

    private void play() {
        setup();
        //playGame();
    }

    private void setup() {
        // display setup window and get setup info, create player objects using GUI and add to array

        // Shuffle decks
        Collections.shuffle(weapons);
        Collections.shuffle(suspects);
        Collections.shuffle(rooms);

        // Pick one random card from each deck to be in the murderPocket
        murderPocket = new ArrayList<>();
        murderPocket.add(suspects.get(0));
        suspects.remove(0);
        murderPocket.add(weapons.get(0));
        weapons.remove(0);
        murderPocket.add(rooms.get(0));
        rooms.remove(0);

        // Rest of cards in to deck and shuffle deck
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

    //TODO: Fix missing gameOver variable, uncomment below to debug

    /*private void playGame() {
        int count = 0;
        int totalLost = 0;
        while (!gameOver) {
            if (totalLost == players.size()) {
                // GUI pop up saying all players are out and reveal the murder pocket
                break;
            }

            // Player rolls dice, and moves
            Player curr = players.get(count % players.size());

            // skip player if they have already lost
            if (curr.isLost()) {
                count++;
                continue;
            }

            // Display board and who's turn it is
            int roll = rollDice();
            // Update "moves left" count on GUI

            // handle player actions
            for (int i = 0; i < roll; i++) {
                int choice = 0;
                while (choice < 1 || choice > ((curr.getPos().getType() > 0 && curr.getPos().getType() <= 10) ? 7 : 6)) {
                    // Keyboard listener for player moves, or button push
                    if (curr.getPos().getType() > 0 && curr.getPos().getType() <= 10) {
                        // Allow for suggestion button cause in room
                    }
                }

                // Move logic
                // needs to be redone around buttons and keyboard listening from GUI
                *//**if (choice <= 4) {
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
                 } *//*
            }
            count++;
        }
    }*/


    private int rollDice() {
        Random rand = new Random();
        int roll = rand.nextInt(13);

        if (roll == 0) {
            roll = 1;
        }

        return roll;
    }


    public Cell[][] getCells() {return board.getCells();}

    public static void main(String[] args) {
        MainController mc = new MainController();
        mainView = new MainView(mc);
    }
}

