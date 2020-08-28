package views;

import controllers.MainController;

import javax.swing.*;
import java.awt.*;

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
        newFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        newFrame.setResizable(false);

        newFrame.setMinimumSize(new Dimension(500, 500));
        newFrame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Number of players combobox
        Integer[] numPlayers = {3, 4, 5, 6};
        playerNumChooser = new JComboBox<>(numPlayers);
        newFrame.add(playerNumChooser);


        newFrame.setVisible(true);
    }

}
