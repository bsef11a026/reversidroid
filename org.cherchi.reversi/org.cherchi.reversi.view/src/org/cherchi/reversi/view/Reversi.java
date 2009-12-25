package org.cherchi.reversi.view;

import org.cherchi.reversi.logic.GameFacade;
import org.cherchi.reversi.logic.internal.GameFacadeImpl;
import org.cherchi.reversi.logic.internal.GameLogicImpl;

import android.app.Activity;
import android.os.Bundle;

public class Reversi extends Activity {
	// ///////////////////// PRIVATE FIELDS //////////////////////////////
	/**
	 * The game board
	 */
	private GameFacade gameFacade = null;

	// ///////////////////////// LIFETIME /////////////////////////////////

	
	// ///////////////////////// EVENTS /////////////////////////////////

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		this.setTitle("Rever-droid");
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		
		//retrieving the old facade if any
		//trying to recover the last version
		this.gameFacade = (GameFacade) this.getLastNonConfigurationInstance();
		//if is the first time... 
		if (gameFacade == null) {
			this.gameFacade = new GameFacadeImpl();
			this.gameFacade.setGameLogic(new GameLogicImpl());
		}
		
		GameBoard gameBoard = (GameBoard) this.findViewById(R.id.GameBoard01);
		gameBoard.setGameFacade(this.gameFacade);

	}

	@Override
	public Object onRetainNonConfigurationInstance() {

		return this.gameFacade;
	}

}