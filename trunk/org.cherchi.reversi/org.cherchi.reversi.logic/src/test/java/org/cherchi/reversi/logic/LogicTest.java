package org.cherchi.reversi.logic;

import static org.junit.Assert.*;

import org.cherchi.reversi.logic.GameLogic;
import org.cherchi.reversi.logic.internal.Board;
import org.cherchi.reversi.logic.internal.GameLogicImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test of the logic
 * 
 * @author Fernando Cherchi
 * 
 */
public class LogicTest {

	// /////////////////////// FIELDS ///////////////////////////////

	/**
	 * This is the object to test
	 */
	GameLogic game = null;

	// /////////////////////// PREPARE ///////////////////////////////

	
	/**
	 * initializing
	 * 
	 */
	@Before
	public void beforeTest() {
		game = new GameLogicImpl(new Board());
	}

	/**
	 * Tests counter
	 */
	@Test
	public void testCounters() {

		// initially there must be 2 and 2
		assertEquals("It must be 2 player one stones", 2, game
				.getCounterForPlayer(GameLogic.PLAYER_ONE));
		assertEquals("It must be 2 player two stones", 2, game
				.getCounterForPlayer(GameLogic.PLAYER_TWO));

	}

	/**
	 * Tests that cannot set a chip in a busy cell
	 */
	@Test
	public void testCannotSetWhenCellIsBusy() {

		// player one sets a chip into 2,2
		game.setStone(GameLogic.PLAYER_ONE, 2, 2);
		assertFalse("Should not put a chip in a busy cell", game.canSet(
				GameLogic.PLAYER_TWO, 2, 2));
	}

	/**
	 * Test in the upwards direction if a chip can be put
	 */
	@Test
	public void testCanSetIfOwnChipUpwards() {
		game.setStone(GameLogic.PLAYER_ONE, 1, 0);
		game.setStone(GameLogic.PLAYER_TWO, 1, 1);
		assertTrue("Should be able to put a chip if encloses an opponent chip",
				game.canSet(GameLogic.PLAYER_ONE, 1, 2));
		// but if there is an empty space, should not be able to put
		game.setStone(GameLogicImpl.EMPTY, 1, 1);
		assertFalse(
				"Should be able to put a chip if encloses an opponent chip after empty spaces",
				game.canSet(GameLogic.PLAYER_ONE, 1, 2));
	}

	/**
	 * Test in the upwards direction if a chip can be put
	 */
	@Test
	public void testCannotSetIfNoOwnChipUpwards() {
		game.setStone(GameLogic.PLAYER_TWO, 1, 1);
		assertFalse(
				"Should not be able to put a chip if encloses an opponent chip",
				game.canSet(GameLogic.PLAYER_ONE, 1, 2));
	}

	/**
	 * Test in the downwards direction if a chip can be put
	 */
	@Test
	public void testCanSetIfOwnChipDownwards() {
		game.setStone(GameLogic.PLAYER_ONE, 1, 3);
		game.setStone(GameLogic.PLAYER_TWO, 1, 2);
		game.setStone(GameLogic.PLAYER_TWO, 1, 1);
		assertTrue("Should be able to put a chip if encloses an opponent chip",
				game.canSet(GameLogic.PLAYER_ONE, 1, 0));

		// but if there is an empty space, should not be able to put
		game.setStone(GameLogicImpl.EMPTY, 1, 2);
		assertFalse(
				"Should be able to put a chip if encloses an opponent chip after empty spaces",
				game.canSet(GameLogic.PLAYER_ONE, 1, 0));

	}

	/**
	 * Test in the downwards direction if a chip can be put
	 */
	@Test
	public void testCanSetIfOwnChipRight() {
		game.setStone(GameLogic.PLAYER_ONE, 7, 1);
		game.setStone(GameLogic.PLAYER_TWO, 6, 1);
		game.setStone(GameLogic.PLAYER_TWO, 5, 1);

		assertTrue("Should be able to put a chip if encloses an opponent chip",
				game.canSet(GameLogic.PLAYER_ONE, 4, 1));

		// but if there is an empty space, should not be able to put
		game.setStone(GameLogicImpl.EMPTY, 5, 1);
		assertFalse(
				"Should not be able to put a chip if encloses an opponent chip after empty spaces",
				game.canSet(GameLogic.PLAYER_ONE, 4, 1));

	}

	/**
	 * Test in the downwards direction if a chip can be put
	 */
	@Test
	public void testConquerPositions() {
		game.setStone(GameLogic.PLAYER_ONE, 7, 2);
		game.setStone(GameLogic.PLAYER_TWO, 6, 2);
		game.setStone(GameLogic.PLAYER_TWO, 5, 2);
		game.conquerPosition(GameLogic.PLAYER_ONE, 4, 2);
		assertEquals("Position 5 in row 2 should change color",
				GameLogic.PLAYER_ONE, game.getGameMatrix()[5][2]);
		assertEquals("Position 6 in row 2 should change color",
				GameLogic.PLAYER_ONE, game.getGameMatrix()[5][2]);
	}

	/**
	 * test the allowed positions are well calculated
	 */
	@Test
	public void testAllowedPositions() {

		// playing with initial positions
		int[][] allowed = game
				.getAllowedPositionsForPlayer(GameLogic.PLAYER_ONE);

		assertEquals("position 5, 3 should be allowed", GameLogic.PLAYER_ONE,
				allowed[5][3]);
		assertEquals("position 2, 4 should be allowed", GameLogic.PLAYER_ONE,
				allowed[2][4]);
		assertEquals("position 4, 2 should be allowed", GameLogic.PLAYER_ONE,
				allowed[4][2]);
		assertEquals("position 3, 5 should be allowed", GameLogic.PLAYER_ONE,
				allowed[3][5]);

		boolean otherAllowed = false;
		// scanning the grid
		for (int col = 0; col < GameLogic.COLS; col++) {
			for (int row = 0; row < GameLogic.ROWS; row++) {
				// if other is allowed then fails
				if (allowed[col][row] == GameLogic.PLAYER_ONE) {
					if (!(col == 2 && row == 4 || col == 5 && row == 3)
							&& (col == 4 && row == 2) && (col == 2 && row == 5)) {
						otherAllowed = true;
					}
				}
			}
		}
		assertFalse("should not be more allowed places", otherAllowed);

	}

	/**
	 * Test in the downwards direction if a chip can be put
	 */
	@Test
	public void testCanSetIfOwnChipLeft() {
		game.setStone(GameLogic.PLAYER_ONE, 4, 4);
		game.setStone(GameLogic.PLAYER_TWO, 5, 4);
		game.setStone(GameLogic.PLAYER_TWO, 6, 4);
		assertTrue("Should be able to put a chip if encloses an opponent chip",
				game.canSet(GameLogic.PLAYER_ONE, 7, 4));
		// but if there is an empty space, should not be able to put
		game.setStone(GameLogicImpl.EMPTY, 5, 4);
		assertFalse(
				"Should be able to put a chip if encloses an opponent chip after empty spaces",
				game.canSet(GameLogic.PLAYER_ONE, 7, 4));
	}

	/**
	 * Test in the left-up direction if a chip can be put
	 */
	@Test
	public void testCanSetIfOwnChipLeftUp() {
		game.setStone(GameLogic.PLAYER_TWO, 2, 2);
		assertTrue("Should be able to put a chip if encloses an opponent chip",
				game.canSet(GameLogic.PLAYER_TWO, 5, 5));

		// but if there is an empty space, should not be able to put
		game.setStone(GameLogicImpl.EMPTY, 4, 4);
		assertFalse(
				"Should be able to put a chip if encloses an opponent chip after empty spaces",
				game.canSet(GameLogic.PLAYER_TWO, 5, 5));
	}

	/**
	 * Test in the left-down direction if a chip can be put
	 */
	@Test
	public void testCanSetIfOwnChipLeftDown() {
		game.setStone(GameLogic.PLAYER_ONE, 0, 3);
		game.setStone(GameLogic.PLAYER_TWO, 1, 2);
		game.setStone(GameLogic.PLAYER_TWO, 2, 1);
		assertTrue("Should be able to put a chip if encloses an opponent chip",
				game.canSet(GameLogic.PLAYER_ONE, 3, 0));

		// but if there is an empty space, should not be able to put
		game.setStone(GameLogicImpl.EMPTY, 5, 2);
		assertFalse(
				"Should be able to put a chip if encloses an opponent chip after empty spaces",
				game.canSet(GameLogic.PLAYER_ONE, 1, 0));
	}

	/**
	 * Test in the right-down direction if a chip can be put
	 */
	@Test
	public void testCanSetIfOwnChipRightDown() {
		game.setStone(GameLogic.PLAYER_ONE, 4, 3);
		game.setStone(GameLogic.PLAYER_TWO, 2, 1);
		game.setStone(GameLogic.PLAYER_TWO, 3, 2);
		assertTrue("Should be able to put a chip if encloses an opponent chip",
				game.canSet(GameLogic.PLAYER_ONE, 1, 0));

		// but if there is an empty space, should not be able to put
		game.setStone(GameLogicImpl.EMPTY, 3, 2);
		assertFalse(
				"Should be able to put a chip if encloses an opponent chip after empty spaces",
				game.canSet(GameLogic.PLAYER_ONE, 1, 0));
	}

	/**
	 * Test in the right-down direction if a chip can be put
	 */
	@Test
	public void testCanSetIfOwnChipRightUp() {
		game.setStone(GameLogic.PLAYER_ONE, 3, 0);
		game.setStone(GameLogic.PLAYER_TWO, 2, 1);
		game.setStone(GameLogic.PLAYER_TWO, 1, 2);
		assertTrue("Should be able to put a chip if encloses an opponent chip",
				game.canSet(GameLogic.PLAYER_ONE, 0, 3));

		// but if there is an empty space, should not be able to put
		game.setStone(GameLogic.EMPTY, 2, 1);
		assertFalse(
				"Should be able to put a chip if encloses an opponent chip after empty spaces",
				game.canSet(GameLogic.PLAYER_ONE, 1, 0));
	}
	
	/**
	 * Testing the is blocked method
	 */
	@Test
	public void testIsBlocked() {
		
		TestHelper.cleanBoard(game);
		
		// setting a blocking situation for player 2
		game.setStone(GameLogic.PLAYER_TWO, 0, 7);
		game.setStone(GameLogic.PLAYER_ONE, 0, 6);
		game.setStone(GameLogic.PLAYER_ONE, 1, 6);
		game.setStone(GameLogic.PLAYER_ONE, 1, 7);
		game.setStone(GameLogic.PLAYER_TWO, 0, 5);
		game.setStone(GameLogic.PLAYER_TWO, 1, 5);
		game.setStone(GameLogic.PLAYER_TWO, 2, 5);
		game.setStone(GameLogic.PLAYER_TWO, 2, 6);
		game.setStone(GameLogic.PLAYER_TWO, 2, 7);

		assertTrue("Player 2 should be blocked", game
				.isBlockedPlayer(GameLogic.PLAYER_TWO));
		assertFalse("Player 1 shouldn't be blocked", game
				.isBlockedPlayer(GameLogic.PLAYER_ONE));
	}
	
	
	/**
	 * Tests the is finished method
	 */
	@Test
	public void testIsFinished() {
		
		//if all stones are from the same player is finished
		TestHelper.fillAllBoardWithPlayer(GameLogic.PLAYER_ONE, game);
		
		// setting a blocking situation for player 2
		game.setStone(GameLogic.PLAYER_TWO, 0, 7);
		game.setStone(GameLogic.PLAYER_ONE, 0, 6);
		game.setStone(GameLogic.PLAYER_ONE, 1, 6);
		game.setStone(GameLogic.PLAYER_ONE, 1, 7);
		game.setStone(GameLogic.PLAYER_TWO, 0, 5);
		game.setStone(GameLogic.PLAYER_TWO, 1, 5);
		game.setStone(GameLogic.PLAYER_TWO, 2, 5);
		game.setStone(GameLogic.PLAYER_TWO, 2, 6);
		game.setStone(GameLogic.PLAYER_TWO, 2, 7);

		
		assertTrue("Should be finished", this.game.isFinished());
	}
	
	
	
	
	

}
