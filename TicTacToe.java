/**
 * defines the game of tic tac toe and then plays the game
 *
 * @author (Logan Yeubanks)
 * @version (Version 1)
 */


import java.util.Scanner;
public class TicTacToe
{
    //fields for tic tac toe class
    // 2 dimensional array called board
    private Space[][] board;
    // enumeration called space with Empty, X, and O
    public enum Space
    {
       EMPTY, X, O
    }

    /**
     * Creates a board for the tic tac toe game and populates it with the empty enumeration
     */
    public TicTacToe ()
    {
        board = new Space[3][3];
        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board.length; col++)
            {
                board[row][col] = Space.EMPTY;
            }
        }
    }

    /**
     * prints the board to the screen and fills each empty space with a "-"
     */
    private void printBoard() {

        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board.length; col++)
            {
                if(board[row][col] == Space.EMPTY)
                {
                    System.out.print("-");
                }
                else
                {
                    System.out.print(board[row][col]);
                }
            }
            System.out.println();
        }
    }

    /**
     * method that checks to see if a row, column, or diagonal has 3 matching marks, indicating a win
     *
     * @param currentPlayerMark
     * @return true or false
     */
    private boolean isGameOver(Space currentPlayerMark)
    {
        for(int row = 0; row < board.length; row++)
        {
            if(isRowAllSame(row, currentPlayerMark))
            {
                return true;
            }
        }
        for(int col = 0; col < board.length; col++)
        {
            if(isColAllSame(col, currentPlayerMark))
            {
                return true;
            }
        }
        if(isDiagonalAllSame(currentPlayerMark))
        {
            return true;
        }
        return false;
    }

    /**
     * checks to see if a row has the same mark and returns true for a win
     */
    private boolean isRowAllSame(int row, Space currentPlayerMark)
    {
        for(Space s: board[row])
        {
            if(s != currentPlayerMark)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * checks to see if a column has the same mark and returns true for a win
     *
     * @param currentPlayerMark
     * @return true or false
     */
    private boolean isColAllSame(int col, Space currentPlayerMark)
    {
        for(int row = 0; row < board.length; row++)
        {
            if(board[row][col] != currentPlayerMark)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * checks to see if both diagonal paths are matching with the same mark and returns true for a win
     *
     * @param currentPlayerMark
     * @return true or false
     */
    private boolean isDiagonalAllSame(Space currentPlayerMark)
    {
        // checks diagonal positions of the board for matching marks. numbers indicate a row and column number for all possible diagonal spaces
        if(board[0][0] == currentPlayerMark && board[1][1] == currentPlayerMark && board[2][2] == currentPlayerMark || board[0][2] == currentPlayerMark && board[1][1] == currentPlayerMark && board[2][0] == currentPlayerMark)
        {
            return true;
        }
        return false;
    }

    public static void runGame() {
        //Prompts for welcoming and then receiving input for a name for player 1 and 2
        System.out.println("Welcome to tic tac toe!");
        System.out.println("What is your name player 1?");
        Scanner input = new Scanner(System.in);
        String p1Name = input.next();
        System.out.println("What is your name player 2?");
        String p2Name = input.next();

        //creates new tic tac toe object called game
        TicTacToe game = new TicTacToe();

        //boolean value to use for while loop to always run until told not to
        boolean running = true;

        //2 new player objects are made with one getting the x enumeration and the other the O enumeration
        Player p1 = new Player(Space.X, p1Name);
        Player p2 = new Player(Space.O, p2Name);

        // used to later manipulate who's turn it is in the game
        Player currentPlayer = p1;

        //counters to later raise and check if equal to 9 in order to determine if the game is a tie
        int numOfTurns = 0;
        int maxNumOfTurns = 9;

        //main while loop. Game is played until this loop is broken by running being equal to false
        while(running){



            System.out.println(currentPlayer.getName() + ", it is your turn");
            game.printBoard();
            //player turn loop, prompts user for a row and column 1-3 and then subtracts one to accommodate, then adds mark of current player to the board
            int row;
            int col;
            do {
                System.out.println("Choose a row 1-3");
                row = input.nextInt() -1;
                System.out.println("Choose a column 1-3");
                col = input.nextInt() -1;
                //checks to see if someone has already played that space on the board by comparing to empty
                if(row >= 0 && col >= 0 && game.board[row][col] != Space.EMPTY)
                {
                    System.out.println("Someone has already played that space! Choose a empty space to play");
                }
            }
            while(row < 0 || col < 0 || row >= 3 || col >= 3 || game.board[row][col] != Space.EMPTY);

            //sets the specified position with the marking for current player
            game.board[row][col] = currentPlayer.getPlayerType();
            numOfTurns++;

            // sets running to false and proclaims the game as a tie if there has been 9 iterations of the loop and no winner
            if (numOfTurns == maxNumOfTurns && !game.isGameOver(currentPlayer.getPlayerType()))
            {
                System.out.println("Tie! Game Over");
                running = false;
            }

            //uses isGameOver method to state that someone has won the game and then sets running to false to end the game
            if(game.isGameOver(currentPlayer.getPlayerType()))
            {
                System.out.println("Game over! " + currentPlayer.getName() + " won the match!");
                running = false;
            }


            // statement that tells the current player to change to the other player if a successful iteration of the above while loop is completed
            if(currentPlayer == p1)
            {
                currentPlayer = p2;
            }
            else
            {
                currentPlayer = p1;
            }
        }

    }


}

