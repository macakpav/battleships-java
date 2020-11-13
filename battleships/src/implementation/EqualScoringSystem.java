/**
 * 
 */
package implementation;

/**
 * @author Pavel Mačák
 *
 */
public final class EqualScoringSystem extends ScoringSystem {

    @Override
    protected double pointsOnHit(double points, Player scoringPlayer) {
	return points;
    }

    @Override
    public String toString() {
	return "EqualScoringSystem";
    }

}
