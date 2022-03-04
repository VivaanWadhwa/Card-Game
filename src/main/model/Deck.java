package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Deck implements Iterable<Card>, Writable {
    private List<Card> deck;
    private static final int MAX_CARDS = 5;

//  Constructor for Deck
//  EFFECTS: Constructs a deck
//  no of cards < MAX_CARDS
    public Deck(List<Card> list) {
        if (list.size() <= MAX_CARDS) {
            this.deck = list;
        }
    }

//  Getter
    public List<Card> getCards() {
        return this.deck;
    }

//  MODIFIES: this
//  REQUIRES: deck size < MAX_CARDS
//  EFFECTS: adds a card to deck
    public void addCard(Card c) {
        if (deck.size() <= MAX_CARDS) {
            deck.add(c);
        }
    }

//  MODIFIES: this
//  REQUIRES: deck size < MAX_CARDS
//  EFFECTS: adds a list of cards to deck
    public void addCards(List<Card> lc) {
        if (deck.size() <= MAX_CARDS) {
            deck.addAll(lc);
        }
    }

//  MODIFIES: this
//  REQUIRES: deck size != 0
//  EFFECTS: Removes card from deck
    public void removeCard(Card c) {
        if (deck.size() > 0) {
            deck.remove(c);
        }
    }

//  MODIFIES: this
//  EFFECTS: Gets a particular card from the deck using its ID
    public Card getCardfromID(int id) {
        for (Card card : this.deck) {
            if (card.getCardID() == id) {
                return card;
            }
        }
        return null;
    }

//  EFFECTS: returns Iterable list of cards
    public Iterator<Card> iterator() {
        return this.deck.iterator();
    }

//  EFFECTS: Chooses a random card from a given deck
    public Card randomCard() {
        Random rand = new Random();
        Card c = deck.get(rand.nextInt(deck.size()));
        return c;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Deck",cardsToJson(deck));
        return json;
    }

    private JSONArray cardsToJson(List<Card> deck) {
        JSONArray jsonArray = new JSONArray();

        for (Card c : deck) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
