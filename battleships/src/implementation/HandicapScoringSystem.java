/**
 * 
 */
package implementation;

/**
 * Players get different amount of points.
 * 
 * @author Pavel Macak
 *
 */
abstract class HandicapScoringSystem implements IScoringSystem {
	protected final Player handicapedPlayer;
	protected double handicapCoeficient;

	protected HandicapScoringSystem(Player handicapedPlayer,
			double handicapCoeficient) {
		this.handicapedPlayer = handicapedPlayer;
		this.handicapCoeficient = handicapCoeficient;
	}
}
