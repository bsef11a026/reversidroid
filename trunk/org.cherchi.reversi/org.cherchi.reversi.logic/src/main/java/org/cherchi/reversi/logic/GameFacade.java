package org.cherchi.reversi.logic;

public interface GameFacade {
	
	
	/**
	 * players
	 */
	public static int PLAYER_ONE = 1;
	
	public static int PLAYER_TWO = 2;
	
	/**
	 * starts a new game
	 */
	void restart();
	
	
	
	
	/**
	 * Sets a chip in the given position
	 * @param player
	 * @param col
	 * @param row
	 */
	void set (int player, int col, int row);
	
	
	/**
	 * Gets the current player
	 * @return
	 */
	int getCurrentPlayer();



	/**
	 * Gets the matrix of allowed positions of the player that has to play
	 * @return
	 */
	int[][] getAllowedPositionsForPlayer();


	
	/**
	 * Gets the current matrix of the game
	 * @return
	 */
	int[][] getGameMatrix();
	
	/**
	 * @param gameLogic the gameLogic to set
	 */
	public void setGameLogic(GameLogic gameLogic);

}
