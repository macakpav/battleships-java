/**
 * 
 */
package implementation;

/**
 * @author Pavel Macak
 *
 */
public final class MultiplierScoringSystem extends HandicapScoringSystem {

	public MultiplierScoringSystem(double handicap, Player handicapedPlayer) {
		super(handicapedPlayer, handicap);
	}

	/**
	 * @return the handicap
	 */
	public double getHandicap() {
		return super.handicapCoeficient;
	}

	/**
	 * @param handicap the handicap to set
	 */
	public void setHandicap(double handicap) {
		super.handicapCoeficient = handicap;
	}

	@Override
	public double pointsOnHit(double points, Player scoringPlayer) {
		double coef = 1.0;
		if (super.handicapedPlayer.equals(scoringPlayer))
			coef = super.handicapCoeficient;
		return points * coef;
	}

	@Override
	public String toString() {
		return "Handicap scoring system[" + super.handicapCoeficient + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof MultiplierScoringSystem)
				&& ((MultiplierScoringSystem) obj)
						.getHandicap() == super.handicapCoeficient;
	}

}
