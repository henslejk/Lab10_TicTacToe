
import java.util.Scanner;

public class TicTacToes
{
    // Constants
    private static final int ROWS = 3;
    private static final int COLS = 3;

    // The game board
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        // Variables used in the game
        String currentPlayer;
        int rowMove;
        int colMove;
        int moveCount;
        boolean gameOver;
        boolean playAgain = true;

        // Main game loop
        while (playAgain)
        {
            // Clear the board
            clearBoard();

            // X goes first
            currentPlayer = "X";

            moveCount = 0;
            gameOver = false;

            // Continue
            while (!gameOver)
            {
                // Display the board
                display();

                IO.println("Player " + currentPlayer + "'s turn.");

                // Get the player's move
                rowMove = SafeInput.getRangedInt(in, "Enter row", 1, ROWS);
                colMove = SafeInput.getRangedInt(in, "Enter column", 1, COLS);

                // Convert coordinates to array
                rowMove--;
                colMove--;

                // Checks a valid move
                while (!isValidMove(rowMove, colMove))
                {
                    System.out.println("That position is already occupied. Try again.");

                    display();

                    rowMove = SafeInput.getRangedInt(in, "Enter row", 1, ROWS);
                    colMove = SafeInput.getRangedInt(in, "Enter column", 1, COLS);

                    rowMove--;
                    colMove--;
                }

                // Player's move
                board[rowMove][colMove] = currentPlayer;

                // Increment the move counter
                moveCount++;

                // Check for a win
                if (isWin(currentPlayer))
                {
                    display();

                    System.out.println("Player " + currentPlayer + " wins!");

                    gameOver = true;
                }

                // Check for a tie
                else if (moveCount >= 5 && isTie())
                {
                    display();

                    System.out.println("The game is a tie!");

                    gameOver = true;
                }

                // Switch players
                else
                {
                    if (currentPlayer.equals("X"))
                    {
                        currentPlayer = "O";
                    }
                    else
                    {
                        currentPlayer = "X";
                    }
                }
            }

            //Another game
            playAgain = SafeInput.getYNconfirm(in, "Would you like to play again?");
        }

        IO.println("Thanks for playing Tic Tac Toe!");

        in.close();
    }

    // Clears all spaces on the board
    private static void clearBoard()
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    // Displays the Tic Tac Toe board
    private static void display()
    {
        System.out.println();

        for (int row = 0; row < ROWS; row++)
        {
            System.out.print(" ");

            for (int col = 0; col < COLS; col++)
            {
                System.out.print(board[row][col]);

                if (col < COLS - 1)
                {
                    System.out.print(" | ");
                }
            }

            System.out.println();

            if (row < ROWS - 1)
            {
                System.out.println("---+---+---");
            }
        }

        System.out.println();
    }

    // Checks whether a move is valid
    private static boolean isValidMove(int row, int col)
    {
        return board[row][col].equals(" ");
    }

    // Checks whether the specified player has won
    private static boolean isWin(String player)
    {
        return isRowWin(player) ||
                isColWin(player) ||
                isDiagnalWin(player);
    }

    // Checks a horizontal win
    private static boolean isRowWin(String player)
    {
        for (int row = 0; row < ROWS; row++)
        {
            boolean match = true;

            for (int col = 0; col < COLS; col++)
            {
                if (!board[row][col].equals(player))
                {
                    match = false;
                }
            }

            if (match)
            {
                return true;
            }
        }

        return false;
    }

    // Checks a vertical win
    private static boolean isColWin(String player)
    {
        for (int col = 0; col < COLS; col++)
        {
            boolean match = true;

            for (int row = 0; row < ROWS; row++)
            {
                if (!board[row][col].equals(player))
                {
                    match = false;
                }
            }

            if (match)
            {
                return true;
            }
        }

        return false;
    }

    // Checks a diagonal win
    private static boolean isDiagnalWin(String player)
    {
        // Check top-left to bottom-right
        boolean diagonal1 = true;

        for (int i = 0; i < ROWS; i++)
        {
            if (!board[i][i].equals(player))
            {
                diagonal1 = false;
            }
        }

        if (diagonal1)
        {
            return true;
        }

        // Check top-right to bottom-left
        boolean diagonal2 = true;

        for (int i = 0; i < ROWS; i++)
        {
            if (!board[i][COLS - 1 - i].equals(player))
            {
                diagonal2 = false;
            }
        }

        return diagonal2;
    }

    // Checks a tie
    private static boolean isTie()
    {
        // Checks board
        boolean fullBoard = true;

        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                if (board[row][col].equals(" "))
                {
                    fullBoard = false;
                }
            }
        }

        if (fullBoard)
        {
            return true;
        }

        // Check blocked
        boolean allRowsBlocked = true;

        for (int row = 0; row < ROWS; row++)
        {
            if (!hasBothPlayers(board[row][0],
                    board[row][1],
                    board[row][2]))
            {
                allRowsBlocked = false;
            }
        }

        // Checks blocked
        boolean allColsBlocked = true;

        for (int col = 0; col < COLS; col++)
        {
            if (!hasBothPlayers(board[0][col],
                    board[1][col],
                    board[2][col]))
            {
                allColsBlocked = false;
            }
        }

        // Checks both diagonals
        boolean diagonal1Blocked = hasBothPlayers(
                board[0][0], board[1][1], board[2][2]);

        boolean diagonal2Blocked = hasBothPlayers(
                board[0][2], board[1][1], board[2][0]);

        // All eight winning combinations must be blocked
        return allRowsBlocked &&
                allColsBlocked &&
                diagonal1Blocked &&
                diagonal2Blocked;
    }

    // Checks whether a combination contains both X and O
    private static boolean hasBothPlayers(String a, String b, String c)
    {
        boolean hasX = a.equals("X") ||
                b.equals("X") ||
                c.equals("X");

        boolean hasO = a.equals("O") ||
                b.equals("O") ||
                c.equals("O");

        return hasX && hasO;
    }
}