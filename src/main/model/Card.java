package model;

import model.moves.Move;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a Card
public class Card implements Writable {
    private String name;
    private int cardID;
    private int health;
    private ArrayList<Move> moves;
    private String condition;



//  Constructor for Card
    public Card(String name,int cardId,ArrayList<Move> moves) {
        this.name = name;
        this.cardID = cardId;
        this.moves = moves;
        this.health = 100;
        this.condition = "Alive";
    }


    //   Getters
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public List<Move> getMoves() {
        return this.moves;
    }

    public String checkCondition() {
        return this.condition;
    }

//  MODIFIES: this
//  EFFECTS: Gets a particular card from the deck using its ID
    public int getCardID() {
        return cardID;
    }

//  MODIFIES: this
//  EFFECTS: Reduces health of card according to damage of Move
//           if health reaches zero, maintains it at that
//           if health is < 50, condition is damages, and if =0, then Fainted
    public void doDamage(Move m) {
        this.health -= m.getDamage();
        if (this.health <= 0) {
            this.health = 0;
            this.condition = "Fainted";
        } else if (this.health < 50) {
            this.condition = "Damaged";
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("CardID", cardID);
        json.put("Moves", movesToJson(moves));
        json.put("Health",health);
        json.put("Condition",condition);
        return json;
    }

//  EFFECTS:Represents a list of Moves as a JSON Array
    private JSONArray movesToJson(List<Move> moves) {
        JSONArray jsonArray = new JSONArray();

        for (Move m : moves) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
}
