package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WalletTest {
    private Wallet wallet;


    @BeforeEach
    void runBefore() {
        wallet = new Wallet();
    }

    @Test
    void testConstructor() {
        assertEquals(1000,wallet.getBalance());
    }


    @Test
    void testDeductBalanceBaseCase() {
        wallet.deductBalance(850);
        assertEquals(150,wallet.getBalance());
    }

    @Test
    void testDeductBalanceBalanceLess() {
        wallet.deductBalance(10001);
        assertEquals(1000,wallet.getBalance());
    }
}
