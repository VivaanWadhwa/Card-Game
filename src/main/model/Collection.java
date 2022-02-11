package model;

import java.util.ArrayList;
import java.util.List;

public class Collection {
    private List<Card> collection;

//  Constructor for collection
    public void collection() {
        this.collection  = new ArrayList<>();
    }

    public List<Card> showCollection() {
        return this.collection;
    }

//  REQUIRES: Card to be added
//  EFFECTs: Adds desired card to the collection
    public void addCard(Card c) {
        this.collection.add(c);
    }

    public void removeCard(Card c) {
        this.collection.remove(c);
    }

    public void addToDeck(Card c,Deck d) {
        d.addCard(c);
        this.collection.remove(c);
    }
}
