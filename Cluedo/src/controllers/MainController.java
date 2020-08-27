package controllers;

import model.Board;
import model.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainController {

    /*
     * TODO: Store logic for stuff like action listeners here
     *  - logic directly manipulating data in Models should be in relevant model class (e.g. handling coordinate movement of players)
     * TODO: Store references to model here (use - import models.*)
     * TODO: Update (or call update methods) of views here
     */

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

    public Cell[][] getCells() {return board.getCells();}
}

