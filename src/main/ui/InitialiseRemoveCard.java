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

public class InitialiseRemoveCard extends JFrame {

    private GridLayout p1;

    public InitialiseRemoveCard(Deck d, Collection col) {
        p1 = new GridLayout(2, 1);
        setTitle("Remove Card");
        JLabel label = new JLabel("Card ");
        JScrollPane listScrollPane = new JScrollPane(generateList(this,d,col));
        this.setLayout(p1);
        this.add(label);
        this.add(listScrollPane);
        pack();
        setVisible(true);
    }


    private JList generateList(JFrame jf, Deck d, Collection col) {
        DefaultListModel listmodel = new DefaultListModel();
        for (Card c: d) {
            listmodel.addElement(c.getName());
        }
        JList list = new JList(listmodel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        list.setSelectedIndex();
        list.setVisibleRowCount(5);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Card c1 = d.getCardfromCardName(list.getSelectedValue());
                EventLog.getInstance().logEvent(new Event("Card " + c1.getName() + " removed from deck"));
                d.removeCard(c1);
                col.addCard(c1);
                createTempFrame();
                jf.dispose();
            }

        });
        return list;
    }

    private void createTempFrame() {
        JFrame temp = new JFrame("Card Removed");
        temp.setLayout(new GridLayout(2,1));
        JLabel j = new JLabel("Card Succesfully Removed!");
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
