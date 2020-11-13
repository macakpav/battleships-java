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

    @Override
    protected double pointsOnHit(double points, Player scoringPlayer) {
	double coef = 1.0;
	if (super.handicapedPlayer.equals(scoringPlayer))
	    coef = this.handicap;
	return points * coef;
    }

    @Override
    public String toString() {
	return "HandicapScoringSystem[" + this.handicap + "]";
    }

}
