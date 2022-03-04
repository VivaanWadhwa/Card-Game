package persistence;

import model.*;
import model.moves.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @BeforeEach
    void runBefore() {
            //   Moves
            //  Major Attacks
            final Move fireCharge = new Move("fireCharge",12,5);
            final Move waterGun = new Move("waterGun",11,4);
            final Move razorLeaf = new Move("razorLeaf",12,5);
            final Move electricBolt = new Move("electricBolt",11,4);
            final Move flameThrower = new Move("flameThrower",11,4);
            final Move hydroCanon = new Move("hydroCanon",12,5);
            final Move leafWhip = new Move("leafWhip",11,4);
            final Move karin = new Move("karin",12,5);
            //  Common Attacks
            final Move quickClaw = new Move("quickClaw",5,2);
            final Move quickPunch = new Move("quickPunch",4,1);
            final Move push = new Move("push",5,2);
            final Move kick = new Move("kick",4,1);
            List<Move> moveSet1 = new ArrayList<>(Arrays.asList(fireCharge, quickClaw, push, kick));
            List<Move> moveSet2 = new ArrayList<>(Arrays.asList(waterGun, quickPunch, push, kick));
            List<Move> moveSet3 = new ArrayList<>(Arrays.asList(razorLeaf, quickClaw, push, kick));
            List<Move> moveSet4 = new ArrayList<>(Arrays.asList(electricBolt, quickPunch, push, kick));
            List<Move> moveSet5 = new ArrayList<>(Arrays.asList(flameThrower, quickClaw, push, kick));
            List<Move> moveSet6 = new ArrayList<>(Arrays.asList(hydroCanon, quickClaw, push, kick));
            List<Move> moveSet7 = new ArrayList<>(Arrays.asList(leafWhip, quickPunch, push, kick));
            List<Move> moveSet8 = new ArrayList<>(Arrays.asList(karin, quickClaw, push, kick));
            Card card1 = new Card("Card1", 1, moveSet1);
            Card card2 = new Card("Card2", 2, moveSet2);
            Card card3 = new Card("Card3", 3, moveSet3);
            Card card4 = new Card("Card4", 4, moveSet4);
            Card card5 = new Card("Card5", 5, moveSet5);
            Card card6 = new Card("Card6", 6, moveSet6);
            Card card7 = new Card("Card7", 7, moveSet7);
            Card card8 = new Card("Card8", 8, moveSet8);
            Card card9 = new Card("Card9",9,moveSet8);
            Card card10 = new Card("Card10",10,moveSet3);
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            HasCards hs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHasCards() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            HasCards hs = reader.read();
            checkHasCards(hs,new Collection(new ArrayList<>()), new Deck(new ArrayList<>()), new Shop(new Hashtable<>()), new Wallet());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            HasCards hs = reader.read();

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}