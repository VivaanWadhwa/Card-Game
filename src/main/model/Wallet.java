package model;

public class Wallet {
    private int balance;

    public Wallet() {
        this.balance = 1000;
    }

    public int getBalance() {
        return this.balance;
    }

    public void deductBalance(int cost) {
        if (this.balance > cost) {
            this.balance -= cost;
        }
    }
}
