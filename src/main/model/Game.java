package model;

public class Game {
    public static final int TICKS_PER_SECOND = 10;
    public final Player player = new Player();
    private boolean gameEnded = false;
    private final int width;
    private final int height;

    public Game(int width,int height) {
        this.width = width;
        this.height = height;
    }

    public void tick() {


        if (player.getHealth() == 0) {
            gameEnded = true;
        }
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public Player getPlayer() {
        return player;
    }

}
