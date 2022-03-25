package ui;

import model.Card;
import model.Deck;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class InitialiseSelectCard extends JFrame {
    private Card selectedCard;

//  EFFECTS
    public InitialiseSelectCard(Deck d) {
        setLayout(new GridLayout(2, 1));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel label1 = new JLabel("Select Card from Deck");
        JScrollPane listScrollPane = new JScrollPane(generateList(d,this));
        add(label1);
        add(listScrollPane);
        pack();
        setVisible(true);
    }

    private JList generateList(Deck deck, JFrame jf) {
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
                jf.dispose();
            }
        });
        return list;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }
}
