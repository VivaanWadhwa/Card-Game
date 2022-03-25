package ui;

import model.Card;
import model.Collection;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CardPanel extends JPanel {
    private Collection col;
    private Card card;

    public CardPanel(Collection col, Card card) throws IOException {
        this.col = col;
    }
}
