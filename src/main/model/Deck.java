package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Deck implements Iterable<Card> {
    private List<Card> deck;
    private static final int MAX_CARDS = 5;

//  Constructor for Deck
//  EFFECTS: Constructs a deck
//  no of cards < MAX_CARDS
    public Deck(List<Card> list) {
        if (list.size() < MAX_CARDS) {
            this.deck = list;
        }
    }

    public void addCard(Card c) {
        if (deck.size() < MAX_CARDS) {
            deck.add(c);
        }
    }

    public void addCards(List<Card> lc) {
        deck.addAll(lc);
    }

    public void removeCard(Card c) {
        if (deck.size() > 0) {
            deck.remove(c);
        }
    }

    public Iterator<Card> iterator() {
        return this.deck.iterator();
    }

    public Card getCardfromID(int id) {
        for (Card card : this.deck) {
            if (card.getCardID() == id) {
                return card;
            }
        }
        return null;
    }

}
