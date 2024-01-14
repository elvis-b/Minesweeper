package minesweeper;

import java.util.Scanner;

public class GameController {
    private final Config config;
    private final Board board;

    public GameController(int rowAmount, int columnAmount) {
        this.config = new Config(rowAmount, columnAmount);
        this.board = new Board(config);
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("How many mines do you want on the field? > ");
            config.setNumOfMines(scanner.nextInt());

            while (!board.isGameOver()) {
                System.out.println(board);
                System.out.println("Set/unset mine marks or claim a cell as free: ");

                int i = scanner.nextInt() - 1;
                int j = scanner.nextInt() - 1;
                String command = scanner.next();

                if (board.isCellExplored(i, j)) {
                    System.out.println("Cell already explored. Choose a different cell.");
                } else {
                    if (command.equals("mine")) {
                        board.setFlag(i, j);
                    } else {
                        board.claimFreeCell(i, j);
                    }
                }
            }
        }
    }
}
