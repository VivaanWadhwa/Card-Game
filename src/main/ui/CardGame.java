package ui;

import model.*;
import model.Collection;
import model.moves.Move;

import java.util.*;

public class CardGame {
    private Scanner input;
    private Collection col;
    private Deck deck;
    private Boolean keepGoing;
    private Shop shop;
    private Wallet wallet;

    //   Moves
    //  Major Attacks
    private final Move fireCharge = new Move("fireCharge",12,5);
    private final Move waterGun = new Move("waterGun",11,4);
    private final Move razorLeaf = new Move("razorLeaf",12,5);
    private final Move electricBolt = new Move("electricBolt",11,4);
    private final Move flameThrower = new Move("flameThrower",11,4);
    private final Move hydroCanon = new Move("hydroCanon",12,5);
    private final Move leafWhip = new Move("leafWhip",11,4);
    private final Move karin = new Move("karin",12,5);
    //  Common Attacks
    private final Move quickClaw = new Move("quickClaw",5,2);
    private final Move quickPunch = new Move("quickPunch",4,1);
    private final Move push = new Move("push",5,2);
    private final Move kick = new Move("kick",4,1);

    public CardGame() {
        runGame();
    }

    public void runGame() {
        this.keepGoing = true;

        init();
        initMoves();

        while (this.keepGoing) {
            game();
        }

        System.out.println("\nGoodbye!");
    }

    public void game() {
        displayMenu();
        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("q")) {
            this.keepGoing = false;
        } else {
            processCommand(command);
        }
    }

    public void init() {
        input = new Scanner(System.in);
        Hashtable<Card, Integer> inventory = new Hashtable<>();
        this.shop = new Shop(inventory);
        this.wallet = new Wallet();
    }

    public void initMoves() {
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
        List<Card> otherList1 = new ArrayList<>(Arrays.asList(card1,card2,card7));
        List<Card> otherList2 = new ArrayList<>(Arrays.asList(card5, card6, card3, card4));
        col = new Collection(otherList1);
        this.deck =  new Deck(otherList2);
        this.shop.addItem(card8,100);
    }

    private void displayMenu() {
        System.out.println("\nWelcome to the ANIME CARDGAME");
        System.out.println("\t p -> Play");
        System.out.println("\t d -> Modify deck");
        System.out.println("\t c -> See collection");
        System.out.println("\t s -> Shop");
        System.out.println("\t q -> Quit");
    }

    private void processCommand(String command) {
        switch (command) {
            case "p":
                play();
                break;
            case "d":
                deck();
                break;
            case "c":
                collection();
                break;
            case "s":
                shop();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    private void shop() {
        Set<Card> inv = this.shop.getKeys();
        System.out.println("Name\tcardID\tCost");
        for (Card card: inv) {
            System.out.println(card.getName() + "\t" + card.getCardID() + "\t" + this.shop.getValue(card));
        }
        System.out.print("Balance:");
        System.out.println(this.wallet.getBalance());
        System.out.println("Enter Card Number to buy");
        System.out.println("b -> Go back");
        String command = input.next();
        processShopCommand(command);
    }

    private void processShopCommand(String command) {
        if (isInteger(command)) {
            int id = Integer.parseInt(command);
            this.shop.buyItem(this.shop.getCardFromID(id),this.wallet,this.col);
        } else if (command.equals("b")) {
            game();
        } else {
            System.out.println("Selection is not Valid...");
            shop();
        }
    }


    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private void play() {
//    TODO!!!!!
    }

    private void deck() {
        System.out.println("Welcome to DECK");
        System.out.println("This is your DECK");
        for (Card card : this.deck) {
            System.out.println(card.getName());
        }
        System.out.println("\ta -> add card to deck");
        System.out.println("\tr -> remover card from deck");
        System.out.println("\tb -> go back");
        String command = input.next();
        command = command.toLowerCase();
        processDeckCommand(command);

    }

    private void processDeckCommand(String command) {
        switch (command) {
            case "a":
                addCard();
                break;
            case "r":
                removeCard();
                break;
            case "b":
                game();
                break;
            default:
                System.out.println("Selection not Valid...");
                deck();
        }
    }

    private void addCard() {
        System.out.println("Enter CardID from below");
        System.out.print("Card \t CardID\n");
        for (Card card : this.col) {
            System.out.print(card.getName() + "\t");
            System.out.println(card.getCardID());
        }
        int command = Integer.parseInt(input.next());
        Card c = this.col.getCardfromID(command);
        this.deck.addCard(c);
        this.col.removeCard(c);
        deck();
    }

    private void removeCard() {
        System.out.println("Enter CardID from below");
        System.out.print("Card \t CardID\n");
        for (Card card : this.deck) {
            System.out.print(card.getName() + "\t");
            System.out.println(card.getCardID());
        }
        int command = Integer.parseInt(input.next());
        this.col.addCard(this.deck.getCardfromID(command));
        this.deck.removeCard(this.deck.getCardfromID(command));
        deck();
    }

    private void collection() {
        System.out.println("Welcome to COLLECTION");
        System.out.println("This is your COLLECTION");
        System.out.print("Card \t CardID\n");
        for (Card card : this.col) {
            System.out.print(card.getName() + "\t");
            System.out.println(card.getCardID());
        }
        System.out.println("Select Card Number to see Card Details");
        System.out.println("\t b -> go back");
        String command = input.next();
        processCollectionCommand(command);
    }

    private void processCollectionCommand(String command) {
        if (command.equals("b")) {
            game();
        } else if (isInteger(command)) {
            showDetails(this.col.getCardfromID(Integer.parseInt(command)));
            System.out.println("b -> go back");
            String commandNew = input.next();
            if (commandNew.equals("b")) {
                collection();
            } else {
                System.out.println("Selection not Valid...");
                game();
            }
        } else {
            System.out.println("Selection not Valid...");
            collection();
        }
    }

    private void showDetails(Card card) {
        System.out.print("Name:");
        System.out.println(card.getName());
        System.out.print("ID");
        System.out.println(card.getName());
        System.out.print("Health:");
        System.out.println(card.getHealth());
        System.out.println("Moves:");
        List<Move> moves = card.getMoves();
        System.out.println("Name\tdDamage\tSpeed");
        for (Move move : moves) {
            System.out.print(move.getName() + "\t");
            System.out.print(move.getDamage() + "\t");
            System.out.println(move.getSpeed());
        }
    }


}
