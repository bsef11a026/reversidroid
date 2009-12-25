package org.cherchi.reversi.logic.internal;

import org.cherchi.reversi.logic.Direction;
import org.cherchi.reversi.logic.GameLogic;

public class GameLogicImpl implements GameLogic {

	// /////////////////////// CONSTANTS ///////////////////////////////
	
	 

	// /////////////////////// PRIVATE FIELDS //////////////////////////

	/**
	 * The matrix
	 */
	private int[][] gameMatrix = new int[COLS][ROWS];

	/**
	 * Just a helper.
	 */
	private MatrixChecker matrixChecker = new MatrixChecker(gameMatrix);

	/**
	 * The player that has to play
	 */
	private int currentPlayer = GameLogic.PLAYER_ONE;

	// /////////////////////// LIFETIME ////////////////////////////////
	/**
	 * initializes the matrix
	 */
	public GameLogicImpl() {
		this.initializeMatrix();
	}

	// /////////////////////// PUBLIC METHODS //////////////////////////

	/**
	 * Returns the game status
	 */
	@Override
	public int[][] getGameMatrix() {

		return this.gameMatrix;
	}

	/**
	 * Informs if a player can set a stick in a given position
	 */
	@Override
	public boolean canSet(int player, int col, int row) {

		if (col >= 0 && col < COLS && row >= 0 && row < ROWS) {
			
			return this.matrixChecker.canSet(player, col, row);
		} else {
			//not a good column or row
			return false;
		}
	}

	@Override
	public boolean isBlockedPlayer(int player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Sets a chip in a given place
	 */
	@Override
	public void setChip(int player, int col, int row) {

		if (col < COLS && row < ROWS) {
			this.gameMatrix[col][row] = player;
		}
	}

	/**
	 * Returns all the allowed positions for a player
	 */
	@Override
	public int[][] getAllowedPositionsForPlayer(int player) {

		int[][] allowedPositions = new int[COLS][ROWS];

		// scanning the grid
		for (int col = 0; col < COLS; col++) {
			for (int row = 0; row < ROWS; row++) {
				// if player can set
				if (this.matrixChecker.canSet(player, col, row)) {
					allowedPositions[col][row] = player;
				}
			}
		}
		return allowedPositions;
	}

	
	/**
	 * Conquers the positions in all directions (if the player is enclosing opponent pieces, 
	 * just converting them to player color) 
	 */
	@Override
	public void conquerPosition(int player, int column, int row) {
		
		//in each direction, if we are enclosing opponent chips, conquering...
		if (this.matrixChecker.isEnclosingUpwards(player, column, row)) {
			this.conquer(player, column, row, Direction.X.NONE, Direction.Y.UP);
		}
		if (this.matrixChecker.isEnclosingDownwards(player, column, row)) {
			this.conquer(player, column, row, Direction.X.NONE, Direction.Y.DOWN);
		}
		if (this.matrixChecker.isEnclosingRight(player, column, row)) {
			this.conquer(player, column, row, Direction.X.RIGHT, Direction.Y.NONE);
		}
		if (this.matrixChecker.isEnclosingLeft(player, column, row)) {
			this.conquer(player, column, row, Direction.X.LEFT, Direction.Y.NONE);
		}
		if (this.matrixChecker.isEnclosingLeftAndDown(player, column, row)) {
			this.conquer(player, column, row, Direction.X.LEFT, Direction.Y.DOWN);
		}
		if (this.matrixChecker.isEnclosingLeftAndUp(player, column, row)) {
			this.conquer(player, column, row, Direction.X.LEFT, Direction.Y.UP);
		}
		if (this.matrixChecker.isEnclosingRightAndDown(player, column, row)) {
			this.conquer(player, column, row, Direction.X.RIGHT, Direction.Y.DOWN);
		}
		if (this.matrixChecker.isEnclosingRightAndUp(player, column, row)) {
			this.conquer(player, column, row, Direction.X.RIGHT, Direction.Y.UP);
		}

	}
	
	/**
	 * Gets the number of stones for a given player
	 * @return 
	 */
	@Override
	public int getCounterForPlayer(int player) {
		
		int counter = 0;
		for (int i = 0; i < COLS; i ++) {
			for (int j = 0; j < ROWS; j ++) {
				if (this.gameMatrix[i][j] == player) {
					counter++;
				}
			}
		}
		return counter;
	}

	// /////////////////////// PRIVATE METHODS //////////////////////////

	/**
	 * Changes all the opponent chips to the player color in the given direction
	 * @param player
	 * the current player
	 * @param column
	 * the column where the player sets the chip (starting point of the conquer)
	 * @param row
	 * the row where the player sets the chip 
	 * @param xDirection 
	 * the direction in the x axis (left or right or none )
	 * @param yDirection
	 * the direction in the y axis (up or down or none) 
	 */
	private void conquer (int player, int column, int row, int xDirection, int yDirection) {
		
		int x = column;
		int y = row;
		boolean ownChip = false;
		
		//conquer until an own chip is found
		while ( !ownChip ) {
			//advancing in the given direction
			x += xDirection;
			y += yDirection;
			
			//if is not an own chip
			if (this.gameMatrix[x][y] != player) {
				this.gameMatrix[x][y] = player;
			} else {
				ownChip = true;
			}
		}
	}
	
	/**
	 * initializes the game
	 */
	private void initializeMatrix() {
		for (int col = 0; col < COLS; col++) {
			for (int row = 0; row < ROWS; row++) {
				this.gameMatrix[col][row] = EMPTY;
			}
		}
		this.gameMatrix[3][3] = GameLogic.PLAYER_ONE;
		this.gameMatrix[4][4] = GameLogic.PLAYER_ONE;
		this.gameMatrix[3][4] = GameLogic.PLAYER_TWO;
		this.gameMatrix[4][3] = GameLogic.PLAYER_TWO;
	}

	
	// /////////////////////// ACCESSORS ////////////////////////////
	
	/**
	 * Gets the current player
	 * @return the currentPlayer
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Sets the current player
	 */
	@Override
	public void setCurrentPlayer(int player) {
		this.currentPlayer = player;
	}

	
	

}
