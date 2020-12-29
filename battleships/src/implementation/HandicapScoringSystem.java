/**
 * 
 */
package implementation;

/**
 * @author Pavel Mačák
 *
 */
public final class HandicapScoringSystem extends UnequalScoringSystem {
    private double handicap;

    public HandicapScoringSystem(double handicap, Player handicapedPlayer) {
	super(handicapedPlayer);
	this.handicap = handicap;
    }

    /**
     * @return the handicap
     */
    public double getHandicap() {
	return this.handicap;
    }

    /**
     * @param handicap the handicap to set
     */
    public void setHandicap(double handicap) {
	this.handicap = handicap;
    }

    @Override
    public double pointsOnHit(double points, Player scoringPlayer) {
	double coef = 1.0;
	if (super.handicapedPlayer.equals(scoringPlayer))
	    coef = this.handicap;
	return points * coef;
    }

    @Override
    public String toString() {
	return "Handicap scoring system[" + this.handicap + "]";
    }

    @Override
    public boolean equals(Object obj) {
	return (obj instanceof HandicapScoringSystem)
		&& ((HandicapScoringSystem) obj).getHandicap() == this.handicap;
    }
}
