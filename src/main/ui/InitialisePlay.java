package ui;

import model.Card;
import model.Deck;
import model.moves.Move;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Formatter;
import java.util.List;
import java.util.Random;

// Represents Play Frame
public class InitialisePlay extends JFrame {
    private Move selectedMove;
    private Move cpuMove;
    private Card selectedCard;

//  Constructor
    public InitialisePlay(Deck deck, Deck cpudeck, Card cpuCard) {
        selectCard(deck,cpuCard);
    }

//  EFFECTS: Generates Play Panel
    public void startPlay(Card cpuCard) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,2));
        JPanel selectedCardInfo = generateSelectedCardPanel(this.selectedCard);
        JPanel cpuCardInfo = generateCpuCardPanel(cpuCard);
        JPanel movePanel = generateMovePanel(this.selectedCard);
        JPanel logPanel = new JPanel();
        mainPanel.add(selectedCardInfo);
        mainPanel.add(cpuCardInfo);
        mainPanel.add(movePanel);
        mainPanel.add(logPanel);
        add(mainPanel);
        pack();
        setVisible(true);
    }

//  EFFECTS: Generates Select Card Panel
    private void selectCard(Deck deck, Card cpuCard) {
        this.cpuMove = getRandMove(cpuCard);
        JFrame jf =  new JFrame();
        jf.setLayout(new GridLayout(2, 1));
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel label1 = new JLabel("Select Card from Deck");
        JScrollPane listScrollPane = new JScrollPane(generateList(deck,jf,cpuCard));
        jf.add(label1);
        jf.add(listScrollPane);
        jf.pack();
        jf.setVisible(true);
    }

//  EFFECTS: Generates JList of deck
    private JList generateList(Deck deck, JFrame jf, Card cpuCard) {
        DefaultListModel listmodel = new DefaultListModel();
        for (Card c: deck) {
            listmodel.addElement(c.getName());
        }
        JList list = new JList(listmodel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(5);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedCard = deck.getCardfromCardName(list.getSelectedValue());
                startPlay(cpuCard);
                jf.dispose();
            }
        });
        return list;
    }

//  EFFECTS: Generates move panel
    private JPanel generateMovePanel(Card selectedCard) {
        JPanel movePanel = new JPanel();
        movePanel.setLayout(new GridLayout(2,1));
        Formatter fmt = new Formatter();
        fmt.format("%15s %15s %15s \n", "Name", "Damage", "Speed");
        for (Move m : selectedCard.getMoves()) {
            fmt.format("%15s %15s %15s \n", m.getName(), m.getDamage(), m.getSpeed());
        }
        JTextArea ja = new JTextArea(fmt.toString());
        JPanel moves = generateMovesPanel(selectedCard);
        movePanel.add(ja);
        movePanel.add(moves);
        return movePanel;
    }

//  EFFECTS: Generates panel with all moves
    private JPanel generateMovesPanel(Card selectedCard) {
        JPanel moves = new JPanel();
        moves.setLayout(new GridLayout(2,2));
        List<Move> moveset = selectedCard.getMoves();
        JButton move1 = new JButton(moveset.get(0).getName());
        JButton move2 = new JButton(moveset.get(1).getName());
        JButton move3 = new JButton(moveset.get(2).getName());
        JButton move4 = new JButton(moveset.get(3).getName());
        moves.add(move1);
        moves.add(move2);
        moves.add(move3);
        moves.add(move4);
        setMoveMethods(move1, move2,move3,moveset);
        setMoreMoveMethods(move4,moveset);
        return moves;
    }

//  EFFECTS: Adds Functionality to buttons in Move Panel
    private void setMoreMoveMethods(JButton move4, List<Move> moveset) {
        move4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMove = moveset.get(3);
            }
        });
    }

    //  EFFECTS: Adds Functionality to buttons in Move Panel
    private void setMoveMethods(JButton move1, JButton move2, JButton move3, List<Move> moveset) {
        move1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMove = moveset.get(0);
            }
        });
        move2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMove = moveset.get(1);
            }
        });
        move3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMove = moveset.get(2);
            }
        });
    }

//  EFFECTS: Generates CPU Card info Panel
    private JPanel generateCpuCardPanel(Card cpuCard) {
        JPanel cpuCardInfo = new JPanel();
        cpuCardInfo.setLayout(new GridLayout(3,1));
        JLabel label1 = new JLabel("Name: " + cpuCard.getName());
        JLabel label2 = new JLabel("Condition: " + cpuCard.checkCondition());
        JLabel label3 = new JLabel("Health: " + cpuCard.getHealth());
        cpuCardInfo.add(label1);
        cpuCardInfo.add(label2);
        cpuCardInfo.add(label3);
        return cpuCardInfo;
    }

    //  EFFECTS: Generates User Selected Card info Panel
    private JPanel generateSelectedCardPanel(Card selectedCard) {
        JPanel selectedCardInfo = new JPanel();
        selectedCardInfo.setLayout(new GridLayout(3,1));
        JLabel label1 = new JLabel("Name: " + selectedCard.getName());
        JLabel label2 = new JLabel("Condition: " + selectedCard.checkCondition());
        JLabel label3 = new JLabel("Health: " + selectedCard.getHealth());
        selectedCardInfo.add(label1);
        selectedCardInfo.add(label2);
        selectedCardInfo.add(label3);
        return selectedCardInfo;
    }

//  EFFECTS: Determines which card will attack first
    public void handleDamage(Card selectedCard, Move selectedMove, Move cpuMove, Card cpuCard) {
        if (fasterAttack(selectedMove,cpuMove) == selectedMove) {
            damage(selectedCard,
                    selectedMove,
                    cpuMove,
                    cpuCard);
        } else {
            damage(cpuCard,
                    cpuMove,
                    selectedMove,
                    selectedCard);
        }
    }

//  EFFECTS: Determines which move is faster
    public Move fasterAttack(Move move1, Move move2) {
        if (move1.getSpeed() <= move2.getSpeed()) {
            return move1;
        } else {
            return move2;
        }
    }

//  EFFECTS: Generates Random move from moves
    public Move getRandMove(Card card) {
        List<Move> moves = card.getMoves();
        Random rand = new Random();
        Move m = moves.get(rand.nextInt(moves.size()));
        return m;
    }

//  EFFECTS: Handles the damage done to each card
    public void damage(Card faster, Move fasterMove, Move slowerMove, Card slower) {
        System.out.println(faster.getName() + " performed " + fasterMove.getName());
        slower.doDamage(fasterMove);
        System.out.println(slower.getName() + "'s health went down to " + slower.getHealth());
        System.out.println(slower.getName() + " performed " + slowerMove.getName());
        faster.doDamage(slowerMove);
        System.out.println(faster.getName() + "'s health went down to " + faster.getHealth());
    }

}
