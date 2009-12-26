package org.cherchi.reversi.logic.internal;

import org.cherchi.reversi.logic.GameEventsListener;
import org.cherchi.reversi.logic.GameFacade;
import org.cherchi.reversi.logic.GameLogic;

/**
 * The implementation of the high level method used in the game
 * 
 * @author Fernando Cherchi
 * 
 */
public class GameFacadeImpl implements GameFacade {

	// /////////////////////// CONSTANTS ///////////////////////////////

	// /////////////////////// PRIVATE FIELDS //////////////////////////

	/**
	 * access to the game operations
	 */
	private GameLogic gameLogic;

	/**
	 * This is the object to be notified of the game situation
	 */
	private GameEventsListener gameEventsListener;

	// /////////////////////// PUBLIC METHODS //////////////////////////

	/**
	 * Starts a new game
	 */
	@Override
	public void restart() {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets the chip for the given player in a given position
	 */
	@Override
	public void set(int player, int col, int row) {

		if (this.gameLogic.canSet(player, col, row)) {
			this.gameLogic.setChip(player, col, row);
			this.gameLogic.conquerPosition(player, col, row);
			this.togglePlayer();
			this.notifyChanges();
		}
	}

	/**
	 * Gets the current allowed positions for current Player
	 */
	@Override
	public int[][] getAllowedPositionsForPlayer() {
		return this.gameLogic.getAllowedPositionsForPlayer(this
				.getCurrentPlayer());
	}

	/**
	 * Gets the current matrix of the game
	 */
	@Override
	public int[][] getGameMatrix() {
		return this.gameLogic.getGameMatrix();
	}

	/**
	 * Sets the event listener (is going to be notified when the game situation
	 * changes)
	 */
	@Override
	public void setGameEventsListener(GameEventsListener listener) {
		this.gameEventsListener = listener;
	}

	// /////////////////////// PRIVATE METHODS ///////////////////////

	/**
	 * Notifies the listener for the changes occured in the game
	 */
	private void notifyChanges() {
		if (this.gameEventsListener != null) {
			this.gameEventsListener.onScoreChanged(this.gameLogic
					.getCounterForPlayer(PLAYER_ONE), this.gameLogic
					.getCounterForPlayer(PLAYER_TWO));
		}
	}

	/**
	 * Changes the player
	 */
	private void togglePlayer() {

		int current = this.gameLogic.getCurrentPlayer();
		// if the current player can play (has at least one place to put the
		// chip)
		if (!this.gameLogic.isBlockedPlayer(current)) {

			// just toggles
			if (current == GameLogic.PLAYER_ONE) {
				this.gameLogic.setCurrentPlayer(GameLogic.PLAYER_TWO);
			} else {
				this.gameLogic.setCurrentPlayer(GameLogic.PLAYER_ONE);
			}
		}
	}

	// /////////////////////// ACCESSORS //////////////////////////

	/**
	 * @param gameLogic
	 *            the gameLogic to set
	 */
	public void setGameLogic(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
	}

	/**
	 * @return the gameLogic
	 */
	public GameLogic getGameLogic() {
		return gameLogic;
	}

	/**
	 * Gets the player that has to play
	 */
	@Override
	public int getCurrentPlayer() {
		return this.gameLogic.getCurrentPlayer();
	}

	/**
	 * Gets the score for the given player
	 */
	@Override
	public int getScoreForPlayer(int player) {
		return this.gameLogic.getCounterForPlayer(player);
	}

}
