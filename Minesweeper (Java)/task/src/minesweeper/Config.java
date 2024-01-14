package minesweeper;

// Class representing the constants of the game related to the different cell types and the size of the board.

public class Config {
    public static final char UNEXPLORED = '.';
    public static final char FREE = '/';
    public static final char FLAG = '*';
    public static final char MINE = 'X';

    private final int rowAmount;
    private final int columnAmount;


    private int numOfMines;

    private boolean isGameOver;


    public Config(int rowAmount, int columnAmount) {
        this.rowAmount = rowAmount;
        this.columnAmount = columnAmount;
        this.numOfMines = 0;
        this.isGameOver = false;
    }

    public int getRowAmount() {
        return rowAmount;
    }

    public int getColumnAmount() {
        return columnAmount;
    }

    public int getNumOfMines() {
        return numOfMines;
    }

    public void setNumOfMines(int numOfMines) {
        this.numOfMines = numOfMines;
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}