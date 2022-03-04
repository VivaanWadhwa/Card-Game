package model;

import org.json.JSONObject;
import persistence.Writable;

public class Wallet implements Writable {
    private int balance;

//  Constructor
    public Wallet() {
        this.balance = 1000;
    }

//  Getters
    public int getBalance() {
        return this.balance;
    }

//  MODIFIES: this
//  EFFECTS: deducts balance from wallet
    public void deductBalance(int cost) {
        if (this.balance > cost) {
            this.balance -= cost;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Balance",balance);
        return json;
    }
}
