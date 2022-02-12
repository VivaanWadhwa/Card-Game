package model;

import model.moves.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveTest {
    private Move move;

    @BeforeEach
    void runBefore() {
        move = new Move("Move1",100,22);
    }

    @Test
    void testConstructor() {
        assertEquals("Move1",move.getName());
        assertEquals(100,move.getDamage());
        assertEquals(22,move.getSpeed());
    }
}
