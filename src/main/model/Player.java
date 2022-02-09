package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
//  Position of player
    private Position position;
//  Inventory of Player
    private List<Items> inventory;
//  Starting direction of Player
    private Direction dir;
//  Players Health
    private int health;
//  Height of frame
    protected int height;
//  Width of frame
    protected int width;


//  Constructor
    public Player() {
        this.position = new Position(0,height / 2);
        this.inventory = new ArrayList<>();
        this.dir = Direction.RIGHT;
        this.health = 100;
    }


//  REQUIRES: Position of Player
//  MODIFIES: Position of Player
//  EFFECTS: changes the pos of the object
//           according to the direction of movement
    public void move(Direction dir) {
        dir.move(position);
    }

    public int getHealth() {
        return this.health;
    }

    public void setDir(Direction inputDir) {
        this.dir = inputDir;
    }
}
