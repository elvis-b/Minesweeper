package minesweeper;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Board {
    private final char[][] cell;
    private final Set<Integer> mines;
    private final Set<Integer> flags;
    private final Set<Integer> unexplored;

    private final Config config;


    public Board(Config config) {
        this.config = config;
        this.cell = new  char[config.getRowAmount()][config.getColumnAmount()];
        this.mines = new HashSet<>();
        this.flags = new HashSet<>();
        this.unexplored = new HashSet<>();

        initializeCell();

    }


    // Method to get coordinates of neighboring cells for a given position
    private int[][] getNeighboringCellCoordinates(int i, int j) {
        int[][] coordinates = new int[Coordinates.values().length][2];

        int index = 0;
        for (Coordinates coordinate: Coordinates.values()) {
            coordinates[index][0] = i + coordinate.getRowOffset();
            coordinates[index][1] = j + coordinate.getColumnOffset();
            index++;
        }

        return  coordinates;
    }

    // Method to initialize the cell array with unexplored cells
    private void initializeCell() {
        for (int i = 0; i < config.getRowAmount(); i++) {
            for (int j = 0; j < config.getColumnAmount(); j++) {
                cell[i][j] = Config.UNEXPLORED;
                unexplored.add(i * config.getRowAmount() + j);
            }
        }
    }

    // Method to randomly set mines on the game board, excluding a specified position

    public void setMines(int i, int j) {
        Random random = new Random();
        int forbidden = i * config.getRowAmount() + j;

        while (mines.size() < config.getNumOfMines()) {
            int temp = random.nextInt(config.getRowAmount() * config.getColumnAmount());
            if (temp != forbidden) {
                mines.add(temp);
            }
        }
    }

    public int getCell(int i, int j) {
        if (i >= 0 && i < config.getRowAmount() && j < config.getColumnAmount() && mines.contains(i * config.getRowAmount() + j)) {
            return 1;
        } else {
            return  0;
        }
    }

    // Method to count the number of mines in neighboring cells for a given position

    public char countOfNearbyMines(int i, int j) {
        int count = 0;
        int[][] neighbors = getNeighboringCellCoordinates(i, j);

        for (int[] neighbor: neighbors) {
            count += getCell(neighbor[0], neighbor[1]);
        }

        if (count == 0) {
            return Config.FREE;
        } else {
            return (char) (count + '0');
        }
    }

    // Method to set / unset a flag / mark at a specified position
    public void setFlag(int i, int j) {
        int position = i * config.getRowAmount() + j;
        if (flags.contains(position)) {
            flags.remove(position);
        } else {
            flags.add(position);
        }
    }

    public void claimFreeCell(int i, int j) {
        if (i < 0 || i >= config.getRowAmount() || j < 0 || j >= config.getColumnAmount()) {
            return;
        }

        if (config.getNumOfMines() > mines.size()) {
            setMines(i, j);
        }

        // If the selected position contains a mine, the game is lost; otherwise, explore the cell
        if (mines.contains(i * config.getRowAmount() + j)) {
            gameLost();
        } else if (cell[i][j] == Config.UNEXPLORED) {
            unexplored.remove(i * config.getRowAmount() + j);
            flags.remove(i * config.getRowAmount() + j);
            char count = countOfNearbyMines(i, j);
            cell[i][j] = count;
            if (count == Config.FREE) {
                for (int[] neighbor: getNeighboringCellCoordinates(i, j)) {
                    claimFreeCell(neighbor[0], neighbor[1] );
                }
            }
        }
    }

    public boolean isCellExplored(int i, int j) {
        return cell[i][j] != Config.UNEXPLORED;
    }

    public void gameLost() {
        for (Integer mine: mines) {
            int i = mine / config.getRowAmount();
            int j = mine % config.getRowAmount();
            cell[i][j] = Config.MINE;
        }

        System.out.println(this);
        System.out.println("You stepped on a mine and failed!");

        flags.clear();
        config.setGameOver(true);
    }

    public boolean isGameOver() {
        if (config.isGameOver()) {
            return true;
        }
        if (config.getNumOfMines() > mines.size()) {
            return false;
        } else {
            boolean temp = flags.equals(mines) || unexplored.equals(mines);
            if (temp) {
                System.out.println(this);
                System.out.println("Congratulations! You found all mines!");
            }
            return temp;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(" |123456789|\n");
        sb.append("-|---------|\n");

        for (int i = 0; i < config.getRowAmount(); i++) {
            sb.append((char) (i + '1'));
            sb.append("|");

            for (int j = 0; j < config.getColumnAmount(); j++) {
                if (flags.contains(i * config.getRowAmount() + j)) {
                    sb.append(Config.FLAG);
                } else {
                    sb.append(cell[i][j]);
                }
            }
            sb.append("|\n");
        }
        sb.append("-|---------|\n");
        return sb.toString();
    }
}