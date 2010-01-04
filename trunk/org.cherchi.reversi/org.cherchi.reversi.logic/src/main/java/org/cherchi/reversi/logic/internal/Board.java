package org.cherchi.reversi.logic.internal;

import org.cherchi.reversi.logic.GameLogic;

public class Board {

	// /////////////////////// CONSTANTS ///////////////////////////////
	/***
	 * Board dimensions
	 */
	public static int COLS = 8;

	public static int ROWS = 8;
	

	// /////////////////////// PRIVATE FIELDS //////////////////////////

	/**
	 * The matrix
	 */
	private int[][] gameMatrix = new int[COLS][ROWS];
	
	/**
	 * The situation of all the columns and rows. 
	 * The integer is an unique value that identifies the column status
	 */
	//private int[] columns = new int[COLS];
	//private int[] rows = new int[ROWS];
	//private int[] northWest = new int[COLS + ROWS - 1];
	//private int[] northEast = new int[COLS + ROWS - 1];

	/**
	 * the scores for both players
	 */
	private int[] scores = new int[2];
	
	
	// /////////////////////// LIFETIME ///////////////////////////////

	public Board() {
		this.initializeMatrix();
	}

	// /////////////////////// PUBLIC METHODS //////////////////////////

	/**
	 * Sets the given stone in the given coordinate and calculates mobility and
	 * location values
	 */
	public void setStone(int col, int row, int player) {

		//if the position was occupied by other player, 
		//reducing the score
		if (this.gameMatrix[col][row] != GameLogic.EMPTY) {
			scores[this.gameMatrix[col][row]-1]--;
		}
		this.gameMatrix[col][row] = player;
		if (player != GameLogic.EMPTY) {
			scores[player - 1]++;
		}
		//this.updatePosition(col, row, player);
	}
	
	

	/**
	 * Returns a new representation that is an exact clone of this board
	 */
	public Board clone() {
		Board cloned = new Board();
		
		int[][] clonedMatrix = new int[GameLogic.COLS][GameLogic.ROWS];
		
		for(int i = 0; i < GameLogic.COLS; i++) {
			for (int j = 0; j < GameLogic.ROWS; j++) {
				clonedMatrix[i][j] = this.gameMatrix[i][j];
			}
		}
		
		//cloned.setMatrix(this.gameMatrix.clone());
		cloned.setMatrix(clonedMatrix);
		cloned.setCounterForPlayer(GameLogic.PLAYER_ONE, this.scores[0]);
		cloned.setCounterForPlayer(GameLogic.PLAYER_TWO, this.scores[1]);
		//System.out.println(GameHelper.counter++);
		return cloned;
	}
	
	/**
	 * Returns a string representation of the matrix
	 */
	public String toString() {
		
		StringBuffer str = new StringBuffer();
		str.append("\n");
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				str.append(String.format("|%d|", this.gameMatrix[col][row]));
			}
			str.append("\n");
		}
		
		return str.toString();
	}
	
	// /////////////////////// PRIVATE METHODS //////////////////////////
	/**
	 * initializes the game matrix
	 */
	private void initializeMatrix() {
		for (int col = 0; col < COLS; col++) {
			for (int row = 0; row < ROWS; row++) {
				this.gameMatrix[col][row] = GameLogic.EMPTY;
			}
		}
		this.setStone(3, 3, GameLogic.PLAYER_ONE);
		this.setStone(4, 4, GameLogic.PLAYER_ONE);
		this.setStone(3, 4, GameLogic.PLAYER_TWO);
		this.setStone(4, 3, GameLogic.PLAYER_TWO);
	}
	
	/**
	 * Updates the indicators that defines the position
	 * @param player 
	 * @param row 
	 * @param col 
	 */
//	private void updatePosition(int col, int row, int player) {
//		
//		// the position indicator in the column is such that 
//		// 3^n*player being n a value that indicates the column
//		// (in fact is reversed), so, 0 is an empty column
//		// 3 is a 1 (player one) in the column 7 (power 0)
//		// 6 is a 2 (player two) in the column 7 (because (3^0)*player is 1*2 = 2)
//		// and so on
//		
//		int posX = 7 - col;
//		int posY = 7 - row;
//		
//		//power is used also for the columns to simplify
//		this.columns[posX] += GameHelper.pow(3, posX);
//		
//		this.rows[posY] += GameHelper.pow(3, posY);
//		this.updatePositionNorthWest(col, row, player);
//		this.updatePositionNorthEast(col, row, player);
//	}
	
	/**
	 * Stores the update position in the north west axis (\)
	 * @param col
	 * @param row
	 * @param player
	 */
//	private void updatePositionNorthWest(int col, int row, int player) {
//		int nwIndex = 7 - row + col; //its a way to identify the \ axis
//		//getting the array position indicator (the min value of row and column will be good enough)
//		int pos = col;
//		if (row < col) {
//			pos = row;
//		}
//		this.northWest[nwIndex] += GameHelper.pow(3, pos);
//	}
	
	/**
	 * Stores the update position in the north east axis (/)
	 * @param col
	 * @param row
	 * @param player
	 */
//	private void updatePositionNorthEast(int col, int row, int player) {
//		int neIndex = row + col; //its a way to identify the / axis
//		//getting the array position indicator (the min value of row and column will be good enough)
//		int pos = col;
//		if (row < col) {
//			pos = row;
//		} 
//		this.northEast[neIndex] = GameHelper.pow(3, pos);
//	}
	
	

	// /////////////////////// ACCESSORS //////////////////////////
	/**
	 * Gets the matrix (read only)
	 */
	public int[][] getMatrix() {
		return this.gameMatrix.clone();
	}
	
	/**
	 * Sets the given matrix 
	 * @param matrix
	 */
	public void setMatrix(int[][] matrix) {
		this.gameMatrix = matrix;
	}

	/**
	 * Gets the current score for the given player
	 * 
	 * @param player
	 * @return
	 */
	public int getCounterForPlayer(int player) {

		return scores[player - 1];
	}

	/**
	 * Sets the given score for the given player
	 * @param player
	 * @param score
	 */
	public void setCounterForPlayer(int player, int score) {
		scores[player - 1] = score;
	}
}
