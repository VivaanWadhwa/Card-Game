package ui;

import model.Card;
import model.Collection;
import model.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents Deck JFrame
public class InitialiseDeck extends JFrame {

    //  MODIFIES: this
//  EFFECTS: Initialises Deck Frame
    public InitialiseDeck(Deck deck, Collection col) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout p1 = new GridLayout(3, 1);
        this.setLayout(p1);
        DefaultListModel listmodel = new DefaultListModel();
        for (Card c: deck) {
            listmodel.addElement(c.getName());
        }
        JList list = new JList(listmodel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        this.add(listScrollPane);
        addButtons(this, deck, col);
        pack();
        setVisible(true);
    }

//  EFFECTS: Adds Button to Jframe
    private void addButtons(JFrame jf,Deck d, Collection col) {
        JButton addCard = new JButton("Add Card");
        this.add(addCard);
        addCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InitialiseAddCard(d,col);
                jf.dispose();
            }
        });
        JButton removeCard = new JButton("Remove Card");
        removeCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (d.getSize() == 0) {
                    JFrame temp = new JFrame("Caution");
                    temp.setLayout(new GridLayout(2,1));
                    JLabel j = new JLabel("Cannot Remove Card! No Card in Deck.");
                    JButton b = new JButton("Ok");
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            temp.dispose();
                        }
                    });
                    temp.add(j);
                    temp.add(b);
                    temp.pack();
                    temp.setVisible(true);
                } else {
                    new InitialiseRemoveCard(d,col);
                    jf.dispose();
                }
            }
        });
        this.add(removeCard);
        this.add(goBack(this));
    }

//  EFFECTS: Adds Go Back button
    private JButton goBack(JFrame jf) {
        JButton goBack = new JButton("Go Back");
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
            }
        });
        return goBack;
    }
}
