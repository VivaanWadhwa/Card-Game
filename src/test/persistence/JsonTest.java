package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkHasCards(HasCards hs, Collection col, Deck deck, Shop shop, Wallet wallet) {
        assertEquals(col, hs.getCollection());
        assertEquals(deck, hs.getDeck());
        assertEquals(shop, hs.getShop());
        assertEquals(wallet, hs.getWallet());
    }
}
