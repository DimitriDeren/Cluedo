package models;

public class Cell {
    private int x;
    private int y;
    private int type;
    private int occupant;
    private Room room;

    Cell(int x, int y, int type, int occ, Room room) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.occupant = occ;
        this.room = room;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public int getOccupant() {
        return occupant;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setOccupant(int occupant) {
        this.occupant = occupant;
    }

    public Room getRoom(){
        return room;
    }
}
