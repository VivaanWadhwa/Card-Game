package ui;

import model.*;
import model.Collection;
import model.Event;
import model.moves.Move;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
import static javax.swing.GroupLayout.Alignment.*;

public class CardGameGUI extends JFrame {
    private static final String JSON_STORE = "./data/HasCards.json";
    private final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private final JsonReader jsonReader = new JsonReader(JSON_STORE);
    private final JLabel startLabel = new JLabel("WELCOME TO THE CARDGAME");
    private final JButton playButton = new JButton(new ImageIcon("./images/Play.png"));
    private final JButton deckButton = new JButton(new ImageIcon("./images/Deck.png"));
    private final JButton colButton = new JButton(new ImageIcon("./images/Collection.png"));
    private final JButton shopButton = new JButton(new ImageIcon("./images/Shop.png"));
    private final JButton loadButton = new JButton(new ImageIcon("./images/Load.png"));
    private final JButton saveButton = new JButton(new ImageIcon("./images/Save.png"));
    protected Collection col;
    protected Deck deck;
    protected Deck cpudeck;
    protected Shop shop;
    protected Wallet wallet;
    protected HasCards hasCards;
    private HashMap<Card, String> images;

    public CardGameGUI() {
        runGame();
    }

    public void runGame() {
        init();
        initMoves();
        game();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                for (Event el : EventLog.getInstance()) {
                    System.out.println(el.toString());
                }
            }
        });
    }

    private void init() {
        Hashtable<Card, Integer> inventory = new Hashtable<>();
        this.shop = new Shop(inventory);
        this.wallet = new Wallet();
    }

    public void initMoves() {
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
        ArrayList<Move> moveSet1 = new ArrayList<>(Arrays.asList(fireCharge, quickClaw, push, kick));
        ArrayList<Move> moveSet2 = new ArrayList<>(Arrays.asList(waterGun, quickPunch, push, kick));
        ArrayList<Move> moveSet3 = new ArrayList<>(Arrays.asList(razorLeaf, quickClaw, push, kick));
        ArrayList<Move> moveSet4 = new ArrayList<>(Arrays.asList(electricBolt, quickPunch, push, kick));
        ArrayList<Move> moveSet5 = new ArrayList<>(Arrays.asList(flameThrower, quickClaw, push, kick));
        ArrayList<Move> moveSet6 = new ArrayList<>(Arrays.asList(hydroCanon, quickClaw, push, kick));
        ArrayList<Move> moveSet7 = new ArrayList<>(Arrays.asList(leafWhip, quickPunch, push, kick));
        ArrayList<Move> moveSet8 = new ArrayList<>(Arrays.asList(karin, quickClaw, push, kick));
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
        List<Card> otherList1 = new ArrayList<>(Arrays.asList(card1,card2,card7));
        List<Card> otherList2 = new ArrayList<>(Arrays.asList(card5, card6, card3, card4));
        List<Card> otherList3 = new ArrayList<>(Arrays.asList(card1,card2,card7,card8,card9));
        col = new Collection(otherList1);
        this.deck =  new Deck(otherList2);
        this.shop.addItem(card8,100);
        this.shop.addItem(card9,100);
        this.shop.addItem(card10,110);
        this.cpudeck = new Deck(otherList3);
        images = new HashMap<>();
        images.put(card1,"./images/Card1.png");
        images.put(card2,"./images/Card2.png");
        images.put(card3,"./images/Card3.png");
        images.put(card4,"./images/Card4.png");
        images.put(card5,"./images/Card5.png");
        images.put(card6,"./images/Card6.png");
        images.put(card7,"./images/Card7.png");
        images.put(card8,"./images/Card8.png");
        images.put(card9,"./images/Card9.png");
        images.put(card10,"./images/Card10.png");
    }

    private void game() {
        displayMenu();
    }

    private void displayMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPanel = this.getContentPane();
        GroupLayout p1 = new GroupLayout(contentPanel);
        contentPanel.setLayout(p1);
        p1.setAutoCreateGaps(true);
        p1.setAutoCreateContainerGaps(true);

        p1.setHorizontalGroup(
                p1.createSequentialGroup()
                        .addGroup(p1.createParallelGroup(CENTER).addComponent(startLabel)
                                .addGroup(p1.createParallelGroup(LEADING).addComponent(playButton)
                                        .addComponent(deckButton)
                                        .addComponent(colButton))
                                .addGroup(p1.createParallelGroup(TRAILING).addComponent(shopButton)
                                        .addComponent(loadButton)
                                        .addComponent(saveButton)))
        );

        p1.linkSize(SwingConstants.HORIZONTAL, playButton, deckButton, colButton, shopButton, loadButton, saveButton);

        p1.setVerticalGroup(
                p1.createSequentialGroup()
                        .addComponent(startLabel)
                        .addGroup(p1.createSequentialGroup().addComponent(playButton)
                                .addComponent(shopButton))
                        .addGroup(p1.createSequentialGroup().addComponent(deckButton)
                                .addComponent(colButton))
                        .addGroup(p1.createSequentialGroup().addComponent(loadButton)
                                .addComponent(saveButton))
        );

        setButtonActions();
        pack();
        setVisible(true);
    }

    private void setButtonActions() {
        playButton.addActionListener(e -> startPlay());
        deckButton.addActionListener(e -> deck());
        shopButton.addActionListener(e -> shop());
        loadButton.addActionListener(e -> loadHasCards());
        saveButton.addActionListener(e -> saveHasCards());
        colButton.addActionListener(e -> collection());
    }

    private void startPlay() {
        Card cpuCard = cpudeck.randomCard();
        new GamePlayPanel(deck, cpudeck, cpuCard);
        resetHealth(deck);
        resetHealth(cpudeck);
    }

    private void resetHealth(Deck deck) {
        for (Card c : deck) {
            c.regenHealth();
        }
    }

    public void saveHasCards() {
        try {
            hasCards = new HasCards(col, deck, shop, wallet);
            jsonWriter.open();
            jsonWriter.write(hasCards);
            jsonWriter.close();
            showMessage("Saved Data", "Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            showMessage("ERROR", "Unable to write to file: " + JSON_STORE);
        }
    }

    public void loadHasCards() {
        try {
            hasCards = jsonReader.read();
            col = hasCards.getCollection();
            deck = hasCards.getDeck();
            shop = hasCards.getShop();
            wallet = hasCards.getWallet();
            showMessage("Loaded Data", "Loaded data from " + JSON_STORE);
        } catch (IOException | JSONException e) {
            showMessage("ERROR", "Unable to load file: " + JSON_STORE);
        }
    }

    private void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void shop() {
        new InitialiseShop(shop, wallet, col);
    }

    private void deck() {
        new InitialiseDeck(deck, col);
    }

    private void collection() {
        new InitialiseCollection(col, images);
    }
}

