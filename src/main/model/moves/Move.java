package model.moves;

public class Move {
    private int damage;
    private int speed;

    public Move(int d, int s) {
        this.damage = d;
        this.speed = s;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getSpeed() {
        return this.speed;
    }

}
