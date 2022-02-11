package model;

import java.util.Hashtable;

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
}
