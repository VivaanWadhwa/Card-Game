package ui;

import model.Card;
import model.Collection;
import model.Deck;
import model.Event;
import model.EventLog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents an Add Card Frame
public class InitialiseAddCard extends JFrame {
    private GridLayout p1;

//  EFFECTS: Initialises an Add Card frame
    public InitialiseAddCard(Deck d, Collection col) {
        p1 = new GridLayout(2, 1);
        setTitle("Add Card");
        JLabel label = new JLabel("Select Card from these Cards:");
        JScrollPane listScrollPane = new JScrollPane(generateList(this,d,col));
        this.setLayout(p1);
        this.add(label);
        this.add(listScrollPane);
        pack();
        setVisible(true);
    }

//  EFFECTS: Generates a list of collection
    private JList generateList(JFrame jf, Deck d, Collection col) {
        DefaultListModel listmodel = new DefaultListModel();
        for (Card c: col) {
            listmodel.addElement(c.getName());
        }
        JList list = new JList(listmodel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Card c1 = col.getCardfromCardName(list.getSelectedValue());
                EventLog.getInstance().logEvent(new Event("Card " + c1.getName() + " added to deck from collection"));
                d.addCard(c1);
                col.removeCard(c1);
                createTempFrame();
                jf.dispose();
            }

        });
        return list;
    }

//  EFFECTS: Creates Temporary Frame
    private void createTempFrame() {
        JFrame temp = new JFrame("Card Added");
        temp.setLayout(new GridLayout(2,1));
        JLabel j = new JLabel("Card Succesfully Added!");
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
    }
}
