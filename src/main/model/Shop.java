package model;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Shop {
    private Hashtable<Card, Integer> inventory;

    public Shop(Hashtable<Card, Integer> inv) {
        this.inventory = inv;
    }

    public void addItem(Card c, int price) {
        this.inventory.put(c,price);
    }

    public void buyItem(Card c, Wallet w, Collection col) {
        col.addCard(c);
        w.deductBalance(this.inventory.get(c));
        this.inventory.remove(c);
    }

    public Set<Card> getKeys() {
        return this.inventory.keySet();
    }

    public int getValue(Card c) {
        return this.inventory.get(c);
    }

    public Card getCardFromID(int id) {
        for (Card card : this.inventory.keySet()) {
            if (card.getCardID() == id) {
                return card;
            }
        }
        return null;
    }

}
