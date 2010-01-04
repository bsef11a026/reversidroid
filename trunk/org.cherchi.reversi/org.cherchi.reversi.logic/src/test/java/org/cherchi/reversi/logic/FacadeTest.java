package org.cherchi.reversi.logic;

import static org.junit.Assert.assertEquals;

import org.cherchi.reversi.logic.internal.Board;
import org.cherchi.reversi.logic.internal.GameFacadeImpl;
import org.cherchi.reversi.logic.internal.GameLogicImpl;
import org.junit.Before;
import org.junit.Test;

public class FacadeTest {

	// /////////////////////// FIELDS ////////////////////////////////

	/**
	 * The object to be tested
	 */
	private GameFacadeImpl facade;

	private GameLogicImpl logic;

	// /////////////////////// PREPARE ///////////////////////////////

	@Before
	public void prepare() {

		this.facade = new GameFacadeImpl();
		this.logic = new GameLogicImpl(new Board());

		this.facade.setGameLogic(logic);
	}

	// /////////////////////// TESTS ////////////////////////////////

	/**
	 * Tests that a chip can be set
	 */
	@Test
	public void testSet() {

		this.facade.setMachineOpponent(false);
		// initial position should allow set a chip to player one
		this.facade.set(GameLogic.PLAYER_ONE, 5, 3);
		// if the set is ok, player should change
		assertEquals("player should be 2.", GameFacadeImpl.PLAYER_TWO,
				this.facade.getCurrentPlayer());

		// if cannot set, the player should be the same
		this.facade.set(GameLogic.PLAYER_TWO, 5, 3);
		// if the set is not ok, player should not change
		assertEquals("player should be 2", GameFacadeImpl.PLAYER_TWO,
				this.facade.getCurrentPlayer());
	}

	/**
	 * Test the restart function
	 */
	@Test
	public void testRestart() {

		// initial position should allow set a chip to player one
		this.facade.setMachineOpponent(false);
		this.facade.set(GameLogic.PLAYER_ONE, 5, 3);
		assertEquals("Before restart, score should be 4", 4, this.facade
				.getScoreForPlayer(GameLogic.PLAYER_ONE));
		this.facade.restart();
		assertEquals("After restart, score should be 2", 2, this.facade
				.getScoreForPlayer(GameLogic.PLAYER_ONE));

	}

	/**
	 * Testing that when a player cannot set, the turn goes to the other player
	 */
	@Test
	public void testSkipPlay() {

		// all board is for player 1
		TestHelper.fillAllBoardWithPlayer(GameLogic.PLAYER_ONE, this.logic);

		// preparing first player 2 movement
		this.logic.setStone(GameLogic.EMPTY, 2, 0);
		this.logic.setStone(GameLogic.PLAYER_TWO, 2, 2);

		// preparing second player 2 movement
		this.logic.setStone(GameLogic.EMPTY, GameLogic.COLS - 1, 0);
		this.logic.setStone(GameLogic.PLAYER_TWO, 0, 0);

		// preparing the last time player 1 plays
		this.logic.setStone(GameLogic.EMPTY, 0, 0);
		this.logic.setStone(GameLogic.PLAYER_TWO, 0, 1);

		// setting last time player 1
		this.facade.set(GameLogic.PLAYER_ONE, 0, 0);

		// now the one playing is player 2 (normal case)
		assertEquals("Player 2 should be playing", GameLogic.PLAYER_TWO,
				this.facade.getCurrentPlayer());

		// setting player 2 (normal case, but should skip player 1)
		this.facade.set(GameLogic.PLAYER_TWO, 0, 2);

		// player 2 should be playing again (skip turn)
		assertEquals("Player 2 should be playing, skip unsuccessful",
				GameLogic.PLAYER_TWO, this.facade.getCurrentPlayer());

	}

}
