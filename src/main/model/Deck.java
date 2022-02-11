package model;

import sun.security.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> deck;
    private static final int MAX_CARDS = 5;

//  Constructor for Deck
//  EFFECTS: Constructs a deck
//  no of cards < MAX_CARDS
    public void deck(ArrayList<Card> list) {
        if (list.size() < MAX_CARDS) {
            this.deck = list;
        }
    }

    public void addCard(Card c) {
        if (deck.size() < MAX_CARDS) {
            deck.add(c);
        }
    }

    public void removeCard(Card c) {
        if (deck.size() > 0) {
            deck.remove(c);
        }
    }
}
