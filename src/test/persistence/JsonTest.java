package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkHasCards(HasCards hs, Collection col, Deck deck, Shop shop, Wallet wallet) {
        assertEquals(col.showCollection().get(0).getCardID(), hs.getCollection().showCollection().get(0).getCardID());
        assertEquals(deck.getCards().get(0).getCardID(), hs.getDeck().getCards().get(0).getCardID());
        assertEquals(shop.getKeys().iterator().next().getCardID(),hs.getShop().getKeys().iterator().next().getCardID());
        assertEquals(wallet.getBalance(), hs.getWallet().getBalance());
    }

    protected void checkEmpty(HasCards hs, Collection col, Deck deck, Shop shop, Wallet wallet) {
    }
}
