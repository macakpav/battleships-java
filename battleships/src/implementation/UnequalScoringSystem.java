/**
 * 
 */
package implementation;

/**
 * Players get different amount of points.
 * 
 * @author Pavel Mačák
 *
 */
abstract class UnequalScoringSystem implements ScoringSystem {
    protected Player handicapedPlayer;

    protected UnequalScoringSystem(Player handicapedPlayer) {
	this.handicapedPlayer = handicapedPlayer;
    }

}
