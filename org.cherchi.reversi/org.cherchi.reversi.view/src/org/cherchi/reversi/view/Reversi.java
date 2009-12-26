package org.cherchi.reversi.view;

import org.cherchi.reversi.logic.GameEventsListener;
import org.cherchi.reversi.logic.GameFacade;
import org.cherchi.reversi.logic.internal.GameFacadeImpl;
import org.cherchi.reversi.logic.internal.GameLogicImpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.TextView;

public class Reversi extends Activity implements GameEventsListener {
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

		super.onCreate(savedInstanceState);
		
		this.setTitle("Rever-droid");
		this.setContentView(R.layout.main);

		// retrieving the old facade if any
		// trying to recover the last version
		this.gameFacade = (GameFacade) this.getLastNonConfigurationInstance();
		// if is the first time...
		if (gameFacade == null) {
			this.gameFacade = new GameFacadeImpl();
			this.gameFacade.setGameLogic(new GameLogicImpl());
		} else {
			this.refreshCounters();
		}
		//caution. "this" has been re-constructed after an orientation change... so need to 
		//subscribe as listener again
		this.gameFacade.setGameEventsListener(this);
		
		GameBoard gameBoard = (GameBoard) this.findViewById(R.id.gameBoard);
		gameBoard.setGameFacade(this.gameFacade);

	}

	/**
	 * Occurs when the user presses the menu key 
	 */
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		MenuInflater inflater = super.getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	
	/**
	 * Occurs when the user clicks in a menu option
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			super.startActivity(new Intent(this, Settings.class));
			break;
		case R.id.restart:
			this.gameFacade.restart();
		default:
			return false;
		}
		return true;
	}
	
	

	/**
	 * Provides the data to remember (if the screen changes orientation, the
	 * full class is rebuilt)
	 */
	@Override
	public Object onRetainNonConfigurationInstance() {

		return this.gameFacade;
	}
	
	

	/**
	 * Occurs when the score has changed... so refrewhing counters
	 */
	@Override
	public void onScoreChanged(int p1Score, int p2Score) {

		this.setPlayersCounters(p1Score, p2Score);
	}
	
	// ///////////////////////// REFRESH COUNTERS /////////////////////////////////

	/**
	 * Refreshes the counters after an orientation changing
	 */
	private void refreshCounters() {
		
		int p1 = this.gameFacade.getScoreForPlayer(GameFacade.PLAYER_ONE);
		int p2 = this.gameFacade.getScoreForPlayer(GameFacade.PLAYER_TWO);
		this.setPlayersCounters(p1, p2);
	}
	
	/**
	 * Draws the scores
	 */
	private void setPlayersCounters(int p1Score, int p2Score) {
		
		TextView txtP1 = (TextView) this.findViewById(R.id.txtPlayer1Counter);
		txtP1.setText(String.format(" %d", p1Score));
		TextView txtP2 = (TextView) this.findViewById(R.id.txtPlayer2Counter);
		txtP2.setText(String.format(" %d", p2Score));
	}
	
	



}