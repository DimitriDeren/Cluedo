package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    /**
     * The height of main frame
     */
    public static final int HEIGHT = 800;
    /**
     * The width of game board (left panel)
     */
    public static final int LEFT_PANEL_WIDTH = 800;
    /**
     * the width of game board (right panel)
     */
    public static final int RIGHT_PANEL_WIDTH = 800;

    // =========== Views ================

    /**
     * the main window
     */
    private JPanel window;


    // ============= models ===================

    /*
     * the game
     */
    //private model.CluedoGame game;

    /**
     * the number of players
     */
    private int numPlayers;
    /**
     * the number of dices
     */
    private int numDices;

    /**
     * Construct a views.GUI to run Cluedo
     */
    public GUI() {
        welcomeScreen();
    }

    /**
     * Initialise the main frame, menuBar, and a welcome screen
     */
    private void welcomeScreen() {

        JFrame frame = new JFrame("Menu");
        frame.setSize(1920,1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);


        Integer[] players = { 3, 4, 5, 6 };

        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        JComboBox playerAmount = new JComboBox(players);
        playerAmount.setSelectedIndex(0);
        frame.add(playerAmount);
        JButton button;

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        button = new JButton("Button 1");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(playerAmount, c);

        button = new JButton("Button 2");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        frame.add(button, c);

        button = new JButton("Button 3");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        frame.add(button, c);


        //BOARD SECTION
        button = new JButton("BOARD BUTTON");
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        frame.add(button, c);

        button = new JButton("5");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        //c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 2;       //third row
        frame.add(button, c);

    }

    private class MyGridLayout {

        JFrame f;
        MyGridLayout(){
            f = new JFrame();
        }

    }




}

