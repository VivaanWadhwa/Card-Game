package ui;

import model.Card;
import model.Deck;
import model.moves.Move;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Random;

// Represents Play Frame
public class InitialisePlay extends JFrame {
    private Move selectedMove;
    private Move cpuMove;
    private Card selectedCard;

    // Constructor
    public InitialisePlay(Deck deck, Deck cpudeck, Card cpuCard) {
        this.cpuMove = getRandMove(cpuCard);
        selectCard(deck, cpuCard);
    }

    // EFFECTS: Generates Play Panel
    private void startPlay(Card cpuCard) {
        JPanel mainPanel = new JPanel(new GridLayout(2, 2));
        mainPanel.add(generateCardPanel("Selected Card", selectedCard));
        mainPanel.add(generateCardPanel("CPU Card", cpuCard));
        mainPanel.add(generateMovePanel());
        mainPanel.add(new JPanel());
        add(mainPanel);
        pack();
        setVisible(true);
    }

    // EFFECTS: Generates Select Card Panel
    private void selectCard(Deck deck, Card cpuCard) {
        JFrame selectionFrame = new JFrame("Select Card");
        selectionFrame.setLayout(new GridLayout(2, 1));
        selectionFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Select a Card from Deck");
        JScrollPane listScrollPane = new JScrollPane(generateCardList(deck, selectionFrame, cpuCard));
        selectionFrame.add(label);
        selectionFrame.add(listScrollPane);
        selectionFrame.pack();
        selectionFrame.setVisible(true);
    }

    // EFFECTS: Generates JList of deck
    private JList<String> generateCardList(Deck deck, JFrame selectionFrame, Card cpuCard) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        deck.forEach(card -> listModel.addElement(card.getName()));
        JList<String> cardList = new JList<>(listModel);
        cardList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cardList.setVisibleRowCount(5);

        cardList.addListSelectionListener((ListSelectionEvent e) -> {
            selectedCard = deck.getCardfromCardName(cardList.getSelectedValue());
            selectionFrame.dispose();
            startPlay(cpuCard);
        });

        return cardList;
    }

    // EFFECTS: Generates move panel
    private JPanel generateMovePanel() {
        JPanel movePanel = new JPanel(new GridLayout(2, 1));
        JTextArea moveInfoArea = new JTextArea(formatMoveInfo(selectedCard.getMoves()));
        movePanel.add(moveInfoArea);
        movePanel.add(generateMovesButtonPanel());
        return movePanel;
    }

    // EFFECTS: Formats move information
    private String formatMoveInfo(List<Move> moves) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%15s %15s %15s \n", "Name", "Damage", "Speed"));
        moves.forEach(move -> sb.append(String.format("%15s %15s %15s \n", move.getName(), move.getDamage(), move.getSpeed())));
        return sb.toString();
    }

    // EFFECTS: Generates panel with move buttons
    private JPanel generateMovesButtonPanel() {
        JPanel movesPanel = new JPanel(new GridLayout(2, 2));
        List<Move> moves = selectedCard.getMoves();
        for (int i = 0; i < moves.size(); i++) {
            JButton moveButton = new JButton(moves.get(i).getName());
            int moveIndex = i; // Capture index for lambda expression
            moveButton.addActionListener((ActionEvent e) -> selectedMove = moves.get(moveIndex));
            movesPanel.add(moveButton);
        }
        return movesPanel;
    }

    // EFFECTS: Generates card info panel
    private JPanel generateCardPanel(String title, Card card) {
        JPanel cardPanel = new JPanel(new GridLayout(3, 1));
        cardPanel.setBorder(BorderFactory.createTitledBorder(title));
        cardPanel.add(new JLabel("Name: " + card.getName()));
        cardPanel.add(new JLabel("Condition: " + card.checkCondition()));
        cardPanel.add(new JLabel("Health: " + card.getHealth()));
        return cardPanel;
    }

    // EFFECTS: Determines which card will attack first
    public void handleDamage(Card selectedCard, Move selectedMove, Move cpuMove, Card cpuCard) {
        if (fasterAttack(selectedMove, cpuMove)) {
            applyDamage(selectedCard, selectedMove, cpuMove, cpuCard);
        } else {
            applyDamage(cpuCard, cpuMove, selectedMove, selectedCard);
        }
    }

    // EFFECTS: Determines if the first move is faster than the second
    private boolean fasterAttack(Move move1, Move move2) {
        return move1.getSpeed() <= move2.getSpeed();
    }

    // EFFECTS: Generates Random move from moves
    private Move getRandMove(Card card) {
        List<Move> moves = card.getMoves();
        Random rand = new Random();
        return moves.get(rand.nextInt(moves.size()));
    }

    // EFFECTS: Handles the damage done to each card
    private void applyDamage(Card faster, Move fasterMove, Move slowerMove, Card slower) {
        System.out.println(faster.getName() + " performed " + fasterMove.getName());
        slower.doDamage(fasterMove);
        System.out.println(slower.getName() + "'s health went down to " + slower.getHealth());
        System.out.println(slower.getName() + " performed " + slowerMove.getName());
        faster.doDamage(slowerMove);
        System.out.println(faster.getName() + "'s health went down to " + faster.getHealth());
    }
}
