package model;

import model.moves.Move;

import java.util.List;

public class Card {
    private String name;
    private int cardID;
    private int health;
    private List<Move> moves;



//  Constructor for Card
    public Card(String name,int cardId,List<Move> moves) {
        this.name = name;
        this.cardID = cardId;
        this.moves = moves;
        this.health = 100;

    }


    //   Getters
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getCardID() {
        return cardID;
    }

    public List<Move> getMoves() {
        return this.moves;
    }

    public void doDamage(Move m) {
        this.health -= m.getDamage();
    }
}
