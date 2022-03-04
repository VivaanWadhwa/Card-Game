package model;

import org.json.JSONObject;
import persistence.Writable;


public class HasCards implements Writable {
    Collection collection;
    Deck deck;
    Shop shop;
    Wallet wallet;


    public HasCards(Collection collection, Deck deck, Shop shop, Wallet wallet) {
        this.collection = collection;
        this.deck = deck;
        this.shop = shop;
        this.wallet = wallet;
    }

//  Getters
    public Collection getCollection() {
        return collection;
    }

    public Deck getDeck() {
        return deck;
    }

    public Shop getShop() {
        return shop;
    }

    public Wallet getWallet() {
        return wallet;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Collection",collection.toJson());
        json.put("Deck",deck.toJson());
        json.put("Shop",shop.toJson());
        json.put("Wallet",wallet.toJson());
        return json;
    }
}
