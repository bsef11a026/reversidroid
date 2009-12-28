package org.cherchi.reversi.logic;

/***
 * Test helper
 * @author Fernando Cherchi
 *
 */
public class TestHelper {
	
	/**
	 * Removes all positions
	 */
	public static void cleanBoard(GameLogic game) {
		for (int col = 0; col < GameLogic.COLS; col++) {
			for (int row = 0; row < GameLogic.COLS; row++) {
				game.setChip(GameLogic.EMPTY, col, row);
			}
		}
	}
	
	/**
	 * Fills all the board with stones of the given player
	 * @param player
	 */
	public static void fillAllBoardWithPlayer(int player, GameLogic game) {
		for (int col = 0; col < GameLogic.COLS; col++) {
			for (int row = 0; row < GameLogic.COLS; row++) {
				game.setChip(player, col, row);
			}
		}
	}
}
