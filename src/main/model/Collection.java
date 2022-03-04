package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Iterator;
import java.util.List;

public class Collection implements Iterable<Card>, Writable {
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

//  MODIFIES: this and deck
//  EFFECTS: Removes card from collection and adds to deck
    public void addToDeck(Card c,Deck d) {
        d.addCard(c);
        this.collection.remove(c);
    }

//  EFFECTS: returns iterable collection
    public Iterator<Card> iterator() {
        return this.collection.iterator();
    }

//  MODIFIES: this
//  EFFECTS: Gets a particular card from the deck using its ID
    public Card getCardfromID(int id) {
        for (Card card : this.collection) {
            if (card.getCardID() == id) {
                return card;
            }
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Collection",cardsToJson(collection));
        return json;
    }

    private JSONArray cardsToJson(List<Card> collection) {
        JSONArray jsonArray = new JSONArray();

        for (Card c : collection) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
