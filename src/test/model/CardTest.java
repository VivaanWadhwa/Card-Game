package model;

import model.moves.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CardTest {
    private Card testCard;
    private Move move1;
    private Move move2;

    @BeforeEach
    void runBefore() {
        testCard = new Card("test",59, null);
        move1 = new Move("RKO",5,100);
        move2 = new Move("AA",51,10001);
    }

    @Test
    void testConstructor() {
        assertEquals("test",testCard.getName());
        assertEquals(59,testCard.getCardID());
        assertEquals(100,testCard.getHealth());
        assertNull(testCard.getMoves());
    }

    @Test
    void testDoDamageAndAlive() {
        testCard.doDamage(move1);
        assertEquals(95,testCard.getHealth());
        assertEquals("Alive",testCard.checkCondition());
    }

    @Test
    void testDoDamageAndDamaged() {
        testCard.doDamage(move2);
        assertEquals(49,testCard.getHealth());
        assertEquals("Damaged",testCard.checkCondition());
    }

    @Test
    void testDoDamageAndFainted() {
        testCard.doDamage(move2);
        testCard.doDamage(move2);
        assertEquals(0, testCard.getHealth());
        assertEquals("Fainted",testCard.checkCondition());
    }
}