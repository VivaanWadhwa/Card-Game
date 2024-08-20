// New GamePlayPanel Class

package ui;

import model.Card;
import model.Deck;
import model.moves.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Random;

public class GamePlayPanel extends JPanel {
    private Move selectedMove;
    private Move cpuMove;
    private Card selectedCard;

    public GamePlayPanel(Deck deck, Deck cpudeck, Card cpuCard) {
        this.cpuMove = getRandMove(cpuCard);
        selectCard(deck, cpuCard);
    }

    private void selectCard(Deck deck, Card cpuCard) {
        JFrame selectCardFrame = new JFrame("Select Card");
        selectCardFrame.setLayout(new BorderLayout());
        JList<String> cardList = createCardList(deck, selectCardFrame, cpuCard);
        selectCardFrame.add(new JLabel("Select a card from your deck:"), BorderLayout.NORTH);
        selectCardFrame.add(new JScrollPane(cardList), BorderLayout.CENTER);
        selectCardFrame.pack();
        selectCardFrame.setVisible(true);
    }

    private JList<String> createCardList(Deck deck, JFrame frame, Card cpuCard) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Card c : deck) {
            listModel.addElement(c.getName());
        }
        JList<String> cardList = new JList<>(listModel);
        cardList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cardList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedCard = deck.getCardfromCardName(cardList.getSelectedValue());
                frame.dispose();
                startPlay(cpuCard);
            }
        });
        return cardList;
    }

    private void startPlay(Card cpuCard) {
        setLayout(new GridLayout(2, 2));
        add(generateCardInfoPanel("Your Card", selectedCard));
        add(generateCardInfoPanel("CPU Card", cpuCard));
        add(generateMovePanel(selectedCard));
        add(new JPanel());  // Placeholder for future log panel
        revalidate();
        repaint();
    }

    private JPanel generateCardInfoPanel(String title, Card card) {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        System.out.println(card);
        panel.add(new JLabel("Name: " + card.getName()));
        panel.add(new JLabel("Condition: " + card.checkCondition()));
        panel.add(new JLabel("Health: " + card.getHealth()));
        return panel;
    }

    private JPanel generateMovePanel(Card card) {
        JPanel movePanel = new JPanel(new GridLayout(2, 1));
        JTextArea moveInfo = new JTextArea();
        moveInfo.append(String.format("%15s %15s %15s", "Move Name", "Move Speed", "Move Damage\n"));
        List<Move> moves = card.getMoves();
        for (Move m : moves) {
        moveInfo.append(String.format("%15s %15s %15s", m.getName(), m.getSpeed(), m.getDamage()));
        moveInfo.append("\n");
        }
        movePanel.add(new JScrollPane(moveInfo));
        movePanel.add(createMoveButtonPanel(card));
        return movePanel;
        }

        private JPanel createMoveButtonPanel(Card card) {
            JPanel buttonPanel = new JPanel(new GridLayout(1, card.getMoves().size()));
            for (Move move : card.getMoves()) {
                JButton moveButton = new JButton(move.getName());
                moveButton.addActionListener((ActionEvent e) -> {
                    selectedMove = move;
                    executeMove();
                });
                buttonPanel.add(moveButton);
            }
            return buttonPanel;
        }
        
        private void executeMove() {
            // Logic for move execution
            // e.g., calculating damage, updating card conditions, etc.
        }
        
        private Move getRandMove(Card card) {
            Random rand = new Random();
            return card.getMoves().get(rand.nextInt(card.getMoves().size()));
        }
    }
        
