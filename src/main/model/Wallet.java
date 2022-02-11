package model;

public class Wallet {
    private int balance;

    public void wallet() {
        this.balance = 1000;
    }

    public int getBalance() {
        return this.balance;
    }

    public void deductBalance(int cost) {
        this.balance -= cost;
    }
}
