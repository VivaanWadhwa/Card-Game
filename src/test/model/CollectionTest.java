package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionTest {
    private Collection collection1;
    private List<Card> otherList1;
    private List<Card> otherList2;
    private List<Card> otherList3;
    private Card card4;
    private Card card6;
    private Deck deck1;

    @BeforeEach
    void runBefore() {
        Card card1 = new Card("Card1", 1, null);
        Card card2 = new Card("Card2", 2, null);
        Card card3 = new Card("Card3", 3, null);
        card4 = new Card("Card4", 4, null);
        Card card5 = new Card("Card5", 5, null);
        card6 = new Card("Card6",6,null);
        otherList1 = new ArrayList<>(Arrays.asList(card1, card2, card3, card4));
        otherList2 = new ArrayList<>(Collections.singletonList(card6));
        otherList3 = new ArrayList<>(Arrays.asList(card1, card2, card3,card4, card5));
        collection1 = new Collection(otherList3);
        deck1 = new Deck(otherList1);
    }

    @Test
    void testConstructor() {
        assertEquals(otherList3,collection1.showCollection());
    }

    @Test
    void testAddCard() {
        collection1.addCard(card6);
        otherList3.add(card6);
        assertEquals(otherList3,collection1.showCollection());
    }

    @Test
    void testAddCards() {
        collection1.addCards(otherList2);
        otherList3.addAll(otherList2);
        assertEquals(otherList3,collection1.showCollection());
    }

    @Test
    void testRemoveCard() {
        collection1.removeCard(card4);
        otherList3.remove(card4);
        assertEquals(otherList3, collection1.showCollection());
    }

    @Test
    void testAddToDeck() {
        collection1.addToDeck(card6,deck1);
        otherList1.add(card6);
        assertEquals(otherList1,deck1.getCards());
        otherList3.remove(card4);
        assertEquals(otherList3,collection1.showCollection());
    }

    @Test
    void testGetCardFromID() {
        assertEquals(card4,collection1.getCardfromID(4));
    }
}
