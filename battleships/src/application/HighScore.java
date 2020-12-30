
package application;

/**
 * Class holding a single high score and name of the player who scored it.
 * 
 * @author Pavel Macak
 *
 */
class HighScore {
	private final String playerName;
	private final double score;
	static final String separator = " scored ";

	HighScore(String playerName, double score) {
		this.playerName = playerName;
		this.score = score;
	}

	/**
	 * @return the playerName
	 */
	String getPlayerName() {
		return this.playerName;
	}

	/**
	 * @return the score
	 */
	double getScore() {
		return this.score;
	}

	@Override
	public String toString() {
		return this.playerName + separator + this.score;
	}
}
