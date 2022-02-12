package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Collection implements Iterable<Card> {
    private List<Card> collection;

//  Constructor for collection
    public Collection(List<Card> list) {
        this.collection  = list;
    }

    public List<Card> showCollection() {
        return this.collection;
    }

//  REQUIRES: Card to be added
//  EFFECTs: Adds desired card to the collection
    public void addCard(Card c) {
        this.collection.add(c);
    }

    public void addCards(List<Card> lc) {
        this.collection.addAll(lc);
    }

    public void removeCard(Card c) {
        this.collection.remove(c);
    }

    public void addToDeck(Card c,Deck d) {
        d.addCard(c);
        this.collection.remove(c);
    }

    public Iterator<Card> iterator() {
        return this.collection.iterator();
    }


    public Card getCardfromID(int id) {
        for (Card card : this.collection) {
            if (card.getCardID() == id) {
                return card;
            }
        }
        return null;
    }
}
