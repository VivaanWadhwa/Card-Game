package model;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Shop {
    private Hashtable<Card, Integer> inventory;

// Constructor
    public Shop(Hashtable<Card, Integer> inv) {
        this.inventory = inv;
    }

//  Getters
    public Set<Card> getKeys() {
        return this.inventory.keySet();
    }

    public int getValue(Card c) {
        return this.inventory.get(c);
    }

//  MODIFIES: this
//  EFFECTS: adds item to inventory with its price
    public void addItem(Card c, int price) {
        this.inventory.put(c,price);
    }

//  MODIFIES: this, Card, Wallet and Collection
//  EFFECTS: Removes item from shop, deducts cost from wallet, and adds to collection
    public void buyItem(Card c, Wallet w, Collection col) {
        col.addCard(c);
        w.deductBalance(this.inventory.get(c));
        this.inventory.remove(c);
    }

//  MODIFIES: this
//  EFFECTS: Gets a particular card from the deck using its ID
    public Card getCardFromID(int id) {
        for (Card card : this.inventory.keySet()) {
            if (card.getCardID() == id) {
                return card;
            }
        }
        return null;
    }

}
