package views;

import controllers.MainController;

import javax.swing.*;

/**
 * Popup window for new game setup
 */
public class NewGameView {

    // == Controllers ==================
    private MainController mainController;

    // == View Elements ================
    private JFrame newFrame;
    private JComboBox<Integer> playerNumChooser;

    public NewGameView(MainController mc) {
        mainController = mc;
        createDisplay();
    }

    private void createDisplay() {
        //Define main window specs =========================================================
        newFrame = new JFrame();
        newFrame.setSize(500, 500);
        newFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        newFrame.setResizable(false);

        //Number of players combobox
        Integer[] numPlayers = {3, 4, 5, 6};
        playerNumChooser = new JComboBox<>(numPlayers);
        newFrame.add(playerNumChooser);


        newFrame.setVisible(true);
    }

}
