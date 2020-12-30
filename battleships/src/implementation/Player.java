/**
 * 
 */
package implementation;

/**
 * Class representing a player playing the game.
 * 
 * @author Pavel Mačák
 *
 */
public class Player {
    private String name;
    private double score;
    private final String myDefaultName;

    /**
     * Basic constructor with score initialized to zero.
     * 
     * @param name
     */
    public Player(String name) {
	super();
	this.score = 0.0;
	if (name == null || name.trim().equals("")) {
	    this.name = "Empty name";
	} else {
	    this.name = name;
	}
	this.myDefaultName = this.name;
    }

    /**
     * @return the player's score
     */
    protected double getScore() {
	return this.score;
    }

    /**
     * @param points        the points to add to player's score
     * @param iScoringSystem system of adding points to player's score
     */
    protected void addPoints(double points, IScoringSystem iScoringSystem) {
	this.score += iScoringSystem.pointsOnHit(points, this);
    }

    /**
     * @return the player's name
     */
    public String getName() {
	return this.name;
    }

    /**
     * @param name new name for the player.
     */
    public void setName(String name) {
	if (name.trim().equals("")) {
	    this.name = this.myDefaultName;
	} else {
	    this.name = name;
	}
    }

    @Override
    public String toString() {
	return "Player [name=" + this.name + ", score=" + this.score + "]";
    }

    /**
     * 
     */
    public void reset() {
	score = 0.0;
    }

}
