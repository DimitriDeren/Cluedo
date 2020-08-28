package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private Cell[][] cells = new Cell[24][25];
    ArrayList<Room> rooms = new ArrayList<>(Arrays.asList(
            new Room("Kitchen"), new Room("Ball model.Room"), new Room("Conservatory"),
            new Room("Dinning model.Room"), new Room("Billiard model.Room"), new Room("Library"),
            new Room("Lounge"), new Room("Hall"), new Room("Study"), new Room("Corridor"), new Room("Stairs")
    ));

    public Board() {
        for (int y = 0; y < cells[0].length; y++) {
            for (int x = 0; x < cells.length; x++) {
                cells[x][y] = new Cell(x,y,0,0, rooms.get(9), Color.YELLOW);
            }
        }

        //Kitchen Initialization

        //Kitchen walls
        for(int x = 0; x < 7; x++){
            for(int y = 0; y < 5; y++){
                cells[x][y] = new Cell(x, y, 13, 0, rooms.get(0), Color.BLACK);
            }
        }

        //Kitchen model.Room
        for(int x = 0; x < 6; x++){
            for(int y = 0; y < 4; y++){
                cells[x][y] = new Cell(x, y, 1, 0, rooms.get(0), Color.YELLOW);
            }
        }

        //Ballroom Initialization

        //Ballroom walls
        for(int x = 8; x < 16; x++){
            for(int y = 0; y < 5; y++){
                cells[x][y] = new Cell(x, y, 13, 0, rooms.get(1), Color.BLACK);
            }
        }

        //Ballroom room
        for(int x = 9; x < 15; x++){
            for(int y = 0; y < 4; y++){
                cells[x][y] = new Cell(x, y, 2, 0, rooms.get(1), Color.YELLOW);
            }
        }

        //Conservatory model.Room Initialization

        //Conservatory Walls
        for(int x = 17; x < 24; x++){
            for(int y = 0; y < 5; y++){
                cells[x][y] = new Cell(x, y, 13, 0, rooms.get(2), Color.BLACK);
            }
        }

        //Conservatory model.Room
        for(int x = 18; x < 23; x++){
            for(int y = 0; y < 4; y++){
                cells[x][y] = new Cell(x, y, 3, 0, rooms.get(2), Color.YELLOW);
            }
        }

        //Dining model.Room Initialization

        //Dining model.Room Walls
        for(int x = 0; x < 7; x++){
            for(int y = 6; y < 19; y++){
                cells[x][y] = new Cell(x, y, 13, 0, rooms.get(3), Color.BLACK);
            }
        }

        //Conservatory model.Room
        for(int x = 0; x < 6; x++){
            for(int y = 7; y < 18; y++){
                cells[x][y] = new Cell(x, y, 4, 0, rooms.get(3), Color.YELLOW);
            }
        }

        //Stairs model.Room Initialization

        //Stairs model.Room Walls
        for(int x = 8; x < 16; x++){
            for(int y = 6; y < 19; y++){
                cells[x][y] = new Cell(x, y, 13, 0, rooms.get(10), Color.BLACK);
            }
        }

        for(int x = 9; x < 15; x++){
            for(int y = 7; y < 18; y++){
                cells[x][y] = new Cell(x, y, 5, 0, rooms.get(10), Color.YELLOW);
            }
        }

        //Billiard model.Room Initialization

        //Billiard model.Room Walls
        for(int x = 17; x < 24; x++){
            for(int y = 6; y < 12; y++){
                cells[x][y] = new Cell(x, y, 13, 0, rooms.get(4), Color.BLACK);
            }
        }

        //Billiard model.Room
        for(int x = 18; x < 23; x++){
            for(int y = 7; y < 11; y++){
                cells[x][y] = new Cell(x, y, 6, 0, rooms.get(4), Color.YELLOW);
            }
        }

        //Library model.Room Initialization

        //Library model.Room Walls
        for(int x = 17; x < 24; x++){
            for(int y = 13; y < 19; y++){
                cells[x][y] = new Cell(x, y, 13, 0, rooms.get(5), Color.BLACK);
            }
        }

        //Library model.Room
        for(int x = 18; x < 23; x++){
            for(int y = 14; y < 18; y++){
                cells[x][y] = new Cell(x, y, 7, 0, rooms.get(5), Color.YELLOW);
            }
        }

        //Lounge model.Room Initialization

        //Lounge model.Room Walls
        for(int x = 0; x < 7; x++){
            for(int y = 20; y < 25; y++){
                cells[x][y] = new Cell(x, y, 13, 0, rooms.get(6), Color.BLACK);
            }
        }

        //Lounge model.Room
        for(int x = 0; x < 6; x++){
            for(int y = 21; y < 24; y++){
                cells[x][y] = new Cell(x, y, 8, 0, rooms.get(6), Color.YELLOW);
            }
        }

        //Hall model.Room Initialization

        //Hall model.Room Walls
        for(int x = 8; x < 16; x++){
            for(int y = 20; y < 25; y++){
                cells[x][y] = new Cell(x, y, 13, 0, rooms.get(7), Color.BLACK);
            }
        }

        //Hall model.Room
        for(int x = 9; x < 15; x++){
            for(int y = 21; y < 24; y++){
                cells[x][y] = new Cell(x, y, 9, 0, rooms.get(7), Color.YELLOW);
            }
        }

        //Study model.Room Initialization

        //Study model.Room Walls
        for(int x = 17; x < 24; x++){
            for(int y = 20; y < 25; y++){
                cells[x][y] = new Cell(x, y, 13, 0, rooms.get(8), Color.BLACK);
            }
        }

        //Study model.Room
        for(int x = 18; x < 23; x++){
            for(int y = 21; y < 24; y++){
                cells[x][y] = new Cell(x, y, 10, 0, rooms.get(8), Color.YELLOW);
            }
        }

        cells[4][4] = new Cell(4, 4, 1, 0, rooms.get(0), Color.DARK_GRAY); //Kitchen entrance/exit
        cells[11][4] = new Cell(11, 4, 2, 0, rooms.get(1), Color.DARK_GRAY); //Ball entrance/exit
        cells[19][4] = new Cell(19, 4, 3, 0, rooms.get(2), Color.DARK_GRAY); //Conservatory entrance/exit
        cells[4][6] = new Cell(4, 6, 4, 0, rooms.get(3), Color.DARK_GRAY); //Dining entrance/exit
        cells[4][18] = new Cell(4, 18, 5, 0, rooms.get(3), Color.DARK_GRAY); //Dining entrance/exit
        cells[17][7] = new Cell(4, 4, 6, 0, rooms.get(4), Color.DARK_GRAY); //Billiard entrance/exit
        cells[17][17] = new Cell(4, 4, 7, 0, rooms.get(5), Color.DARK_GRAY); //Library entrance/exit
        cells[6][21] = new Cell(4, 4, 8, 0, rooms.get(6), Color.DARK_GRAY); //Lounge entrance/exit
        cells[11][20] = new Cell(4, 4, 9, 0, rooms.get(7), Color.DARK_GRAY); //Hall entrance/exit
        cells[17][21] = new Cell(4, 4, 10, 0, rooms.get(8), Color.DARK_GRAY); //Study entrance/exit



        //cells[8][17] = new model.Cell(8, 17, 5, 0); //Stairs entrance/exit
        //cells[15][7] = new model.Cell(15, 7, 5, 0); //Stairs entrance/exit

    }

    public void draw() {
        System.out.println("BOARD -------------------------------------------------------------------");
        for (int y = 0; y < cells[0].length; y++) {
            for (int x = 0; x < cells.length; x++) {
                if (cells[x][y].getOccupant() != 0) {
                    if (cells[x][y].getOccupant() == 1) {
                        System.out.print("1 ");
                    } else if (cells[x][y].getOccupant() == 2) {
                        System.out.print("2 ");
                    } else if (cells[x][y].getOccupant() == 3) {
                        System.out.print("3 ");
                    } else if (cells[x][y].getOccupant() == 4) {
                        System.out.print("4 ");
                    } else if (cells[x][y].getOccupant() == 5) {
                        System.out.print("5 ");
                    } else {
                        System.out.print("6 ");
                    }
                } else if (cells[x][y].getType() == 0) {
                    System.out.print("  ");
                } else if (cells[x][y].getType() > 0 && cells[x][y].getType() <= 10){
                    System.out.print("R ");
                } else {
                    System.out.print("| ");
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    /* old ver
                if (cells[x][y].getType() == 0) {
                    System.out.print("  ");
                } else if (cells[x][y].getType() > 0 && cells[x][y].getType() <= 10){
                    System.out.print("R ");
                } else if (cells[x][y].getType() == 11) {
                    if (cells[x][y].getOccupant() == 1) {
                        System.out.print("1 ");
                    } else if (cells[x][y].getOccupant() == 2) {
                        System.out.print("2 ");
                    } else if (cells[x][y].getOccupant() == 3) {
                        System.out.print("3 ");
                    } else if (cells[x][y].getOccupant() == 4) {
                        System.out.print("4 ");
                    } else if (cells[x][y].getOccupant() == 5) {
                        System.out.print("5 ");
                    } else {
                        System.out.print("6 ");
                    }
                } else {
                    System.out.print("| ");
                }
     */

    public Cell[][] getCells() {
        return cells;
    }
}