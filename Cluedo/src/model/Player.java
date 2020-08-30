package model;

import java.awt.*;
import java.util.*;

/**
 * model.Player class to handle all player actions and track position and known clues
 */
public class Player {

    private final String separate = "===========================================\n";

    //player state
    private String name;
    private Suspects playerSuspect;
    private Cell pos;
    private boolean lost;
    private ArrayList<Item> cards = new ArrayList<>();

    private final Map<Suspects, String> suspects;
    private final Map<Weapon, String> weapons;
    private final Map<Room, String> rooms;

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 650;
    private static final int RECT_WIDTH = WINDOW_WIDTH / 24;
    private static final int RECT_HEIGHT = WINDOW_HEIGHT / 25;
    private static final int spacing = 2;

    /**
     * Initialise model.Player object
     * @param name - player name
     * @param playerSuspect - suspect character chosen by player
     * @param startPos - starting position of player on board
     * @param susObjs - list of suspects in game
     * @param weaObjs - list of weapons in game
     * @param rmObjs - list of rooms in game
     */
    public Player(String name, Suspects playerSuspect, Cell startPos, ArrayList<Suspects> susObjs, ArrayList<Weapon> weaObjs, ArrayList<Room> rmObjs) {
        this.name = name;
        this.playerSuspect = playerSuspect;
        this.pos = startPos;
        this.lost = false;

        this.suspects = new HashMap<>();
        for (Suspects sus : susObjs) {
            suspects.put(sus, " ");
        }

        this.weapons = new HashMap<>();
        for (Weapon wea : weaObjs) {
            weapons.put(wea, " ");
        }

        this.rooms = new HashMap<>();
        for (Room rm : rmObjs) {
            rooms.put(rm, " ");
        }
    }

    /**
     * Finds next cell co-ordinate based on direction passed in
     * @param dir - direction to move player
     * @return int[x][y] co-ordinate array of new cell position
     */
    public int[] move(Integer dir) {
        //get next co-ordinate - handle movable checks
        int x = pos.getX();
        int y = pos.getY();

        if (dir.equals(1)) {
            // North movement
            y--;
            if (y < 0) { y = -1; }
        } else if (dir.equals(3)) {
            // South movement
            y++;
            if (y > 24) { y = -1; }
        } else if (dir.equals(2)) {
            // East movement
            x++;
            if (x > 23) { x = -1; }
        } else if (dir.equals(4)) {
            // West movement
            x--;
            if (x < 0) { x = -1; }
        }

        return new int[]{x, y};
    }

    /**
     * Asks player for suggestion based on current room and returns list to main game class
     * @return ArrayList suggestion {suspect, weapon, room}
     */
    public ArrayList<Item> suggest() {
        ArrayList<Item> suggestion = new ArrayList<>();
        Room currRoom = getCurrRoom();
        Suspects suspect;
        Weapon weapon;

        System.out.println("What suggestion would you like to make in " + currRoom.getName() + "?");

        int counter = 1;
        ArrayList<Suspects> susNames = new ArrayList<>();
        for (Suspects sus : suspects.keySet()) {
            System.out.println(" " + counter + ". " + sus.getName());
            susNames.add(sus);
            counter++;
        }
        suspect = susNames.get(Utility.checkIntInput() - 1);

        System.out.println(suspect.getName() + " committed crime with: ");

        counter = 1;
        ArrayList<Weapon> weaponNames = new ArrayList<>();
        for (Weapon wea : weapons.keySet()) {
            System.out.println(" " + counter + ". " + wea.getName());
            weaponNames.add(wea);
            counter++;
        }
        weapon = weaponNames.get(Utility.checkIntInput() - 1);

        suggestion.add(suspect);
        suggestion.add(weapon);
        suggestion.add(currRoom);

        System.out.println(separate);
        return suggestion;
    }

    /**
     * Asks player for accusation and returns list to main game class
     * @return ArrayList accusation {suspect, weapon, room}
     */
    public ArrayList<Item> accuse() {
        ArrayList<Item> accusation = new ArrayList<>();
        Room room;
        Suspects suspect;
        Weapon weapon;

        System.out.println("What accusation do you want to make?");

        int counter = 1;
        ArrayList<Suspects> susNames = new ArrayList<>();
        for (Suspects sus : suspects.keySet()) {
            System.out.println(" " + counter + ". " + sus.getName());
            susNames.add(sus);
            counter++;
        }
        suspect = susNames.get(Utility.checkIntInput() - 1);

        System.out.println(suspect.getName() + " committed crime with: ");

        counter = 1;
        ArrayList<Weapon> weaponNames = new ArrayList<>();
        for (Weapon wea : weapons.keySet()) {
            System.out.println(" " + counter + ". " + wea.getName());
            weaponNames.add(wea);
            counter++;
        }
        weapon = weaponNames.get(Utility.checkIntInput() - 1);

        System.out.println("...in: ");

        counter = 1;
        ArrayList<Room> roomNames = new ArrayList<>();
        for (Room rm : rooms.keySet()) {
            System.out.println(" " + counter + ". " + rm.getName());
            roomNames.add(rm);
            counter++;
        }
        room = roomNames.get(Utility.checkIntInput() - 1);

        accusation.add(suspect);
        accusation.add(weapon);
        accusation.add(room);

        System.out.println(separate);
        return accusation;
    }

    /**
     * Receive suggestion items and if possible ask player for a refutation. Return null if no possible refutation
     * @param suggestion - items suggested by another player
     * @param otherName - name of player who made the suggestion
     * @return model.Item refuteCard - item object to refute with
     */
    public Item refute(ArrayList<Item> suggestion, String otherName) {
        Item refuteCard = null;

        if (!Collections.disjoint(suggestion, this.cards)) {
            //print ALLOWED cards in hand and ask for refutation
            ArrayList<Item> refuteList = new ArrayList<>();
            System.out.println("model.Player " + name + " - Please select a card to refute " + otherName + "'s suggestion (select relevant number): ");

            int count = 0;
            for (int i = 0; i < suggestion.size(); i++) {
                if (this.cards.contains(suggestion.get(i))) {
                    refuteList.add(suggestion.get(i));
                    count++;
                    System.out.println("    " + count + ". " + suggestion.get(i).getName());
                }
            }

            int choice = -1;
            while (choice < 0 || choice > count - 1) {
                choice = Utility.checkIntInput() - 1;
            }
            refuteCard = refuteList.get(choice);
        }

        System.out.println(separate);
        return refuteCard;
    }

    /**
     * Receive clues from other players due to refutation
     * @param clue - received from other players
     */
    public void receiveClues(Item clue) {
        //find relevant map to update by checking if clue is right type & exists in the map
        if (clue instanceof Suspects && suspects.containsKey(clue)) {
            suspects.replace((Suspects) clue, "X");
        } else if (clue instanceof Weapon && weapons.containsKey(clue)) {
            weapons.replace((Weapon) clue, "X");
        } else if (clue instanceof Room && rooms.containsKey(clue)) {
            rooms.replace((Room) clue, "X");
        }
    }

    /**
     * Display current clue report of player.
     *  - Will display an "X" against a received clue
     *  - Will display an " " against clues not yet received
     */
    public void checkClues() {
        System.out.println("MANSION CLUE REPORT");
        System.out.println(separate);
        System.out.println("WHO?");
        for (Map.Entry<Suspects, String> entry : suspects.entrySet()) {
            System.out.println(" - " + entry.getKey().getName() + " | " + entry.getValue() + " | ");
        }
        System.out.println("WHAT?");
        for (Map.Entry<Weapon, String> entry : weapons.entrySet()) {
            System.out.println(" - " + entry.getKey().getName() + " | " + entry.getValue() + " | ");
        }
        System.out.println("WHERE?");
        for (Map.Entry<Room, String> entry : rooms.entrySet()) {
            System.out.println(" - " + entry.getKey().getName() + " | " + entry.getValue() + " | ");
        }
        System.out.println(separate);
    }

    public void draw(Graphics g){
        //TODO: Get player position in 2D Board and draw it
        if(playerSuspect.getName().equals("Miss Scarlett")){
            g.setColor(Color.RED);
        } else if (playerSuspect.getName().equals("Colonel Mustard")){
            g.setColor(Color.ORANGE);
        } else if(playerSuspect.getName().equals("Mrs. White")){
            g.setColor(Color.WHITE);
        } else if (playerSuspect.getName().equals("Mr. Green")){
            g.setColor(Color.GREEN);
        } else if(playerSuspect.getName().equals("Mrs. Peacock")){
            g.setColor(Color.BLUE);
        } else {
            g.setColor(Color.PINK);
        }
        g.fillOval((spacing + pos.getX() * RECT_WIDTH) + (RECT_WIDTH / 8) - 1, spacing + pos.getY() * RECT_HEIGHT + RECT_HEIGHT, (RECT_HEIGHT - 2 * spacing), RECT_HEIGHT - 2 * spacing);
    }

    public Room getCurrRoom() {
        return pos.getRoom();
    }


    public void setPos(Cell newPos) {
        this.pos = newPos;
    }

    public Cell getPos() {
        return this.pos;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public void addCard(Item card) {
        cards.add(card);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getCards() {
        return cards;
    }

}
