package views;

import controllers.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

/**
 * Main Game Window View for Cluedo Game
 */
public class MainView {

    /*
     * TODO: only GUI elements should be stored here
     * TODO: create object of needed controllers
     * TODO: action listeners should call relevant methods from controller objects and update view as needed
     * TODO: get cell array from MainController
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

    // == View Elements ================
    private JFrame gameWindow;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem newGameMI;

    private JLabel playerNameLabel;
    private JPanel boardPanel;
    private JButton rollButton;
    private JButton suggestButton;
    private JButton accuseButton;
    private JLabel movesLabel;
    private JTextArea cluesTextArea;

    public MainView(MainController mc) {
        mainController = mc;
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
        gameWindow = new JFrame("Cluedo");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(true);

        gameWindow.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        gameWindow.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Create menu bar ==================================================================
        menuBar = new JMenuBar();

        menu = new JMenu("Menu");
        menuBar.add(menu);

        newGameMI = new JMenuItem("New Game");
        newGameMI.addActionListener(e -> {
            mainController.newGameMethod();
        });
        menu.add(newGameMI);

        //Create game display ==============================================================

        //Player name label
        playerNameLabel = new JLabel("Player Name: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        gameWindow.add(playerNameLabel, c);

        //Game board panel
        boardPanel = new BoardView(mainController);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        gameWindow.add(boardPanel, c);

        //Roll button
        rollButton = new JButton("Roll");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        gameWindow.add(rollButton, c);
        rollButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mainController.rollDice();
                System.out.println(mainController.rollDice());
                rollButton.setEnabled(false);
                if(mainController.getDiceStatus()){
                    rollButton.setEnabled(true);
                }
            }
        });

        //Suggest button
        suggestButton = new JButton("Suggest");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        gameWindow.add(suggestButton, c);
        suggestButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mainController.suggestMethod();
            }
        });

        //Accuse button
        accuseButton = new JButton("Accuse");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        gameWindow.add(accuseButton, c);
        accuseButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mainController.accuseMethod();
            }
        });

        //Moves label
        movesLabel = new JLabel("Moves Left: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        gameWindow.add(movesLabel, c);

        //Clues text area
        cluesTextArea = new JTextArea();
        cluesTextArea.setEditable(false);                       //Prevent user from editing text area
        JScrollPane cluesSP = new JScrollPane(cluesTextArea);   //Add scroll pane to text area
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 2;
        gameWindow.add(cluesSP, c);

        gameWindow.setJMenuBar(menuBar);
        gameWindow.pack();
        gameWindow.setVisible(true);
    }

//    public static void main(String[] args) {
//        MainView game = new MainView();
//    }

}
