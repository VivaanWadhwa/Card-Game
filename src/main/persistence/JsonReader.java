package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import model.moves.Move;
import org.json.*;

//Code used in this method has been taken from JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads HasCards from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads HasCards from file and returns it;
    // throws IOException if an error occurs reading data from file
    public HasCards read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHasCards(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

//  EFFECTS: Parses HasCards from JSON Object and returns it
    private HasCards parseHasCards(JSONObject jsonObject) {
        JSONObject jcol = jsonObject.getJSONObject("Collection");
        Collection col = parseCollection(jcol);
        JSONObject jdeck = jsonObject.getJSONObject("Deck");
        Deck deck = parseDeck(jdeck);
        JSONObject jshop = jsonObject.getJSONObject("Shop");
        Shop shop = parseShop(jshop);
        JSONObject jwallet = jsonObject.getJSONObject("Wallet");
        Wallet wallet = parseWallet(jwallet);
        HasCards hs = new HasCards(col, deck, shop, wallet);
        return hs;
    }

    //  EFFECTS: Parses Wallet from JSON Object and returns it
    private Wallet parseWallet(JSONObject jwallet) {
        int balance = jwallet.getInt("Balance");
        int deductBalance = 1000 - balance;
        Wallet w = new Wallet();
        w.deductBalance(deductBalance);
        return w;
    }


    // EFFECTS: parses Collection from JSON object and returns it
    private Collection parseCollection(JSONObject jsonObject) {
        List<Card> collection = new ArrayList<>();
        Collection cl = new Collection(collection);
        addCardsToCol(cl, jsonObject);
        return cl;
    }

//  EFFECTS: Parses Deck from JSON Object and returns it
    private Deck parseDeck(JSONObject jsonObject) {
        List<Card> deck = new ArrayList<>();
        Deck d = new Deck(deck);
        addCardsToDeck(d, jsonObject);
        return d;
    }

//  EFFECTS: Parses Shop from JSON Object and returns it
    private Shop parseShop(JSONObject jsonObject) {
        Shop s = addItemsToShop(jsonObject);
        return s;
    }

//  MODIFIES: Shop
//  EFFECTS: Parses Items from JSON object and adds them to make a shop
    private Shop addItemsToShop(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Cards");
        List<Card> keys = new ArrayList<>();
        for (Object json: jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            Card c = returnCard(nextCard);
            keys.add(c);
        }
        JSONArray jsonArray1 = jsonObject.getJSONArray("Costs");
        List<Integer> values = new ArrayList<>();
        for (Object json : jsonArray1) {
            int nextInt = (int) json;
            values.add(nextInt);
        }
        Hashtable<Card,Integer> map = new Hashtable<>();
        Iterator<Card> i1 = keys.iterator();
        Iterator<Integer> i2 = values.iterator();
        while (i1.hasNext() && i2.hasNext()) {
            map.put(i1.next(), i2.next());
        }
        Shop s = new Shop(map);
        return s;
    }

//  EFFECTS: Returns Card from jsonObject
    private Card returnCard(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int cardID = jsonObject.getInt("CardID");
        JSONArray jsonMoves = jsonObject.getJSONArray("Moves");
        ArrayList<Move> moves = new ArrayList<>();
        for (Object json: jsonMoves) {
            JSONObject nextMove = (JSONObject) json;
            addMove(moves,nextMove);
        }
        Card c = new Card(name, cardID,moves);
        return c;
    }

    // MODIFIES: Collection
    // EFFECTS: parses Cards from JSON object and adds them to Collection
    private void addCardsToCol(Collection col, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Collection");
        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addCardToCol(col, nextCard);
        }
    }

    // MODIFIES: Deck
    // EFFECTS: parses Cards from JSON object and adds them to Deck
    private void addCardsToDeck(Deck col, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Deck");
        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addCardToDeck(col, nextCard);
        }
    }

    // MODIFIES: Collection
    // EFFECTS: parses a Card from JSON object and adds them to Collection
    private void addCardToCol(Collection col, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int cardID = jsonObject.getInt("CardID");
        JSONArray jsonMoves = jsonObject.getJSONArray("Moves");
        ArrayList<Move> moves = new ArrayList<>();
        for (Object json: jsonMoves) {
            JSONObject nextMove = (JSONObject) json;
            addMove(moves,nextMove);
        }
        Card c = new Card(name, cardID,moves);
        col.addCard(c);
    }

    // MODIFIES: Collection
    // EFFECTS: parses a Card from JSON object and adds them to Deck
    private void addCardToDeck(Deck col, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int cardID = jsonObject.getInt("CardID");
        JSONArray jsonMoves = jsonObject.getJSONArray("Moves");
        ArrayList<Move> moves = new ArrayList<>();
        for (Object json: jsonMoves) {
            JSONObject nextMove = (JSONObject) json;
            addMove(moves,nextMove);
        }
        Card c = new Card(name, cardID,moves);
        col.addCard(c);
    }

    // MODIFIES: moves
    // EFFECTS: parses a move from JSON object and adds them to moves
    private void addMove(List<Move> moves, JSONObject nextMove) {
        String name = nextMove.getString("Name");
        int damage = nextMove.getInt("Damage");
        int speed = nextMove.getInt("Speed");
        Move m = new Move(name,damage,speed);
        moves.add(m);
    }
}
