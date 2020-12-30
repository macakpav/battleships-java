/**
 * 
 */
package implementation;

/**
 * @author Pavel Macak
 *
 */
public class SubstractScoringSystem extends HandicapScoringSystem {

	/**
	 * @param handicapedPlayer
	 */
	public SubstractScoringSystem(double substractValue, Player handicapedPlayer) {
		super(handicapedPlayer, substractValue);
	}

	/**
	 * @return the substractValue
	 */
	public double getSubstractValue() {
		return super.handicapCoeficient;
	}

	/**
	 * @param substractValue the substractValue to set
	 */
	public void setSubstractValue(double substractValue) {
		super.handicapCoeficient = substractValue;
	}

	@Override
	public double pointsOnHit(double points, Player scoringPlayer) {
		if (points == 0.0)
			return 0.0;
		double coef = 0.0;
		if (super.handicapedPlayer.equals(scoringPlayer))
			coef = super.handicapCoeficient;
		return points - coef;
	}

	@Override
	public String toString() {
		return "Substract scoring system[" + super.handicapCoeficient + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof SubstractScoringSystem)
				&& ((SubstractScoringSystem) obj)
						.getSubstractValue() == super.handicapCoeficient;
	}

}
