package model.moves;

public class Move {
    private int damage;
    private int speed;
    private String name;

    public Move(String name, int d, int s) {
        this.damage = d;
        this.speed = s;
        this.name = name;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getSpeed() {
        return this.speed;
    }

    public String getName() {
        return this.name;
    }

}
