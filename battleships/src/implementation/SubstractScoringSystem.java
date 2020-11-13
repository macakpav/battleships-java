/**
 * 
 */
package implementation;

/**
 * @author Pavel Mačák
 *
 */
public class SubstractScoringSystem extends UnequalScoringSystem {

    private double substractValue;

    /**
     * @param handicapedPlayer
     */
    public SubstractScoringSystem(double substractValue, Player handicapedPlayer) {
	super(handicapedPlayer);
	this.substractValue = substractValue;
    }

    @Override
    protected double pointsOnHit(double points, Player scoringPlayer) {
	double coef = 0.0;
	if (super.handicapedPlayer.equals(scoringPlayer))
	    coef = this.substractValue;
	return points - coef;
    }

    @Override
    public String toString() {
	return "SubstractScoringSystem[" + this.substractValue + "]";
    }

}
