package org.cherchi.reversi.logic.internal;

import java.util.List;

import org.cherchi.reversi.logic.GameLogic;

public class AI {

	/**
	 * The number of movements the machine will go further
	 */
	private int depth = 1;

	private Movement bestMoveP1 = null;
	private Movement bestMoveP2 = null;

	/**
	 * The board (initially a clone of the real one)
	 */
	private Board board;

	private int[] sign = new int[] { 1, -1 };

	public AI(Board board) {
		this.board = board;
	}

	/**
	 * Return the best movement for the given player
	 * 
	 * @param player
	 * @return
	 */
	public Movement getBestMove(int player) {

		int color = player - 1;
		int currDepth = 0;
		this.negaMax(this.board, currDepth, color);
		if (player == GameLogic.PLAYER_ONE) {
			return this.bestMoveP1;
		} else {
			return this.bestMoveP2;
		}
	}

	/**
	 * Negamax algorithm to look forward
	 * 
	 * @param board
	 * @param currentDepth
	 * @param color
	 * @return
	 */
	private int negaMax(Board board, int currentDepth, int color) {

		int player = color + 1;
		boolean isFinished = false;

		if (isFinished || currentDepth > this.depth) {
			return sign[color] * analysis(board, color);
		}
		// max play value of all movements
		int max = Integer.MIN_VALUE + 1;

		List<Movement> movements = GameHelper.getAllowedMovementsForPlayer(
				player, board);
		if (movements.size() == 0) {
			isFinished = true;
		}
		for (Movement movement : movements) {

			Board newMovementBoard = board.clone();
			GameLogic gl = new GameLogicImpl(newMovementBoard);

			gl.setStone(movement.getPlayer(), movement.getColumn(), movement
					.getRow());
			gl.conquerPosition(movement.getPlayer(), movement.getColumn(),
					movement.getRow());
			int x = -negaMax(newMovementBoard, currentDepth + 1, 1 - color);
			if (x > max) {
				max = x;
				if (player == GameLogic.PLAYER_ONE) {
					this.bestMoveP1 = movement;
				} else {
					this.bestMoveP2 = movement;
				}
			}
		}
		return max;
	}

	/**
	 * Analyze the situation regarding mobility.
	 * 
	 * @param board
	 * @param color
	 * @return
	 */
	private int analysis(Board board, int color) {

		int[][] matrix = board.getMatrix();
		int player = color + 1;
		int mobility = 0;
		for (int col = 0; col < GameLogic.COLS; col++) {
			for (int row = 0; row < GameLogic.ROWS; row++) {
				// looking for own mobility
				if (matrix[col][row] == GameHelper.opponent(player)
						&& this.isNextToEmtpySpace(col, row, matrix)) {
					mobility++;
				}
			}
		}
		return mobility;
	}

	private boolean isNextToEmtpySpace(int col, int row, int[][] matrix) {

		if (col > 0 && matrix[col - 1][row] == GameLogic.EMPTY) {
			return true;
		}
		if (col < GameLogic.COLS - 1 && matrix[col + 1][row] == GameLogic.EMPTY) {
			return true;
		}
		if (row > 0 && matrix[col][row - 1] == GameLogic.EMPTY) {
			return true;
		}
		if (row < GameLogic.ROWS - 1 && matrix[col][row + 1] == GameLogic.EMPTY) {
			return true;
		}
		if (col > 0 && row > 0 && matrix[col - 1][row - 1] == GameLogic.EMPTY) {
			return true;
		}
		if (col > 0 && row < GameLogic.ROWS - 1
				&& matrix[col - 1][row + 1] == GameLogic.EMPTY) {
			return true;
		}
		if (col < GameLogic.COLS - 1 && row > 0
				&& matrix[col + 1][row - 1] == GameLogic.EMPTY) {
			return true;
		}
		if (col < GameLogic.COLS - 1 && row < GameLogic.ROWS - 1
				&& matrix[col + 1][row + 1] == GameLogic.EMPTY) {
			return true;
		}

		return false;
	}
}
