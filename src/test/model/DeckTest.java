package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DeckTest {
    private Deck deck1;
    private Deck deck2;
    private Deck deck3;
    private Deck deck4;
    private List<Card> otherList1;
    private List<Card> otherList3;
    private List<Card> otherList4;
    private Card card1;
    private Card card2;
    private Card card4;


    @BeforeEach
    void runBefore() {
        card1 = new Card("Card1", 1, null);
        card2 = new Card("Card2", 2, null);
        Card card3 = new Card("Card3", 3, null);
        card4 = new Card("Card4", 4, null);
        Card card5 = new Card("Card5", 5, null);
        Card card6 = new Card("Card6", 6, null);
        otherList1 = new ArrayList<>(Arrays.asList(card1,card2, card3));
        List<Card> otherList2 = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5, card6));
        otherList3 = new ArrayList<>(Arrays.asList(card1,card2, card3,card4, card5));
        otherList4 = new ArrayList<>(Arrays.asList(card1,card2));
        deck1 = new Deck(otherList1);
        deck2 = new Deck(otherList2);
        deck3 = new Deck(otherList3);
        deck4 = new Deck(new ArrayList<>());
    }

    @Test
    void testConstructor() {
        assertEquals(otherList1,deck1.getCards());
        assertNull(deck2.getCards());
    }

    @Test
    void testAddCardBaseCase() {
        deck1.addCard(card4);
        otherList1.add(card4);
        assertEquals(otherList1,deck1.getCards());
    }

    @Test
    void testAddCardMoreThanMax() {
        deck3.addCard(card2);
        assertEquals(otherList3,deck3.getCards());
    }

    @Test
    void testAddCardsBaseCase() {
        deck1.addCards(otherList4);
        otherList1.addAll(otherList4);
        assertEquals(otherList1,deck1.getCards());
    }

    @Test
    void testAddCardsMoreThanMax() {
        deck3.addCards(otherList3);
        assertEquals(otherList3,deck3.getCards());
    }

    @Test
    void testGetCardFromID() {
        assertEquals(card1,deck1.getCardFromID(1));
        assertNull(deck1.getCardFromID(34));
    }

    @Test
    void testRemoveCard() {
        deck1.removeCard(card2);
        otherList1.remove(card2);
        assertEquals(otherList1,deck1.getCards());
    }

    @Test
    void testRemoveCardSize0() {
        deck4.removeCard(card2);
        assertEquals(new ArrayList<>(),deck4.getCards());
    }
}
