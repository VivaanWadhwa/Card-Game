package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopTest {
    private Shop shop;
    private final Hashtable<Card, Integer> inv = new Hashtable<>();
    private Card card1;
    private final Set<Card> set = new HashSet<>();
    private Wallet wallet;
    private Collection col;
    private List<Card> otherList;



    @BeforeEach
    void runBefore() {
        card1 = new Card("Card1", 1, null);
        Card card2 = new Card("Card2", 2, null);
        Card card3 = new Card("Card3", 3, null);
        set.add(card1);
        set.add(card2);
        set.add(card3);
        inv.put(card1,100);
        inv.put(card2,100);
        inv.put(card3,100);
        shop = new Shop(inv);
        wallet = new Wallet();
        otherList = new ArrayList<>(Collections.singletonList(card1));
        List<Card> temp = new ArrayList<>();
        col = new Collection(temp);
    }

    @Test
    void testConstructor() {
        assertEquals(set,shop.getKeys());
        assertEquals(100,shop.getValue(card1));
    }

    @Test
    void testBuyItem() {
        shop.buyItem(card1,wallet,col);
        set.remove(card1);
        assertEquals(set,shop.getKeys());
        assertEquals(otherList,col.showCollection());
        assertEquals(900,wallet.getBalance());
    }

    @Test
    void testGetCardFromID() {
        assertEquals(card1,shop.getCardFromID(1));
    }


}
