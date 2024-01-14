package minesweeper;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController(9,9);
        gameController.start();
    }
}