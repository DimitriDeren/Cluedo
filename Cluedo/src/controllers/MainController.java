package controllers;

import model.*;
import views.MainView;
import views.NewGameView;

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

    // == VIEWS ================================================================================
    private static MainView mainView;

    // == MODELS ===============================================================================
    private static Board board = new Board();

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

    private static final ArrayList<Cell> STARTING_POSITIONS = new ArrayList<>(Arrays.asList(
            board.getCells()[0][5], board.getCells()[23][5], board.getCells()[23][12],
            board.getCells()[16][24], board.getCells()[7][24], board.getCells()[0][19]));

    private Map<String, Player> players = new HashMap<>();

    private ArrayList<Item> murderPocket;

    // == GAME STATE ===========================================================================
    private boolean diceStatus = false;
    private boolean suggestionStatus = false;
    private boolean gameOver = false;

    //Current player status
    private ArrayList<String> playerTurnOrder = new ArrayList<>();
    private int turnCount = 0;
    private Player currentPlayer;
    private int currentMoves;
    private int totalLost = 0;

    public void newGameMethod() {
        NewGameView ngw = new NewGameView(this, suspects, weapons, rooms, STARTING_POSITIONS);
    }
    
    public void accuseMethod() {
        //========================= Initialize the Buttons and PopUp Menu =========================
        JFrame frame = new JFrame("Accusation!");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(500, 120);
        frame.setLocation(430, 100);

        JPanel panel = new JPanel();

        frame.add(panel);

        Suspects[] suspect = {suspects.get(0), suspects.get(1),suspects.get(2),suspects.get(3),suspects.get(4), suspects.get(5)};
        Weapon[] weapon = { weapons.get(0), weapons.get(1),weapons.get(2),weapons.get(3),weapons.get(4), weapons.get(5)};
        Room[] room = {  rooms.get(0), rooms.get(1),rooms.get(2),rooms.get(3),rooms.get(4), rooms.get(5)};

        final JComboBox<Suspects> selChoice = new JComboBox<Suspects>(suspect);
        final JComboBox<Weapon> selWeap = new JComboBox<Weapon>(weapon);
        final JComboBox<Room> selRoom = new JComboBox<Room>(room);

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
                //TODO: type cast choices
                ArrayList<Item> accusation = new ArrayList<>();

                Object susChoice = selChoice.getSelectedItem(); //gets suspect item from drop-down menu
                Object weapChoice = selWeap.getSelectedItem(); //gets weapon item from drop-down menu
                Object roomChoice = selRoom.getSelectedItem(); //gets room item from drop-down menu

                accusation.add((Item)susChoice);
                accusation.add((Item)weapChoice);
                accusation.add((Item)roomChoice);

                frame.dispose();

                if (murderPocket.containsAll(accusation)) {
                    //TODO: player has won message pop-up, end the game
                } else {
                    currentPlayer.setLost(true);
                    //TODO: player has lost message pop-up (show murder pocket?)
                    totalLost++;
                    if (totalLost == players.size()) {
                        //TODO: end the game, all players have lost
                    } else {
                        //TODO: un comment on merge
                        //nextPlayerTurn();
                    }
                }
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

        JFrame frame = new JFrame("Suggestion!");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(200, 150);
        frame.setLocation(430, 100);


        JPanel panel = new JPanel();

        frame.add(panel);

        Suspects[] suspect = suspects.toArray(new Suspects[0]);
        Weapon[] weapon = weapons.toArray(new Weapon[0]);

        final JComboBox<Suspects> selChoice = new JComboBox<Suspects>(suspect);
        final JComboBox<Weapon> selWeap = new JComboBox<Weapon>(weapon);

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
                ArrayList<Item> suggestion = new ArrayList<>();

                Object susChoice = selChoice.getSelectedItem(); //gets suspect item from drop-down menu
                Object weapChoice = selWeap.getSelectedItem(); //gets weapon item from drop-down menu
                Object roomChoice = currentPlayer.getCurrRoom(); //gets room the current player is in

                suggestion.add((Item)susChoice);
                suggestion.add((Item)weapChoice);
                suggestion.add((Item)roomChoice);

                for (Player p : players.values()) {
                    if (p.equals(currentPlayer)) {
                        continue;
                    }

                    if (p.getCards().contains(susChoice) || p.getCards().contains(weapChoice) || p.getCards().contains(roomChoice)) {
                        Item refutationItem = null;
                        //TODO: Popup - "player * please pick a card to refute", set refutationItem to this
                        p.refute(suggestion, currentPlayer.getName());

                        if (!(refutationItem == null)) {
                            currentPlayer.receiveClues(refutationItem);
                        }
                    }
                }

                frame.dispose();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    //TODO: nextPlayer method called when player moves = 0 in movement method && after player suggests
    private void nextPlayerTurn() {
        System.out.println("entered next player turn");
        String currPlayerName;
        do {
            currPlayerName = playerTurnOrder.get(turnCount % players.size());
            currentPlayer = players.get(currPlayerName);
            System.out.println(currPlayerName);
        } while (currentPlayer.isLost());

        System.out.println(currPlayerName);
        mainView.setPlayerNameLabel(currPlayerName);
        mainView.setRollButton(true);

        checkSuggestButton();

        turnCount++;
    }

    private void checkSuggestButton() {
        if (currentPlayer.getPos().getType() > 0 && currentPlayer.getPos().getType() <= 10) {
            mainView.setSuggestButton(true);
        }
    }

    //========================= CluedoGame method calls below =========================

    private void play() {
        setup();
        playGame();
    }

    public void setup() {
        // display setup window and get setup info, create player objects using GUI and add to array
        System.out.println("Player setup entered");
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

            players.get(playerTurnOrder.get(count)).addCard(deck.pop());
            count++;
        }

        System.out.println("Player setup done");
        nextPlayerTurn();
    }

    //TODO: Fix missing gameOver variable, uncomment below to debug.
    // Needs to implement roll, suggest, accuse functions into its respective buttons

    private void playGame() {
        int totalLost = 0;
        while (!gameOver) {
            for (Player p : players.values()) {
                if (totalLost == players.size()) {
                    // GUI pop up saying all players are out and reveal the murder pocket
                    gameOver = true;
                    break;
                }

                // Player rolls dice, and moves
                Player curr = p;

                // skip player if they have already lost
                if (curr.isLost()) {
                    continue;
                }

                // Display board and who's turn it is
                mainView.updatePlayerLabel(p.getName());
//                int roll = rollDice();

                // Update "moves left" count on GUI
//                mainView.updateMoves(roll);

                // handle player actions
//                for (int i = 0; i < roll; i++) {
//                    if (curr.getPos().getType() > 0 && curr.getPos().getType() <= 10) {
//                        mainView.setSuggestButton(true);
//                    } else {
//                        mainView.setSuggestButton(false);
//                    }
//                }

                diceStatus = true;
            }
        }
    }

    public void rollDice() {
        Random rand = new Random();
        int roll = rand.nextInt(13);

        if (roll == 0) {
            roll = 1;
        }

        mainView.setRollButton(false);      //reset when next player's turn starts
        mainView.updateMoves(roll);

        currentMoves = roll;
    }

    public Cell[][] getCells() {
        return board.getCells();
    }

    public boolean getDiceStatus() {
        return diceStatus; //set diceStatus to true and use this function after a player has finished their turn
    }

    public boolean getSuggestionStatus() {
        return suggestionStatus;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public void setPlayerTurnOrder(ArrayList<String> playerTurnOrder) {
        this.playerTurnOrder = playerTurnOrder;
    }

    public void setPlayers(Map<String, Player> players) {
        this.players = players;
    }

    public static void main(String[] args) {
        MainController mc = new MainController();
        mainView = new MainView(mc);
    }
}
