package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.Direction;
import model.Game;

import java.io.IOError;
import java.io.IOException;

public class TerminalGame {
    private Game game;
    private Screen screen;
    private WindowBasedTextGUI endGUI;

    public void start() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        TerminalSize terminalSize = screen.getTerminalSize();

        game = new Game(terminalSize.getColumns(),
                terminalSize.getRows());

    }

    public void beginTicks() throws IOException, InterruptedException {
        while (game.isGameEnded() || endGUI.getActiveWindow() != null) {
            tick();
            Thread.sleep(1000L / Game.TICKS_PER_SECOND);
        }

        System.exit(0);
    }

    public void tick() throws IOException {
        game.tick();

        screen.setCursorPosition(new TerminalPosition(0,0));
        screen.clear();
        render();
        screen.refresh();

        screen.setCursorPosition(new TerminalPosition(screen.getTerminalSize().getColumns(),0));
    }

    public void userInput() throws IOException {
        KeyStroke key = screen.pollInput();

        if (key == null || key.getCharacter() != null) {
            return;
        }

        Direction dir = newDir(key.getKeyType());

        if (dir == null) {
            return;
        }

        game.getPlayer().setDir(dir);
    }

    public Direction newDir(KeyType key) {
        switch (key) {
            case ArrowUp:
                return Direction.UP;
            case ArrowDown:
                return Direction.DOWN;
            case ArrowLeft:
                return Direction.LEFT;
            case ArrowRight:
                return Direction.RIGHT;
            default:
                return null;
        }
    }

    public void render() {
        if (game.isGameEnded()) {
            if (endGUI == null) {
                endScreen();
            }

            return;
        }

        drawLevel();
        drawPlayer();
        drawItems();
    }

    public void endScreen() {
        endGUI = new MultiWindowTextGUI(screen);

        new MessageDialogBuilder()
                .setTitle("YOU DIED.")
                .setText("Retry?")
                .addButton(MessageDialogButton.Retry)
                .build()
                .showDialog(endGUI);
    }

    public void drawLevel() {
//        DO!!!!!
    }

    public void drawPlayer() {
//        DO!!!
    }
    public void drawItems() {
//        DO!!!!
    }
}
