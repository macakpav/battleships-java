/**
 * 
 */
package implementation;

/**
 * Abstract interface for different scoring systems.
 * 
 * @author Pavel Mačák
 *
 */
public abstract interface ScoringSystem {
    public abstract double pointsOnHit(double points, Player scoringPlayer);
}
