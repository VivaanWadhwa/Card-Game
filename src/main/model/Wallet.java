package model;

public class Wallet {
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
}
