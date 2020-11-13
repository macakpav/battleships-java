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

    public Game(Board board, ScoringSystem scoringSystem) {
	this.board = board;
	this.scoringSystem = scoringSystem;
	this.playerOne = new Player("PlayerOne");
	this.playerTwo = new Player("PlayerTwo");
	this.playerOnTurn = this.playerOne;
	this.turnNumber = 1;
    }

    public void clickOnTile(Tile tile) {
	if (!tile.isVisible()) {
	    this.fire(this.playerOnTurn, tile);
	}
    }

    private void fire(Player player, Tile tile) {
	try {
	    tile.hit();
	    player.addPoints(tile.pointsForReveleaning(), this.scoringSystem);
	    this.nextPlayer();
	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println(e);
	}
    }

    private void nextPlayer() {
	if (this.playerOnTurn.equals(this.playerOne))
	    this.playerOnTurn = this.playerTwo;
	else {
	    this.playerOnTurn = this.playerOne;
	    this.nextTurn();
	}
    }

    private void nextTurn() {
	this.turnNumber += 1;
    }

    public String scoreString() {
	return "Turn " + this.turnNumber + "\n" + this.playerOne.getName() + " has "
		+ this.playerOne.getScore() + "points \n" + this.playerTwo.getName()
		+ " has " + this.playerTwo.getScore() + "points \n";
    }

    @Override
    public String toString() {
	return "Game [" + this.scoringSystem + ", turn number " + this.turnNumber
		+ ",\nplayerOn: " + this.playerOne + ",\nplayerTwo: " + this.playerTwo
		+ ",\nplayerOnTurn: " + this.playerOnTurn.getName() + "]\n\n"
		+ this.board;
    }

    public void consolePrint() {
	System.out.println(this.toString());
    }

}
