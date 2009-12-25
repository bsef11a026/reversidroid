package org.cherchi.reversi.view;

import android.app.Activity;
import android.os.Bundle;

public class Reversi extends Activity {
	// ///////////////////// PRIVATE FIELDS //////////////////////////////
	/**
	 * The game board
	 */
	GameBoard gameBoard = null;

	// ///////////////////////// EVENTS /////////////////////////////////

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		this.setTitle("Rever-droid");
		super.onCreate(savedInstanceState);

		//gameBoard = new GameBoard(this.getApplicationContext());

		//this.setContentView(gameBoard);
		this.setContentView(R.layout.main);

	}

}