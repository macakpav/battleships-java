/**
 * 
 */
package implementation;

/**
 * @author Pavel Mačák
 *
 */
class Player {
    private String name;
    private double score;

    /**
     * Basic constructor with score initialized to zero.
     * 
     * @param name
     */
    protected Player(String name) {
	super();
	this.name = name;
	this.score = 0.0;
    }

    /**
     * @return the player's score
     */
    protected double getScore() {
	return this.score;
    }

    /**
     * @param points        the points to add to player's score
     * @param scoringSystem system of adding points to player's score
     */
    protected void addPoints(double points, ScoringSystem scoringSystem) {
	this.score += scoringSystem.pointsOnHit(points, this);
    }

    /**
     * @return the player's name
     */
    protected String getName() {
	return this.name;
    }

    @Override
    public String toString() {
	return "Player [name=" + this.name + ", score=" + this.score + "]";
    }

}
