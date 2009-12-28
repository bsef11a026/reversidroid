package org.cherchi.reversi.view;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Settings manager
 * 
 * @author Fernando Cherchi
 * 
 */
public class Settings extends PreferenceActivity {
	
	
	// ///////////////////// CONSTANTS ///////////////////////////////////////
	/**
	 * The key for the show allowed position value
	 */
	private static final String SHOW_ALLOWED_POS = "show_allowed_positions";

	
	// ///////////////////// OVERRIDES //////////////////////////////////////
	/**
	 * adding preferences from res
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.addPreferencesFromResource(R.xml.settings);
	}

	// ///////////////////// ACCESSORS ///////////////////////////////////////
	
	/**
	 * Get the 'show allowed positions' setting
	 */
	public static boolean getShowAllowedPositions(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(SHOW_ALLOWED_POS, true);
	}

}
