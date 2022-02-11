package model;

public class Card {
    private String name;
    private int cardID;

//  Constructor for Card
    public void card(String name, int cardID) {
        this.name = name;
        this.cardID = cardID;
    }

//   Getters
    public String getName() {
        return name;
    }

    public int getCardID() {
        return cardID;
    }
}
