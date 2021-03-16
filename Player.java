/**
 * defines what a player is for use in the game of tic tac toe
 *
 * @author (Logan Yeubanks)
 * @version (Version 1)
 */
public class Player {
    //fields for elements of a player
    private TicTacToe.Space playerType;
    private String name;

    /**
     * Constructor for the Player class
     */
    public Player(TicTacToe.Space playerType, String name)
    {
        this.playerType = playerType;
        this.name = name;
    }

    /**
     * returns the name of player
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * returns the type of mark the player is using
     *
     * @return playerType
     */
    public TicTacToe.Space getPlayerType()
    {
        return playerType;
    }

}

