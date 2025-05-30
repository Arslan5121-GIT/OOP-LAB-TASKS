import java.util.Scanner;

// Base class: Game
class Game {
    public void start() {
        System.out.println("Starting the game...");
    }
    public void end() {
        System.out.println("Game over.");
    }
    // Method to be overridden for game-specific rules
    public boolean isGameOver() {
        return false;
    }
}
// TicTacToe class inherits from Game
class TicTacToe extends Game {
    protected char[][] board;
    protected char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public boolean placeMark(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        if (board[row][col] != ' ') {
            return false;
        }
        board[row][col] = currentPlayer;
        return true;
    }

    public boolean hasWon() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer &&
                    board[i][1] == currentPlayer &&
                    board[i][2] == currentPlayer) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer &&
                    board[1][j] == currentPlayer &&
                    board[2][j] == currentPlayer) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][2] == currentPlayer) {
            return true;
        }

        if (board[0][2] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    // Overriding Game's method
    @Override
    public boolean isGameOver() {
        return hasWon() || isBoardFull();
    }
}

// Subclass extending TicTacToe to override some behavior
class AdvancedTicTacToe extends TicTacToe {

    public AdvancedTicTacToe() {
        super();
    }

    // Override printBoard to show row and column numbers for better UX
    @Override
    public void printBoard() {
        System.out.println("    1   2   3 ");
        System.out.println("  -------------");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " | ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("  -------------");
        }
    }
}

public class SimpleTicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Using the subclass with overridden methods
        AdvancedTicTacToe game = new AdvancedTicTacToe();

        System.out.println("Welcome to Tic Tac Toe ");
        game.printBoard();

        while (true) {
            System.out.println("Player " + game.getCurrentPlayer() + ", enter your move (row[1-3] and column[1-3]):");
            int row = scanner.nextInt() - 1; // zero indexed
            int col = scanner.nextInt() - 1;

            if (!game.placeMark(row, col)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }


            game.printBoard();

            if (game.hasWon()) {
                System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                break;
            }

            if (game.isBoardFull()) {
                System.out.println("The game is a draw.");
                break;
            }

            game.changePlayer();
        }

        game.end();
        scanner.close();
    }
}