/**
 * 
 */
package implementation;

/**
 * @author Pavel Mačák
 *
 */
abstract class UnequalScoringSystem extends ScoringSystem {
    protected Player handicapedPlayer;

    protected UnequalScoringSystem(Player handicapedPlayer) {
	this.handicapedPlayer = handicapedPlayer;
    }

}
