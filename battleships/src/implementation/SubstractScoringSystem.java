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

    /**
     * @return the substractValue
     */
    public double getSubstractValue() {
	return this.substractValue;
    }

    /**
     * @param substractValue the substractValue to set
     */
    public void setSubstractValue(double substractValue) {
	this.substractValue = substractValue;
    }

    @Override
    public double pointsOnHit(double points, Player scoringPlayer) {
	double coef = 0.0;
	if (super.handicapedPlayer.equals(scoringPlayer))
	    coef = this.substractValue;
	return points - coef;
    }

    @Override
    public String toString() {
	return "Substract scoring system[" + this.substractValue + "]";
    }

    @Override
    public boolean equals(Object obj) {
	return (obj instanceof SubstractScoringSystem)
		&& ((SubstractScoringSystem) obj)
			.getSubstractValue() == this.substractValue;
    }

}
