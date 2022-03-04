package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            HasCards wr = new HasCards(new Collection(new ArrayList<>()), new Deck(new ArrayList<>()), new Shop(new Hashtable<>()), new Wallet());
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            HasCards hs = new HasCards(new Collection(new ArrayList<>()), new Deck(new ArrayList<>()), new Shop(new Hashtable<>()), new Wallet());
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(hs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            HasCards hs1 = reader.read();
            assertEquals(hs1.getCollection().showCollection().size(),hs.getCollection().showCollection().size());
            assertEquals(hs1.getDeck().getCards().size(),hs.getDeck().getCards().size());
            assertEquals(hs1.getShop().getKeys().size(),hs.getShop().getKeys().size());
            assertEquals(hs1.getWallet().getBalance(),hs.getWallet().getBalance());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            HasCards hs = new HasCards(new Collection(new ArrayList<>()), new Deck(new ArrayList<>()),new Shop(new Hashtable<>()), new Wallet());
            hs.getCollection().addCard(new Card("A",1,new ArrayList<>()));
            hs.getDeck().addCard(new Card("A",1,new ArrayList<>()));
            hs.getShop().addItem(new Card("A",1,new ArrayList<>()),110);
            hs.getWallet().deductBalance(100);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(hs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            HasCards hs1 = reader.read();
            checkHasCards(hs1, hs.getCollection(), hs.getDeck(), hs.getShop(), hs.getWallet());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}