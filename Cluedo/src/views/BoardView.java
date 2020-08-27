package views;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {

    /*
     * TODO: note this code is just an example for testing display area on main view
     */

    // TODO: pass in MainController object from MainView to BoardView

    private static final int RECT_X = 0;
    private static final int RECT_Y = RECT_X;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 650;
    private static final int RECT_WIDTH = WINDOW_WIDTH / 24;
    private static final int RECT_HEIGHT = WINDOW_HEIGHT / 25;



    private static final int spacing = 2;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the rectangle here
        g.drawRect(RECT_X, RECT_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(RECT_X, RECT_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.setColor(Color.yellow);


        for (int y = 0; y < 24; y++) {
            for (int x = 0; x < 24; x++) {
                g.setColor(Color.YELLOW);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Kitchen Initialization

        //Kitchen walls
        for(int x = 0; x < 7; x++){
            for(int y = 0; y < 5; y++){
                g.setColor(Color.BLACK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Kitchen Room
        for(int x = 0; x < 6; x++){
            for(int y = 0; y < 4; y++){
                g.setColor(Color.PINK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Ballroom Initialization

        //Ballroom walls
        for(int x = 8; x < 16; x++){
            for(int y = 0; y < 5; y++){
                g.setColor(Color.BLACK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Ballroom room
        for(int x = 9; x < 15; x++){
            for(int y = 0; y < 4; y++){
                g.setColor(Color.PINK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Conservatory Room Initialization

        //Conservatory Walls
        for(int x = 17; x < 24; x++){
            for(int y = 0; y < 5; y++){
                g.setColor(Color.BLACK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Conservatory Room
        for(int x = 18; x < 23; x++){
            for(int y = 0; y < 4; y++){
                g.setColor(Color.PINK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Dining Room Initialization

        //Dining Room Walls
        for(int x = 0; x < 7; x++){
            for(int y = 6; y < 19; y++){
                g.setColor(Color.BLACK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Conservatory Room
        for(int x = 0; x < 6; x++){
            for(int y = 7; y < 18; y++){
                g.setColor(Color.PINK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Stairs Room Initialization

        //Stairs Room Walls
        for(int x = 8; x < 16; x++){
            for(int y = 6; y < 19; y++){
                g.setColor(Color.BLACK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        for(int x = 9; x < 15; x++){
            for(int y = 7; y < 18; y++){
                g.setColor(Color.PINK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Billiard Room Initialization

        //Billiard Room Walls
        for(int x = 17; x < 24; x++){
            for(int y = 6; y < 12; y++){
                g.setColor(Color.BLACK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Billiard Room
        for(int x = 18; x < 23; x++){
            for(int y = 7; y < 11; y++){
                g.setColor(Color.PINK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Library Room Initialization

        //Library Room Walls
        for(int x = 17; x < 24; x++){
            for(int y = 13; y < 19; y++){
                g.setColor(Color.BLACK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Library Room
        for(int x = 18; x < 23; x++){
            for(int y = 14; y < 18; y++){
                g.setColor(Color.PINK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Lounge Room Initialization

        //Lounge Room Walls
        for(int x = 0; x < 7; x++){
            for(int y = 20; y < 25; y++){
                g.setColor(Color.BLACK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Lounge Room
        for(int x = 0; x < 6; x++){
            for(int y = 21; y < 24; y++){
                g.setColor(Color.PINK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Hall Room Initialization

        //Hall Room Walls
        for(int x = 8; x < 16; x++){
            for(int y = 20; y < 25; y++){
                g.setColor(Color.BLACK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Hall Room
        for(int x = 9; x < 15; x++){
            for(int y = 21; y < 24; y++){
                g.setColor(Color.PINK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Study Room Initialization

        //Study Room Walls
        for(int x = 17; x < 24; x++){
            for(int y = 20; y < 25; y++){
                g.setColor(Color.BLACK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }

        //Study Room
        for(int x = 18; x < 23; x++){
            for(int y = 21; y < 24; y++){
                g.setColor(Color.PINK);
                g.fillRect(spacing + x * RECT_WIDTH, spacing + y * RECT_HEIGHT + RECT_HEIGHT, RECT_WIDTH - 2 * spacing, RECT_HEIGHT - 2 * spacing);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // so that our GUI is big enough
        return new Dimension(WINDOW_WIDTH + 2, WINDOW_HEIGHT + 2);
    }



}
