package views;

import controllers.MainController;

import javax.swing.*;
import java.awt.*;

/**
 * Main Game Window View for Cluedo Game
 */
public class MainView {

    /*
     * TODO: only GUI elements should be stored here
     * TODO: create object of needed controllers
     * TODO: action listeners should call relevant methods from controller objects and update view as needed
     */

    //Window dimensions
    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;

    // == Models =======================
    // store models in relevant controllers?

    // == Views ========================
    // board view
    // setup popup view

    // == Controllers ==================
    private MainController mainController;

    public MainView() {
        mainController = new MainController();
        displayGameWindow();
    }

    /*
     * Create all elements on game window
     *  - get player setup info
     *  - call displayBoard to generate game board
     *  - handle player turns
     */
    private void displayGameWindow() {
        //Define main window specs =========================================================
        JFrame gameWindow = new JFrame("Cluedo");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);

        gameWindow.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        gameWindow.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Create menu bar ==================================================================
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem newGameMI = new JMenuItem("New Game");
        menu.add(newGameMI);

        //Create game display ==============================================================

        //Player name label
        JLabel playerNameLabel = new JLabel("Player Name: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        gameWindow.add(playerNameLabel, c);

        //Game board panel
        JPanel boardPanel = new BoardView();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        gameWindow.add(boardPanel, c);

        //Roll button
        JButton rollButton = new JButton("Roll");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        gameWindow.add(rollButton, c);

        //Suggest button
        JButton suggestButton = new JButton("Suggest");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        gameWindow.add(suggestButton, c);

        //Accuse button
        JButton accuseButton = new JButton("Accuse");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        gameWindow.add(accuseButton, c);

        //Moves label
        JLabel movesLabel = new JLabel("Moves Left: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        gameWindow.add(movesLabel, c);

        //Clues text area
        JTextArea cluesTextArea = new JTextArea();
        cluesTextArea.setEditable(false);                       //Prevent user from editing text area
        JScrollPane cluesSP = new JScrollPane(cluesTextArea);   //Add scroll pane to text area
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 2;
        gameWindow.add(cluesSP, c);

        gameWindow.setJMenuBar(menuBar);
        gameWindow.pack();
        gameWindow.setVisible(true);
    }

    public static void main(String[] args) {
        MainView game = new MainView();
    }

}
