package org.cherchi.reversi.logic.test;
import static org.junit.Assert.assertEquals;

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
		this.logic = new GameLogicImpl();
		
		this.facade.setGameLogic(logic);
	}
	
	
	// /////////////////////// TESTS ////////////////////////////////

	/**
	 * Tests that a chip can be set
	 */
	@Test
	public void testSet() {
		
		//initial position should allow set a chip to player one
		this.facade.set(GameFacadeImpl.PLAYER_ONE, 5, 3);
		//if the set is ok, player should change
		assertEquals("player should be 2.", GameFacadeImpl.PLAYER_TWO, this.facade.getCurrentPlayer());
		
		//if cannot set, the player should be the same
		this.facade.set(GameFacadeImpl.PLAYER_TWO, 5, 3);
		//if the set is not ok, player should not change
		assertEquals("player should be 2", GameFacadeImpl.PLAYER_TWO, this.facade.getCurrentPlayer());
	}
}
