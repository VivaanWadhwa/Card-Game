package model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Shop {
    private Hashtable<Card, Integer> inventory = new Hashtable<>();

    private void shop(Hashtable<Card, Integer> inv) {
        this.inventory = inv;
    }

    private void addItem(Card c, int price) {
        this.inventory.put(c,price);
    }

    private void buyItem(Card c, Wallet w, Collection col) {
        col.addCard(c);
        w.deductBalance(this.inventory.get(c));
        this.inventory.remove(c);
    }
}
