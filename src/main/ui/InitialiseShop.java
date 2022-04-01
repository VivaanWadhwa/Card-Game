package ui;

import model.*;
import model.Event;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

// Represents a JFrame of Shopping Class
public class InitialiseShop extends JFrame {

//    MODFIES: this
//    EFFECTS: Generates Shop Panel
    public InitialiseShop(Shop s, Wallet w, Collection c) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Shop");
        setLayout(new GridLayout(5,1));
        JLabel label1 = new JLabel("Shop");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel label2 = new JLabel("Select Card to Buy");
        JScrollPane listScrollPane = new JScrollPane(generateList(this,s,w,c));
        String balance = "Balance: " + w.getBalance();
        JLabel label3 = new JLabel(balance);
        JButton goback = goBack(this);
        this.add(label1);
        this.add(label2);
        this.add(listScrollPane);
        this.add(label3);
        this.add(goback);
        pack();
        setSize(300,600);
        this.setVisible(true);
    }

//  EFFECTS:Generates List from shop Cards
    private JList generateList(JFrame jf,Shop s, Wallet w, Collection c) {
        DefaultListModel listmodel = new DefaultListModel();
        for (Card p: s.getKeys()) {
            listmodel.addElement(p.getName());
        }
        JList list = new JList(listmodel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Card c1 = s.getCardFromName(list.getSelectedValue());
                EventLog.getInstance().logEvent(new Event("Bought card " + c1.getName()
                        + " and added it to collection"));
                s.buyItem(c1,w,c);
                createTempFrame();
                jf.dispose();
            }
        });
        return list;
    }

//  EFFECTS: Generates Go back button
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

//  EFFECTS: Creates temporary frame
    private void createTempFrame() {
        JFrame temp = new JFrame("Card Purchased");
        temp.setLayout(new GridLayout(2,1));
        JLabel j = new JLabel("Card Succesfully Purchased!");
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
