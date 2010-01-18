package org.cherchi.reversi.logic;

import static org.junit.Assert.*;

import org.cherchi.reversi.logic.internal.AI;
import org.cherchi.reversi.logic.internal.Board;
import org.cherchi.reversi.logic.internal.Movement;
import org.junit.Test;

public class AITest {
	
	/**
	 * Tests that a get best move is properly performed
	 */
	@Test
	public void testGetBestMove() {
		
		Board b = new Board();
		AI ai = new AI(b);
		
		this.prepareBoard(b);
		Movement bestMove = ai.getBestMove(GameLogic.PLAYER_TWO);
		int x = bestMove.getColumn();
		int y = bestMove.getRow();
		assertTrue("Best move should be 2, 3 or 4, 5", 
				x == 2 && y == 3 || x == 4 && y == 5);
		
		
	}
	
	/**
	 * Prepares the board for the get best move test
	 * @param b
	 */
	private void prepareBoard(Board b) {
		
		b.setStone(3, 5, GameLogic.PLAYER_ONE);
		b.setStone(3, 4, GameLogic.PLAYER_ONE);
		
//		for (int i = 0; i < GameLogic.COLS; i++) {
//			for (int j = 0; j < GameLogic.ROWS; j++) {
//				b.setStone(i, j, GameLogic.PLAYER_ONE);
//			}
//		}
//		
		
	}
}
