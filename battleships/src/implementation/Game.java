/**
 * 
 */
package implementation;

/**
 * @author Pavel Mačák
 *
 */
public class Game {
    private Board board;
    private ScoringSystem scoringSystem;
    // private GUI gui;
    private Player playerOne;
    private Player playerTwo;
    private Player playerOnTurn;
    public int turnNumber;

    /**
     * Basic initialization of Game instance.
     * 
     * @param board         that the game will be played on.
     * @param scoringSystem used for counting points.
     */
    public Game(Board board, ScoringSystem scoringSystem) {
	this.board = board;
	this.scoringSystem = scoringSystem;
	this.playerOne = new Player("PlayerOne");
	this.playerTwo = new Player("PlayerTwo");
	this.playerOnTurn = this.playerOne;
	this.turnNumber = 1;
    }

    /**
     * Process a "mouse click" signal on tile.
     * 
     * @param tile which was clicked on.
     */
    public void clickOnTile(Tile tile) {
	if (!tile.isVisible()) {
	    this.fire(this.playerOnTurn, tile);
	    this.nextPlayer();
	} else {
	    System.out.println("Tile is already visible.");
	}
    }

    /**
     * Process a player's attempt to hit a tile.
     * 
     * @param player who fired
     * @param tile   that he wants to hit
     */
    private void fire(Player player, Tile tile) {
	try {
	    tile.hit();
	    player.addPoints(tile.pointsForReveleaning(), this.scoringSystem);
	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println(e);
	}
    }

    /**
     * Switch the playerOrTurn. Proceed to next turn if playerTwo played his move.
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
	return "Turn " + this.turnNumber + "\n" + this.playerOne.getName() + " has "
		+ this.playerOne.getScore() + "points \n" + this.playerTwo.getName()
		+ " has " + this.playerTwo.getScore() + "points \n";
    }

    /**
     * @return The current game status. The Board visualization is also included.
     */
    @Override
    public String toString() {
	return "Game [" + this.scoringSystem + ", turn number " + this.turnNumber
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

}
