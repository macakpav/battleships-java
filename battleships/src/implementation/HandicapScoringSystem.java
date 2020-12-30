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
abstract class HandicapScoringSystem implements ScoringSystem {
    protected Player handicapedPlayer;
    protected double handicapCoeficient;

    protected HandicapScoringSystem(Player handicapedPlayer,
	    double handicapCoeficient) {
	this.handicapedPlayer = handicapedPlayer;
	this.handicapCoeficient = handicapCoeficient;
    }
}
