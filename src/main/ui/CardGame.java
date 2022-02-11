package ui;

import model.Card;
import model.Collection;
import model.Deck;
import model.Shop;
import model.moves.Move;

import java.util.*;

public class CardGame {
    private Scanner input;
    private Collection col;
    private Deck deck;
    private Boolean keepGoing;
    private Shop shop;

    //   Moves
    //  Major Attacks
    private Move fireCharge = new Move(12,5);
    private Move waterGun = new Move(11,4);
    private Move razorLeaf = new Move(12,5);
    private Move electricBolt = new Move(11,4);
    private Move flameThrower = new Move(11,4);
    private Move hydroCanon = new Move(12,5);
    private Move leafWhip = new Move(11,4);
    private Move karin = new Move(12,5);
    //  Common Attacks
    private Move quickClaw = new Move(5,2);
    private Move quickPunch = new Move(4,1);
    private Move push = new Move(5,2);
    private Move kick = new Move(4,1);

    public CardGame() {
        runGame();
    }

    public void runGame() {
        this.keepGoing = true;
        String command = null;

        init();
        initMoves();

        while (this.keepGoing) {
            game(command);
        }

        System.out.println("\nGoodbye!");
    }

    public void game(String command) {
        displayMenu();
        command = input.next();
        command = command.toLowerCase();

        if (command.equals("q")) {
            this.keepGoing = false;
        } else {
            processCommand(command);
        }
    }

    public void init() {
        input = new Scanner(System.in);
        this.col = new Collection();
        Hashtable<Card, Integer> inventory = new Hashtable<>();
        this.shop = new Shop(inventory);
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
        List<Card> otherList1 = Arrays.asList(card1,card2,card7);
        List<Card> otherList2 = new ArrayList<>(Arrays.asList(card5, card6, card3, card4));
        col.addCards(otherList1);
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
    }

    private void play() {}

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
                command = null;
                game(command);
                break;
            default:
                System.out.println("Selection not Valid...");
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
        System.out.println("\t b -> go back");
        String command = input.next();
        if (command.equals("b")) {
            command = null;
            game(command);
        } else {
            System.out.println("Selection not Valid...");
        }
    }



}
