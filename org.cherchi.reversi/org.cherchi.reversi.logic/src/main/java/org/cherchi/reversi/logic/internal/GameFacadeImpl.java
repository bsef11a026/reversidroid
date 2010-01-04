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

	// /////////////////////// PRIVATE FIELDS //////////////////////////

	/**
	 * access to the game operations
	 */
	private GameLogic gameLogic;

	/**
	 * This is the object to be notified of the game situation
	 */
	private GameEventsListener gameEventsListener;

	/**
	 * Indicates if we are in 1 player mode or 2 player mode
	 */
	private boolean isMachineOpponent = true;

	// /////////////////////// PUBLIC METHODS //////////////////////////

	/**
	 * Starts a new game
	 */
	@Override
	public void restart() {

		this.gameLogic.initialize();
	}

	/**
	 * Sets the chip for the given player in a given position
	 */
	@Override
	public void set(int player, int col, int row) {

		boolean machineHasToPlay = true;

		this.doMovement(player, col, row);
		// if is the machine moment...
		if (this.isMachineOpponent
				&& this.getCurrentPlayer() == GameLogic.PLAYER_TWO) {

			while (machineHasToPlay) {
				Movement machineMovement = this.machinePlays();
				if (machineMovement != null) {
					this.doMovement(GameLogic.PLAYER_TWO, machineMovement
							.getColumn(), machineMovement.getRow());
				}

				// if opponent is blocked machine must continue playing
				machineHasToPlay = this.gameLogic
						.isBlockedPlayer(GameLogic.PLAYER_ONE)
						&& !this.gameLogic
								.isBlockedPlayer(GameLogic.PLAYER_TWO);
			}
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
	 * makes a movement, conquer positions, update possible positions, toggles
	 * player
	 * 
	 * @param player
	 * @param col
	 * @param row
	 */
	private void doMovement(int player, int col, int row) {

		if (this.gameLogic.canSet(player, col, row)) {
			this.gameLogic.setStone(player, col, row);
			this.gameLogic.conquerPosition(player, col, row);
			this.gameLogic.refreshMovilityTable();
			this.togglePlayer();
		}
		this.notifyChanges();
	}

	/**
	 * Notifies the listener for the changes occured in the game
	 */
	private void notifyChanges() {
		if (this.gameEventsListener != null) {

			int p1 = this.gameLogic.getCounterForPlayer(PLAYER_ONE);
			int p2 = this.gameLogic.getCounterForPlayer(PLAYER_TWO);

			this.gameEventsListener.onScoreChanged(p1, p2);

			if (this.gameLogic.isFinished()) {
				int winner = NONE;
				if (p1 > p2) {
					winner = GameLogic.PLAYER_ONE;
				} else if (p2 > p1) {
					winner = GameLogic.PLAYER_TWO;
				}

				this.gameEventsListener.onGameFinished(winner);
			}

		}
	}

	/**
	 * Changes the player
	 */
	private void togglePlayer() {

		int current = this.gameLogic.getCurrentPlayer();
		// if the next player can play (has at least one place to put the
		// chip)
		if (!this.gameLogic.isBlockedPlayer(GameHelper.opponent(current))) {
			// just toggles
			this.gameLogic.setCurrentPlayer(GameHelper.opponent(current));
		} else {
			System.out.println(String.format(
					"player %d cannot play!!!!!!!!!!!!!!!!!!!", GameHelper
							.opponent(current)));
		}

	}

	private Movement machinePlays() {

		AI ai = new AI(this.gameLogic.getBoard());
		Movement best = ai.getBestMove(getCurrentPlayer());
		return best;
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

	/**
	 * If true is playing against droid
	 */
	@Override
	public void setMachineOpponent(boolean machineOpponent) {
		this.isMachineOpponent = machineOpponent;
	}

	
}
