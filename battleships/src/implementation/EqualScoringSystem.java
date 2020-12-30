package implementation;

/**
 * Both Players get the same amount of points.
 * 
 * @author Pavel Macak
 *
 */
public final class EqualScoringSystem implements IScoringSystem {

	@Override
	public double pointsOnHit(double points, Player scoringPlayer) {
		return points;
	}

	@Override
	public String toString() {
		return "Equal scoring system";
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof EqualScoringSystem);
	}

}
