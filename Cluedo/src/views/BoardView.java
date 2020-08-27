package views;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {

    /*
     * TODO: note this code is just an example for testing display area on main view
     */

    private static final int RECT_X = 0;
    private static final int RECT_Y = RECT_X;
    private static final int RECT_WIDTH = 800;
    private static final int RECT_HEIGHT = 650;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the rectangle here
        g.drawRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
    }

    @Override
    public Dimension getPreferredSize() {
        // so that our GUI is big enough
        return new Dimension(RECT_WIDTH + 2, RECT_HEIGHT + 2);
    }

}
