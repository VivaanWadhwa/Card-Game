package model.moves;

import org.json.JSONObject;
import persistence.Writable;

public class Move implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name",name);
        json.put("Damage",damage);
        json.put("Speed", speed);
        return json;
    }
}
