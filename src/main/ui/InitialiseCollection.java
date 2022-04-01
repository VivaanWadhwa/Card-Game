package ui;

import model.Card;
import model.Collection;
import model.moves.Move;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;

// Represents Collection Frame
public class InitialiseCollection extends JFrame {
    private Card currCard;
    private final Collection col;

//  MODIFIES: this
//  EFFECTS: Creates the panels for Collection Frame
    public InitialiseCollection(Collection c, HashMap<Card, String> images) {
        this.col = c;
        setLayout(new GridLayout(3, 1));
        JLabel label1 = new JLabel("Select Card from Collection");
        JScrollPane listScrollPane = new JScrollPane(generateList(col,this,images));
        JButton goBack = goBack(this);
        add(label1);
        add(listScrollPane);
        add(goBack);
        pack();
        setVisible(true);
    }

//  EFFECTS: Generates List of Collections and adds List Selection Listener
    private JList generateList(Collection col, JFrame jf, HashMap<Card, String> images) {
        DefaultListModel listmodel = new DefaultListModel();
        for (Card c: col) {
            listmodel.addElement(c.getName());
        }
        JList list = new JList(listmodel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(5);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    currCard = col.getCardfromCardName(list.getSelectedValue());
                    generateCardPanel(images);
                }
            }
        });
        return list;
    }

//  EFFECTS: Generates CardDetails panel
    private void generateCardPanel(HashMap<Card, String> images) {
        JFrame cardDetails = new JFrame(currCard.getName());
        cardDetails.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cardDetails.setSize(700,590);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,1));
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File(images.get(currCard))), 0, 0, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        JPanel belowPanel = generateBelowPanel();
        mainPanel.add(imagePanel);
        mainPanel.add(belowPanel);
        cardDetails.add(mainPanel);
        cardDetails.setVisible(true);
    }

//  EFFECTS: Generates Panel below the ImagePanel
    private JPanel generateBelowPanel() {
        JPanel belowPanel = new JPanel();
        belowPanel.setLayout(new GridLayout(1,2));
        JPanel leftSidePanel = generateLeftPanel();
        JPanel rightSidePanel = generateRightPanel();
        belowPanel.add(leftSidePanel);
        belowPanel.add(rightSidePanel);
        return belowPanel;
    }

//  EFFECTS: Generates the right Panel below the ImagePanel
    private JPanel generateRightPanel() {
        JPanel rightSidePanel = new JPanel();
        rightSidePanel.setLayout(new GridLayout(1,1));
        Object[] column = {"Name","Damage","Speed"};
        DefaultTableModel data = new DefaultTableModel(0,3);
        data.addRow(column);
        for (Move m : currCard.getMoves()) {
            Object[] temp = {m.getName(),m.getDamage(),m.getSpeed()};
            data.addRow(temp);
        }
        System.out.println(data.getDataVector());
        JTable jt = new JTable(data);
        rightSidePanel.add(jt);
        return rightSidePanel;
    }

//  EFFECTS: Generates the right-handed Panel in the below Panel
    private JPanel generateLeftPanel() {
        JPanel leftSidePanel = new JPanel();
        leftSidePanel.setLayout(new GridLayout(4,1));
        JLabel name = new JLabel("Name: " + currCard.getName());
        name.setFont(new Font("Calibri", Font.PLAIN, 20));
        JLabel id = new JLabel("ID: " + currCard.getCardID());
        id.setFont(new Font("Calibri", Font.PLAIN, 20));
        JLabel health = new JLabel("Health: " + currCard.getHealth());
        health.setFont(new Font("Calibri", Font.PLAIN, 20));
        JLabel condition = new JLabel("Condition: " + currCard.checkCondition());
        condition.setFont(new Font("Calibri", Font.PLAIN, 20));
        leftSidePanel.add(name);
        leftSidePanel.add(id);
        leftSidePanel.add(health);
        leftSidePanel.add(condition);
        return leftSidePanel;
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
}

