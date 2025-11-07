// Chess.java
import java.util.Scanner;

public class Chess {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.initializeBoard();
        board.displayBoard();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your move (e.g., e2 e4): ");
            String move = scanner.nextLine();
            if (move.equalsIgnoreCase("exit")) break;

            String[] positions = move.split(" ");
            if (positions.length == 2) {
                board.movePiece(positions[0], positions[1]);
                board.displayBoard();
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }
        scanner.close();
    }
}

// ChessBoard.java
class ChessBoard {
    private final String[][] board = new String[8][8];

    public void initializeBoard() {
        // Initialize pieces for both players
        String[] backRow = {"R", "N", "B", "Q", "K", "B", "N", "R"};
        String[] pawns = {"P", "P", "P", "P", "P", "P", "P", "P"};

        // Set up the board
        for (int i = 0; i < 8; i++) {
            board[0][i] = "B" + backRow[i]; // Black back row
            board[1][i] = "BP";            // Black pawns
            board[6][i] = "WP";            // White pawns
            board[7][i] = "W" + backRow[i]; // White back row
        }

        // Empty spaces
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = "--";
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void movePiece(String from, String to) {
        int[] fromPos = parsePosition(from);
        int[] toPos = parsePosition(to);

        if (fromPos != null && toPos != null) {
            String piece = board[fromPos[0]][fromPos[1]];
            board[fromPos[0]][fromPos[1]] = "--";
            board[toPos[0]][toPos[1]] = piece;
        } else {
            System.out.println("Invalid move. Try again.");
        }
    }

    private int[] parsePosition(String pos) {
        if (pos.length() != 2) return null;

        char file = pos.charAt(0);
        char rank = pos.charAt(1);

        int row = 8 - Character.getNumericValue(rank);
        int col = file - 'a';

        if (row >= 0 && row < 8 && col >= 0 && col < 8) {
            return new int[]{row, col};
        }
        return null;
    }
}
