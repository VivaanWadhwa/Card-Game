package model;

public enum Direction {
    UP(0,-1),
    DOWN(0,1),
    LEFT(-1,0),
    RIGHT(1,0);

    private int dx;
    private int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
//  REQUIRES: Current pos of object
//  MODIFIES: Pos of object
//  EFFECTS: changes the pos of the object
//           according to the direction of movement
    public Position move(Position pos) {
        return new Position (
                pos.getX() + dx,
                pos.getY() + dy
        );
    }
}
