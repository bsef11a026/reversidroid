package org.cherchi.reversi.logic;

/**
 * This listener will be notified when the score changes
 * @author Fernando Cherchi
 */
public interface GameEventsListener {
	
	/**
	 * Invoked when the score changes
	 * @param p1Score
	 * @param p2Score
	 */
	void onScoreChanged(int p1Score, int p2Score);
}
