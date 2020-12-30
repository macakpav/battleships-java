/**
 * 
 */
package implementation;

import java.awt.Color;

import application.BoardSetup;

/**
 * Instance of this class represents a game that is played.
 * 
 * @author Pavel Mačák
 *
 */
public class Game {
    private final Board board;
    private final IScoringSystem iScoringSystem;
    private final Player playerOne;
    private final Player playerTwo;
    private Player playerOnTurn;
    private String lastPlayComment;
    private boolean gameEnded;
    public int turnNumber;

    /**
     * Short initialization of Game instance. Player one and player two will have
     * default names.
     * 
     * @param boardSetup    setup for creating a board.
     * @param scoringSystem used for counting points.
     */
    public Game(BoardSetup boardSetup, EqualScoringSystem scoringSystem) {
	this(new Board(boardSetup), scoringSystem);
    }

    /**
     * Short initialization of Game instance. Player one and player two will have
     * default names.
     * 
     * @param board         that the game will be played on.
     * @param iScoringSystem used for counting points.
     */
    Game(Board board, IScoringSystem iScoringSystem) {
	this(board, iScoringSystem, new Player("Player One"),
		new Player("Player Two"));
    }

    /**
     * Basic initialization of Game instance.
     * 
     * @param boardSetup    setup for creating a board.
     * @param iScoringSystem used for counting points.
     * @param one           first player.
     * @param two           second player.
     */
    public Game(BoardSetup boardSetup, IScoringSystem iScoringSystem, Player one,
	    Player two) {
	this(new Board(boardSetup), iScoringSystem, one, two);
    }

    /**
     * Basic initialization of Game instance.
     * 
     * @param board         that the game will be played on.
     * @param iScoringSystem used for counting points.
     * @param one           first player.
     * @param two           second player.
     */
    Game(Board board, IScoringSystem iScoringSystem, Player one, Player two) {
	this.board = board;
	this.iScoringSystem = iScoringSystem;
	this.playerOne = one;
	this.playerTwo = two;
	this.playerOnTurn = this.playerOne;
	this.turnNumber = 1;
	this.gameEnded = false;
    }

    /**
     * Process a "mouse click" signal on tile.
     * 
     * @param tileID of the tile which was clicked on.
     * @return True if state of game changed.
     */
    public boolean clickOnTile(int tileID) {
	return clickOnTile(this.board.tile(tileID));
    }

    /**
     * Process a "mouse click" signal on tile.
     * 
     * @param tile which was clicked on.
     * @return True if state of game changed.
     */
    public boolean clickOnTile(Tile tile) {
	if (this.gameEnded)
	    return false;
	if (!tile.isVisible()) {
	    HitType hit = this.fire(this.playerOnTurn, tile);
	    if (!(hit == HitType.HIT)) {
		nextPlayer();
	    }
	    this.gameEnded = this.board.areAllSunk();
	    changeComment(hit);
	    return true;
	}
	changeComment("Tile is already visible.");
//	    System.out.println("Tile is already visible.");
	return false;

    }

    /**
     * @param hit
     */
    private void changeComment(HitType hit) {
	assert (hit != null);
	switch (hit) {
	case MISS:
	    changeComment("Well that one went far away from its target.");
	    break;
	case HIT:
	    changeComment("Direct hit! Keep going!");
	    break;
	case SINK:
	    changeComment(
		    "Boom! This one is going down. Now let the other player to the cannon.");
	    break;
	default:
	    break;
	}
    }

    /**
     * @param string
     */
    private void changeComment(String string) {
	if (this.gameEnded)
	    this.lastPlayComment = "The game is over!";
	else
	    this.lastPlayComment = string;
    }

    /**
     * Process a player's attempt to hit a tile.
     * 
     * @param player who fired
     * @param tile   that he wants to hit
     * @return type of hit: miss, hit or sink
     */
    private HitType fire(Player player, Tile tile) {
	HitType hit = null;
	try {
	    hit = tile.hit();
	    player.addPoints(tile.pointsForReveleaning(), this.iScoringSystem);
	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println(e);
	}
	return hit;

    }

    /**
     * Switch the playerOrTurn. Proceed to next turn.
     */
    private void nextPlayer() {
	if (this.playerOnTurn.equals(this.playerOne))
	    this.playerOnTurn = this.playerTwo;
	else {
	    this.playerOnTurn = this.playerOne;
	    this.nextTurn();
	}
    }

    /**
     * Add one to turn number.
     */
    private void nextTurn() {
	this.turnNumber += 1;
    }

    /**
     * @return current standings of players.
     */
    public String scoreString() {
	return "Turn " + this.turnNumber + "\n" + playerOneName() + " has "
		+ playerOneScore() + "points \n" + playerTwoName() + " has "
		+ playerTwoScore() + "points \n";
    }

    public String playerOneScore() {
	return String.valueOf(this.playerOne.getScore());
    }

    public String playerTwoScore() {
	return String.valueOf(this.playerTwo.getScore());
    }

    public String playerTwoName() {
	return this.playerTwo.getName();
    }

    public String playerOneName() {
	return this.playerOne.getName();
    }

    public String playerOnTurnName() {
	return this.playerOnTurn.getName();
    }

    /**
     * @return The current game status. The Board visualization is also included.
     */
    @Override
    public String toString() {
	return "Game [" + this.iScoringSystem + ", turn number " + this.turnNumber
		+ ",\nplayerOne: " + this.playerOne + ",\nplayerTwo: "
		+ this.playerTwo + ",\nplayerOnTurn: " + this.playerOnTurn.getName()
		+ "]\n\n" + this.board;
    }

    /**
     * Prints toString() representation of Game.
     */
    public void consolePrint() {
	System.out.println(this.toString());
    }

    public int getCols() {
	return this.board.getCols();
    }

    public int getRows() {
	return this.board.getRows();
    }

    public Color getTileColor(int id) {
	return this.board.tile(id).getColor().COLOR();
    }

    public String getComment() {
	return this.lastPlayComment;
    }

    public boolean isOver() {
	return this.gameEnded;
    }

    private int getWinner() {
	if (!this.gameEnded)
	    return -1;
	if (this.playerOne.getScore() == this.playerTwo.getScore())
	    return 0;
	if (this.playerOne.getScore() > this.playerTwo.getScore())
	    return 1;

	return 2;
    }

    public String getWinnerName() {
	switch (getWinner()) {
	case 0:
	    return playerOneName() + " and " + playerTwoName();
	case 1:
	    return playerOneName();
	case 2:
	    return playerTwoName();
	}
	return "";

    }

    public double getWinnerScore() {
	switch (getWinner()) {
	case 0:
	    return this.playerOne.getScore();
	case 1:
	    return this.playerOne.getScore();
	case 2:
	    return this.playerTwo.getScore();
	}
	return -1;
    }

    /**
     * @return
     */
    public boolean isTie() {
	return getWinner() == 0;
    }

    /**
     * 
     */
    public void resetPlayers() {
	playerOne.reset();
	playerTwo.reset();
    }

}
